package com.example.composedevlab.core.data.http

import com.example.composedevlab.core.data.enumns.RequestType
import com.example.composedevlab.core.data.models.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.URLEncoder

open class HttpClient {

    open var baseUrl: String = "https://sua-api-aqui" // TODO: atualizar base URL

    open var headers: Map<String, String> = mapOf(
        "Content-Type" to "application/json",
        "Accept" to "application/json"
    )

    private val client = OkHttpClient()

    open suspend fun execute(request: AplicationBaseRequest): ResponseModel = withContext(Dispatchers.IO) {
        // Monta URL: baseUrl + path + queryParameters
        val urlBuilder = StringBuilder(request.path)
        request.queryParameters?.let { params ->
            if (params.isNotEmpty()) {
                urlBuilder.append(if (urlBuilder.contains("?")) "&" else "?")
                urlBuilder.append(
                    params.entries.joinToString("&") { (k, v) ->
                        "${URLEncoder.encode(k, "UTF-8")}=${URLEncoder.encode(v?.toString() ?: "", "UTF-8")}"
                    }
                )
            }
        }

        val mediaType = "application/json; charset=utf-8".toMediaType()

        // Monta body
        val bodyString: String? = when (val d = request.data) {
            null -> null
            is String -> d
            is Map<*, *> -> {
                val obj = JSONObject()
                for ((key, value) in d.entries) {
                    val k = key as? String ?: continue
                    obj.put(k, value)
                }
                obj.toString()
            }
            is JSONObject -> d.toString()
            else -> d.toString()
        }

        // Monta request
        val builder = Request.Builder().url(urlBuilder.toString())

        // Headers padrão + headers da requisição
        headers.forEach { (k, v) -> builder.addHeader(k, v) }
        request.headers?.forEach { (k, v) -> builder.addHeader(k, v?.toString() ?: "") }

        // Método HTTP
        val body = bodyString?.toRequestBody(mediaType)
        when (request.requestType) {
            RequestType.get    -> builder.get()
            RequestType.post   -> builder.post(body ?: "".toRequestBody(mediaType))
            RequestType.put    -> builder.put(body ?: "".toRequestBody(mediaType))
            RequestType.delete -> if (body != null) builder.delete(body) else builder.delete()
            RequestType.patch  -> builder.patch(body ?: "".toRequestBody(mediaType))
        }

        try {
            client.newCall(builder.build()).execute().use { resp ->
                val respBytes = resp.body.bytes()
                return@withContext ResponseModel(
                    success = resp.isSuccessful,
                    statusCode = resp.code,
                    data = respBytes
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext ResponseModel(success = false, statusCode = 0, data = null)
        }
    }
}