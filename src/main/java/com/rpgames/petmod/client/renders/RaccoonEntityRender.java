package com.rpgames.petmod.client.renders;

import com.rpgames.petmod.PetMod;
import com.rpgames.petmod.client.models.RaccoonEntityModel;
import com.rpgames.petmod.entity.RaccoonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RaccoonEntityRender extends MobRenderer<RaccoonEntity, RaccoonEntityModel<RaccoonEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(PetMod.MOD_ID,
            "textures/entity/raccoon_entity/raccoon_entity.png");

    public RaccoonEntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RaccoonEntityModel<RaccoonEntity>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(RaccoonEntity entity) {
        return TEXTURE;
    }
}
