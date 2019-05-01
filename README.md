# swagger-springboot-sample

microserver with springboot by generating swagger-codegen sample


## How to build

to generate source code with swagger-codegen-cli from [spec/openapi.yaml](spec/openapi.yaml)

```bash
$ gradle generateSwaggerCode
```

to show help related to generateSwaggerCode

```bash
$ gradle generateSwaggerCodeOpenapiHelp
```

generated code is in `build/swagger-code-openapi`

```bash
$ tree build/swagger-code-openapi/
build/swagger-code-openapi/
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── io
    │   │       └── swagger
    │   │           ├── RFC3339DateFormat.java
    │   │           ├── Swagger2SpringBoot.java
    │   │           ├── api
    │   │           │   ├── ApiException.java
    │   │           │   ├── ApiOriginFilter.java
    │   │           │   ├── ApiResponseMessage.java
    │   │           │   ├── BooksApi.java
    │   │           │   ├── BooksApiController.java
    │   │           │   ├── NotFoundException.java
    │   │           │   ├── UsersApi.java
    │   │           │   └── UsersApiController.java
    │   │           ├── configuration
    │   │           │   ├── CustomInstantDeserializer.java
    │   │           │   ├── HomeController.java
    │   │           │   ├── JacksonConfiguration.java
    │   │           │   └── SwaggerDocumentationConfig.java
    │   │           └── model
    │   │               ├── Book.java
    │   │               ├── InlineResponse201.java
    │   │               └── User.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── io
                └── swagger
                    └── api
                        ├── BooksApiControllerIntegrationTest.java
                        └── UsersApiControllerIntegrationTest.java

14 directories, 22 files
```

## How to boot

```bash
$ gradle bootRun
```

or

```bash
$ gradle build
$ java -jar build/libs/swagger-springboot-sample.jar
```
(but there is no data, just confirm that generated jar can boot up)


## request to localhost

```bash
$ gradle bootRun
```

### ping

```bash
$ curl -s -v -X GET localhost:8080/ping | jq .
{
  "message": "pong"
}
```

### list

```bash
$ curl -s -v -X GET -H 'Auth-Token: secret' localhost:8080/users/list | jq .
```

### create

```bash
$ curl -s -v -X POST -H 'Auth-Token: passwd' -H 'Content-Type: application/json' localhost:8080/users/create -d '{"id": 4, "username": "sala", "phone": "000-123-456"}' | jq .
{
  "id": 4
}
```

### retrieve

```bash
$ curl -s -v -X GET -H 'Auth-Token: passwd' localhost:8080/users/4 | jq .
{
  "id": 4,
  "username": "sala",
  "firstName": null,
  "lastName": null,
  "birthday": null,
  "email": null,
  "password": null,
  "phone": "000-123-456",
  "userStatus": null
}
```

### update

```bash
$ curl -s -v -X PUT -H 'Auth-Token: passwd' -H 'Content-Type: application/json' localhost:8080/users/4 -d '{"firstName": "sala", "lastName": "smith"}' | jq .
< HTTP/1.1 200 OK
$ curl -s -v -X GET -H 'Auth-Token: passwd' localhost:8080/users/4 | jq .
{
  "id": 4,
  "username": null,
.
150 < HTTP/1.1 200 OK  "firstName": "sala",
  "lastName": "smith",
  "birthday": null,
  "email": null,
  "password": null,
  "phone": null,
  "userStatus": null
}
```

### delete

```bash
$ curl -s -v -X DELETE -H 'Auth-Token: passwd' localhost:8080/users/4 | jq .
< HTTP/1.1 200 OK
$ curl -s -v -X GET -H 'Auth-Token: passwd' localhost:8080/users/4 | jq .
< HTTP/1.1 404 Not Found
```

## References

* https://swagger.io/specification/
* https://github.com/int128/gradle-swagger-generator-plugin
* https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/

