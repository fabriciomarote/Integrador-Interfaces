package org.example.controllers

import com.github.unqui.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.example.*

class UserController(private val noteService: NoteService, private val tokenJWT: TokenJWT) {

    fun login(ctx: Context) {
        val loginModel = ctx.bodyValidator<LoginBody>()
            .check({ it.email.isNotEmpty() }, "Email cannot be empty")
            .check({
                "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)$"
                    .toRegex()
                    .matches(it.email)
            }, "Invalid email address")
            .check({ it.password.trim().isNotEmpty() }, "Password cannot be empty")
            .get()
        try {
            val user = noteService.login(loginModel.email, loginModel.password)
            val token = tokenJWT.generateToken(user)
            ctx.header("Authorization", token)
            ctx.status(200).json(OkResponse())
        } catch (e: UserException) {
            throw BadRequestResponse(e.message!!)
        }
    }

    fun register(ctx: Context) {
        val registerModel = ctx.bodyValidator<RegisterBody>()
            .check({ it.name.isNotEmpty()}, "Name cannot be empty")
            .check({ it.email.isNotEmpty() }, "Email cannot be empty")
            .check({
                "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)$"
                    .toRegex()
                    .matches(it.email)
            }, "Invalid email address")
            .check({ it.password.isNotEmpty()}, "Password cannot be empty")
            .check({ it.image.isNotEmpty()}, "Image cannot be empty")
            .get()
        try {
            noteService.register(UserDraft( registerModel.name, registerModel.email, registerModel.password, registerModel.image))
            ctx.status(201).json(OkResponse())
        } catch (e: UserException) {
            ctx.status(404).json(ErrorResponse("The e-mail is not available"))
        }
    }

    fun getUser(ctx: Context) {
        val token = ctx.header("Authorization")
        val userId = tokenJWT.validate(token!!)
        val user: User
        try {
            user = noteService.getUser(userId)
            val userBody = UserBody(user.id, user.email, user.image, user.password, user.displayName, Transform.notesToSimpleNotes(user.notes))
            ctx.status(200).json(userBody)
        } catch (e: UserException) {
            ctx.status(401).json({})
        }
    }

    fun addNote(ctx: Context) {
        val token = ctx.header("Authorization")
        val userId = tokenJWT.validate(token!!)
        val draft = ctx.bodyValidator<NoteDraft>()
            .check({ it.title.isNotEmpty()}, "Title cannot be empty")
            .check({ it.description.isNotEmpty()}, "Description cannot be empty")
            .get()
        try {
            noteService.addNote(userId, draft)
            ctx.status(200).json(draft)
        } catch (e: UserException) {
            ctx.status(401).json("Not cant add note because ...")
        }
    }

    fun editNote(ctx: Context) {
        val token = ctx.header("Authorization")
        val userId = tokenJWT.validate(token!!)
        val noteId = ctx.pathParam("{id}")
        val draft = ctx.bodyValidator<NoteDraft>()
            .check({ it.title.isNotEmpty()}, "Title cannot be empty")
            .check({ it.description.isNotEmpty()}, "Description cannot be empty")
            .get()
        val note = noteService.getNote(userId, noteId)
        try {
               noteService.editNote(userId, noteId, draft)
            val noteEdited = NoteBody(note.id, draft.title, draft.description, Transform.dateToFormattedDate(note.lastModifiedDate))
            ctx.status(200).json(noteEdited)
        } catch (e: UserException) {
            ctx.status(401).json({})
        }
    }

    fun deleteNote(ctx: Context) {
        val token = ctx.header("Authorization")
        val userId = tokenJWT.validate(token!!)
        val noteId = ctx.pathParam("{id}")
        val user = noteService.getUser(userId)
        try {
            noteService.removeNote(userId, noteId)
            val userBody = UserBody(user.id, user.email, user.image, user.password, user.displayName, Transform.notesToSimpleNotes(user.notes))
            ctx.status(200).json(userBody)
        } catch (e: UserException) {
            ctx.status(401).json({})
        }
    }

    private fun getUserId(ctx: Context) : String {
        val id : String? = ctx.attribute("id")
        if (id == null) {
            UnauthorizedResponse()
        }
        return id!!
    }
}
