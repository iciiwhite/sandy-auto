package com.sandyauto.platform

actual fun getPlatformName(): String = System.getProperty("os.name")