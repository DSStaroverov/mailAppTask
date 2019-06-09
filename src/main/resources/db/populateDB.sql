DELETE FROM letters;
DELETE FROM folders;
DELETE FROM emails;
DELETE FROM user_roles;
DELETE FROM users;


ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, password, first_name, last_name) VALUES
  ('User', '{noop}password','user','first'), --100000
  ('User2', '{noop}password','user2','second'); --100001

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001);

INSERT INTO emails(address, user_id) VALUES
  ('user@mail.ru',100000), --100002
  ('user2@mail.ru',100001); --100003

INSERT INTO folders(name, email_id) VALUES
  ('inbox',100002),--100004
  ('outbox',100002),--100005
  ('inbox',100003),--100006
  ('outbox',100003);--100007

INSERT INTO letters(sender_id, recipient_id, folder_id, title, message) VALUES
  (100002,100003,100005,'hello','test message user for user2'),--100008
  (100002,100003,100006,'hello','test message user for user2'),--100009
  (100003,100002,100004,'re:hello','answer user2 for user'),--100010
  (100003,100002,100007,'re:hello','answer user2 for user');--100011
