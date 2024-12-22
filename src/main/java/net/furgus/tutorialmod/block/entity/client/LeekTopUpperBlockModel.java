package net.furgus.tutorialmod.block.entity.client;

import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.block.entity.LeekTopUpperBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LeekTopUpperBlockModel extends GeoModel<LeekTopUpperBlockEntity> {

    @Override
    public Identifier getModelResource(LeekTopUpperBlockEntity animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "geo/leek_top_upper_block.geo.json");
    }

    @Override
    public Identifier getTextureResource(LeekTopUpperBlockEntity animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/block/leek_leaves.png");
    }

    @Override
    public Identifier getAnimationResource(LeekTopUpperBlockEntity animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "animations/leek_top_upper_block.animation.json");
    }
}
