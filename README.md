# Spring-boot-NVision
Приложение использует базу MySql перед сборкой исходника необходимо ввести свои настройки бд в файле \src\main\resources\application.properties

# Connection url for the database "one"
spring.datasource.url=jdbc:mysql://localhost:3306/one

# Username and password
spring.datasource.username=root
spring.datasource.password=genius7783

# Ветка master собирает jar file 
# Далее в консоли для сборки исходника необходимо ввести команду
mvn clean install

# Далее для запуска jar исходника необходимо в консоли ввести команду
java -Dfile.encoding=utf-8 -jar target\NVision.jar

# Приложение стартует на порту 8000

example: 
http://localhost:8000/jobs
http://localhost:8000/statistics

# Ветка dev собирает war file 
# Далее в консоли для сборки исходника необходимо ввести команду
mvn clean install

# Далее для запуска war исходника
 1) необходимо поместить его в tomcat/webapps
 2) зайти через консоль в папку tomcat\bin и ввести команду startup.bat (для Windows)

# Приложение стартует на порту 8080
example: 
http://localhost:8000/NVision/jobs
http://localhost:8000/NVision/statistics
