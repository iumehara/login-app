test:
	./gradlew build

start:
	./gradlew build && java -jar build/libs/login-app-spring-0.0.1-SNAPSHOT.jar

reset-db-dev:
	mysql -u root -e 'drop database login_app_dev'
	mysql -u root -e 'create database login_app_dev'
	mysql -u root login_app_dev < ./src/main/resources/db/migrations.sql
	mysql -u root login_app_dev < ./src/main/resources/db/seed.sql

reset-db-test:
	mysql -u root -e 'drop database login_app_test'
	mysql -u root -e 'create database login_app_test'
	mysql -u root login_app_test < ./src/main/resources/db/migrations.sql