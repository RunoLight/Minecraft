//package com.rpgames.petmod.client.renders;
//
//import com.rpgames.petmod.client.models.RaccoonEntityModel;
//import com.rpgames.petmod.entity.RaccoonEntity;
//import com.rpgames.petmod.init.RegistryHandler;
//import net.minecraft.client.renderer.entity.EntityRenderer;
//import net.minecraft.client.renderer.entity.EntityRendererManager;
//import net.minecraft.client.renderer.entity.LivingRenderer;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//public class RaccoonEntityRender extends MobRenderer<RaccoonEntity, RaccoonEntityModel<RaccoonEntity>> {
//
//    public RaccoonEntityRender(EntityRendererManager manager) {
//        super(manager, new RaccoonEntityModel<RaccoonEntity>(), 0.5f);
//    }
//
//    @Override
//    public ResourceLocation getEntityTexture(RaccoonEntity entity) {
//        return RegistryHandler.location("textures/entities/raccoon_entity");
//    }
//
//    public static class RenderFactory implements IRenderFactory<RaccoonEntity>{
//        @Override
//        public EntityRenderer<? super RaccoonEntity> createRenderFor(EntityRendererManager manager) {
//            return new RaccoonEntityRender(manager);
//        }
//
//    }
//}
