package net.furgus.furgusmenagerie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.block.custom.LeekCropBlock;
import net.furgus.furgusmenagerie.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output){
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator){

        blockStateModelGenerator.registerCrop(ModBlocks.LEEK_CROP, LeekCropBlock.AGE, 0,1,2,3,4,5,6);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator){

        itemModelGenerator.register(ModItems.HATSUNE_MIKU_MUSIC_DISC_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.HATSUNE_MIKU_MUSIC_DISC_2, Models.GENERATED);

        itemModelGenerator.register(ModItems.MONOGLYPH_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(ModItems.HEIROGLITH_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }
}
