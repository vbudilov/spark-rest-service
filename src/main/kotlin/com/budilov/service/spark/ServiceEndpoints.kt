package com.budilov.service.spark

import com.budilov.service.cognito.CognitoService
import com.google.gson.Gson
import org.slf4j.LoggerFactory
import spark.Request
import spark.Spark.*
import java.util.*


private val logger = LoggerFactory.getLogger("ServiceEndpoint")

fun main(args: Array<String>) {

    port(80)
    val maxThreads = 150
    val minThreads = 50
    val timeOutMillis = 10000

    val authHeaderValue = "Authorization"

    threadPool(maxThreads, minThreads, timeOutMillis)

    val gson = Gson()

    path("/query") {

        path("/health") {
            get("") { req, res ->
                logger.debug("I'm alive")
                val connected = true // check for service health here

                if (connected) {
                    res.status(200)
                    "alive"
                } else {
                    logger.error("Connection died")
                    res.status(500)
                    "dead"
                }
            }
        }

        path("/myendpoint")
        {
            SparkFilter.filter("*", "POST,OPTIONS,PUT,GET", "Origin,Content-Type,X-Amz-Date,search-key,Authorization,X-Api-Key,X-Amz-Security-Token")

            get("/one") { req, res ->

                // Query Params
                val userId = CognitoService.getCognitoSubId(req.headers(authHeaderValue))
                val limit = ServiceEndpoints.getLimit(req) //category type from my inventory

                // Response
                val responseMap = HashMap<String, Any?>()

                gson.toJson(responseMap)

            }
            get("/one/two") { req, res ->

                // Query Params
                val userId = CognitoService.getCognitoSubId(req.headers(authHeaderValue))
                val limit = ServiceEndpoints.getLimit(req) //category type from my inventory

                // Response
                val responseMap = HashMap<String, Any?>()

                responseMap["key"] = "value"

                gson.toJson(responseMap)

            }

        }

    }


}

object ServiceEndpoints {
    fun getLimit(req: Request): Int {

        var limit = 1000

        try {
            limit = req.queryParams("limit").toInt()
        } catch (exc: Exception) {

        }

        return limit
    }
}

