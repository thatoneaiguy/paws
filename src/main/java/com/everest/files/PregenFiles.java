package com.everest.files;

import com.everest.Paws;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import java.io.*;

public class PregenFiles {
    private static final int MOD_VERSION = Paws.COSMETIC_VERSION;
    private static final File PAWS_DIR = new File(MinecraftClient.getInstance().runDirectory, "paws");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static byte[] foxEarsTexture = new byte[] {
            (byte)0x89, (byte)0x50, (byte)0x4E, (byte)0x47, (byte)0x0D, (byte)0x0A, (byte)0x1A, (byte)0x0A, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0D, (byte)0x49, (byte)0x48, (byte)0x44, (byte)0x52,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x08, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1F, (byte)0xF3, (byte)0xFF,
            (byte)0x61, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xB0, (byte)0x49, (byte)0x44, (byte)0x41, (byte)0x54, (byte)0x78, (byte)0x5E, (byte)0x63, (byte)0x60, (byte)0x18, (byte)0x32, (byte)0xE0,
            (byte)0x40, (byte)0xA1, (byte)0xCF, (byte)0x7F, (byte)0x10, (byte)0xBE, (byte)0x35, (byte)0x2D, (byte)0xF6, (byte)0x3F, (byte)0xBA, (byte)0x1C, (byte)0x18, (byte)0x20, (byte)0x2B, (byte)0x40,
            (byte)0x57, (byte)0x04, (byte)0x12, (byte)0xFF, (byte)0xF7, (byte)0x73, (byte)0xC5, (byte)0xFF, (byte)0x3F, (byte)0x77, (byte)0x7B, (byte)0xC1, (byte)0x6A, (byte)0x90, (byte)0xE5, (byte)0xE0,
            (byte)0x00, (byte)0x24, (byte)0x01, (byte)0x52, (byte)0x00, (byte)0xC3, (byte)0x30, (byte)0x85, (byte)0x60, (byte)0xCD, (byte)0xFF, (byte)0xEF, (byte)0x81, (byte)0x0D, (byte)0x00, (byte)0x61,
            (byte)0xA2, (byte)0x0C, (byte)0x00, (byte)0x2B, (byte)0x06, (byte)0x6A, (byte)0x82, (byte)0xD9, (byte)0x0C, (byte)0xE3, (byte)0xC3, (byte)0xC4, (byte)0xD0, (byte)0xF5, (byte)0x82, (byte)0x01,
            (byte)0x8A, (byte)0x62, (byte)0xA8, (byte)0x06, (byte)0xB8, (byte)0x81, (byte)0x50, (byte)0x17, (byte)0xAC, (byte)0x08, (byte)0x74, (byte)0xC1, (byte)0xAE, (byte)0x19, (byte)0x06, (byte)0x40,
            (byte)0x0A, (byte)0x60, (byte)0x36, (byte)0xC1, (byte)0x0C, (byte)0x78, (byte)0xBF, (byte)0xBB, (byte)0x18, (byte)0x1C, (byte)0x26, (byte)0x04, (byte)0x35, (byte)0xC3, (byte)0x00, (byte)0x48,
            (byte)0x21, (byte)0x48, (byte)0x13, (byte)0xCC, (byte)0x66, (byte)0x98, (byte)0x21, (byte)0x44, (byte)0x1B, (byte)0x00, (byte)0x02, (byte)0x20, (byte)0xC5, (byte)0xC8, (byte)0xE1, (byte)0x01,
            (byte)0x33, (byte)0x64, (byte)0xA2, (byte)0xB1, (byte)0x3D, (byte)0x69, (byte)0x86, (byte)0xC0, (byte)0x5D, (byte)0x02, (byte)0x34, (byte)0x04, (byte)0xC4, (byte)0x26, (byte)0xDA, (byte)0x10,
            (byte)0x70, (byte)0x58, (byte)0x40, (byte)0x03, (byte)0x13, (byte)0xA6, (byte)0x91, (byte)0x24, (byte)0x57, (byte)0xC0, (byte)0x0C, (byte)0x00, (byte)0x69, (byte)0x02, (byte)0x69, (byte)0x80,
            (byte)0x25, (byte)0x2E, (byte)0x18, (byte)0x1F, (byte)0x5D, (byte)0x3D, (byte)0x06, (byte)0x00, (byte)0x45, (byte)0x29, (byte)0x48, (byte)0x21, (byte)0x4C, (byte)0x31, (byte)0x8C, (byte)0x8D,
            (byte)0x2C, (byte)0x36, (byte)0x78, (byte)0x00, (byte)0x00, (byte)0xB1, (byte)0x3F, (byte)0x19, (byte)0x46, (byte)0x84, (byte)0x86, (byte)0x15, (byte)0xB2, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x49, (byte)0x45, (byte)0x4E, (byte)0x44, (byte)0xAE, (byte)0x42, (byte)0x60, (byte)0x82
    };

    public static void generateDefaultFiles() {
        File versionFile = new File(PAWS_DIR, "version.json");
        File configFile = new File(PAWS_DIR, "config.json");
        File defaultDir = new File(PAWS_DIR, "default");

        boolean needsRegen = true;

        if (versionFile.exists()) {
            try (FileReader reader = new FileReader(versionFile)) {
                JsonObject existing = GSON.fromJson(reader, JsonObject.class);
                if (existing != null && existing.has("version") && existing.get("version").isJsonPrimitive()) {
                    int existingVersion = existing.get("version").getAsInt();
                    if (existingVersion == MOD_VERSION) {
                        needsRegen = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!configFile.exists() && needsRegen == false) {
            needsRegen = true;
        }

        if (needsRegen) {
            if (versionFile.exists()) {
                versionFile.delete();
            }

            Config.writeConfigFile(configFile);
            deleteRecursive(defaultDir);
            writeVersionFile(versionFile);
            writeDefaultCosmeticJson();
            writeTextures();
        }
    }

    private static void writeVersionFile(File versionFile) {
        try {
            PAWS_DIR.mkdirs();
            JsonObject obj = new JsonObject();
            obj.addProperty("version", MOD_VERSION);
            try (FileWriter writer = new FileWriter(versionFile)) {
                GSON.toJson(obj, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDefaultCosmeticJson() {
        File defaultDir = new File(PAWS_DIR, "default");
        defaultDir.mkdirs();
        File earsFile = new File(defaultDir, "fox_ears.json");

        JsonObject earsJson = new JsonObject();
        earsJson.addProperty("active", false);
        earsJson.addProperty("nsfw", false);
        earsJson.addProperty("name", "fox ears");
        earsJson.addProperty("texture", "null");

        JsonObject offset = new JsonObject();
        offset.addProperty("x", -8);
        offset.addProperty("y", 24);
        offset.addProperty("z", 0);
        earsJson.add("offset", offset);

        JsonObject rotation = new JsonObject();
        rotation.addProperty("x", 0);
        rotation.addProperty("y", 0);
        rotation.addProperty("z", 0);
        earsJson.add("rotation", rotation);

        try (FileWriter writer = new FileWriter(earsFile)) {
            GSON.toJson(earsJson, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteRecursive(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteRecursive(child);
            }
        }
        file.delete();
    }

    public static void writeTextures() {
        File defaultDir = new File(MinecraftClient.getInstance().runDirectory, "paws/default");
        if (!defaultDir.exists()) defaultDir.mkdirs();

        writeTexture(defaultDir, "fox_ears.png", foxEarsTexture);
    }

    private static void writeTexture(File dir, String fileName, byte[] data) {
        File outFile = new File(dir, fileName);
        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}