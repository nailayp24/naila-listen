// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    // GANTI KE VERSI KSP K2 COMPILER INI
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
}