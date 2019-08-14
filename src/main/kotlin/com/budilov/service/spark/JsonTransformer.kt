package com.budilov.service.spark

import com.google.gson.Gson
import spark.ResponseTransformer

class JsonTransformer : ResponseTransformer {

    private val gson = Gson()

    override
    fun render(model: Any): String {
        val map = mutableMapOf<String, Any?>()

        map["items"] = model
        return gson.toJson(map)
    }

}
