package net.furgus.furgusmenagerie.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.Vec3d;

public class SculkTraceParticle extends SpriteBillboardParticle {
    protected SculkTraceParticle (ClientWorld level, double xCoord, double yCoord, double zCoord,
                                  SpriteProvider spriteSet, double xd, double yd, double zd) {
        super (level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.velocityMultiplier = 0.98F;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.scale *= 0.3F;
        this.maxAge = 1;
        this.setSprite(spriteSet.getSprite(0, 1));

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        Vec3d vec3d = camera.getPos();
        float f = (float)(this.x - vec3d.x);
        float g = (float)(this.y - vec3d.y);
        float h = (float)(this.z - vec3d.z);

        Vec3d[] vec3ds = new Vec3d[]{
                new Vec3d(-1.0, 0.0, -1.0),
                new Vec3d(-1.0, 0.0, 1.0),
                new Vec3d(1.0, 0.0, 1.0),
                new Vec3d(1.0, 0.0, -1.0)
        };

        float j = this.scale / 2.0F;

        for(int k = 0; k < 4; ++k) {
            Vec3d vec3d2 = vec3ds[k];
            vec3d2 = vec3d2.multiply(j);
            vec3d2 = vec3d2.add(f, g, h);

            vertexConsumer.vertex(
                    vec3d2.x, vec3d2.y, vec3d2.z
            );
        }
    }

    @Override
    public void tick () {
        super.tick ();
        fadeOut ();
    }

    private void fadeOut () {
        this.alpha = (-(1 / (float) maxAge) * age + 1);
    }

    @Override
    public ParticleTextureSheet getType () {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new SculkTraceParticle (level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
