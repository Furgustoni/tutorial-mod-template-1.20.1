package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.SculkHoverboardEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SculkHoverboardModel extends GeoModel<SculkHoverboardEntity> {

    @Override
    public Identifier getModelResource(SculkHoverboardEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "geo/sculk_hoverboard.geo.json");
    }

    @Override
    public Identifier getTextureResource(SculkHoverboardEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "textures/entity/sculk_hoverboard/sculk_hoverboard.png");
    }

    @Override
    public Identifier getAnimationResource(SculkHoverboardEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "animations/sculk_hoverboard.animation.json");
    }
}
