package org.example.views

import org.example.NoteViewModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteNoteWindow(owner: WindowOwner, model: NoteViewModel): Dialog<NoteViewModel>(owner, model) {
    override fun createFormPanel(mainPanel: Panel) {
        Label(mainPanel) with {
            text = "Seguro de eliminar la nota ${modelObject.id}"
        }

        Button(mainPanel) with {
            caption = "Aceptar"
            onClick {
                accept()
            }
        }

        Button(mainPanel) with {
            caption = "Cancelar"
            onClick {
                cancel()
            }
        }
    }
}