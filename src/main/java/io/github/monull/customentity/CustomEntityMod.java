package io.github.monull.customentity;

import io.github.monull.customentity.client.CustomEntityManager;
import io.github.monull.customentity.network.NetworkRegistry;
import net.fabricmc.api.ClientModInitializer;

public class CustomEntityMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NetworkRegistry.init();
        CustomEntityManager.init();
    }
}
