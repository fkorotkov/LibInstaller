package com.hyperiumjailbreak.view

import tornadofx.*

class StartupView : View("Hyperium Jailbreak Installer - MainMenu") {
    override val root =
            borderpane {
                setMinSize(1200.0, 800.0)
                center = text("Welcome to The Hyperium Jailbreak Installer") {
                    style {
                        fontSize = 30.0.px
                    }
                }
            }
}