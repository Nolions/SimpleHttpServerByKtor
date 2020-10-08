package tw.nolions.simplehttpserver

import kotlinx.serialization.*

@Serializable
class User(
    val Name: String,
    val Age: Int
)
