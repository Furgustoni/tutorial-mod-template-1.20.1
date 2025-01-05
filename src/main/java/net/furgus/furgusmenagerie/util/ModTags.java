package net.furgus.furgusmenagerie.util;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(FurgusMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HATSUNE_MUSIC_DISC = createTag("hatsune_music_disc");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(FurgusMod.MOD_ID, name));
        }
    }
}
