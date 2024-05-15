kubectl create deployment leare-gateway --image=northamerica-northeast1-docker.pkg.dev/mystic-tempo-416400/leare/apigateway-leare-gateway:gcp
kubectl create deployment users-db --image=mariadb --env="MYSQL_DATABASE=my_app_development" --env="MYSQL_ROOT_PASSWORD=password"
kubectl create deployment users-web --image=northamerica-northeast1-docker.pkg.dev/mystic-tempo-416400/leare/apigateway-users-web:gcp --env="DATABASE_URL=mysql2://root:password@users-db/my_app_development"

kubectl expose deployment leare-gateway --type=LoadBalancer --port 5555