# **DBCRUD**
## **Описание**
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

Writer (id, firstName, lastName, List<Post> posts)
Post (id, content, created, updated, List<Label> labels)
Label (id, name)
PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

## **Запуск проекта**
Запуск docker-compose файла "docker-compose up"
Запуск плагина liquibase для maven "mvn liquibase:update"
