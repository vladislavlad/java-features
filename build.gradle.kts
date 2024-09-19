plugins {
    java
    application
}

group = "software.darkmatter"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("software.darkmatter.Main")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}
