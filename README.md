[![Codacy Badge](https://api.codacy.com/project/badge/Grade/120a0430623240b68cdf64555215234a)](https://www.codacy.com/app/przrak/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=przrak/topjava&amp;utm_campaign=Badge_Grade)

Curl команды для тестирования:
1. GetAllMeals (User)

curl -X GET \
  http://localhost:8080/topjava/rest/meals/ \
  -H 'Postman-Token: 367fee94-392d-4c65-b5dd-ab841c1965ca' \
  -H 'cache-control: no-cache'
  
2. GetMealById (User)

curl -X GET \
  http://localhost:8080/topjava/rest/meals/100005 \
  -H 'Postman-Token: 0914b47e-4bc9-4031-bf53-9ddc711aa5b5' \
  -H 'cache-control: no-cache'
  
3. DeleteMealById (User)

curl -X DELETE \
  http://localhost:8080/topjava/rest/meals/100005 \
  -H 'Postman-Token: b5c29224-892c-4307-878c-3c678805317c' \
  -H 'cache-control: no-cache'
  
4. CreateMeal (User)

curl -X POST \
  http://localhost:8080/topjava/rest/meals/ \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 5eb2893b-c601-470a-9043-825eb07d353c' \
  -H 'cache-control: no-cache' \
  -d '{"dateTime":[2015,6,1,13,0],"description":"Тестовая запись","calories":1000}'
  
5. CreateMeal(alternate) (User)

curl -X POST \
  http://localhost:8080/topjava/rest/meals/ \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6d6a100e-c8e9-4cc1-b0f0-b0ca89976472' \
  -H 'cache-control: no-cache' \
  -d '{"dateTime":"2011-05-31T19:00","description":"Тестовая запись","calories":1000}'
  
6. UpdateMeal (User)

curl -X PUT \
  http://localhost:8080/topjava/rest/meals/100005 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 27930596-8599-419b-bf94-78ed105be5d4' \
  -H 'cache-control: no-cache' \
  -d '{"dateTime":"2011-05-31T19:01","description":"Тестовая запись2","calories":1000}'
  
6. GetFilteredMeals(alternate) (User)

curl -X GET \
  'http://localhost:8080/topjava/rest/meals/filter?startDate=2015-05-31&endDate=2015-05-31&startTime=19:00:00&endTime=21:00:00' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 07faaf0f-2b8d-40e2-a4d1-39fa24744682' \
  -H 'cache-control: no-cache'
  
  7. CreateUser (Admin)
  
  curl -X POST \
  http://localhost:8080/topjava/rest/admin/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6d13d7a3-e2c5-47af-9469-aa921d24b7de' \
  -H 'cache-control: no-cache' \
  -d '{"name":"Vasya","email":"test2@gmail.com","password":"pass","roles":["ROLE_USER"]}'
  
  8. GetAllUsers (Admin)
  
  curl -X GET \
  http://localhost:8080/topjava/rest/admin/users \
  -H 'Postman-Token: 941f4f25-374b-4d39-9df4-dae06667c661' \
  -H 'cache-control: no-cache'
  
  9. GetByEmail (Admin)
  
  curl -X GET \
  'http://localhost:8080/topjava/rest/admin/users/by?email=user@yandex.ru' \
  -H 'Postman-Token: ffd2dcb5-e90c-4c1a-9c0d-accafef364a2' \
  -H 'cache-control: no-cache'
  
  10. DeleteById (Admin)
  
  curl -X DELETE \
  http://localhost:8080/topjava/rest/admin/users/100000 \
  -H 'Postman-Token: adaeb1e5-5919-4146-8652-da5f9d9b83f1' \
  -H 'cache-control: no-cache'
  
  11. UpdateById (Admin)
  
  curl -X PUT \
  http://localhost:8080/topjava/rest/admin/users/100000 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 80c19f82-ebf1-4f69-aef8-c0aeb7c5cf04' \
  -H 'cache-control: no-cache' \
  -d '{"name":"UpdatedName","email":"user1@yandex.ru","password":"test","roles":["ROLE_ADMIN"]}'
  
  12. GetById (Admin)
  
  curl -X GET \
  http://localhost:8080/topjava/rest/admin/users/100001 \
  -H 'Postman-Token: 98bc4bf2-d3f7-44af-bc05-c35fe5f26fa0' \
  -H 'cache-control: no-cache'
  
  13. GetUser (User)
  
  curl -X GET \
  http://localhost:8080/topjava/rest/profile/ \
  -H 'Postman-Token: 13278721-5254-4055-8cf1-551c676e3f95' \
  -H 'cache-control: no-cache'
  
  14. DeleteUser (User)
  
  curl -X DELETE \
  http://localhost:8080/topjava/rest/profile/ \
  -H 'Postman-Token: b9ca2f16-5f4f-4a29-9b37-130ce59e5591' \
  -H 'cache-control: no-cache'
  
  15. UpdateUser (User)
  
  curl -X PUT \
  http://localhost:8080/topjava/rest/profile/ \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 922edb6c-4182-43a7-bd53-c6890ddc7c68' \
  -H 'cache-control: no-cache' \
  -d '{"name":"newName","email":"newemail@yandex.ru","password":"newPassword","roles":["ROLE_USER"]}'
  
