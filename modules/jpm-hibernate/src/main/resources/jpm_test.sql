
CREATE DATABASE `jpm_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE USER 'jpm'@'localhost' IDENTIFIED BY 'jpm';

GRANT USAGE ON *.* TO 'jpm'@'localhost' IDENTIFIED BY 'jpm' WITH 
MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 
MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

GRANT ALL PRIVILEGES ON `jpm_test`.* TO 'jpm'@'localhost';

CREATE TABLE `jpm_test`.`simpleclass` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    `description` VARCHAR( 255 ) NULL
) ENGINE = InnoDB;

INSERT INTO `jpm_test`.`simpleclass` (`id`, `description`) VALUES ('1', 'T1');
INSERT INTO `jpm_test`.`simpleclass` (`id`, `description`) VALUES ('2', 'T2');
