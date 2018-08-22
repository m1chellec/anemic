 -- DROP DATABASE IF EXISTS anemic_domain;

 -- CREATE DATABASE anemic_domain;

USE anemic_domain;

DROP TABLE IF EXISTS `purchased_movie`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint auto_increment NOT NULL ,
  `name` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `status_expiration_date` date NULL,
  `money_spent` decimal(18,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` bigint auto_increment NOT NULL,
  `name` varchar(50) NOT NULL,
  `licensing_model` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `purchased_movie` (
  `id` bigint auto_increment NOT NULL,
  `id_movie` bigint NOT NULL,
  `id_customer` bigint NOT NULL,
  `price` decimal(18,2) NOT NULL,
  `purchase_date` date NOT NULL,
  `expiration_date` date NULL,
  PRIMARY KEY (`id`),
  KEY `fk_purchased_movie_customer` (`id_customer`),
  KEY `fk_purchased_movie_movie` (`id_movie`),
  CONSTRAINT `fk_purchased_movie_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_purchased_movie_movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






