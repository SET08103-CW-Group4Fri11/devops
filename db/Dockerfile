FROM mysql/mysql-server:latest
COPY world.sql /docker-entrypoint-initdb.d
#Add env var
ENV MYSQL_ROOT_PASSWORD example
#Fix for mac
ENV MYSQL_ROOT_HOST=%