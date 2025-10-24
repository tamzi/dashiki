# Utilities Package

This package contains **utility functions** used by convention plugins. These are NOT convention
plugins themselves.

## 📦 What's in this Package

### Configuration Files

#### `BuildLogicConstants.kt`

**Purpose:** Centralized Android SDK versions

```kotlin
COMPILE_SDK = 36
TARGET_SDK = 36
MIN_SDK = 33
JAVA_VERSION = "17"
KOTLIN_JVM_TARGET = "17"
```

**Used by:** All Android convention plugins  
**Why it exists:** Single source of truth for SDK versions across the project

---

#### `DependencyConfig.kt` ⭐

**Purpose:** Build configuration (currently minimal)

```kotlin
// Only one configuration currently:
DependencyConfig.Compose.STABILITY_CONFIG_FILE = "compose_compiler_config.conf"
```

**Used by:** `AndroidCompose.kt` for Compose compiler stability configuration

**What it contains:**

- ✅ Path to Compose stability configuration file

**What it does NOT contain:**

- ❌ Dependency inclusion flags (dependencies are always included)
- ❌ Feature toggles (features are always enabled)
- ❌ Optional configurations (everything is essential)

**Why so minimal?**
After simplification, most "configuration" became unnecessary because:

- Core dependencies are always included (they're requirements, not options)
- Compiler features work best with default settings
- Build optimizations run automatically

**When to add to this file:**
Only add configuration when you need to:

1. Change a compiler setting that varies by project
2. Point to different configuration files
3. Enable truly optional build features

**Future:** Could be extended if needed, but prefer simplicity.

---

### Build Configuration Utilities

#### `AndroidCompose.kt`

**What:** Configures Jetpack Compose settings
**Type:** Build configuration utility
**Called by:** `AndroidApplicationComposeConventionPlugin`, `AndroidLibraryComposeConventionPlugin`
**Provides:**

- Compose BOM
- Compose dependencies (controlled by `DependencyConfig`)
- Compose compiler configuration
- Stability configuration

---

#### `KotlinAndroid.kt`

**What:** Configures Kotlin for Android projects
**Type:** Build configuration utility
**Called by:** `AndroidApplicationConventionPlugin`, `AndroidLibraryConventionPlugin`
**Provides:**

- Kotlin compiler options
- Core library desugaring
- `androidx.core:core-ktx` dependency

---

#### `JUnit5.kt`

**What:** Configures JUnit 5 for unit tests
**Type:** Test framework configuration utility
**Called by:** `AndroidApplicationConventionPlugin`, `AndroidLibraryConventionPlugin`
**Provides:**

- JUnit Platform configuration
- JUnit 5 dependencies (API, engine, params)
- Enabled for src/test/ only

**Important:** This is automatically applied to ALL modules. It does NOT conflict with testing
conventions.

---

### Build Optimization Utilities

#### `AndroidInstrumentedTests.kt`

**What:** Disables empty androidTest builds
**Type:** Build optimization utility
**Called by:** `AndroidLibraryConventionPlugin`
**Purpose:**

- Detects if `src/androidTest` folder exists
- Disables androidTest build if folder missing
- Saves 5-10 seconds per clean build

**Important:** This is NOT a dependency provider. See `AndroidInstrumentedTestConventionPlugin` for
dependencies.

---

#### `ProjectExtensions.kt`

**What:** Extension functions for Gradle `Project` class
**Type:** Helper utilities
**Provides:**

- `libs` - Access to version catalog
- Other project-level helpers

---

## 🔄 Utilities vs Convention Plugins

### Utilities (THIS PACKAGE):

- ✅ Internal functions called BY convention plugins
- ✅ Configure build settings
- ✅ Add common dependencies
- ✅ Optimize build performance
- ❌ NOT applied directly by modules

### Convention Plugins (../):

- ✅ Applied BY modules in build.gradle.kts
- ✅ Call utilities to configure build
- ✅ Opt-in features
- ✅ Visible in module's plugins block

---

## 📊 Architecture Diagram

```
Module build.gradle.kts
    ↓
    plugins {
        alias(libs.plugins.dashiki.android.application.compose)
    }
    ↓
AndroidApplicationComposeConventionPlugin
    ↓
    calls configureAndroidCompose()
    ↓
util/AndroidCompose.kt
    ↓
    reads DependencyConfig
    ↓
    adds dependencies based on flags
```

---

## 🎯 Common Questions

### "Should I apply utilities as plugins?"

**No.** Utilities are internal functions, not plugins.

### "How do I control what dependencies are included?"

Edit `DependencyConfig.kt` flags.

### "Why is JUnit5.kt separate from AndroidUnitTestConventionPlugin?"

- **JUnit5.kt:** Core test runner (applied to ALL modules automatically)
- **AndroidUnitTestConventionPlugin:** Additional testing libraries (opt-in)

They work together, not instead of each other.

### "What's the difference between AndroidInstrumentedTests.kt and AndroidInstrumentedTestConventionPlugin?"

```
AndroidInstrumentedTests.kt:
  Purpose: Build optimization
  Action: Disables empty test builds
  Applied: Automatically to all libraries
  
AndroidInstrumentedTestConventionPlugin:
  Purpose: Dependency management
  Action: Adds test dependencies
  Applied: Opt-in by modules that have tests
```

---

## 🚀 Adding a New Utility

1. **Create the utility file** in this package
2. **Make it `internal`** - utilities shouldn't be public
3. **Document clearly:**
    - What it does
    - Who calls it
    - What it provides
4. **Add to this README**
5. **Call it from convention plugins**

Example:

```kotlin
// util/NewUtility.kt
internal fun Project.configureSomething(extension: SomeExtension) {
    // Configuration logic
}

// application/SomeConventionPlugin.kt
class SomeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureSomething(extension)
        }
    }
}
```

---

## 📚 Related Documentation

- Convention plugins: `../README.md`
- Build logic overview: `../../README.md`
- Dependency configuration: `DependencyConfig.kt`
