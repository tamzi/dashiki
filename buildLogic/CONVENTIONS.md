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
- Lint configuration
- Core library desugaring

**Dependencies Included:**

- `androidx.core:core-ktx` - Core Android KTX extensions
- `com.google.android.material:material` - Material Components for themes

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

**Dependencies Included:**

- `androidx.compose:compose-bom` - Compose Bill of Materials (platform)
- `androidx.activity:activity-compose` - Activity Compose integration
- `androidx.compose.material3:material3` - Material 3 components
- `androidx.compose.ui:ui-tooling-preview` - Compose preview support
- `androidx.compose.ui:ui-tooling` (debug) - Compose debugging tools
- `androidx.compose.material:material-icons-extended` - Extended Material icons
- `androidx.constraintlayout:constraintlayout-compose` - ConstraintLayout for Compose

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
- Compose dependencies (same as application.compose)

**Dependencies Included:**

- `androidx.compose:compose-bom` - Compose Bill of Materials (platform)
- `androidx.activity:activity-compose` - Activity Compose integration
- `androidx.compose.material3:material3` - Material 3 components
- `androidx.compose.ui:ui-tooling-preview` - Compose preview support
- `androidx.compose.ui:ui-tooling` (debug) - Compose debugging tools
- `androidx.compose.material:material-icons-extended` - Extended Material icons
- `androidx.constraintlayout:constraintlayout-compose` - ConstraintLayout for Compose

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

### Testing Conventions

Testing conventions are separated by test type and purpose. Apply only the testing conventions
you need for your module.

#### `dashiki.android.unit.test`

**Purpose:** JVM-based unit tests (test source set)

**Test Types:**

- Business logic tests
- ViewModel tests
- Use case/repository tests
- Pure function tests
- Coroutine/Flow tests

**Dependencies Included:**

- `kotlinx-coroutines-test` - Testing utilities for coroutines and flows
- `truth` - Fluent assertions library
- `turbine` - Flow testing library
- `robolectric` - Android framework testing on JVM

**When to use:**
Apply to modules that test logic without requiring a device/emulator.
These tests run on the local JVM and are fast.

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.unit.test)
}
```

**Example test:**

```kotlin
@Test
fun `viewModel emits correct state`() = runTest {
    val viewModel = MyViewModel()
    viewModel.state.test {
        assertThat(awaitItem()).isEqualTo(Loading)
        assertThat(awaitItem()).isEqualTo(Success(data))
    }
}
```

#### `dashiki.android.instrumented.test`

**Purpose:** Device/emulator-based instrumented tests (androidTest source set)

**Test Types:**

- Integration tests
- E2E tests
- UI interaction tests (non-Compose)
- System behavior tests

**Dependencies Included:**

- `androidx.test.ext:junit` - JUnit test extensions
- `androidx.test:core` - Core testing framework
- `androidx.test:runner` - Test runner
- `androidx.test:rules` - Test rules
- `androidx.test.espresso:espresso-core` - UI testing framework

**When to use:**
Apply to modules that test Android-specific behavior, system interactions,
or UI components that require a device/emulator.

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.instrumented.test)
}
```

**Example test:**

```kotlin
@Test
fun testButtonClick() {
    onView(withId(R.id.button))
        .perform(click())
    onView(withId(R.id.text))
        .check(matches(withText("Clicked")))
}
```

#### `dashiki.android.compose.instrumented.test`

**Purpose:** Compose UI instrumented tests (androidTest source set)

**Test Types:**

- Compose UI tests
- @Composable function tests
- Compose state tests
- Compose navigation tests
- Semantics tree tests

**Dependencies Included:**

- `androidx.compose.ui:ui-test-junit4` - Compose UI testing framework
- `androidx.compose.ui:ui-test-manifest` (debug) - Test manifest for debugging

**Requires:**

- Must be used with a Compose-enabled module
- Usually combined with `dashiki.android.instrumented.test` for complete setup

**When to use:**
Apply to modules that test Jetpack Compose UI components.
Use with `dashiki.android.instrumented.test` for full test infrastructure.

