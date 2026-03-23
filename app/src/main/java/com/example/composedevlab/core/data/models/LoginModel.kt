package com.example.composedevlab.core.data.models

import org.json.JSONObject

@Suppress("UNCHECKED_CAST")
class LoginModel(
    var matricula: String?,
    var nome: String?
) {
    companion object {
        fun fromJson(json: Map<String, Any?>) = LoginModel(
            matricula = json["matricula"] as? String,
            nome = json["nome"] as? String
        )
    }

    fun toJson() = mapOf(
        "matricula" to matricula,
        "nome" to nome
    )

    fun toJsonString(): String {
        return JSONObject(this.toJson()).toString()
    }
}
