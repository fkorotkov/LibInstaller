package com.hyperiumjailbreak

import java.io.File
import java.util.*

enum class OSType {
    Windows,
    Darwin,
    Linux,
    Other
}

fun getType(): OSType {
    val os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)
    return when {
        os.contains("mac") || os.contains("darwin") -> OSType.Darwin
        os.contains("win") -> OSType.Windows
        os.contains("nux") -> OSType.Linux
        else -> OSType.Other
    }
}

fun getMinecraftDirectory(): File {
    val os = getType()
    return when {
        os.equals(OSType.Windows) -> File(System.getenv("APPDATA"), ".minecraft")
        os.equals(OSType.Darwin) -> File(System.getProperty("user.home") + "/Library/Application Support", "minecraft")
        else -> File(System.getProperty("user.home"), ".minecraft")
    }
}
