FROM mysql:8.4
# Seed database at first start
COPY world.sql /docker-entrypoint-initdb.d/1_world.sql
ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_DATABASE=world
ENV MYSQL_ROOT_HOST=%