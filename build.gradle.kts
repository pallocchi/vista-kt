group = "com.bulltimate"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.dokka") version "0.10.0"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.10")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.10")
    testCompile("org.assertj:assertj-core:3.11.1")
    testCompile("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.+")
}

tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
    configuration {
        includes = listOf("Module.md")
        samples = listOf("$rootDir/src/test/kotlin")
    }
}

tasks {
    withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}
