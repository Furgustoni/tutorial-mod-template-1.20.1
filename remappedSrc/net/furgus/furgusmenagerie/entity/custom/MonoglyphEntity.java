package net.furgus.furgusmenagerie.entity.custom;

import net.furgus.furgusmenagerie.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MonoglyphEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public MonoglyphEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 3;
    }

    @Override
    protected void initGoals(){
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2,new MeleeAttackGoal(this,1.20,false));
        this.goalSelector.add(0, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 1));

        this.targetSelector.add(0,new ActiveTargetGoal<>(this,PlayerEntity.class,true));
        this.targetSelector.add(2,new ActiveTargetGoal<>(this, MerchantEntity.class,true));
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.method_48926().isClient()) {
            this.setupAnimationStates();
        }
    }

    @Nullable
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",3,this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackController",0,this::attackPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "deathController",0,this::deathPredicate));
    }

    private PlayState predicate(software.bernie.geckolib.animation.AnimationState<MonoglyphEntity> monoglyphEntityAnimationState) {
        // Check if the entity is moving
        if (monoglyphEntityAnimationState.isMoving()) {
            // If moving, set the walk animation
            monoglyphEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.monoglyph.walk", Animation.LoopType.LOOP));
        } else {
            // If not moving, set the idle or deploy animation based on age
            if (this.age == 0) {
                monoglyphEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.monoglyph.deploy", Animation.LoopType.PLAY_ONCE));
            } else {
                monoglyphEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.monoglyph.idle", Animation.LoopType.LOOP));
            }
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(software.bernie.geckolib.animation.AnimationState<MonoglyphEntity> monoglyphEntityAnimationState) {
        // Ensure the entity has a target
        if (this.getTarget() instanceof PlayerEntity player) {
            // Check if the entity can see the target and is in range
            if (this.canSee(player) && this.squaredDistanceTo(player) < 9.0F) {
                monoglyphEntityAnimationState.getController()
                        .setAnimation(RawAnimation.begin().then("animation.monoglyph.attack", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;
            }
        }
        // Stop the attack animation if conditions are not met
        return PlayState.STOP;
    }

    private PlayState deathPredicate(software.bernie.geckolib.animation.AnimationState<MonoglyphEntity> monoglyphEntityAnimationState) {
        // Check if the entity has health and is in the process of dying
        if (this.isDead() || this.getHealth() <= 0) {
            // Set the death animation to play once
            monoglyphEntityAnimationState.getController()
                    .setAnimation(RawAnimation.begin().then("animation.monoglyph.death", Animation.LoopType.PLAY_ONCE));

            // Kill the entity (remove it from the world)
            this.kill();

            // Return CONTINUE to let the animation play
            return PlayState.CONTINUE;
        }

        // If conditions are not met, stop the animation
        return PlayState.STOP;
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
