package io.github.PAntomie.cynblockex;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.text.Text;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class cynblockexMod implements ModInitializer {
    public static final String MOD_ID = "cynblockex";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final String MOD_VERSION_URL = "https://antomie.pages.dev/mod-version.json";
    private static String CURRENT_VERSION;
    private static boolean isNewVersionAvailable = false;

    @Override
    public void onInitialize() {
        loadCurrentVersion();
        checkForUpdates();
        ModBlock.initialize();
        ModItem.initialize();
        ModGroup.initialize();

        // player login event
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (isNewVersionAvailable) {  // update availed
                // get player entity
                ServerPlayerEntity player = handler.getPlayer();
                // text remind
                player.sendMessage(Text.translatable("message.cynblockex.update_available"));
            }
        });

        LOGGER.info("C.Y.N. BlockEX Mod Loaded");
    }

    private void loadCurrentVersion() {
        Path modJsonPath = FabricLoader.getInstance().getModContainer(MOD_ID)
                .orElseThrow(() -> new RuntimeException("Failed to find mod container for " + MOD_ID))
                .getPath("fabric.mod.json");
        try (InputStream input = Files.newInputStream(modJsonPath)) {
            JsonObject modJson = new Gson().fromJson(new String(input.readAllBytes()), JsonObject.class);
            CURRENT_VERSION = modJson.get("version").getAsString();
            if (CURRENT_VERSION == null || CURRENT_VERSION.isEmpty()) {
                throw new RuntimeException("version not found in fabric.mod.json");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkForUpdates() {
        new Thread(() -> {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MOD_VERSION_URL))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    LOGGER.error("Unexpected code {}", response.statusCode());
                    return;
                }

                String responseBody = response.body();
                try {
                    VersionInfo versionInfo = new Gson().fromJson(responseBody, VersionInfo.class);
                    if (versionInfo == null || versionInfo.cynblockex == null || versionInfo.cynblockex.isEmpty()) {
                        throw new RuntimeException("Invalid version info in response");
                    }

                    if (!CURRENT_VERSION.equals(versionInfo.cynblockex)) {
                        LOGGER.info("New version available: {}", versionInfo.cynblockex);
                        isNewVersionAvailable = true;
                    } else {
                        LOGGER.info("You are using the latest version.");
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed to parse version info: {}", e.getMessage());
                    e.printStackTrace();
                }
            } catch (Exception e) {
                LOGGER.error("Failed to check for updates: {}", e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    // 静态内部类用于存储版本信息
    public static class VersionInfo {
        public String cynblockex;
    }
}