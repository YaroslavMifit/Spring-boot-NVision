# Spring-boot-NVision
Приложение использует базу MySql перед сборкой исходника необходимо ввести свои настройки бд в файле \src\main\resources\application.properties

# Connection url for the database "one"
spring.datasource.url=jdbc:mysql://localhost:3306/one

# Username and password
spring.datasource.username=root
spring.datasource.password=genius7783

Далее в консоли для сборки исходника необходимо ввести команду

mvn clean install

Далее для запуска исходника необходимо в консоли ввести команду

java -Dfile.encoding=utf-8 -jar target\NVision.jar

Приложение стартует на порту 8000
