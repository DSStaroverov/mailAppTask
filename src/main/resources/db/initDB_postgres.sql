DROP TABLE IF EXISTS letters;
DROP TABLE IF EXISTS folders;
DROP TABLE IF EXISTS emails;
DROP TABLE IF EXISTS user_roles ;
DROP TABLE IF EXISTS users;


DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  login            VARCHAR(255)            NOT NULL,
  password         VARCHAR(255)            NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL,
  first_name       VARCHAR(255)            NOT NULL,
  last_name        VARCHAR(255)            NOT NULL,
  birthday         TIMESTAMP DEFAULT now() NOT NULL,
  phone_number     VARCHAR(13)
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE emails
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  address          VARCHAR(255)            NOT NULL,
  user_id          INTEGER                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  CONSTRAINT email_unique_idx UNIQUE (address),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE folders
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  name            VARCHAR(255)            NOT NULL,
  email_id        INTEGER                 NOT NULL,
  CONSTRAINT folders_unique_idx UNIQUE (name,email_id),
  FOREIGN KEY (email_id) REFERENCES EMAILS (id) ON DELETE CASCADE
);

CREATE TABLE letters
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  sender_id       INTEGER                 NOT NULL,
  recipient_id    INTEGER                 NOT NULL,
  folder_id       INTEGER                 NOT NULL,
  send_time       TIMESTAMP DEFAULT now() NOT NULL,
  title           VARCHAR(255)            NOT NULL,
  message         text                    NOT NULL,
  FOREIGN KEY (sender_id) REFERENCES EMAILS (id),
  FOREIGN KEY (recipient_id) REFERENCES EMAILS (id) ON DELETE CASCADE,
  FOREIGN KEY (folder_id) REFERENCES FOLDERS (id) ON DELETE CASCADE
);
