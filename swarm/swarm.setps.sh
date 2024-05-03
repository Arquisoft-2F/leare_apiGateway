# start swarm on master
sudo docker swarm init
# get token to join master as worker
sudo docker swarm join-token worker
# join from the workers  the docker swarm part is from the last command
sudo docker swarm join --token SWMTKN-1-2tbi0jtw6j2m1asankbv8f3zen5a2yd6gwa4u7zrfpktwc7fny-agrpikysdrzp2sk2bq4fpn3gw 192.168.65.9:2377
sudo docker swarm join --token SWMTKN-1-2tbi0jtw6j2m1asankbv8f3zen5a2yd6gwa4u7zrfpktwc7fny-agrpikysdrzp2sk2bq4fpn3gw 192.168.65.9:2377
# get nodes id
sudo docker node ls
# add labels to nodes   the number is the node id that you get from the last command
sudo docker node update --label-add rack=leare-gateway <node-id>
sudo docker node update --label-add rack=leare-ms <node-id>
sudo docker node update --label-add rack=leare-db <node-id>
#create the network
sudo docker network create --scope=swarm --driver overlay leare-network
# deploy
sudo docker stack deploy -c swarm.yaml leare



#test
docker stack ls
docker stack ps
docker stack services leare
docker stack service
#better logging
docker service ps --no-trunc <service-name>

#stoping stack
sudodocker stack rm leare
#leave swarm
sudodocker swarm leave --force
sudodocker swarm leave --force
sudodocker swarm leave --force
