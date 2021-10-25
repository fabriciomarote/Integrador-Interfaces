package org.example

import com.github.unqui.getNoteService
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.RouteRole
import io.javalin.core.validation.ValidationException
import org.example.controllers.JWTAccessManager
import org.example.controllers.UserController
import java.time.LocalDateTime

enum class Role : RouteRole { ANYONE, USER }

class NoteApi {

    fun start() {
        val noteService = getNoteService()

        val tokenJWT = TokenJWT()
        val userController = UserController(noteService, tokenJWT)

        val jwtAccessManager = JWTAccessManager(tokenJWT, noteService)

        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.accessManager(jwtAccessManager)
            it.enableCorsForAllOrigins()
        }.start(7000)

        app.before {
            it.header("Access-Control-Expose-Headers", "*")
        }

        app.routes {
            path("login") {
                post(userController::login, Role.ANYONE)
            }
            path("register") {
                post(userController::register, Role.ANYONE)
            }
            path("user") {
                get(userController::getUser, Role.USER)
                path("note") {
                    post(userController::addNote, Role.USER)
                    path("{id}") {
                        put(userController::editNote, Role.USER)
                        delete(userController::deleteNote, Role.USER)
                    }
                }
            }
        }

        app.exception(ValidationException::class.java) { e, ctx ->
            ctx.json(e.errors).status(400)
        }
    }
}

fun main(args: Array<String>) = NoteApi().start()