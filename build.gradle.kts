import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "3.5.8"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.openapi.generator") version "7.12.0"
    //id("org.springdoc") version "2.5.0"
}

group = "com.udemy"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}
val springdocVersion = "2.2.0"
val querydlsVersion="5.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.querydsl:querydsl-core:$querydlsVersion")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/src/main/resources/static/open-api.yaml")
    outputDir.set(layout.buildDirectory.dir("generated").get().asFile.absolutePath)
    configFile.set("$rootDir/src/main/resources/api-config.json")
    apiPackage.set("com.udemy.openapidemo.apis")
    modelPackage.set("com.udemy.openapidemo.models")
    configOptions.set(
        mapOf(
            "useJakartaEE" to "true",
            "useSpringBoot3" to "true",
            "interfaceOnly" to "true",
            "useRuntimeException" to "true"
        )
    )
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir(layout.buildDirectory.dir("generated/src/main/kotlin").get().asFile)
        java.exclude("**/Application.kt")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xjsr305=strict"))
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}


