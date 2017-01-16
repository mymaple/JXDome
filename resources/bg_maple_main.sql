/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jxdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-01-16 17:33:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bg_maple_main
-- ----------------------------
DROP TABLE IF EXISTS `bg_maple_main`;
CREATE TABLE `bg_maple_main` (
  `mapleId` varchar(100) NOT NULL COMMENT '代码生成 主键id',
  `mapleCode` varchar(100) DEFAULT NULL COMMENT '代码生成代号',
  `mapleName` varchar(100) DEFAULT NULL COMMENT '代码生成名称',
  `mapleType` varchar(100) DEFAULT NULL COMMENT '代码生成类型',
  `mapleStatus` varchar(100) DEFAULT NULL COMMENT '代码生成状态',
  `controllerPackage` varchar(100) DEFAULT NULL COMMENT '控制器包代号',
  `entityPackage` varchar(100) DEFAULT NULL COMMENT '实体类包代号',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`mapleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成';

-- ----------------------------
-- Records of bg_maple_main
-- ----------------------------
INSERT INTO `bg_maple_main` VALUES ('0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetail', '代码生成详情', '04', '00', 'bg', 'bg', '1484190988154', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_main` VALUES ('27a853950d0e4876ba0eccf8d7e2dd8f', 'baseField', '基本字段', '01', '00', 'bg', 'bg', '1484122184486', '01', '1', '2017-01-11 16:09:44', '1', '2017-01-11 16:09:44');
INSERT INTO `bg_maple_main` VALUES ('ad181811909d4f50b3c2c802e901be84', 'dict', '数据字典', '02', '00', 'bg', 'com', '1484190988200', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-16 11:22:04');
INSERT INTO `bg_maple_main` VALUES ('c9da8f2d57774bbbad13030135b6a1cb', 'maple', '代码生成', '03', '00', 'bg', 'bg', '1484149707387', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_main` VALUES ('ebd0083161064722a7c37d25a0030434', 'menu', '后台菜单', '02', '00', 'bg', 'bg', '1484546659607', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
