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

/*Data for the table `log_event` */

insert  into `log_event`(`id`,`file_name`,`type`,`date`,`description`) values (6,'data.txt','PROCESSED','2011-03-03 13:28:26',NULL),(7,'data.txt','SENT','2011-03-03 13:28:50',NULL),(8,'110221130912_1111_03702.txt','SENT','2011-03-07 15:03:14',NULL),(9,'110303065116_0001_00001.txt','SENT','2011-03-07 15:03:14',NULL),(10,'110304100746_0001_00002.txt','SENT','2011-03-07 15:03:15',NULL),(11,'110221130912_1111_03702.txt','SENT','2011-03-07 15:07:53',NULL),(12,'110303065116_0001_00001.txt','SENT','2011-03-07 15:07:53',NULL),(13,'110304100746_0001_00002.txt','SENT','2011-03-07 15:07:54',NULL),(14,'110221130912_1111_03702.txt','SENT','2011-03-07 15:10:11',NULL),(15,'110303065116_0001_00001.txt','SENT','2011-03-07 15:10:12',NULL),(16,'110304100746_0001_00002.txt','SENT','2011-03-07 15:10:13',NULL),(17,'110221130912_1111_03702.txt','SENT','2011-03-07 15:12:20',NULL),(18,'110221130912_1111_03702.txt','PROCESSED','2011-03-07 15:12:20',NULL),(19,'110303065116_0001_00001.txt','SENT','2011-03-07 15:12:20',NULL),(20,'110303065116_0001_00001.txt','PROCESSED','2011-03-07 15:12:20',NULL),(21,'110304100746_0001_00002.txt','SENT','2011-03-07 15:12:21',NULL),(22,'110304100746_0001_00002.txt','PROCESSED','2011-03-07 15:12:21',NULL),(23,'110221130912_1111_03702.txt','SENT','2011-03-07 15:17:47',NULL),(24,'110303065116_0001_00001.txt','SENT','2011-03-07 15:17:48',NULL),(25,'110304100746_0001_00002.txt','SENT','2011-03-07 15:17:49',NULL),(26,'110221130912_1111_03702.txt','SENT','2011-03-07 15:19:13',NULL),(27,'110221130912_1111_03702.txt','PROCESSED','2011-03-07 15:19:13',NULL),(28,'110303065116_0001_00001.txt','SENT','2011-03-07 15:19:13',NULL),(29,'110303065116_0001_00001.txt','PROCESSED','2011-03-07 15:19:14',NULL),(30,'110304100746_0001_00002.txt','SENT','2011-03-07 15:19:14',NULL),(31,'110304100746_0001_00002.txt','PROCESSED','2011-03-07 15:19:15',NULL),(32,'duplicated.txt','DUPLICATED','2011-03-16 08:21:04',NULL),(33,'data.txt','DUPLICATED','2011-03-16 08:39:55',NULL),(34,'data.txt','SENDING_FAILED','2011-03-16 08:42:12','FTP ERROR'),(35,'processedFailed.txt','PROCESSED_FAILED','2011-03-16 08:54:02',''),(36,'processedFailed.txt','SENDING_FAILED','2011-03-16 09:24:50','FTP ERROR'),(37,'sendingFailed.txt','SENDING_FAILED','2011-03-16 09:39:18','Error Message');

/*Data for the table `permissions` */

insert  into `permissions`(`id`,`permission_name`) values (1,'PRS_LOG'),(2,'PRS_CREATE_USER'),(3,'PRS_ADM_UPDATE_USER'),(4,'PRS_DISABLE_USR'),(5,'PRS_ENABLE_USR'),(6,'PRS_UPDATE_USR'),(8,'PRS_USER_LIST'),(9,'PRS_USR_VIEW');

/*Data for the table `role_permission` */

insert  into `role_permission`(`role`,`permission`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,8),(1,9),(2,1),(2,6),(2,9);

/*Data for the table `roles` */

insert  into `roles`(`id`,`role_name`) values (1,'ROLE_ADMIN'),(2,'ROLE_REGULAR');

/*Data for the table `user_role` */

insert  into `user_role`(`user`,`role`) values (1,1),(22,1),(23,1),(24,1),(1,2),(2,2),(25,2);

/*Data for the table `users` */

insert  into `users`(`id`,`user_name`,`password`,`created_date`,`enabled`) values (1,'admin','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-14 02:33:29',1),(2,'adi','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-14 02:33:29',1),(22,'soni','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-15 12:20:51',1),(23,'yai','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-16 00:36:24',1),(24,'robi','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-16 00:38:49',1),(25,'kaka','5f4dcc3b5aa765d61d8327deb882cf99','2011-03-16 00:40:56',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
