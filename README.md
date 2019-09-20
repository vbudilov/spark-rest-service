### Sample Spark RESTful Service 

I've been writing a lot of RESTful services lately and decided to utilize the SparkJava framework. Since most of my apps use Cognito for authN, I created the functionality to check the JWT tokens with each request.

This github repo is a template, so you can fork it and you will have a ready-to-use RESTful service. 


In order to get Cognito working, modify the following snippet of code:

```
private val COGNITO_USER_POOL_ID: String = try {
    System.getenv("cognitoUserPoolId")
} catch (e: Exception) {
 "us-east-1_AAAAAAA"
}
```

The service is ready to be deployed as a container. You can use ECS, ECS Fargate, EKS, etc. 

I also included the code to lookup parameters in the AWS Parameter Store (AppProperties object). 

P.S. You can use the embedded ALB or API Gateway Cognito authorizers instead of using the code I created, but I needed to implement this functionality one way or another so decided to share. 

#### Running locally
```
# Let's build the image
docker build -t spark-rest-service .

# Let's run it
docker run -p 8080:8080 -it spark-rest-service

# Test it out
curl http://localhost:8080/query/health

# Check the CONTAINER_ID
docker ps

# Stop the instance (use the container id value from the 'ps' command
docker stop <CONTAINER_ID>

# Remove the previously created image
docker rmi spark-rest-service

```

#### Pushing to Amazon ECR (Image Repository)

```docker

# Setup ECR
https://docs.aws.amazon.com/AmazonECR/latest/userguide/ECR_AWSCLI.html

# Push the image to ECR 
docker tag spark-rest-service:latest <YOUR_AWS_ACCT>.dkr.ecr.<YOUR_REGION>.amazonaws.com/spark-rest-service:1.0


```
#### Running on Amazon ECS Fargate
Install the [Fargate CLI](https://github.com/awslabs/fargatecli) 

``` 

# Create the Application Load Balancer
fargate lb create mylb --port 80 

# Create the Fargate service
fargate service create myspark --region us-east-1 --port HTTP:8080 --lb mylb --image 540403165297.dkr.ecr.us-east-1.amazonaws.com/spark-rest-service:1.0

That's it!

```
#### Running on Amazon EKS (Kubernetes)

##### Setup [EKS](https://eksctl.io/)
```docker

# Create the cluster - CAUTION: this will cost you money since an EKS cluster and EC2 instances are created
eksctl create cluster --name=myeks --nodes=2 --alb-ingress-access --region=us-east-1


```

##### Setup the ALB Ingress Controller 
[Follow instructions here](https://kubernetes-sigs.github.io/aws-alb-ingress-controller/guide/controller/setup/)

##### Deploy the app
```docker
# Before you follow the next deployment steps, make sure you specify the correct url of your
# ECR image in the spark-java-deployment.yml file
# This part needs to change: 
540403165297.dkr.ecr.us-east-1.amazonaws.com/spark-rest-service:1.0


# Deploy the app
kubectl apply -f kubernetes/spark-java-namespace.yml
kubectl apply -f kubernetes/spark-java-deployment.yml
kubectl apply -f kubernetes/spark-java-service.yml
kubectl apply -f kubernetes/spark-java-ingress.yml



```
