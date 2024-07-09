# run mysql in docker
docker run -p 3307:3306 --name=mysql -e MYSQL_ROOT_PASSWORD=admin mysql
# in docker terminal create database
mysql -u root -p # enter password
# create database
CREATE DATABASE springbootmysql;
# build app with gradle
.\gradlew build
# build docker image
docker build -f Dockerfile -t springbootmysql .
# run spring boot docker image
docker run --name=springbootmysql -p 8080:8080 springbootmysql
