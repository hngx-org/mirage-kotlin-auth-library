// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20") // Update to the latest version
        classpath ("org.jetbrains.kotlin:kotlin-annotation-processing-gradle:1.8.20") // Update to the latest version
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id ("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}