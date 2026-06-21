package com.example.naila_listen.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.naila_listen.MainActivity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Pengingat SIGANA"
        val message = intent.getStringExtra("message") ?: "Waktunya mengecek posko"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("OPEN_FRAGMENT", "LOGISTIK")
            }
        } else {
            Intent(context, MainActivity::class.java)
        }

        NotificationHelper.showNotification(context = context, title = title, message = message, intent = targetIntent)
    }
}