first you should install mail dev on your system with following command :
sudo git clone https://github.com/maildev/maildev.git
sudo apt-get install nodejs-dev node-gyp libssl1.0-dev
sudo apt-get install npm
sudo npm install -g maildev
sudo npm install -g ./MailDev
maildev

To get started with this project, you will need to have the following installed on your local machine:

JDK 17+
Maven 3+
To build and run the project, follow these steps:

Clone the repository: git clone https://github.com/aliidolati/Authentication-emailVerfication.git
Navigate to the project directory: cd authentication
Add database "user" to mariadb or any database(change the application.properties)
Build the project: mvn clean install
Run the project: mvn spring-boot:run
-> The application will be available at http://localhost:8080.




