plugins {
    id("java")
    id("jacoco")
}

group = "com.github.natche"
version = "1.0"

repositories {
    mavenCentral()
}

val junitVersion by extra { "5.9.2" }
val guavaVersion by extra { "31.1-jre" }

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("com.google.guava:guava:$guavaVersion")
}

tasks.withType<JacocoReport> {
    reports {
        xml.outputLocation.set(file("$buildDir/reports/jacoco/test/xml"))
        xml.required.set(true)
        html.required.set(false)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
