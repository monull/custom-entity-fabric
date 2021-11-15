package io.github.monull.customentity.mixin;

import io.github.monull.customentity.client.CustomEntity;
import io.github.monull.customentity.client.CustomEntityManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class RenderManagerMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M>  {
    protected RenderManagerMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public int id;

    @Inject(at = @At(value = "TAIL"), method = "scale")
    public void scale(T entity, MatrixStack matrices, float amount, CallbackInfo ci) {
        CustomEntity entity1 = CustomEntityManager.entities.get(entity.getId());
        if (entity1 != null) {
            matrices.scale(entity1.lastScaleX, entity1.lastScaleY, entity1.lastScaleZ);
            this.shadowRadius = Math.max(entity1.lastScaleX, entity1.lastScaleZ);
        }
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
    public void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        this.id = livingEntity.getId();
    }

    @ModifyConstant(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", constant = @Constant(floatValue = 1.0F, ordinal = 4))
    public float getColorR(float value) {
        if (CustomEntityManager.entities.get(this.id) != null) {
            return (float) (CustomEntityManager.entities.get(this.id).lastColorR / 255);
        } else return 1.0F;
    }
    @ModifyConstant(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", constant = @Constant(floatValue = 1.0F, ordinal = 5))
    public float getColorG(float value) {
        if (CustomEntityManager.entities.get(this.id) != null) {
            return (float) (CustomEntityManager.entities.get(this.id).lastColorG / 255);
        } else return 1.0F;
    }
    @ModifyConstant(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", constant = @Constant(floatValue = 1.0F, ordinal = 6))
    public float getColorB(float value) {
        if (CustomEntityManager.entities.get(this.id) != null) {
            return (float) (CustomEntityManager.entities.get(this.id).lastColorB / 255);
        } else return 1.0F;
    }
}
