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
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testCompile("org.assertj:assertj-core:3.11.1")
}

tasks.dokka {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
    configuration {
        includes = listOf("Module.md")
        samples = listOf("$rootDir/src/test/kotlin")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
