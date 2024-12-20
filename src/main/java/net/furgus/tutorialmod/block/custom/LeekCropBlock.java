package net.furgus.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.furgus.tutorialmod.item.ModItems;

public class LeekCropBlock extends CropBlock {
    public static final int MAX_AGE = 6; // Custom age for your crop
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE); // Custom age property

    public LeekCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemConvertible getSeedsItem() {
        return ModItems.LEEK; // The item that works as seeds
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE; // Use the custom AGE property
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE; // Return the custom max age
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE); // Register the custom AGE property
    }
}
