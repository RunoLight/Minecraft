package com.rpgames.petmod.entity;

import com.rpgames.petmod.init.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class RaccoonEntity extends CowEntity {

    public RaccoonEntity(EntityType<? extends RaccoonEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public RaccoonEntity createChild(AgeableEntity ageableEntity) {
        RaccoonEntity entity = new RaccoonEntity(RegistryHandler.RACCOON_ENTITY.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
                SpawnReason.SPAWN_EGG, null, null);
        return entity;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.3d));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(RegistryHandler.PEANUT_FOOD.get()), false));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == RegistryHandler.PEANUT_FOOD.get() ||
               stack.getItem() == Items.TROPICAL_FISH ||
               stack.getItem() == Items.EGG ||
               stack.getItem() == Items.SALMON;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FOX_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_FOX_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_FOX_SNIFF, 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }
}

