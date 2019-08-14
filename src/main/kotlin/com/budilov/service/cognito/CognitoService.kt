package com.budilov.service.cognito


import com.amazonaws.auth.AnonymousAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient
import com.auth0.jwk.GuavaCachedJwkProvider
import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.slf4j.LoggerFactory
import java.net.URL
import java.security.interfaces.RSAKey


/**
 * Created by Vladimir Budilov
 */

object CognitoService {

    private val identityClient: AmazonCognitoIdentity? = AmazonCognitoIdentityClient(AnonymousAWSCredentials())
            .withRegion<AmazonCognitoIdentityClient>(Region.getRegion(Properties.REGION_ENUM))
    private val logger = LoggerFactory.getLogger("CognitoService")

    /**
     * Requires:
     * https://github.com/auth0/jwks-rsa-java
     *
     * Another option is to use this one:
     * https://github.com/jwtk/jjwt
     *
     */
    fun isTokenValid(token: String): Boolean {

        // Decode the key and set the kid
        val decodedJwtToken = JWT.decode(token)
        val kid = decodedJwtToken.keyId

        val http = UrlJwkProvider(URL(Properties.JWK_URL))
        // Let's cache the result from Cognito for the default of 10 hours
        val provider = GuavaCachedJwkProvider(http)
        val jwk = provider.get(kid)

        val algorithm = Algorithm.RSA256(jwk.publicKey as RSAKey)
        val verifier = JWT.require(algorithm)
                .withIssuer(Properties.JWT_TOKEN_ISSUER)
                .build() //Reusable verifier instance
        val jwt = try {
            verifier.verify(token)
            true
        } catch (e: Exception) {
            false
        }

        logger.debug("Returning $jwt")
        return jwt
    }

    /**
     * Retrieve the cognito id from the id token
     *
     * The result should be cached so as not to call the cognito service for every single request (although I'm not
     * caching it anywhere right now)
     */
    fun getCognitoSubId(idToken: String?): String {
        val cognitoId = JWT.decode(idToken).subject

        logger.debug("cognitoId: $cognitoId")
        return cognitoId
    }
}

