plugins {
    id("java")
}

group = "fr.uge.ebc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.0")
    implementation(project(":webservice_client"))
    implementation(project(":bankservice_client"))

    implementation("org.apache.axis:axis:1.4")
    implementation("javax.xml.rpc:javax.xml.rpc-api:1.1.1")
    implementation("javax.xml.soap:saaj-api:1.3.5")
    implementation("commons-discovery:commons-discovery:0.2")
    implementation("commons-logging:commons-logging:1.1.1")
    implementation("wsdl4j:wsdl4j:1.6.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}