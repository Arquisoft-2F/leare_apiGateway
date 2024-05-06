#!/bin/bash
echo "stack deployted"
sudo docker stack deploy -c swarm.yaml leare
echo "inter deploy"
sudo docker compose up -d leare-int int-rp
