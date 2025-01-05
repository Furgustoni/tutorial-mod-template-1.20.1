package net.furgus.furgusmenagerie.block.custom;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.ModEntities;
import net.furgus.furgusmenagerie.entity.custom.HeiroglithEntity;
import net.furgus.furgusmenagerie.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import java.util.Random;

public class HeiroglithHead extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WRONG_ITEM_USED = BooleanProperty.of("wrong_item_used");

    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    protected static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(1.0, 0.0, 3.0, 15.0, 16.0, 13.0);

    private static final int RESET_DELAY_TICKS = 40; // 2 seconds (20 ticks per second)
    private static final int SOUND_COOLDOWN_TICKS = 5; // 0.25 seconds

    // Track the last time the failure sound was played
    private long lastFailSoundTime = 0;

    // Constructor
    public HeiroglithHead(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(HALF, DoubleBlockHalf.LOWER)
                .with(WRONG_ITEM_USED, false));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();

        // Check if the block is being placed directly above or below another instance of the same block
        if (world.getBlockState(pos.down()).isOf(this) || world.getBlockState(pos.up()).isOf(this)) {
            return null;
        }

        if (pos.getY() < world.getHeight() - 1 &&
                (world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.up()).canReplace(ctx))) {
            return this.getDefaultState()
                    .with(FACING, ctx.getHorizontalPlayerFacing())
                    .with(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), state.with(FACING, state.get(FACING))
                .with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.get(HALF);
        if (direction.getAxis() == Direction.Axis.Y) {
            if ((half == DoubleBlockHalf.LOWER && direction == Direction.UP) || (half == DoubleBlockHalf.UPPER && direction == Direction.DOWN)) {
                if (neighborState.isOf(this) && neighborState.get(HALF) != half) {
                    return state;
                } else {
                    return Blocks.AIR.getDefaultState();
                }
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            DoubleBlockHalf half = state.get(HALF);
            BlockPos otherHalfPos = (half == DoubleBlockHalf.LOWER) ? pos.up() : pos.down();
            BlockState otherHalfState = world.getBlockState(otherHalfPos);

            if (otherHalfState.isOf(this) && otherHalfState.get(HALF) != half) {
                world.breakBlock(otherHalfPos, false);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            ItemStack itemStack = player.getStackInHand(hand);

            if (itemStack.isOf(Items.DIAMOND)) {
                // Correct item used - spawn Heiroglith entity
                if (world instanceof ServerWorld serverWorld) {
                    HeiroglithEntity heiroglith = ModEntities.HEIROGLITH.create(serverWorld);
                    if (heiroglith != null) {
                        Vec3d spawnPos = Vec3d.ofBottomCenter(pos).add(0, 1.0, 0);
                        heiroglith.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                        heiroglith.setYaw(0.0F);
                        heiroglith.setPitch(0.0F);
                        serverWorld.spawnEntity(heiroglith);

                        itemStack.decrement(1); // Decrease diamond
                        world.playSound(null, pos, ModSounds.MONOGLYPH_HURT, SoundCategory.RECORDS, 1f, 1f);
                        world.breakBlock(pos, false); // Break the block after entity is spawned
                        return ActionResult.SUCCESS;
                    }
                }
            } else if (itemStack.isEmpty() && state.get(WRONG_ITEM_USED)) {
                // Reset texture immediately when clicked with empty hand
                world.setBlockState(pos, state.with(WRONG_ITEM_USED, false));
                BlockPos otherPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
                BlockState otherState = world.getBlockState(otherPos);
                if (otherState.isOf(this)) {
                    world.setBlockState(otherPos, otherState.with(WRONG_ITEM_USED, false));
                }
                return ActionResult.SUCCESS;
            } else {
                // Wrong item used - change texture and set timer to revert
                long currentTime = world.getTime();
                if (currentTime - lastFailSoundTime >= SOUND_COOLDOWN_TICKS) {
                    // Set wrong_item_used to true for both halves
                    world.setBlockState(pos, state.with(WRONG_ITEM_USED, true));
                    BlockPos otherPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
                    BlockState otherState = world.getBlockState(otherPos);
                    if (otherState.isOf(this)) {
                        world.setBlockState(otherPos, otherState.with(WRONG_ITEM_USED, true));
                    }

                    // Schedule reset after 40 ticks (2 seconds)
                    if (world instanceof ServerWorld) {
                        world.scheduleBlockTick(pos, this, RESET_DELAY_TICKS); // 40 ticks = 2 seconds
                        world.scheduleBlockTick(otherPos, this, RESET_DELAY_TICKS);
                    }

                    // Play sound
                    world.playSound(null, pos, ModSounds.MONOGLYPH_HURT, SoundCategory.BLOCKS, 1f, 1f);
                    lastFailSoundTime = currentTime;
                }
                return ActionResult.FAIL;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(WRONG_ITEM_USED)) {
            // Reset texture after timer expires
            world.setBlockState(pos, state.with(WRONG_ITEM_USED, false));
            // Reset other half if it exists
            BlockPos otherPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
            BlockState otherState = world.getBlockState(otherPos);
            if (otherState.isOf(this) && otherState.get(WRONG_ITEM_USED)) {
                world.setBlockState(otherPos, otherState.with(WRONG_ITEM_USED, false));
            }
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        tick(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, WRONG_ITEM_USED);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);

        VoxelShape shape = OUTLINE_SHAPE;

        switch (facing) {
            case EAST:
                shape = rotateShape(shape, 90);
                break;
            case SOUTH:
                shape = rotateShape(shape, 180);
                break;
            case WEST:
                shape = rotateShape(shape, 270);
                break;
            default:
                break;
        }

        return shape;
    }

    private VoxelShape rotateShape(VoxelShape shape, int degrees) {
        double minX = shape.getMin(Direction.Axis.X);
        double minY = shape.getMin(Direction.Axis.Y);
        double minZ = shape.getMin(Direction.Axis.Z);
        double maxX = shape.getMax(Direction.Axis.X);
        double maxY = shape.getMax(Direction.Axis.Y);
        double maxZ = shape.getMax(Direction.Axis.Z);

        switch (degrees) {
            case 90:
                return VoxelShapes.cuboid(minZ, minY, 1.0 - maxX, maxZ, maxY, 1.0 - minX);
            case 180:
                return VoxelShapes.cuboid(1.0 - maxX, minY, 1.0 - maxZ, 1.0 - minX, maxY, 1.0 - minZ);
            case 270:
                return VoxelShapes.cuboid(1.0 - maxZ, minY, minX, 1.0 - minZ, maxY, maxX);
            default:
                return shape;
        }
    }
}