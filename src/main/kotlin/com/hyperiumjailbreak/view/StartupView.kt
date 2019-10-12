package com.hyperiumjailbreak.view

import tornadofx.*

class StartupView : View("HyperiumJailbreak Installer - MainMenu") {
    override val root = borderpane {
                center = button("Start") {
                    action {
                        replaceWith<InstallationView>()
                    }
                }
            }
}