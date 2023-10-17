-- Drop a table if it exists
DROP TABLE IF EXISTS home;

DROP TABLE IF EXISTS user;

-- imobiliaria.`user` definition
CREATE TABLE user (
  `id` VARCHAR(255) NOT NULL,
  `active` BOOLEAN DEFAULT NULL,
  `address` VARCHAR(255) DEFAULT NULL,
  `agent_society` VARCHAR(255) DEFAULT NULL,
  `agent_type` VARCHAR(255) DEFAULT NULL,
  `county` VARCHAR(255) DEFAULT NULL,
  `date_birth` DATE DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `image_file_name` VARCHAR(255) DEFAULT NULL,
  `image` VARCHAR(255) DEFAULT NULL,
  `language` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `permissions` VARCHAR(255) DEFAULT NULL,
  `phone_number` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`email`)
);

-- imobiliaria.home definition
CREATE TABLE home (
  `id` VARCHAR(255) NOT NULL,
  `construction_year` INT DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `floor` VARCHAR(255) DEFAULT NULL,
  `home_type` VARCHAR(255) DEFAULT NULL,
  `house_business_state` VARCHAR(255) DEFAULT NULL,
  `image_file_name` VARCHAR(255) DEFAULT NULL,
  `image` VARCHAR(255) DEFAULT NULL,
  `location` VARCHAR(255) DEFAULT NULL,
  `lot_total` VARCHAR(255) DEFAULT NULL,
  `parking` BOOLEAN DEFAULT NULL,
  `price` VARCHAR(255) DEFAULT NULL,
  `room` INT DEFAULT NULL,
  `wcs` INT DEFAULT NULL,
  `id_user` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
);

INSERT INTO user
(id, active, address, agent_society, agent_type, county, date_birth, email, image_file_name, image, language, name, password, permissions, phone_number)
VALUES('7b2fae87-178a-4a4a-a328-9f94a026bb28', FALSE, 'teste', 'Gestor Equipa Comercial', 'teste', 'Cascais', NULL, 'bernardo.ferreira@decode.pt', NULL, NULL, NULL, 'Bernardo', '$2a$10$6TtZIOInhopCAcZlTRLfFuvwukw8vEIHGs0/RZCcr2iaTuGQWxZP6', 'ADMIN', 936142653);

INSERT INTO home
(id, construction_year, description, floor, home_type, house_business_state, image_file_name, image, location, lot_total, parking, price, room, wcs, id_user)
VALUES('1991fb89-4e94-4d5f-852d-11cfc1116db8', 2023, 'teste', '3', 'moradia', NULL, NULL, NULL, 'oeiras', '500', TRUE, '500,000.00', 3, 3, '7b2fae87-178a-4a4a-a328-9f94a026bb28');