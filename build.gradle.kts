

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.0"
    id("io.ktor.plugin") version "2.2.1"
}


group = "mystore.net"
version = "0.0.1"
application {
    mainClass.set("mystore.net.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}




dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    testImplementation(libs.server.test)
    testImplementation(libs.jetbrains.kotlin.test.junit)
    implementation(libs.io.ktor.server.call.logging)
    implementation(libs.io.ktor.server.status.pages)
    implementation(libs.io.ktor.server.content.negotiation)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation (libs.io.ktor.serialization.jackson)
    implementation(libs.io.ktor.server.auth)
    implementation(libs.io.ktor.server.auth.jwt)
    implementation(libs.commons.codec)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)
    implementation(libs.postgresql)
    implementation(libs.zaxxer.hikariCP.library)
    implementation (libs.org.jbcrypt)
    implementation(libs.request.validation)
    // implementation(libs.firebase.admin)



}


