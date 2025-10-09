FROM mysql/mysql-server:latest
#Seeding DB with data from world.sql
COPY world.sql /docker-entrypoint-initdb.d
#Add env var
ENV MYSQL_ROOT_PASSWORD example
#Fix for mac
ENV MYSQL_ROOT_HOST=%