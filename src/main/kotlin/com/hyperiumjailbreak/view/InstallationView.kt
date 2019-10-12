package com.hyperiumjailbreak.view

import com.hyperiumjailbreak.backend.InstallationUtils
import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.button

class InstallationView : View("Hyperium Jailbreak - Installer") {
    override val root = borderpane {
        center = button("Starting Install...") {
            action {
                InstallationUtils.install()
                if (InstallationUtils.callback.getCallbackCode() == 2) {
                    text = InstallationUtils.callback.getCallbackText()
                }
            }
        }
    }
}
