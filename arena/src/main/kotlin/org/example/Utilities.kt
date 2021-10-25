package org.example

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import java.awt.Color

fun inputWithLabel(label: String, property: String, mainPanel: Panel) {
    Panel(mainPanel) with {
        asHorizontal()
        Label(it) with {
            text = label
            width = 60
        }
        TextBox(it) with {
            bindTo(property)
            width = 250
        }
    }
}

fun inputDisabledWithLabel(label: String, property: String, mainPanel: Panel) {
    Panel(mainPanel) with {
        asHorizontal()
        Label(it) with {
            text = label
            width = 60
        }
        Label(it) with {
            bindTo(property)
            alignLeft()
            width = 250
            bgColor = Color.LIGHT_GRAY
            color = Color.WHITE
        }
    }
}

fun passwordInputWithLabel(label: String, property: String, mainPanel: Panel) {
    Panel(mainPanel) with {
        asHorizontal()
        Label(it) with {
            text = label
            width = 60
        }
        PasswordField(it) with {
            bindTo(property)
            width = 250
        }
    }
}

fun errorLabel(property: String, mainPanel: Panel) {
    Label(mainPanel) with {
        bindTo(property)
        color = Color.RED
    }
}

fun separatorLine(mainPanel: Panel) {
    Label(mainPanel) withText "------------------------------------------------------------"
}

fun button(text: String, mainPanel: Panel, action: () -> Unit) {
    Button(mainPanel) with {
        caption = text
        onClick { action() }
    }
}