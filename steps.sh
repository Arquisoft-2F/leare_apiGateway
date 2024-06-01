gcloud compute ssh --project=mystic-tempo-416400 --zone=northamerica-northeast1-a leare-large
mkdir leare
gcloud compute scp --project=mystic-tempo-416400 --zone=northamerica-northeast1-a ./compose.cloud.yaml  leare-large:/home/david/leare/docker-compose.yaml
gcloud compute scp --project=mystic-tempo-416400 --zone=northamerica-northeast1-a ./swarm/swarm.yaml  leare-large:/home/david/leare/swarm.yaml
# installar docker 
# https://docs.docker.com/engine/install/debian/
#gcloud

#sudo docker compose ps --services
gcloud init 
# here i initialized with my root user (that has admin role) and added the acount to gcloud
gcloud auth configure-docker northamerica-northeast1-a-docker.pkg.dev

podman push northamerica-northeast1-docker.pkg.dev/mystic-tempo-416400/leare/apigateway-leare-gateway:gcp northamerica-northeast1-docker.pkg.dev/mystic-tempo-416400/leare


gcloud auth print-access-token | podman login -u oauth2accesstoken --password-stdin us.gcr.io


gcloud auth configure-docker
gcloud auth configure-docker northamerica-northeast1-docker.pkg.dev


sudo docker cp document-db:/home/dynamodblocal/shared-local-instance.db ./
chmod -R 777 ./data