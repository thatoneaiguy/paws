package com.everest;

import com.everest.files.PregenFiles;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Paws implements ModInitializer {
	public static final String MOD_ID = "paws";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final int COSMETIC_VERSION = 2;

	@Override
	public void onInitialize() {
		PregenFiles.generateDefaultFiles();
		LOGGER.info("Paws has initialised!");
	}
}