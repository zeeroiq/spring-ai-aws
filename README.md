It has utilized postgres running on docker container.

## command to create/run/connect to postgres
docker run --name my-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
## access container's shell
docker exec -it postgres psql -U test -d test_db

if test_db isn't created, create using below command 

### create test_db for app
CREATE DATABASE test_db
WITH OWNER = test
ENCODING = 'UTF8'
TEMPLATE = template0
LC_COLLATE = 'en_US.UTF-8'
LC_CTYPE = 'en_US.UTF-8'
CONNECTION LIMIT = -1;