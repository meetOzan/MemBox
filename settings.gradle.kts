pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MemBox"
include(":app")
include(":core")
include(":core:data")
include(":feature")
include(":feature:login")
include(":core:presentation")
include(":core:localization")
