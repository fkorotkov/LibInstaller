package com.hyperiumjailbreak.backend

import cc.hyperium.installer.ProtectionDomain
import com.google.common.io.Files
import com.hyperiumjailbreak.backend.callback.Callback
import com.hyperiumjailbreak.backend.utils.DownloadTask
import com.hyperiumjailbreak.backend.utils.Utils
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.IOException

val logger = LogManager.getLogger()!!

@Suppress("UnstableApiUsage")
object InstallationUtils {
    val callback = Callback()

    init {
        System.setProperty("http.agent", "Mozilla/5.0 HJBInstall")
        install()
    }

    fun install() {
        val sep = File.separator
        val mc = Utils.getMinecraftDirectory()
        val libraries = File(mc, "versions")

        if (mc.exists()) {
            logger.info("Found MC Dir")
        }
        if (!mc.exists()) {
            logger.fatal("No MC Dir")
        } else {
            val versions = File(mc, "versions")
            val origin = File(versions, "1.8.9")
            val originJson = File(origin, "1.8.9.json")
            val originJar = File(origin, "1.8.9.jar")

            if (Utils.checkForClient(origin, originJar, originJson)) {
                callback.sendText("You need to run Minecraft 1.8.9 first!")
                callback.sendCode(2)
                logger.fatal("No 1.8.9")
            } else {
                val target = File(versions, "Hyperium 1.8.9")
                val libs = File(mc, "libraries")

                Utils.deleteOldFiles(target)

                val local = File(ProtectionDomain::class.java.protectionDomain.codeSource.location.toURI())
                val localLib = File(libs, "cc" + sep + "hyperium" + sep + "Hyperium" + sep + "LOCAL" + sep + "Hyperium-LOCAL.jar")

                try {
                    localLib.parentFile.mkdirs()
                    Files.copy(local, File(localLib.parent, localLib.name))
                } catch (e: IOException) {
                    logger.fatal(e.message)
                    callback.sendCode(2)
                    callback.sendText("Couldn't copy local Jar")
                }

                val tmpDir = java.nio.file.Files.createTempDirectory("Hyperium").toFile()

                val optifine = File(tmpDir, DownloadTask("https://raw.githubusercontent.com/hyperiumjailbreak/tools/master/OptiFine_1.8.9_HD_U_I7.jar", tmpDir.toString()).download())

                val targetJson = File(target, "Hyperium 1.8.9.json")
                val targetJar = File(target, "Hyperium 1.8.9.jar")

                Utils.copyVersion(originJar, originJson, targetJar, targetJson)

                val addonsDir = File(mc, "addons")
                if (!addonsDir.exists()) addonsDir.mkdirs()

                Utils.downloadLaunchWrapper(libraries)

                Utils.patchOptifine(libraries, optifine, originJar)

                Utils.buildAndSetLauncherProfiles(mc, target)
            }
        }
    }
}