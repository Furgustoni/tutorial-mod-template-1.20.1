package net.furgus.furgusmenagerie.entity.custom;

import net.furgus.furgusmenagerie.event.KeyInputHandler;
import net.furgus.furgusmenagerie.item.ModItems;
import net.furgus.furgusmenagerie.particle.ModParticles;
import net.furgus.furgusmenagerie.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SculkHoverboardEntity extends AnimalEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean maintainYLevel = false;
    private double maintainedYLevel = 0.0;
    private int hitCount = 0;
    private int hurtTimer = 0;
    private int echoShardCount = 0;
    private final int echoShardThreshold = 5 + this.random.nextInt(4);

    private boolean isDrifting = false;// Keep only drag factor
    private Vec3d previousVelocity = Vec3d.ZERO;
    private float momentumDrag = 0.85F;
    private float baseDrag = 0.95F;
    private boolean wasMoving = false;

    public SculkHoverboardEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    @Override
    protected void initGoals(){
        this.goalSelector.add(0, new FlyGoal(this, 1)); // Increased speed
        this.goalSelector.add(3, new TemptGoal(this, 1.1, Ingredient.ofItems(new ItemConvertible[]{Items.ECHO_SHARD}), false));
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 30.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6F)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 60.0);
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        // No fall damage
    }

    private ItemStack createHoverboardItem() {
        ItemStack stack = new ItemStack(ModItems.SCULK_HOVERBOARD_ITEM);
        if (this.hasCustomName()) {
            stack.setCustomName(this.getCustomName());
        }
        return stack;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.getWorld().isClient && source.getAttacker() instanceof PlayerEntity) {
            hitCount++;
            hurtTimer = 10; // Set hurt animation duration
            this.scheduleVelocityUpdate();
            this.emitGameEvent(GameEvent.ENTITY_DAMAGE, source.getAttacker());
            this.playSound(SoundEvents.BLOCK_SCULK_SENSOR_CLICKING_STOP, 1.0F, 1.0F); // Play sound

            if (hitCount >= 2) {
                // Use the new method to create the item with preserved data
                this.dropStack(createHoverboardItem());
                // Kill the entity
                this.discard();
                return true;
            }
        }
        return super.damage(source, amount);
    }

    private int hoverSoundTimer = 0;
    private int hoverSoundDelay = 20 + this.random.nextInt(40);
    @Override
    public void tickMovement() {
        super.tickMovement();
        if (hurtTimer > 0) {
            hurtTimer--;
        }

        // Base hover particles
        if (this.getWorld().isClient) {
            for (int i = 0; i < 3; i++) {
                double offsetX = this.random.nextGaussian() * 0.1;
                double offsetZ = this.random.nextGaussian() * 0.1;
                this.getWorld().addParticle(
                        ModParticles.SCULK_HOVER_PARTICLE,
                        this.getX() + offsetX,
                        this.getY(),
                        this.getZ() + offsetZ,
                        0, -0.01, 0 // Increase fade speed
                );
            }
        }

        // Play hover sound when moving with random delay and pitch
        if (!this.getWorld().isClient && this.isMoving()) {
            if (hoverSoundTimer <= 0) {
                float pitch = 0.8F + this.random.nextFloat() * 0.4F; // Random pitch between 0.8 and 1.2
                this.playSound(ModSounds.SCULK_HOVERBOARD_HOVERING, 1.0F, pitch);
                hoverSoundTimer = hoverSoundDelay;
                hoverSoundDelay = 20 + this.random.nextInt(40); // Reset delay
            } else {
                hoverSoundTimer--;
            }
        }
    }


    private boolean isMoving() {
        Vec3d velocity = this.getVelocity();
        return velocity.x != 0 || velocity.y != 0 || velocity.z != 0;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<> (this, "controller", 0, this::predicate));
    }

    private PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<SculkHoverboardEntity> animationState) {
        if (hurtTimer > 0) {
            animationState.getController().transitionLength(5)
                    .setAnimation(RawAnimation.begin().then("hurt", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        Vec3d velocity = this.getVelocity();
        LivingEntity passenger = this.getControllingPassenger();

        if (passenger != null) {
            // Check vertical movement first
            if (velocity.y > 0.1) {
                animationState.getController().transitionLength(15)
                        .setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            } else if (velocity.y < -0.1) {
                animationState.getController().transitionLength(15)
                        .setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            }
            // Check horizontal movement
            else if (passenger.forwardSpeed > 0.1) {
                animationState.getController().transitionLength(15)
                        .setAnimation(RawAnimation.begin().then("forward", Animation.LoopType.LOOP));
            } else if (passenger.forwardSpeed < -0.1) {
                animationState.getController().transitionLength(15)
                        .setAnimation(RawAnimation.begin().then("backward", Animation.LoopType.LOOP));
            }
            // Check strafing
            else if (passenger.sidewaysSpeed > 0.1) {
                animationState.getController().transitionLength(15)
                        .setAnimation(RawAnimation.begin().then("strafe_right", Animation.LoopType.LOOP));
            } else if (passenger.sidewaysSpeed < -0.1) {
                animationState.getController().transitionLength(15)
                        .setAnimation(RawAnimation.begin().then("strafe_left", Animation.LoopType.LOOP));
            } else {
                animationState.getController().transitionLength(20)
                        .setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            }
        } else {
            animationState.getController().transitionLength(15)
                    .setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        playSound(SoundEvents.BLOCK_SCULK_CATALYST_STEP, 1.0F, 1.0F);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        hitCount = 0;
        ItemStack itemStack = player.getStackInHand(hand);

        // Add name tag handling
        if (itemStack.isOf(Items.NAME_TAG)) {
            ItemStack itemStack2 = itemStack.copy();
            itemStack2.setCount(1);
            if (itemStack.hasCustomName()) {
                this.setCustomName(itemStack.getName());
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                return ActionResult.success(this.getWorld().isClient);
            }
            return ActionResult.PASS;
        }

        if (!player.getWorld().isClient) {
            // Check if the player is holding an echo shard
            if (itemStack.isOf(Items.ECHO_SHARD)) {
                echoShardCount++;
                itemStack.decrement(1); // Consume one echo shard
                this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F); // Play eating sound

                // Spawn particle effects on the server side
                ((ServerWorld) this.getWorld()).spawnParticles(
                        ModParticles.SCULK_TRACE_PARTICLE,
                        this.getX(), this.getY() + 0.5, this.getZ(),
                        20, // Number of particles
                        0.5, 0.5, 0.5, // Offset for X, Y, Z
                        0.1// Speed
                );

                if (echoShardCount >= echoShardThreshold) {
                    // Drop the reward item
                    this.dropItem(ModItems.LEEK);
                    echoShardCount = 0; // Reset the count

                    // Spawn particle effects on the server side
                    ((ServerWorld) this.getWorld()).spawnParticles(
                            ParticleTypes.SCULK_CHARGE_POP,
                            this.getX(), this.getY() + 0.5, this.getZ(),
                            20, // Number of particles
                            0.5, 0.5, 0.5, // Offset for X, Y, Z
                            1// Speed
                    );
                }
                return ActionResult.SUCCESS;
            }

            // Check if the player is holding the Sculk Hoverboard item
            if (itemStack.isOf(ModItems.SCULK_HOVERBOARD_ITEM)) {
                System.out.println("Player is holding the Sculk Hoverboard item.");
                // Toggle maintain Y level
                maintainYLevel = !maintainYLevel;
                if (maintainYLevel) {
                    maintainedYLevel = this.getY();
                }
                return ActionResult.SUCCESS;
            }

            // Modified sneaking interaction
            if (player.isSneaking()) {
                System.out.println("Player is sneaking and right-clicked the entity.");
                // Create item with preserved data
                ItemStack hoverboardItem = createHoverboardItem();
                if (!player.getInventory().insertStack(hoverboardItem)) {
                    player.dropItem(hoverboardItem, false);
                }
                // Remove the entity
                this.discard();
                return ActionResult.SUCCESS;
            }
        }

        // If the player is not holding an echo shard, allow them to ride the entity
        if (!this.hasPassengers() && !itemStack.isOf(Items.ECHO_SHARD)) {
            player.startRiding(this);
            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        playSound(ModSounds.SCULK_HOVERBOARD_HOVERING, 3.0F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SCULK_HOVERBOARD_AMBIENT;
    }



    @Override
    public void travel(Vec3d pos) {
        if (this.isAlive() && this.hasPassengers()) {
            LivingEntity passenger = this.getControllingPassenger();
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();
            this.setYaw(MathHelper.wrapDegrees(passenger.getYaw()));
            this.setPitch(MathHelper.clamp(passenger.getPitch() * 0.5F, -90.0F, 90.0F));
            this.setRotation(this.getYaw(), this.getPitch());
            this.bodyYaw = this.getYaw();
            this.headYaw = this.bodyYaw;

            float forward = passenger.forwardSpeed;
            float strafe = passenger.sidewaysSpeed;
            double y = 0.0;

            if (passenger instanceof PlayerEntity player) {
                if (KeyInputHandler.hoverUpKey.isPressed()) {
                    y = 0.5; // Move up when hover up key is pressed
                } else if (KeyInputHandler.hoverDownKey.isPressed()) {
                    y = -0.5; // Move down when hover down key is pressed
                }

                // Check for drifting
                isDrifting = player.isSneaking();
            }

            // Apply drifting effect
            if (isDrifting) {
                strafe *= 2.0F; // Increase strafe speed when drifting
            }

            Vec3d movement = calculateMovementVector(forward, strafe, y, this.getYaw());

            boolean isMovingNow = forward != 0 || strafe != 0 || y != 0;

            // Apply slippery momentum only when transitioning from moving to stopping
            if (!isMovingNow && wasMoving) {
                // Initial momentum from previous movement
                previousVelocity = this.getVelocity();
            } else if (!isMovingNow) {
                // Continue sliding with decay
                movement = previousVelocity.multiply(momentumDrag);
                previousVelocity = movement;

                // Stop completely if movement is very small
                if (movement.lengthSquared() < 0.001) {
                    previousVelocity = Vec3d.ZERO;
                    movement = Vec3d.ZERO;
                }
            } else {
                // Normal movement
                previousVelocity = Vec3d.ZERO;
            }

            wasMoving = isMovingNow;

            if (maintainYLevel) {
                movement = new Vec3d(movement.x, 0, movement.z); // Maintain current Y level
                this.setPos(this.getX(), maintainedYLevel, this.getZ());
            }
            this.setVelocity(movement);
            this.move(MovementType.SELF, this.getVelocity());

            // Apply base drag only when actively moving
            if (forward != 0 || strafe != 0 || y != 0) {
                this.setVelocity(this.getVelocity().multiply(baseDrag));
            }
        } else {
            super.travel(pos);
        }
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
        if (this.hasPassenger(passenger)) {
            double yOffset = this.getMountedHeightOffset() + passenger.getHeightOffset();
            passenger.setPosition(this.getX(), this.getY() + yOffset, this.getZ());
            passenger.setYaw(MathHelper.wrapDegrees(this.getYaw()));
            passenger.setPitch(MathHelper.clamp(passenger.getPitch(), -90.0F, 90.0F));
        }
    }

    private Vec3d calculateMovementVector(float forward, float strafe, double y, float yaw) {
        double rad = Math.toRadians(yaw);
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);
        // Fix inverted controls by correcting signs
        double dx = (strafe * cos - forward * sin) * 0.5;
        double dz = (forward * cos + strafe * sin) * 0.5;
        return new Vec3d(dx, y, dz);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        Entity var2 = this.getFirstPassenger();
        if (var2 instanceof LivingEntity entity) {
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public boolean isLogicalSideForUpdatingMovement() {
        return true;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public PassiveEntity createChild(ServerWorld level, PassiveEntity partner) {
        return null;
    }

    public boolean collidesWith(Entity other) {
        return canCollide(this, other);
    }

    public boolean isSmallerThanBoat(Entity entity) {
        return entity.getWidth() < this.getWidth();
    }

    public static boolean canCollide(Entity entity, Entity other) {
        return (other.isCollidable() || other.isPushable()) && !entity.isConnectedThroughVehicle(other);
    }

    protected void copyEntityData(Entity entity) {
        entity.setBodyYaw(this.getYaw());
        float f = MathHelper.wrapDegrees(entity.getYaw() - this.getYaw());
        float g = MathHelper.clamp(f, -105.0F, 105.0F);
        entity.prevYaw += g - f;
        entity.setYaw(entity.getYaw() + g - f);
        entity.setHeadYaw(entity.getYaw());
    }

    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < this.getMaxPassengers() && !this.isSubmergedIn(FluidTags.WATER);
    }

    protected int getMaxPassengers() {
        return 2;
    }

    public void onPassengerLookAround(Entity passenger) {
        this.copyEntityData(passenger);
    }

    public boolean canHit() {
        return !this.isRemoved();
    }

    public boolean isCollidable() {
        return true;
    }

    public boolean isPushable() {
        return true;
    }

    @Override
    public double getMountedHeightOffset() {
        return 0.4D; // Adjust this value as needed to make the player stand
    }

    @Override
    public boolean hasNoGravity() {
        return true; // Disable gravity for this entity
    }

    @Override
    public boolean isInsideWall() {
        return false; // Prevent suffocation damage
    }

    public void moveUp() {
        this.setVelocity(this.getVelocity().add(0, 0.5, 0));
    }

    public void moveDown() {
        this.setVelocity(this.getVelocity().add(0, -0.5, 0));
    }

    @Override
    public void writeCustomDataToNbt(net.minecraft.nbt.NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.hasCustomName()) {
            nbt.putString("CustomName", Text.Serializer.toJson(this.getCustomName()));
        }
    }

    @Override
    public void readCustomDataFromNbt(net.minecraft.nbt.NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("CustomName", 8)) {
            this.setCustomName(Text.Serializer.fromJson(nbt.getString("CustomName")));
        }
    }
}