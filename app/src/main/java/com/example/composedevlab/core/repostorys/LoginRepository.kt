package com.example.composedevlab.core.repostorys

import com.example.composedevlab.core.data.http.AplicationBaseRequest
import com.example.composedevlab.core.data.http.HttpClient
import com.example.composedevlab.core.data.enumns.RequestType
import com.example.composedevlab.core.data.models.LoginModel
import org.json.JSONObject

@Suppress("UNCHECKED_CAST")
class LoginRepository : HttpClient() {

    suspend fun getLogin(cpf: String, senha: String): LoginModel? {
        var loginModel: LoginModel? = null

        val request = AplicationBaseRequest(
            path = "/login", // TODO: atualizar endpoint
            requestType = RequestType.post,
            data = mapOf("cpf" to cpf, "senha" to senha)
        )

        val response = execute(request)
        if (response.success) {
            val json = JSONObject(String(response.data as ByteArray))
            loginModel = LoginModel.fromJson(
                json.keys().asSequence().associateWith { k -> json.opt(k) }
            )
        }

        return loginModel
    }
}
