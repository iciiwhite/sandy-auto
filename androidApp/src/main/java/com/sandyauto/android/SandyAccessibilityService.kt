package com.sandyauto.android

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class SandyAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
    }

    override fun onInterrupt() {
    }
}