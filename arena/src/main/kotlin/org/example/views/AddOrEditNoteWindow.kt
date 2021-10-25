package org.example.views

import org.example.NoteDraftViewModel
import org.example.button
import org.example.inputWithLabel
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class AddOrEditNoteWindow(owner: WindowOwner, model: NoteDraftViewModel): Dialog<NoteDraftViewModel>(owner,  model){
    override fun createFormPanel(mainPanel: Panel) {
        title = "Note"
        inputWithLabel("Title", "title", mainPanel)
        inputWithLabel("Description", "description", mainPanel)
    }

    override fun addActions(actionsPanel: Panel) {
        button("Accept", actionsPanel) {
            modelObject.update()
            accept()
        }
        button("Cancel", actionsPanel) {
            cancel()
        }
    }

}