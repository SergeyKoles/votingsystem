DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM users;

ALTER SEQUENCE global_seq
  RESTART WITH 100000;

INSERT INTO users (email, password)
VALUES
  ('user_A@yandex.ru', 'password_A'),
  ('user_B@yandex.ru', 'password_B'),
  ('user_C@yandex.ru', 'password_C'),
  ('user_D@yandex.ru', 'password_D'),
  ('user_E@yandex.ru', 'password_E'),
  ('user_F@yandex.ru', 'password_F'),
  ('user_G@yandex.ru', 'password_G'),
  ('admin_A@yandex.ru', 'password_admin_A'),
  ('admin_B@yandex.ru', 'password_admin_B');
INSERT INTO user_roles (user_id, role)
VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER'),
  (100003, 'ROLE_USER'),
  (100004, 'ROLE_USER'),
  (100005, 'ROLE_USER'),
  (100006, 'ROLE_USER'),
  (100007, 'ROLE_ADMIN'),
  (100008, 'ROLE_ADMIN');
INSERT INTO restaurants (NAME, USER_ID)
VALUES
  ('DODO', 100007),
  ('TEREMOK', 100008),
  ('MD', 100008),
  ('OLIS', 100007),
  ('LESTER', 100008),
  ('KFC', 100008);
INSERT INTO dishes (NAME, PRICE, RESTAURANT_ID)
VALUES
  ('Pizza_Morgarita', 300, 100009),
  ('Pizza_Peperoni', 350, 100009),
  ('Blin_Cezar', 200, 100009),
  ('Blin_Plain', 100, 100010),
  ('CHICKEN', 120, 100010),
  ('HAMBURGER', 130, 100010),
  ('EGGS', 140, 100011),
  ('PORK', 150, 100011),
  ('PEPSI', 160, 100011),
  ('COCA_COLA', 170, 100012),
  ('FANTA', 180, 100012),
  ('TEA', 190, 100012),
  ('COFFEE', 210, 100013),
  ('JUICE', 220, 100013),
  ('BEER', 275, 100014),
  ('MOCHA', 290, 100013),
  ('BEFF', 260, 100014),
  ('ICE_CREAM', 270, 100014);
INSERT INTO votes (TIME_OF_VOTING, USER_ID, RESTAURANT_ID)
VALUES
  ('09:00:00', 100000, 100009),
  ('10:00:00', 100001, 100009),
  ('10:11:00', 100002, 100009),
  ('10:00:13', 100003, 100010),
  ('08:00:00', 100004, 100010),
  ('10:30:00', 100005, 100012),
  ('10:59:59', 100006, 100014);

