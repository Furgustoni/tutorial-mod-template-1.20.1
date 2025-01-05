package net.furgus.furgusmenagerie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.furgus.furgusmenagerie.block.ModBlocks;
import net.furgus.furgusmenagerie.block.entity.ModBlockEntities;
import net.furgus.furgusmenagerie.entity.ModEntities;
import net.furgus.furgusmenagerie.block.entity.client.LeekTopUpperBlockRenderer;
import net.furgus.furgusmenagerie.entity.client.HeiroglithRenderer;
import net.furgus.furgusmenagerie.entity.client.MonoglyphRenderer;
import net.furgus.furgusmenagerie.entity.client.SculkHoverboardRenderer;
import net.furgus.furgusmenagerie.event.KeyInputHandler;
import net.furgus.furgusmenagerie.particle.ModParticles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;


public class FurgusModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HATSUNE_MIKU_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEIROGLITH_HEAD, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HEIROGLITH_HEAD, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_BOTTOM_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_TOP_UPPER_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEEK_SAPLING, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticles.SCULK_HOVER_PARTICLE, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SCULK_TRACE_PARTICLE, EndRodParticle.Factory::new);

        KeyInputHandler.register ();

        EntityRendererRegistry.register(ModEntities.MONOGLYPH, MonoglyphRenderer::new);
        EntityRendererRegistry.register(ModEntities.HEIROGLITH, HeiroglithRenderer::new);
        EntityRendererRegistry.register(ModEntities.SCULK_HOVERBOARD, SculkHoverboardRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.LEEK_TOP_UPPER_BLOCK_ENTITY, LeekTopUpperBlockRenderer::new);
    }
}
