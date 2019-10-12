package com.hyperiumjailbreak.backend.utils

import com.hyperiumjailbreak.backend.utils.OS.*
import java.io.File
import java.util.*
object Utils {
    fun getOS(): OS {
        val os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)
        return when {
            os.contains("mac") || os.contains("darwin") -> Darwin
            os.contains("win") -> Windows
            os.contains("nux") -> Linux
            else -> Other
        }
    }

    fun getMinecraftDirectory(): File {
        return when (getOS()) {
            Windows -> File(System.getenv("APPDATA"), ".minecraft")
            Darwin -> File(System.getProperty("user.home"), "/Library/Application Support/minecraft")
            else -> File(System.getProperty("user.home"), ".minecraft")
        }
    }
}