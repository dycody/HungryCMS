/*
Navicat MySQL Data Transfer

Source Server         : 192.168.5.129_3306
Source Server Version : 50720
Source Host           : 192.168.5.129:3306
Source Database       : hungry

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-06 07:07:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `u_type` tinyint(4) DEFAULT NULL COMMENT '0-admin 1-mergant 2-courier 3-customer',
  `REMOVED` char(1) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('14', 'user10', 'd964173dc44da83eeafa3aebbee9a1a0', '3', '0', '2018-06-30 20:48:50', '2018-06-30 20:48:50');
INSERT INTO `account` VALUES ('16', 'user22', '123456', '3', '0', '2018-06-29 15:58:05', '2018-06-29 15:58:05');
INSERT INTO `account` VALUES ('17', 'user20', 'd964173dc44da83eeafa3aebbee9a1a0', '3', '0', '2018-06-30 20:50:40', '2018-06-30 20:50:40');
INSERT INTO `account` VALUES ('18', 'user20', '123456', '3', '1', '2018-06-29 16:56:09', '2018-06-29 16:56:09');
INSERT INTO `account` VALUES ('19', 'user21', '123456', '3', '1', '2018-06-29 17:05:39', '2018-06-29 17:05:39');
INSERT INTO `account` VALUES ('20', 'user12', 'd964173dc44da83eeafa3aebbee9a1a0', '3', '1', '2018-07-01 00:10:23', '2018-07-01 00:10:23');
INSERT INTO `account` VALUES ('30', 'courier20', '123456', '2', '1', '2018-06-30 20:17:18', '2018-06-30 20:17:18');
INSERT INTO `account` VALUES ('31', 'courier20', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2018-06-30 23:26:16', '2018-06-30 23:26:16');
INSERT INTO `account` VALUES ('32', 'user23', 'd964173dc44da83eeafa3aebbee9a1a0', '3', '1', '2018-07-01 00:10:36', '2018-07-01 00:10:36');
INSERT INTO `account` VALUES ('36', 'mergant21', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '2018-07-05 12:26:59', '2018-07-05 12:26:59');
INSERT INTO `account` VALUES ('37', 'qiaozhuzhu', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '2018-07-01 00:09:19', '2018-07-01 00:09:19');
INSERT INTO `account` VALUES ('39', 'courier40', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2018-07-05 22:16:44', '2018-07-05 22:16:44');
INSERT INTO `account` VALUES ('40', 'niubiqishou', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2018-07-05 22:45:04', '2018-07-05 22:45:04');
INSERT INTO `account` VALUES ('41', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', '2018-07-06 01:39:32', '2018-07-06 01:39:32');
INSERT INTO `account` VALUES ('42', 'xiaoyonghu', 'e10adc3949ba59abbe56e057f20f883e', '3', '0', '2018-07-06 02:35:16', '2018-07-06 02:35:16');

-- ----------------------------
-- Table structure for `courier`
-- ----------------------------
DROP TABLE IF EXISTS `courier`;
CREATE TABLE `courier` (
  `u_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` int(10) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courier
-- ----------------------------
INSERT INTO `courier` VALUES ('30', '5', '45456565632', '王二狗', '2018-06-30 19:54:09', '2018-06-30 19:54:09');
INSERT INTO `courier` VALUES ('31', '1', '13056567878', 'abcc', null, null);
INSERT INTO `courier` VALUES ('39', '2', '13712345123', 'cour', null, null);
INSERT INTO `courier` VALUES ('40', '1', '13712345123', '小骑手', null, null);

-- ----------------------------
-- Table structure for `food`
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `inf` text,
  `yuan_price` double DEFAULT NULL,
  `cut_price` double DEFAULT NULL,
  `REMOVED` char(1) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `picture` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '36', '麻辣鱼', '123455', '20', '10', '0', '2018-07-03 20:44:22', '2018-07-03 20:44:22', null);
INSERT INTO `food` VALUES ('3', '36', 'ajkjlk', 'asdff', '15.2', '10.2', '0', '2018-07-03 22:23:08', '2018-07-03 22:23:08', null);

-- ----------------------------
-- Table structure for `mergant`
-- ----------------------------
DROP TABLE IF EXISTS `mergant`;
CREATE TABLE `mergant` (
  `opentime` time DEFAULT NULL COMMENT '1 --- 8',
  `closetime` time DEFAULT NULL,
  `r_id` int(10) DEFAULT NULL,
  `type` enum('披萨','炒菜','麻辣','鲜花','大牌','医药','果蔬','菜超','美食','晚餐') DEFAULT NULL,
  `boss_name` varchar(10) DEFAULT NULL,
  `boss_tel` varchar(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `u_id` bigint(20) NOT NULL,
  `m_name` varchar(10) DEFAULT NULL,
  `m_tel` varchar(11) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `min_price` double DEFAULT '0',
  `add_price` double DEFAULT '0',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mergant
-- ----------------------------
INSERT INTO `mergant` VALUES ('07:00:00', '09:15:00', '1', '果蔬', '嘤嘤嘤', '13056567878', '2018-07-06 04:32:08', '2018-07-06 04:32:08', '36', '洪新东北菜', '13056567878', '复兴路27号', '25', '1');

-- ----------------------------
-- Table structure for `order_inf`
-- ----------------------------
DROP TABLE IF EXISTS `order_inf`;
CREATE TABLE `order_inf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_inf
-- ----------------------------
INSERT INTO `order_inf` VALUES ('1', '麻辣鱼', '10', '1', '1');
INSERT INTO `order_inf` VALUES ('2', 'ajkjlk', '10.2', '4', '1');
INSERT INTO `order_inf` VALUES ('3', '麻辣鱼', '10', '1', '1');
INSERT INTO `order_inf` VALUES ('4', 'ajkjlk', '10.2', '2', '1');
INSERT INTO `order_inf` VALUES ('5', 'ajkjlk', '10.2', '3', '1');
INSERT INTO `order_inf` VALUES ('6', '麻辣鱼', '10', '2', null);
INSERT INTO `order_inf` VALUES ('7', 'ajkjlk', '10.2', '3', null);
INSERT INTO `order_inf` VALUES ('8', '麻辣鱼', '10', '5', null);
INSERT INTO `order_inf` VALUES ('9', 'ajkjlk', '10.2', '2', null);
INSERT INTO `order_inf` VALUES ('10', '麻辣鱼', '10', '1', null);
INSERT INTO `order_inf` VALUES ('11', 'ajkjlk', '10.2', '2', null);
INSERT INTO `order_inf` VALUES ('12', '麻辣鱼', '10', '1', '9');
INSERT INTO `order_inf` VALUES ('13', 'ajkjlk', '10.2', '3', '9');

-- ----------------------------
-- Table structure for `orderu`
-- ----------------------------
DROP TABLE IF EXISTS `orderu`;
CREATE TABLE `orderu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `courier_id` bigint(20) DEFAULT NULL,
  `mergant_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `add_price` double DEFAULT NULL,
  `real_price` double DEFAULT NULL,
  `rec_name` varchar(20) DEFAULT NULL,
  `rec_tel` varchar(20) DEFAULT NULL,
  `location_from` varchar(100) DEFAULT NULL,
  `location_to` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REMOVED` char(1) DEFAULT '0',
  `REACH` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderu
-- ----------------------------
INSERT INTO `orderu` VALUES ('1', '31', '36', '14', '23', '32', '王二狗', '13056564545', '111', 'asdfsf', '2018-07-06 01:40:44', '2018-07-06 01:40:44', '0', '2');
INSERT INTO `orderu` VALUES ('2', '31', '36', '14', '11', '11', '王二狗', '13056564545', '2', '12', '2018-07-06 04:16:01', '2018-07-06 04:16:01', '0', '0');
INSERT INTO `orderu` VALUES ('3', '40', '36', '42', '1', '51.8', '123', '13712345123', '复兴路27号', '123', null, null, null, null);
INSERT INTO `orderu` VALUES ('4', '40', '36', '42', '1', '31.4', '王亲亲', '13712345123', '复兴路27号', '11', null, null, null, null);
INSERT INTO `orderu` VALUES ('5', '31', '36', '42', '1', '31.6', '111', '13712345123', '复兴路27号', '1', '2018-07-06 07:03:50', '2018-07-06 07:03:50', '0', '2');
INSERT INTO `orderu` VALUES ('6', '40', '36', '42', '1', '51.6', '12312', '13142425111', '复兴路27号', '1113', '2018-07-06 06:43:20', '2018-07-06 06:43:20', '0', '1');
INSERT INTO `orderu` VALUES ('7', '31', '36', '42', '1', '71.4', '2323', '13223123421', '复兴路27号', '123', '2018-07-06 06:44:23', '2018-07-06 06:44:23', '0', '0');
INSERT INTO `orderu` VALUES ('8', '40', '36', '42', '1', '31.4', '123', '13212451231', '复兴路27号', '123', '2018-07-06 06:44:21', '2018-07-06 06:44:21', '0', '0');
INSERT INTO `orderu` VALUES ('9', '31', '36', '42', '1', '41.6', '113', '13512345123', '复兴路27号', '12', '2018-07-06 05:42:38', '2018-07-06 05:42:38', '0', '0');

-- ----------------------------
-- Table structure for `region`
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `id` int(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES ('1', '迎泽区');
INSERT INTO `region` VALUES ('2', '小店区');
INSERT INTO `region` VALUES ('3', '万柏林区');
INSERT INTO `region` VALUES ('4', '晋源区');
INSERT INTO `region` VALUES ('5', '尖草坪区');
INSERT INTO `region` VALUES ('6', '杏花岭区');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` bigint(20) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', '张三', '4545645', '13845454545', '2018-06-29 15:56:32', '2018-06-29 15:56:32');
INSERT INTO `user` VALUES ('15', '测试用户', '454545', '13045454545', '2018-06-29 15:57:15', '2018-06-29 15:57:15');
INSERT INTO `user` VALUES ('16', '测试用户2', '454545', '13589898989', '2018-06-29 15:58:05', '2018-06-29 15:58:05');
INSERT INTO `user` VALUES ('17', '测试用户3', '454545', '13056565656', '2018-06-29 15:58:52', '2018-06-29 15:58:52');
INSERT INTO `user` VALUES ('18', '测试用户4', '45656879', '13089895656', '2018-06-29 16:00:50', '2018-06-29 16:00:50');
INSERT INTO `user` VALUES ('19', '测试用户5', '45656', '13578785656', '2018-06-29 16:01:21', '2018-06-29 16:01:21');
INSERT INTO `user` VALUES ('20', '测试用户6', '4545456', '13645457878', '2018-06-30 22:38:10', '2018-06-30 22:38:10');
INSERT INTO `user` VALUES ('32', '测试2', '123456', '13056567878', '2018-06-30 20:54:48', '2018-06-30 20:54:48');
INSERT INTO `user` VALUES ('37', '乔猪猪', '乔猪猪的家地址不告诉你', '13712345124', '2018-07-01 00:09:32', '2018-07-01 00:09:32');
INSERT INTO `user` VALUES ('42', '可爱的小用户', 'bugaosuni', '13712345123', '2018-07-06 02:35:16', '2018-07-06 02:35:16');

-- ----------------------------
-- Table structure for `user_location`
-- ----------------------------
DROP TABLE IF EXISTS `user_location`;
CREATE TABLE `user_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `rec_name` varchar(20) DEFAULT NULL,
  `rec_tel` varchar(20) DEFAULT NULL,
  `REMOVED` char(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_location
-- ----------------------------
