package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.MonoglyphEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MonoglyphRenderer extends GeoEntityRenderer<MonoglyphEntity> {
    public MonoglyphRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new MonoglyphModel());
    }

    @Override
    public Identifier getTexture(MonoglyphEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "textures/entity/monoglyph/monoglyph.png");
    }

    @Override
    public void render(MonoglyphEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        }
}