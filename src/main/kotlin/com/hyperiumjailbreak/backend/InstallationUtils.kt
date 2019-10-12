package com.hyperiumjailbreak.backend

import cc.hyperium.installer.ProtectionDomain
import com.google.common.io.Files
import com.hyperiumjailbreak.backend.utils.Utils
import com.hyperiumjailbreak.backend.callback.Callback
import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.LogManager
import java.io.File
import java.io.IOException

object InstallationUtils {
    private val logger = LogManager.getLogger()!!
    val callback = Callback()
    fun install() {
        val sep = File.separator
        val mc = Utils.getMinecraftDirectory()

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
            if (!origin.exists() || !originJson.exists() || !originJar.exists()) {
                callback.sendText("You need to run Minecraft 1.8.9 first!")
                callback.sendCode(2)
                logger.fatal("No 1.8.9")
            } else {

                val target = File(versions, "Hyperium 1.8.9")
                val libs = File(mc, "libraries")

                if (target.exists()) {
                    FileUtils.deleteDirectory(target)
                }

                val local = File(ProtectionDomain::class.java.protectionDomain.codeSource.location.toURI())
                val localLib = File(libs, "cc" + sep + "hyperium" + sep + "Hyperium" + sep + "LOCAL" + sep + "Hyperium-LOCAL.jar")
                try {
                    localLib.parentFile.mkdirs()
                    Files.copy(local, File(localLib.parent, localLib.name))
                } catch (e: IOException) {
                    logger.fatal("H ${e.message} MC Dir: $mc")
                    callback.sendCode(2)
                    callback.sendText("Couldn't copy local Jar")
                }

                val tmpDir = Files.createTempDir().renameTo(File("Hyperium"))
            }
        }
    }
}