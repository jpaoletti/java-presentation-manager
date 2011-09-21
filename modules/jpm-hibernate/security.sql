CREATE TABLE IF NOT EXISTS `sec_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` char(1) NOT NULL,
  `creation` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB ;

INSERT INTO `sec_groups` (`id`, `active`, `creation`, `description`, `name`)
VALUES (1, 'Y', CURRENT_DATE, 'Super Administration', 'Administrators');

CREATE TABLE IF NOT EXISTS `sec_group_perms` (
  `sec_group` bigint(20) NOT NULL,
  `sec_perm` bigint(20) NOT NULL,
  PRIMARY KEY (`sec_group`,`sec_perm`),
  KEY `FKD32752D52BAA1925` (`sec_group`),
  KEY `FKD32752D5580C114D` (`sec_perm`)
) ENGINE=InnoDB;

INSERT INTO `sec_group_perms` (`sec_group`, `sec_perm`) VALUES
(1, 1),(1, 2),(1, 3);

CREATE TABLE IF NOT EXISTS `sec_perms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;


INSERT INTO `sec_perms` (`id`, `description`, `name`) VALUES
(1, NULL, 'login'),
(2, NULL, 'useradmin'),
(3, NULL, 'sysadmin');

CREATE TABLE IF NOT EXISTS `sec_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` char(1) NOT NULL,
  `change_password` char(1) DEFAULT NULL,
  `deleted` char(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick` varchar(32) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nick` (`nick`)
) ENGINE=InnoDB;

-- user: admin , password: test

INSERT INTO `sec_users` (`id`, `active`, `change_password`, `deleted`, `email`, `name`, `nick`, `password`) VALUES
(1, 'Y', 'N', 'N', '', 'Superadmin', 'admin', '$2a$12$zofXZl6UI.uTuqBSyKwvvOh2Qbx5vjGkgGv8MeH9/6TBPncRK2RHq');

CREATE TABLE IF NOT EXISTS `sec_user_groups` (
  `sec_user` bigint(20) NOT NULL,
  `sec_group` bigint(20) NOT NULL,
  KEY `FK742049DA2BAA1925` (`sec_group`),
  KEY `FK742049DA66703824` (`sec_user`)
) ENGINE=InnoDB;

INSERT INTO `sec_user_groups` (`sec_user`, `sec_group`) VALUES (1, 1);

CREATE TABLE IF NOT EXISTS `sec_user_props` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `propName` varchar(255) DEFAULT NULL,
  `propValue` varchar(255) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3E0C40AA2F4930F6` (`user`)
) ENGINE=InnoDB;


ALTER TABLE `sec_group_perms`
    ADD CONSTRAINT `FKD32752D52BAA1925` FOREIGN KEY (`sec_group`)
    REFERENCES `sec_groups` (`id`),
    ADD CONSTRAINT `FKD32752D5580C114D` FOREIGN KEY (`sec_perm`)
    REFERENCES `sec_perms` (`id`);

ALTER TABLE `sec_user_groups`
    ADD CONSTRAINT `sec_user_groups_ibfk_1` FOREIGN KEY (`sec_user`)
    REFERENCES `sec_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `sec_user_groups_ibfk_2` FOREIGN KEY (`sec_group`)
    REFERENCES `sec_groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `sec_user_props`
    ADD CONSTRAINT `FK3E0C40AA2F4930F6` FOREIGN KEY (`user`)
    REFERENCES `sec_users` (`id`);
