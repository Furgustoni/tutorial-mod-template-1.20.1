package net.furgus.furgusmenagerie.item.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.item.custom.LeekTopUpperBlockItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LeekTopUpperBlockItemModel extends GeoModel<LeekTopUpperBlockItem>{
    @Override
    public Identifier getModelResource(LeekTopUpperBlockItem animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "geo/leek_top_upper_block.geo.json");
    }

    @Override
    public Identifier getTextureResource(LeekTopUpperBlockItem animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "textures/block/leek_leaves.png");
    }

    @Override
    public Identifier getAnimationResource(LeekTopUpperBlockItem animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "animations/leek_top_upper_block.animation.json");
    }
}
