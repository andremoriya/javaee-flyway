CREATE TABLE IF NOT EXISTS address (
  id bigserial NOT NULL PRIMARY KEY,
  zipcode VARCHAR(12) NOT NULL,
  street VARCHAR(300),
  neighborhood VARCHAR (150),
  city VARCHAR (200) NOT NULL,
  state VARCHAR(4) NOT NULL,
  country VARCHAR(250) NOT NULL
);