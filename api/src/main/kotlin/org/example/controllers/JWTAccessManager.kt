package org.example.controllers

import com.github.unqui.NoteService
import com.github.unqui.User
import com.github.unqui.UserException
import io.javalin.core.security.AccessManager
import io.javalin.core.security.RouteRole
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.example.NotFoundToken
import org.example.Role
import org.example.TokenJWT

class JWTAccessManager(private val tokenJWT: TokenJWT, private val noteService: NoteService): AccessManager {

    private fun getUser(token: String): User {
        try {
            val userId = tokenJWT.validate(token)
            return noteService.getUser(userId)
        } catch (e: NotFoundToken) {
            throw UnauthorizedResponse("Token not found")
        } catch (e: UserException) {
            throw UnauthorizedResponse("Invalid Token")
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<RouteRole>) {
        val token = ctx.header("Authorization")
        when {
            token == null && roles.contains(Role.ANYONE) -> handler.handle(ctx)
            token == null -> throw UnauthorizedResponse("Token not found")
            roles.contains(Role.ANYONE) -> handler.handle(ctx)
            roles.contains(Role.USER) -> {
                val user = getUser(token)
                ctx.sessionAttribute("user", user)
                handler.handle(ctx)
            }
        }
    }
}