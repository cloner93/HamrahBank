pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "HamrahBank"
include(":app")
include(":ballon")
include(":profile")
include(":core")
include(":auth")
include(":home")
include(":account")
include(":transfer")
include(":camera")
include(":compressor")
include(":navigation")
include(":facilities")
include(":calender")
