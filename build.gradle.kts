import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED

plugins {
  kotlin("jvm") version "1.9.21"
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events(PASSED, FAILED)
    exceptionFormat = FULL
    showExceptions = true
    showStackTraces = true
  }
}

kotlin {
  jvmToolchain(17)
}
