package net.furgus.furgusmenagerie.entity;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.HeiroglithEntity;
import net.furgus.furgusmenagerie.entity.custom.MonoglyphEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MonoglyphEntity> MONOGLYPH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FurgusMod.MOD_ID, "monoglyph"),
            EntityType.Builder.create(MonoglyphEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(1f, 1.2f)
                    .build("monoglyph"));

    public static final EntityType<HeiroglithEntity> HEIROGLITH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FurgusMod.MOD_ID, "heiroglith"),
            EntityType.Builder.create(HeiroglithEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(1f, 3.5f)
                    .build("heiroglith"));

    public static void registerModEntities() {
        FurgusMod.LOGGER.info("Registering Mod Entities for " + FurgusMod.MOD_ID);
    }
}