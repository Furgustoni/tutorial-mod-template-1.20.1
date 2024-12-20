package net.furgus.tutorialmod.entity.client;

import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.entity.custom.MonoglyphEntity;
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
        return Identifier.of(TutorialMod.MOD_ID, "textures/entity/monoglyph/monoglyph.png");
    }

    @Override
    public void render(MonoglyphEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.7f,0.7f, 0.7f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}