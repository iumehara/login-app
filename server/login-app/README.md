# LoginApp-Server

# Getting Started

## Set Up the DB - MySQL
from `mysql -u root`,

#### Create, Migrate and Seed the dev database
- `create database login_app_dev;`
- `use login_app_dev;`
- `\. /path-to-app/login-app/src/main/resources/db/migrations.sql`
- `\. /path-to-app/login-app/src/main/resources/db/seed.sql`

#### Create, Migrate and Seed the test database
- `create database login_app_test;`
- `use login_app_test;`
- `\. /path-to-app/login-app/src/main/resources/db/migrations.sql`
- `\. /path-to-app/login-app/src/main/resources/db/seed.sql`

# Start the App
- `./gradlew build && java -jar ./build/libs/login-app-0.0.1-SNAPSHOT.jar`

# Run the Tests
- `./gradlew build`