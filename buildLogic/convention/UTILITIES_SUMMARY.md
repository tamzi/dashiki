# Convention Utilities Summary

## ✅ Improvements Made

### 1. Fixed Deprecated Compose Compiler APIs

**Problem:** Using deprecated `stabilityConfigurationFile` and `enableStrongSkippingMode`

**Solution:**

- Updated to `stabilityConfigurationFiles.set()` (accepts list)
- Updated to `featureFlags.set()` with `ComposeFeatureFlag.StrongSkipping`
- Disabled by default since it's enabled automatically in newer Compose versions

**File:** `util/AndroidCompose.kt`

---

### 2. Centralized Dependency Configuration

**Problem:** Dependencies hardcoded in convention plugins, hard to control project-wide

**Solution:** Created `DependencyConfig.kt` with feature flags

```kotlin
// Example: Control what Compose dependencies are included
DependencyConfig.Compose.INCLUDE_MATERIAL3 = true
DependencyConfig.Compose.INCLUDE_ICONS_EXTENDED = true

// Example: Control testing dependencies
DependencyConfig.Testing.INCLUDE_TRUTH = true
DependencyConfig.Testing.INCLUDE_ROBOLECTRIC = true
```

**Benefits:**

- Single source of truth for dependency inclusion
- Easy to enable/disable dependencies project-wide
- Can be extended to load from external config files
- Clear visibility of what's included

**File:** `util/DependencyConfig.kt`

---

### 3. Clarified Utility vs Plugin Roles

**Problem:** Confusion about what AndroidInstrumentedTests.kt, JUnit5.kt, and convention plugins do

**Solution:** Added comprehensive documentation explaining:

#### `util/AndroidInstrumentedTests.kt`

- **Role:** Build optimization utility
- **Purpose:** Disables empty androidTest builds
- **Called by:** Convention plugins automatically
- **Does NOT:** Add dependencies

#### `util/JUnit5.kt`

- **Role:** Test framework configuration utility
- **Purpose:** Configures JUnit 5 for unit tests
- **Called by:** All Android conventions automatically
- **Relationship:** Works WITH AndroidUnitTestConventionPlugin (not instead of)

#### `test/AndroidInstrumentedTestConventionPlugin.kt`

- **Role:** Testing dependencies provider
- **Purpose:** Adds androidx.test, Espresso, etc.
- **Applied by:** Modules that have tests (opt-in)
- **Does NOT:** Configure build optimization

**Files:** Updated documentation in each file + created `util/README.md`

---

### 4. Created Comprehensive Utils README

**Created:** `util/README.md`

**Contents:**

- What's in the utils package
- Purpose of each utility file
- Utilities vs Convention Plugins comparison
- Architecture diagrams
- Common questions answered
- How to add new utilities

---

## 📊 Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                  Module build.gradle.kts                    │
│                                                             │
│  plugins {                                                  │
│      alias(libs.plugins.dashiki.android.application)       │
│      alias(libs.plugins.dashiki.android.application.compose)│
│      id("dashiki.android.unit.test")                       │
│  }                                                          │
└──────────────────────┬──────────────────────────────────────┘
                       │
           ┌───────────┴────────────┐
           ↓                        ↓
┌──────────────────────┐ ┌──────────────────────────┐
│  Convention Plugins  │ │   Testing Conventions    │
│                      │ │                          │
│ - Application        │ │ - Unit Test             │
│ - Compose            │ │ - Instrumented Test     │
│ - Library            │ │ - Compose UI Test       │
└──────────┬───────────┘ └────────────┬─────────────┘
           │                          │
           └───────────┬──────────────┘
                       ↓
        ┌──────────────────────────┐
        │   Utility Functions      │
        │   (util/ package)        │
        │                          │
        │ - AndroidCompose.kt      │
        │ - KotlinAndroid.kt       │
        │ - JUnit5.kt              │
        │ - AndroidInstrumentedTests.kt │
        └──────────┬───────────────┘
                   ↓
        ┌──────────────────────────┐
        │   DependencyConfig.kt    │
        │   (Centralized Control)  │
        │                          │
        │ Controls what dependencies│
        │ are included             │
        └──────────────────────────┘
```

---

## 🎯 Key Takeaways

### For Developers:

1. **Want to change what dependencies are included?**
   → Edit `DependencyConfig.kt`

2. **Adding a new module?**
   → Apply convention plugins, dependencies are automatic

3. **Adding tests to a module?**
   → Apply the appropriate testing convention plugins

4. **Confused about a utility file?**
   → Read `util/README.md`

### For Convention Plugin Authors:

1. **Adding dependencies?**
   → Check `DependencyConfig` flags before adding

2. **Creating a new utility?**
   → Make it `internal`, document clearly, update README

3. **Need build optimization?**
   → Add to utils package, not as a convention plugin

---

## 📁 File Organization

```
buildLogic/convention/src/main/kotlin/
├── util/                          # Utilities (internal functions)
│   ├── README.md                  # ⭐ Start here
│   ├── DependencyConfig.kt        # ⭐ Control dependencies here
│   ├── BuildLogicConstants.kt     # SDK versions
│   ├── AndroidCompose.kt          # Compose configuration
│   ├── KotlinAndroid.kt           # Kotlin configuration
│   ├── JUnit5.kt                  # JUnit 5 setup
│   ├── AndroidInstrumentedTests.kt # Build optimization
│   └── ProjectExtensions.kt       # Helper extensions
│
├── application/                   # Application conventions
│   ├── AndroidApplicationConventionPlugin.kt
│   └── AndroidApplicationComposeConventionPlugin.kt
│
├── library/                       # Library conventions
│   ├── AndroidLibraryConventionPlugin.kt
│   └── AndroidLibraryComposeConventionPlugin.kt
│
└── test/                          # Testing conventions
    ├── AndroidUnitTestConventionPlugin.kt
    ├── AndroidInstrumentedTestConventionPlugin.kt
    └── AndroidComposeInstrumentedTestConventionPlugin.kt
```

---

## 🚀 What's Next?

Potential future enhancements:

1. **External Configuration:**
   ```kotlin
   // Load DependencyConfig from gradle.properties or JSON
   val config = DependencyConfig.loadFrom("build-config.json")
   ```

2. **Per-Module Overrides:**
   ```kotlin
   // Allow modules to override global config
   dashiki {
       compose {
           includeIconsExtended = false
       }
   }
   ```

3. **Build Variants Configuration:**
   ```kotlin
   // Different dependencies for debug vs release
   DependencyConfig.Debug.INCLUDE_LEAK_CANARY = true
   ```

4. **Dependency Analytics:**
   ```kotlin
   // Report what dependencies each module uses
   ./gradlew dependencyReport
   ```

---

## 📚 Related Documentation

- **Main conventions docs:** `CONVENTIONS.md`
- **Utils package README:** `util/README.md`
- **Build logic overview:** `README.md`
- **Testing conventions:** See `CONVENTIONS.md` → "Testing Conventions"
