/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : db_hrs_hzc

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-06-09 20:27:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_apply_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_apply_hzc`;
CREATE TABLE `t_apply_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenantId` int(11) NOT NULL,
  `houseId` int(11) NOT NULL,
  `landlordId` int(11) NOT NULL,
  `offerPrice` decimal(10,2) NOT NULL,
  `leaveMessage` varchar(255) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `flag` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_apply_house_house_id` (`tenantId`) USING BTREE,
  KEY `fk_apply_user_tenant_id` (`houseId`),
  KEY `fk_apply_user_landlord_id` (`landlordId`),
  CONSTRAINT `fk_apply_house_house_id` FOREIGN KEY (`houseId`) REFERENCES `t_house_hzc` (`id`),
  CONSTRAINT `fk_apply_user_landlord_id` FOREIGN KEY (`landlordId`) REFERENCES `t_user_hzc` (`id`),
  CONSTRAINT `fk_apply_user_tenant_id` FOREIGN KEY (`tenantId`) REFERENCES `t_user_hzc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_apply_hzc
-- ----------------------------
INSERT INTO `t_apply_hzc` VALUES ('17', '3', '156', '2', '9000.00', '我是土豪', '2020-06-06 23:34:00', '已接受');
INSERT INTO `t_apply_hzc` VALUES ('18', '12', '156', '2', '7000.00', '我是何志成', '2020-06-09 17:14:12', '已接受');

-- ----------------------------
-- Table structure for t_comment_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_comment_hzc`;
CREATE TABLE `t_comment_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenantId` int(11) NOT NULL,
  `landlordId` int(11) NOT NULL,
  `contractId` int(11) NOT NULL,
  `houseId` int(11) NOT NULL,
  `tenantContentCreateTime` datetime DEFAULT NULL,
  `landlordContentCreateTime` datetime DEFAULT NULL,
  `houseContentCreateTime` datetime DEFAULT NULL,
  `tenantContent` varchar(255) DEFAULT NULL,
  `landlordContent` varchar(255) DEFAULT NULL,
  `houseContent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_house_house_id` (`houseId`),
  KEY `fk_comment_user_landlord_id` (`landlordId`),
  KEY `fk_comment_user_tenant_id` (`tenantId`),
  CONSTRAINT `fk_comment_house_house_id` FOREIGN KEY (`houseId`) REFERENCES `t_house_hzc` (`id`),
  CONSTRAINT `fk_comment_user_landlord_id` FOREIGN KEY (`landlordId`) REFERENCES `t_user_hzc` (`id`),
  CONSTRAINT `fk_comment_user_tenant_id` FOREIGN KEY (`tenantId`) REFERENCES `t_user_hzc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment_hzc
-- ----------------------------
INSERT INTO `t_comment_hzc` VALUES ('7', '3', '2', '20', '156', '2020-06-07 00:53:24', '2020-06-07 00:52:35', '2020-06-07 00:53:24', '还不错', '还可以吧', '非常好');
INSERT INTO `t_comment_hzc` VALUES ('8', '12', '2', '21', '156', '2020-06-09 19:16:30', '2020-06-09 19:42:33', '2020-06-09 19:16:30', '一般般吧', '一般般吧', '房子很不错');

-- ----------------------------
-- Table structure for t_community_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_community_hzc`;
CREATE TABLE `t_community_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `communityIntroduction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community_hzc
-- ----------------------------
INSERT INTO `t_community_hzc` VALUES ('1', '瑞景河畔', '长沙瑞景河畔', '魅力瑞景河畔');
INSERT INTO `t_community_hzc` VALUES ('2', '蔚蓝小区', '衡阳蔚蓝小区', '溪河蔚蓝小区');
INSERT INTO `t_community_hzc` VALUES ('3', '和盛园小区', '湘潭和盛园小区', '钱多和盛园小区');
INSERT INTO `t_community_hzc` VALUES ('6', '新月小区', '湖南省岳阳市xx区xx街道', '一个豪华的小区');
INSERT INTO `t_community_hzc` VALUES ('7', '幸福小区', '湖南省衡阳市蒸湘区联合街道', '非常的幸运非常的幸福');
INSERT INTO `t_community_hzc` VALUES ('8', '学士小区', '湖南省长沙市岳麓区学士街道', '一个非常安全的小区');
INSERT INTO `t_community_hzc` VALUES ('9', '雅丽小区', '湖南省衡阳市蒸湘区红湘街道', '一个非常漂亮的小区');

