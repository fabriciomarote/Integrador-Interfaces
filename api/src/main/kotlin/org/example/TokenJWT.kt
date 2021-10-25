package org.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.unqui.User
import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider

class NotFoundToken: Exception()

class UserGenerator : JWTGenerator<User> {
    override fun generate(user: User, algorithm: Algorithm): String {
        val token = JWT.create().withClaim("id", user.id)
        return token.sign(algorithm)
    }
}

class TokenJWT {

    private val algorithm = Algorithm.HMAC256("very_secret")
    private val generator = UserGenerator()
    private val verifier = JWT.require(algorithm).build()
    private val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(user: User): String {
        return provider.generateToken(user)
    }

    fun validate(token: String?): String {
        val token = provider.validateToken(token)
        if (!token.isPresent) throw NotFoundToken()
        return token.get().getClaim("id").asString()
    }

}