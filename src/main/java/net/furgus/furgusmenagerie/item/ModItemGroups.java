package net.furgus.furgusmenagerie.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HATSUNE_MIKU_STUFF = Registry.register(Registries.ITEM_GROUP,
             new Identifier(FurgusMod.MOD_ID, "hatsune_miku_stuff"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.HATSUNE_MIKU_PLUSHIE))
                    .displayName(Text.translatable("itemgroup.furgusmenagerie.hatsune_miku_stuff"))
            .entries((displayContext, entries) -> {
                entries.add(ModBlocks.HATSUNE_MIKU_PLUSHIE);
                entries.add(ModItems.MONOGLYPH_SPAWN_EGG);
                entries.add(ModItems.HEIROGLITH_SPAWN_EGG);
                entries.add(ModItems.SCULK_HOVERBOARD_ITEM);
                entries.add(ModItems.HATSUNE_MIKU_MUSIC_DISC_1);
                entries.add(ModItems.HATSUNE_MIKU_MUSIC_DISC_2);

            }).build());

    public static final ItemGroup HATSUNE_BUILDING_BLOCKS = Registry.register(Registries.ITEM_GROUP,
             new Identifier(FurgusMod.MOD_ID, "hatsune_miku_building_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.LEEK_BOTTOM_BLOCK))
                    .displayName(Text.translatable("itemgroup.furgusmenagerie.hatsune_miku_building_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.LEEK);
                        entries.add(ModBlocks.LEEK_BOTTOM_BLOCK);
                        entries.add(ModBlocks.LEEK_MIDDLE_BOTTOM_BLOCK);
                        entries.add(ModBlocks.LEEK_MIDDLE_TOP_BLOCK);
                        entries.add(ModBlocks.LEEK_TOP_BLOCK);
                        entries.add(ModBlocks.LEEK_TIP_TOP_BLOCK);
                        entries.add(ModBlocks.LEEK_TOP_UPPER_BLOCK);
                        entries.add(ModBlocks.HEIROGLITH_HEAD);


                    }).build());

    public static void registerItemGroups() {
        FurgusMod.LOGGER.info("Registering Item Groups for " + FurgusMod.MOD_ID);
    }

}
