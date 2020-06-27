CREATE TABLE PHONE_PLAN (
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   minutes DECIMAL(10,2) NOT NULL,
   created_at DATE NOT NULL,
   updated_at DATE NOT NULL
);

CREATE TABLE PHONE_COST (
   id INT NOT NULL AUTO_INCREMENT,
   source_ddd INT NOT NULL,
   destination_ddd INT NOT NULL,
   cost_per_minute  DECIMAL(10,2),
   created_at DATE NOT NULL,
   updated_at DATE NOT NULL
);

