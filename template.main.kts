#!/usr/bin/env kotlin

import java.nio.file.Paths
import kotlin.io.path.copyTo
import kotlin.io.path.div
import kotlin.io.path.name
import kotlin.io.path.readText
import kotlin.io.path.writeText


print("Day ➔ ")
val day = readln().toInt().toString().padStart(length = 2, padChar = '0')

Paths.get(".").normalize().run {
    fun String.format() = replace("__", day)
    listOf(
        this / "src" / "main" / "kotlin" / "Day__.kt",
        this / "src" / "test" / "kotlin" / "Day__Test.kt",
        this / "src" / "test" / "resources" / "Day__.txt",
        this / "src" / "test" / "resources" / "Day__-sample.txt",
    ).forEach {
        it.copyTo(it.resolveSibling(it.name.format())).apply { writeText(readText().format()) }
    }
}
