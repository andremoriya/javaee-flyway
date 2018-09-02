CREATE TABLE IF NOT EXISTS country (
  id bigserial NOT NULL PRIMARY KEY,
  country_code INT,
  country_name VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS state (
  id bigserial NOT NULL PRIMARY KEY,
  state_code INT,
  state_name VARCHAR(150),
  country_id BIGINT,
  CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE IF NOT EXISTS city (
  id bigserial NOT NULL PRIMARY KEY,
  city_code INT,
  city_name VARCHAR(250) NOT NULL,
  state_id bigint,
  CONSTRAINT fk_state_id FOREIGN KEY (state_id) REFERENCES state (id)
);

ALTER TABLE address DROP COLUMN IF EXISTS city, DROP COLUMN IF EXISTS state, DROP COLUMN IF EXISTS country;

ALTER TABLE address ADD COLUMN IF NOT EXISTS city_id bigint;
ALTER TABLE address ADD CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES city (id);