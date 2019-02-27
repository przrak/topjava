DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, date_time, calories) VALUES
  (100000, 'Завтрак', '2019-02-24 11:05:00.000000', 500),
  (100000, 'Обед', '2019-02-25 14:13:00.000000', 600),
  (100000, 'Ужин', '2019-02-26 19:15:00.000000', 1000),
  (100001, 'Завтрак', '2019-02-23 10:04:00.000000', 500),
  (100001, 'Обед', '2019-02-24 16:11:00.000000', 550),
  (100001, 'Ужин', '2019-02-25 23:08:00.000000', 580);
