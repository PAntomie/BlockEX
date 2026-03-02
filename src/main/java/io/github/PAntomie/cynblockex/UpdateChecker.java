package io.github.PAntomie.cynblockex;

import com.google.gson.Gson;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static io.github.PAntomie.cynblockex.cynblockexMod.LOGGER;

public class UpdateChecker {
    public static boolean isNewVersionAvailable = false;
    private static final String MOD_VERSION_URL = "https://antomie.pages.dev/mod-version.json";
    private static final String CURRENT_VERSION = FabricLoader.getInstance()
            .getModContainer("cynblockex")
            .map(container -> container.getMetadata().getVersion().getFriendlyString())
            .orElse("0.0.0");

    private static void checkForUpdates() {
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
                        LOGGER.info("New version available: {} (current: {})", versionInfo.cynblockex, CURRENT_VERSION);
                        isNewVersionAvailable = true;
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed to parse version info: {}", e.getMessage());
                }
            } catch (Exception e) {
                LOGGER.error("Failed to check for updates: {}", e.getMessage());
            }
        }).start();
    }

    private static void UpdateTip() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (isNewVersionAvailable) {
                ServerPlayerEntity player = handler.getPlayer();
                player.sendMessage(Text.translatable("message.cynblockex.update_available"));
            }
        });
    }

    public static class VersionInfo {
        public String cynblockex;
    }

    public static void initialize() {
        checkForUpdates();
        UpdateTip();
    }
}
