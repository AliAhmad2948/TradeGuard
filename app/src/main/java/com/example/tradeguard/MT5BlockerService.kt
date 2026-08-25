package com.example.tradeguard

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import java.util.Calendar
import java.util.TimeZone

class MT5BlockerService : AccessibilityService() {

    private val targetPackage = "com.metaquotes.metatrader5"

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null || event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return

        if (packageName == targetPackage && isLockoutWindowActive()) {
            performGlobalAction(GLOBAL_ACTION_HOME)
            Toast.makeText(this, "MT5 is locked! Discipline over emotion.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isLockoutWindowActive(): Boolean {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Karachi"))
        val currentMinutesOfDay = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)

        val lockStartMinutes = 18 * 60 + 30 // 18:30 PKT
        val lockEndMinutes = 4 * 60 + 30    // 04:30 PKT

        return currentMinutesOfDay >= lockStartMinutes || currentMinutesOfDay < lockEndMinutes
    }

    override fun onInterrupt() {}
}
