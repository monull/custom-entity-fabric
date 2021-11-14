package io.github.monull.customentity.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.LiteralText;

import java.util.HashMap;
import java.util.Map;

public class CustomEntityManager {

    public static Map<Integer, CustomEntity> entities = new HashMap<>();

    public static void register(int id) {
        if (entities.get(id) == null) {
            entities.put(id, new CustomEntity());
        }
    }

    public static void scale(int id, float x, float y, float z, int duration) {
        if (entities.get(id) != null) {
            entities.get(id).setScale(x, y, z, duration);
        } else {
            MinecraftClient.getInstance().player.sendMessage(new LiteralText("entity is null"), false);
        }
    }

    public static void init() {
        ClientTickEvents.START_WORLD_TICK.register(world -> {
            entities.values().forEach(CustomEntity::update);
        });
    }
}
