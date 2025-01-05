package net.furgus.furgusmenagerie.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.furgus.furgusmenagerie.FurgusMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType SCULK_HOVER_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType SCULK_TRACE_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier (FurgusMod.MOD_ID, "sculk_hover_particle"),
                SCULK_HOVER_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier (FurgusMod.MOD_ID, "sculk_trace_particle"),
                SCULK_TRACE_PARTICLE);
    }
}
