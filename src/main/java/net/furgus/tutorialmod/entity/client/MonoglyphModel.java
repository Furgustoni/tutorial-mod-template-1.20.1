package net.furgus.tutorialmod.entity.client;

import net.furgus.tutorialmod.TutorialMod;
import net.furgus.tutorialmod.entity.custom.MonoglyphEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MonoglyphModel extends GeoModel<MonoglyphEntity> {

    @Override
    public Identifier getModelResource(MonoglyphEntity animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "geo/monoglyph.geo.json");
    }

    @Override
    public Identifier getTextureResource(MonoglyphEntity animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/entity/monoglyph/monoglyph.png");
    }

    @Override
    public Identifier getAnimationResource(MonoglyphEntity animatable) {
        return Identifier.of(TutorialMod.MOD_ID, "animations/monoglyph.animation.json");
    }

    @Override
    public void setCustomAnimations(MonoglyphEntity animatable, long instanceId, AnimationState<MonoglyphEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
