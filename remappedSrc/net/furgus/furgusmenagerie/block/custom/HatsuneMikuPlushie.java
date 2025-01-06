package net.furgus.furgusmenagerie.block.custom;

import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.sound.ModSounds;
import net.furgus.furgusmenagerie.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HatsuneMikuPlushie extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 12.0, 15.0, 11.0);
    private long lastSoundTime = 0; // Track when the last sound was played

    public HatsuneMikuPlushie(AbstractBlock.Settings settings){
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        long currentTime = world.getTime(); // Get the current world time in ticks
        long cooldownTicks = 30; // 20 ticks = 1 second cooldown

        if (currentTime - lastSoundTime >= cooldownTicks) {
            world.playSound(player, pos, ModSounds.HATSUNE_MIKU_PLACE, SoundCategory.BLOCKS, 0.055f, 1f);
            lastSoundTime = currentTime; // Update the time the sound was last played
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (isValidItem(itemEntity.getStack())) {
                itemEntity.setStack(new ItemStack(ModBlocks.HATSUNE_MIKU_PLUSHIE, itemEntity.getStack().getCount()));
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    private boolean isValidItem(ItemStack stack){
        return stack.isIn(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public static VoxelShape makeShape() {
        return VoxelShapes.union(VoxelShapes.cuboid(0.13124999403953552, 0.00625002384185791, 0.14687499403953552, 0.8812499940395355, 0.8812500238418579, 0.7718749940395355)
        );
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return makeShape(); // Returns the custom shape defined earlier
    }
}
