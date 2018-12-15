DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;

ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO users (name) VALUES
  ('user_A'),
  ('user_B'),
  ('admin_A'),
  ('admin_B');
INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'USER'),
  (100001, 'USER'),
  (100002, 'ADMIN'),
  (100003, 'ADMIN');
INSERT INTO restaurants (NAME, USER_ID) VALUES
  ('DODO', 100002),
  ('Teremok', 100003);
INSERT INTO dishes (NAME, PRICE, RESTAURANT_ID) VALUES
  ('Pizza_Morgarita', 300, 100004),
  ('Pizza_Peperoni', 350, 100004),
  ('Blin_Cezar', 200, 100005),
  ('Blin_Plain', 100, 100005);
INSERT INTO votes (TIME_OF_VOTING, USER_ID, RESTAURANT_ID) VALUES
  ('09:00:00', 100000, 100004),
  ('10:00:00', 100001, 100004);
