package net.furgus.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.furgus.tutorialmod.block.ModBlocks;
import net.furgus.tutorialmod.block.entity.ModBlockEntities;
import net.furgus.tutorialmod.entity.ModEntities;
import net.furgus.tutorialmod.block.entity.client.LeekTopUpperBlockRenderer;
import net.furgus.tutorialmod.entity.client.MonoglyphRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HATSUNE_MIKU_PLUSHIE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_BOTTOM_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_TOP_UPPER_BLOCK, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.MONOGLYPH, MonoglyphRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.LEEK_TOP_UPPER_BLOCK_ENTITY, LeekTopUpperBlockRenderer::new);
    }
}
