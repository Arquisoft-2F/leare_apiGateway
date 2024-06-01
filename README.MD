# LEARE

This repository contains a proyect created for the "Software architecture" course on college. It consists on a API Gateway created with Java Spring boot and Graphql for a microservices-based project. It was deployed on Google Cloud Platform (GCP) using Docker Swarm. This repository contains the API Gateway, some proxys used and all of the configuration files used for deployment and database replication (master-master and master-slave). All of the (custom) images referenced are no longer available 

# Technical Stack:
- Programming Language: Java with Spring Boot
- Databases: Multiple databases, notably. PostgreSQL, MariaDb, DynamoDb 
- API Communication: GraphQL
- Orquestation: Docker swarm
- Proxy and Caching: Nginx
- Testing: Jmeter
- Monitoring: Prometheus and Graphana
- Logging: Loki 

# Cloud infrastructure
## GCP
- Compute Engine servers
- Artifact registry Images storage
## Aws
- S3 for documents and static hosting
- DynamoDb 
# Contact Information:
David Alfonso Cañas | Backend Software Developer inquiries: david.alfonso.canas@gmail.com