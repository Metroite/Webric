package de.metroite.webric;

import de.metroite.webric.web.Server;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class Webric implements ModInitializer {
    private static final String MODID = "voice-web";

    @Override
    public void onInitialize() {

        Server.start();

        // Ressourceupdate (onWorldInitialize)
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier(MODID, "world_reload");
            }

            @Override
            public void apply(ResourceManager manager) {
                System.out.println("Hello Fabric world!");
            }
        });
        // /Ressourceupdate
    }
}