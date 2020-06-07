package com.rpgames.petmod.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.rpgames.petmod.entity.RaccoonEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RaccoonEntityModel<T extends RaccoonEntity> extends EntityModel<T> {
    private final ModelRenderer scotina_enot_dranyi;
    private final ModelRenderer head_full;
    private final ModelRenderer body_full;
    private final ModelRenderer tail_for_u;
    private final ModelRenderer lb;
    private final ModelRenderer lf;
    private final ModelRenderer rb;
    private final ModelRenderer rf;

    public RaccoonEntityModel() {
        textureWidth = 64;
        textureHeight = 32;

        scotina_enot_dranyi = new ModelRenderer(this);
        scotina_enot_dranyi.setRotationPoint(0.0F, 24.0F, 0.0F);

        head_full = new ModelRenderer(this);
        head_full.setRotationPoint(2.0F, -4.0F, -4.0F);
        scotina_enot_dranyi.addChild(head_full);
        head_full.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
        head_full.setTextureOffset(56, 10).addBox(-2.0F, -2.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        head_full.setTextureOffset(56, 6).addBox(-4.0F, -8.0F, -4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        head_full.setTextureOffset(56, 6).addBox(0.0F, -8.0F, -4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        body_full = new ModelRenderer(this);
        body_full.setRotationPoint(0.0F, -4.0F, 0.0F);
        scotina_enot_dranyi.addChild(body_full);
        body_full.setTextureOffset(0, 18).addBox(-2.0F, -4.0F, -2.0F, 6.0F, 4.0F, 10.0F, 0.0F, false);

        tail_for_u = new ModelRenderer(this);
        tail_for_u.setRotationPoint(3.0F, -4.0F, 8.0F);
        scotina_enot_dranyi.addChild(tail_for_u);
        tail_for_u.setTextureOffset(24, 0).addBox(-4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

        lb = new ModelRenderer(this);
        lb.setRotationPoint(3.0F, -4.0F, 7.0F);
        scotina_enot_dranyi.addChild(lb);
        lb.setTextureOffset(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        lf = new ModelRenderer(this);
        lf.setRotationPoint(3.0F, -4.0F, -1.0F);
        scotina_enot_dranyi.addChild(lf);
        lf.setTextureOffset(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        rb = new ModelRenderer(this);
        rb.setRotationPoint(-1.0F, -4.0F, 7.0F);
        scotina_enot_dranyi.addChild(rb);
        rb.setTextureOffset(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        rf = new ModelRenderer(this);
        rf.setRotationPoint(-1.0F, -4.0F, -1.0F);
        scotina_enot_dranyi.addChild(rf);
        rf.setTextureOffset(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    }
@Override
public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
    scotina_enot_dranyi.render(matrixStack, buffer, packedLight, packedOverlay);
}
    @Override
    public void setRotationAngles(RaccoonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
                this.head_full.rotateAngleX = headPitch * ((float) Math.PI / 180F);
                this.head_full.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
                this.rb.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
                this.lb.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
                this.rf.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
                this.lf.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
                this.tail_for_u.rotateAngleY = headPitch * ((float) Math.PI / 180F);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }
}
