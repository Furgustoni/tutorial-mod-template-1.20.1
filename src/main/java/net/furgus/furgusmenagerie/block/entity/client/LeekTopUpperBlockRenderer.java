package net.furgus.furgusmenagerie.block.entity.client;

import net.furgus.furgusmenagerie.block.entity.LeekTopUpperBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LeekTopUpperBlockRenderer extends GeoBlockRenderer<LeekTopUpperBlockEntity> {
    public LeekTopUpperBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new LeekTopUpperBlockModel());
    }
}