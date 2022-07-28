# price-tag
#Consist of 3 modules 
#Config: It contains all aspect,cache,and spring configuration needed.
#Model: It is not desired shared model in microservices but due to time limitation , it is seperated
#Producer: It is designed as a producer model but embedded activemq configuration couldn't be done 
#for microservice arch.Therefore sending and receiving implemented in single project.


# Step-1:run docker run --name redis-test -p 6379:6379  -d redis
# Step-2:run application 
# Step-3:import Price-Tag.postman_collection to postman  

