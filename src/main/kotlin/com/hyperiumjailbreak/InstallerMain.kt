package com.hyperiumjailbreak

import com.hyperiumjailbreak.stylesheet.MaterialStylesheet
import com.hyperiumjailbreak.view.StartupView
import tornadofx.App
import tornadofx.launch

fun main() {
    launch<InstallerApp>()
}

class InstallerApp : App(StartupView::class, MaterialStylesheet::class)