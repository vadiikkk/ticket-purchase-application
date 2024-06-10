# Система заказов на покупку билетов

## Описание проекта

Проект представляет собой систему, состоящую из двух микросервисов, разработанных для обработки заказов на покупку билетов. Один микросервис отвечает за авторизацию пользователей, второй - за формирование и обработку заказов на покупку билетов авторизованными пользователями.

### Микросервис авторизации пользователей

Основные функции:
- Регистрация нового пользователя.
- Вход пользователя в систему.
- Предоставление информации о пользователе.

### Микросервис заказов на покупку билетов

Основные функции:
- Создание заказа на покупку билета.
- Обработка заказов (включая изменение статуса заказа).
- Предоставление информации о заказе.

## Технологии

- Spring Boot для создания RESTful сервисов.
- PostgreSQL для хранения данных.
- Docker для контейнеризации и упрощения развертывания.
- Liquibase для управления версиями базы данных.
- Postman для тестирования API.

## Как запустить проект

### Предварительные требования

Убедитесь, что на вашем компьютере установлены Docker и Docker Compose.

### Запуск сервисов

1. Клонируйте репозиторий:
```
git clone git@github.com:vadiikkk/ticket-purchase-application.git
```
2. Перейдите в директорию проекта:
```
cd ticket-purchase-application
```
3. Запустите Docker Compose:
```
docker-compose up -d
```
4. Запустите каждый микросервис через IDE или командную строку.

## API Documentation

Для просмотра и тестирования API вы можете использовать коллекцию Postman, доступную по следующей ссылке:
[Коллекция Postman](https://www.postman.com/xfandomx/workspace/ticketpurchasing/example/33449527-8b1c90a2-16fa-4d8d-9eb2-2588c66843d3)

## Схемы баз данных

### Микросервис авторизации
```sql
CREATE TABLE user (
 id INT AUTO_INCREMENT PRIMARY KEY,
 nickname VARCHAR(50) NOT NULL,
 email VARCHAR(100) UNIQUE NOT NULL,
 password VARCHAR(255) NOT NULL,
 created TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE session (
 id INT AUTO_INCREMENT PRIMARY KEY,
 user_id INT NOT NULL,
 token VARCHAR(255) NOT NULL,
 expires TIMESTAMP NOT NULL,
 FOREIGN KEY (user_id) REFERENCES user(id)
);
```

### Микросервис заказов на покупку билетов
```sql
CREATE TABLE station (
    id INT AUTO_INCREMENT PRIMARY KEY,
    station VARCHAR(50) NOT NULL
);

CREATE TABLE order (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    from_station_id INT NOT NULL,
    to_station_id INT NOT NULL,
    status INT NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (from_station_id) REFERENCES station(id),
    FOREIGN KEY (to_station_id) REFERENCES station(id)
);
```

