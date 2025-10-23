package com.tamzi.dashiki

import android.app.Application
import timber.log.Timber

/**
 * Main Application class for Dashiki.
 *
 * Initializes app-wide components and configurations.
 */
class DashikiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber logging
        initializeLogging()
    }

    /**
     * Initialize Timber logging based on build configuration.
     *
     * In debug builds, logs are printed to logcat.
     * In release builds, logging is disabled for performance and security.
     */
    private fun initializeLogging() {
        if (BuildConfig.DEBUG_MODE) {
            // Plant a debug tree that logs to logcat
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber logging initialized for ${BuildConfig.BUILD_TYPE} build")
            Timber.d("Log level: ${BuildConfig.LOG_LEVEL}")
        }
    }
}
