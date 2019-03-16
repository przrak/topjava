DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS positions;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE positions
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL
);
CREATE TABLE persons
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  position_id      INTEGER                 NOT NULL
);

DELETE FROM persons;
DELETE FROM positions;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO persons (name, position_id) VALUES
('Владимир', 1),
('Татьяна', 2),
('Александр', 6),
('Борис', 2);

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO positions (name) VALUES
('Дизайнер'),
('Редактор'),
('Программист');