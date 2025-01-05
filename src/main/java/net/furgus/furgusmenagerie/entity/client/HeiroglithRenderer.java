package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.HeiroglithEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class HeiroglithRenderer extends GeoEntityRenderer<HeiroglithEntity> {

    public HeiroglithRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new HeiroglithModel());
        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTexture(HeiroglithEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "textures/entity/heiroglith/heiroglith.png");
    }

    @Override
    public void render(HeiroglithEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}