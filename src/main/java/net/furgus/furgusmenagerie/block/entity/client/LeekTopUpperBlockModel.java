package net.furgus.furgusmenagerie.block.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.block.entity.LeekTopUpperBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LeekTopUpperBlockModel extends GeoModel<LeekTopUpperBlockEntity> {

    @Override
    public Identifier getModelResource(LeekTopUpperBlockEntity animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "geo/leek_top_upper_block.geo.json");
    }

    @Override
    public Identifier getTextureResource(LeekTopUpperBlockEntity animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "textures/block/leek_leaves.png");
    }

    @Override
    public Identifier getAnimationResource(LeekTopUpperBlockEntity animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "animations/leek_top_upper_block.animation.json");
    }
}
