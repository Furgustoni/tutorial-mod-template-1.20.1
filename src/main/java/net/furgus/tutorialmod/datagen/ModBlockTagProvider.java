package net.furgus.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.furgus.tutorialmod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup){
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.PINK_GARNET_BLOCK)
                .add(ModBlocks.LEEK_CROP);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.LEEK_CROP)
                .add(ModBlocks.LEEK_BOTTOM_BLOCK)
                .add(ModBlocks.LEEK_MIDDLE_BOTTOM_BLOCK)
                .add(ModBlocks.LEEK_MIDDLE_TOP_BLOCK)
                .add(ModBlocks.LEEK_TOP_BLOCK)
                .add(ModBlocks.LEEK_TIP_TOP_BLOCK)
                .add(ModBlocks.LEEK_TOP_UPPER_BLOCK);

        getOrCreateTagBuilder(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)
                .add(ModBlocks.LEEK_CROP);


        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);
    }
}