-- ----------------------------
-- Table structure for t_contract_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_contract_hzc`;
CREATE TABLE `t_contract_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applyId` int(11) NOT NULL,
  `tenantId` int(11) NOT NULL,
  `landlordId` int(11) NOT NULL,
  `houseId` int(11) NOT NULL,
  `contractContent` varchar(255) DEFAULT NULL,
  `contractCreateTime` datetime NOT NULL,
  `contractEndTime` datetime NOT NULL,
  `approveFromTenant` varchar(10) NOT NULL,
  `approveFromLandlord` varchar(10) NOT NULL,
  `flag` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `fk_contract_house_house_id` (`houseId`),
  KEY `fk_contract_user_landlord_id` (`landlordId`),
  KEY `fk_contract_user_tenant_id` (`tenantId`),
  KEY `fk_contract_apply_apply_id` (`applyId`),
  CONSTRAINT `fk_contract_apply_apply_id` FOREIGN KEY (`applyId`) REFERENCES `t_apply_hzc` (`id`),
  CONSTRAINT `fk_contract_house_house_id` FOREIGN KEY (`houseId`) REFERENCES `t_house_hzc` (`id`),
  CONSTRAINT `fk_contract_user_landlord_id` FOREIGN KEY (`landlordId`) REFERENCES `t_user_hzc` (`id`),
  CONSTRAINT `fk_contract_user_tenant_id` FOREIGN KEY (`tenantId`) REFERENCES `t_user_hzc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_contract_hzc
-- ----------------------------
INSERT INTO `t_contract_hzc` VALUES ('20', '17', '3', '2', '156', '约法三章', '2020-06-06 23:34:34', '2020-06-07 00:34:00', '已确认', '已确认', '已完成');
INSERT INTO `t_contract_hzc` VALUES ('21', '18', '12', '2', '156', '合同细则:1 ... 2.... 3...', '2020-06-09 17:21:18', '2020-06-08 19:07:00', '已确认', '已确认', '已完成');

