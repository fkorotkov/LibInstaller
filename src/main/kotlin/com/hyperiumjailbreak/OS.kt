package com.hyperiumjailbreak

enum OSType {
    Windows,
    Darwin,
    Linux,
    Other
}

fun type() {
    val os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)
    when {
        os.contains("mac"), os.contains("darwin") -> return OSType.Darwin
        os.contains("win")                        -> return OSType.Windows
        os.contains("nux")                        -> return OSType.Linux
        else                                      -> return OSType.Other
    }
}

fun minecraftDir() {
    val os = type()
    when {
        os.equals(OSType.Windows) -> return File(System.getenv("APPDATA"), ".minecraft")
        os.equals(OSType.Darwin)  -> return File(System.getProperty("user.home") + "/Library/Application Support", "minecraft")
        else                      -> return File(System.getProperty("user.home"), ".minecraft")
    }
}
