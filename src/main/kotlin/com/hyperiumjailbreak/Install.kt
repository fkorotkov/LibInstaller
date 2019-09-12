package com.hyperiumjailbreak

import cc.hyperium.installer.ProtectionDomain
import org.apache.commons.io.FileUtils
import com.google.common.io.Files
import java.io.File
import java.io.IOException

class Install {
    fun doInstall() {
        val sep = File.separator
        val mc = File(config.getDir())

        if(mc.exists()) {
            InstallerMain.logger.info("Found MC Dir")
        }
        if(!mc.exists()) {
            InstallerMain.fail("No MC Dir")
        }

        val versions = File(mc, "versions")
        val origin = File(versions, "1.8.9")
        val originJson = File(origin, "1.8.9.json")
        val originJar = File(origin, "1.8.9.jar")
        if(!origin.exists() || !originJson.exists() || !originJar.exists())
            InstallerMain.fail("You need to run Minecraft 1.8.9 first!")

        val target = File(versions, "Hyperium 1.8.9")
        val libs = File(mc, "libraries")

        if(target.exists()) {
            FileUtils.deleteDirectory(target)
        }

        val local = File(ProtectionDomain.class.getProtectionDomain().getCodeSource().getLocation().toURI())
        val localLib = File(libs, "cc" + sep + "hyperium" + sep + "Hyperium" + sep + "LOCAL" + sep + "Hyperium-LOCAL.jar")
        try {
            localLib.getParentFile().mkdirs()
            Files.copy(local, new File(localLib.parent, localLib.getName())
        } catch (e: IOException) {
            InstallerMain.logger.fatal(e.message)
            InstallerMain.fail("Couldn't copy local Jar")
        }

        val tmpDir = File(java.nio.Files.createTempDirectory("Hyperium").toFile())
    }
}
