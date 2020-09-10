# <img src="https://github.com/tino097/awesome-spring-boot-rest-api/raw/master/spring-logo.png" align="absmiddle"/> Spring Boot Rest API For Pipa Score Test

[![Wercker](https://img.shields.io/badge/spring--boot-2.3.3.RELEASE-green.svg?style=flat-square&logo=spring)](https://spring.io/projects/spring-boot)
[![Wercker](https://img.shields.io/badge/java-8-blue.svg?style=flat-square&logo=java)](https://openjdk.java.net/install/)



## Introdução

Desenvolvimento de um mini back-end baseado em HTTP que registra pontos de diferentes usuários capaz de
retornar a posição atual do usuário e a lista ordenada pela pontuação.
Segue abaixo api's desenvolvidas:



## API para adicionar uma pontuação 

### Request

`POST /user/`

    curl --location --request POST 'localhost:8080/user'  --header 'Content-Type: application/json'  --data-raw '{"userId":11,"points":5000}    

### Response

    Date: Thu, 10 Sep 2020 01:23:03 GMT
    Status: 201 Created
    Content-Length: 0


## API para obter a posição do usuário 

### Request

`GET /user/<user_id>`

    curl --location --request GET 'localhost:8080/user/11/position'

### Response

    Date: Thu, 10 Sep 2020 01:22:52 GMT
    Status: 302 Found
    Content-Length: 0
    
## API para listar usuários por ranking 

### Request

`GET /user/highscorelist`

    curl --location --request GET 'localhost:8080/user/highscorelist'

### Response

    Date: Thu, 10 Sep 2020 01:22:57 GMT
    Status: 302 Found
    Content-Length: 0
    Response: {"highscores":[{"position":1,"user_id":10,"score":206},{"user_id":2,"score":185,"position":2},{"user_id":6,"score":164,"position":3},{"user_id":7,"position":5,"score":158},{"user_id":11,"position":5,"score":158},{"user_id":4,"score":157,"position":6},{"user_id":5,"score":129,"position":7},{"position":8,"user_id":8,"score":126},{"position":9,"user_id":3,"score":108},{"score":97,"position":10,"user_id":1},{"position":11,"score":91,"user_id":9}]}

