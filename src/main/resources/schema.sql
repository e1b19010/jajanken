CREATE TABLE users(
  id INT NOT NULL,
  name CHAR NOT NULL
);

CREATE TABLE matches(
  id INT NOT NULL,
  user1 INT NOT NULL,
  user2 INT NOT NULL,
  user1Hand CHAR,
  user2Hand CHAR,
  isActive BOOLEAN
);

CREATE TABLE matchinfo(
  id IDENTITY,
  user1 INT NOT NULL,
  user2 INT NOT NULL,
  user1Hand CHAR NOT NULL,
  isActive BOOLEAN NOT NULL
);
