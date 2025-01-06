package net.furgus.furgusmenagerie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.block.entity.ModBlockEntities;
import net.furgus.furgusmenagerie.entity.ModEntities;
import net.furgus.furgusmenagerie.block.entity.client.LeekTopUpperBlockRenderer;
import net.furgus.furgusmenagerie.entity.client.HeiroglithRenderer;
import net.furgus.furgusmenagerie.entity.client.MonoglyphRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;


public class FurgusModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HATSUNE_MIKU_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEIROGLITH_HEAD, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEIROGLITH_HEAD, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_BOTTOM_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_TOP_UPPER_BLOCK, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.MONOGLYPH, (context) -> new MonoglyphRenderer(context));
        EntityRendererRegistry.register(ModEntities.HEIROGLITH, HeiroglithRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.LEEK_TOP_UPPER_BLOCK_ENTITY, LeekTopUpperBlockRenderer::new);
    }
}
