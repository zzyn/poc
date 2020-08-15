FROM mysql:5.7.29

ENV MYSQL_ROOT_PASSWORD=123

#mysql will execute the file in alphabetical order
COPY ./schema/kt-master-data-db.ddl /docker-entrypoint-initdb.d/0-schema.sql

EXPOSE 3306