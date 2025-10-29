// ARQUIVO: build.gradle.kts (NÍVEL DO PROJETO - RAIZ)

plugins {
    // CORREÇÃO: Remova 'alias(libs.plugins.android.application) apply false'
    // E use a sintaxe ID e Version diretamente (substitua '8.5.1' pela sua versão real do AGP):
    id("com.android.application") version "8.5.1" apply false

    // O Plugin do Google Services, que já estava correto:
    id("com.google.gms.google-services") version "4.4.4" apply false
}