package com.hyperiumjailbreak.backend.utils

import cc.hyperium.installer.JsonHolder
import com.google.common.io.Files
import com.hyperiumjailbreak.backend.utils.OS.*
import java.io.File
import java.util.*
import java.util.UUID
import java.time.Instant.ofEpochMilli
import java.io.IOException
import java.nio.charset.Charset
import java.time.Instant

object Utils {
    private val iconInBase64 = "data:image/png;base64,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"

    private fun getOS(): OS {
        val os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)
        return when {
            os.contains("mac") || os.contains("darwin") -> Darwin
            os.contains("win") -> Windows
            os.contains("nux") -> Linux
            else -> Other
        }
    }

    fun getMinecraftDirectory(): File {
        return when (getOS()) {
            Windows -> File(System.getenv("APPDATA"), ".minecraft")
            Darwin -> File(System.getProperty("user.home"), "/Library/Application Support/minecraft")
            else -> File(System.getProperty("user.home"), ".minecraft")
        }
    }

    @Suppress("UnstableApiUsage")
    fun buildVersionJson(mc: File) {
        val versions = File(mc, "versions")
        val target = File(versions, "Hyperium 1.8.9")
        target.mkdirs()
        val origin = File(versions, "1.8.9")
        val originJson = File(origin, "1.8.9.json")
        val originJar = File(origin, "1.8.9.jar")
        val targetJson = File(target, "Hyperium 1.8.9.json")
        val targetJar = File(target, "Hyperium 1.8.9.jar")
        val json: JsonHolder
        val launcherProfiles: JsonHolder
        try {
            json = JsonHolder(Files.asCharSource(targetJson, Charset.defaultCharset()).read())
            launcherProfiles = JsonHolder(Files.asCharSource(File(mc, "launcher_profiles.json"), Charset.defaultCharset()).read())
        } catch (ex: IOException) {
            throw Exception()
        }

        val lib = JsonHolder()
        lib.put("name", "cc.hyperium:Hyperium:LOCAL")
        val libs = json.optJSONArray("libraries")
        libs.add(lib.getObject())
        libs.add(JsonHolder().put("name", "net.minecraft:launchwrapper:Hyperium").getObject())
        libs.add(JsonHolder().put("name", "optifine:OptiFine:1.8.9_HD_U_I7").getObject())
        json.put("libraries", libs)
        json.put("id", "Hyperium 1.8.9")
        json.put("mainClass", "net.minecraft.launchwrapper.Launch")
        json.put("minecraftArguments", json.optString("minecraftArguments") + " --tweakClass=cc.hyperium.launch.HyperiumTweaker")

        val profiles = launcherProfiles.optJSONObject("profiles")
        val instant = ofEpochMilli(System.currentTimeMillis())
        var installedUUID = UUID.randomUUID().toString()
        for (key in profiles.keys) {
            if (profiles.optJSONObject(key).has("name"))
                if (profiles.optJSONObject(key).optString("name") == "Hyperium 1.8.9")
                    installedUUID = key
        }
        val p = theProfile(instant)
    }

    fun theProfile(instant: Instant) {
        val profile = JsonHolder()
                .put("name", "Hyperium 1.8.9")
                .put("type", "custom")
                .put("created", instant.toString())
                .put("lastUsed", instant.toString())
                .put("lastVersionId", "Hyperium 1.8.9")
                .put("javaArgs", "-Duser.country=US -Duser.language=en -Xms512M -Xmx" + config.getWam() + "G")
                .put("icon", iconInBase64)
        if (System.getProperty("java.version").startsWith("1.8"))
            if (System.getProperty("sun.arch.data.model", "").equals("64", ignoreCase = true)) {
                val file = File(System.getProperty("java.home"), "bin" + sep + "java" + if (InstallerUtils.getOS() === InstallerUtils.OSType.Windows) "w.exe" else "")
                if (file.exists())
                    profile.put("javaDir", file.absolutePath)
            }
    }
}