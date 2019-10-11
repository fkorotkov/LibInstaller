package com.hyperiumjailbreak

import cc.hyperium.installer.ProtectionDomain
import com.google.common.io.Files
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

class Install {
    fun install() {
        val sep = File.separator
        val mc = minecraftDir()

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
            fail("You need to run Minecraft 1.8.9 first!")

        val target = File(versions, "Hyperium 1.8.9")
        val libs = File(mc, "libraries")

        if(target.exists()) {
            FileUtils.deleteDirectory(target)
        }

        val local = File(ProtectionDomain.class.protectionDomain.codeSource.location.uri)
        val localLib = File(libs, "cc" + sep + "hyperium" + sep + "Hyperium" + sep + "LOCAL" + sep + "Hyperium-LOCAL.jar")
        try {
            localLib.getParentFile().mkdirs()
            Files.copy(local, new File(localLib.parent, localLib.name)
        } catch (e: IOException) {
            logger.fatal(e.message)
            fail("Couldn't copy local Jar")
        }

        val tmpDir = File(java.nio.Files.createTempDirectory("Hyperium").file)
    }
}
