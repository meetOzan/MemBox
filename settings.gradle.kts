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
include(":core:domain")
include(":core:network")
include(":feature:home")
include(":navigation")
include(":core:common")
include(":core:model")
include(":feature:addmemory")
include(":feature:detail")
include(":feature:profile")
