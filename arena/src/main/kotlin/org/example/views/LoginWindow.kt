package org.example.views

import com.github.unqui.UserException
import org.example.*
import org.uqbar.arena.kotlin.extensions.thisWindow
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class LoginWindow(owner: WindowOwner, model: LoginViewModel) : SimpleWindow<LoginViewModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        title = "Notes login"

        inputWithLabel("Email:", "email", mainPanel)
        passwordInputWithLabel("Password:", "password", mainPanel)
        errorLabel("errorMessage", mainPanel)
    }

    override fun addActions(actionsPanel: Panel) {
        button("Login", actionsPanel) {
            try {
                modelObject.errorMessage = ""
                val user = modelObject.login()
                close()
                UserWindow(thisWindow, UserViewModel(modelObject.noteService, user)).open()
            } catch (e: UserException) {
                modelObject.errorMessage = "Wrong user or password"
                modelObject.email = ""
                modelObject.password = ""
            }
        }
    }
}