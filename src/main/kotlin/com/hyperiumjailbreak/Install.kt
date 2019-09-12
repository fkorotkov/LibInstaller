package com.hyperiumjailbreak

import cc.hyperium.installer.ProtectionDomain

class Install {
    fun do() {
        @JvmStatic val sep = File.separator;
        val mc = new File(config.getDir());

        mc.exists() ? InstallerMain.logger.info("Found MC Dir") : InstallerMain.fail("No MC Dir")
    }
}
