package com.everest.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private static final File PAWS_DIR = new File(MinecraftClient.getInstance().runDirectory, "paws");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private boolean nsfw = true;

    public void createConfigJson() {
        File configFile = new File(PAWS_DIR, "config.json");

        writeConfigFile(configFile);
    }

    public boolean allowNsfw() {
        File configFile = new File(PAWS_DIR, "config.json");

        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                JsonObject existing = GSON.fromJson(reader, JsonObject.class);
                if (existing != null && existing.has("nsfw") && existing.get("nsfw").isJsonPrimitive()) {
                    boolean nsfw = existing.get("nsfw").getAsBoolean();
                    return nsfw;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void writeConfigFile(File configFile) {
        try {
            PAWS_DIR.mkdirs();
            JsonObject obj = new JsonObject();
            obj.addProperty("nsfw", false);
            try (FileWriter writer = new FileWriter(configFile)) {
                GSON.toJson(obj, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
