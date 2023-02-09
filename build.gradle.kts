import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"

    id("io.ktor.plugin") version "2.2.3" //this one is added in plugins
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val exposedVersion: String by project
dependencies {
    implementation("io.arrow-kt:arrow-core:1.1.2")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    testImplementation(kotlin("test"))
    implementation("com.mysql:mysql-connector-j:8.0.31")
    implementation("io.ktor:ktor-server-core-jvm:2.2.2")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.2.2")
    implementation("io.ktor:ktor-serialization-jackson-jvm:2.2.2")
    implementation("io.ktor:ktor-server-netty-jvm:2.2.2")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.2.2")
    implementation("ch.qos.logback:logback-classic:1.2.11")
}