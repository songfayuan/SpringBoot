/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : spring_boot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-13 21:46:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别（0 男 ，1女）',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '宋发元', '123456', '1', '24', '13867400741', '1414798079@qq.com');
INSERT INTO `user` VALUES ('2', '周吉吉', '123456', '1', '30', '10086', 'zhoujiji@qq.com');
INSERT INTO `user` VALUES ('4', '孙斌', '123456', '1', '30', '10086', '1414@qq.com');
INSERT INTO `user` VALUES ('5', '孙斌', '123456', '1', '30', '10086', '1414@qq.com');
INSERT INTO `user` VALUES ('6', '孙斌', '123456', '1', '30', '10086', '1414@qq.com');
SET FOREIGN_KEY_CHECKS=1;
