package util

/**
 * Centralized Android SDK configuration
 * This object contains all SDK versions used across the project
 */
object BuildLogicConstants {
    /**
     * Compile SDK version - the highest Android API level your app is designed to work with
     */
    const val COMPILE_SDK = 36
    
    /**
     * Target SDK version - the highest Android API level your app has been tested with
     */
    const val TARGET_SDK = 36
    
    /**
     * Minimum SDK version - the lowest Android API level your app supports
     * Set to 33 (Android 13) to support predictive back gesture (enableOnBackInvokedCallback)
     */
    const val MIN_SDK = 33
    
    /**
     * Build tools version
     */
    const val BUILD_TOOLS = "36.0.0"
    
    /**
     * Java version for compilation
     */
    const val JAVA_VERSION = "17"
    
    /**
     * Kotlin JVM target version
     */
    const val KOTLIN_JVM_TARGET = "17"
}
