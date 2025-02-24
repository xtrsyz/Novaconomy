import io.github.patrick.gradle.remapper.RemapTask

plugins {
    id("io.github.patrick.remapper") version "1.4.0"
}

val mcVersion = "1.19.3"

dependencies {
    api(project(":novaconomy-abstract"))
    api(project(":novaconomy-api"))

    compileOnly("org.spigotmc:spigot:$mcVersion-R0.1-SNAPSHOT:remapped-mojang")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    assemble {
        dependsOn("remap")
    }

    remap {
        dependsOn("shadowJar")

        version.set(mcVersion)
        action.set(RemapTask.Action.MOJANG_TO_SPIGOT)
        archiveName.set("${project.name}-${project.version}.jar")
    }
}