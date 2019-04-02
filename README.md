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
  -H 'Postman-Token: 6d6a100e-c8e9-4cc1-b0f0-b0ca89976472' \
  -H 'cache-control: no-cache' \
  -d '{"dateTime":"2011-05-31T19:00","description":"Тестовая запись","calories":1000}'
  
5. UpdateMeal (User)
curl -X PUT \
  http://localhost:8080/topjava/rest/meals/100005 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 27930596-8599-419b-bf94-78ed105be5d4' \
  -H 'cache-control: no-cache' \
  -d '{"dateTime":"2011-05-31T19:01","description":"Тестовая запись2","calories":1000}'
  
6. GetFilteredMeals (User)
curl -X GET \
  'http://localhost:8080/topjava/rest/meals/filter?startDate=2015-05-31T19:00:00&endDate=2015-05-31T21:00:00' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 59c5ba8d-88e7-4311-8e7a-e4a643510a96' \
  -H 'cache-control: no-cache'
