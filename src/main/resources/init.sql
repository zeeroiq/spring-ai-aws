-- docker run --name my-postgres-container -e POSTGRES_USER=test -e POSTGRES_PASSWORD=password -p 5432:5432 -v $(pwd)/init.sql:/docker-entrypoint-initdb.d/init.sql -d postgres:15

-- CREATE EXTENSION IF NOT EXISTS vector;