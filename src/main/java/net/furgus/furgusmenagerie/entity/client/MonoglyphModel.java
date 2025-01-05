package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.MonoglyphEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MonoglyphModel extends GeoModel<MonoglyphEntity> {

    @Override
    public Identifier getModelResource(MonoglyphEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "geo/monoglyph.geo.json");
    }

    @Override
    public Identifier getTextureResource(MonoglyphEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "textures/entity/monoglyph/monoglyph.png");
    }

    @Override
    public Identifier getAnimationResource(MonoglyphEntity animatable) {
        return new Identifier(FurgusMod.MOD_ID, "animations/monoglyph.animation.json");
    }
}
