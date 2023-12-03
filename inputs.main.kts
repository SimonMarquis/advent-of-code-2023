#!/usr/bin/env kotlin

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.absolute
import kotlin.io.path.div
import kotlin.io.path.extension
import kotlin.io.path.isRegularFile
import kotlin.io.path.name
import kotlin.io.path.notExists
import kotlin.io.path.walk
import kotlin.io.path.writeText

val sessionId = requireNotNull(System.getenv("SESSION_ID")) { "Missing SESSION_ID environment variable or command line argument!" }
println("🔣 Fetching inputs…")

val regex = """Day\d+Test\.kt""".toRegex()
@OptIn(ExperimentalPathApi::class)
(Paths.get(".").normalize() / "src" / "main" / "kotlin").walk()
    .filter { it.isRegularFile() && it.name.startsWith("Day") && it.extension == "kt" }
    .mapNotNull { it.name.filter(Char::isDigit).toIntOrNull() }
    .distinct()
    .map { it to inputPath(day = it) }
    .filter { (day, path) -> path.notExists() }
    .onEach { (day, path) -> println("Day $day ${path.absolute().toUri()}") }
    .forEach { (day, path) -> path.writeText(fetchInput(day = day)) }

fun inputPath(day: Int) = Paths.get(".").normalize() / "src" / "test" / "resources" / "Day${day.toString().padStart(length = 2, padChar = '0')}.txt"
fun fetchInput(day: Int): String = HttpRequest.newBuilder()
    .uri(URI.create("https://adventofcode.com/2023/day/$day/input"))
    .header("Cookie", "session=$sessionId")
    .build()
    .let { HttpClient.newHttpClient().send(it, HttpResponse.BodyHandlers.ofString()) }
    .also { require(it.statusCode() == 200) { "Unexpected status code: ${it.statusCode()}\n${it.body()}" } }
    .body()
