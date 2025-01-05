package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.SculkHoverboardEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class SculkHoverboardRenderer extends GeoEntityRenderer<SculkHoverboardEntity> {
    public SculkHoverboardRenderer (EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new SculkHoverboardModel ());
        this.addRenderLayer(new AutoGlowingGeoLayer<> (this));
    }

    @Override
    public Identifier getTexture(SculkHoverboardEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "textures/entity/sculk_hoverboard/sculk_hoverboard.png");
    }

    @Override
    public void render(SculkHoverboardEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        }
}