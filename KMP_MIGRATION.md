# Kotlin Multiplatform (KMP) Migration Guide

## Overview
This project has been successfully migrated to Kotlin Multiplatform, enabling code sharing across Android, iOS, and Desktop platforms.

## What Was Migrated

### Core Shared Modules
The following modules have been converted to KMP and can now run on multiple platforms:

1. **`:model`** - Data models and DTOs
2. **`:domain`** - Business logic and use cases
3. **`:network`** - API layer with Ktor client
4. **`:data`** - Repository implementations
5. **`:shared`** - Main SDK module that aggregates all shared functionality

### Platform-Specific Modules
These modules remain Android-specific for now:
- `:app` - Android application
- `:core` - Android utilities (uses Android-specific APIs)
- `:feature:*` - UI feature modules (Android/Compose specific)
- `:navigation` - Navigation (Android/Compose specific)
- `:camera`, `:ballon`, `:compressor`, `:calender`, `:snapui` - Android utilities

## Architecture

```
┌─── :app (Android App)
│    ├── :shared (KMP Aggregator)
│    │   ├── :model (KMP)
│    │   ├── :domain (KMP)
│    │   ├── :network (KMP)
│    │   └── :data (KMP)
│    ├── :core (Android)
│    ├── :navigation (Android)
│    └── :feature:* (Android)
```

## Platform Support

### Currently Supported Platforms
- **Android** - Full support with all features
- **iOS** - Shared business logic, models, and networking
- **Desktop (JVM)** - Shared business logic, models, and networking

### Source Set Structure
Each KMP module follows this structure:
```
src/
├── commonMain/kotlin/     # Shared code
├── androidMain/kotlin/    # Android-specific code
├── iosMain/kotlin/        # iOS-specific code
└── desktopMain/kotlin/    # Desktop-specific code
```

## Key Dependencies

### Shared Dependencies (All Platforms)
- **Kotlin Serialization** - JSON serialization
- **Ktor Client** - HTTP networking
- **Kotlinx Coroutines** - Asynchronous programming

### Platform-Specific Dependencies
- **Android**: Ktor Android Engine, DataStore, Paging
- **iOS**: Ktor Darwin Engine
- **Desktop**: Ktor Java Engine

## Building for Different Platforms

### Android
```bash
./gradlew :app:assembleDebug
```

### iOS Framework
```bash
./gradlew :shared:linkDebugFrameworkIosX64
./gradlew :shared:linkReleaseFrameworkIosX64
```

### Desktop JAR
```bash
./gradlew :shared:desktopJar
```

### All Platforms
```bash
./gradlew build
```

## Development Workflow

### Adding New Shared Code
1. Add common interfaces/classes in `commonMain`
2. Add platform-specific implementations in respective platform source sets
3. Use `expect`/`actual` keywords for platform-specific APIs

### Example: Platform Detection
```kotlin
// commonMain
expect fun isAndroid(): Boolean
expect fun isIOS(): Boolean
expect fun isDesktop(): Boolean

// androidMain
actual fun isAndroid(): Boolean = true
actual fun isIOS(): Boolean = false
actual fun isDesktop(): Boolean = false
```

## Testing

### Running Tests
```bash
# All platforms
./gradlew allTests

# Specific platforms
./gradlew desktopTest
./gradlew iosX64Test
```

## Migration Benefits

1. **Code Reuse**: Business logic, models, and networking shared across platforms
2. **Consistency**: Same business rules on all platforms
3. **Maintenance**: Single source of truth for core functionality
4. **Testing**: Shared tests for business logic
5. **Future-Ready**: Easy to add new platforms (Web, watchOS, etc.)

## Next Steps

### Recommended Future Enhancements
1. **Shared Database**: Migrate to SQLDelight for cross-platform database
2. **Shared ViewModels**: Use shared business logic in UI layer
3. **iOS App**: Create native iOS application using shared modules
4. **Desktop App**: Create Compose Desktop application
5. **Web Support**: Add JavaScript target for web applications

### Dependency Injection
Consider migrating from Hilt to a KMP-compatible DI solution like:
- Koin (already KMP-ready)
- Kodein-DI

## Configuration Files Updated

- `gradle/libs.versions.toml` - Added KMP plugin and iOS-specific dependencies
- `build.gradle.kts` - Added KMP plugin
- `gradle.properties` - Added KMP configuration
- `settings.gradle.kts` - Added shared module
- Individual module `build.gradle.kts` files - Converted to KMP configuration

## Troubleshooting

### Common Issues
1. **Native targets disabled**: Normal on non-Mac machines for iOS targets
2. **SDK not found**: Ensure ANDROID_HOME is set or create `local.properties`
3. **Build errors**: Check that all modules use consistent Kotlin versions

### Build Performance
- KMP builds may be slower initially due to multiple target compilation
- Use `kotlin.native.ignoreDisabledTargets=true` to skip unavailable targets
- Consider using build cache and parallel builds

## Version Information
- Kotlin: 2.1.0
- Android Gradle Plugin: 8.7.3
- Ktor: 2.3.6
- Gradle: 8.9