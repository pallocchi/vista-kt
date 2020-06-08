import java.util.Date

group = "com.bulltimate"
version = "0.0.1"

val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/bulltimate/vista-kt"
val pomScmUrl = "https://github.com/bulltimate/vista-kt"
val pomIssueUrl = "https://github.com/bulltimate/vista-kt/issues"
val pomDesc = "https://github.com/bulltimate/vista-kt"

val githubRepo = "bulltimate/vista-kt"
val githubReadme = "README.md"

val pomLicenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val pomDeveloperId = "pallocchi"
val pomDeveloperName = "Pablo Pallocchi"

plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.dokka") version "0.10.0"
    id("com.jfrog.bintray") version "1.8.5"
    `maven-publish`
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

publishing {
    publications {
        create<MavenPublication>("vista") {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion
            from(components["java"])
            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    setPublications("vista")
    pkg.apply {
        repo = "maven"
        name = artifactName
        userOrg = "bulltimate"
        githubRepo = githubRepo
        vcsUrl = pomScmUrl
        description = "A modern technical analysis library for Kotlin."
        setLabels("kotlin", "technical", "analysis", "trading", "ta", "bitcoin", "crypto", "ethereum", "stock", "forex")
        setLicenses("MIT")
        desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubReleaseNotesFile = githubReadme
        version.apply {
            name = artifactVersion
            desc = pomDesc
            released = Date().toString()
            vcsTag = artifactVersion
        }
    }
}