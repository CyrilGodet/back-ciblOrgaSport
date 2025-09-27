docker run --rm -d -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=glop -e POSTGRES_HOST_AUTH_METHOD=trust -v /tmp/postgresqlvolume:/var/lib/postgresql/data --name postgresql_database postgres:16.10-bookworm


docker exec -it postgresql_database psql -U admin -d glop


mvn clean package spring-boot:run
