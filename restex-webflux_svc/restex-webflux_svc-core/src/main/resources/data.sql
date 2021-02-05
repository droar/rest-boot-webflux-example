DROP TABLE IF EXISTS exm_users;
DROP TABLE IF EXISTS exm_roles;

CREATE TABLE exm_users (
  usr_id INT AUTO_INCREMENT  PRIMARY KEY,
  usr_first_name VARCHAR(250) NOT NULL,
  usr_last_name VARCHAR(250) NOT NULL,
  usr_career VARCHAR(250) DEFAULT NULL,
  usr_username VARCHAR(250) DEFAULT NULL,
  usr_pwd VARCHAR(250) NOT NULL,
  usr_role_id INT NOT NULL
);

CREATE TABLE exm_roles (
  rle_id INT PRIMARY KEY,
  rle_name VARCHAR(250) NOT NULL
);

INSERT INTO exm_roles (rle_id, rle_name) VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_ADMIN'); 

INSERT INTO exm_users (usr_first_name, usr_last_name, usr_career, usr_username, usr_pwd, usr_role_id) VALUES
  ('Sergio', 'Roa', 'Software Engineer', 'sroaespi', '$2y$12$8N28iWH18pVmjg9jpibDn.e3g2/dVsxTsKk90qjwA0S3NpC0XnVQy', 2),
  ('Sergio Clone', 'Roa clone', 'Software Engineer', 'chileno', '$2y$12$8N28iWH18pVmjg9jpibDn.e3g2/dVsxTsKk90qjwA0S3NpC0XnVQy', 1),
  ('Example user 1', 'ex', 'Software Engineer', 'sroa' , '$2y$12$8N28iWH18pVmjg9jpibDn.e3g2/dVsxTsKk90qjwA0S3NpC0XnVQy', 1); 
