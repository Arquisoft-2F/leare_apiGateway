.PHONY: all migrate-chats migrate-users

all: migrate-chats migrate-users
# Build commands
migrate-chats:
	
not-used:
	gcloud compute scp --project=mystic-tempo-416400 --zone=northamerica-northeast1-a  ../leare_courses_ms/migrations.sql  leare-large:/home/david/leare/migrations.sql
	docker cp ../leare_courses_ms/migrations.sql real-courses-db:/tmp/
		docker cp ./migrations.sql real-courses-db:/tmp/
	docker exec -it real-courses-db /bin/bash
	# inside of that bash terminal i just created
		su - postgres
		createdb -p 5490 leare_courses_db
		exit
		psql -U postgres -p 5490 -d leare_courses_db < /tmp/migrations.sql
		exit

migrate-users:
	@echo "Building the users application..."
	start cmd /c "docker exec users-web rails db:migrate" 



# docker cp ../leare_courses_ms/migrations.sql courses-db:/tmp/
# docker exec -it courses-db /bin/bash    
# psql -U postgres -d leare_courses_db < /tmp/migrations.sql
# # docker exec -i real-courses-db psql -U postgres -d leare_courses_db < /tmp/migrations.sql