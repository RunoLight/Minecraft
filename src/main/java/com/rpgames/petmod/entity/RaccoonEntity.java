package com.rpgames.petmod.entity;

import com.rpgames.petmod.init.RegistryHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class RaccoonEntity extends CowEntity {


    public RaccoonEntity(EntityType<? extends RaccoonEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public RaccoonEntity createChild(AgeableEntity ageableEntity) {
        RaccoonEntity entity = new RaccoonEntity(RegistryHandler.RACCOON_ENTITY.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
                SpawnReason.SPAWN_EGG, (ILivingEntityData) null, (CompoundNBT) null);
        //entity.setGlowing(true);
        return entity;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.2d));
//        this.goalSelector.addGoal(1, new CatSitOnBlockGoal(this, 0d));
//        this.goalSelector.addGoal(1, new FollowMobGoal(this, 16d, 1f, 1f));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
//        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2000.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }
}

//    @Override
//    public IPacket<?> createSpawnPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }

