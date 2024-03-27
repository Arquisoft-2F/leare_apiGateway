.PHONY: all migrate-chats migrate-users

all: migrate-chats migrate-users
# Build commands
migrate-chats:
	@echo "Building the chats application..."
	start cmd /c "cd server && make"  

migrate-users:
	@echo "Building the users application..."
	start cmd /c "docker exec users_web rails db:migrate" 


docker cp ../leare_courses_ms/migrations.sql courses-db:/tmp/
docker exec -it courses-db /bin/bash    
psql -U postgres -d leare_courses_db < /tmp/migrations.sql
# docker exec -i courses-db psql -U postgres -d leare_courses_db < /tmp/migrations.sql