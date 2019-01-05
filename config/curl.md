
### **any user**
### get All Restaurants with menu and vote's rate  - any user
`curl -s http://localhost:8080/voting/restaurants`
### get Restaurant with menu and vote's rate  - any user
`curl -s http://localhost:8080/voting/restaurants/100009`
### count votes by restaurant id  - any user
`curl -s http://localhost:8080/voting/restaurants/100009/votes`

### **authorized user**
### (create) vote DODO restaurant by Admin_A   - authorized user
`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/profile/restaurants/100009/votes --user admin_A@yandex.ru:password_admin_A`
### (update) vote TEREMOK restaurant by User_A (already voted)  - authorized user
`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/profile/restaurants/100010/votes --user user_A@yandex.ru:password_A`
### (delete) vote DODO restaurant by User_A (already voted)  - authorized user
`curl -s -X DELETE http://localhost:8080/voting/profile/votes --user user_B@yandex.ru:password_B`

### **admin**
### get All Restaurants  - admin
`curl -s http://localhost:8080/voting/admin/restaurants --user admin_A@yandex.ru:password_admin_A`
### get All Restaurants by Admin Id  - admin
`curl -s http://localhost:8080/voting/admin/100008/restaurants --user admin_B@yandex.ru:password_admin_B`
### get Restaurant by Id and by Admin Id  - admin
`curl -s http://localhost:8080/voting/admin/100007/restaurants/100009 --user admin_A@yandex.ru:password_admin_A`
### delete Restaurant by Id and by Admin Id  - admin
`curl -s -X DELETE http://localhost:8080/voting/admin/restaurants/100010 --user admin_B@yandex.ru:password_admin_B`
### create Restaurants  - admin
`curl -s -X POST -d '{"name":"DROVA"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/admin/restaurants --user admin_A@yandex.ru:password_admin_A`
### update Restaurants  - admin
`curl -s -X PUT -d '{"id":100009,"name":"Super Dodo","admin":{"id":100007}}' -H 'Content-Type: application/json' http://localhost:8080/voting/admin/restaurants/100012 --user admin_A@yandex.ru:password_admin_A`

### get All Dishes by Restaurant Id  - admin
`curl -s http://localhost:8080/voting/admin/restaurants/100009/dishes --user admin_A@yandex.ru:password_admin_A`
### create Dishes  - admin
`curl -s -X POST -d '{"name":"kulebyakas", "price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/admin/restaurants/100009/dishes --user admin_A@yandex.ru:password_admin_A`
### update Dishes  - admin
`curl -s -X PUT -d '{"id":100024,"name":"Grill","price":400}' -H 'Content-Type: application/json' http://localhost:8080/voting/admin/restaurants/100012/dishes/100024 --user admin_A@yandex.ru:password_admin_A`
### delete Dishes  - admin
`curl -s -X DELETE http://localhost:8080/voting/admin/restaurants/100013/dishes/100027 --user admin_B@yandex.ru:password_admin_B`

### reset votes  - admin
`curl -s -X DELETE http://localhost:8080/voting/admin/votes/reset --user admin_B@yandex.ru:password_admin_B`

