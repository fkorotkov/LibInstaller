package com.hyperiumjailbreak

import com.hyperiumjailbreak.stylesheet.MaterialStylesheet
import com.hyperiumjailbreak.view.StartupView
import tornadofx.App
import tornadofx.launch
import org.apache.logging.log4j.LogManager

public val logger = LogManager.getLogger()

fun main() {
    launch<InstallerApp>()
}

public fun fail(reason: String) {
    logger.fatal(reason)
}

class InstallerApp : App(StartupView::class, MaterialStylesheet::class)