**Usage:**

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.instrumented.test)
    alias(libs.plugins.dashiki.android.compose.instrumented.test)
}
```

**Example test:**

```kotlin
@Test
fun testComposeButton() {
    composeTestRule.setContent {
        MyButton(onClick = { /* ... */ })
    }
    composeTestRule
        .onNodeWithText("Click Me")
        .performClick()
    composeTestRule
        .onNodeWithText("Clicked!")
        .assertIsDisplayed()
}
```

#### `dashiki.android.test`

**Purpose:** Legacy test module configuration

**Note:** This is the legacy convention for dedicated Android test modules (`:app:androidTest`).
For most cases, use the specific testing conventions above instead.

**Usage:**
Only use for separate test-only modules, not for regular modules with tests.

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.test)
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
    
    // Testing (only if this module has tests)
    alias(libs.plugins.dashiki.android.unit.test)              // For unit tests
    alias(libs.plugins.dashiki.android.instrumented.test)      // For UI/E2E tests
    alias(libs.plugins.dashiki.android.compose.instrumented.test) // For Compose UI tests
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
    // Common dependencies are provided by conventions
}
```

### New Library Module

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.library)
    alias(libs.plugins.dashiki.android.library.compose) // If using Compose
    
    // Testing (only if this module has tests)
    alias(libs.plugins.dashiki.android.unit.test)        // For unit tests
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
    
    // Testing conventions (feature modules typically have both unit and UI tests)
    alias(libs.plugins.dashiki.android.unit.test)
    alias(libs.plugins.dashiki.android.instrumented.test)
    alias(libs.plugins.dashiki.android.compose.instrumented.test)
}

android {
    namespace = "com.tamzi.dashiki.feature.myfeature"
}

dependencies {
    // Feature-specific dependencies only
    // Common feature dependencies are provided by convention
}
```

### Module with Only Unit Tests

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.library)
    alias(libs.plugins.dashiki.android.unit.test)  // Only unit testing
}

android {
    namespace = "com.tamzi.dashiki.domain"
}

dependencies {
    // Domain logic dependencies
}
```

### Module with Only Compose UI Tests

```kotlin
plugins {
    alias(libs.plugins.dashiki.android.library)
    alias(libs.plugins.dashiki.android.library.compose)
    alias(libs.plugins.dashiki.android.instrumented.test)
    alias(libs.plugins.dashiki.android.compose.instrumented.test)
}

android {
    namespace = "com.tamzi.dashiki.ui.components"
}

dependencies {
    // UI component dependencies
}
```

## What Conventions Provide

You **don't need** to configure in your module build files:

### Configuration
- ✅ `compileSdk`, `minSdk`, `targetSdk`
- ✅ JUnit 5 configuration and dependencies
- ✅ Compose BOM and common Compose dependencies
- ✅ Test instrumentation runner
- ✅ Core library desugaring
- ✅ Kotlin compiler options
- ✅ Lint configuration

### Common Dependencies (Automatically Included)

**Base Android (application & library):**

- ✅ `androidx.core:core-ktx`

**Android Application:**

- ✅ `com.google.android.material:material`

**Compose (application & library):**

- ✅ `androidx.compose:compose-bom` (platform)
- ✅ `androidx.activity:activity-compose`
- ✅ `androidx.compose.material3:material3`
- ✅ `androidx.compose.ui:ui-tooling-preview`
- ✅ `androidx.compose.ui:ui-tooling` (debug)
- ✅ `androidx.compose.material:material-icons-extended`
- ✅ `androidx.constraintlayout:constraintlayout-compose`

**Unit Testing (`dashiki.android.unit.test`):**

- ✅ `kotlinx-coroutines-test`
- ✅ `truth` (assertions)
- ✅ `turbine` (Flow testing)
- ✅ `robolectric`

**Instrumented Testing (`dashiki.android.instrumented.test`):**

- ✅ `androidx.test.ext:junit`
- ✅ `androidx.test:core`
- ✅ `androidx.test:runner`
- ✅ `androidx.test:rules`
- ✅ `androidx.test.espresso:espresso-core`

**Compose Instrumented Testing (`dashiki.android.compose.instrumented.test`):**

- ✅ `androidx.compose.ui:ui-test-junit4`
- ✅ `androidx.compose.ui:ui-test-manifest` (debug)

**Note:** Only add dependencies to your module's `build.gradle.kts` if they are:

1. Module-specific (not used across all modules)
2. Optional features (not required by all modules)
3. API dependencies that need to be exposed to consumers (use `api()` instead of `implementation()`)

**Testing Note:** Testing dependencies are opt-in. Apply the specific testing convention plugins
(`dashiki.android.unit.test`, `dashiki.android.instrumented.test`, etc.) only to modules that need
them.

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

