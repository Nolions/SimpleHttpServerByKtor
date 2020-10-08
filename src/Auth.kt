package tw.nolions.simplehttpserver

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import java.util.*

object Auth {
    private const val SECRET_KEY = "secret"
    private val algorithm = Algorithm.HMAC512(SECRET_KEY)
    private const val issuer = "ktor.io"
    private const val validityInMs = 1000 * 60 * 20 // 20 mines

    fun makeJwtVerifier(): JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun sign(name: String): Map<String, String> {
        return mapOf("token" to makeToken(name))
    }

    private fun makeToken(name: String): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("name", name)
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}