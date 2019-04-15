/*
Navicat MySQL Data Transfer

Source Server         : bysj
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : dabao

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2018-04-21 12:57:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `login` varchar(50) default '',
  `pass` varchar(50) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '111111');

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `ID` int(11) NOT NULL auto_increment,
  `Title` varchar(50) default '',
  `InTime` datetime default NULL,
  `Content` text,
  `BlogType_ID` int(11) NOT NULL default '0',
  PRIMARY KEY  (`ID`),
  KEY `BlogType_ID` (`BlogType_ID`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`BlogType_ID`) REFERENCES `blogtype` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for `blogtype`
-- ----------------------------
DROP TABLE IF EXISTS `blogtype`;
CREATE TABLE `blogtype` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(50) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogtype
-- ----------------------------
INSERT INTO `blogtype` VALUES ('1', '家政动态');
INSERT INTO `blogtype` VALUES ('2', '家政知识');

-- ----------------------------
-- Table structure for `board`
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `ID` int(11) NOT NULL auto_increment,
  `Client_ID` int(11) default '0',
  `Title` varchar(50) default '',
  `Content` text,
  `intime` datetime default NULL,
  PRIMARY KEY  (`ID`),
  KEY `Client_ID` (`Client_ID`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`Client_ID`) REFERENCES `client` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of board
-- ----------------------------

-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `ID` int(11) NOT NULL auto_increment,
  `Type` varchar(50) default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '朝阳区');
INSERT INTO `city` VALUES ('2', '南关区');
INSERT INTO `city` VALUES ('3', '宽城区');
INSERT INTO `city` VALUES ('4', '二道区');
INSERT INTO `city` VALUES ('5', '绿园区');
INSERT INTO `city` VALUES ('6', '双阳区');
INSERT INTO `city` VALUES ('7', '测试区');

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `ID` int(11) NOT NULL auto_increment,
  `name` varchar(50) default '',
  `sex` varchar(50) default '',
  `age` int(11) default '0',
  `tel` varchar(50) default '',
  `address` varchar(50) default '',
  `mail` varchar(50) default '',
  `xueli` varchar(50) default '',
  `login` varchar(50) default '',
  `pass` varchar(50) default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------

-- ----------------------------
-- Table structure for `sysdiagrams`
-- ----------------------------
DROP TABLE IF EXISTS `sysdiagrams`;
CREATE TABLE `sysdiagrams` (
  `name` varchar(128) NOT NULL default '',
  `principal_id` int(11) NOT NULL default '0',
  `diagram_id` int(11) NOT NULL auto_increment,
  `version` int(11) default '0',
  `definition` text,
  PRIMARY KEY  (`diagram_id`),
  KEY `UK_principal_name` (`principal_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysdiagrams
-- ----------------------------

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `ID` int(11) NOT NULL auto_increment,
  `Name` varchar(50) default '',
  `Age` int(11) default '0',
  `xueli` varchar(50) default '',
  `Tel` varchar(50) default '',
  `ZhuanYe_ID` int(11) default '0',
  `Content` text,
  `Img` varchar(500) default '',
  `City_ID` int(11) default '0',
  `pass` varchar(50) default '',
  `login` varchar(50) default '',
  `state` int(11) default '0',
  PRIMARY KEY  (`ID`),
  KEY `ZhuanYe_ID` (`ZhuanYe_ID`),
  KEY `City_ID` (`City_ID`),
  CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`City_ID`) REFERENCES `city` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`ZhuanYe_ID`) REFERENCES `zhuanye` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '谢兵', '38', '大专', '13057509390', '1', '2008年薛迅老师以无锡市青歌赛第一名的身份获得参加省总工会、省文联联合举办的第三届江苏省职工歌手大赛的资格，她以纯朴、甜美、清亮、娴熟的演唱和优雅大方的舞台形象夺得本次大赛民族组最高分，荣获金奖。无锡市总工会锡工发[2008]69号文件特授于薛迅老师“无锡市五一劳动奖章”。\r\n薛迅是江阴市教师进修学校艺术组的一名青年教师，她教授的课程有声乐、钢琴和视唱等专业。她潜心学习，勤于研究、乐于教学，深受全日制班学生和各类培训班学员的欢迎和好评。她以良好的教学水平荣获了江阴市青年教师基本功比赛一等奖和“江阴市教学新秀”称号。她担任了江阴市第一、二两期小学音乐教师轮训班班主任，她与学员的PK课也受到好评。她撰写的教学论文获省教育学会的奖励，她在江阴市、无锡市的师陶演讲赛中都勇夺桂冠。她还荣获江阴市新长征突击手、无锡市优秀团员、民盟江阴市先进个人等光荣称号，仅2008年一年中，她就获得各类奖状12张。\r\n薛迅老师把艺术当作生命，把艺术作为贡献，把艺术作为服务社会的力量。她每年都会主动无偿参加社会公益演出活动，社区、部队、企业留下了她美妙的歌声，江阴春晚、市纪委廉政晚会等大型宣传活动都有她高雅的艺术身影。\r\n在薛迅老师辛勤的耕耘途中，她获得了江苏省五星工程奖金奖，全国群星奖，江阴市人民政府颁发的“江阴市文学艺术奖评奖活动表演三等奖”证书，还荣获了江阴团市委、青联授予的“江阴市十佳青年歌手”称号。她作词作曲的《彩虹梦》发表于中国音乐家协会主办的《歌曲》杂志上，在上海、江苏、浙江、福建省文化厅联合举办的第三届长三角（华东地区）青年歌手大奖赛上，作为唯一的非专业选手她以抒情而豪迈的歌声，娴熟而精湛的表演征服了评委，获得了银奖。\r\n她就是这样一个勤于耕耘、乐于奉献的青年音乐教师。', 'file/11.jpg', '1', '111111', '001', '0');
INSERT INTO `teacher` VALUES ('2', '李万根', '28', '大专', '13057509390', '4', '2008年薛迅老师以无锡市青歌赛第一名的身份获得参加省总工会、省文联联合举办的第三届江苏省职工歌手大赛的资格，她以纯朴、甜美、清亮、娴熟的演唱和优雅大方的舞台形象夺得本次大赛民族组最高分，荣获金奖。无锡市总工会锡工发[2008]69号文件特授于薛迅老师“无锡市五一劳动奖章”。\r\n薛迅是江阴市教师进修学校艺术组的一名青年教师，她教授的课程有声乐、钢琴和视唱等专业。她潜心学习，勤于研究、乐于教学，深受全日制班学生和各类培训班学员的欢迎和好评。她以良好的教学水平荣获了江阴市青年教师基本功比赛一等奖和“江阴市教学新秀”称号。她担任了江阴市第一、二两期小学音乐教师轮训班班主任，她与学员的PK课也受到好评。她撰写的教学论文获省教育学会的奖励，她在江阴市、无锡市的师陶演讲赛中都勇夺桂冠。她还荣获江阴市新长征突击手、无锡市优秀团员、民盟江阴市先进个人等光荣称号，仅2008年一年中，她就获得各类奖状12张。\r\n薛迅老师把艺术当作生命，把艺术作为贡献，把艺术作为服务社会的力量。她每年都会主动无偿参加社会公益演出活动，社区、部队、企业留下了她美妙的歌声，江阴春晚、市纪委廉政晚会等大型宣传活动都有她高雅的艺术身影。\r\n在薛迅老师辛勤的耕耘途中，她获得了江苏省五星工程奖金奖，全国群星奖，江阴市人民政府颁发的“江阴市文学艺术奖评奖活动表演三等奖”证书，还荣获了江阴团市委、青联授予的“江阴市十佳青年歌手”称号。她作词作曲的《彩虹梦》发表于中国音乐家协会主办的《歌曲》杂志上，在上海、江苏、浙江、福建省文化厅联合举办的第三届长三角（华东地区）青年歌手大奖赛上，作为唯一的非专业选手她以抒情而豪迈的歌声，娴熟而精湛的表演征服了评委，获得了银奖。\r\n她就是这样一个勤于耕耘、乐于奉献的青年音乐教师。', 'file/22.jpg', '1', '11111', '002', '0');
INSERT INTO `teacher` VALUES ('10000', '泪水', '22', '本科', '123123', '1', 'testtesttest', 'file/1.jpeg', '1', '111111', 'test', '1');
INSERT INTO `teacher` VALUES ('10001', '王刚', '35', '博士', '13057509394', '2', '无内容', 'file/bb.jpg', '2', '111111', 'test001', '0');

-- ----------------------------
-- Table structure for `yuyue`
-- ----------------------------
DROP TABLE IF EXISTS `yuyue`;
CREATE TABLE `yuyue` (
  `ID` int(11) NOT NULL auto_increment,
  `Client_ID` int(11) default '0',
  `Date` varchar(50) default '',
  `Time` varchar(50) default '',
  `BeiZhu` varchar(2000) default '',
  `Teacher_ID` int(11) default '0',
  `Grade` varchar(50) default '',
  `state` int(11) default '0',
  PRIMARY KEY  (`ID`),
  KEY `Client_ID` (`Client_ID`),
  KEY `Teacher_ID` (`Teacher_ID`),
  CONSTRAINT `yuyue_ibfk_2` FOREIGN KEY (`Teacher_ID`) REFERENCES `teacher` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `yuyue_ibfk_1` FOREIGN KEY (`Client_ID`) REFERENCES `client` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yuyue
-- ----------------------------

-- ----------------------------
-- Table structure for `zhuanye`
-- ----------------------------
DROP TABLE IF EXISTS `zhuanye`;
CREATE TABLE `zhuanye` (
  `ID` int(11) NOT NULL auto_increment,
  `Type` varchar(50) default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhuanye
-- ----------------------------
INSERT INTO `zhuanye` VALUES ('1', '婴幼儿语言训练');
INSERT INTO `zhuanye` VALUES ('2', '小学数学');
INSERT INTO `zhuanye` VALUES ('3', '小学英语');
INSERT INTO `zhuanye` VALUES ('4', '初中代数');
