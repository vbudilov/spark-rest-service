Sample Spark RESTful Service 
----------

I've been writing a lot of RESTful services lately and decided to utilize the SparkJava framework. Since most of my apps use Cognito for authN, I created the functionality to check the JWT tokens with each request.

The github repo is a template, so you can fork it and you will have a ready-to-use RESTful service. 


Here's what you need to modify to include your own Cognito pool id:

```
private val COGNITO_USER_POOL_ID: String = try {
    System.getenv("cognitoUserPoolId")
} catch (e: Exception) {
 "us-east-1_AAAAAAA"
}
```

The service is ready to be deployed as a container. Personally I use AWS Fargate, but you can use ECS, EKS, etc. 

I also included the code to lookup parameters in the AWS Parameter Store (AppProperties object). 

If you like it, please star it :) 

P.S. You can definitely use the embedded ALB or API Gateway Cognito authorizers instead of using the code I created, but I needed to implement this functionality one way or another so decided to share. 
