package com.budilov.service

import com.amazonaws.regions.Regions
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersRequest
import java.util.*

object AppProperties {

    private val parameters: Map<String, String> = emptyMap()
    private val parameterNames = arrayListOf("SecureItemOne", "SecureItemTwo")
    init {

//        parameters = loadParameters()
    }

    // Aurora Read Cluster URL with username/password
//    val dbReadUrl = parameters["SecureItemOne"] ?: throw Exception("SecureItemOne is a required field but I couldn't retrieve it")


    /**
     * Load the parameters from the parameter store.
     *
     * Note: defaults to us-east-1
     */
    private fun loadParameters(): Map<String, String> {
        val client = AWSSimpleSystemsManagementClientBuilder.standard().withRegion(Regions.US_EAST_1).build()

        val request = GetParametersRequest()
        request.withNames(parameterNames).isWithDecryption = true

        val result = client.getParameters(request)

        val store = HashMap<String, String>()

        result.parameters.forEach { parameter ->
            store[parameter.name] = parameter.value
        }

        return store
    }

    // Environment variables
    val MY_SERVICE_PORT: Int = try {
        System.getenv("MY_SERVICE_PORT").toInt()
    } catch (e: Exception) {
        throw Exception("Couldn't retrieve the service port...can't function without it")
    }


}
