package com.budilov.service.spark

import com.budilov.service.cognito.CognitoService
import org.slf4j.LoggerFactory
import spark.Spark.*


/**
 * Created by Vladimir Budilov
 */

object SparkFilter {
    private val logger = LoggerFactory.getLogger("SparkFilter")
    fun filter(origin: String, methods: String, headers: String) {
        logger.debug("Enabling SparkFilter")

        options("/*") { request, response ->
            logger.debug("In the options method")
            val accessControlRequestHeaders = request.headers("Access-Control-Request-Headers")
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders)
            }

            val accessControlRequestMethod = request.headers("Access-Control-Request-Method")
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod)
            }

            "OK"
        }

        before("/*") { req, res ->
            logger.debug("In the before() route. Request method: ${req.requestMethod()}")
            if (req.requestMethod() != "OPTIONS") {
                // Get the auth token (cognito)
                val authToken = req.headers("Authorization")
                logger.debug("authToken = $authToken")

                // Check if it's valid...if not then fail
                if (authToken == null || !CognitoService.isTokenValid(authToken)) {
                    logger.error("The token isn't valid")
                    halt(403, "You're not authenticated")
                }
            }
            res.header("Access-Control-Allow-Origin", origin)
            res.header("Access-Control-Request-Method", methods)
            res.header("Access-Control-Allow-Headers", headers)
            res.type("application/json")

        }

    }
}

