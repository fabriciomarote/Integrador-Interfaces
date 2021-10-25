package org.example

import com.github.unqui.Note
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class LoginBody(val email: String, val password: String)

data class ErrorResponse(val result: String)

data class NoteBody(val id: String, val title: String, val description: String, val lastModifiedDate: String)

data class RegisterBody(val name: String, val email: String, val password: String, val image: String)

data class UserBody(val id: String, val email: String, val image: String, val password: String, val displayName: String, val notes: List<NoteBody>)

data class OkResponse(val message: String = "Ok")

class Transform() {
    companion object transformer {

        fun dateToFormattedDate(date: LocalDateTime): String {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")
            val formatDateTime = LocalDateTime.parse(date.toString())
            return formatDateTime.format(formatter).toString()
        }

        fun noteToNoteBody(note: Note): NoteBody {
            return NoteBody(note.id, note.title, note.description, dateToFormattedDate(note.lastModifiedDate))
        }
        fun notesToSimpleNotes(notes: MutableList<Note>): List<NoteBody>{
            val notesTransformed = notes.map {
                NoteBody(it.id, it.title, it.description, dateToFormattedDate(it.lastModifiedDate))
            }
            return notesTransformed
        }
    }
}