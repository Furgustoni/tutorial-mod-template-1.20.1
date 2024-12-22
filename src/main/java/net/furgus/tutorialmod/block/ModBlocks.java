package net.furgus.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.custom.HatsuneMikuPlushie;
import net.furgus.tutorialmod.block.custom.LeekCropBlock;
import net.furgus.tutorialmod.block.custom.LeekTopUpperBlock;
import net.furgus.tutorialmod.sound.ModSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            new Block(AbstractBlock.Settings.create().strength(3f)
                    .requiresTool()));

    public static final Block LEEK_BOTTOM_BLOCK = registerBlock("leek_bottom_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(0.02f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_MIDDLE_BOTTOM_BLOCK = registerBlock("leek_middle_bottom_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(0.02f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_MIDDLE_TOP_BLOCK = registerBlock("leek_middle_top_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(0.02f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_TOP_BLOCK = registerBlock("leek_top_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(0.02f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_TIP_TOP_BLOCK = registerBlock("leek_tip_top_block",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(0.02f).sounds(BlockSoundGroup.SHROOMLIGHT)));
    public static final Block LEEK_TOP_UPPER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, "leek_top_upper_block"),
            new LeekTopUpperBlock(AbstractBlock.Settings.create()
                    .strength(0.02f)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SHROOMLIGHT)));


    public static final Block HATSUNE_MIKU_PLUSHIE = registerBlock("hatsune_miku_plushie",
            new HatsuneMikuPlushie(AbstractBlock.Settings.create()
                    .strength(0.02f).breakInstantly().sounds(ModSounds.HATSUNE_MIKU_SOUND).noCollision().nonOpaque().noBlockBreakParticles()));


    public static final Block LEEK_CROP = registerBlockWithoutBlockItem("leek_crop",
            new LeekCropBlock(AbstractBlock.Settings.create()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN).noCollision().nonOpaque().noBlockBreakParticles()));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.PINK_GARNET_BLOCK);
            entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
            entries.add(ModBlocks.LEEK_BOTTOM_BLOCK);
            entries.add(ModBlocks.HATSUNE_MIKU_PLUSHIE);
        });
    }
}
