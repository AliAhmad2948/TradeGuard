package com.example.tradeguard

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TradeGuardAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        Toast.makeText(context, "Anti-Uninstall Activated", Toast.LENGTH_SHORT).show()
    }
}
