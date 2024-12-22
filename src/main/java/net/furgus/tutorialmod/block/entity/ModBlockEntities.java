package net.furgus.tutorialmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<LeekTopUpperBlockEntity> LEEK_TOP_UPPER_BLOCK_ENTITY;

    public static void registerAllBlockEntities() {
        LEEK_TOP_UPPER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(TutorialMod.MOD_ID, "leek_top_upper_block_entity"),
                FabricBlockEntityTypeBuilder.create(LeekTopUpperBlockEntity::new,
                        ModBlocks.LEEK_TOP_UPPER_BLOCK).build());
    }
}