-- ----------------------------
-- Table structure for t_deposit_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_deposit_hzc`;
CREATE TABLE `t_deposit_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `administratorId` int(11) NOT NULL COMMENT '管理员Id',
  `tenantDeposit` decimal(18,2) NOT NULL COMMENT '押金',
  `tenantId` int(11) NOT NULL COMMENT '房东Id',
  `landlordId` int(11) NOT NULL,
  `landlordDeposit` decimal(18,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_deposit_user_tenant_id` (`tenantId`),
  KEY `fk_deposit_user_landlord_id` (`landlordId`),
  KEY `fk_deposit_user_administrator_id` (`administratorId`),
  CONSTRAINT `fk_deposit_user_administrator_id` FOREIGN KEY (`administratorId`) REFERENCES `t_user_hzc` (`id`),
  CONSTRAINT `fk_deposit_user_landlord_id` FOREIGN KEY (`landlordId`) REFERENCES `t_user_hzc` (`id`),
  CONSTRAINT `fk_deposit_user_tenant_id` FOREIGN KEY (`tenantId`) REFERENCES `t_user_hzc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_deposit_hzc
-- ----------------------------
INSERT INTO `t_deposit_hzc` VALUES ('3', '5', '7000.15', '2', '3', '500.74');
INSERT INTO `t_deposit_hzc` VALUES ('4', '14', '4000.00', '13', '12', '200.00');

-- ----------------------------
-- Table structure for t_house_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_house_hzc`;
CREATE TABLE `t_house_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `communityId` int(11) DEFAULT NULL,
  `landlordId` int(11) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `houseIntroduction` varchar(255) DEFAULT NULL,
  `flag` varchar(20) DEFAULT NULL,
  `houseAddress` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `unitType` varchar(20) DEFAULT NULL,
  `monthlyRent` decimal(18,2) DEFAULT NULL,
  `area` double(20,0) DEFAULT NULL,
  `lastEditTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_house_community_community_id` (`communityId`) USING BTREE,
  KEY `fk_house_user_landlord_id` (`landlordId`) USING BTREE,
  CONSTRAINT `fk_house_community_community_id` FOREIGN KEY (`communityId`) REFERENCES `t_community_hzc` (`id`),
  CONSTRAINT `fk_house_user_landlord_id` FOREIGN KEY (`landlordId`) REFERENCES `t_user_hzc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5563 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_house_hzc
-- ----------------------------
INSERT INTO `t_house_hzc` VALUES ('1', '2', '2', '瑞景河畔 二期房01', '美丽瑞景河畔 欢迎您', '已配租', '瑞景河畔16号楼6-161', '/HRS/images/house1.png', '一室一厅一卫', '2500.00', '65', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('2', '3', '2', '瑞景河畔 二期房02', '美丽瑞景河畔 欢迎您', '待出租', '瑞景河畔16号楼6-162', '/HRS/images/house3.png', '二室一厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('7', '2', '2', '瑞景河畔 二期房04', '待出租', '待出租', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('8', '1', '2', '瑞景河畔 二期房05', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house2.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('10', '2', '2', '瑞景河畔 二期房06', '待出租', '未发布', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('11', '2', '2', '瑞景河畔 二期房07', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house3.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('12', '2', '2', '瑞景河畔 二期房08', '待出租', '未发布', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('13', '1', '2', '瑞景河畔 二期房09', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house2.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('14', '2', '2', '瑞景河畔 二期房10', '待出租', '未发布', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('15', '2', '2', '瑞景河畔 二期房11', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house3.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('16', '2', '2', '瑞景河畔 二期房12', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house1.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('55', '2', '2', '瑞景河畔 二期房13', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house2.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('77', '2', '2', '瑞景河畔 二期房14', '待出租', '未发布', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('155', '2', '2', '瑞景河畔 二期房15', '待出租', '未发布', '瑞景河畔16号楼6-151', '/HRS/images/house1.png', '二室二厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('156', '2', '2', '瑞景河畔 二期房16', '待出租', '待出租', '瑞景河畔16号楼6-160', '/HRS/images/house2.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('157', '2', '2', '瑞景河畔 二期房01', '待出租', '待出租', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('220', '1', '2', '瑞景河畔 一期房01', '美丽瑞景河畔 欢迎您', '未发布', '瑞景河畔16号楼6-161', '/HRS/images/house3.png', '一室一厅一卫', '2500.00', '65', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5545', '2', '2', '瑞景河畔 一期房02', '美丽瑞景河畔 欢迎您', '待出租', '瑞景河畔16号楼6-162', '/HRS/images/house1.png', '二室一厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5546', '2', '2', '瑞景河畔 一期房03', '待出租', '待出租', '瑞景河畔16号楼6-160', '/HRS/images/house2.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5547', '2', '2', '瑞景河畔 一期房04', '待出租', '待出租', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5548', '1', '2', '瑞景河畔 一期房05', '美丽瑞景河畔 欢迎您', '已配租', '瑞景河畔16号楼6-161', '/HRS/images/house3.png', '一室一厅一卫', '2500.00', '65', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5549', '2', '2', '瑞景河畔 一期房06', '美丽瑞景河畔 欢迎您', '已配租', '瑞景河畔16号楼6-162', '/HRS/images/house1.png', '二室一厅一卫', '3000.00', '80', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5550', '2', '2', '瑞景河畔 一期房07', '待出租', '待出租', '瑞景河畔16号楼6-160', '/HRS/images/house1.png', '一室一厅一卫', '2000.00', '75', '2020-06-09 19:26:15');
INSERT INTO `t_house_hzc` VALUES ('5558', '6', '2', 'xxx 新月 1号', '非常好房子', '未发布', '123121', '/HRS/images/house1.png', '二室一厅三卫', '9000.00', '80', '2020-06-09 10:23:25');
INSERT INTO `t_house_hzc` VALUES ('5560', '3', '18', '瑞景河畔 一期99', '河风吹入你的家', '未发布', '5栋9单元705室', '/HRS/images/house1.png', '三室二厅', '6000.00', '90', '2020-06-09 20:20:56');
INSERT INTO `t_house_hzc` VALUES ('5561', '2', '16', '蔚蓝小区 一期99', '美丽的大海就在对面', '未发布', '2栋6单元 101室', '/HRS/images/house1.png', '二室二厅', '5000.00', '70', '2020-06-09 20:20:55');
INSERT INTO `t_house_hzc` VALUES ('5562', '3', '17', '和盛园小区 一期99', '草和花的美丽屋', '未发布', '13栋9单元801室', '/HRS/images/house1.png', '一室一厅', '4000.00', '50', '2020-06-09 20:20:56');

-- ----------------------------
-- Table structure for t_logger_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_logger_hzc`;
CREATE TABLE `t_logger_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `methodName` varchar(255) DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  `errorMessage` varchar(255) DEFAULT NULL,
  `arg` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_logger_hzc
-- ----------------------------
INSERT INTO `t_logger_hzc` VALUES ('1', 'login', 'org.hzc.service.SecurityServiceImpl', '用户角色验证失败', 'class java.lang.Stringclass java.lang.Stringclass org.hzc.entity.Role', '2020-06-08 20:12:33');
INSERT INTO `t_logger_hzc` VALUES ('2', 'login', 'org.hzc.service.SecurityServiceImpl', '用户角色验证失败', 'class java.lang.Stringclass java.lang.Stringclass org.hzc.entity.Role', '2020-06-08 20:14:44');
INSERT INTO `t_logger_hzc` VALUES ('3', 'login', 'org.hzc.service.SecurityServiceImpl', '用户角色验证失败', 'class java.lang.Stringclass java.lang.Stringclass org.hzc.entity.Role', '2020-06-09 09:49:03');
INSERT INTO `t_logger_hzc` VALUES ('6', 'login', 'org.hzc.service.SecurityServiceImpl', '密码不匹配', 'class java.lang.Stringclass java.lang.Stringclass org.hzc.entity.Role', '2020-06-09 17:15:56');
INSERT INTO `t_logger_hzc` VALUES ('7', 'login', 'org.hzc.service.SecurityServiceImpl', '密码不匹配', 'class java.lang.Stringclass java.lang.Stringclass org.hzc.entity.Role', '2020-06-09 19:34:50');

-- ----------------------------
-- Table structure for t_user_hzc
-- ----------------------------
DROP TABLE IF EXISTS `t_user_hzc`;
CREATE TABLE `t_user_hzc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `pwdHash` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`,`role`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_hzc
-- ----------------------------
INSERT INTO `t_user_hzc` VALUES ('2', '斯大林', '18397424120', '$2a$10$uadz4ZyWV/yHn81U6sKtc.hQYdXL84RNy/ZrOTUgJ2yLSDGoULD8C', 'LANDLORD');
INSERT INTO `t_user_hzc` VALUES ('3', '蒋介石', '18397424125', '$2a$10$UKrtbPAIwJriBeylaK9xSOILBt/eD9QVXJaYv3VUwCigu.c1f.vgS', 'TENANT');
INSERT INTO `t_user_hzc` VALUES ('5', 'hzc', '13574777381', '$2a$10$MyMOp2jrB0sR/QixAnmlaeJXxUQUYiUYu4yvPqifEF8a207aW3qa.', 'ADMINISTRATOR');
INSERT INTO `t_user_hzc` VALUES ('12', 'hezhicheng', '13574777820', '$2a$10$eBTQIBWpozVNjyjkb4xsru2SbVR7cuTUncHBC38sUuMqXEgpq9OYy', 'TENANT');
INSERT INTO `t_user_hzc` VALUES ('13', 'cs20180509hzc', '13574777899', '$2a$10$QU.H7XE0NYh56jq5ooWAfufmLBafn7QIJekAK.kMrCg9ZH8QhJqCy', 'LANDLORD');
INSERT INTO `t_user_hzc` VALUES ('14', 'administeredHzc', '18397424121', '$2a$10$uPgZT7pYB.99dARptK/Wcupzw2u.flR/6piTuiWRTdlj7vQYLTLyy', 'ADMINISTRATOR');
INSERT INTO `t_user_hzc` VALUES ('15', '张三', '13574777301', '$2a$10$O3gDI7.ftgH7GoatGGinkuPQdpi9HbfPn4hNtIjOIVzkxBrg3WmiG', 'LANDLORD');
INSERT INTO `t_user_hzc` VALUES ('16', '李四', '13574777302', '$2a$10$J1/XPaVpFNaYvfWVrgb2DOmhjZ2.OZcIhVeMBbxKRom/kJz6z2qH2', 'LANDLORD');
INSERT INTO `t_user_hzc` VALUES ('17', '王五', '13574777303', '$2a$10$wpwv4nOOHIovlOXjuaRo5ejfVBzukINRShVqijg0UcLA1M2.Xjiku', 'LANDLORD');
INSERT INTO `t_user_hzc` VALUES ('18', '许六', '13574777304', '$2a$10$kORoa1XCjybb.cgvO0Kk8OtFYWaIC0BKRNJkoVPmxRg.n/3EE08gO', 'LANDLORD');
