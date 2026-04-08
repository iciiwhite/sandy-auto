package com.sandyauto.core

sealed class Command {
    data class Click(val x: Int, val y: Int) : Command()
    data class Type(val text: String) : Command()
    data class Swipe(val startX: Int, val startY: Int, val endX: Int, val endY: Int) : Command()
    data class Wait(val millis: Long) : Command()
}