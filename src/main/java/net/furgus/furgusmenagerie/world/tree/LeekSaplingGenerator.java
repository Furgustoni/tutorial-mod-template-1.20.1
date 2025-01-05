package net.furgus.furgusmenagerie.world.tree;

import net.furgus.furgusmenagerie.world.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class LeekSaplingGenerator extends SaplingGenerator {
    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature (Random random, boolean bees) {
        return ModConfiguredFeatures.LEEK_KEY;
    }
}
