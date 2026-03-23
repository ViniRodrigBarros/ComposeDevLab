package com.example.composedevlab.core.data.models

data class ResponseModel(
    val success: Boolean,
    val statusCode: Int,
    val data: Any?
)

