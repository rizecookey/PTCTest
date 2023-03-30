plugins {
    kotlin("jvm") version "1.8.10"
    application
}

group = "net.rizecookey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.5")
    implementation("org.fusesource.jansi:jansi:2.4.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("net.rizecookey.ptctest.cli.PTCTestKt")
}