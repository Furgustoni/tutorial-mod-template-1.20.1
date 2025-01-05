package net.furgus.furgusmenagerie.entity.custom;

import net.furgus.furgusmenagerie.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class MonoglyphEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public MonoglyphEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    // Set the attributes of the Monoglyph entity
    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D) // Slightly higher health
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0f) // Increased damage
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.5f) // Faster attack speed
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35f); // Slightly slower movement
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.3D, true)); // Slightly faster melee attack
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.7f, 1)); // Slower wander speed
        this.goalSelector.add(4, new LookAroundGoal(this));

        // Adding more complex target selectors
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, ChickenEntity.class, true)); // Keeps chicken as prey
    }

    // Removed the createChild method

    // Register animation controllers (GeckoLib integration)
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    // Define the animation state machine (walking, idle, attacking, etc.)
    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.monoglyph.walk", Animation.LoopType.LOOP));
        } else {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.monoglyph.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MONOGLYPH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MONOGLYPH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.MONOGLYPH_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSounds.MONOGLYPH_STEP, 0.07F, 0.4F);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
