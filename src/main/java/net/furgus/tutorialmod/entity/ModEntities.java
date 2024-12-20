package net.furgus.tutorialmod.entity;

import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.entity.custom.MonoglyphEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MonoglyphEntity> MONOGLYPH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TutorialMod.MOD_ID, "monoglyph"),
            EntityType.Builder.create(MonoglyphEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1f, 1.2f)
                    .build("monoglyph"));

    public static void registerModEntities() {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }
}