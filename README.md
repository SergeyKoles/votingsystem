# votingsystem

#### Выпускной проект Topjava
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides new menu each day.


Используемые инструменты и технологии: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Java 8 Stream and Time API и хранением базы данных в HSQLDB.

Скрипты инициализации и наполнение базы данных(HSQLDB) лежат -  `votingsystem/src/main/resources/db`

Статическая переменная DEAD_LINE_OF_VOTING (время до которого разрешается голосовать) устанавливается в `votingsystem\src\main\java\ru\kolesnikov\votingsystem\util\VoteUtil.java`

Команды curl находятся - `votingsystem\config\curl.md`

## Использование API

### Любой пользователь может посмотреть:
- список ресторанов `curl -s http://localhost:8080/voting/restaurants`
- ресторан с меню и рейтингом `curl -s http://localhost:8080/voting/restaurants/100009`
- посмотреть рейтинг ресторана `curl -s http://localhost:8080/voting/restaurants/100009/votes`

### Залогиненый пользователь может:
- проголосовать за ресторан до 11:00  `curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/profile/restaurants/100009/votes --user admin_A@yandex.ru:password_admin_A`
- изменить свое решение и отдать голос другому ресторану до 11:00 `curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/profile/restaurants/100010/votes --user user_A@yandex.ru:password_A`
- удалить свой голос до 11:00 `curl -s -X DELETE http://localhost:8080/voting/profile/votes --user user_B@yandex.ru:password_B`

### Администратор может:
- смотреть список ресторанов `curl -s http://localhost:8080/voting/admin/restaurants --user admin_A@yandex.ru:password_admin_A`
- смотреть список только своих ресторанов `curl -s http://localhost:8080/voting/admin/100008/restaurants --user admin_B@yandex.ru:password_admin_B`
- сотреть конкретный свой ресторан `curl -s http://localhost:8080/voting/admin/100007/restaurants/100009 --user admin_A@yandex.ru:password_admin_A`
- удалить свой ресторан `curl -s -X DELETE http://localhost:8080/voting/admin/restaurants/100010 --user admin_B@yandex.ru:password_admin_B`
- создать ресторан `curl -s -X POST -d '{"name":"DROVA"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/admin/restaurants --user admin_A@yandex.ru:password_admin_A`
- обновить данные ресторана `curl -s -X PUT -d '{"id":100009,"name":"Super Dodo","admin":{"id":100007}}' -H 'Content-Type: application/json' http://localhost:8080/voting/admin/restaurants/100012 --user admin_A@yandex.ru:password_admin_A`

- смотреть меню любого ресторана `curl -s http://localhost:8080/voting/admin/restaurants/100009/dishes --user admin_A@yandex.ru:password_admin_A`
- создать блюдо только своего ресторана `curl -s -X POST -d '{"name":"kulebyakas", "price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/admin/restaurants/100009/dishes --user admin_A@yandex.ru:password_admin_A`
- обновить блюдо только своего ресторана `curl -s -X PUT -d '{"id":100024,"name":"Grill","price":400}' -H 'Content-Type: application/json' http://localhost:8080/voting/admin/restaurants/100012/dishes/100024 --user admin_A@yandex.ru:password_admin_A`
- удалить блюдо только своего ресторана `curl -s -X DELETE http://localhost:8080/voting/admin/restaurants/100013/dishes/100027 --user admin_B@yandex.ru:password_admin_B`

- сбросить голоса всех ресторанов `curl -s -X DELETE http://localhost:8080/voting/admin/votes/reset --user admin_B@yandex.ru:password_admin_B`

