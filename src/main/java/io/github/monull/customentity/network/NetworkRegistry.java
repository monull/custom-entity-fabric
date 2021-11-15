package io.github.monull.customentity.network;

import io.github.monull.customentity.client.CustomEntityManager;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class NetworkRegistry {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier("customentity", "custom"), (client, handler, buf, responseSender) -> {
            int ver = buf.readInt();
            if (ver == 0) {
                register(buf);
            } else if (ver == 1) {
                unregister(buf);
            } else if (ver == 2) {
                color(buf);
            } else if (ver == 3) {
                scale(buf);
            } else if (ver == 4) {
                colorAndScale(buf);
            }
        });
    }

    private static void register(ByteBuf buf) {
        int entityId = buf.readInt();
        CustomEntityManager.register(entityId);
    }

    private static void unregister(ByteBuf buf) {
        int entityId = buf.readInt();
        CustomEntityManager.unregister(entityId);
    }

    private static void color(ByteBuf buf) {
        int entityId = buf.readInt();
        int colorR = buf.readInt();
        int colorG = buf.readInt();
        int colorB = buf.readInt();
        int duration = buf.readInt();
        MinecraftClient.getInstance().player.sendMessage(new LiteralText(String.valueOf(colorR) + colorG + colorB), false);
        CustomEntityManager.color(entityId, colorR, colorG, colorB, duration);
    }

    private static void scale(ByteBuf buf) {
        int entityId = buf.readInt();
        float scaleX = buf.readFloat();
        float scaleY = buf.readFloat();
        float scaleZ = buf.readFloat();
        int duration = buf.readInt();
        CustomEntityManager.scale(entityId, scaleX, scaleY, scaleZ, duration);
    }

    private static void colorAndScale(ByteBuf buf) {
        int entityId = buf.readInt();
        byte colorR = buf.readByte();
        byte colorG = buf.readByte();
        byte colorB = buf.readByte();
        float scaleX = buf.readFloat();
        float scaleY = buf.readFloat();
        float scaleZ = buf.readFloat();
        int duration = buf.readInt();
        CustomEntityManager.color(entityId, colorR, colorG, colorB, duration);
        CustomEntityManager.scale(entityId, scaleX, scaleY, scaleZ, duration);
    }
}
