/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jxdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-01-17 17:42:14
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
-- Table structure for bgrole
-- ----------------------------
DROP TABLE IF EXISTS `bgrole`;
CREATE TABLE `bgrole` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台角色表id',
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `roleRights` varchar(255) DEFAULT NULL COMMENT '角色权限',
  `parentId` int(11) DEFAULT NULL COMMENT '上级id',
  `addRights` varchar(255) DEFAULT NULL COMMENT '新增权限',
  `delRights` varchar(255) DEFAULT NULL COMMENT '删除权限',
  `editRights` varchar(255) DEFAULT NULL COMMENT '修改权限',
  `seleRights` varchar(255) DEFAULT NULL COMMENT '查看权限',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='后台角色表';

-- ----------------------------
-- Records of bgrole
-- ----------------------------
INSERT INTO `bgrole` VALUES ('1', '系统管理组', '147573389638682795998', '0', '147573389638682795998', '147573389638682795998', '147573389638682795998', '147573389638682795998', '2016-06-06 02:27:47');
INSERT INTO `bgrole` VALUES ('2', '会员组1', '0', '0', '0', '0', '0', '0', '2016-12-22 11:02:04');
INSERT INTO `bgrole` VALUES ('4', '系统管理员', '0', '2', '0', '0', '0', '0', '2016-12-22 11:10:55');
INSERT INTO `bgrole` VALUES ('5', 'QWEQW', '27102961624680521488', '1', '9826886221838', '119911964136739700928', '1015808', '558446353793941504', '2016-12-22 17:42:41');

-- ----------------------------
-- Table structure for bguser
-- ----------------------------
DROP TABLE IF EXISTS `bguser`;
CREATE TABLE `bguser` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台用户表id',
  `userCode` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `userRights` varchar(255) DEFAULT NULL COMMENT '用户权限',
  `roleId` int(11) DEFAULT NULL COMMENT '角色id',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `lastLoginIp` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `userIconSrc` varchar(255) DEFAULT NULL COMMENT '用户头像路径',
  `userNumber` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of bguser
