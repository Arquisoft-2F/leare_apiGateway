#!/bin/bash
echo "pulling data"
sudo docker compose pull
sudo docker compose -f swarm.yaml pull
echo "stack deployted"
sudo docker stack deploy -c swarm.yaml leare
echo "inter deploy"
sudo docker compose up -d leare-int int-rp
