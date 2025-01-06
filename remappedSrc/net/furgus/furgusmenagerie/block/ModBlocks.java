package net.furgus.furgusmenagerie.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.block.custom.*;
import net.furgus.furgusmenagerie.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.register;

public class ModBlocks {

    public static final Block HEIROGLITH_HEAD = registerBlock("heiroglith_head",
            new HeiroglithHead (AbstractBlock.Settings.create()
                    .strength(1.0F)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));

    public static final Block LEEK_BOTTOM_BLOCK = registerBlock("leek_bottom_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(1.5f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_MIDDLE_BOTTOM_BLOCK = registerBlock("leek_middle_bottom_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(1.5f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_MIDDLE_TOP_BLOCK = registerBlock("leek_middle_top_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(1.5f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_TOP_BLOCK = registerBlock("leek_top_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(1.5f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_TIP_TOP_BLOCK = registerBlock("leek_tip_top_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(1.5f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_TOP_UPPER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(FurgusMod.MOD_ID, "leek_top_upper_block"),
            new LeekTopUpperBlock(AbstractBlock.Settings.create()
                    .strength(1.5f)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SHROOMLIGHT)));


    public static final Block HATSUNE_MIKU_PLUSHIE = registerBlock("hatsune_miku_plushie",
            new HatsuneMikuPlushie(AbstractBlock.Settings.create()
                    .strength(0.02f).breakInstantly().sounds(ModSounds.HATSUNE_MIKU_SOUND).noCollision().nonOpaque()));


    public static final Block LEEK_CROP = registerBlockWithoutBlockItem("leek_crop",
            new LeekCropBlock(AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN).noCollision().nonOpaque()));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(FurgusMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(FurgusMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(FurgusMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        FurgusMod.LOGGER.info("Registering Mod Blocks for " + FurgusMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.LEEK_BOTTOM_BLOCK);
            entries.add(ModBlocks.HATSUNE_MIKU_PLUSHIE);
        });
    }
}
