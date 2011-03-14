/*
SQLyog Ultimate - MySQL GUI v8.21 
MySQL - 5.1.43-community : Database - ocbcmcd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ocbcmcd` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ocbcmcd`;

/*Data for the table `permissions` */

insert  into `permissions`(`id`,`permission_name`) values (1,'ROLE_PRS_LOG'),(2,'ROLE_PRS_CREATE_USER');

/*Data for the table `role_permission` */

insert  into `role_permission`(`role`,`permission`) values (1,1),(1,2);

/*Data for the table `roles` */

insert  into `roles`(`id`,`role_name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

/*Data for the table `user_role` */

insert  into `user_role`(`user`,`role`) values (1,1),(2,1),(9,1),(10,1),(1,2);

/*Data for the table `users` */

insert  into `users`(`id`,`user_name`,`password`,`created_date`,`enabled`) values (1,'admin','5f4dcc3b5aa765d61d8327deb882cf99',NULL,1),(2,'adi','5f4dcc3b5aa765d61d8327deb882cf99',NULL,1),(9,'tony','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-14 00:30:57',1),(10,'tony','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-14 00:31:57',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
