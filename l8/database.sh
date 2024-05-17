docker run --env MYSQL_DATABASE=my_app_development --env MYSQL_ROOT_PASSWORD=password -p 3307:3307 mariadb

# user_ms
  users-db:
    image: mariadb
    volumes:
      - users_data:/var/lib/mysql
    environment:
      MYSQL_DATABASE: my_app_development
      # MYSQL_ALLOW_EMPTY_PASSWORD: "true"
      MYSQL_ROOT_PASSWORD: password
    deploy:
      replicas: 1
      placement:
        constraints:
          - node.labels.rack == leare-db
    networks:
      - leare-network
