# Records schema

# --- !Ups
CREATE TABLE RECORDS
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  number VARCHAR(11) NOT NULL
);

INSERT INTO Records (name, number) VALUES ('Ivanov', '+79111111111');
INSERT INTO Records (name, number) VALUES ('Petrov', '79222222222');
INSERT INTO Records (name, number) VALUES ('Sidorov', '79333333333');

# --- !Downs
drop table RECORDS;