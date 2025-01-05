package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.HeiroglithEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HeiroglithModel extends GeoModel<HeiroglithEntity> {
    @Override
    public Identifier getModelResource(HeiroglithEntity object) {
        return new Identifier("furgusmenagerie", "geo/heiroglith.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeiroglithEntity object) {
        return new Identifier("furgusmenagerie", "textures/entity/heiroglith.png");
    }

    @Override
    public Identifier getAnimationResource(HeiroglithEntity animatable) {
        return new Identifier("furgusmenagerie", "animations/heiroglith.animation.json");
    }

    @Override
    public void setCustomAnimations(HeiroglithEntity animatable, long instanceId, AnimationState<HeiroglithEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}