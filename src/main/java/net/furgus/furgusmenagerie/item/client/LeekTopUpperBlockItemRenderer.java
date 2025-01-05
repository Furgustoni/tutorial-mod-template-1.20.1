package net.furgus.furgusmenagerie.item.client;

import net.furgus.furgusmenagerie.item.custom.LeekTopUpperBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LeekTopUpperBlockItemRenderer extends GeoItemRenderer<LeekTopUpperBlockItem> {
    public LeekTopUpperBlockItemRenderer() {
        super(new LeekTopUpperBlockItemModel());
    }
}
