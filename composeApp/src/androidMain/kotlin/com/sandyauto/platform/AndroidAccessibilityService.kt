package com.sandyauto.platform

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AndroidAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
    }

    override fun onInterrupt() {
    }
}