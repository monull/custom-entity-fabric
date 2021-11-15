package io.github.monull.customentity.mixin;

import io.github.monull.customentity.client.CustomEntity;
import io.github.monull.customentity.client.CustomEntityManager;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntityRenderer.class)
public class CreeperMixin {
    @Inject(method = "scale(Lnet/minecraft/entity/mob/CreeperEntity;Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At("TAIL"))
    public void scale(CreeperEntity creeperEntity, MatrixStack matrixStack, float f, CallbackInfo ci) {
        CustomEntity custom = CustomEntityManager.entities.get(creeperEntity.getId());
        if (custom != null) {
            matrixStack.scale(custom.lastScaleX, custom.lastScaleY, custom.lastScaleZ);
        }
    }
}
