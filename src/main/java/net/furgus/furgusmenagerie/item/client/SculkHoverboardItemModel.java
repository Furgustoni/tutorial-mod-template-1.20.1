package net.furgus.furgusmenagerie.item.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.item.custom.SculkHoverboardItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SculkHoverboardItemModel extends GeoModel<SculkHoverboardItem>{
    @Override
    public Identifier getModelResource(SculkHoverboardItem animatable) {
        return  new Identifier(FurgusMod.MOD_ID, "geo/sculk_hoverboard.geo.json");
    }

    @Override
    public Identifier getTextureResource(SculkHoverboardItem animatable) {
        return  new Identifier(FurgusMod.MOD_ID, "textures/entity/sculk_hoverboard/sculk_hoverboard.png");
    }

    @Override
    public Identifier getAnimationResource(SculkHoverboardItem animatable) {
        return  new Identifier(FurgusMod.MOD_ID, "animations/sculk_hoverboard.animation.json");
    }
}
