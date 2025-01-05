package net.furgus.furgusmenagerie.item.client;

import net.furgus.furgusmenagerie.item.custom.SculkHoverboardItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SculkHoverboardItemRenderer extends GeoItemRenderer<SculkHoverboardItem> {
    public SculkHoverboardItemRenderer () {
        super(new SculkHoverboardItemModel());
    }
}
