package org.example.views

import com.github.unqui.NoteDraft
import org.example.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class UserWindow(owner: WindowOwner, userViewModel: UserViewModel): SimpleWindow<UserViewModel>(owner, userViewModel)  {
    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel) with {
            text = "Add new Note"
            onClick {
                var draft = NoteDraft(modelObject.selectedNote!!.title, modelObject.selectedNote!!.shortDescription)
                var draftModel = NoteDraftViewModel(draft)
                val view = AddOrEditNoteWindow(this@UserWindow, draftModel)
                view.onAccept {
                    modelObject.addNote(draft)
                }
                view.open()
            }
        }

        Button(actionsPanel) with {
            text = "Edit Note"
            onClick {
                try {
                    var draft = NoteDraft(modelObject.selectedNote!!.title, modelObject.selectedNote!!.shortDescription)
                    var draftModel = NoteDraftViewModel(draft)
                    val view = AddOrEditNoteWindow(this@UserWindow, draftModel)
                    view.onAccept {
                        modelObject.editNote(draft)
                    }
                    view.open()
                } catch( e: NullPointerException) {
                    throw UserException("El usuario o la nota no existen")
                }
            }
        }

        Button(actionsPanel) with {
            text = "Delete Note"
            onClick {
                val view = DeleteNoteWindow(this@UserWindow, modelObject.selectedNote!!)
                view.onAccept {
                    modelObject.delete()
                }
                view.open()
            }
            println("TODO + ventana de confirmación")
        }
    }

    override fun createFormPanel(mainPanel: Panel) {
        createUserForm(mainPanel)
        separatorLine(mainPanel)
        createNotesForm(mainPanel)
    }

    private fun createUserForm(mainPanel: Panel) {
        inputDisabledWithLabel("Id:", "id", mainPanel)
        inputDisabledWithLabel("Email:", "email", mainPanel)
        inputDisabledWithLabel("Display name:", "displayName", mainPanel)
    }

    private fun createNotesForm(mainPanel: Panel) {
        title = "Notes - User"

        table<NoteViewModel>(mainPanel) {
            bindItemsTo("notes")
            bindSelectionTo("selectedNote")
            visibleRows = 5
            column {
                title = "ID"
                fixedSize = 60
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 120
                bindContentsTo("title")
            }
            column {
                title = "Description"
                fixedSize = 200
                bindContentsTo("shortDescription")
            }
        }
    }
}