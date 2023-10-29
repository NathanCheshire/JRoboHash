plugins {
    id("java")
    id("jacoco")
}

group = "com.github.natche"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation("com.google.guava:guava:32.1.3-jre")
}

tasks.withType<JacocoReport> {
    reports {
        xml.outputLocation.set(file("$buildDir/reports/jacoco/test/xml/index.xml"))
        xml.required.set(true)
        html.required.set(false)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
