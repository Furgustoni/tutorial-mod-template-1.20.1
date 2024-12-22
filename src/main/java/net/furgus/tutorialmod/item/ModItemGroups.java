package net.furgus.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HATSUNE_MIKU_STUFF = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "hatsune_miku_stuff"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.HATSUNE_MIKU_PLUSHIE))
                    .displayName(Text.translatable("itemgroup.tutorialmod.hatsune_miku_stuff"))
            .entries((displayContext, entries) -> {
                entries.add(ModBlocks.HATSUNE_MIKU_PLUSHIE);
                entries.add(ModItems.MONOGLYPH_SPAWN_EGG);

            }).build());

    public static final ItemGroup HATSUNE_BUILDING_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "hatsune_miku_building_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.LEEK_BOTTOM_BLOCK))
                    .displayName(Text.translatable("itemgroup.tutorialmod.hatsune_miku_building_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.LEEK);
                        entries.add(ModBlocks.LEEK_BOTTOM_BLOCK);
                        entries.add(ModBlocks.LEEK_MIDDLE_BOTTOM_BLOCK);
                        entries.add(ModBlocks.LEEK_MIDDLE_TOP_BLOCK);
                        entries.add(ModBlocks.LEEK_TOP_BLOCK);
                        entries.add(ModBlocks.LEEK_TIP_TOP_BLOCK);
                        entries.add(ModBlocks.LEEK_TOP_UPPER_BLOCK);

                    }).build());

    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for " +TutorialMod.MOD_ID);
    }

}
