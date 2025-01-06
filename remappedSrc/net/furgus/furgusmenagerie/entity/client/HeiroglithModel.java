package net.furgus.furgusmenagerie.entity.client;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.entity.custom.HeiroglithEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HeiroglithModel extends GeoModel<HeiroglithEntity> {


    @Override
    public Identifier getModelResource(HeiroglithEntity animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "geo/heiroglith.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeiroglithEntity animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "textures/entity/heiroglith/heiroglith.png");
    }

    @Override
    public Identifier getAnimationResource(HeiroglithEntity animatable) {
        return Identifier.of(FurgusMod.MOD_ID, "animations/heiroglith.animation.json");
    }

    @Override
    public void setCustomAnimations(HeiroglithEntity animatable, long instanceId, AnimationState<HeiroglithEntity> animationState) {
        // Handle head rotation
        GeoBone head = getAnimationProcessor().getBone("head");
        if (head != null) {
            // Get entity model data for head rotation
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            // Apply head pitch and yaw adjustments in radians
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE); // Head pitch
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE); // Head yaw
        }
    }
}
