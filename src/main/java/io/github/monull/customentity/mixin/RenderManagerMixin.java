package io.github.monull.customentity.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.monull.customentity.client.CustomEntity;
import io.github.monull.customentity.client.CustomEntityManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class RenderManagerMixin<T extends LivingEntity> {
    @Inject(at = @At(value = "TAIL"), method = "scale")
    public void scale(T entity, MatrixStack matrices, float amount, CallbackInfo ci) {
        CustomEntity entity1 = CustomEntityManager.entities.get(entity.getId());
        if (entity1 != null) {
            matrices.scale(entity1.lastScaleX, entity1.lastScaleY, entity1.lastScaleZ);
        }
    }


}