-- ----------------------------
INSERT INTO `bguser` VALUES ('1', 'admin', '802ad7f0bf5d54f941bd0a866d3fb6225af153a71d90e456b72a62e4a40008c9e8fc7a01b5d89121d90006f4628c95c4e5ba88ced2b958033791f663d97eed3a', 'FH', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2017-01-17 16:19:08', '0:0:0:0:0:0:0:1', 'static/ace/avatars/user.jpg', '001', 'QQ313596790@main.com', '18788888888', '1', 'zzzzzzzzzzz', '2016-06-06 02:27:47');
INSERT INTO `bguser` VALUES ('4', 'eqweqwe', '22ee6e217f5f507ab0e05932a083700533d454f909fea16922d98a95064d8155e430ead134eacedb453ba9d18c7d405ade8864b5a726b7aafefd00ef31a688fc', 'efesf是的范德萨', '0', '5', '2016-12-28 14:10:00', '127.0.0.1', 'static/ace/avatars/user.jpg', '0001', 'wewqe@sa.sds', '13698751542', '1', 'fd从五色风范', '2016-12-28 14:10:00');
INSERT INTO `bguser` VALUES ('5', 'dqwdwq', '5f634547762883d59bbb2fea284ae874ef0deba3e8395f616816765d136961b1f32be618c4b1c1979d0e960322e389292798178d7de6bd1554dbfa58aa74ebd3', '达瓦大', '0', '5', '2016-12-28 14:18:24', '127.0.0.1', 'static/ace/avatars/user.jpg', '00006', 'fsdfsdf@sad.vv', '13688877522', '1', 'asdsad', '2016-12-28 14:18:24');
INSERT INTO `bguser` VALUES ('6', 'qweqweq', '764ac9c70fc40eedf4f5bc8489a798e627bd6767d201d7d03c5877ef0c23d4ab9197b3a20af86ffa3c9b34ea28814620bfba59df8d4ab4e6afbfff6dc421b64b', 'dad', '0', '5', '2016-12-28 14:22:01', '127.0.0.1', 'static/ace/avatars/user.jpg', '0009', 'eqweqw@w.gg', '13695215642', '1', 'adsdsa d', '2016-12-28 14:22:01');

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
INSERT INTO `bg_maple_detail` VALUES ('022169ef83d8462ebb373e46d4ded0f6', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleName', '代码生成名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('06090486109a45ba91ea41fa724f629b', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailCode', '代码生成详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('0799c155ca024ee88ea6761acca8e369', '0c1ea3878cd34c8b9f99283de36f25fb', 'isEdit', '是否录入', '01', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484191624802', '01', '1', '2017-01-12 11:27:05', '1', '2017-01-12 11:27:05');
INSERT INTO `bg_maple_detail` VALUES ('161adf42874147d78e00d72a997783e9', 'ebd0083161064722a7c37d25a0030434', 'menuTag', '菜单数字标记', '02', '00', '10', '0', '', '00', '01', '01', '', '1484546691846', '01', '1', '2017-01-16 14:07:03', '1', '2017-01-16 14:07:03');
INSERT INTO `bg_maple_detail` VALUES ('2252b3cc015b4397b80193f919e15b89', 'ebd0083161064722a7c37d25a0030434', 'menuIcon', '菜单图标', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546889624', '01', '1', '2017-01-16 14:08:42', '1', '2017-01-16 14:08:42');
INSERT INTO `bg_maple_detail` VALUES ('2ad3266fdcdb4a6b8d384ddc5cea4a8b', 'ad181811909d4f50b3c2c802e901be84', 'dictCode', '数据字典代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542050', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('3fa8dba2b21c4f458acbb0bf82ce6f46', 'ebd0083161064722a7c37d25a0030434', 'menuStatus', '后台菜单状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484546688787', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('40f190c57abe431697318c35808f44c6', 'ebd0083161064722a7c37d25a0030434', 'menuCode', '后台菜单代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688739', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('43deda2ca573444eaae12e6136b19356', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleStatus', '代码生成状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4616314fd35947a69e9f0edce517b4ca', '0c1ea3878cd34c8b9f99283de36f25fb', 'typeCode', '类型代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191596294', '01', '1', '2017-01-12 11:26:36', '1', '2017-01-12 11:26:36');
INSERT INTO `bg_maple_detail` VALUES ('4c86cb30de514679be2f9af313a0e1e1', 'ebd0083161064722a7c37d25a0030434', 'menuName', '后台菜单名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688782', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('4d48487873ab44f6b5cae2a9f5092c23', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleType', '代码生成类型', '05', '00', '100', '0', 'bg_mapleType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('531737534ab649f9bd4ced96cda1ffda', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailType', '代码生成详情类型', '05', '00', '100', '0', 'bg_mapleDetailType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('54398981fb824b04bafb3f1d3cd48f6f', '27a853950d0e4876ba0eccf8d7e2dd8f', 'status', '状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 17:11:35', '1', '2017-01-17 16:47:23');
INSERT INTO `bg_maple_detail` VALUES ('5712b8ad5b1c4589a7bb5a40aa391962', '0c1ea3878cd34c8b9f99283de36f25fb', 'defaultValue', '默认值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191753948', '01', '1', '2017-01-12 11:29:14', '1', '2017-01-12 11:29:14');
INSERT INTO `bg_maple_detail` VALUES ('688f191c23ef4d1f98a3559e470cf6e4', '27a853950d0e4876ba0eccf8d7e2dd8f', 'code', '代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 16:52:43', '1', '2017-01-11 16:52:43');
INSERT INTO `bg_maple_detail` VALUES ('6d5509c750f84076b65b019c7a49828f', 'ebd0083161064722a7c37d25a0030434', 'menuUrl', '菜单链接', '01', '00', '255', '0', '', '00', '01', '01', '', '1484546828872', '01', '1', '2017-01-16 14:08:02', '1', '2017-01-16 14:08:02');
INSERT INTO `bg_maple_detail` VALUES ('6e1c83d28e19411ab4778f546e107435', '27a853950d0e4876ba0eccf8d7e2dd8f', 'type', '类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 17:08:55', '1', '2017-01-11 17:08:55');
INSERT INTO `bg_maple_detail` VALUES ('76e8bed04d6b4e5f8acdb81b6223ccf3', 'c9da8f2d57774bbbad13030135b6a1cb', 'controllerPackage', '控制器包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150518096', '01', '1', '2017-01-12 00:01:58', '1', '2017-01-12 00:01:58');
INSERT INTO `bg_maple_detail` VALUES ('77d5c1c3c1c8425ea9536c64b3614c8e', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleCode', '代码生成代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('878e2c01af44411b957d4203417bb024', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailStatus', '代码生成详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('969f5383c0a54b479439227e2e8adf24', '0c1ea3878cd34c8b9f99283de36f25fb', 'isKey', '是否主键', '05', '00', '100', '0', '', '00', '01', '01', '\"00\"', '1484191616190', '01', '1', '2017-01-12 11:28:16', '1', '2017-01-17 15:15:51');
INSERT INTO `bg_maple_detail` VALUES ('9bf5394fccf044c6b01aac17acdffea6', 'ad181811909d4f50b3c2c802e901be84', 'dictStatus', '数据字典状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484529542053', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('a35792a023f848ac897f4a7753ea6938', '0c1ea3878cd34c8b9f99283de36f25fb', 'isNull', '是否null', '01', '00', '100', '9', '', '00', '01', '01', '\"01\"', '1484191654251', '01', '1', '2017-01-12 11:27:34', '1', '2017-01-17 16:01:38');
INSERT INTO `bg_maple_detail` VALUES ('a795aac6efd64eb4b81bafcfdba74f19', 'ebd0083161064722a7c37d25a0030434', 'menuType', '后台菜单类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484546688785', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('be775a41a6544563b93b6a3e17ca727a', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailName', '代码生成详情名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('c67ea20d301d4f29ac881f9cf300a2ba', 'ad181811909d4f50b3c2c802e901be84', 'dictName', '数据字典名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542051', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('c997cc1f6d8a4fbbaa5e4eb0490c501a', '0c1ea3878cd34c8b9f99283de36f25fb', 'decimalLength', '小数长度', '02', '00', '1', '0', '', '00', '01', '01', '0', '1484191459111', '01', '1', '2017-01-12 11:24:19', '1', '2017-01-16 00:00:07');
INSERT INTO `bg_maple_detail` VALUES ('d549cf2254bb4f3a8cee5fae93537a29', 'ad181811909d4f50b3c2c802e901be84', 'dictValue', '数据字典值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529553258', '01', '1', '2017-01-16 09:19:57', '1', '2017-01-16 09:19:57');
INSERT INTO `bg_maple_detail` VALUES ('eeafe483879b40c6aaeb56083f9b8c44', '27a853950d0e4876ba0eccf8d7e2dd8f', 'name', '名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 17:07:21', '1', '2017-01-11 17:07:21');
INSERT INTO `bg_maple_detail` VALUES ('ef3cc316b9bf45059bd65405e7366741', 'c9da8f2d57774bbbad13030135b6a1cb', 'entityPackage', '实体类包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150564639', '01', '1', '2017-01-12 00:02:44', '1', '2017-01-12 00:02:44');
INSERT INTO `bg_maple_detail` VALUES ('ef6b8a854ab140bbb0c01b18094f9db9', '0c1ea3878cd34c8b9f99283de36f25fb', 'totalLength', '总长度', '02', '00', '20', '0', '', '00', '01', '01', '100', '1484191421616', '01', '1', '2017-01-12 11:23:42', '1', '2017-01-15 23:59:55');
INSERT INTO `bg_maple_detail` VALUES ('f5288b368efa4b3eaf1ad35f56a2d348', 'ad181811909d4f50b3c2c802e901be84', 'dictType', '数据字典类型', '05', '00', '100', '0', 'bg_dictType', '00', '01', '01', '\"01\"', '1484529542052', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 10:37:46');

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
INSERT INTO `bg_menu_tree` VALUES ('36', '2', 'background/role', '角色(基础权限)', '01', '01', '36', 'background/role/list.do', 'menu-icon fa fa-key orange', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('37', '2', '#', '按钮权限', '01', '01', '37', 'buttonrights/list.do', 'menu-icon fa fa-key green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('38', '1', 'background/menu', '菜单管理', '01', '01', '38', 'background/menu/main.do', 'menu-icon fa fa-folder-open-o brown', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('39', '1', '#', '按钮管理', '01', '01', '39', 'fhbutton/list.do', 'menu-icon fa fa-download orange', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('4', '59', '#', '文件管理', '01', '01', '4', 'fhfile/list.do', 'menu-icon fa fa-folder-open purple', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('40', '0', '#', '用户管理', '02', '01', '40', '#', 'menu-icon fa fa-users blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('41', '40', 'background/user', '系统用户', '01', '01', '41', 'background/user/list.do', 'menu-icon fa fa-users green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('42', '40', '#', '会员管理', '01', '01', '42', 'happuser/listUsers.do', 'menu-icon fa fa-users orange', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('43', '1', 'background/dict', '数据字典', '01', '01', '43', 'background/dict/main.do', 'menu-icon fa fa-book purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
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
INSERT INTO `bg_menu_tree` VALUES ('61', '44', 'background/maple', '反向生成', '01', '01', '61', 'background/maple/list.do', 'menu-icon fa fa-cogs blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('62', '44', 'background/maple', '正向生成', '01', '01', '62', 'background/maple/list.do', 'menu-icon fa fa-cogs green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('63', '6', '#', '主附结构', '01', '01', '63', 'attached/list.do', 'menu-icon fa fa-folder-open blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('64', '59', '#', '员工管理', '01', '01', '64', 'staff/list.do', 'menu-icon fa fa-users blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('65', '6', '#', '多数据源', '01', '01', '65', 'datasource2/list.do', 'menu-icon fa fa-folder-open-o purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('66', '6', '#', '下拉联动', '01', '01', '66', 'linkage/view.do', 'menu-icon fa fa-exchange green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('7', '6', '#', '图片管理', '02', '01', '7', '#', 'menu-icon fa fa-folder-o pink', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('72', '1', 'qweqqweqweqwe', 'asdsad', '01', '01', '72', 'qweqwesadfasd', 'menu-icon fa fa-adjust black', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('8', '9', '#', '性能监控', '01', '01', '8', 'druid/index.html', 'menu-icon fa fa-tachometer red', '8', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bg_menu_tree` VALUES ('9', '0', '#', '系统工具', '02', '01', '9', '#', 'menu-icon fa fa-cog black', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');

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
INSERT INTO `com_dict_tree` VALUES ('0c608e9327344f588eebd8edce01de8b', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_01', '标准型', '01', '00', '01', '1', '1484025817953', '01', '1', '2017-01-10 13:23:38', '1', '2017-01-10 13:23:38');
INSERT INTO `com_dict_tree` VALUES ('1c0caa13e3514c87a3280f2f48653d83', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_02', 'Int', '01', '00', '02', '1', '1484018861734', '01', '1', '2017-01-10 11:27:42', '1', '2017-01-11 16:26:59');
INSERT INTO `com_dict_tree` VALUES ('280f1698d19d49bbb676b09f95d24a51', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_02', '业务菜单', '01', '00', '02', '0', '1484555389350', '01', '1', '2017-01-16 16:30:42', '1', '2017-01-16 16:30:42');
INSERT INTO `com_dict_tree` VALUES ('28510c12e9834ea29bbb5621f99e804e', '0', 'bg_mapleDetailType', '字段属性类型', '01', '00', 'bg_mapleDetailType', '0', '1484018714788', '01', '1', '2017-01-10 11:25:15', '1', '2017-01-11 16:23:30');
INSERT INTO `com_dict_tree` VALUES ('2c43919dfe434841a92bc2a39b2ad02e', '0', 'bg_menuType', '后台菜单类型', '01', '00', 'bg_menuType', '0', '1484555292472', '01', '1', '2017-01-16 16:28:52', '1', '2017-01-16 16:28:52');
INSERT INTO `com_dict_tree` VALUES ('30c39f10c07e4b3b905734277e527eee', '0', 'com_sf', '是否判断', '01', '00', 'com_sf', '0', '1484536370790', '01', '1', '2017-01-16 11:13:37', '1', '2017-01-16 11:18:18');
INSERT INTO `com_dict_tree` VALUES ('42293fbe78a649568d01da16ff66040f', 'b120815786714648a7247046fe7186d9', 'bg_dictType_02', '数据库字典', '01', '00', '02', '1', '1484017474427', '01', '1', '2017-01-10 11:04:34', '1', '2017-01-10 11:04:34');
INSERT INTO `com_dict_tree` VALUES ('4324f08d02c943ab96e9078b01f703d8', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_04', '主从型（从）', '01', '00', '04', '1', '1484025928454', '01', '1', '2017-01-10 13:25:28', '1', '2017-01-10 13:25:28');
INSERT INTO `com_dict_tree` VALUES ('4ed0b868ed2b4d69887bcef4932eb4ef', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_05', 'Dict', '01', '00', '05', '1', '1484019114333', '01', '1', '2017-01-10 11:31:54', '1', '2017-01-11 16:27:15');
INSERT INTO `com_dict_tree` VALUES ('540b4214c9ea4927867b1a878365ec9c', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_wx', 'weixin', '01', '00', 'wx', '1', '1484018416335', '01', '1', '2017-01-10 11:20:16', '1', '2017-01-10 11:20:16');
INSERT INTO `com_dict_tree` VALUES ('54467b4e2dc84c1ca9e21828803eb9b8', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_04', 'Double', '01', '00', '04', '1', '1484019019767', '01', '1', '2017-01-10 11:30:20', '1', '2017-01-11 16:27:10');
INSERT INTO `com_dict_tree` VALUES ('609bbe934cd24f7588af2f717a8d49f3', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_03', 'Time', '01', '00', '03', '1', '1484019001039', '01', '1', '2017-01-10 11:30:01', '1', '2017-01-11 16:27:04');
INSERT INTO `com_dict_tree` VALUES ('6d58e343386e47afbf79fb2dbc8c7d76', 'b120815786714648a7247046fe7186d9', 'bg_dictType_01', '参数字典', '01', '00', '01', '1', '1484017427635', '01', '1', '2017-01-10 11:03:48', '1', '2017-01-10 11:03:48');
INSERT INTO `com_dict_tree` VALUES ('7faad10eb0424cf29a51fb5add0f1b50', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_01', 'String', '01', '00', '01', '1', '1484018802176', '01', '1', '2017-01-10 11:26:42', '1', '2017-01-11 16:26:54');
INSERT INTO `com_dict_tree` VALUES ('b120815786714648a7247046fe7186d9', '0', 'bg_dictType', '字典类型', '01', '00', 'bg_dictType', '0', '1484017251117', '01', '1', '2017-01-10 11:00:51', '1', '2017-01-10 11:00:51');
INSERT INTO `com_dict_tree` VALUES ('d8cf8becf1db4418a098a43500d5cf3f', '0', 'com_packageType', '模块包分类', '01', '00', 'com_packageType', '0', '1484017801545', '01', '1', '2017-01-10 11:10:02', '1', '2017-01-10 11:10:02');
INSERT INTO `com_dict_tree` VALUES ('e53230ed682741f4ad63e470e0d86488', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_mb', 'mobile', '01', '00', 'mb', '1', '1484018478585', '01', '1', '2017-01-10 11:21:19', '1', '2017-01-10 11:21:33');
INSERT INTO `com_dict_tree` VALUES ('e69288a45145429c8c3a865024d4b83c', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_02', '树形', '01', '00', '02', '1', '1484025846486', '01', '1', '2017-01-10 13:24:06', '1', '2017-01-10 13:24:06');
INSERT INTO `com_dict_tree` VALUES ('ec677b163d7448f8a1b050829917f657', '30c39f10c07e4b3b905734277e527eee', 'com_sf_00', '否', '01', '00', '00', '0', '1484536554576', '01', '1', '2017-01-16 11:16:26', '1', '2017-01-16 11:18:34');
INSERT INTO `com_dict_tree` VALUES ('f0cde8fcc255428fae4cceb7f1a3abf1', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_bg', 'background', '01', '00', 'bg', '1', '1484018374289', '01', '1', '2017-01-10 11:19:34', '1', '2017-01-10 11:19:34');
INSERT INTO `com_dict_tree` VALUES ('f29b6b4266b54c67a3d9f629abc95560', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_com', 'common', '01', '00', 'com', '1', '1484017926047', '01', '1', '2017-01-10 11:12:06', '1', '2017-01-10 11:13:22');
INSERT INTO `com_dict_tree` VALUES ('f2b727ee2e654654bd91363660fe2197', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_03', '主从型（主）', '01', '00', '03', '1', '1484025903963', '01', '1', '2017-01-10 13:25:04', '1', '2017-01-10 13:25:04');
INSERT INTO `com_dict_tree` VALUES ('f4377b4baf504d5a98eedbf4cb65a46e', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_01', '系统菜单', '01', '00', '01', '0', '1484555346406', '01', '1', '2017-01-16 16:29:48', '1', '2017-01-16 16:29:48');
INSERT INTO `com_dict_tree` VALUES ('f9831d6e9c3b482381bb940ffc5edf20', '0', 'bg_mapleType', '代码结构类型', '01', '00', 'bg_mapleType', '0', '1484025760602', '01', '1', '2017-01-10 13:22:41', '1', '2017-01-10 13:22:41');
INSERT INTO `com_dict_tree` VALUES ('fe507ec52dd74565b9b44138a30763bf', '30c39f10c07e4b3b905734277e527eee', 'com_sf_01', '是', '01', '00', '01', '0', '1484536535794', '01', '1', '2017-01-16 11:15:53', '1', '2017-01-16 11:18:28');
