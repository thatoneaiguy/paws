package com.everest.util;

import com.everest.Paws;
import com.google.gson.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CosmeticReader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public JsonElement readCosmeticJson(File file, String data) {
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JsonObject existing = GSON.fromJson(reader, JsonObject.class);
                if (existing != null && existing.has("version") && existing.get("version").isJsonPrimitive()) {
                    return existing.get(data);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JsonNull.INSTANCE;
    }

    public boolean isActive(File file) {
        return readCosmeticJson(file, "active").getAsBoolean();
    }

    public boolean isNsfw(File file) {
        return readCosmeticJson(file, "nsfw").getAsBoolean();
    }

    public String getName(File file) {
        return readCosmeticJson(file, "name").getAsString();
    }

    public Identifier getTexture(File file) {
        return new Identifier(Paws.MOD_ID, readCosmeticJson(file, "texture").getAsString());
    }

    public Vec3d readOffset(File file) {
        JsonElement element = readCosmeticJson(file, "offset");
        if (element != null && element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            double x = obj.has("x") ? obj.get("x").getAsDouble() : 0.0;
            double y = obj.has("y") ? obj.get("y").getAsDouble() : 0.0;
            double z = obj.has("z") ? obj.get("z").getAsDouble() : 0.0;
            return new Vec3d(x, y, z);
        }
        return Vec3d.ZERO;
    }

    public Vec3d readRotation(File file) {
        JsonElement element = readCosmeticJson(file, "rotation");
        if (element != null && element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            double x = obj.has("x") ? obj.get("x").getAsDouble() : 0.0;
            double y = obj.has("y") ? obj.get("y").getAsDouble() : 0.0;
            double z = obj.has("z") ? obj.get("z").getAsDouble() : 0.0;
            return new Vec3d(x, y, z);
        }
        return Vec3d.ZERO;
    }
}
