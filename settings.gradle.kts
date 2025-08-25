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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "HamrahBank"
include(":app")

include(":feature:auth")
include(":feature:home")
include(":feature:transfer")
include(":feature:account")
include(":feature:profile")
include(":feature:facilities")

include(":ballon")
include(":core")
include(":camera")
include(":compressor")
include(":navigation")
include(":calender")
include(":snapui")
include(":snapui:receipt")
include(":domain")
include(":network")
include(":data")
include(":model")
include(":shared")
