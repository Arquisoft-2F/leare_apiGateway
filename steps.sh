gcloud compute ssh --project=mystic-tempo-416400 --zone=northamerica-northeast1-a leare-mid
mkdir leare
gcloud compute scp --project=mystic-tempo-416400 --zone=northamerica-northeast1-a  ./compose.cloud.yaml  leare-mid:/home/david/leare/docker-compose.yaml
# installar docker 
# https://docs.docker.com/engine/install/debian/
#gcloud

#sudo docker compose ps --services
gcloud init 
# here i initialized with my root user (that has admin role) and added the acount to gcloud
gcloud auth configure-docker northamerica-northeast1-a-docker.pkg.dev
