DROP database IF EXISTS `ocbcmcd`;

-- drop database user
USE `mysql` ;

DROP PROCEDURE IF EXISTS `mysql`.`drop_user_if_exists` ;
DELIMITER $$
CREATE PROCEDURE `mysql`.`drop_user_if_exists`(IN p_username VARCHAR(255))
BEGIN
  DECLARE foo BIGINT DEFAULT 0 ;
  SELECT COUNT(*)
  INTO foo
    FROM `mysql`.`user`
      WHERE `User` =  p_username ;
  
  IF foo > 0 THEN 
         DROP USER  'ocbc' ;
  END IF;
END ;$$
DELIMITER ;

CALL `mysql`.`drop_user_if_exists`('ocbc') ;

DROP PROCEDURE IF EXISTS `mysql`.`drop_users_if_exists` ;
