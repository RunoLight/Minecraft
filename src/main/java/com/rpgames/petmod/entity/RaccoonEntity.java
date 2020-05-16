package com.rpgames.petmod.entity;

import com.rpgames.petmod.init.RaccoonEntityInit;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class RaccoonEntity extends CreatureEntity {

    public RaccoonEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super((EntityType<? extends CreatureEntity>) RaccoonEntityInit.RACCOON_ENTITY, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.2d));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        //this.goalSelector.addGoal(1, new FollowOwnerGoal());
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0d);

    }
}
