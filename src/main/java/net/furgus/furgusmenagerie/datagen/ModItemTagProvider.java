package net.furgus.furgusmenagerie.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.furgus.furgusmenagerie.item.ModItems;
import net.furgus.furgusmenagerie.util.ModTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup){
        getOrCreateTagBuilder(ModTags.Items.HATSUNE_MUSIC_DISC)
                .add(ModItems.HATSUNE_MIKU_MUSIC_DISC_1)
                .add(ModItems.HATSUNE_MIKU_MUSIC_DISC_2);

        getOrCreateTagBuilder (ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.HATSUNE_MIKU_MUSIC_DISC_1)
                .add(ModItems.HATSUNE_MIKU_MUSIC_DISC_2);
    }
}
