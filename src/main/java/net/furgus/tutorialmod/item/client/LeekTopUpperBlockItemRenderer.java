package net.furgus.tutorialmod.item.client;

import net.furgus.tutorialmod.item.custom.LeekTopUpperBlockItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LeekTopUpperBlockItemRenderer extends GeoItemRenderer<LeekTopUpperBlockItem> {
    public LeekTopUpperBlockItemRenderer() {
        super(new LeekTopUpperBlockItemModel());
    }
}
