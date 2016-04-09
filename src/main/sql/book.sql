-- MySQL dump 10.16  Distrib 10.1.12-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: book
-- ------------------------------------------------------
-- Server version	10.1.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `associations`
--

DROP TABLE IF EXISTS `associations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `associations` (
  `id` varchar(36) NOT NULL,
  `percent` double NOT NULL,
  `x` varchar(255) NOT NULL,
  `y` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `associations`
--

LOCK TABLES `associations` WRITE;
/*!40000 ALTER TABLE `associations` DISABLE KEYS */;
INSERT INTO `associations` VALUES ('1',0.6,'10','6'),('2',0.7,'10','7'),('3',0.8,'10','8'),('4',0.9,'10','9');
/*!40000 ALTER TABLE `associations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` varchar(36) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `percent` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfb1k27rsxco0bd8me1s2kdpq` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('10','潘晓燕','真题集训的描述','真题集训','NUM-2016-0010','1bfourji.png',23,0),('11','路易斯','holes的描述','holes','NUM-2016-0011','1choles.png',46,0),('12','James','cat的描述','cat','NUM-2016-0012','1ccat.png',35,0),('13','欧雷','bunny的描述','bunny','NUM-2016-0013','1chunny.png',48,0),('14','JKRowling','harrypotter的描述','harrypotter','NUM-2016-0014','1charry.png',157,0),('15','莫里斯','cookie的描述','cookie','NUM-2016-0015','1ccookie.png',33,0),('16','李栓科','极致之美的描述','极致之美','NUM-2016-0016','2tjizhimei.png',168,0),('17','罗莎蒙德','生命之灵的描述','生命之灵','NUM-2016-0017','2tshengming.png',176,0),('18','克里斯蒂娜','自然灵感的描述','自然灵感','NUM-2016-0018','2tziran.png',159,0),('19','IBE','自然之美的描述','自然之美','NUM-2016-0019','2tziranmei.png',182,0),('2','胡壮麟','新世纪英汉大词典的描述','新世纪英汉大词典','NUM-2016-002','1axinshiji.png',99,0),('20','肖邦','肖邦圆舞曲集的描述','肖邦圆舞曲集','NUM-2016-0020','2qyuanwu.png',44,0),('21','肖邦','肖邦练习曲集的描述','肖邦练习曲集','NUM-2016-0021','2qlianxi.png',44,0),('22','哈农','哈农的描述','哈农','NUM-2016-0022','2qhanong.png',34,0),('23','杰姆钦克夫','钢琴传奇的描述','钢琴传奇','NUM-2016-0023','2qgangqing.png',33,0),('24','巴赫','巴赫的描述','巴赫','NUM-2016-0024','2qbahe.png',40,0),('25','韩林申','钢琴基础教程1的描述','钢琴基础教程1','NUM-2016-0025','2qgangqin1.png',23,0),('26','韩林申','钢琴基础教程2的描述','钢琴基础教程2','NUM-2016-0026','2qgangqin2.png',23,0),('27','韩林申','钢琴基础教程3的描述','钢琴基础教程3','NUM-2016-0027','2qgangqin3.png',23,0),('28','牛雪彤','梵高的描述','梵高','NUM-2016-0028','2hfangao.png',79,0),('29','蔡国胜','中国油画家的描述','中国油画家','NUM-2016-0029','2hyouhuajia.png',78,0),('3','杨建峰','小学生英汉词典的描述','小学生英汉词典','NUM-2016-003','1axiao.png',36,0),('30','张恒国','油画的描述','油画','NUM-2016-0030','2hyouhuajichu.png',88,0),('31','杰里米','油画技法的描述','油画技法','NUM-2016-0031','2hyouhua.png',39,0),('32','张恒国','素描基础教程的描述','素描基础教程','NUM-2016-0032','2hsumiaojichu.png',35,0),('33','伯特','素描的诀窍的描述','素描的诀窍','NUM-2016-0033','2hsumiao.png',35,0),('34','李宗吾','厚黑学的描述','厚黑学','NUM-2016-0034','3ahouhei.png',57,0),('35','古斯塔夫','乌合之众的描述','乌合之众','NUM-2016-0035','3awuhe.png',60,0),('36','邹鸿名','性情心理学的描述','性情心理学','NUM-2016-0036','3axinli1.png',34,0),('37','查理的','心理学与生活的描述','心理学与生活','NUM-2016-0037','3axinli2.png',34,0),('38','人民日报','平天下的描述','平天下','NUM-2016-0038','3bpingtianxia.png',45,0),('39','亨利','世界秩序的描述','世界秩序','NUM-2016-0039','3bworldzhixu.png',46,0),('4','国际出版社','英汉百科的描述','英汉百科','NUM-2016-004','1ayinghan.png',86,0),('40','尤瓦尔','人类简史的描述','人类简史','NUM-2016-0040','3bpeoplehistory.png',57,0),('41','费孝通','乡土中国的描述','乡土中国','NUM-2016-0041','3bxiangtu.png',47,0),('42','阎明复','中苏关系的描述','中苏关系','NUM-2016-0042','3bzhongsu.png',55,0),('43','马东峰','中国史的描述','中国史','NUM-2016-0043','3bchinahistory.png',123,0),('44','王国轩','中庸大学的描述','中庸大学','NUM-2016-0044','3cdaxue.png',20,0),('45','万丽华','孟子的描述','孟子','NUM-2016-0045','3cmengzi.png',20,0),('46','饶尚宽','老子的描述','老子','NUM-2016-0046','3claozi.png',20,0),('47','万韬','山海经的描述','山海经','NUM-2016-0047','3cshanhaijing.png',20,0),('48','廖文远','战国策的描述','战国策','NUM-2016-0048','3czhanguoc.png',20,0),('49','叶晓红','小学数学的描述','小学数学','NUM-2016-0049','4xxshuxue2.png',15,0),('5','李仁发','英汉大词典的描述','英汉大词典','NUM-2016-005','1astudent.png',84,0),('50','龚勋','语文知识金库的描述','语文知识金库','NUM-2016-0050','4xxyuwen.png',25,0),('51','黄冈','满分作文的描述','满分作文','NUM-2016-0051','4xxzuowen.png',33,0),('52','小学英语语法','10的描述','10','NUM-2016-0052','4xxenglish.png',31,0),('53','朱文君','小学古文100课的描述','小学古文100课','NUM-2016-0053','4xxguwen.png',29,0),('54','广播电台数独组','小学数独的描述','小学数独','NUM-2016-0054','4xxshudu.png',22,0),('55','外文出版社','初中生阅读能力的描述','初中生阅读能力','NUM-2016-0055','4czyeudu.png',32,0),('56','黄嘉琪','公式定理大全的描述','公式定理大全','NUM-2016-0056','4czgongshi.png',35,0),('57','曲一线','数学知识清单的描述','数学知识清单','NUM-2016-0057','4czshuxue.png',29,0),('58','曲一线','语文知识清单的描述','语文知识清单','NUM-2016-0058','4czyuwen.png',29,0),('59','曲一线','地理知识清单的描述','地理知识清单','NUM-2016-0059','4czdili.png',29,0),('6','俞敏洪','四级词汇的描述','四级词汇','NUM-2016-006','1bfour.png',76,0),('60','曲一线','化学知识清单的描述','化学知识清单','NUM-2016-0060','4czhuaxue.png',29,0),('61','曲一线','历史知识清单的描述','历史知识清单','NUM-2016-0061','4czlishi.png',29,0),('62','曲一线','物理知识清单的描述','物理知识清单','NUM-2016-0062','4czwuli.png',29,0),('63','张艺','HTML的描述','HTML','NUM-2016-0063','5lhtml.png',30,0),('64','严蔚敏','数据结构的描述','数据结构','NUM-2016-0064','5lshugou.png',30,0),('65','谭浩强','C的描述','C++','NUM-2016-0065','5lc+.png',25,0),('66','谭浩强','C语言的描述','C语言','NUM-2016-0066','5lc.png',25,0),('67','明日科技','PHP的描述','PHP','NUM-2016-0067','5lphp.png',31,0),('68','开发联盟','C的描述','C#','NUM-2016-0068','5lcsharp.png',32,0),('69','ken','软件定义网络的描述','软件定义网络','NUM-2016-0069','5nsdn.png',78,0),('7','俞敏洪','六级词汇的描述','六级词汇','NUM-2016-007','1bsix.png',76,0),('70','崔北亮','CCNA的描述','CCNA','NUM-2016-0070','5nccna.png',56,0),('71','谢希仁','计算机网络的描述','计算机网络','NUM-2016-0071','5nnetwork.png',30,0),('72','思科','思科网络技术教程的描述','思科网络技术教程','NUM-2016-0072','5ncisco.png',89,0),('73','赖特','TCP/IP的描述','TCP/IPNUM-2016-0073','73','5ntcp.png',65,0),('74','理查德','UNIX的描述','UNIX','NUM-2016-0074','5osunix.png',56,0),('75','王世江','Linux的描述','Linux','NUM-2016-0075','5oslinux.png',54,0),('76','郭平','Windows的描述','Windows','NUM-2016-0076','5oswindow.png',49,0),('77','andrew','现代操作系统的描述','现代操作系统','NUM-2016-0077','5os.png',33,0),('78','张春晓','Shell的描述','Shell','NUM-2016-0078','5osshell.png',36,0),('79','石胁智广','你不懂咖啡的描述','你不懂咖啡','NUM-2016-0079','6ecoffee.png',44,0),('8','振宇英语','四级真题的描述','四级真题','NUM-2016-008','1bfourti.png',18,0),('80','姜倩','我爱面包机的描述','我爱面包机','NUM-2016-0080','6ebread.png',33,0),('81','双福','花式营养早餐的描述','花式营养早餐','NUM-2016-0081','6ezao.png',49,0),('82','双福','家常菜2888的描述','家常菜2888','NUM-2016-0082','6ecai.png',58,0),('83','贝太','清粥小菜的描述','清粥小菜','NUM-2016-0083','6ezhou.png',56,0),('84','刘哲非','面的描述','面','NUM-2016-0084','6enoodles.png',35,0),('85','planet','澳大利亚的描述','澳大利亚','NUM-2016-0085','6laustralia.png',88,0),('86','雷克','徒步中国的描述','徒步中国','NUM-2016-0086','6lchina.png',134,0),('87','lonely','带孩子旅行的描述','带孩子旅行','NUM-2016-0087','6lchild.png',99,0),('88','穷游网','发现最世界的描述','发现最世界','NUM-2016-0088','6lworld.png',170,0),('89','lonely','欧洲的描述','欧洲','NUM-2016-0089','6leurope.png',122,0),('9','王开虎','六级真题的描述','六级真题','NUM-2016-009','1bsixti.png',19,0),('90','莫仙','花艺素材的描述','花艺素材','NUM-2016-0090','6zhua.png',66,0),('91','藤田智','在阳台上种菜的描述','在阳台上种菜','NUM-2016-0091','6zyang.png',67,0),('92','宅','装修施工书的描述','装修施工书','NUM-2016-0092','6zxiu.png',57,0),('93','宅','家居色彩设计指南的描述','家居色彩设计指南','NUM-2016-0093','6zjiaju.png',58,0),('94','乔磊','家装一本通的描述','家装一本通','NUM-2016-0094','6zzhuangxiu.png',55,0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` varchar(36) NOT NULL,
  `cart` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('5ce52119-b144-4989-b96d-fe36574a9bb1','11:1;10:1;12:1;','2016-04-09 23:07:57','test-user-id'),('aba42f92-3e31-42c3-b984-0ef2650777f3','11:1;10:1;12:1;','2016-04-09 20:32:08','test-user-id'),('c4838422-c275-4754-aa98-c935ab220e8c','11:1;10:1;12:1;','2016-04-09 20:31:36','test-user-id'),('d05f6eef-1c33-4077-931e-6ac7866f6de4','17:1;2:5;','2016-04-09 20:32:24','test-user-id'),('ef7fb2d1-49de-49b6-b2a9-f326d61c76e3','2:1;19:1;20:1;','2016-04-09 23:08:27','test-user-id');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(36) NOT NULL,
  `cart` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('test-user-id','7:1;8:5;9:1;','admin','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-08 19:25:32
