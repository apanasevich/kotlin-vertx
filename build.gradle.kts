import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.10"
}

group = "kotlin-vertx"
version = "1.0-SNAPSHOT"

val vertxVersion = "3.6.1"
val log4jVersion = "2.9.1"

repositories {
  mavenCentral()
}

dependencies {
  compile(kotlin("stdlib-jdk8"))
  compile("io.vertx:vertx-core:$vertxVersion")
  compile("io.vertx:vertx-web:$vertxVersion")
  compile("io.vertx:vertx-hazelcast:$vertxVersion")

  compile("org.apache.logging.log4j:log4j-core:$log4jVersion")
  compile("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
  compile("org.slf4j:jul-to-slf4j:1.7.4")

  compile("info.picocli:picocli:3.7.0")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}