package tw.nolions.simplehttpserver

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Application.routing() {
    routing {
        route("/") {
            authenticate {
                get {
                    get()
                }

                post {
                    post()
                }
            }

            post("login") {
                login()
            }
        }

    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.login() {
    val user = call.receive<User>()

    call.respond(Auth.sign(user.Name))
}

private suspend fun PipelineContext<Unit, ApplicationCall>.get() {
//    val name = call.request.queryParameters.getAll("name") // query string of array
//    val name = call.request.queryParameters["name"] // query string of string

    call.respond(User(Name = "nolions", Age = 30))
}

private suspend fun PipelineContext<Unit, ApplicationCall>.post() {
//    val post = call.receiveParameters() // get from data
//    val user = call.receive<User>() // get json body

    call.respond(HttpStatusCode.NoContent)
}