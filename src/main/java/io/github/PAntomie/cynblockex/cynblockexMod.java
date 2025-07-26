package io.github.PAntomie.cynblockex;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class cynblockexMod implements ModInitializer {
	public static final String MOD_ID = "cynblockex";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		ModBlock.initialize();
		ModItem.initialize();
		ModGroup.initialize();
		LOGGER.info("C.Y.N. BlockEX Mod Loaded");
	}
}