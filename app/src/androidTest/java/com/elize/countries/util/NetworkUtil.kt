package com.elize.countries.util

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

class NetworkUtil {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun toggleConnectionState(enable: Boolean) {
        if (enable) {
            device.executeShellCommand("svc wifi enable");
            device.executeShellCommand("svc data enable");
        } else {
            device.executeShellCommand("svc wifi disable");
            device.executeShellCommand("svc data disable");
        }

    }
}