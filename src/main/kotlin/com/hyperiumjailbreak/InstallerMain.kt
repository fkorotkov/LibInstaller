package com.hyperiumjailbreak

import com.hyperiumjailbreak.stylesheet.MaterialStylesheet
import com.hyperiumjailbreak.view.StartupView
import tornadofx.App
import tornadofx.launch
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@JvmStatic Logger l = LogManager.getLogger()

fun main() {
    launch<InstallerApp>()
}

class InstallerApp : App(StartupView::class, MaterialStylesheet::class)
