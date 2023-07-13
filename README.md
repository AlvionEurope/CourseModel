# CourseModel
Тестовое задание для кандидатов 

Рудников Кирилл Глебович

Контактная информация:

Email: kirillrudnikov811@gmail.com
Telegram: https://t.me/kirillrudnikov

# API

StudentController:

| Метод | Путь | Описание | Тело запроса |
| ------ | ------ | ------ | ------ |
| HTTP::POST | localhost:8080/api/v1/students | Создать нового студента | StudentCreateDto |
| HTTP::GET | localhost:8080/api/v1/students | Получить список всех студентов | Нет |
| HTTP::GET | localhost:8080/api/v1/students/id | Получить студента с ID = id | Нет |
| HTTP::PUT | localhost:8080/api/v1/students/id | Обновить студента с ID = id | StudentUpdateDto |
| HTTP::DELETE | localhost:8080/api/v1/students/id | Удалить студента с ID = id | Нет |

ProfessorController:

| Метод | Путь | Описание | Тело запроса |
| ------ | ------ | ------ | ------ |
| HTTP::POST | localhost:8080/api/v1/professors | Создать нового профессора | ProfessorCreateDto |
| HTTP::GET | localhost:8080/api/v1/professors | Получить список всех профессоров | Нет |
| HTTP::GET | localhost:8080/api/v1/professors/id | Получить профессора с ID = id | Нет |
| HTTP::PUT | localhost:8080/api/v1/professors/id | Обновить профессора с ID = id | StudentUpdateDto |
| HTTP::DELETE | localhost:8080/api/v1/professors/id | Удалить профессора с ID = id | Нет |

CourseController:

| Метод | Путь | Описание | Тело запроса |
| ------ | ------ | ------ | ------ |
| HTTP::POST | localhost:8080/v1/courses | Создать новый курс | CourseCreateDto |
| HTTP::GET | localhost:8080/v1/courses | Получить список всех курсов | Нет |
| HTTP::GET | localhost:8080/v1/courses/id | Получить курс с ID = id | Нет |
| HTTP::PUT | localhost:8080/v1/courses/id | Обновить курс с ID = id | CourseUpdateDto |
| HTTP::DELETE | localhost:8080/v1/courses/id | Удалить курс с ID = id | Нет |

CourseProgressController:

| Метод | Путь | Описание | Тело запроса |
| ------ | ------ | ------ | ------ |
| HTTP::POST | localhost:8080/api/v1/course_progresses | Создать новый прогресс по курсу | CourseProgressCreateDto |
| HTTP::GET | localhost:8080/api/v1/course_progresses | Получить список всех прогрессов по курсу | Нет |
| HTTP::GET | localhost:8080/api/v1/course_progresses/id | Получить прогресс по курсу с ID = id | Нет |
| HTTP::PUT | localhost:8080/api/v1/course_progresses/id | Обновить прогресс по курсу с ID = id | CourseProgressUpdateDto |
| HTTP::DELETE | localhost:8080/api/v1/course_progresses/id | Удалить прогресс по курсу с ID = id | Нет |

ExcelReportController:

| Метод | Путь | Описание | Тело запроса |
| ------ | ------ | ------ | ------ |
| HTTP::GET | localhost:8080/api/v1/reports/professors | Создать отчёт о преподавательском составе | Нет |
| HTTP::GET | localhost:8080/api/v1/reports/professors/id | Создать отчёт о преподавателе, его курсах и его студентах с ID = id | Нет |
