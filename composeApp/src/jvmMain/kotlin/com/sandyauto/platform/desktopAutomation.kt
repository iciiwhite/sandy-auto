package com.sandyauto.platform

import java.awt.Robot
import java.awt.event.InputEvent

class DesktopAutomation {
    private val robot = Robot()

    fun click(x: Int, y: Int) {
        robot.mouseMove(x, y)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun type(text: String) {
        text.forEach { c ->
            robot.keyPress(c.code)
            robot.keyRelease(c.code)
        }
    }
}