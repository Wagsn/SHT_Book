/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.17-log : Database - book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `bookName` varchar(50) DEFAULT NULL,
  `bookIntroduction` text,
  `bookPicture` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `bookGalleryId` int(11) DEFAULT NULL,
  `bookPost` varchar(20) DEFAULT NULL,
  `bookTime` datetime DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

/*Table structure for table `bookgallery` */

DROP TABLE IF EXISTS `bookgallery`;

CREATE TABLE `bookgallery` (
  `bookGalleryId` int(11) NOT NULL AUTO_INCREMENT,
  `bookGalleryName` varchar(20) DEFAULT NULL,
  `bookGalleryIntroduction` text,
  PRIMARY KEY (`bookGalleryId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `bookgallery` */

insert  into `bookgallery`(`bookGalleryId`,`bookGalleryName`,`bookGalleryIntroduction`) values (1,'音乃木坂学院','一所位于东京都千代田区的传统高校，因为入读的学生人数骤减，所以正面临着废校的危机。'),(2,'私立浦之星女子学院','位于静冈县沼津市的海边小镇。'),(3,'古典文学部','濒临废社'),(4,'椚丘中学','各国首脑将暗杀的重任交给椚丘中学3年E班，配送特制的武器给他们，希望学生们能在一年之内将杀老师杀掉，成功报酬是一百亿日元。'),(5,'雄英高中','是一所以培养对抗犯罪份子的英雄为目的而创设的高等教育机构。'),(6,'重庆邮电大学',NULL);

/*Table structure for table `fathertype` */

DROP TABLE IF EXISTS `fathertype`;

CREATE TABLE `fathertype` (
  `fathertypeId` int(11) NOT NULL AUTO_INCREMENT,
  `fathertypeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`fathertypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `fathertype` */

insert  into `fathertype`(`fathertypeId`,`fathertypeName`) values (1,'研究生/本科/专科教材'),(2,'外语考试'),(3,'工具书'),(4,'计算机/网络'),(5,'医学'),(6,'农业/林业'),(7,'自然科学'),(8,'工业技术'),(9,'建筑'),(10,'人文社科'),(11,'经管'),(12,'文学'),(13,'艺术'),(14,'摄影'),(15,'英文原版'),(16,'其他');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `bookId` int(11) DEFAULT NULL,
  `sellerid` int(11) DEFAULT NULL,
  `buyerid` int(11) DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `orderType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  `fathertypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`typeId`,`typeName`,`fathertypeId`) values (1,'公共课',1),(2,'工学',1),(3,'理学',1),(4,'文法类',1),(5,'医学',1),(6,'农学',1),(7,'经济管理类',1),(8,'大学生素质教育',1),(9,'外语',1),(10,'雅思IELTS',2),(11,'托福TOEFL',2),(12,'CET-4',2),(13,'CET-6',2),(14,'TEM-4',2),(15,'日语',2),(16,'韩语',2),(17,'德语',2),(18,'俄语',2),(19,'法语',2),(20,'西班牙语',2),(21,'意大利语',2),(22,'汉语工具书',3),(23,'英语工具书',3),(24,'其他语种工具书',3),(25,'百科全书/年鉴',3),(26,'手册',3),(27,'标准',3),(28,'资料类',3),(29,'类书/政书',3),(30,'Java',4),(31,'Python',4),(32,'C/C++',4),(33,'C#',4),(34,'PHP',4),(35,'Andriod',4),(36,'Ios',4),(37,'操作系统',4),(38,'数据库',4),(39,'深度学习',4),(40,'网络安全',4),(41,'算法',4),(42,'Go',4),(43,'Ruby',4),(44,'HTML XML JavaScript',4),(45,'Jsp',4),(46,'其他',4),(47,'预防医学/卫生学',5),(48,'临床医学理论',5),(49,'内科学',5),(50,'外科学',5),(51,'妇产科学',5),(52,'儿科学',5),(53,'其他临床医学',5),(54,'护理学',5),(55,'医技学',5),(56,'中医',5),(57,'药学',5),(58,'医疗器械及使用',5),(59,'农业基础科学',6),(60,'农业工程',6),(61,'农学',6),(62,'植物保护',6),(63,'农作物',6),(64,'园艺',6),(65,'林业',6),(66,'畜牧/狩猎/蚕/蜂',6),(67,'水产/渔业',6),(68,'动物医学',6),(69,'总论',7),(70,'数学',7),(71,'力学',7),(72,'物理学',7),(73,'化学',7),(74,'天文学',7),(75,'地球科学',7),(76,'生物科学',7),(77,'科技史',7),(78,'一般工业技术',8),(79,'机械/仪表工业',8),(80,'电子技术',8),(81,'电子通信',8),(82,'化学工业',8),(83,'冶金工业',8),(84,'矿业工程',8),(85,'石油/天然气工业',8),(86,'金属学与金属工艺',8),(87,'武器工业',8),(88,'能源与动力工程',8),(89,'原子能技术',8),(90,'轻工业/手工业',8),(91,'水利工程',8),(92,'汽车与交通运输',8),(93,'航空/航天',8),(94,'环境科学',8),(95,'安全科学',8),(96,'建筑史与建筑文化',9),(97,'建筑科学',9),(98,'建筑外观设计',9),(99,'室内设计/装潢装修',9),(100,'建筑施工与监理',9),(101,'工程经济与管理',9),(102,'城乡规划/市政工程',9),(103,'园林景观/环境艺术',9),(104,'历史',10),(105,'古籍',10),(106,'哲学/宗教',10),(107,'文化',10),(108,'政治/军事',10),(109,'法律',10),(110,'社会科学',10),(111,'心理学',10),(112,'管理',11),(113,'投资理财',11),(114,'经济',12),(115,'文集',12),(116,'诗歌',12),(117,'随笔',12),(118,'民间文学',12),(119,'名家作品',12),(120,'文学评论与鉴赏',12),(121,'艺术理论',13),(122,'绘画',13),(123,'书法/篆刻',13),(124,'影视/媒体艺术',13),(125,'戏剧艺术/舞台艺术',13),(126,'音乐',13),(127,'舞蹈',13),(128,'雕刻',13),(129,'设计',13),(130,'人体艺术',13),(131,'工艺美术',13),(132,'民间艺术',13),(133,'摄影理论',14),(134,'摄影入门',14),(135,'摄影进阶',14),(136,'摄影器材',14),(137,'分类摄影',14),(138,'技法教程',14),(139,'后期处理',14),(140,'工具书',15),(141,'计算机/网络',15),(142,'医学',15),(143,'农业/林业',15),(144,'自然科学',15),(145,'工业技术',15),(146,'建筑',15),(147,'人文社科',15),(148,'经管',15),(149,'文学',15),(150,'艺术',15),(151,'摄影',15),(152,'小说',16),(153,'漫画',16),(154,'杂志',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
