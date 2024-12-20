package net.furgus.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.ModBlocks;
import net.furgus.tutorialmod.entity.ModEntities;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));

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

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(MONOGLYPH_SPAWN_EGG);
        });
    }
}
