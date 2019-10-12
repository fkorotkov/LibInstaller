package com.hyperiumjailbreak.view

import tornadofx.*

class StartupView : View("Hyperium Jailbreak Installer - MainMenu") {
    override val root =
            borderpane {
                setMinSize(1200.0, 800.0)
                center = button("Start") {
                    action {
                        replaceWith<InstallationView>()
                    }
                }
            }
}