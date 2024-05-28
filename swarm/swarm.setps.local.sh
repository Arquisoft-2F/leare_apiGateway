docker pull docker:dind
# create the nodes
# docker run --privileged --name dind-manager -d docker:dind
docker run --privileged --name dind-worker1 -d docker:dind
docker run --privileged --name dind-worker2 -d docker:dind
# start swarm on master
docker swarm init
        #si quiero que el maestro del swarm (y todo el swarm por consecuente) este en un contenedor 
        docker exec -it dind-manager docker swarm init  
# get token to join master as worker
        docker exec dind-manager docker swarm join-token worker
docker swarm join-token worker
# join from the workers  the docker swarm part is from the last command
docker exec -it dind-worker1  docker swarm join --token SWMTKN-1-2tbi0jtw6j2m1asankbv8f3zen5a2yd6gwa4u7zrfpktwc7fny-agrpikysdrzp2sk2bq4fpn3gw 192.168.65.9:2377
docker exec -it dind-worker2  docker swarm join --token SWMTKN-1-2tbi0jtw6j2m1asankbv8f3zen5a2yd6gwa4u7zrfpktwc7fny-agrpikysdrzp2sk2bq4fpn3gw 192.168.65.9:2377
# get nodes id
docker node ls
# add labels to nodes   the number is the node id that you get from the last command
docker node update --label-add rack=leare-master <node-id>
docker node update --label-add rack=leare-gateway <node-id>
docker node update --label-add rack=leare-front <node-id>
docker node update --label-add rack=leare-ms <node-id>
docker node update --label-add rack=leare-db <node-id>
docker node update --label-add rack=leare-db-replicas <node-id>
#create the network
docker network create --scope=swarm --driver overlay --attachable leare-network
# deploy
docker stack deploy -c swarm.yaml leare
        docker stack deploy -c small.yaml leare



#test
docker stack ls
docker stack ps
docker stack services leare
docker stack service
#better logging
docker service ps --no-trunc <service-name>


# how to check if joined
docker exec -it dind-manager docker node ls
#stoping stack
docker stack rm leare
#leave swarm
        docker exec -it dind-manager docker swarm leave --force
docker exec -it dind-worker1 docker swarm leave --force
docker exec -it dind-worker2 docker swarm leave --force
docker swarm leave --force
