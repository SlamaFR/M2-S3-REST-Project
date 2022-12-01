plugins {
    java
    war
}

group = "fr.uge.ebc"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}


configure(listOf(":users-server", ":bikes-server", ":webservice", ":webserver").map(::project)) {
    dependencies {
        implementation(project(":common"))
    }
}
