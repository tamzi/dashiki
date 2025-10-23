# Dashiki Convention Plugins

This document describes the available convention plugins and how to use them.

## Overview

Convention plugins centralize build configuration to reduce boilerplate and ensure consistency
across modules. They follow the pattern established
by [Now in Android](https://github.com/android/nowinandroid).

## Available Conventions

### Application Conventions

#### `dashiki.android.application`

Base configuration for Android application modules.

**Provides:**

- Android application plugin
- Kotlin Android support
- SDK versions (compile, min, target)
- JUnit 5 configuration
- Lint configuration

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.application)
}
```

#### `dashiki.android.application.compose`

Jetpack Compose configuration for applications.

**Provides:**

- Compose compiler plugin
- Compose BOM
- Compose UI tooling dependencies

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.compose)
}
```

#### `dashiki.android.application.flavors`

Product flavor configuration (demo/prod).

**Provides:**

- `demo` flavor - Uses local/mock data, no backend required
- `prod` flavor - Uses real backend services

**Build Variants Created:**

- `demoDebug`
- `demoRelease`
- `prodDebug`
- `prodRelease`

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.flavors)
}
```

**Build Commands:**

```bash
./gradlew assembleDemoDebug    # Demo version for development
./gradlew assembleProdRelease  # Production release
./gradlew installDemoDebug     # Install demo on device
```

#### `dashiki.android.application.printing`

Debug logging configuration with Timber.

**Provides:**

- Timber logging library
- `BuildConfig.DEBUG_MODE` field (true in debug, false in release)
- `BuildConfig.LOG_LEVEL` field ("DEBUG" in debug, "ERROR" in release)

**Usage in build.gradle.kts:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.printing)
}
```

**Usage in Application class:**

```kotlin
class DashikiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        if (BuildConfig.DEBUG_MODE) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
```

**Usage in code:**

```kotlin
Timber.d("Debug message")
Timber.e("Error: %s", errorMessage)
Timber.i("Info message")
Timber.w("Warning message")
```

#### `dashiki.android.application.firebase`

Firebase configuration for applications.

**Provides:**

- Firebase Crashlytics
- Firebase Performance Monitoring
- Firebase Analytics

### Library Conventions

#### `dashiki.android.library`

Base configuration for Android library modules.

**Provides:**

- Android library plugin
- Kotlin Android support
- SDK versions
- JUnit 5 configuration
- Resource prefixing
- Lint configuration

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.library)
}
```

#### `dashiki.android.library.compose`

Jetpack Compose configuration for libraries.

**Provides:**

- Compose support for libraries
- Compose dependencies

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.library)
    alias(libs.plugins.dashiki.android.library.compose)
}
```

### Feature Conventions

#### `dashiki.android.feature`

Configuration for feature modules.

**Provides:**

- Android library + Compose
- Hilt dependency injection
- Common feature dependencies (Navigation Compose, ViewModel, etc.)

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.feature)
}
```

**Note:** Feature convention expects `:core` modules. Add core module dependencies when created.

### Other Conventions

#### `dashiki.hilt`

Hilt dependency injection configuration.

**Provides:**

- Hilt Android plugin
- Hilt dependencies and compiler

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.hilt)
}
```

#### `dashiki.android.room`

Room database configuration.

**Provides:**

- Room plugin
- Room dependencies and compiler
- KSP configuration

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.room)
}
```

#### `dashiki.android.lint`

Custom lint configuration.

**Usage:**
Applied automatically by application and library conventions.

#### `dashiki.jvm.library`

Configuration for pure JVM/Kotlin library modules.

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.jvm.library)
}
```

## Common Patterns

### New Application Module

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.compose)
    alias(libs.plugins.dashiki.android.application.flavors)  // Optional
    alias(libs.plugins.dashiki.android.application.printing) // Optional
}

android {
    namespace = "com.tamzi.dashiki.mymodule"
    
    defaultConfig {
        applicationId = "com.tamzi.dashiki.mymodule"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    // Module-specific dependencies only
}
```

### New Library Module

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.library)
    alias(libs.plugins.dashiki.android.library.compose) // If using Compose
}

android {
    namespace = "com.tamzi.dashiki.mylibrary"
}

dependencies {
    // Module-specific dependencies only
}
```

### New Feature Module

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.feature)
}

android {
    namespace = "com.tamzi.dashiki.feature.myfeature"
}

dependencies {
    // Feature-specific dependencies only
    // Common feature dependencies are provided by convention
}
```

## What Conventions Provide

You **don't need** to configure in your module build files:

- ✅ `compileSdk`, `minSdk`, `targetSdk`
- ✅ JUnit 5 configuration and dependencies
- ✅ Compose BOM and common Compose dependencies
- ✅ Test instrumentation runner
- ✅ Core library desugaring
- ✅ Kotlin compiler options
- ✅ Lint configuration

## Build Logic Constants

All SDK versions are centralized in `BuildLogicConstants`:

- `COMPILE_SDK = 36`
- `TARGET_SDK = 36`
- `MIN_SDK = 33`
- `JAVA_VERSION = "17"`
- `KOTLIN_JVM_TARGET = "17"`

## Adding New Conventions

1. Create plugin class in `buildLogic/convention/src/main/kotlin/`
2. Register in `buildLogic/convention/build.gradle.kts`
3. Add to `gradle/libs.versions.toml` plugins section
4. Document usage in this file

## References

- [Now in Android Build Logic](https://github.com/android/nowinandroid/tree/main/build-logic)
- [Gradle Convention Plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html)
