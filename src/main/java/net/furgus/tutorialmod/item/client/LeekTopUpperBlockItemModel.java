package net.furgus.tutorialmod.item.client;

import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.item.custom.LeekTopUpperBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LeekTopUpperBlockItemModel extends GeoModel<LeekTopUpperBlockItem> {

    @Override
    public Identifier getModelResource(LeekTopUpperBlockItem animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "geo/leek_top_upper_block.geo.json");
    }

    @Override
    public Identifier getTextureResource(LeekTopUpperBlockItem animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/block/leek_top_upper_block.png");
    }

    @Override
    public Identifier getAnimationResource(LeekTopUpperBlockItem animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "animations/leek_top_upper_block.animation.json");
    }
}
