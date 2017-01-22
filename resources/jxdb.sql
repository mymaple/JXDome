/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jxdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-01-22 17:51:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgconfig
-- ----------------------------
DROP TABLE IF EXISTS `bgconfig`;
CREATE TABLE `bgconfig` (
  `configId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台配置id',
  `configType` varchar(50) DEFAULT NULL COMMENT '配置类型',
  `configName` varchar(50) DEFAULT NULL COMMENT '配置名称',
  `param1` varchar(100) DEFAULT NULL COMMENT '接入网址',
  `param2` varchar(100) DEFAULT NULL COMMENT '端口号/数字',
  `param3` varchar(100) DEFAULT NULL COMMENT '账号/X',
  `param4` varchar(100) DEFAULT NULL COMMENT '密码/Y',
  `isOpen` varchar(10) DEFAULT NULL COMMENT '是否启动',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`configId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='后台配置';

-- ----------------------------
-- Records of bgconfig
-- ----------------------------
INSERT INTO `bgconfig` VALUES ('1', 'configBgSystem', '系统配置', 'JX1', '11', 'admin', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('2', 'configBgEmailServer', '邮箱服务器配置', 'smt1p.qq.com', '21', 'it1@126.com', '1231', '1', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('3', 'configBgMessage', '短信账户配置', 'http://www.dx1ton.com/', null, 'username1', 'ppp1', '1', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('4', 'configBgWordWaterMark', '文字水印配置', 'JX1', '21', '11', '12', '1', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('5', 'configBgImageWaterMark', '图片水印配置', 'watermark.png', null, '14', '13', '0', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('6', 'configBgWeiXin', ' 微信接口配置', '/weixin/index ', null, 'token1', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('7', 'configBgInstantChat', '即时聊天服务器配置', '127.0.0.2', '6021', '', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgconfig` VALUES ('8', 'configBgOnlineManage', '在线管理服务器配置', '127.0.0.3', '6022', '', '', '1', '2015-06-03 22:09:13');

-- ----------------------------
-- Table structure for bg_crontab_info
-- ----------------------------
DROP TABLE IF EXISTS `bg_crontab_info`;
CREATE TABLE `bg_crontab_info` (
  `crontabId` varchar(100) NOT NULL COMMENT '定时任务 主键id',
  `crontabCode` varchar(100) DEFAULT NULL COMMENT '定时任务代号',
  `crontabName` varchar(100) DEFAULT NULL COMMENT '定时任务名称',
  `crontabType` varchar(100) DEFAULT NULL COMMENT '定时任务类型',
  `crontabStatus` varchar(100) DEFAULT NULL COMMENT '定时任务状态',
  `startupTimes` int(10) DEFAULT NULL COMMENT '启动次数',
  `jobName` varchar(100) DEFAULT NULL COMMENT '任务名',
  `jobGroupName` varchar(100) DEFAULT NULL COMMENT '任务组名',
  `jobClass` varchar(100) DEFAULT NULL COMMENT '任务',
  `triggerName` varchar(100) DEFAULT NULL COMMENT '触发器名',
  `triggerGroupName` varchar(100) DEFAULT NULL COMMENT '触发器组名',
  `triggerTimes` int(10) DEFAULT NULL COMMENT '运作次数',
  `endTime` datetime DEFAULT NULL COMMENT '停止时间',
  `timeExp` varchar(100) DEFAULT NULL COMMENT '时间表达式',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`crontabId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of bg_crontab_info
-- ----------------------------
INSERT INTO `bg_crontab_info` VALUES ('433ccd82e6554b2bb3344876fe339e0c', 'bgWxCallback', '微信回调', '03', '00', '0', 'bgWxCallback', 'bgWxCallback', 'bgWxCallback', 'bgWxCallback', 'bgWxCallback', '0', '2017-01-22 10:48:48', '0 5 0/1 * * ?', '1485052049567', '01', '1', '2017-01-22 10:48:48', '1', '2017-01-22 10:50:33');

-- ----------------------------
-- Table structure for bg_maple_detail
-- ----------------------------
DROP TABLE IF EXISTS `bg_maple_detail`;
CREATE TABLE `bg_maple_detail` (
  `mapleDetailId` varchar(100) NOT NULL COMMENT '代码生成详情 主键id',
  `mapleId` varchar(100) NOT NULL COMMENT '代码生成 id',
  `mapleDetailCode` varchar(100) DEFAULT NULL COMMENT '代码生成详情代号',
  `mapleDetailName` varchar(100) DEFAULT NULL COMMENT '代码生成详情名称',
  `mapleDetailType` varchar(100) DEFAULT NULL COMMENT '代码生成详情类型',
  `mapleDetailStatus` varchar(100) DEFAULT NULL COMMENT '代码生成详情状态',
  `totalLength` int(20) DEFAULT NULL COMMENT '总长度',
  `decimalLength` int(1) DEFAULT NULL COMMENT '小数长度',
  `typeCode` varchar(100) DEFAULT NULL COMMENT '类型代号',
  `isKey` varchar(100) DEFAULT NULL COMMENT '是否主键',
  `isEdit` varchar(100) DEFAULT NULL COMMENT '是否录入',
  `isNull` varchar(100) DEFAULT NULL COMMENT '是否null',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`mapleDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成详情';

-- ----------------------------
-- Records of bg_maple_detail
-- ----------------------------
INSERT INTO `bg_maple_detail` VALUES ('006671e6bef3446fa5b2db1becf2f362', 'ad181811909d4f50b3c2c802e901be84', 'level', '级别', '02', '00', '100', '0', '', '00', '00', '01', '', '1484529598922', '01', '1', '2017-01-16 09:20:54', '1', '2017-01-16 09:20:54');
INSERT INTO `bg_maple_detail` VALUES ('0110be7e650c4e128a4f9dbe9c2560ae', '7928b5c3a71443e69dac9a775fb732aa', 'userIconSrc', '用户头像路径', '01', '00', '255', '0', '', '00', '00', '01', '\"static/ace/avatars/user.jpg\"', '1484728084351', '01', '1', '2017-01-18 16:31:30', '1', '2017-01-18 16:31:30');
INSERT INTO `bg_maple_detail` VALUES ('018d93b604a149f594a461f41c34d9cc', '7928b5c3a71443e69dac9a775fb732aa', 'userName', '后台用户名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484726215273', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('022169ef83d8462ebb373e46d4ded0f6', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleName', '代码生成名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('06090486109a45ba91ea41fa724f629b', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailCode', '代码生成详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('06467c464283483f846e90fd3f60763b', '67163a4e05664fa39d5bea61b445163b', 'roleName', '后台角色名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484794097627', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('066e01f3bddb4dd0bfd1186a4c5e6b9f', '36ff1b75838f43efac8625df493e0df1', 'token', 'Token(令牌)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882525498', '01', '1', '2017-01-20 11:22:20', '1', '2017-01-20 11:22:20');
INSERT INTO `bg_maple_detail` VALUES ('0799c155ca024ee88ea6761acca8e369', '0c1ea3878cd34c8b9f99283de36f25fb', 'isEdit', '是否录入', '01', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484191624802', '01', '1', '2017-01-12 11:27:05', '1', '2017-01-12 11:27:05');
INSERT INTO `bg_maple_detail` VALUES ('07bcb64dd5944dc3b808f7419aed7440', '3b04706d4ab8494faa53e05e822a6e82', 'crontabType', '定时任务类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484983375406', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('111031d1ebdc4ac5a8028ac9c286e08e', '67163a4e05664fa39d5bea61b445163b', 'roleStatus', '后台角色状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484794097652', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('1379e9109d224ce784e05df3241b8329', '36ff1b75838f43efac8625df493e0df1', 'mchId', '微信支付商户号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882711370', '01', '1', '2017-01-20 11:33:53', '1', '2017-01-20 11:33:53');
INSERT INTO `bg_maple_detail` VALUES ('161adf42874147d78e00d72a997783e9', 'ebd0083161064722a7c37d25a0030434', 'menuTag', '菜单数字标记', '02', '00', '10', '0', '', '00', '01', '01', '', '1484546691846', '01', '1', '2017-01-16 14:07:03', '1', '2017-01-16 14:07:03');
INSERT INTO `bg_maple_detail` VALUES ('1a826d69ea0d4122b31eddb0af8cea04', '3b04706d4ab8494faa53e05e822a6e82', 'jobClass', '任务', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983679664', '01', '1', '2017-01-21 15:28:20', '1', '2017-01-21 15:28:20');
INSERT INTO `bg_maple_detail` VALUES ('2252b3cc015b4397b80193f919e15b89', 'ebd0083161064722a7c37d25a0030434', 'menuIcon', '菜单图标', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546889624', '01', '1', '2017-01-16 14:08:42', '1', '2017-01-16 14:08:42');
INSERT INTO `bg_maple_detail` VALUES ('22d65fec87014577ace005d6e47d562a', '3b04706d4ab8494faa53e05e822a6e82', 'timeExp', '时间表达式', '01', '00', '100', '0', '', '00', '01', '01', '', '1484989028911', '01', '1', '2017-01-21 17:04:35', '1', '2017-01-21 17:04:35');
INSERT INTO `bg_maple_detail` VALUES ('276866ecbb21416f94f83ab692d96b71', '36ff1b75838f43efac8625df493e0df1', 'wxAccountStatus', '微信账户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484882249480', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('2775107f29434bf68b812a8437b751cf', '36ff1b75838f43efac8625df493e0df1', 'apiKey', 'API密钥', '01', '00', '100', '0', '', '00', '01', '01', '', '1484883254237', '01', '1', '2017-01-20 12:20:56', '1', '2017-01-20 14:55:36');
INSERT INTO `bg_maple_detail` VALUES ('28056f5c4e78405187c39a0b8ec21293', '67163a4e05664fa39d5bea61b445163b', 'seleRights', '查询权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794231222', '01', '1', '2017-01-19 10:50:51', '1', '2017-01-19 10:50:51');
INSERT INTO `bg_maple_detail` VALUES ('28d66288b90a4cc0833f8e6cec268dbe', '7928b5c3a71443e69dac9a775fb732aa', 'userStatus', '后台用户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484726215275', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('2ad3266fdcdb4a6b8d384ddc5cea4a8b', 'ad181811909d4f50b3c2c802e901be84', 'dictCode', '数据字典代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542050', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('2d1f6f9eb85f4a2987afcee0e32093af', '7928b5c3a71443e69dac9a775fb732aa', 'userCode', '后台用户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484726215270', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('2d5da37fa3764af8a4fe4cc5f735366e', '36ff1b75838f43efac8625df493e0df1', 'accessToken', '公众号的全局唯一票据', '01', '00', '1000', '0', '', '00', '00', '01', '', '1484889777852', '01', '1', '2017-01-20 13:24:19', '1', '2017-01-20 13:24:19');
INSERT INTO `bg_maple_detail` VALUES ('32efc08f0b9b446e9822df5064053c40', '3b04706d4ab8494faa53e05e822a6e82', 'jobName', '任务名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983425892', '01', '1', '2017-01-21 15:27:40', '1', '2017-01-21 15:27:40');
INSERT INTO `bg_maple_detail` VALUES ('382aec89d15d47f6959c43dd6a663be3', '3b04706d4ab8494faa53e05e822a6e82', 'triggerGroupName', '触发器组名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983820288', '01', '1', '2017-01-21 15:30:57', '1', '2017-01-21 15:30:57');
INSERT INTO `bg_maple_detail` VALUES ('3fa8dba2b21c4f458acbb0bf82ce6f46', 'ebd0083161064722a7c37d25a0030434', 'menuStatus', '后台菜单状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484546688787', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('40f190c57abe431697318c35808f44c6', 'ebd0083161064722a7c37d25a0030434', 'menuCode', '后台菜单代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688739', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('43deda2ca573444eaae12e6136b19356', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleStatus', '代码生成状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4616314fd35947a69e9f0edce517b4ca', '0c1ea3878cd34c8b9f99283de36f25fb', 'typeCode', '类型代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191596294', '01', '1', '2017-01-12 11:26:36', '1', '2017-01-12 11:26:36');
INSERT INTO `bg_maple_detail` VALUES ('47ec4fadae18458e8b9ee8d723939085', '7928b5c3a71443e69dac9a775fb732aa', 'phone', '手机号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1484728458411', '01', '1', '2017-01-18 16:35:49', '1', '2017-01-18 16:35:49');
INSERT INTO `bg_maple_detail` VALUES ('4c86cb30de514679be2f9af313a0e1e1', 'ebd0083161064722a7c37d25a0030434', 'menuName', '后台菜单名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688782', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('4d48487873ab44f6b5cae2a9f5092c23', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleType', '代码生成类型', '05', '00', '100', '0', 'bg_mapleType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('530502b6cc5b45dfa9d4c751d095de9c', '7928b5c3a71443e69dac9a775fb732aa', 'remarks', '备注信息', '01', '00', '255', '0', '', '00', '01', '01', '', '1484728674804', '01', '1', '2017-01-18 16:38:12', '1', '2017-01-18 16:38:12');
INSERT INTO `bg_maple_detail` VALUES ('531737534ab649f9bd4ced96cda1ffda', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailType', '代码生成详情类型', '05', '00', '100', '0', 'bg_mapleDetailType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('54398981fb824b04bafb3f1d3cd48f6f', '27a853950d0e4876ba0eccf8d7e2dd8f', 'status', '状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 17:11:35', '1', '2017-01-17 16:47:23');
INSERT INTO `bg_maple_detail` VALUES ('5712b8ad5b1c4589a7bb5a40aa391962', '0c1ea3878cd34c8b9f99283de36f25fb', 'defaultValue', '默认值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191753948', '01', '1', '2017-01-12 11:29:14', '1', '2017-01-12 11:29:14');
INSERT INTO `bg_maple_detail` VALUES ('5777ff112dcc4b57a9314a9b7fcf813d', '36ff1b75838f43efac8625df493e0df1', 'wxAccountName', '微信账户名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882249398', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('688f191c23ef4d1f98a3559e470cf6e4', '27a853950d0e4876ba0eccf8d7e2dd8f', 'code', '代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 16:52:43', '1', '2017-01-11 16:52:43');
INSERT INTO `bg_maple_detail` VALUES ('6ce2076ea1334fcdac96c1e044cea7b9', '3b04706d4ab8494faa53e05e822a6e82', 'triggerName', '触发器名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983701452', '01', '1', '2017-01-21 15:28:43', '1', '2017-01-21 15:28:43');
INSERT INTO `bg_maple_detail` VALUES ('6d5509c750f84076b65b019c7a49828f', 'ebd0083161064722a7c37d25a0030434', 'menuUrl', '菜单链接', '01', '00', '255', '0', '', '00', '01', '01', '', '1484546828872', '01', '1', '2017-01-16 14:08:02', '1', '2017-01-16 14:08:02');
INSERT INTO `bg_maple_detail` VALUES ('6e1c83d28e19411ab4778f546e107435', '27a853950d0e4876ba0eccf8d7e2dd8f', 'type', '类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 17:08:55', '1', '2017-01-11 17:08:55');
INSERT INTO `bg_maple_detail` VALUES ('762f024f88d640ada0f36a59a259f7fb', '67163a4e05664fa39d5bea61b445163b', 'delRights', '删除权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794193285', '01', '1', '2017-01-19 10:50:08', '1', '2017-01-19 10:50:08');
INSERT INTO `bg_maple_detail` VALUES ('76e8bed04d6b4e5f8acdb81b6223ccf3', 'c9da8f2d57774bbbad13030135b6a1cb', 'controllerPackage', '控制器包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150518096', '01', '1', '2017-01-12 00:01:58', '1', '2017-01-12 00:01:58');
INSERT INTO `bg_maple_detail` VALUES ('77d5c1c3c1c8425ea9536c64b3614c8e', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleCode', '代码生成代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('7ad405a7439c46cf93fd4bda1bb3037b', '7928b5c3a71443e69dac9a775fb732aa', 'password', '密码', '01', '00', '255', '0', '', '00', '01', '01', '', '1484726215272', '01', '1', '2017-01-18 16:36:25', '1', '2017-01-18 16:41:14');
INSERT INTO `bg_maple_detail` VALUES ('83dd1de384524795af254eff73adf927', '36ff1b75838f43efac8625df493e0df1', 'wxAccountCode', '微信账户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882249321', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('878e2c01af44411b957d4203417bb024', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailStatus', '代码生成详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('89a5a14b9b7b44bab50594b6325cf415', '3b04706d4ab8494faa53e05e822a6e82', 'crontabName', '定时任务名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983375383', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('969f5383c0a54b479439227e2e8adf24', '0c1ea3878cd34c8b9f99283de36f25fb', 'isKey', '是否主键', '05', '00', '100', '0', '', '00', '01', '01', '\"00\"', '1484191616190', '01', '1', '2017-01-12 11:28:16', '1', '2017-01-17 15:15:51');
INSERT INTO `bg_maple_detail` VALUES ('9ae2dab2efcc41aab876f03b7395c8e1', '67163a4e05664fa39d5bea61b445163b', 'roleCode', '后台角色代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484794097594', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('9bf5394fccf044c6b01aac17acdffea6', 'ad181811909d4f50b3c2c802e901be84', 'dictStatus', '数据字典状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484529542053', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('9d5252681e574c9b9673296c1b73aad7', '3b04706d4ab8494faa53e05e822a6e82', 'crontabStatus', '定时任务状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484983375410', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('a2e09db55f0c43beb3fe9f09f62d2cd0', '67163a4e05664fa39d5bea61b445163b', 'addRights', '新增权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794135451', '01', '1', '2017-01-19 10:49:47', '1', '2017-01-19 10:49:47');
INSERT INTO `bg_maple_detail` VALUES ('a35792a023f848ac897f4a7753ea6938', '0c1ea3878cd34c8b9f99283de36f25fb', 'isNull', '是否null', '01', '00', '100', '9', '', '00', '01', '01', '\"01\"', '1484191654251', '01', '1', '2017-01-12 11:27:34', '1', '2017-01-17 16:01:38');
INSERT INTO `bg_maple_detail` VALUES ('a795aac6efd64eb4b81bafcfdba74f19', 'ebd0083161064722a7c37d25a0030434', 'menuType', '后台菜单类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484546688785', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('a9d320b6ba534243a2e87ca11cf50eb3', '7928b5c3a71443e69dac9a775fb732aa', 'userType', '后台用户类型', '05', '00', '100', '0', 'bg_userType', '00', '01', '01', '\"01\"', '1484726215274', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-19 14:46:05');
INSERT INTO `bg_maple_detail` VALUES ('aba1d712b9894f07bce792563f35ba21', '3b04706d4ab8494faa53e05e822a6e82', 'crontabCode', '定时任务代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983375345', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('acccd70e48194133bb92afb0585b1de2', '36ff1b75838f43efac8625df493e0df1', 'wxAccountType', '微信账户类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484882249476', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('ae880d3d066b4fa7851d89c9732be099', '67163a4e05664fa39d5bea61b445163b', 'roleRights', '角色权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794097660', '01', '1', '2017-01-19 10:57:05', '1', '2017-01-19 10:57:32');
INSERT INTO `bg_maple_detail` VALUES ('af1467b9630543038a2784eba02e562a', '67163a4e05664fa39d5bea61b445163b', 'roleType', '后台角色类型', '05', '00', '100', '0', 'bgRoleType', '00', '01', '01', '\"01\"', '1484794097628', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 14:46:28');
INSERT INTO `bg_maple_detail` VALUES ('be775a41a6544563b93b6a3e17ca727a', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailName', '代码生成详情名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('c0dea14e2ab54b5581ca065caf00415f', '3b04706d4ab8494faa53e05e822a6e82', 'startupTimes', '启动次数', '02', '00', '10', '0', '', '00', '00', '01', '', '1484983425888', '01', '1', '2017-01-21 16:52:48', '1', '2017-01-21 16:56:12');
INSERT INTO `bg_maple_detail` VALUES ('c56f03bc5c864dcd854f57ea8e972436', '36ff1b75838f43efac8625df493e0df1', 'appSecret', 'AppSecret(应用密钥)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882415113', '01', '1', '2017-01-20 11:21:24', '1', '2017-01-20 11:21:24');
INSERT INTO `bg_maple_detail` VALUES ('c67ea20d301d4f29ac881f9cf300a2ba', 'ad181811909d4f50b3c2c802e901be84', 'dictName', '数据字典名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542051', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('c997cc1f6d8a4fbbaa5e4eb0490c501a', '0c1ea3878cd34c8b9f99283de36f25fb', 'decimalLength', '小数长度', '02', '00', '1', '0', '', '00', '01', '01', '0', '1484191459111', '01', '1', '2017-01-12 11:24:19', '1', '2017-01-16 00:00:07');
INSERT INTO `bg_maple_detail` VALUES ('cb36f7e6cca94d61b6ca68b6b6d62746', '3b04706d4ab8494faa53e05e822a6e82', 'jobGroupName', '任务组名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983661875', '01', '1', '2017-01-21 15:27:58', '1', '2017-01-21 15:27:58');
INSERT INTO `bg_maple_detail` VALUES ('d549cf2254bb4f3a8cee5fae93537a29', 'ad181811909d4f50b3c2c802e901be84', 'dictValue', '数据字典值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529553258', '01', '1', '2017-01-16 09:19:57', '1', '2017-01-16 09:19:57');
INSERT INTO `bg_maple_detail` VALUES ('d74d0cac2f204274a6c9fdbaea1ec886', '3b04706d4ab8494faa53e05e822a6e82', 'triggerTimes', '运作次数', '02', '00', '10', '0', '', '00', '00', '01', '', '1484988775488', '01', '1', '2017-01-21 16:53:43', '1', '2017-01-21 16:53:43');
INSERT INTO `bg_maple_detail` VALUES ('d89d83f6a74b4128b91179f95cab3d33', '3b04706d4ab8494faa53e05e822a6e82', 'endTime', '停止时间', '03', '00', '100', '0', '', '00', '00', '01', '', '1484988827177', '01', '1', '2017-01-21 16:55:02', '1', '2017-01-21 16:55:02');
INSERT INTO `bg_maple_detail` VALUES ('dafc3c3d3f4846e786a547cda1f17b45', '7928b5c3a71443e69dac9a775fb732aa', 'roleId', '角色id', '05', '00', '100', '0', 'bg_roleEffective', '00', '01', '01', '', '1484726205220', '01', '1', '2017-01-18 16:37:03', '1', '2017-01-18 16:39:44');
INSERT INTO `bg_maple_detail` VALUES ('e8f6ef912613464daa85940683606069', '67163a4e05664fa39d5bea61b445163b', 'editRights', '修改权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794210103', '01', '1', '2017-01-19 10:50:23', '1', '2017-01-19 10:50:23');
INSERT INTO `bg_maple_detail` VALUES ('edaaa6a0b0164200869165de5681dad1', '36ff1b75838f43efac8625df493e0df1', 'jsApiTicket', '微信JS接口的临时票据', '01', '00', '100', '0', '', '00', '00', '01', '', '1484889868656', '01', '1', '2017-01-20 13:49:23', '1', '2017-01-20 14:55:51');
INSERT INTO `bg_maple_detail` VALUES ('eeafe483879b40c6aaeb56083f9b8c44', '27a853950d0e4876ba0eccf8d7e2dd8f', 'name', '名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 17:07:21', '1', '2017-01-11 17:07:21');
INSERT INTO `bg_maple_detail` VALUES ('ef3cc316b9bf45059bd65405e7366741', 'c9da8f2d57774bbbad13030135b6a1cb', 'entityPackage', '实体类包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150564639', '01', '1', '2017-01-12 00:02:44', '1', '2017-01-12 00:02:44');
INSERT INTO `bg_maple_detail` VALUES ('ef6b8a854ab140bbb0c01b18094f9db9', '0c1ea3878cd34c8b9f99283de36f25fb', 'totalLength', '总长度', '02', '00', '20', '0', '', '00', '01', '01', '100', '1484191421616', '01', '1', '2017-01-12 11:23:42', '1', '2017-01-15 23:59:55');
INSERT INTO `bg_maple_detail` VALUES ('f388076884914adb9df661523c17ff88', '36ff1b75838f43efac8625df493e0df1', 'appId', 'AppID(应用ID)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882330433', '01', '1', '2017-01-20 11:20:13', '1', '2017-01-20 13:22:42');
INSERT INTO `bg_maple_detail` VALUES ('f5288b368efa4b3eaf1ad35f56a2d348', 'ad181811909d4f50b3c2c802e901be84', 'dictType', '数据字典类型', '05', '00', '100', '0', 'bg_dictType', '00', '01', '01', '\"01\"', '1484529542052', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 10:37:46');
INSERT INTO `bg_maple_detail` VALUES ('fcbd78fd88f94fa5bbb971e88da084c4', '7928b5c3a71443e69dac9a775fb732aa', 'email', '电子邮箱', '01', '00', '100', '0', '', '00', '01', '01', '', '1484728391889', '01', '1', '2017-01-18 16:34:12', '1', '2017-01-18 16:34:12');

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
INSERT INTO `bg_maple_main` VALUES ('36ff1b75838f43efac8625df493e0df1', 'wxAccount', '微信账户', '01', '00', 'bg', 'com', '1484881971240', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_main` VALUES ('3b04706d4ab8494faa53e05e822a6e82', 'crontab', '定时任务', '01', '00', 'bg', 'bg', '1484983080995', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_main` VALUES ('67163a4e05664fa39d5bea61b445163b', 'role', '后台角色', '01', '00', 'bg', 'bg', '1484794064296', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_main` VALUES ('7928b5c3a71443e69dac9a775fb732aa', 'user', '后台用户', '01', '00', 'bg', 'bg', '1484726157405', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_main` VALUES ('ad181811909d4f50b3c2c802e901be84', 'dict', '数据字典', '02', '00', 'bg', 'com', '1484190988200', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-16 11:22:04');
INSERT INTO `bg_maple_main` VALUES ('c9da8f2d57774bbbad13030135b6a1cb', 'maple', '代码生成', '03', '00', 'bg', 'bg', '1484149707387', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_main` VALUES ('ebd0083161064722a7c37d25a0030434', 'menu', '后台菜单', '02', '00', 'bg', 'bg', '1484546659607', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');

-- ----------------------------
-- Table structure for bg_menu_tree
-- ----------------------------
DROP TABLE IF EXISTS `bg_menu_tree`;
CREATE TABLE `bg_menu_tree` (
  `menuId` varchar(100) NOT NULL COMMENT '后台菜单 主键id',
  `parentId` varchar(100) NOT NULL COMMENT '上级 id',
  `menuCode` varchar(100) DEFAULT NULL COMMENT '后台菜单代号',
  `menuName` varchar(100) DEFAULT NULL COMMENT '后台菜单名称',
  `menuType` varchar(100) DEFAULT NULL COMMENT '后台菜单类型',
  `menuStatus` varchar(100) DEFAULT NULL COMMENT '后台菜单状态',
  `menuTag` int(10) DEFAULT NULL COMMENT '菜单数字标记',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `menuIcon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台菜单';

-- ----------------------------
-- Records of bg_menu_tree
-- ----------------------------
INSERT INTO `bg_menu_tree` VALUES ('08c4fe77d0a143f59dd765394e3605a2', '15', 'background_wxAccount', '微信账号管理', '01', '01', '77', 'background/wxAccount/list.do', 'menu-icon fa fa-desktop blue', '1484892666233', '01', '1', '2017-01-20 14:13:30', '1', '2017-01-20 14:13:30');
INSERT INTO `bg_menu_tree` VALUES ('1', '0', '#', '系统管理', '02', '01', '1', '#', 'menu-icon fa fa-desktop blue', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('10', '9', '#', '接口测试', '01', '01', '10', 'tool/interfaceTest.do', 'menu-icon fa fa-exchange green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('11', '9', '#', '发送邮件', '01', '01', '11', 'tool/goSendEmail.do', 'menu-icon fa fa-envelope-o green', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('12', '9', '#', '置二维码', '01', '01', '12', 'tool/goTwoDimensionCode.do', 'menu-icon fa fa-barcode green', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('14', '9', '#', '地图工具', '01', '01', '14', 'tool/map.do', 'menu-icon fa fa-globe black', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('15', '0', '#', '微信管理', '02', '01', '15', '#', 'menu-icon fa fa-comments purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('16', '15', '#', '文本回复', '02', '01', '16', 'textmsg/list.do', 'menu-icon fa fa-comment green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('17', '15', '#', '应用命令', '02', '01', '17', 'command/list.do', 'menu-icon fa fa-comment grey', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('18', '15', '#', '图文回复', '02', '01', '18', 'imgmsg/list.do', 'menu-icon fa fa-comment pink', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('19', '15', '#', '关注回复', '02', '01', '19', 'textmsg/goSubscribe.do', 'menu-icon fa fa-comment orange', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('2', '1', '#', '权限管理', '01', '01', '2', '#', 'menu-icon fa fa-lock black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('20', '1', '#', '在线管理', '01', '01', '20', 'onlinemanager/list.do', 'menu-icon fa fa-laptop green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('21', '9', '#', '打印测试', '01', '01', '21', 'tool/printTest.do', 'menu-icon fa fa-hdd-o grey', '7', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('22', '0', '#', '一级菜单', '02', '01', '22', '#', 'menu-icon fa fa-fire orange', '10', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('23', '22', '#', '二级菜单', '01', '01', '23', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('24', '23', '#', '三级菜单', '01', '01', '24', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('3', '1', '#', '日志管理', '01', '01', '3', 'fhlog/list.do', 'menu-icon fa fa-book blue', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('30', '24', '#', '四级菜单', '01', '01', '30', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('31', '30', '#', '五级菜单1', '01', '01', '31', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('32', '30', '#', '五级菜单2', '01', '01', '32', '#', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('33', '31', '#', '六级菜单', '01', '01', '33', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('34', '31', '#', '六级菜单2', '01', '01', '34', 'login_default.do', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('35', '24', '#', '四级菜单2', '01', '01', '35', 'login_default.do', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('36', '2', 'background_role', '角色(基础权限)', '01', '01', '36', 'background/role/list.do', 'menu-icon fa fa-key orange', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('37', '2', '#', '按钮权限', '01', '01', '37', 'buttonrights/list.do', 'menu-icon fa fa-key green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('38', '1', 'background_menu', '菜单管理', '01', '01', '38', 'background/menu/main.do', 'menu-icon fa fa-folder-open-o brown', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('39', '1', '#', '按钮管理', '01', '01', '39', 'fhbutton/list.do', 'menu-icon fa fa-download orange', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('4', '59', '#', '文件管理', '01', '01', '4', 'fhfile/list.do', 'menu-icon fa fa-folder-open purple', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('40', '0', '#', '用户管理', '02', '01', '40', '#', 'menu-icon fa fa-users blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('41', '40', 'background_user', '系统用户', '01', '01', '41', 'background/user/list.do', 'menu-icon fa fa-users green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('42', '40', '#', '会员管理', '01', '01', '42', 'happuser/listUsers.do', 'menu-icon fa fa-users orange', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('43', '1', 'background_dict', '数据字典', '01', '01', '43', 'background/dict/main.do', 'menu-icon fa fa-book purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('44', '9', '#', '代码生成器', '01', '01', '44', '#', 'menu-icon fa fa-cogs brown', '0', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('45', '33', '#', '七级菜单1', '01', '01', '45', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('46', '33', '#', '七级菜单2', '01', '01', '46', '#', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('47', '45', '#', '八级菜单', '01', '01', '47', 'login_default.do', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('48', '9', '#', '图表报表', '01', '01', '48', ' tool/fusionchartsdemo.do', 'menu-icon fa fa-bar-chart-o black', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('50', '6', '#', '站内信', '01', '01', '50', 'fhsms/list.do', 'menu-icon fa fa-envelope green', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('51', '7', '#', '图片列表', '01', '01', '51', 'pictures/list.do', 'menu-icon fa fa-folder-open-o green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('52', '7', '#', '图片爬虫', '01', '01', '52', 'pictures/goImageCrawler.do', 'menu-icon fa fa-cloud-download green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('53', '9', '#', '表单构建器', '01', '01', '53', 'tool/goFormbuilder.do', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('54', '0', '#', '数据库管理', '02', '01', '54', '#', 'menu-icon fa fa-hdd-o blue', '9', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('55', '54', '#', '数据库备份', '01', '01', '55', 'brdb/listAllTable.do', 'menu-icon fa fa-cloud-upload blue', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('56', '54', '#', '数据库还原', '01', '01', '56', 'brdb/list.do', 'menu-icon fa fa-cloud-download blue', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('57', '54', '#', '备份定时器', '01', '01', '57', 'timingbackup/list.do', 'menu-icon fa fa-tachometer blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('58', '54', '#', 'SQL编辑器', '01', '01', '58', 'sqledit/view.do', 'menu-icon fa fa-pencil-square-o blue', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('59', '0', '#', 'OA办公', '02', '01', '59', '#', 'menu-icon fa fa-laptop pink', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('6', '0', '#', '信息管理', '02', '01', '6', '#', 'menu-icon fa fa-credit-card green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('60', '59', '#', '组织机构', '01', '01', '60', 'department/listAllDepartment.do?DEPARTMENT_ID=0', 'menu-icon fa fa-users green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('61', '44', 'background_maple', '反向生成', '01', '01', '61', 'background/maple/list.do', 'menu-icon fa fa-cogs blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('62', '44', 'background_maple', '正向生成', '01', '01', '62', 'background/maple/list.do', 'menu-icon fa fa-cogs green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('63', '6', '#', '主附结构', '01', '01', '63', 'attached/list.do', 'menu-icon fa fa-folder-open blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('64', '59', '#', '员工管理', '01', '01', '64', 'staff/list.do', 'menu-icon fa fa-users blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('65', '6', '#', '多数据源', '01', '01', '65', 'datasource2/list.do', 'menu-icon fa fa-folder-open-o purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('66', '6', '#', '下拉联动', '01', '01', '66', 'linkage/view.do', 'menu-icon fa fa-exchange green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('6f32cc2a85ce49f6896c26b0c22597b2', '9', 'background_crontab', '定时任务管理', '01', '01', '78', 'background/crontab/list.do', 'menu-icon fa fa-folder-o pink', '1485051094670', '01', '1', '2017-01-22 10:13:22', '1', '2017-01-22 10:13:22');
INSERT INTO `bg_menu_tree` VALUES ('7', '6', '#', '图片管理', '02', '01', '7', '#', 'menu-icon fa fa-folder-o pink', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('72', '1', 'qweqqweqweqwe', 'asdsad', '01', '01', '72', 'qweqwesadfasd', 'menu-icon fa fa-adjust black', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('8', '9', '#', '性能监控', '01', '01', '8', 'druid/index.html', 'menu-icon fa fa-tachometer red', '8', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('9', '0', '#', '系统工具', '02', '01', '9', '#', 'menu-icon fa fa-cog black', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');

-- ----------------------------
-- Table structure for bg_role_info
-- ----------------------------
DROP TABLE IF EXISTS `bg_role_info`;
CREATE TABLE `bg_role_info` (
  `roleId` varchar(100) NOT NULL COMMENT '后台角色 主键id',
  `roleCode` varchar(100) DEFAULT NULL COMMENT '后台角色代号',
  `roleName` varchar(100) DEFAULT NULL COMMENT '后台角色名称',
  `roleType` varchar(100) DEFAULT NULL COMMENT '后台角色类型',
  `roleStatus` varchar(100) DEFAULT NULL COMMENT '后台角色状态',
  `roleRights` varchar(100) DEFAULT NULL COMMENT '角色权限',
  `addRights` varchar(100) DEFAULT NULL COMMENT '新增权限',
  `delRights` varchar(100) DEFAULT NULL COMMENT '删除权限',
  `editRights` varchar(100) DEFAULT NULL COMMENT '修改权限',
  `seleRights` varchar(100) DEFAULT NULL COMMENT '查询权限',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色';

-- ----------------------------
-- Records of bg_role_info
-- ----------------------------
INSERT INTO `bg_role_info` VALUES ('1', 'admin', '系统管理员', '00', '00', '453494755745124623310814', '453494755745124623310814', '453494755745124623310814', '453494755745124623310814', '453494755745124623310814', '1', '01', '1', '2017-01-19 13:24:30', '1', '2017-01-22 10:14:24');

-- ----------------------------
-- Table structure for bg_user_info
-- ----------------------------
DROP TABLE IF EXISTS `bg_user_info`;
CREATE TABLE `bg_user_info` (
  `userId` varchar(100) NOT NULL COMMENT '后台用户 主键id',
  `roleId` varchar(100) DEFAULT NULL COMMENT '角色id',
  `userCode` varchar(100) DEFAULT NULL COMMENT '后台用户代号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userName` varchar(100) DEFAULT NULL COMMENT '后台用户名称',
  `userType` varchar(100) DEFAULT NULL COMMENT '后台用户类型',
  `userStatus` varchar(100) DEFAULT NULL COMMENT '后台用户状态',
  `userIconSrc` varchar(255) DEFAULT NULL COMMENT '用户头像路径',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户';

-- ----------------------------
-- Records of bg_user_info
-- ----------------------------
INSERT INTO `bg_user_info` VALUES ('1', '1', 'admin', '802ad7f0bf5d54f941bd0a866d3fb6225af153a71d90e456b72a62e4a40008c9e8fc7a01b5d89121d90006f4628c95c4e5ba88ced2b958033791f663d97eed3a', 'maple', '01', '00', 'static/ace/avatars/user.jpg', '54325621@qq.com', '13256876192', '灰机', '1', '01', '1', '2017-01-19 10:17:03', '1', '2017-01-19 10:17:07');
INSERT INTO `bg_user_info` VALUES ('aaee1e4622724f79bb2f9a1c667a7395', '1', 'xxxxx', '9a2cf82b5e81d253c971b09b2fab3da41082e1a4d6d42327111fccdcb6e9f8f20d17c6cd172cd30b73e82ce6e75395cebb89024feb48b832cd8ffa3e44becefa', '22222222222', '01', '00', 'static/ace/avatars/user.jpg', 'wad@df.cc', '13575542432', '', '1484814622729', '01', '1', '2017-01-19 16:30:58', '1', '2017-01-19 16:31:05');

-- ----------------------------
-- Table structure for com_dict_tree
-- ----------------------------
DROP TABLE IF EXISTS `com_dict_tree`;
CREATE TABLE `com_dict_tree` (
  `dictId` varchar(100) NOT NULL COMMENT '数据字典 主键id',
  `parentId` varchar(100) NOT NULL COMMENT '上级 id',
  `dictCode` varchar(100) DEFAULT NULL COMMENT '数据字典代号',
  `dictName` varchar(100) DEFAULT NULL COMMENT '数据字典名称',
  `dictType` varchar(100) DEFAULT NULL COMMENT '数据字典类型',
  `dictStatus` varchar(100) DEFAULT NULL COMMENT '数据字典状态',
  `dictValue` varchar(100) DEFAULT NULL COMMENT '数据字典值',
  `level` int(100) DEFAULT NULL COMMENT '级别',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of com_dict_tree
-- ----------------------------
INSERT INTO `com_dict_tree` VALUES ('012a42b40dc84d82a0bd957eefcf86e0', '0', 'bg_mapleEffective', '生效代码生成器', '02', '00', 'bg_mapleEffective', '0', '1484804281118', '01', '1', '2017-01-19 13:38:27', '1', '2017-01-19 13:51:11');
INSERT INTO `com_dict_tree` VALUES ('049f63a9f923403e94e3056a0216afaf', '0', 'com_dictEffective', '生效数据字典', '02', '00', 'com_dictEffective', '0', '1484804310182', '01', '1', '2017-01-19 13:38:49', '1', '2017-01-19 13:51:18');
INSERT INTO `com_dict_tree` VALUES ('06f38de637814d9285397f1baed966f9', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_02', '跑批任务', '01', '00', '02', '1', '1485051595520', '01', '1', '2017-01-22 10:20:16', '1', '2017-01-22 10:20:16');
INSERT INTO `com_dict_tree` VALUES ('0c608e9327344f588eebd8edce01de8b', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_01', '标准型', '01', '00', '01', '1', '1484025817953', '01', '1', '2017-01-10 13:23:38', '1', '2017-01-10 13:23:38');
INSERT INTO `com_dict_tree` VALUES ('132b054047c14abc87f6be78ad0f710a', '0', 'bg_userEffective', '后台生效用户', '02', '00', 'bg_userEffective', '0', '1484726025794', '01', '1', '2017-01-18 15:55:03', '1', '2017-01-18 15:55:09');
INSERT INTO `com_dict_tree` VALUES ('173468d2a2d24dd48642f6cd452c11d2', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_03', '微信回调', '01', '00', '03', '1', '1485051617535', '01', '1', '2017-01-22 10:21:26', '1', '2017-01-22 10:21:26');
INSERT INTO `com_dict_tree` VALUES ('1c0caa13e3514c87a3280f2f48653d83', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_02', 'Int', '01', '00', '02', '1', '1484018861734', '01', '1', '2017-01-10 11:27:42', '1', '2017-01-11 16:26:59');
INSERT INTO `com_dict_tree` VALUES ('280f1698d19d49bbb676b09f95d24a51', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_02', '业务菜单', '01', '00', '02', '1', '1484555389350', '01', '1', '2017-01-16 16:30:42', '1', '2017-01-16 16:30:42');
INSERT INTO `com_dict_tree` VALUES ('28510c12e9834ea29bbb5621f99e804e', '0', 'bg_mapleDetailType', '字段属性类型', '01', '00', 'bg_mapleDetailType', '0', '1484018714788', '01', '1', '2017-01-10 11:25:15', '1', '2017-01-11 16:23:30');
INSERT INTO `com_dict_tree` VALUES ('2c43919dfe434841a92bc2a39b2ad02e', '0', 'bg_menuType', '后台菜单类型', '01', '00', 'bg_menuType', '0', '1484555292472', '01', '1', '2017-01-16 16:28:52', '1', '2017-01-16 16:28:52');
INSERT INTO `com_dict_tree` VALUES ('30a83d0bc4364ac39edb8d0f5786a338', '0', 'bg_crontabType', '定时任务类型', '01', '00', 'bg_crontabType', '0', '1485051437664', '01', '1', '2017-01-22 10:18:50', '1', '2017-01-22 10:18:50');
INSERT INTO `com_dict_tree` VALUES ('30c39f10c07e4b3b905734277e527eee', '0', 'com_sf', '是否判断', '01', '00', 'com_sf', '0', '1484536370790', '01', '1', '2017-01-16 11:13:37', '1', '2017-01-16 11:18:18');
INSERT INTO `com_dict_tree` VALUES ('319bbf4a475b4aa4b852c30d91d9c48c', '0', 'com_wxAccountType', '微信账号类型', '01', '00', 'com_wxAccountType', '0', '1484895809412', '01', '1', '2017-01-20 15:04:51', '1', '2017-01-20 15:04:51');
INSERT INTO `com_dict_tree` VALUES ('351a934eb7654c2db753fc51b39d3e16', '0', 'bg_userType', '后台用户类型', '01', '00', 'bg_userType', '0', '1484804429010', '01', '1', '2017-01-19 13:41:51', '1', '2017-01-19 13:41:51');
INSERT INTO `com_dict_tree` VALUES ('366429147bef4767a545483285ee0b3c', '351a934eb7654c2db753fc51b39d3e16', 'bg_userType_02', '注册用户', '01', '00', '02', '1', '1484804565584', '01', '1', '2017-01-19 13:43:10', '1', '2017-01-19 13:43:10');
INSERT INTO `com_dict_tree` VALUES ('3894f644c42e441aa1a605513f7c22c6', '0', 'bg_menuEffective', '后台生效菜单', '02', '00', 'bg_menuEffective', '0', '1484804259249', '01', '1', '2017-01-19 13:37:57', '1', '2017-01-19 13:51:05');
INSERT INTO `com_dict_tree` VALUES ('42293fbe78a649568d01da16ff66040f', 'b120815786714648a7247046fe7186d9', 'com_dictType_02', '数据库字典', '01', '00', '02', '1', '1484017474427', '01', '1', '2017-01-10 11:04:34', '1', '2017-01-10 11:04:34');
INSERT INTO `com_dict_tree` VALUES ('4324f08d02c943ab96e9078b01f703d8', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_04', '主从型（从）', '01', '00', '04', '1', '1484025928454', '01', '1', '2017-01-10 13:25:28', '1', '2017-01-10 13:25:28');
INSERT INTO `com_dict_tree` VALUES ('4ed0b868ed2b4d69887bcef4932eb4ef', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_05', 'Dict', '01', '00', '05', '1', '1484019114333', '01', '1', '2017-01-10 11:31:54', '1', '2017-01-11 16:27:15');
INSERT INTO `com_dict_tree` VALUES ('540b4214c9ea4927867b1a878365ec9c', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_wx', 'weixin', '01', '00', 'wx', '1', '1484018416335', '01', '1', '2017-01-10 11:20:16', '1', '2017-01-10 11:20:16');
INSERT INTO `com_dict_tree` VALUES ('54467b4e2dc84c1ca9e21828803eb9b8', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_04', 'Double', '01', '00', '04', '1', '1484019019767', '01', '1', '2017-01-10 11:30:20', '1', '2017-01-11 16:27:10');
INSERT INTO `com_dict_tree` VALUES ('609bbe934cd24f7588af2f717a8d49f3', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_03', 'Time', '01', '00', '03', '1', '1484019001039', '01', '1', '2017-01-10 11:30:01', '1', '2017-01-11 16:27:04');
INSERT INTO `com_dict_tree` VALUES ('6d58e343386e47afbf79fb2dbc8c7d76', 'b120815786714648a7247046fe7186d9', 'com_dictType_01', '参数字典', '01', '00', '01', '1', '1484017427635', '01', '1', '2017-01-10 11:03:48', '1', '2017-01-10 11:03:48');
INSERT INTO `com_dict_tree` VALUES ('79750bdf92c840e6bcc2fe2cb25cfda5', 'a60fe6c08d7c4708bca2a20cb2feb681', 'bg_roleType_01', '一般角色', '01', '00', '01', '1', '1484804728835', '01', '1', '2017-01-19 13:46:17', '1', '2017-01-19 13:46:17');
INSERT INTO `com_dict_tree` VALUES ('7faad10eb0424cf29a51fb5add0f1b50', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_01', 'String', '01', '00', '01', '1', '1484018802176', '01', '1', '2017-01-10 11:26:42', '1', '2017-01-11 16:26:54');
INSERT INTO `com_dict_tree` VALUES ('8ea0f054459f4e619c8a75bfd90f67bb', 'a60fe6c08d7c4708bca2a20cb2feb681', 'bg_roleType_00', '系统管理员角色', '01', '00', '00', '1', '1484804778802', '01', '1', '2017-01-19 13:46:56', '1', '2017-01-19 13:46:56');
INSERT INTO `com_dict_tree` VALUES ('8f1b49981a4d449383bfb703c0176bd7', '351a934eb7654c2db753fc51b39d3e16', 'bg_userType_01', '录入用户', '01', '00', '01', '1', '1484804518887', '01', '1', '2017-01-19 13:42:43', '1', '2017-01-19 13:42:43');
INSERT INTO `com_dict_tree` VALUES ('916b2c8543b94d488d80a5a08f05b681', '0', 'bg_roleEffective', '后台生效角色', '02', '00', 'bg_roleEffective', '0', '1484804220171', '01', '1', '2017-01-19 13:37:22', '1', '2017-01-19 13:37:37');
INSERT INTO `com_dict_tree` VALUES ('97eb8171437447fc8714a8c1a2075b9a', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_01', '数据表备份', '01', '00', '01', '1', '1485051549949', '01', '1', '2017-01-22 10:19:54', '1', '2017-01-22 10:19:54');
INSERT INTO `com_dict_tree` VALUES ('a60fe6c08d7c4708bca2a20cb2feb681', '0', 'bg_roleType', '后台角色类型', '01', '00', 'bg_roleType', '0', '1484804620997', '01', '1', '2017-01-19 13:45:08', '1', '2017-01-19 13:45:08');
INSERT INTO `com_dict_tree` VALUES ('b120815786714648a7247046fe7186d9', '0', 'com_dictType', '字典类型', '01', '00', 'bg_dictType', '0', '1484017251117', '01', '1', '2017-01-10 11:00:51', '1', '2017-01-10 11:00:51');
INSERT INTO `com_dict_tree` VALUES ('b20d74e13ced4513b1ac20c4ead6d736', '319bbf4a475b4aa4b852c30d91d9c48c', 'com_wxAccountType_01', '服务号', '01', '00', '01', '1', '1484896386061', '01', '1', '2017-01-20 15:14:06', '1', '2017-01-20 15:14:06');
INSERT INTO `com_dict_tree` VALUES ('d8cf8becf1db4418a098a43500d5cf3f', '0', 'com_packageType', '模块包分类', '01', '00', 'com_packageType', '0', '1484017801545', '01', '1', '2017-01-10 11:10:02', '1', '2017-01-10 11:10:02');
INSERT INTO `com_dict_tree` VALUES ('e53230ed682741f4ad63e470e0d86488', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_mb', 'mobile', '01', '00', 'mb', '1', '1484018478585', '01', '1', '2017-01-10 11:21:19', '1', '2017-01-10 11:21:33');
INSERT INTO `com_dict_tree` VALUES ('e69288a45145429c8c3a865024d4b83c', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_02', '树形', '01', '00', '02', '1', '1484025846486', '01', '1', '2017-01-10 13:24:06', '1', '2017-01-10 13:24:06');
INSERT INTO `com_dict_tree` VALUES ('ec677b163d7448f8a1b050829917f657', '30c39f10c07e4b3b905734277e527eee', 'com_sf_00', '否', '01', '00', '00', '1', '1484536554576', '01', '1', '2017-01-16 11:16:26', '1', '2017-01-16 11:18:34');
INSERT INTO `com_dict_tree` VALUES ('f0cde8fcc255428fae4cceb7f1a3abf1', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_bg', 'background', '01', '00', 'bg', '1', '1484018374289', '01', '1', '2017-01-10 11:19:34', '1', '2017-01-10 11:19:34');
INSERT INTO `com_dict_tree` VALUES ('f29b6b4266b54c67a3d9f629abc95560', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_com', 'common', '01', '00', 'com', '1', '1484017926047', '01', '1', '2017-01-10 11:12:06', '1', '2017-01-10 11:13:22');
INSERT INTO `com_dict_tree` VALUES ('f2b727ee2e654654bd91363660fe2197', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_03', '主从型（主）', '01', '00', '03', '1', '1484025903963', '01', '1', '2017-01-10 13:25:04', '1', '2017-01-10 13:25:04');
INSERT INTO `com_dict_tree` VALUES ('f4377b4baf504d5a98eedbf4cb65a46e', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_01', '系统菜单', '01', '00', '01', '1', '1484555346406', '01', '1', '2017-01-16 16:29:48', '1', '2017-01-16 16:29:48');
INSERT INTO `com_dict_tree` VALUES ('f9831d6e9c3b482381bb940ffc5edf20', '0', 'bg_mapleType', '代码结构类型', '01', '00', 'bg_mapleType', '0', '1484025760602', '01', '1', '2017-01-10 13:22:41', '1', '2017-01-10 13:22:41');
INSERT INTO `com_dict_tree` VALUES ('fe507ec52dd74565b9b44138a30763bf', '30c39f10c07e4b3b905734277e527eee', 'com_sf_01', '是', '01', '00', '01', '1', '1484536535794', '01', '1', '2017-01-16 11:15:53', '1', '2017-01-16 11:18:28');

-- ----------------------------
-- Table structure for com_wx_account_info
-- ----------------------------
DROP TABLE IF EXISTS `com_wx_account_info`;
CREATE TABLE `com_wx_account_info` (
  `wxAccountId` varchar(100) NOT NULL COMMENT '微信账户 主键id',
  `wxAccountCode` varchar(100) DEFAULT NULL COMMENT '微信账户代号',
  `wxAccountName` varchar(100) DEFAULT NULL COMMENT '微信账户名称',
  `wxAccountType` varchar(100) DEFAULT NULL COMMENT '微信账户类型',
  `wxAccountStatus` varchar(100) DEFAULT NULL COMMENT '微信账户状态',
  `appId` varchar(100) DEFAULT NULL COMMENT 'AppID(应用ID)',
  `appSecret` varchar(100) DEFAULT NULL COMMENT 'AppSecret(应用密钥)',
  `token` varchar(100) DEFAULT NULL COMMENT 'Token(令牌)',
  `mchId` varchar(100) DEFAULT NULL COMMENT '微信支付商户号',
  `apiKey` varchar(100) DEFAULT NULL COMMENT 'API密钥',
  `accessToken` varchar(1000) DEFAULT NULL COMMENT '公众号的全局唯一票据',
  `jsApiTicket` varchar(100) DEFAULT NULL COMMENT '微信JS接口的临时票据',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`wxAccountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信账户';

-- ----------------------------
-- Records of com_wx_account_info
-- ----------------------------
INSERT INTO `com_wx_account_info` VALUES ('9828b1377557429c8cb449440291e042', 'giftOL', '在线送礼', '01', '01', 'wxf3a522044df3f3df', '2028ab8d1cdb5c5e918bfb2c3f4c47f5', 'jiangxiang', '1423462402', '3034adc8e3218f3f3be7bb27590c90ed', 'Oc3Sr7kGjwyoSpIMKX6Xa2fWQ08FC65wKZP-S6OXKVlIS3igAXrNDq3VGCb7l3x71ZoWDhTlnWi8nAihe68n-FSuwSXhFfMyBk4BqXtUh7bt2ilHIC1afIznW_DbDvRcWDHgAJADKO', '', '1484896943192', '01', '1', '2017-01-20 15:26:49', '1', '2017-01-21 16:08:34');
