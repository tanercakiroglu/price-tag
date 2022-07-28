# price-tag
#Consist of 3 modules 
#Config: It contains all aspect,cache,and spring configuration needed.
#Model: It is not desired shared model in microservices but due to time limitation , it is seperated
#Producer: It is designed as a producer for microservice arch.It takes xml input send activemq
#Consumer: It is designed as a consumer for microservice arch.It reads xml from activemq and put
#data to the redis.


# Step-0:https://activemq.apache.org/components/classic/download/ download and run locally
# Step-1:run docker run --name redis-test -p 6379:6379  -d redis
# Step-2:run application 
# Step-3:import Price-Tag.postman_collection to postman  

