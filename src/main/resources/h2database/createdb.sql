CREATE TABLE IF NOT EXISTS club (
  id serial PRIMARY KEY,
  title CHARACTER VARYING(100) NOT NULL,
  opt_lock_version INTEGER
);

CREATE TABLE IF NOT EXISTS city (
  id serial PRIMARY KEY,
  title CHARACTER VARYING(100) NOT NULL,
  opt_lock_version INTEGER
);

CREATE TABLE IF NOT EXISTS client (
  id serial PRIMARY KEY,
  first_name CHARACTER VARYING(20) NOT NULL,
  last_name CHARACTER VARYING(20) NOT NULL,
  city_id INTEGER,
  opt_lock_version INTEGER,
  FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE IF NOT EXISTS client_club
(
	client_id      INTEGER,
	club_id       INTEGER,
	PRIMARY KEY (client_id, club_id),
	FOREIGN KEY (client_id) REFERENCES client (id),
	FOREIGN KEY (club_id) REFERENCES club (id)
);