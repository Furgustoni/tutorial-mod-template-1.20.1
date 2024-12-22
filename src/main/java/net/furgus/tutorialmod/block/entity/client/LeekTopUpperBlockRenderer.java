package net.furgus.tutorialmod.block.entity.client;

import net.furgus.tutorialmod.block.entity.LeekTopUpperBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LeekTopUpperBlockRenderer extends GeoBlockRenderer<LeekTopUpperBlockEntity> {

    // Constructor with context, which passes to the superclass GeoBlockRenderer
    public LeekTopUpperBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new LeekTopUpperBlockModel());
    }

    @Override
    public void render(LeekTopUpperBlockEntity blockEntity, float partialTicks, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        super.render(blockEntity, partialTicks, matrices, vertexConsumers, light, overlay);
    }
}
