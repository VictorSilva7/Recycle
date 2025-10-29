// ARQUIVO: build.gradle.kts (NÍVEL DO MÓDULO :app)

plugins {
    // CORREÇÃO: Substituído alias(libs.plugins...) por id("...")
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.recycle"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.recycle"
        minSdk = 26 // Mantendo 26 para evitar o erro do ícone adaptável
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // ----------------------------------------------------
    // DEPENDÊNCIAS DO FIREBASE
    // ----------------------------------------------------
    val firebaseBom = platform("com.google.firebase:firebase-bom:34.4.0")
    implementation(firebaseBom)
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    // ----------------------------------------------------
    // DEPENDÊNCIAS DO PROJETO (AGORA COM SINTAXE DE STRING DIRETA)
    // ----------------------------------------------------

    // Substituído libs.appcompat por:
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Substituído libs.material por:
    implementation("com.google.android.material:material:1.11.0")

    // ConstraintLayout (Versão atualizada)
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    // CardView
    implementation("androidx.cardview:cardview:1.0.0")

    // Testes - Corrigidos para sintaxe direta ou padrão
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}