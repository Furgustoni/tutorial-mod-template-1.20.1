package net.furgus.tutorialmod.block.custom;

import net.furgus.tutorialmod.block.ModBlocks;
import net.furgus.tutorialmod.sound.ModSounds;
import net.furgus.tutorialmod.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LeekBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 13.0, 11.0);
    private long lastSoundTime = 0; // Track when the last sound was played

    public LeekBlock(Settings settings){
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        long currentTime = world.getTime(); // Get the current world time in ticks
        long cooldownTicks = 20; // 20 ticks = 1 second cooldown

        if (currentTime - lastSoundTime >= cooldownTicks) {
            world.playSound(player, pos, ModSounds.HATSUNE_MIKU_PLACE, SoundCategory.BLOCKS, 0.09f, 1f);
            lastSoundTime = currentTime; // Update the time the sound was last played
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (isValidItem(itemEntity.getStack())) {
                    itemEntity.setStack(new ItemStack(ModBlocks.LEEK_BLOCK, itemEntity.getStack().getCount()));
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
}
