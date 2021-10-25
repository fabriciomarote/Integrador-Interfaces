package org.example

import com.github.unqui.Note

data class LoginBody(val email: String, val password: String)

data class ErrorResponse(val result: String)

data class SimpleNote(val id: String, val title: String, val description: String)

data class RegisterBody(val name: String, val email: String, val password: String, val image: String)

data class UserBody(val id: String, val email: String, val image: String, val password: String, val displayName: String, val notes: List<SimpleNote>)

data class OkResponse(val message: String = "Ok")

class Transform() {
    companion object transformer {

        fun noteToSimpleNote(note: Note): SimpleNote {
            return SimpleNote(note.id, note.title, note.description)
        }
        fun notesToSimpleNotes(notes: MutableList<Note>): List<SimpleNote>{
            val notesTransformed = notes.map {
                SimpleNote(it.id, it.title, it.description)
            }
            return notesTransformed
        }
    }
}