/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : spring_boot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-14 16:54:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志产生时间',
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型（1601信息，1602异常）',
  `content` text COMMENT '日志内容',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '2017-12-14 15:35:22', '1061', '【请求类名】:com.songfayuan.springBoot.controller.UserController,【请求方法名】：findUserListByPage', '0');
INSERT INTO `log` VALUES ('2', '2017-12-14 15:36:34', '1061', '【请求类名】:com.songfayuan.springBoot.controller.UserController,【请求方法名】：findUserListByPage', '0');
INSERT INTO `log` VALUES ('3', '2017-12-14 15:38:10', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('4', '2017-12-14 15:38:20', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('5', '2017-12-14 15:38:32', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('6', '2017-12-14 15:40:46', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('7', '2017-12-14 15:41:09', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('8', '2017-12-14 15:41:38', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('9', '2017-12-14 15:41:48', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('10', '2017-12-14 15:43:03', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('11', '2017-12-14 15:43:03', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('12', '2017-12-14 15:43:03', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('13', '2017-12-14 15:43:03', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('14', '2017-12-14 15:43:03', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('15', '2017-12-14 15:43:03', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('16', '2017-12-14 15:48:41', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('17', '2017-12-14 15:48:42', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('18', '2017-12-14 15:48:42', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('19', '2017-12-14 15:48:43', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('20', '2017-12-14 15:49:44', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('21', '2017-12-14 15:49:45', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('22', '2017-12-14 15:49:46', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogById', '0');
INSERT INTO `log` VALUES ('24', '2017-12-14 15:51:41', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('25', '2017-12-14 15:55:21', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('26', '2017-12-14 15:56:36', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('27', '2017-12-14 15:57:53', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('28', '2017-12-14 15:58:44', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');
INSERT INTO `log` VALUES ('29', '2017-12-14 16:44:25', '1061', '【请求类名】:com.songfayuan.springBoot.controller.LogController,【请求方法名】：findLogListByPage', '0');

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
