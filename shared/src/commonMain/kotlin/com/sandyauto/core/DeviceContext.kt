package com.sandyauto.core

data class DeviceContext(
    val screenWidth: Int,
    val screenHeight: Int,
    val screenshot: ByteArray?,
    val activeApp: String
) {
    companion object {
        fun capture(): DeviceContext {
            return DeviceContext(0, 0, null, "")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as DeviceContext
        if (screenWidth != other.screenWidth) return false
        if (screenHeight != other.screenHeight) return false
        if (screenshot != null) {
            if (other.screenshot == null) return false
            if (!screenshot.contentEquals(other.screenshot)) return false
        } else if (other.screenshot != null) return false
        if (activeApp != other.activeApp) return false
        return true
    }

    override fun hashCode(): Int {
        var result = screenWidth
        result = 31 * result + screenHeight
        result = 31 * result + (screenshot?.contentHashCode() ?: 0)
        result = 31 * result + activeApp.hashCode()
        return result
    }
}