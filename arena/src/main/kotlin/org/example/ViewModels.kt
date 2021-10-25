package org.example

import com.github.unqui.Note
import com.github.unqui.NoteDraft
import com.github.unqui.NoteService
import com.github.unqui.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginViewModel(val noteService: NoteService) {

    var password = ""
    var email = ""

    var errorMessage = ""

    fun login(): User = noteService.login(email, password)

}

@Observable
class UserViewModel(private val noteService: NoteService, private val user: User) {
    val id = user.id
    val email = user.email
    val displayName = user.displayName

    private fun mapNotes() = user.notes.map { NoteViewModel(it) }

    var notes = mapNotes()
    var selectedNote: NoteViewModel? = null

    fun addNote(draft: NoteDraft) {
        noteService.addNote(user.id, draft)
        notes = mapNotes()
    }

    fun editNote(draft: NoteDraft) {
        noteService.editNote(user.id, selectedNote!!.note.id, draft)
        notes = mapNotes()
    }

    fun delete() {
        noteService.removeNote(user.id, selectedNote!!.id)
        notes = mapNotes()
    }
}

@Observable
class NoteViewModel(val note: Note) {
    val id = note.id
    var title = note.title
    val shortDescription = "${note.description.take(30)}${if (note.description.length > 30) "..." else ""}"
}

@Observable
class NoteDraftViewModel(val noteDraft: NoteDraft) {
    var title = noteDraft.title
    var description = noteDraft.description

    fun update() {
        noteDraft.title = title
        noteDraft.description = description
    }
}