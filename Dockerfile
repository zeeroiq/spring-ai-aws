# Use the official PostgreSQL image as the base image
FROM postgres:15

# Install the pgvector extension
RUN apt-get update && apt-get install -y postgresql-15-pgvector && apt-get clean