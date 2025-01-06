package net.furgus.furgusmenagerie.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.entity.ModEntities;
import net.furgus.furgusmenagerie.item.custom.LeekTopUpperBlockItem;
import net.furgus.furgusmenagerie.sound.ModSounds;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {

    public static void addItemsToItemGroup() {
        // Example: Adding an item to the "Building Blocks" tab
        addToItemGroup(ItemGroups.COLORED_BLOCKS, ModItems.LEEK_TOP_UPPER_BLOCK_ITEM);
    }

    private static void addToItemGroup(RegistryKey<ItemGroup> groupKey, Item item) {
        // Register the item to the ItemGroup using ItemGroupEvents
        ItemGroupEvents.modifyEntriesEvent(groupKey).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(item);
        });
    }

    public static final Item LEEK_TOP_UPPER_BLOCK_ITEM = registerItem("leek_top_upper_block",
            new LeekTopUpperBlockItem(ModBlocks.LEEK_TOP_UPPER_BLOCK, new Item.Settings()));

    public static final Item LEEK = registerItem("leek",
            new AliasedBlockItem(ModBlocks.LEEK_CROP,
                    new Item.Settings().food(ModFoodComponents.LEEK))); // Food and Seed item

    public static final Item MONOGLYPH_SPAWN_EGG = registerItem("monoglyph_spawn_egg",
            new SpawnEggItem(ModEntities.MONOGLYPH, 0x515556, 0xEA2B5D, new Item.Settings())
    );

    public static final Item HEIROGLITH_SPAWN_EGG = registerItem("heiroglith_spawn_egg",
            new SpawnEggItem(ModEntities.HEIROGLITH, 0x515556, 0xEA2B5D, new Item.Settings())
    );

    public static final Item HATSUNE_MIKU_MUSIC_DISC_1 = registerItem("hatsune_miku_music_disc_1",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.HATSUNE_MIKU_DISC_1_KEY).maxCount(1)));

    public static final Item HATSUNE_MIKU_MUSIC_DISC_2 = registerItem("hatsune_miku_music_disc_2",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.HATSUNE_MIKU_DISC_2_KEY).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FurgusMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FurgusMod.LOGGER.info("Registering Mod Items for " + FurgusMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(MONOGLYPH_SPAWN_EGG);});
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(HEIROGLITH_SPAWN_EGG);});
    }
}
