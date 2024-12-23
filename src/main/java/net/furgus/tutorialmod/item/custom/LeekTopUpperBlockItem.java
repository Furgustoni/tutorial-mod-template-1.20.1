package net.furgus.tutorialmod.item.custom;

import net.furgus.tutorialmod.block.entity.LeekTopUpperBlockEntity;
import net.furgus.tutorialmod.item.client.LeekTopUpperBlockItemRenderer;
import net.furgus.tutorialmod.item.client.RendererProvider;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.BlockItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;

import java.util.function.Consumer;

public class LeekTopUpperBlockItem extends BlockItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public LeekTopUpperBlockItem(Block block, Settings settings) {
        super(block, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final LeekTopUpperBlockItemRenderer renderer = new LeekTopUpperBlockItemRenderer();

            public BuiltinModelItemRenderer getGeoItemRenderer() {
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<LeekTopUpperBlockItem> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle",Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    @Override
    public double getTick(Object itemStack){
        return RenderUtil.getCurrentTick();
    }
}
