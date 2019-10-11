package com.hyperiumjailbreak

import com.hyperiumjailbreak.stylesheet.MaterialStylesheet
import com.hyperiumjailbreak.view.StartupView
import org.apache.logging.log4j.LogManager
import tornadofx.App
import tornadofx.launch

val logger = LogManager.getLogger()!!

fun main() {
    launch<InstallerApp>()
}

fun fail(reason: String) {
    logger.fatal(reason)
}

class InstallerApp : App(StartupView::class, MaterialStylesheet::class)
