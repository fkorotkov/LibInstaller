package com.hyperiumjailbreak.view

import tornadofx.*

class StartupView : View("HyperiumJailbreak Installer - Main Menu") {
    override val root = borderpane {
                center = button("Start Install!") {
                    action {
                        replaceWith<InstallationView>()
                    }
                }
            }
}