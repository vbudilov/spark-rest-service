package com.budilov.service.cognito

import com.amazonaws.regions.Regions


/**
 * Created by Vladimir Budilov
 *
 * Externalizing of app properties. Will be handy when writing unit tests and de-coupling
 * the storage of properties
 */

object Properties {

    // Environment variables
    val REGION_NAME: String = try {
        System.getenv("regionName")
    } catch (e: Exception) {
        "us-east-1"
    }

    val REGION_ENUM: Regions = Regions.fromName(REGION_NAME)

    private val COGNITO_USER_POOL_ID: String = try {
        System.getenv("cognitoUserPoolId")
    } catch (e: Exception) {
        "us-east-1_Blqt2tzfe"
    }

    val JWK_URL = "https://cognito-idp.${REGION_NAME}.amazonaws.com/$COGNITO_USER_POOL_ID/.well-known/jwks.json"
    val JWT_TOKEN_ISSUER = "https://cognito-idp.${REGION_NAME}.amazonaws.com/$COGNITO_USER_POOL_ID"
}
