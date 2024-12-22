package net.furgus.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.ModBlocks;
import net.furgus.tutorialmod.entity.ModEntities;
import net.furgus.tutorialmod.item.custom.LeekTopUpperBlockItem;
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

    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));

    public static final Item LEEK_TOP_UPPER_BLOCK_ITEM = registerItem("leek_top_upper_block",
            new LeekTopUpperBlockItem(ModBlocks.LEEK_TOP_UPPER_BLOCK, new Item.Settings()));

    public static final Item LEEK = registerItem("leek",
            new AliasedBlockItem(ModBlocks.LEEK_CROP,
                    new Item.Settings().food(ModFoodComponents.LEEK))); // Food and Seed item

    public static final Item MONOGLYPH_SPAWN_EGG = registerItem("monoglyph_spawn_egg",
            new SpawnEggItem(ModEntities.MONOGLYPH, 0x515556, 0xEA2B5D, new Item.Settings())
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(MONOGLYPH_SPAWN_EGG);
        });
    }
}
