package com.example.composedevlab.core.data.http

import com.example.composedevlab.core.data.enumns.RequestType

data class AplicationBaseRequest(
    val path: String,
    val requestType: RequestType = RequestType.get,
    val queryParameters: Map<String, Any?>? = null,
    val headers: Map<String, Any?>? = null,
    val data: Any? = null,
)