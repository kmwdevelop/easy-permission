plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    `maven-publish`
}

android {
    namespace = "kr.co.kmwdev.easy_permission_aos"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "kr.co.kmwdev"
                artifactId = "easy-permission-aos"
                version = "1.0.0"
                pom.packaging = "aar"
                artifact("${layout.projectDirectory}/build/outputs/aar/easy-permission-aos-release.aar")
            }
        }

        repositories {
            maven {
                name = "bluetoothLib-sdk-android"
                url = uri("https://maven.pkg.github.com/kmwdevelop/easy-permission-aos")
//                credentials {
//                    username =
//                    password = githubAccessToken
//                }
            }
        }
    }
}
