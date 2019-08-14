package com.budilov.service.spark

data class ApiResponse(val statusCode: Int,
                       val headers: MutableMap<String, String>? = null,
                       val body: String)
