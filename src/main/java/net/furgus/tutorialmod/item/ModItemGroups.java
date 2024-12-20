package net.furgus.tutorialmod.item;

import com.sun.jna.platform.win32.WinReg;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.ModBlocks;
import net.furgus.tutorialmod.block.custom.HatsuneMikuBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup HATSUNE_MIKU_STUFF = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "hatsune_miku_stuff"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.HATSUNE_MIKU_BLOCK))
                    .displayName(Text.translatable("itemgroup.tutorialmod.hatsune_miku_stuff"))
            .entries((displayContext, entries) -> {
                entries.add(ModBlocks.HATSUNE_MIKU_BLOCK);
                entries.add(ModItems.LEEK);
                entries.add(ModItems.MONOGLYPH_SPAWN_EGG);

            }).build());

    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for " +TutorialMod.MOD_ID);
    }

}
