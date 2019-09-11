package com.hyperiumjailbreak

import com.hyperiumjailbreak.stylesheet.MaterialStylesheet
import com.hyperiumjailbreak.view.StartupView
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.App
import tornadofx.FX
import tornadofx.launch


object InstallerMain {
    @JvmStatic
    fun main(args: Array<String>) {
        launch<InstallerApp>()
    }
}

class InstallerApp : App(StartupView::class, MaterialStylesheet::class)