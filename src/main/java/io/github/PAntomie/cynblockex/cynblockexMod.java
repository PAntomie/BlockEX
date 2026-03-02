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

    @Override
    public void onInitialize() {
        UpdateChecker.initialize();
        ModBlock.initialize();
        ModItem.initialize();
        ModGroup.initialize();
        LOGGER.info("C.Y.N. BlockEX Mod Loaded");
    }

}