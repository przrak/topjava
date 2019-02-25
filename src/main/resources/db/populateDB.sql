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
  (100000, 'Завтрак', '2019-02-25 23:05:00.000000', 500),
--   (100000, 'lunch', '2019-02-25 23:06:00.000000', 5000),
--   (100000, 'dinner', '2019-02-25 23:07:00.000000', 5000),
--   (100001, 'breakfast', '2019-02-25 23:02:00.000000', 5000),
--   (100001, 'lunch', '2019-02-25 23:03:00.000000', 5000),
  (100001, 'Ужин', '2019-02-25 23:04:00.000000', 500);
