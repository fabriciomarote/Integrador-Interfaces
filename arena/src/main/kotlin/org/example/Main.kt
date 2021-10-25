package org.example

import com.github.unqui.getNoteService
import org.example.views.LoginWindow
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window


class NotesApplication: Application() {
    override fun createMainWindow(): Window<*> {
        val noteService = getNoteService()
        return LoginWindow(this, LoginViewModel(noteService))
    }
}


fun main(args: Array<String>) {
    NotesApplication().start()
}