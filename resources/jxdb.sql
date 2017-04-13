/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jxdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-04-13 17:33:14
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
INSERT INTO `bg_maple_detail` VALUES ('02f2a9b3f57b4a1e8cb9d38c5701fcba', '26e2f983954449ed84539074b02628f1', 'phone', '手机号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215138757', '01', '1', '2017-03-11 14:52:51', '1', '2017-03-11 14:52:51');
INSERT INTO `bg_maple_detail` VALUES ('06090486109a45ba91ea41fa724f629b', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailCode', '代码生成详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('06467c464283483f846e90fd3f60763b', '67163a4e05664fa39d5bea61b445163b', 'roleName', '后台角色名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484794097627', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('066e01f3bddb4dd0bfd1186a4c5e6b9f', '36ff1b75838f43efac8625df493e0df1', 'token', 'Token(令牌)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882525498', '01', '1', '2017-01-20 11:22:20', '1', '2017-01-20 11:22:20');
INSERT INTO `bg_maple_detail` VALUES ('0799c155ca024ee88ea6761acca8e369', '0c1ea3878cd34c8b9f99283de36f25fb', 'isEdit', '是否录入', '01', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484191624802', '01', '1', '2017-01-12 11:27:05', '1', '2017-01-12 11:27:05');
INSERT INTO `bg_maple_detail` VALUES ('07bcb64dd5944dc3b808f7419aed7440', '3b04706d4ab8494faa53e05e822a6e82', 'crontabType', '定时任务类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484983375406', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('08b527b1b0cc49109fa16f29634bd5d4', '864215909ec741eda25b708998f777d0', 'lbtStatus', '轮播图状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1489815060706', '01', '1', '2017-03-18 13:31:01', '1', '2017-03-18 13:31:01');
INSERT INTO `bg_maple_detail` VALUES ('09022fb4f49c4c39a1b0ac365ad5f051', '2b8a44269b4542e48e13549c8e2e14a4', 'originalPrice', '原价', '04', '00', '100', '2', '', '00', '01', '01', '', '1487905809816', '01', '1', '2017-02-24 11:10:45', '1', '2017-02-24 11:11:12');
INSERT INTO `bg_maple_detail` VALUES ('09e80d18610a440fa531f7acd1267e40', 'a4dc9b54bb3e4d17ad983912730a6667', 'productCode', '产品代号', '01', '00', '100', '0', 'sp', '00', '00', '01', '', '1487902142331', '01', '1', '2017-02-24 10:09:02', '1', '2017-03-21 22:38:04');
INSERT INTO `bg_maple_detail` VALUES ('0a216c4596594555a61ebeddf288850e', 'a7fef610fae34226afc5515388822a79', 'orderName', '订单名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1489197833013', '01', '1', '2017-03-11 10:03:53', '1', '2017-03-11 10:03:53');
INSERT INTO `bg_maple_detail` VALUES ('0cd951c34ead4d23b43a2924903453b9', '307d768ee19e45b09b016970caf257ca', 'sparepartType', '零配件类型', '05', '00', '100', '0', 'com_sparepartType', '00', '01', '01', '\"01\"', '1488528018794', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_detail` VALUES ('0ec10dc238c2498ea3068837e5692bf2', 'a7fef610fae34226afc5515388822a79', 'appUserId', '平台用户', '05', '00', '100', '0', 'com_appUserEffective', '00', '01', '01', '', '1489198729491', '01', '1', '2017-03-25 17:12:58', '1', '2017-03-25 17:13:21');
INSERT INTO `bg_maple_detail` VALUES ('108129484f3d4b148975614765f97f63', '26e2f983954449ed84539074b02628f1', 'district', '区', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215864746', '01', '1', '2017-03-11 15:05:29', '1', '2017-03-11 15:05:29');
INSERT INTO `bg_maple_detail` VALUES ('111031d1ebdc4ac5a8028ac9c286e08e', '67163a4e05664fa39d5bea61b445163b', 'roleStatus', '后台角色状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484794097652', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('11bfec3c80e54267aea3193e4183fb97', 'd25535c48c444f9fb675294c8a2d8e1f', 'value', '扩展属性值', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533756969', '01', '1', '2017-03-03 17:36:20', '1', '2017-03-03 17:36:20');
INSERT INTO `bg_maple_detail` VALUES ('12167b0828584a44b7d2824ce3f360c2', '2b8a44269b4542e48e13549c8e2e14a4', 'allStockNum', '库存总量', '02', '00', '100', '0', '', '00', '01', '01', '', '1487905200854', '01', '1', '2017-03-21 13:59:07', '1', '2017-03-21 13:59:19');
INSERT INTO `bg_maple_detail` VALUES ('1260a70eaaec4fa1b9e2c905e43e117f', 'e43dbde47f8e4b2fab9d226b9ce3976b', 'styleCategoryDetailStatus', '规格分类详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487913761140', '01', '1', '2017-02-24 13:22:41', '1', '2017-02-24 13:22:41');
INSERT INTO `bg_maple_detail` VALUES ('1379e9109d224ce784e05df3241b8329', '36ff1b75838f43efac8625df493e0df1', 'mchId', '微信支付商户号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882711370', '01', '1', '2017-01-20 11:33:53', '1', '2017-01-20 11:33:53');
INSERT INTO `bg_maple_detail` VALUES ('1490ade6a47b44f798fa97b89cbd6d36', '26e2f983954449ed84539074b02628f1', 'province', '省', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215811047', '01', '1', '2017-03-11 15:04:06', '1', '2017-03-11 15:04:06');
INSERT INTO `bg_maple_detail` VALUES ('161adf42874147d78e00d72a997783e9', 'ebd0083161064722a7c37d25a0030434', 'menuTag', '菜单数字标记', '02', '00', '10', '0', '', '00', '01', '01', '', '1484546691846', '01', '1', '2017-01-16 14:07:03', '1', '2017-01-16 14:07:03');
INSERT INTO `bg_maple_detail` VALUES ('177949f65ec3468c8c50931bf24be6b2', 'a7fef610fae34226afc5515388822a79', 'allActPrice', '实付款', '04', '00', '100', '2', '', '00', '01', '01', '', '1489199104887', '01', '1', '2017-03-11 10:26:03', '1', '2017-03-11 10:26:15');
INSERT INTO `bg_maple_detail` VALUES ('178069562df14ca8860d0cea3ae5e22d', '754773752b53495f9d452fbc9b06c37a', 'remarks', '备注', '01', '00', '1000', '0', '', '00', '01', '01', '', '1487901522779', '01', '1', '2017-02-24 09:58:12', '1', '2017-02-24 10:05:47');
INSERT INTO `bg_maple_detail` VALUES ('183052f953674417b8e20510cf9c02bf', '864215909ec741eda25b708998f777d0', 'description', '描述', '01', '00', '500', '0', '', '00', '01', '01', '', '1489815227525', '01', '1', '2017-03-18 13:34:22', '1', '2017-03-18 13:34:22');
INSERT INTO `bg_maple_detail` VALUES ('18d52145e1d641f9a12e78c196770377', '0d2f5dbae9154a4bb3788bbfc8b61176', 'summary', '摘要', '01', '00', '100', '0', '', '00', '01', '01', '', '1487916371260', '01', '1', '2017-02-24 14:06:23', '1', '2017-02-24 14:06:23');
INSERT INTO `bg_maple_detail` VALUES ('19d9cc3f61c542f4b5f60b2c9633ba0d', '742baf93ab0f4399a98c18d7adbe4622', 'media_id', '永久素材id', '01', '00', '100', '0', '', '00', '01', '01', '', '1486350230757', '01', '1', '2017-02-06 11:05:15', '1', '2017-02-06 11:05:15');
INSERT INTO `bg_maple_detail` VALUES ('1a826d69ea0d4122b31eddb0af8cea04', '3b04706d4ab8494faa53e05e822a6e82', 'jobClass', '任务', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983679664', '01', '1', '2017-01-21 15:28:20', '1', '2017-01-21 15:28:20');
INSERT INTO `bg_maple_detail` VALUES ('1b926da1358b48199cbb5cba490e5d04', '3b3eb93879584c78a5ae5ea461dbc022', 'remarks', '审核结果', '01', '00', '1000', '0', '', '00', '00', '01', '', '1488532060684', '01', '1', '2017-03-03 17:08:04', '1', '2017-03-04 13:38:53');
INSERT INTO `bg_maple_detail` VALUES ('1ce4dd8daffe4d30a03cc2d5080bc98e', '1ba234a840324c2faa9fc56e9ec9e144', 'wxQRcodeUrl', '微信二维码地址', '01', '00', '100', '0', '', '00', '01', '01', '', '1486778974209', '01', '1', '2017-02-11 10:10:45', '1', '2017-02-11 10:10:45');
INSERT INTO `bg_maple_detail` VALUES ('1d2ba2792fda4316a8a25e6af2ebbd07', 'a7fef610fae34226afc5515388822a79', 'freight', '运费', '04', '00', '100', '2', '', '00', '01', '01', '', '1489198926282', '01', '1', '2017-03-11 10:23:23', '1', '2017-03-11 10:23:23');
INSERT INTO `bg_maple_detail` VALUES ('1f7192973fff4fa89e6b19e68b99309c', 'a4dc9b54bb3e4d17ad983912730a6667', 'productStatus', '产品状态', '05', '00', '100', '0', 'com_productStatus', '00', '01', '01', '\"00\"', '1487902142334', '01', '1', '2017-02-24 10:09:02', '1', '2017-03-22 09:29:10');
INSERT INTO `bg_maple_detail` VALUES ('20863638be714a019fa891045325e8b8', '0d2f5dbae9154a4bb3788bbfc8b61176', 'imgSrc2', '滚播图', '06', '00', '1000', '0', '', '00', '01', '01', '', '1487916225225', '01', '1', '2017-02-24 14:04:01', '1', '2017-03-21 16:58:47');
INSERT INTO `bg_maple_detail` VALUES ('20e9659ee4854df5aac96d8e669ec663', '0d2f5dbae9154a4bb3788bbfc8b61176', 'imgSrc1', '长框图', '06', '00', '1000', '0', '', '00', '01', '01', '', '1487916205076', '01', '1', '2017-02-24 14:03:43', '1', '2017-03-21 16:58:42');
INSERT INTO `bg_maple_detail` VALUES ('2155fd11584c4a78aea98a18e54a6414', 'a4dc9b54bb3e4d17ad983912730a6667', 'imgSrc3', '详情图', '06', '00', '1000', '0', '', '00', '01', '01', '', '1487905038002', '01', '1', '2017-02-24 10:57:43', '1', '2017-03-21 11:25:36');
INSERT INTO `bg_maple_detail` VALUES ('2223fbb1f43f4bb4a7cd687cf7e8f4b4', 'a4dc9b54bb3e4d17ad983912730a6667', 'supplierId', '供应商Id', '05', '00', '100', '0', '', '00', '01', '01', '', '1487902142330', '01', '1', '2017-02-24 15:05:01', '1', '2017-02-24 15:05:35');
INSERT INTO `bg_maple_detail` VALUES ('2252b3cc015b4397b80193f919e15b89', 'ebd0083161064722a7c37d25a0030434', 'menuIcon', '菜单图标', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546889624', '01', '1', '2017-01-16 14:08:42', '1', '2017-01-16 14:08:42');
INSERT INTO `bg_maple_detail` VALUES ('22d65fec87014577ace005d6e47d562a', '3b04706d4ab8494faa53e05e822a6e82', 'timeExp', '时间表达式', '01', '00', '100', '0', '', '00', '01', '01', '', '1484989028911', '01', '1', '2017-01-21 17:04:35', '1', '2017-01-21 17:04:35');
INSERT INTO `bg_maple_detail` VALUES ('238bdd85325146158d55392c9ac910d4', 'ac75f2ff51a7405485cf40a6f61785f7', 'productId', '商品Id', '01', '00', '100', '0', '', '00', '01', '01', '', '1489202830435', '01', '1', '2017-03-11 11:28:22', '1', '2017-03-11 11:28:22');
INSERT INTO `bg_maple_detail` VALUES ('242c33930551440798e440acd4e5b21d', 'acb56ae4dd454b26beb150b7d08a14ed', 'invitedUserId', '被邀请人id', '01', '00', '100', '0', '', '00', '01', '01', '', '1487572014023', '01', '1', '2017-02-20 14:27:33', '1', '2017-02-20 14:27:45');
INSERT INTO `bg_maple_detail` VALUES ('248b9af9608f4dfa8faa6a1ed9755080', '26e2f983954449ed84539074b02628f1', 'receiveAddressStatus', '收货地址状态', '05', '00', '100', '0', 'com_receiveAddressStatus', '00', '01', '01', '', '1489648351212', '01', '1', '2017-03-16 15:13:43', '1', '2017-03-20 16:56:37');
INSERT INTO `bg_maple_detail` VALUES ('266ad18e881f410e9aa74b393c5e02a7', 'ac75f2ff51a7405485cf40a6f61785f7', 'originalPrice', '原价', '04', '00', '100', '0', '', '00', '01', '01', '', '1489214445503', '01', '1', '2017-03-11 14:41:02', '1', '2017-03-11 14:41:02');
INSERT INTO `bg_maple_detail` VALUES ('276866ecbb21416f94f83ab692d96b71', '36ff1b75838f43efac8625df493e0df1', 'wxAccountStatus', '微信账户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484882249480', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('2775107f29434bf68b812a8437b751cf', '36ff1b75838f43efac8625df493e0df1', 'apiKey', 'API密钥', '01', '00', '100', '0', '', '00', '01', '01', '', '1484883254237', '01', '1', '2017-01-20 12:20:56', '1', '2017-01-20 14:55:36');
INSERT INTO `bg_maple_detail` VALUES ('28056f5c4e78405187c39a0b8ec21293', '67163a4e05664fa39d5bea61b445163b', 'seleRights', '查询权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794231222', '01', '1', '2017-01-19 10:50:51', '1', '2017-01-19 10:50:51');
INSERT INTO `bg_maple_detail` VALUES ('28697e1df826455b964fe2f0ebe2db0c', '3b3eb93879584c78a5ae5ea461dbc022', 'dealAmt', '交易金额', '04', '00', '100', '2', '', '00', '01', '01', '', '1488531866162', '01', '1', '2017-03-03 17:13:41', '1', '2017-03-04 13:38:20');
INSERT INTO `bg_maple_detail` VALUES ('28d66288b90a4cc0833f8e6cec268dbe', '7928b5c3a71443e69dac9a775fb732aa', 'userStatus', '后台用户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484726215275', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('2a8ae5e5ca3d46a6a721fc885b85a1ab', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryStatus', '商品分类状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487902706438', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('2ad3266fdcdb4a6b8d384ddc5cea4a8b', 'ad181811909d4f50b3c2c802e901be84', 'dictCode', '数据字典代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542050', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('2ae5abacd2cb4893831f310fee2273e3', 'a7fef610fae34226afc5515388822a79', 'allDisPrice', '总优惠', '04', '00', '100', '2', '', '00', '01', '01', '', '1489199010607', '01', '1', '2017-03-11 10:24:55', '1', '2017-03-11 10:24:55');
INSERT INTO `bg_maple_detail` VALUES ('2b09a57a9e5b4c25ad3910caf3ca7f50', '3b3eb93879584c78a5ae5ea461dbc022', 'appUserId', '零部件销售客户', '05', '00', '100', '0', '', '00', '01', '01', '', '1488531743723', '01', '1', '2017-03-03 17:02:44', '1', '2017-03-06 11:06:45');
INSERT INTO `bg_maple_detail` VALUES ('2d1f6f9eb85f4a2987afcee0e32093af', '7928b5c3a71443e69dac9a775fb732aa', 'userCode', '后台用户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484726215270', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('2d5da37fa3764af8a4fe4cc5f735366e', '36ff1b75838f43efac8625df493e0df1', 'accessToken', '公众号的全局唯一票据', '01', '00', '1000', '0', '', '00', '00', '01', '', '1484889777852', '01', '1', '2017-01-20 13:24:19', '1', '2017-01-20 13:24:19');
INSERT INTO `bg_maple_detail` VALUES ('2ee96cb64503442097d963f7b3d15fc7', '742baf93ab0f4399a98c18d7adbe4622', 'menuBtnUrl', '网页链接', '01', '00', '1024', '0', '', '00', '01', '01', '', '1486350025045', '01', '1', '2017-02-06 11:03:26', '1', '2017-02-06 14:00:02');
INSERT INTO `bg_maple_detail` VALUES ('32431a392dfc4a5b8cf34294adb4223e', 'a7fef610fae34226afc5515388822a79', 'wlgs', '物流公司', '01', '00', '100', '0', '', '00', '01', '01', '', '1489200712122', '01', '1', '2017-03-11 10:55:07', '1', '2017-03-11 11:03:01');
INSERT INTO `bg_maple_detail` VALUES ('326d3b8d4aaf477caba774f8fa4a2ec4', 'a7fef610fae34226afc5515388822a79', 'orderProductCount', '订单商品总数', '02', '00', '100', '0', '', '00', '01', '01', '', '1489198729492', '01', '1', '2017-03-11 10:19:45', '1', '2017-03-11 10:19:45');
INSERT INTO `bg_maple_detail` VALUES ('32efc08f0b9b446e9822df5064053c40', '3b04706d4ab8494faa53e05e822a6e82', 'jobName', '任务名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983425892', '01', '1', '2017-01-21 15:27:40', '1', '2017-01-21 15:27:40');
INSERT INTO `bg_maple_detail` VALUES ('37860f50888045d4a1b4dc3e1a13f895', '742baf93ab0f4399a98c18d7adbe4622', 'menuBtnKey', '菜单KEY值', '01', '00', '128', '0', '', '00', '01', '01', '', '1486346807088', '01', '1', '2017-02-06 10:56:54', '1', '2017-02-06 14:00:10');
INSERT INTO `bg_maple_detail` VALUES ('382aec89d15d47f6959c43dd6a663be3', '3b04706d4ab8494faa53e05e822a6e82', 'triggerGroupName', '触发器组名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983820288', '01', '1', '2017-01-21 15:30:57', '1', '2017-01-21 15:30:57');
INSERT INTO `bg_maple_detail` VALUES ('38ab57dcae6c4d87afc1dd52cdc60d29', 'ac75f2ff51a7405485cf40a6f61785f7', 'summary', '摘要', '01', '00', '100', '0', '', '00', '01', '01', '', '1489202927357', '01', '1', '2017-03-11 11:32:39', '1', '2017-03-11 11:32:39');
INSERT INTO `bg_maple_detail` VALUES ('38c4182175c94eaab754e6f90e693a1b', '754773752b53495f9d452fbc9b06c37a', 'supplierCode', '供应商代号', '01', '00', '100', '0', 'gys', '00', '00', '01', '', '1487842063069', '01', '1', '2017-02-23 17:27:43', '1', '2017-03-21 22:40:04');
INSERT INTO `bg_maple_detail` VALUES ('3aa05ba404f14abca74e3f148abd0b09', 'd25535c48c444f9fb675294c8a2d8e1f', 'integralCustomerId', '积分客户', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533665086', '01', '1', '2017-03-03 17:34:43', '1', '2017-03-03 17:34:43');
INSERT INTO `bg_maple_detail` VALUES ('3c2ca551cfcc4847903932819df15dd7', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserNum', '平台用户编号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486697231587', '01', '1', '2017-02-10 11:27:33', '1', '2017-02-10 11:27:33');
INSERT INTO `bg_maple_detail` VALUES ('3fa8dba2b21c4f458acbb0bf82ce6f46', 'ebd0083161064722a7c37d25a0030434', 'menuStatus', '后台菜单状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484546688787', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('40ac3c3da5cc4dcba0ac4ad464f2deb1', '754773752b53495f9d452fbc9b06c37a', 'supplierType', '供应商类型', '05', '00', '100', '0', 'com_supplierType', '00', '01', '01', '\"01\"', '1487842063087', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_detail` VALUES ('40f190c57abe431697318c35808f44c6', 'ebd0083161064722a7c37d25a0030434', 'menuCode', '后台菜单代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688739', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('41d9fdd347ab43649c471ba0a39b61c2', 'd770d5128d82483ab6763cfbed6e847d', 'integralCountBefore', '交易前积分数量', '04', '00', '100', '2', '', '00', '01', '01', '', '1488594678573', '01', '1', '2017-03-04 10:32:11', '1', '2017-03-06 10:30:14');
INSERT INTO `bg_maple_detail` VALUES ('43deda2ca573444eaae12e6136b19356', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleStatus', '代码生成状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4427d48c707045728293a6e68256a061', 'd770d5128d82483ab6763cfbed6e847d', 'integralDealCount', '积分交易数量', '04', '00', '100', '2', '', '00', '01', '01', '', '1488594303772', '01', '1', '2017-03-04 10:25:28', '1', '2017-03-06 10:30:07');
INSERT INTO `bg_maple_detail` VALUES ('44930026a5c6434ebbd84d887c058dbc', 'a4dc9b54bb3e4d17ad983912730a6667', 'headImgSrc', '产品头像', '06', '00', '100', '0', '', '00', '01', '01', '', '1487904553196', '01', '1', '2017-02-24 10:52:42', '1', '2017-03-21 11:25:16');
INSERT INTO `bg_maple_detail` VALUES ('449950385235437e9d0878bf698ce278', '2b8a44269b4542e48e13549c8e2e14a4', 'currentPrice', '现价', '04', '00', '100', '2', '', '00', '01', '01', '', '1487906121367', '01', '1', '2017-02-24 11:16:02', '1', '2017-02-24 11:16:02');
INSERT INTO `bg_maple_detail` VALUES ('44cf249239d04a5ead9aad13c20c1ccd', '9a85215a94334cbc83bb5cf701ccf3bb', 'headImgSrc', '用户头像', '01', '00', '255', '0', '', '00', '00', '01', '', '1486706276767', '01', '1', '2017-02-10 13:59:10', '1', '2017-03-06 13:58:18');
INSERT INTO `bg_maple_detail` VALUES ('4530ca7ba6f9486581ca3f58e31d6980', '9a85215a94334cbc83bb5cf701ccf3bb', 'sex', '性别', '01', '00', '100', '0', '', '00', '01', '01', '', '1486706161606', '01', '1', '2017-02-10 13:57:07', '1', '2017-02-10 13:57:07');
INSERT INTO `bg_maple_detail` VALUES ('4616314fd35947a69e9f0edce517b4ca', '0c1ea3878cd34c8b9f99283de36f25fb', 'typeCode', '类型代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191596294', '01', '1', '2017-01-12 11:26:36', '1', '2017-01-12 11:26:36');
INSERT INTO `bg_maple_detail` VALUES ('47b028fe3bd648f0a5b1d801c3a0eddb', '3b3eb93879584c78a5ae5ea461dbc022', 'sparepartId', '零部件', '05', '00', '100', '0', '', '00', '01', '01', '', '1488531796290', '01', '1', '2017-03-03 17:03:46', '1', '2017-03-03 17:05:06');
INSERT INTO `bg_maple_detail` VALUES ('47ec4fadae18458e8b9ee8d723939085', '7928b5c3a71443e69dac9a775fb732aa', 'phone', '手机号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1484728458411', '01', '1', '2017-01-18 16:35:49', '1', '2017-01-18 16:35:49');
INSERT INTO `bg_maple_detail` VALUES ('4967fd904be74d63a14f8d28cb493c93', '9ca02063e5bb4885837ab43638413cfd', 'shopCarStatus', '购物车状态', '05', '00', '100', '0', 'com_shopCarStatus', '00', '01', '01', '', '1490233057918', '01', '1', '2017-03-23 09:38:31', '1', '2017-03-23 09:38:31');
INSERT INTO `bg_maple_detail` VALUES ('4a87a9e1c8404ad696c1b1629ab5bafc', 'a4dc9b54bb3e4d17ad983912730a6667', 'imgSrc1', '长框图', '06', '00', '100', '0', '', '00', '01', '01', '', '1487904971524', '01', '1', '2017-02-24 10:56:44', '1', '2017-03-21 11:25:24');
INSERT INTO `bg_maple_detail` VALUES ('4b1fda5a7d464922a2aa1d42641d6dd7', '9ca02063e5bb4885837ab43638413cfd', 'productStyleId', '商品规格', '05', '00', '100', '0', 'com_productStyleEffective', '00', '01', '01', '', '1490175443934', '01', '1', '2017-03-22 17:39:00', '1', '2017-03-22 17:39:00');
INSERT INTO `bg_maple_detail` VALUES ('4b44762d9bfa41d79151a9c79532f23c', '2b8a44269b4542e48e13549c8e2e14a4', 'stockNum', '库存数量', '02', '00', '100', '0', '', '00', '01', '01', '', '1487905200855', '01', '1', '2017-02-24 11:06:17', '1', '2017-02-24 11:06:17');
INSERT INTO `bg_maple_detail` VALUES ('4c86cb30de514679be2f9af313a0e1e1', 'ebd0083161064722a7c37d25a0030434', 'menuName', '后台菜单名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688782', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('4d48487873ab44f6b5cae2a9f5092c23', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleType', '代码生成类型', '05', '00', '100', '0', 'bg_mapleType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4e80f2fdfb844b8d99f00a66306bd664', '2b0255e6416a49669ba92dd90732c2ab', 'isFinal', '是否最终分类', '05', '00', '100', '0', 'com_sf', '00', '01', '01', '\"00\"', '1489110802696', '01', '1', '2017-03-10 09:55:01', '1', '2017-03-10 09:55:16');
INSERT INTO `bg_maple_detail` VALUES ('4fcb3375c7f5476098db5160c20dcfd0', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryType', '商品分类类型', '05', '00', '100', '0', 'com_productCategoryType', '00', '01', '01', '\"01\"', '1487902706437', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('51662d1b632c4fbe86630b11cbd3546d', 'a7fef610fae34226afc5515388822a79', 'supplierName', '供应商', '01', '00', '100', '0', '', '00', '00', '01', '', '1489199207939', '01', '1', '2017-03-11 10:30:34', '1', '2017-03-11 10:31:32');
INSERT INTO `bg_maple_detail` VALUES ('52be3e073752470cb93d75b2be92b5e1', 'a4dc9b54bb3e4d17ad983912730a6667', 'summary', '摘要', '01', '00', '1000', '0', '', '00', '01', '01', '', '1487903197089', '01', '1', '2017-02-24 10:35:16', '1', '2017-02-24 10:42:00');
INSERT INTO `bg_maple_detail` VALUES ('530502b6cc5b45dfa9d4c751d095de9c', '7928b5c3a71443e69dac9a775fb732aa', 'remarks', '备注信息', '01', '00', '255', '0', '', '00', '01', '01', '', '1484728674804', '01', '1', '2017-01-18 16:38:12', '1', '2017-01-18 16:38:12');
INSERT INTO `bg_maple_detail` VALUES ('530694467ec34ee494604b51f05d76b9', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailStatus', '规格分类详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487913725176', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('531737534ab649f9bd4ced96cda1ffda', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailType', '代码生成详情类型', '05', '00', '100', '0', 'bg_mapleDetailType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('54398981fb824b04bafb3f1d3cd48f6f', '27a853950d0e4876ba0eccf8d7e2dd8f', 'status', '状态', '05', '00', '100', '0', '', '00', '01', '01', '\"00\"', '4', '01', '1', '2017-01-11 17:11:35', '1', '2017-03-20 16:29:19');
INSERT INTO `bg_maple_detail` VALUES ('54948f8795ea4e3da3f9189a69442e9e', 'ac75f2ff51a7405485cf40a6f61785f7', 'productName', '商品名称', '01', '00', '100', '0', '', '00', '00', '01', '', '1489202905298', '01', '1', '2017-03-11 11:28:45', '1', '2017-04-11 16:28:19');
INSERT INTO `bg_maple_detail` VALUES ('54d7b185a0f040dfb95a4d712205d3ed', 'd770d5128d82483ab6763cfbed6e847d', 'integralCountAfter', '交易后积分数量', '04', '00', '100', '2', '', '00', '01', '01', '', '1488594736670', '01', '1', '2017-03-04 10:32:41', '1', '2017-03-06 10:30:20');
INSERT INTO `bg_maple_detail` VALUES ('5712b8ad5b1c4589a7bb5a40aa391962', '0c1ea3878cd34c8b9f99283de36f25fb', 'defaultValue', '默认值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191753948', '01', '1', '2017-01-12 11:29:14', '1', '2017-01-12 11:29:14');
INSERT INTO `bg_maple_detail` VALUES ('5777ff112dcc4b57a9314a9b7fcf813d', '36ff1b75838f43efac8625df493e0df1', 'wxAccountName', '微信账户名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882249398', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('595ebbf50b574d8c8a40736b0f5a57c4', '3b3eb93879584c78a5ae5ea461dbc022', 'checkTime', '审核日期', '03', '00', '100', '0', '', '00', '00', '01', '', '1488532060685', '01', '1', '2017-03-03 17:06:51', '1', '2017-03-04 13:39:01');
INSERT INTO `bg_maple_detail` VALUES ('59dec13e22c2402a81d18c9040234b6b', 'c35fd480442c4bd6925e5e508daec4ec', 'ewrwType', 'eqwe类型', '05', '00', '100', '0', 'bg_ewrwType', '00', '01', '01', '\"01\"', '1489989316793', '01', '1', '2017-03-20 13:55:17', '1', '2017-03-20 13:55:17');
INSERT INTO `bg_maple_detail` VALUES ('5cc9bd6c27384ce9b6ff09985acb5c69', '26e2f983954449ed84539074b02628f1', 'detail', '详细地址', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215944434', '01', '1', '2017-03-11 15:06:37', '1', '2017-03-11 15:06:37');
INSERT INTO `bg_maple_detail` VALUES ('5f8f85faf8e94e26a5951698f00437d1', '864215909ec741eda25b708998f777d0', 'lbtCode', '轮播图代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1489815060665', '01', '1', '2017-03-18 13:31:01', '1', '2017-03-18 13:31:01');
INSERT INTO `bg_maple_detail` VALUES ('5fc96d4d2c2e4f2ab8d145905663cc85', '3b3eb93879584c78a5ae5ea461dbc022', 'count', '出售数量', '02', '00', '100', '0', '', '00', '01', '01', '', '1488531866161', '01', '1', '2017-03-03 17:04:56', '1', '2017-03-03 17:04:56');
INSERT INTO `bg_maple_detail` VALUES ('601891a9051849e98ccc4ad15298d345', '4e272d40a7cf40009f6a47b0e374c2ba', 'productId', '商品', '05', '00', '100', '0', 'com_productEffective', '00', '01', '01', '', '1489975610231', '01', '1', '2017-03-20 10:07:40', '1', '2017-03-20 10:07:40');
INSERT INTO `bg_maple_detail` VALUES ('62513f06e75d4206935933bcc27e634f', 'a7fef610fae34226afc5515388822a79', 'walletPay', '钱包支付', '04', '00', '100', '0', '', '00', '01', '01', '', '1489199104886', '01', '1', '2017-03-11 11:13:18', '1', '2017-03-11 11:13:54');
INSERT INTO `bg_maple_detail` VALUES ('62cc898b340b484b918c3126def18303', 'a4dc9b54bb3e4d17ad983912730a6667', 'imgSrc2', '滚播图', '06', '00', '1000', '0', '', '00', '01', '01', '', '1487905006817', '01', '1', '2017-02-24 10:57:15', '1', '2017-03-21 11:25:32');
INSERT INTO `bg_maple_detail` VALUES ('62ef773c16164f0a955d5b845930d69c', '1ba234a840324c2faa9fc56e9ec9e144', 'wxQRcodeExpiry', '微信二维码有效期', '03', '00', '100', '0', '', '00', '01', '01', '', '1486779058463', '01', '1', '2017-02-11 10:12:54', '1', '2017-02-11 10:12:54');
INSERT INTO `bg_maple_detail` VALUES ('66b03a304168463e81938cf9c9361d94', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryCode', '商品分类代号', '01', '00', '100', '0', 'spfl', '00', '00', '01', '', '1487902706435', '01', '1', '2017-02-24 10:18:26', '1', '2017-03-21 22:41:18');
INSERT INTO `bg_maple_detail` VALUES ('688f191c23ef4d1f98a3559e470cf6e4', '27a853950d0e4876ba0eccf8d7e2dd8f', 'code', '代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 16:52:43', '1', '2017-01-11 16:52:43');
INSERT INTO `bg_maple_detail` VALUES ('6c50050343a24590b511ca781c1b2e0f', '864215909ec741eda25b708998f777d0', 'lbtImgSrc', '轮播图图片', '06', '00', '100', '0', '', '00', '01', '01', '', '1489815123566', '01', '1', '2017-03-18 13:33:06', '1', '2017-03-21 10:11:31');
INSERT INTO `bg_maple_detail` VALUES ('6cc089172fcd444b97d88f7569c898cf', 'ac75f2ff51a7405485cf40a6f61785f7', 'headImgSrc', '产品头像', '01', '00', '100', '0', '', '00', '01', '01', '', '1489203211009', '01', '1', '2017-03-11 11:33:46', '1', '2017-03-11 11:33:46');
INSERT INTO `bg_maple_detail` VALUES ('6ce2076ea1334fcdac96c1e044cea7b9', '3b04706d4ab8494faa53e05e822a6e82', 'triggerName', '触发器名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983701452', '01', '1', '2017-01-21 15:28:43', '1', '2017-01-21 15:28:43');
INSERT INTO `bg_maple_detail` VALUES ('6d5509c750f84076b65b019c7a49828f', 'ebd0083161064722a7c37d25a0030434', 'menuUrl', '菜单链接', '01', '00', '255', '0', '', '00', '01', '01', '', '1484546828872', '01', '1', '2017-01-16 14:08:02', '1', '2017-01-16 14:08:02');
INSERT INTO `bg_maple_detail` VALUES ('6dc16ba4acbe4c37b634314a35a9a16f', '9ca02063e5bb4885837ab43638413cfd', 'count', '数量', '02', '00', '100', '0', '', '00', '01', '01', '', '1490175542087', '01', '1', '2017-03-22 17:39:16', '1', '2017-03-22 17:39:16');
INSERT INTO `bg_maple_detail` VALUES ('6e0782a153c74e2f967dc04a4e655326', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnName', '微信菜单按钮名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1486346372176', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('6e1c83d28e19411ab4778f546e107435', '27a853950d0e4876ba0eccf8d7e2dd8f', 'type', '类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 17:08:55', '1', '2017-01-11 17:08:55');
INSERT INTO `bg_maple_detail` VALUES ('6f33f41da04d49a7980246d2c1988904', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnCode', '微信菜单按钮代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486346372173', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('701b491c937244a8a46a21d6b3e50a5c', '26e2f983954449ed84539074b02628f1', 'city', '城市', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215847276', '01', '1', '2017-03-11 15:04:23', '1', '2017-03-11 15:04:23');
INSERT INTO `bg_maple_detail` VALUES ('72694e08c5f249368b697395f3d4a4b1', 'a7fef610fae34226afc5515388822a79', 'payMethod', '付款方式', '05', '00', '100', '0', 'com_payMethod', '00', '01', '01', '', '1489200261054', '01', '1', '2017-03-11 10:45:30', '1', '2017-03-11 10:46:05');
INSERT INTO `bg_maple_detail` VALUES ('72a220e9fbe446e0aba82e240c5ee5c0', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryName', '商品分类名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902706436', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('72a287c567e343afb5566afaa016a31b', 'a7fef610fae34226afc5515388822a79', 'orderType', '订单类型', '05', '00', '100', '0', 'com_orderType', '00', '01', '01', '\"01\"', '1489197833016', '01', '1', '2017-03-11 10:03:53', '1', '2017-03-11 10:03:53');
INSERT INTO `bg_maple_detail` VALUES ('7386d05ed06547c39f660e6ef68f1a98', '46359ab1ffb041c196e16b95a654df12', 'productId', '产品id', '01', '00', '100', '0', '', '00', '01', '01', '', '1487917378968', '01', '1', '2017-02-24 14:23:59', '1', '2017-02-24 14:23:59');
INSERT INTO `bg_maple_detail` VALUES ('74c218455f564be4bc0a40c84eb10249', '3b3eb93879584c78a5ae5ea461dbc022', 'orderTime', '订单日期', '03', '00', '100', '0', '', '00', '01', '01', '', '1488531963735', '01', '1', '2017-03-03 17:06:26', '1', '2017-03-03 17:06:26');
INSERT INTO `bg_maple_detail` VALUES ('74f19ae246334fec8c8cfb36ac907c7b', '26e2f983954449ed84539074b02628f1', 'appUserId', '平台用户', '05', '00', '100', '0', 'com_appUserEffective', '00', '01', '01', '', '1489215097087', '01', '1', '2017-03-16 15:49:15', '1', '2017-03-16 15:49:57');
INSERT INTO `bg_maple_detail` VALUES ('7598d6bfa9b14f1bbc2882244a0a90d6', 'a7fef610fae34226afc5515388822a79', 'orderCode', '订单代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1489197832982', '01', '1', '2017-03-11 10:03:53', '1', '2017-03-11 10:03:53');
INSERT INTO `bg_maple_detail` VALUES ('762f024f88d640ada0f36a59a259f7fb', '67163a4e05664fa39d5bea61b445163b', 'delRights', '删除权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794193285', '01', '1', '2017-01-19 10:50:08', '1', '2017-01-19 10:50:08');
INSERT INTO `bg_maple_detail` VALUES ('76e8bed04d6b4e5f8acdb81b6223ccf3', 'c9da8f2d57774bbbad13030135b6a1cb', 'controllerPackage', '控制器包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150518096', '01', '1', '2017-01-12 00:01:58', '1', '2017-01-12 00:01:58');
INSERT INTO `bg_maple_detail` VALUES ('778217d2d3e54a04b2621a8c2ada60e5', '9a85215a94334cbc83bb5cf701ccf3bb', 'phone', '电话号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1486704900464', '01', '1', '2017-02-10 13:41:04', '1', '2017-02-10 13:53:45');
INSERT INTO `bg_maple_detail` VALUES ('77d5c1c3c1c8425ea9536c64b3614c8e', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleCode', '代码生成代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('77dafcf782114bff9e1a53bb5854c5d3', 'd770d5128d82483ab6763cfbed6e847d', 'integralNoteCode', '积分记录代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488532875558', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
INSERT INTO `bg_maple_detail` VALUES ('78cbe66ee65441919b2e68df61f6f88c', 'ac75f2ff51a7405485cf40a6f61785f7', 'productStyleId', '商品类型id', '01', '00', '100', '0', '', '00', '01', '01', '', '1489203171352', '01', '1', '2017-04-11 16:26:58', '1', '2017-04-11 16:27:35');
INSERT INTO `bg_maple_detail` VALUES ('7a5d5343cc4d4a8c9b027aea5fabffbe', '3b3eb93879584c78a5ae5ea461dbc022', 'sparepartDealCode', '零部件交易订单号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488531290179', '01', '1', '2017-03-03 16:54:50', '1', '2017-03-04 13:37:20');
INSERT INTO `bg_maple_detail` VALUES ('7ad405a7439c46cf93fd4bda1bb3037b', '7928b5c3a71443e69dac9a775fb732aa', 'password', '密码', '01', '00', '255', '0', '', '00', '01', '01', '', '1484726215272', '01', '1', '2017-01-18 16:36:25', '1', '2017-01-18 16:41:14');
INSERT INTO `bg_maple_detail` VALUES ('7bc20bf03cd640fe8ea4933be62c9abb', 'c723817cc46c4ff885b9f1a80702c6a9', 'phone', '手机号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1488508289866', '01', '1', '2017-03-03 10:37:48', '1', '2017-03-03 14:35:46');
INSERT INTO `bg_maple_detail` VALUES ('7d01f3725566448fba2ecf239dbe79a3', 'a7fef610fae34226afc5515388822a79', 'receiveAddressId', '收货地址', '05', '00', '100', '0', 'com_receiveAddressEffective', '00', '01', '01', '', '1489199503005', '01', '1', '2017-03-11 10:35:20', '1', '2017-03-11 14:51:18');
INSERT INTO `bg_maple_detail` VALUES ('7d765b5a1f194786ba6e7112bce8c3e0', 'c723817cc46c4ff885b9f1a80702c6a9', 'level', '级别', '02', '00', '100', '0', '', '00', '00', '01', '', '1488521020635', '01', '1', '2017-03-03 14:05:07', '1', '2017-03-03 14:05:07');
INSERT INTO `bg_maple_detail` VALUES ('7fc22e1d11544a1b83995b9edd258bbf', 'a7fef610fae34226afc5515388822a79', 'tradeNum', '交易号', '01', '00', '100', '0', '', '00', '01', '01', '', '1489200122527', '01', '1', '2017-03-11 10:44:06', '1', '2017-03-11 10:44:06');
INSERT INTO `bg_maple_detail` VALUES ('80b50a3f08c74d0ab3ee217f1edbc54a', '3b3eb93879584c78a5ae5ea461dbc022', 'checkId', '审核人', '01', '00', '100', '0', '', '00', '00', '01', '', '1488532020455', '01', '1', '2017-03-03 17:07:25', '1', '2017-03-04 13:38:49');
INSERT INTO `bg_maple_detail` VALUES ('81a7ae095a374c31a2dd6f7995806b0d', 'c723817cc46c4ff885b9f1a80702c6a9', 'integralCustomerType', '积分客户类型', '05', '00', '100', '0', 'com_integralCustomerType', '00', '01', '01', '\"01\"', '1488508256595', '01', '1', '2017-03-03 10:30:57', '1', '2017-03-03 10:30:57');
INSERT INTO `bg_maple_detail` VALUES ('83dd1de384524795af254eff73adf927', '36ff1b75838f43efac8625df493e0df1', 'wxAccountCode', '微信账户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882249321', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('84c0bdb39c704c1b84f970c3030267ef', '307d768ee19e45b09b016970caf257ca', 'integral3', '4S店可获积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528285133', '01', '1', '2017-03-03 16:05:11', '1', '2017-03-03 16:05:11');
INSERT INTO `bg_maple_detail` VALUES ('878712f1b94b4a21843ef4d4bb5d096d', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailName', '规格分类详情名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913725171', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('878e2c01af44411b957d4203417bb024', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailStatus', '代码生成详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('89a5a14b9b7b44bab50594b6325cf415', '3b04706d4ab8494faa53e05e822a6e82', 'crontabName', '定时任务名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983375383', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('89b3d766603d4b4ea6ff015949c5b433', '0d2f5dbae9154a4bb3788bbfc8b61176', 'headImgSrc', '分类头像', '06', '00', '1000', '0', '', '00', '01', '01', '', '1487916024410', '01', '1', '2017-02-24 14:01:39', '1', '2017-03-21 16:58:36');
INSERT INTO `bg_maple_detail` VALUES ('89cbe149059043df967222740130d3cb', '26e2f983954449ed84539074b02628f1', 'street', '街道', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215930189', '01', '1', '2017-03-11 15:05:43', '1', '2017-03-11 15:05:43');
INSERT INTO `bg_maple_detail` VALUES ('8b5c576423c64414b5987f02568b06d9', 'd770d5128d82483ab6763cfbed6e847d', 'integralDealStatus', '积分交易状态', '05', '00', '100', '0', 'com_integralDealStatus', '00', '00', '01', '\"00\"', '1488532875562', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-04 10:24:54');
INSERT INTO `bg_maple_detail` VALUES ('8bd6080677884a63a4fb72d49ccea2f9', '754773752b53495f9d452fbc9b06c37a', 'supplierStatus', '供应商状态', '05', '00', '100', '0', 'com_supplierStatus', '00', '00', '01', '\"00\"', '1487842063088', '01', '1', '2017-02-23 17:27:43', '1', '2017-03-21 11:13:59');
INSERT INTO `bg_maple_detail` VALUES ('8fb2330b8dd94af28f4823d86abd8a45', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailCode', '规格分类详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913725167', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('8fdc96238cc54846be115986ea4be2a9', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleStatus', '商品规格状态', '05', '00', '100', '0', 'com_productStatus', '00', '01', '01', '\"00\"', '1487902538810', '01', '1', '2017-02-24 10:15:39', '1', '2017-03-22 09:31:44');
INSERT INTO `bg_maple_detail` VALUES ('90f2a237fa464fdba1f77b9b26ae0d7f', '307d768ee19e45b09b016970caf257ca', 'sparepartName', '零配件名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1488528018793', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_detail` VALUES ('942d109f9052435eafa23c760e763fe0', '754773752b53495f9d452fbc9b06c37a', 'linkPhone', '联系电话', '01', '00', '100', '0', '', '00', '01', '01', '', '1487901522777', '01', '1', '2017-02-24 09:59:04', '1', '2017-02-24 09:59:04');
INSERT INTO `bg_maple_detail` VALUES ('969f5383c0a54b479439227e2e8adf24', '0c1ea3878cd34c8b9f99283de36f25fb', 'isKey', '是否主键', '05', '00', '100', '0', '', '00', '01', '01', '\"00\"', '1484191616190', '01', '1', '2017-01-12 11:28:16', '1', '2017-01-17 15:15:51');
INSERT INTO `bg_maple_detail` VALUES ('995aa3b2af92499089cdc88d6ab384b3', 'd770d5128d82483ab6763cfbed6e847d', 'integralNoteName', '积分记录名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1488532875559', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
INSERT INTO `bg_maple_detail` VALUES ('99a0aa832cda43bca5a13ed45c2dd441', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteUserId', '邀请人id', '01', '00', '100', '0', '', '00', '01', '01', '', '1487571940370', '01', '1', '2017-02-20 14:26:50', '1', '2017-02-20 14:27:53');
INSERT INTO `bg_maple_detail` VALUES ('9a92e5a8bdb04d04b01fcb0553c9b030', 'd770d5128d82483ab6763cfbed6e847d', 'integralNoteType', '积分记录类型', '05', '00', '100', '0', 'com_integralNoteType', '00', '01', '01', '\"01\"', '1488532875561', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
INSERT INTO `bg_maple_detail` VALUES ('9ae2dab2efcc41aab876f03b7395c8e1', '67163a4e05664fa39d5bea61b445163b', 'roleCode', '后台角色代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484794097594', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('9b4084cf4fef4c7eb972a4c909cb367e', '2b8a44269b4542e48e13549c8e2e14a4', 'curType', '货币种类', '05', '00', '100', '0', 'com_curType', '00', '01', '01', '01', '1487905809817', '01', '1', '2017-03-10 17:15:59', '1', '2017-03-10 17:30:23');
INSERT INTO `bg_maple_detail` VALUES ('9bdbccb409504d26b9ce4b885d8aac6c', '26e2f983954449ed84539074b02628f1', 'receicerName', '收货人', '01', '00', '100', '0', '', '00', '01', '01', '', '1489215097088', '01', '1', '2017-03-11 14:52:16', '1', '2017-03-11 14:52:16');
INSERT INTO `bg_maple_detail` VALUES ('9bf5394fccf044c6b01aac17acdffea6', 'ad181811909d4f50b3c2c802e901be84', 'dictStatus', '数据字典状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484529542053', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('9d29ed260eed4306982adfb52b1f1e6b', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserStatus', '平台用户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1486696707784', '01', '1', '2017-02-10 11:18:28', '1', '2017-02-10 11:18:28');
INSERT INTO `bg_maple_detail` VALUES ('9d5252681e574c9b9673296c1b73aad7', '3b04706d4ab8494faa53e05e822a6e82', 'crontabStatus', '定时任务状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484983375410', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('9e08c85a4a7149c3af63fff1b2eac9fa', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnStatus', '微信菜单按钮状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1486346372203', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('a2b24eba68f742de864f18987eecf80e', 'c723817cc46c4ff885b9f1a80702c6a9', 'integralCustomerCode', '积分客户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488508256550', '01', '1', '2017-03-03 10:30:57', '1', '2017-03-03 10:30:57');
INSERT INTO `bg_maple_detail` VALUES ('a2e09db55f0c43beb3fe9f09f62d2cd0', '67163a4e05664fa39d5bea61b445163b', 'addRights', '新增权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794135451', '01', '1', '2017-01-19 10:49:47', '1', '2017-01-19 10:49:47');
INSERT INTO `bg_maple_detail` VALUES ('a35792a023f848ac897f4a7753ea6938', '0c1ea3878cd34c8b9f99283de36f25fb', 'isNull', '是否null', '01', '00', '100', '9', '', '00', '01', '01', '\"01\"', '1484191654251', '01', '1', '2017-01-12 11:27:34', '1', '2017-01-17 16:01:38');
INSERT INTO `bg_maple_detail` VALUES ('a370afc956194930ab442da6fa7ec051', 'a7fef610fae34226afc5515388822a79', 'sendTime', '发货时间', '03', '00', '100', '0', '', '00', '00', '01', '', '1489199875559', '01', '1', '2017-03-11 10:39:26', '1', '2017-03-11 10:39:36');
INSERT INTO `bg_maple_detail` VALUES ('a3a1939b310d41349f626e6406a20686', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleCode', '商品规格代号', '01', '00', '100', '0', 'spgg', '00', '00', '01', '', '1487902538805', '01', '1', '2017-02-24 10:15:39', '1', '2017-03-21 22:40:45');
INSERT INTO `bg_maple_detail` VALUES ('a512656b82cb4c729d21c0720640e34b', '864215909ec741eda25b708998f777d0', 'lbtType', '轮播图类型', '05', '00', '100', '0', 'com_lbtType', '00', '01', '01', '\"01\"', '1489815060698', '01', '1', '2017-03-18 13:31:01', '1', '2017-03-18 13:31:01');
INSERT INTO `bg_maple_detail` VALUES ('a5b57af5f936434bad463f687f24cc1e', '9a85215a94334cbc83bb5cf701ccf3bb', 'remarks', '备注信息', '01', '00', '100', '0', '', '00', '01', '01', '', '1488779742618', '01', '1', '2017-03-06 13:56:06', '1', '2017-03-06 13:56:06');
INSERT INTO `bg_maple_detail` VALUES ('a795aac6efd64eb4b81bafcfdba74f19', 'ebd0083161064722a7c37d25a0030434', 'menuType', '后台菜单类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484546688785', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('a863984b226847508fa91b8bfc84384a', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailType', '规格分类详情类型', '05', '00', '100', '0', 'bg_styleCategoryDetailType', '00', '01', '01', '\"01\"', '1487913725174', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('a9d320b6ba534243a2e87ca11cf50eb3', '7928b5c3a71443e69dac9a775fb732aa', 'userType', '后台用户类型', '05', '00', '100', '0', 'bg_userType', '00', '01', '01', '\"01\"', '1484726215274', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-19 14:46:05');
INSERT INTO `bg_maple_detail` VALUES ('aba1d712b9894f07bce792563f35ba21', '3b04706d4ab8494faa53e05e822a6e82', 'crontabCode', '定时任务代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983375345', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('acaf2415cdd243a58874cc0172873770', 'c35fd480442c4bd6925e5e508daec4ec', 'ewrwCode', 'eqwe代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1489989316750', '01', '1', '2017-03-20 13:55:17', '1', '2017-03-20 13:55:17');
INSERT INTO `bg_maple_detail` VALUES ('acccd70e48194133bb92afb0585b1de2', '36ff1b75838f43efac8625df493e0df1', 'wxAccountType', '微信账户类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484882249476', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('acfc7ae9467e4a79bd3387f6011a632d', 'e43dbde47f8e4b2fab9d226b9ce3976b', 'styleCategoryDetailCode', '规格分类详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913761117', '01', '1', '2017-02-24 13:22:41', '1', '2017-02-24 13:22:41');
INSERT INTO `bg_maple_detail` VALUES ('add05ce1e60842eda8a8c53ee7c3be57', 'a7fef610fae34226afc5515388822a79', 'supplierId', '供应商id', '05', '00', '100', '0', 'com_supplierEffective', '00', '01', '01', '', '1489199437654', '01', '1', '2017-03-11 10:31:09', '1', '2017-03-11 16:17:21');
INSERT INTO `bg_maple_detail` VALUES ('ae7869c4b1ae443194ec367d56373a08', 'a4dc9b54bb3e4d17ad983912730a6667', 'introduction', '简介', '01', '00', '100', '0', '', '00', '01', '01', '', '1487903728670', '01', '1', '2017-02-24 10:41:10', '1', '2017-02-24 10:42:07');
INSERT INTO `bg_maple_detail` VALUES ('ae880d3d066b4fa7851d89c9732be099', '67163a4e05664fa39d5bea61b445163b', 'roleRights', '角色权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794097660', '01', '1', '2017-01-19 10:57:05', '1', '2017-01-19 10:57:32');
INSERT INTO `bg_maple_detail` VALUES ('af1467b9630543038a2784eba02e562a', '67163a4e05664fa39d5bea61b445163b', 'roleType', '后台角色类型', '05', '00', '100', '0', 'bgRoleType', '00', '01', '01', '\"01\"', '1484794097628', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 14:46:28');
INSERT INTO `bg_maple_detail` VALUES ('af635e4db1894f64b7de2ac306bb7d96', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeType', '用户邀请码类型', '05', '00', '100', '0', 'com_appInviteCodeType', '00', '01', '01', '\"01\"', '1486778955094', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('b10bf48749f24c639c45465c52e434d3', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleName', '商品规格名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902538807', '01', '1', '2017-02-24 10:15:39', '1', '2017-03-21 13:52:28');
INSERT INTO `bg_maple_detail` VALUES ('b2904db80ab24c398ae0acb4a2eb4845', '46359ab1ffb041c196e16b95a654df12', 'categoryType', '分类类型', '05', '00', '100', '0', 'com_categoryType', '00', '01', '01', '\"01\"', '1487917239221', '01', '1', '2017-02-24 14:20:39', '1', '2017-02-24 14:20:39');
INSERT INTO `bg_maple_detail` VALUES ('b3262a3697484cd48874443d34b7e4f9', '307d768ee19e45b09b016970caf257ca', 'allIntegral', '总积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528032670', '01', '1', '2017-03-03 16:02:31', '1', '2017-03-03 16:02:31');
INSERT INTO `bg_maple_detail` VALUES ('b33470b8c56d4089a237c9f3ebac8565', 'a7fef610fae34226afc5515388822a79', 'allPrice', '商品总价', '04', '00', '100', '2', '', '00', '01', '01', '', '1489198792425', '01', '1', '2017-03-11 10:22:02', '1', '2017-03-11 10:22:02');
INSERT INTO `bg_maple_detail` VALUES ('b5d52ee47e254dbaa84d14ee3b7bbeb0', 'ac75f2ff51a7405485cf40a6f61785f7', 'productStyleName', '商品类型名称', '01', '00', '100', '0', '', '00', '00', '01', '', '1489203171353', '01', '1', '2017-03-11 11:33:25', '1', '2017-04-11 16:28:27');
INSERT INTO `bg_maple_detail` VALUES ('b616a6b68eaa4e01ae4e886311616acf', 'ac75f2ff51a7405485cf40a6f61785f7', 'count', '购买数量', '02', '00', '100', '0', '', '00', '01', '01', '', '1489214640271', '01', '1', '2017-03-11 14:44:11', '1', '2017-03-11 14:44:16');
INSERT INTO `bg_maple_detail` VALUES ('b7a963fd39df4a2e851ba52cf063221b', '9a85215a94334cbc83bb5cf701ccf3bb', 'brithday', '生日', '03', '00', '100', '0', '', '00', '01', '01', '', '1486706378774', '01', '1', '2017-02-10 14:02:00', '1', '2017-02-10 14:02:00');
INSERT INTO `bg_maple_detail` VALUES ('ba109fd64c614cb0a56d54d6fbf382cf', '2b0255e6416a49669ba92dd90732c2ab', 'productId', '产品编号', '05', '00', '100', '0', 'com_productEffective', '00', '01', '01', '', '1487912483487', '01', '1', '2017-02-24 13:48:16', '1', '2017-03-10 10:42:27');
INSERT INTO `bg_maple_detail` VALUES ('ba9fb9a227834e38b0e25803e397088e', 'e43dbde47f8e4b2fab9d226b9ce3976b', 'styleCategoryDetailName', '规格分类详情名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913761137', '01', '1', '2017-02-24 13:22:41', '1', '2017-02-24 13:22:41');
INSERT INTO `bg_maple_detail` VALUES ('bbc0b7ca758b4ebca734cbbd2c33254f', '9a85215a94334cbc83bb5cf701ccf3bb', 'level', '级别', '02', '00', '100', '0', '', '00', '00', '01', '', '1488780497687', '01', '1', '2017-03-06 14:08:52', '1', '2017-03-06 14:08:52');
INSERT INTO `bg_maple_detail` VALUES ('bc13f8a43a164e658ea1a670e4270c91', 'd25535c48c444f9fb675294c8a2d8e1f', 'name', '扩展属性名', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533735389', '01', '1', '2017-03-03 17:35:55', '1', '2017-03-03 17:35:55');
INSERT INTO `bg_maple_detail` VALUES ('bd128aaf592c43f3af46ad88e87ade76', '46359ab1ffb041c196e16b95a654df12', 'categoryStatus', '分类状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487917239222', '01', '1', '2017-02-24 14:20:39', '1', '2017-02-24 14:20:39');
INSERT INTO `bg_maple_detail` VALUES ('bd4bc87a29a64199a20d9f0ee8de6800', '2b0255e6416a49669ba92dd90732c2ab', 'styleCategoryName', '规格分类名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487912483493', '01', '1', '2017-02-24 13:01:23', '1', '2017-02-24 13:01:23');
INSERT INTO `bg_maple_detail` VALUES ('be775a41a6544563b93b6a3e17ca727a', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailName', '代码生成详情名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('bf4e556e919a4a9c893e5633e439df1a', '754773752b53495f9d452fbc9b06c37a', 'linkman', '联系人', '01', '00', '100', '0', '', '00', '01', '01', '', '1487901495142', '01', '1', '2017-02-24 09:58:40', '1', '2017-02-24 09:58:40');
INSERT INTO `bg_maple_detail` VALUES ('bf7f125dafb94bd9862d6fbabb490d3e', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserCode', '平台用户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486696707746', '01', '1', '2017-02-10 11:18:28', '1', '2017-02-10 11:18:28');
INSERT INTO `bg_maple_detail` VALUES ('c0dea14e2ab54b5581ca065caf00415f', '3b04706d4ab8494faa53e05e822a6e82', 'startupTimes', '启动次数', '02', '00', '10', '0', '', '00', '00', '01', '', '1484983425888', '01', '1', '2017-01-21 16:52:48', '1', '2017-01-21 16:56:12');
INSERT INTO `bg_maple_detail` VALUES ('c275150ed27b4dc0b840c398a3af1797', 'c723817cc46c4ff885b9f1a80702c6a9', 'integralCustomerStatus', '积分客户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1488508256628', '01', '1', '2017-03-03 10:30:57', '1', '2017-03-03 10:30:57');
INSERT INTO `bg_maple_detail` VALUES ('c317d09b2d8e4748ae5c4c64f5e3622c', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserType', '平台用户类型', '05', '00', '100', '0', 'com_appUserType', '00', '01', '01', '\"01\"', '1486696707782', '01', '1', '2017-02-10 11:18:28', '1', '2017-02-10 11:18:28');
INSERT INTO `bg_maple_detail` VALUES ('c419c1f3fa444e8e9f1c48ddb2948586', 'c723817cc46c4ff885b9f1a80702c6a9', 'roleId', '角色', '05', '00', '100', '0', '', '00', '01', '01', '', '1488508256540', '01', '1', '2017-03-03 10:40:03', '1', '2017-03-03 14:35:53');
INSERT INTO `bg_maple_detail` VALUES ('c56f03bc5c864dcd854f57ea8e972436', '36ff1b75838f43efac8625df493e0df1', 'appSecret', 'AppSecret(应用密钥)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882415113', '01', '1', '2017-01-20 11:21:24', '1', '2017-01-20 11:21:24');
INSERT INTO `bg_maple_detail` VALUES ('c67ea20d301d4f29ac881f9cf300a2ba', 'ad181811909d4f50b3c2c802e901be84', 'dictName', '数据字典名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542051', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('c75e78c566cf470caed51bc6b81fb37e', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteType', '邀请类型', '05', '00', '100', '0', 'com_inviteType', '00', '01', '01', '\"01\"', '1487571659417', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('c997cc1f6d8a4fbbaa5e4eb0490c501a', '0c1ea3878cd34c8b9f99283de36f25fb', 'decimalLength', '小数长度', '02', '00', '1', '0', '', '00', '01', '01', '0', '1484191459111', '01', '1', '2017-01-12 11:24:19', '1', '2017-01-16 00:00:07');
INSERT INTO `bg_maple_detail` VALUES ('c9f06cd7cfb24664985def6ec4a81392', '3b3eb93879584c78a5ae5ea461dbc022', 'sparepartDealStatus', '零部件交易审核状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1488531290184', '01', '1', '2017-03-03 16:54:50', '1', '2017-03-03 17:00:33');
INSERT INTO `bg_maple_detail` VALUES ('ca2fd49e21134489a0ca28a92fd98cfa', 'a4dc9b54bb3e4d17ad983912730a6667', 'productType', '产品类型', '05', '00', '100', '0', 'com_productType', '00', '01', '01', '\"01\"', '1487902142333', '01', '1', '2017-02-24 10:09:02', '1', '2017-02-24 10:09:02');
INSERT INTO `bg_maple_detail` VALUES ('cacac26f7cc8437084eece3beb398c58', '307d768ee19e45b09b016970caf257ca', 'sparepartCode', '零配件代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488528018790', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_detail` VALUES ('cb36f7e6cca94d61b6ca68b6b6d62746', '3b04706d4ab8494faa53e05e822a6e82', 'jobGroupName', '任务组名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983661875', '01', '1', '2017-01-21 15:27:58', '1', '2017-01-21 15:27:58');
INSERT INTO `bg_maple_detail` VALUES ('cefd78bcd562426aa7935ff1ec2ebaab', '307d768ee19e45b09b016970caf257ca', 'integral4', '销售员可获积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1489648295876', '01', '1', '2017-03-16 15:12:03', '1', '2017-03-16 15:12:03');
INSERT INTO `bg_maple_detail` VALUES ('cf244757f5264db1b026d5c39ec57a18', 'c35fd480442c4bd6925e5e508daec4ec', 'ewrwName', 'eqwe名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1489989316792', '01', '1', '2017-03-20 13:55:17', '1', '2017-03-20 13:55:17');
INSERT INTO `bg_maple_detail` VALUES ('d0f60e1176f8492bbee59ddd9d765145', '307d768ee19e45b09b016970caf257ca', 'integral1', '大区经理可获积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528171339', '01', '1', '2017-03-03 16:03:30', '1', '2017-03-03 16:03:30');
INSERT INTO `bg_maple_detail` VALUES ('d144512bdd954f48af8bf1507daf1763', '307d768ee19e45b09b016970caf257ca', 'sparepartStatus', '零配件状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1488528018796', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_detail` VALUES ('d3780155510c4df9a1eb223b0673329f', '46359ab1ffb041c196e16b95a654df12', 'categoryName', '分类名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487917239221', '01', '1', '2017-02-24 14:20:39', '1', '2017-02-24 14:20:39');
INSERT INTO `bg_maple_detail` VALUES ('d549cf2254bb4f3a8cee5fae93537a29', 'ad181811909d4f50b3c2c802e901be84', 'dictValue', '数据字典值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529553258', '01', '1', '2017-01-16 09:19:57', '1', '2017-01-16 09:19:57');
INSERT INTO `bg_maple_detail` VALUES ('d59f4dfab8ad440fa51f882deea3e960', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnType', '微信菜单按钮类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1486346372201', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('d74d0cac2f204274a6c9fdbaea1ec886', '3b04706d4ab8494faa53e05e822a6e82', 'triggerTimes', '运作次数', '02', '00', '10', '0', '', '00', '00', '01', '', '1484988775488', '01', '1', '2017-01-21 16:53:43', '1', '2017-01-21 16:53:43');
INSERT INTO `bg_maple_detail` VALUES ('d7def1ffba704a32b51f4b8b4df86e6f', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserName', '平台用户名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1486696707765', '01', '1', '2017-02-10 11:18:28', '1', '2017-02-10 11:18:28');
INSERT INTO `bg_maple_detail` VALUES ('d88c7b1cfc464a2fb05d738e2c816730', 'c723817cc46c4ff885b9f1a80702c6a9', 'remarks', '备注信息', '01', '00', '100', '0', '', '00', '01', '01', '', '1488518915928', '01', '1', '2017-03-03 10:40:30', '1', '2017-03-03 11:11:00');
INSERT INTO `bg_maple_detail` VALUES ('d89d83f6a74b4128b91179f95cab3d33', '3b04706d4ab8494faa53e05e822a6e82', 'endTime', '停止时间', '03', '00', '100', '0', '', '00', '00', '01', '', '1484988827177', '01', '1', '2017-01-21 16:55:02', '1', '2017-01-21 16:55:02');
INSERT INTO `bg_maple_detail` VALUES ('dafc3c3d3f4846e786a547cda1f17b45', '7928b5c3a71443e69dac9a775fb732aa', 'roleId', '角色id', '05', '00', '100', '0', 'bg_roleEffective', '00', '01', '01', '', '1484726205220', '01', '1', '2017-01-18 16:37:03', '1', '2017-01-18 16:39:44');
INSERT INTO `bg_maple_detail` VALUES ('db5fecb29b9f40549943c0c02ed3894b', 'a4dc9b54bb3e4d17ad983912730a6667', 'productName', '产品名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902142332', '01', '1', '2017-02-24 10:09:02', '1', '2017-02-24 10:09:02');
INSERT INTO `bg_maple_detail` VALUES ('dbfae735ad4c44709e6d2c56d8c012e2', '1ba234a840324c2faa9fc56e9ec9e144', 'mediaId', '媒体文件id', '01', '00', '100', '0', '', '00', '01', '01', '', '1486780133726', '01', '1', '2017-02-11 10:30:27', '1', '2017-02-11 10:30:27');
INSERT INTO `bg_maple_detail` VALUES ('dc26181c863841118957ff7577cd2554', 'a7fef610fae34226afc5515388822a79', 'orderStatus', '订单状态', '01', '00', '100', '0', 'com_orderStatus', '00', '01', '01', '\"00\"', '1489197833019', '01', '1', '2017-03-11 10:03:53', '1', '2017-03-20 23:25:30');
INSERT INTO `bg_maple_detail` VALUES ('dc3554660f37472194ed5acbbda72bf5', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteCode', '邀请代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487571659395', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('dd86b865d60748f9b7e1bda79e943425', '2b8a44269b4542e48e13549c8e2e14a4', 'discountPrice', '折扣优惠', '04', '00', '100', '2', '', '00', '01', '01', '', '1487906084294', '01', '1', '2017-02-24 11:15:10', '1', '2017-02-24 11:15:18');
INSERT INTO `bg_maple_detail` VALUES ('deb09d9a2ee847a78adf60db3ee07b70', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleType', '商品规格类型', '05', '00', '100', '0', 'com_productStyleType', '00', '01', '01', '\"01\"', '1487902538809', '01', '1', '2017-02-24 10:15:39', '1', '2017-03-21 13:52:33');
INSERT INTO `bg_maple_detail` VALUES ('dec6270eeefc467a891e4b8ae9ddba55', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeCode', '用户邀请码代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486778955069', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('ded2d6acc5044f3ea3e8dadbea72f2c4', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeStatus', '用户邀请码状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1486778955103', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('e099d4d3642848d99496eb3c33e9fd4c', 'd25535c48c444f9fb675294c8a2d8e1f', 'code', '扩展属性代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533783136', '01', '1', '2017-03-03 17:37:07', '1', '2017-03-03 17:37:07');
INSERT INTO `bg_maple_detail` VALUES ('e193264b441e4e7986e0600393698674', '9a85215a94334cbc83bb5cf701ccf3bb', 'password', '密码', '01', '00', '255', '0', '', '00', '01', '01', '', '1486706055223', '01', '1', '2017-02-10 13:54:37', '1', '2017-03-06 13:56:31');
INSERT INTO `bg_maple_detail` VALUES ('e22083d9f1c9479ab57a19837afa8d17', 'c35fd480442c4bd6925e5e508daec4ec', 'ewrwStatus', 'eqwe状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1489989316794', '01', '1', '2017-03-20 13:55:17', '1', '2017-03-20 13:55:17');
INSERT INTO `bg_maple_detail` VALUES ('e2aad00ef3e849abae3cf13157b076c6', '754773752b53495f9d452fbc9b06c37a', 'supplierName', '供应商名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487842063086', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_detail` VALUES ('e8e429fb06a048aa942aac0dcac2707a', '2b8a44269b4542e48e13549c8e2e14a4', 'discountRate', '折扣率', '04', '00', '100', '2', '', '00', '01', '01', '', '1487905886721', '01', '1', '2017-02-24 11:11:50', '1', '2017-03-21 13:57:04');
INSERT INTO `bg_maple_detail` VALUES ('e8f6ef912613464daa85940683606069', '67163a4e05664fa39d5bea61b445163b', 'editRights', '修改权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794210103', '01', '1', '2017-01-19 10:50:23', '1', '2017-01-19 10:50:23');
INSERT INTO `bg_maple_detail` VALUES ('ea375dcc8a3842c38cd051e6a3c115f4', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteStatus', '邀请状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487571659418', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('eb32efcd000e4105963887f3dc6fa10a', 'a7fef610fae34226afc5515388822a79', 'wlNum', '运单编号', '01', '00', '100', '0', '', '00', '01', '01', '', '1489200936341', '01', '1', '2017-03-11 10:57:07', '1', '2017-03-11 10:57:07');
INSERT INTO `bg_maple_detail` VALUES ('ebe20425039f42ce89cef007dce47f2c', '2b8a44269b4542e48e13549c8e2e14a4', 'productId', '商品id', '05', '00', '100', '0', '', '00', '01', '01', '', '1487902538803', '01', '1', '2017-02-24 11:07:20', '1', '2017-02-24 11:09:09');
INSERT INTO `bg_maple_detail` VALUES ('ecadb90eba374f51bbfb13482199e30e', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteName', '邀请名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487571659416', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('edaaa6a0b0164200869165de5681dad1', '36ff1b75838f43efac8625df493e0df1', 'jsApiTicket', '微信JS接口的临时票据', '01', '00', '100', '0', '', '00', '00', '01', '', '1484889868656', '01', '1', '2017-01-20 13:49:23', '1', '2017-01-20 14:55:51');
INSERT INTO `bg_maple_detail` VALUES ('edd8ca8904a84061beb0d184bd534f6c', '1ba234a840324c2faa9fc56e9ec9e144', 'mediaExpiry', '媒体文件有效时间', '03', '00', '100', '0', '', '00', '01', '01', '', '1486782310614', '01', '1', '2017-02-11 11:05:53', '1', '2017-02-11 11:05:53');
INSERT INTO `bg_maple_detail` VALUES ('ee78ba611c4544bb96e692debdd8de98', 'a7fef610fae34226afc5515388822a79', 'payTime', '付款时间', '03', '00', '100', '0', '', '00', '00', '01', '', '1489199875558', '01', '1', '2017-03-11 10:40:37', '1', '2017-03-11 10:41:47');
INSERT INTO `bg_maple_detail` VALUES ('eeafe483879b40c6aaeb56083f9b8c44', '27a853950d0e4876ba0eccf8d7e2dd8f', 'name', '名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 17:07:21', '1', '2017-01-11 17:07:21');
INSERT INTO `bg_maple_detail` VALUES ('ef3cc316b9bf45059bd65405e7366741', 'c9da8f2d57774bbbad13030135b6a1cb', 'entityPackage', '实体类包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150564639', '01', '1', '2017-01-12 00:02:44', '1', '2017-01-12 00:02:44');
INSERT INTO `bg_maple_detail` VALUES ('ef643eda1c024e2cae5eb8278ed002f7', 'a4dc9b54bb3e4d17ad983912730a6667', 'productModel', '产品模型', '05', '00', '100', '0', '', '00', '01', '01', '', '1487902775533', '01', '1', '2017-02-24 10:22:33', '1', '2017-02-24 10:22:42');
INSERT INTO `bg_maple_detail` VALUES ('ef6b8a854ab140bbb0c01b18094f9db9', '0c1ea3878cd34c8b9f99283de36f25fb', 'totalLength', '总长度', '02', '00', '20', '0', '', '00', '01', '01', '100', '1484191421616', '01', '1', '2017-01-12 11:23:42', '1', '2017-01-15 23:59:55');
INSERT INTO `bg_maple_detail` VALUES ('f233145128f84b12b9ed7c16c00c57db', '307d768ee19e45b09b016970caf257ca', 'integral2', '小区经理可获积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528219886', '01', '1', '2017-03-03 16:04:43', '1', '2017-03-03 16:04:43');
INSERT INTO `bg_maple_detail` VALUES ('f388076884914adb9df661523c17ff88', '36ff1b75838f43efac8625df493e0df1', 'appId', 'AppID(应用ID)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882330433', '01', '1', '2017-01-20 11:20:13', '1', '2017-01-20 13:22:42');
INSERT INTO `bg_maple_detail` VALUES ('f481fb0a2d7d4a11849c887b9335bbab', 'ac75f2ff51a7405485cf40a6f61785f7', 'currentPrice', '现价', '04', '00', '100', '0', '', '00', '01', '01', '', '1489214464270', '01', '1', '2017-03-11 14:41:23', '1', '2017-03-11 14:41:23');
INSERT INTO `bg_maple_detail` VALUES ('f48e476770d747eab5981bc1aa20861f', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeName', '用户邀请码名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1486778955084', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('f5288b368efa4b3eaf1ad35f56a2d348', 'ad181811909d4f50b3c2c802e901be84', 'dictType', '数据字典类型', '05', '00', '100', '0', 'bg_dictType', '00', '01', '01', '\"01\"', '1484529542052', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 10:37:46');
INSERT INTO `bg_maple_detail` VALUES ('f550e5c2a7f94cd5b5fefcafaf4203f8', 'd770d5128d82483ab6763cfbed6e847d', 'appUserId', '平台用户', '05', '00', '100', '0', '', '00', '01', '01', '', '1488594303771', '01', '1', '2017-03-06 11:08:13', '1', '2017-03-06 11:10:25');
INSERT INTO `bg_maple_detail` VALUES ('f66267f217e94235b4ffbc31e92a1f66', '9ca02063e5bb4885837ab43638413cfd', 'appUserId', '用户', '05', '00', '100', '0', 'com_appUserEffective', '00', '01', '01', '', '1490175363707', '01', '1', '2017-03-22 17:36:37', '1', '2017-03-22 17:36:37');
INSERT INTO `bg_maple_detail` VALUES ('f72da7a85380411a81f0062abe1136d6', '46359ab1ffb041c196e16b95a654df12', 'categoryCode', '分类代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487917239219', '01', '1', '2017-02-24 14:20:39', '1', '2017-02-24 14:20:39');
INSERT INTO `bg_maple_detail` VALUES ('f86e735124884150ba8cdbc23e794551', 'c723817cc46c4ff885b9f1a80702c6a9', 'password', '密码', '01', '00', '100', '0', '', '00', '01', '01', '', '1488510524413', '01', '1', '2017-03-03 11:08:52', '1', '2017-03-03 11:08:52');
INSERT INTO `bg_maple_detail` VALUES ('fa02e27509df4ce6ab9c06c9dec2e4d5', 'c723817cc46c4ff885b9f1a80702c6a9', 'integralCustomerName', '积分客户名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1488508256594', '01', '1', '2017-03-03 10:30:57', '1', '2017-03-03 10:30:57');
INSERT INTO `bg_maple_detail` VALUES ('fb3b9957f4374fad88077a64e37f61ea', 'c723817cc46c4ff885b9f1a80702c6a9', 'userIconSrc', '客户头像', '01', '00', '100', '0', '', '00', '00', '01', '', '1488510581018', '01', '1', '2017-03-03 11:09:59', '1', '2017-03-03 14:05:28');
INSERT INTO `bg_maple_detail` VALUES ('fcbd78fd88f94fa5bbb971e88da084c4', '7928b5c3a71443e69dac9a775fb732aa', 'email', '电子邮箱', '01', '00', '100', '0', '', '00', '01', '01', '', '1484728391889', '01', '1', '2017-01-18 16:34:12', '1', '2017-01-18 16:34:12');
INSERT INTO `bg_maple_detail` VALUES ('ff5a2dd57bad4a5eb3f796737b296840', '9a85215a94334cbc83bb5cf701ccf3bb', 'roleId', '角色', '05', '00', '100', '0', '', '00', '01', '01', '', '1486696707745', '01', '1', '2017-03-06 13:55:26', '1', '2017-03-06 13:57:39');

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
INSERT INTO `bg_maple_main` VALUES ('0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategory', '商品分类', '03', '00', 'bg', 'com', '1487902595858', '01', '1', '2017-02-24 10:18:26', '1', '2017-03-20 13:55:56');
INSERT INTO `bg_maple_main` VALUES ('26e2f983954449ed84539074b02628f1', 'receiveAddress', '收货地址', '01', '00', 'bg', 'com', '1489214758280', '01', '1', '2017-03-11 14:46:18', '1', '2017-03-11 14:51:33');
INSERT INTO `bg_maple_main` VALUES ('27a853950d0e4876ba0eccf8d7e2dd8f', 'baseField', '基本字段', '01', '00', 'bg', 'bg', '1484122184486', '01', '1', '2017-01-11 16:09:44', '1', '2017-01-11 16:09:44');
INSERT INTO `bg_maple_main` VALUES ('2b0255e6416a49669ba92dd90732c2ab', 'styleCategory', '规格分类', '02', '00', 'bg', 'com', '1487912444000', '01', '1', '2017-02-24 13:01:23', '1', '2017-03-10 09:56:44');
INSERT INTO `bg_maple_main` VALUES ('2b8a44269b4542e48e13549c8e2e14a4', 'productStyle', '商品规格', '01', '00', 'bg', 'com', '1487902149025', '01', '1', '2017-02-24 10:15:39', '1', '2017-03-21 13:51:05');
INSERT INTO `bg_maple_main` VALUES ('307d768ee19e45b09b016970caf257ca', 'sparepart', '零配件', '01', '00', 'bg', 'com', '1488527998004', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_main` VALUES ('36ff1b75838f43efac8625df493e0df1', 'wxAccount', '微信账户', '01', '00', 'bg', 'com', '1484881971240', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_main` VALUES ('3b04706d4ab8494faa53e05e822a6e82', 'crontab', '定时任务', '01', '00', 'bg', 'bg', '1484983080995', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_main` VALUES ('3b3eb93879584c78a5ae5ea461dbc022', 'sparepartDeal', '零部件交易', '01', '00', 'bg', 'com', '1488531259942', '01', '1', '2017-03-03 16:54:50', '1', '2017-03-03 17:21:22');
INSERT INTO `bg_maple_main` VALUES ('4e272d40a7cf40009f6a47b0e374c2ba', 'productCategoryDetail', '商品分类详情', '04', '00', 'bg', 'com', '1489975413201', '01', '1', '2017-03-20 10:04:26', '1', '2017-03-20 10:04:26');
INSERT INTO `bg_maple_main` VALUES ('67163a4e05664fa39d5bea61b445163b', 'role', '后台角色', '01', '00', 'bg', 'bg', '1484794064296', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_main` VALUES ('742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtn', '微信菜单按钮', '02', '00', 'bg', 'bg', '1486346320511', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_main` VALUES ('754773752b53495f9d452fbc9b06c37a', 'supplier', '供应商', '01', '00', 'bg', 'com', '1487841762885', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_main` VALUES ('7928b5c3a71443e69dac9a775fb732aa', 'user', '后台用户', '01', '00', 'bg', 'bg', '1484726157405', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_main` VALUES ('864215909ec741eda25b708998f777d0', 'lbt', '轮播图', '01', '00', 'bg', 'com', '1489815038416', '01', '1', '2017-03-18 13:31:01', '1', '2017-03-18 13:31:01');
INSERT INTO `bg_maple_main` VALUES ('9a85215a94334cbc83bb5cf701ccf3bb', 'appUser', '平台用户', '02', '00', 'bg', 'com', '1486696475857', '01', '1', '2017-02-10 11:18:28', '1', '2017-03-06 11:19:24');
INSERT INTO `bg_maple_main` VALUES ('9ca02063e5bb4885837ab43638413cfd', 'shopCar', '购物车', '01', '00', 'bg', 'com', '1490175078823', '01', '1', '2017-03-22 17:32:31', '1', '2017-03-22 17:32:31');
INSERT INTO `bg_maple_main` VALUES ('a4dc9b54bb3e4d17ad983912730a6667', 'product', '商品', '01', '00', 'bg', 'com', '1487901959012', '01', '1', '2017-02-24 10:09:02', '1', '2017-03-21 13:50:26');
INSERT INTO `bg_maple_main` VALUES ('a7fef610fae34226afc5515388822a79', 'order', '订单', '03', '00', 'bg', 'com', '1489197770505', '01', '1', '2017-03-11 10:03:53', '1', '2017-03-20 23:15:46');
INSERT INTO `bg_maple_main` VALUES ('ac75f2ff51a7405485cf40a6f61785f7', 'orderDetail', '订单商品', '04', '00', 'bg', 'com', '1489198655844', '01', '1', '2017-03-11 10:18:17', '1', '2017-03-20 23:16:06');
INSERT INTO `bg_maple_main` VALUES ('acb56ae4dd454b26beb150b7d08a14ed', 'invite', '邀请', '01', '00', 'bg', 'com', '1487571577611', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_main` VALUES ('ad181811909d4f50b3c2c802e901be84', 'dict', '数据字典', '02', '00', 'bg', 'com', '1484190988200', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-16 11:22:04');
INSERT INTO `bg_maple_main` VALUES ('c9da8f2d57774bbbad13030135b6a1cb', 'maple', '代码生成', '03', '00', 'bg', 'bg', '1484149707387', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_main` VALUES ('d25535c48c444f9fb675294c8a2d8e1f', 'integralCustomerExt', '积分客户扩展', '01', '00', 'bg', 'com', '1488533613977', '01', '1', '2017-03-03 17:34:06', '1', '2017-03-03 17:34:06');
INSERT INTO `bg_maple_main` VALUES ('d770d5128d82483ab6763cfbed6e847d', 'integralNote', '积分记录', '01', '00', 'bg', 'com', '1488532786133', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
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
INSERT INTO `bg_menu_tree` VALUES ('11ebc426cf7341628ecb33ddc676f887', 'e198cad6387b4ccb8a0209b961c34f7d', 'dmscq', '代码生成器', '02', '01', '14', '#', 'menu-icon fa fa-camera-retro brown', '1487926880559', '01', '1', '2017-02-24 17:01:51', '1', '2017-02-24 17:04:08');
INSERT INTO `bg_menu_tree` VALUES ('211833bb44a94521b6589dc3b76c3f81', '2ecf674826d941438d9bc8175d309588', 'background_productCategory', '商品类别管理', '02', '01', '22', 'background/productCategory/list.do', 'menu-icon fa fa-globe red', '1487928431940', '01', '1', '2017-02-24 17:32:03', '1', '2017-03-21 15:27:31');
INSERT INTO `bg_menu_tree` VALUES ('24ca601e8eb6460d82f26c3bd0277e45', '2a66f51272904a7e9e4e419448b6400a', 'qxgl', '权限管理', '02', '01', '10', '#', 'menu-icon fa fa-lock black', '1487926583528', '01', '1', '2017-02-24 16:56:44', '1', '2017-02-24 16:56:50');
INSERT INTO `bg_menu_tree` VALUES ('286d62cc09eb41ecaac2f8435a97fc6e', 'e198cad6387b4ccb8a0209b961c34f7d', 'background_crontab', '定时任务管理', '02', '01', '16', 'background/crontab/list.do', 'menu-icon fa fa-lightbulb-o orange', '1487927010037', '01', '1', '2017-02-24 17:04:00', '1', '2017-02-24 17:04:34');
INSERT INTO `bg_menu_tree` VALUES ('2a66f51272904a7e9e4e419448b6400a', '0', 'xtgl', '系统管理', '02', '01', '1', '#', 'menu-icon fa fa-desktop blue', '1487924690462', '01', '1', '2017-02-24 16:25:59', '1', '2017-02-24 16:45:14');
INSERT INTO `bg_menu_tree` VALUES ('2af6eded47c742839cd38ac1815ec305', '0', 'ddgl', '订单管理', '02', '01', '7', '#', 'menu-icon fa fa-credit-card green', '1487925605005', '01', '1', '2017-02-24 16:40:32', '1', '2017-02-24 16:48:32');
INSERT INTO `bg_menu_tree` VALUES ('2ecf674826d941438d9bc8175d309588', '0', 'cpgl', '产品管理', '02', '01', '6', '#', 'menu-icon fa fa-gift brown', '1487925444388', '01', '1', '2017-02-24 16:38:09', '1', '2017-02-24 16:48:26');
INSERT INTO `bg_menu_tree` VALUES ('4237a71a32f442e6ab9b5ad33e2c45c6', '2ecf674826d941438d9bc8175d309588', 'background_sparepart', '零配件管理', '02', '01', '27', 'background/sparepart/list.do', 'menu-icon fa fa-leaf purple', '1488528558603', '01', '1', '2017-03-03 16:10:31', '1', '2017-03-03 16:10:47');
INSERT INTO `bg_menu_tree` VALUES ('4631fcf6f8e24b5180bb5ae64cc118a4', '0', 'shgl', '售后管理', '02', '01', '9', '#', 'menu-icon fa fa-laptop blue', '1487925842602', '01', '1', '2017-02-24 16:45:06', '1', '2017-02-24 16:52:00');
INSERT INTO `bg_menu_tree` VALUES ('5575d1123ad8471781c7e6b9e5ed1bc3', 'b5cb762f1cbc41f3a68e61fa541bb62f', 'background_lbt', '轮播图管理', '02', '01', '35', 'background/lbt/list.do', 'menu-icon fa fa-leaf black', '1489822910648', '01', '1', '2017-03-18 15:42:32', '1', '2017-03-18 15:42:32');
INSERT INTO `bg_menu_tree` VALUES ('656a072a483d44b8b02906c6cc692154', '2a66f51272904a7e9e4e419448b6400a', 'background_dict', '数据字典', '02', '01', '12', 'background/dict/main.do', 'menu-icon fa fa-book orange', '1487926669970', '01', '1', '2017-02-24 16:59:24', '1', '2017-02-24 16:59:51');
INSERT INTO `bg_menu_tree` VALUES ('6952f6500bd7403ebe1dd61337209f29', '0', 'sjkgl', '数据库管理', '02', '01', '3', '#', 'menu-icon fa fa-hdd-o blue', '1487925347271', '01', '1', '2017-02-24 16:36:07', '1', '2017-02-24 16:45:40');
INSERT INTO `bg_menu_tree` VALUES ('6b5fe0915dd64cdc98edb038ce55980a', '24ca601e8eb6460d82f26c3bd0277e45', 'background_role', '角色(基础权限)', '02', '01', '13', 'background/role/list.do', 'menu-icon fa fa-key orange', '1487926824242', '01', '1', '2017-02-24 17:00:48', '1', '2017-02-24 17:01:01');
INSERT INTO `bg_menu_tree` VALUES ('6d8cec4e3459405daae1fcd6016b26f2', '967da4d8a0e34db393a28ba315e59ae0', 'background_integralNote', '积分客户积分交易记录', '02', '01', '29', 'background/integralNote/list.do', 'menu-icon fa fa-leaf black', '1488596110247', '01', '1', '2017-03-04 10:56:30', '1', '2017-03-04 10:56:30');
INSERT INTO `bg_menu_tree` VALUES ('7276636bd85440858b814e04105fc044', '2ecf674826d941438d9bc8175d309588', 'background_productStyle', '商品规格管理', '02', '01', '23', 'background/productStyle/list.do', 'menu-icon fa fa-leaf black', '1487986244765', '01', '1', '2017-02-25 09:33:30', '1', '2017-03-21 15:27:48');
INSERT INTO `bg_menu_tree` VALUES ('727f8a845cd64de094d615e0484c0ddc', '2af6eded47c742839cd38ac1815ec305', 'background_order', '订单管理', '01', '01', '31', 'background/order/list.do', 'menu-icon fa fa-credit-card purple', '1489219146378', '01', '1', '2017-03-11 16:01:05', '1', '2017-03-11 16:01:39');
INSERT INTO `bg_menu_tree` VALUES ('76ea10cc8ddf4648877e3729997eb873', '2ecf674826d941438d9bc8175d309588', 'background_supplier', '供应商管理', '02', '01', '21', 'background/supplier/list.do', 'menu-icon fa fa-users black', '1487928333702', '01', '1', '2017-02-24 17:26:59', '1', '2017-02-25 09:39:37');
INSERT INTO `bg_menu_tree` VALUES ('8c598c875e5a415297d952557199b3a2', '96b19ecb695e45219374388a264faf8b', 'background_wxMenuBtn', '微信菜单按钮管理', '02', '01', '20', 'background/wxMenuBtn/main.do', 'menu-icon fa fa-comments red', '1487927760117', '01', '1', '2017-02-24 17:16:19', '1', '2017-02-24 17:16:30');
INSERT INTO `bg_menu_tree` VALUES ('904be00723324555932961eeb6d1114e', '2ecf674826d941438d9bc8175d309588', 'background_product', '商品管理', '02', '01', '25', 'background/product/list.do', 'menu-icon fa fa-globe purple', '1487928431950', '01', '1', '2017-02-25 09:37:00', '1', '2017-03-21 15:27:39');
INSERT INTO `bg_menu_tree` VALUES ('967da4d8a0e34db393a28ba315e59ae0', '0', 'zdgl', '账单管理', '02', '01', '8', '#', 'menu-icon fa fa-credit-card pink', '1487925762928', '01', '1', '2017-02-24 16:43:49', '1', '2017-02-24 16:48:36');
INSERT INTO `bg_menu_tree` VALUES ('96b19ecb695e45219374388a264faf8b', '0', 'wxgl', '微信管理', '02', '01', '5', '#', 'menu-icon fa fa-comments green', '1487925387762', '01', '1', '2017-02-24 16:48:05', '1', '2017-02-24 16:51:38');
INSERT INTO `bg_menu_tree` VALUES ('982aa26baaa84b6e9d43a0d69654a827', '96b19ecb695e45219374388a264faf8b', 'background_wxAccount', '微信账号管理', '02', '01', '19', 'background/wxAccount/list.do', 'menu-icon fa fa-comments blue', '1487927712953', '01', '1', '2017-02-24 17:15:46', '1', '2017-02-24 17:15:58');
INSERT INTO `bg_menu_tree` VALUES ('9e89d4909c7440c3b9fd5f933055f824', 'd41c4bf59b274d81bcb982c00cb2b2b3', 'background_appUser', '平台用户', '02', '01', '18', 'background/appUser/main.do', 'menu-icon fa fa-users blue', '1487927658996', '01', '1', '2017-02-24 17:14:38', '1', '2017-03-06 15:37:59');
INSERT INTO `bg_menu_tree` VALUES ('a25416cb953a46c49e52cfed916c7995', '2af6eded47c742839cd38ac1815ec305', 'background_orderProduct', '订单商品管理', '02', '01', '32', 'background/orderProduct/list.do', 'menu-icon fa fa-credit-card brown', '1489219304212', '01', '1', '2017-03-11 16:02:48', '1', '2017-03-11 16:02:54');
INSERT INTO `bg_menu_tree` VALUES ('af781392f8f54627b165fa414c4562d6', '2ecf674826d941438d9bc8175d309588', 'background_styleCategory', '规格分类管理', '02', '01', '24', 'background/styleCategory/main.do', 'menu-icon fa fa-globe green', '1487928431960', '01', '1', '2017-02-25 09:35:29', '1', '2017-03-10 10:12:45');
INSERT INTO `bg_menu_tree` VALUES ('b5cb762f1cbc41f3a68e61fa541bb62f', '0', 'ywgl', '业务管理', '02', '01', '34', '#', 'menu-icon fa fa-leaf black', '1489822884011', '01', '1', '2017-03-18 15:41:39', '1', '2017-03-18 15:41:39');
INSERT INTO `bg_menu_tree` VALUES ('c2f704b36ccf49abae4ec777a640dac6', 'daffae3ab1554bd89dab69d72c1b9826', 'background_sparepartDeal', '零配件订单审核', '02', '01', '30', 'background/sparepartDeal/list.do', 'menu-icon fa fa-gavel orange', '1488595937615', '01', '1', '2017-03-04 10:53:01', '1', '2017-03-04 10:57:26');
INSERT INTO `bg_menu_tree` VALUES ('c676a8cdc73c4496bae43af104e2b524', '11ebc426cf7341628ecb33ddc676f887', 'background_maple', '正向生成', '02', '01', '15', 'background/maple/list.do', 'menu-icon fa fa-camera-retro green', '1487926917132', '01', '1', '2017-02-24 17:02:20', '1', '2017-02-24 17:03:17');
INSERT INTO `bg_menu_tree` VALUES ('d080a9f1d0b04ee5bba99c352563178d', '2a66f51272904a7e9e4e419448b6400a', 'background_menu', '菜单管理', '02', '01', '11', 'background/menu/main.do', 'menu-icon fa fa-folder-open-o red', '1487926618522', '01', '1', '2017-02-24 16:57:33', '1', '2017-02-24 16:57:45');
INSERT INTO `bg_menu_tree` VALUES ('d41c4bf59b274d81bcb982c00cb2b2b3', '0', 'yhgl', '用户管理', '02', '01', '4', '#', 'menu-icon fa fa-users blue', '1487925387761', '01', '1', '2017-02-24 16:37:03', '1', '2017-02-24 16:45:48');
INSERT INTO `bg_menu_tree` VALUES ('daffae3ab1554bd89dab69d72c1b9826', '0', 'shegl', '审核管理', '02', '01', '28', '#', 'menu-icon fa fa-gavel black', '1488531040354', '01', '1', '2017-03-03 16:52:10', '1', '2017-03-04 10:57:50');
INSERT INTO `bg_menu_tree` VALUES ('e198cad6387b4ccb8a0209b961c34f7d', '0', 'xtgj', '系统工具', '02', '01', '2', '#', 'menu-icon fa fa-cogs grey', '1487925067666', '01', '1', '2017-02-24 16:34:37', '1', '2017-02-24 16:45:28');
INSERT INTO `bg_menu_tree` VALUES ('e1a2ddec531a4918b798425b61b0a3d2', '2af6eded47c742839cd38ac1815ec305', 'background_shopCar', '购物车管理', '02', '01', '36', 'background/shopCar/list.do', 'menu-icon fa fa-leaf black', '1490190517059', '01', '1', '2017-03-22 21:49:51', '1', '2017-03-22 21:49:51');
INSERT INTO `bg_menu_tree` VALUES ('e34d436058144196af7b32773e9c43b5', 'b5cb762f1cbc41f3a68e61fa541bb62f', 'background_receiveAddress', '收货地址管理', '02', '01', '33', 'background/receiveAddress/list.do', 'menu-icon fa fa-gift black', '1489219401707', '01', '1', '2017-03-11 16:03:59', '1', '2017-03-11 16:04:14');
INSERT INTO `bg_menu_tree` VALUES ('f50c328b5cd3402188380409dcb148ad', 'd41c4bf59b274d81bcb982c00cb2b2b3', 'background_user', '系统用户', '02', '01', '17', 'background/user/list.do', 'menu-icon fa fa-users green', '1487927595401', '01', '1', '2017-02-24 17:13:49', '1', '2017-02-24 17:14:16');

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
INSERT INTO `bg_role_info` VALUES ('1', 'admin', '系统管理员', '00', '00', '137371844606', '137371844606', '137371844606', '137371844606', '137371844606', '1', '01', '1', '2017-01-19 13:24:30', '1', '2017-03-22 22:37:52');
INSERT INTO `bg_role_info` VALUES ('349e880b6e334f8caaac6c250a4cae01', 'integralAdmin', '积分客户管理员', '00', '00', '2013528912', '2013528912', '2013528912', '2013528912', '2013528912', '1488597298370', '01', '1', '2017-03-04 11:15:49', '1', '2017-03-06 15:39:29');

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
INSERT INTO `bg_user_info` VALUES ('59e413d02fe24211a6b5725abe3e868d', '349e880b6e334f8caaac6c250a4cae01', 'zhangsan', '00a2695e20ca136af6724261351ac10e978a887c7b3da9ea8e2e1c1969628476bd0d39955dfb7897b4f68b1282f8e8b685973169065ffab24cff68c0265d8e69', '张三', '01', '00', 'static/ace/avatars/user.jpg', '45233@qq.com', '15873322111', 'dasd', '1488597389108', '01', '1', '2017-03-04 11:18:09', '1', '2017-03-04 11:18:09');
INSERT INTO `bg_user_info` VALUES ('7d251ac19dda4f34946abcec165e2d77', '349e880b6e334f8caaac6c250a4cae01', 'dasdsa', '0af15bb4e9e3b75f628774b8272559552c6656ce678ec97ca045b96dc8ce399b940235ffef51ec3161d472fe066836c7142ec4a7f64626e2f5696415e5088a76', 'xczzxc', '01', '00', 'static/ace/avatars/user.jpg', 'asdasd@qq.com', '13685777441', 'case', '1488857459632', '01', '1', '2017-03-07 11:31:26', '1', '2017-03-07 13:22:09');
INSERT INTO `bg_user_info` VALUES ('aaee1e4622724f79bb2f9a1c667a7395', '1', 'xxxxx', '9a2cf82b5e81d253c971b09b2fab3da41082e1a4d6d42327111fccdcb6e9f8f20d17c6cd172cd30b73e82ce6e75395cebb89024feb48b832cd8ffa3e44becefa', '22222222222', '01', '00', 'static/ace/avatars/user.jpg', 'wad@df.cc', '13575542432', '', '1484814622729', '01', '1', '2017-01-19 16:30:58', '1', '2017-01-19 16:31:05');

-- ----------------------------
-- Table structure for bg_wx_menu_btn_tree
-- ----------------------------
DROP TABLE IF EXISTS `bg_wx_menu_btn_tree`;
CREATE TABLE `bg_wx_menu_btn_tree` (
  `wxMenuBtnId` varchar(100) NOT NULL COMMENT '微信菜单按钮 主键id',
  `parentId` varchar(100) NOT NULL COMMENT '上级 id',
  `wxMenuBtnCode` varchar(100) DEFAULT NULL COMMENT '微信菜单按钮代号',
  `wxMenuBtnName` varchar(100) DEFAULT NULL COMMENT '微信菜单按钮名称',
  `wxMenuBtnType` varchar(100) DEFAULT NULL COMMENT '微信菜单按钮类型',
  `wxMenuBtnStatus` varchar(100) DEFAULT NULL COMMENT '微信菜单按钮状态',
  `menuBtnKey` varchar(128) DEFAULT NULL COMMENT '菜单KEY值',
  `menuBtnUrl` varchar(1024) DEFAULT NULL COMMENT '网页链接',
  `media_id` varchar(100) DEFAULT NULL COMMENT '永久素材id',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`wxMenuBtnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信菜单按钮';

-- ----------------------------
-- Records of bg_wx_menu_btn_tree
-- ----------------------------
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('18f029f961274a84ae452d63abfbd40b', '0', 'gemo', '格默积分商城', '00', '00', '', '', '', '1489369323564', '01', '1', '2017-03-13 09:42:48', '1', '2017-03-13 09:42:48');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('1f4ca1027b91435c93eec22ab6f445dd', '9828b1377557429c8cb449440291e042', 'giftOL_personal', '个人中心', '00', '00', '', '', '', '1486371274109', '01', '1', '2017-02-06 16:55:33', '1', '2017-02-07 15:33:10');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('354db0ea80844ba7950002ab6c62e0ab', '6e7573a58c074fbe9aea359cdd896500', 'toMyCenter', '个人中心', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/mine/toMyCenter.do', '', '1489472866272', '01', '1', '2017-03-14 14:29:25', '1', '2017-03-14 14:29:25');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('3ab4159f505e4a88902f651200fd87c9', '9828b1377557429c8cb449440291e042', 'giftOL_active', '活动', 'view', '00', '', 'http://gift.yooojung.com/mine/mine!toMineIndex1.shtml', '', '1486370775800', '01', '1', '2017-02-06 16:48:04', '1', '2017-02-07 14:12:05');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('432df40516ad4bf9a6cf6b391e66009b', '18f029f961274a84ae452d63abfbd40b', 'toIndex', '首页', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/main/toIndex', '', '1489369583648', '01', '1', '2017-03-13 09:48:02', '1', '2017-03-13 09:48:31');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('483edd3b946441c9a5519845ea43a3b3', '18f029f961274a84ae452d63abfbd40b', 'toLogin', '登录', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/main/toLogin', '', '1489369713481', '01', '1', '2017-03-13 09:49:15', '1', '2017-03-13 09:49:15');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('52f2b818341d4468885f741236b47b29', '1f4ca1027b91435c93eec22ab6f445dd', 'giftOL_toLogin', '登录', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/main/toLogin.do', '', '1487665058373', '01', '1', '2017-02-21 16:18:19', '1', '2017-02-21 16:18:41');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('619831691da0444484c39502fbc307fc', '1f4ca1027b91435c93eec22ab6f445dd', 'giftOL_myWallet', '我的钱包', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/mine/toMyWallet.do', '', '1486454357819', '01', '1', '2017-02-07 15:59:54', '1', '2017-02-21 16:17:16');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('6e7573a58c074fbe9aea359cdd896500', '18f029f961274a84ae452d63abfbd40b', 'toMine', '个人中心', '00', '00', '', '', '', '1489472833271', '01', '1', '2017-03-14 14:27:39', '1', '2017-03-14 14:27:39');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('9828b1377557429c8cb449440291e042', '0', 'giftOL', '在线送礼', '00', '00', null, null, null, '1484896943192', '01', '1', '2017-01-20 15:26:49', '1', '2017-01-20 15:26:49');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('ccd4757c210b48baa852f08ddeac6ece', '1f4ca1027b91435c93eec22ab6f445dd', 'giftOL_myqrcode', '我的二维码', 'click', '00', 'ewm', '', '', '1487057185478', '01', '1', '2017-02-14 15:27:31', '1', '2017-02-14 15:27:31');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('e1378fd669cd4fcca15093da2a0c7155', '6e7573a58c074fbe9aea359cdd896500', 'getMyQRcode', '我的二维码', 'click', '00', 'getMyQRcode', '', '', '1489472987399', '01', '1', '2017-03-14 14:34:18', '1', '2017-03-14 16:37:15');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('e5d35cd9071d4c22af19c165b6a064db', '9828b1377557429c8cb449440291e042', 'giftOL_index', '首页', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/main/toIndex', '', '1486369318418', '01', '1', '2017-02-06 16:22:16', '1', '2017-02-15 17:18:05');

-- ----------------------------
-- Table structure for com_app_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `com_app_user_ext`;
CREATE TABLE `com_app_user_ext` (
  `appUserExtId` varchar(100) NOT NULL COMMENT '平台用户扩展 主键id',
  `appUserId` varchar(100) DEFAULT NULL COMMENT '平台用户 主键id',
  `extCode` varchar(100) DEFAULT NULL COMMENT '扩展属性代号',
  `extName` varchar(100) DEFAULT NULL COMMENT '扩展属性名',
  `extValue` varchar(100) DEFAULT NULL COMMENT '扩展属性值',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`appUserExtId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台用户扩展';

-- ----------------------------
-- Records of com_app_user_ext
-- ----------------------------
INSERT INTO `com_app_user_ext` VALUES ('04f59bcd382047aaaa3276e5531a5c80', 'c2abded86a9944d8a3b312b7c8828eb8', 'wxQRcodeSrc', '微信二维码', '', '1492073756675', '01', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_ext` VALUES ('0523bd8b6b4c4cfba1ba18dc45cb1785', 'e8f6a7d0eae342ea8147ab5b79358766', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:30:42', '1492057842128', '01', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_ext` VALUES ('07da28bc544a467bba798c06f9192c67', 'e8f6a7d0eae342ea8147ab5b79358766', 'wxQRcodeSrc', '微信二维码', '', '1492057842134', '01', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_ext` VALUES ('097b5986fecb4bc1b732c2a3c3cfb61d', '42e4bc5282424aefab593e5c8520c7f2', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:26:49', '1492057609595', '01', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_ext` VALUES ('09e22fff546b474fbd97fe6fc41eac69', 'f79d339325ba42ada9065e80e731574a', 'mediaId', '媒体文件id', '', '1492050077568', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18');
INSERT INTO `com_app_user_ext` VALUES ('0a070796e367424796efc556add1b4ec', '0f4c52c428cf4c18b9c9baf26d949460', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:19:10', '1492049950401', '01', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_ext` VALUES ('0a3eb7394f4f493c9de4422edae85501', 'f79d339325ba42ada9065e80e731574a', 'integralCount', '积分数', '9801.6', '1492050077565', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:36');
INSERT INTO `com_app_user_ext` VALUES ('0bc02aadca914d42b3d0dedc0d650ace', '57fd4c0f533744239bffe41136677b55', 'openId', '微信公众号个人唯一标识', '', '1492073811335', '01', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_ext` VALUES ('0ec480fc66bb4e0eb7d7a0dbfa909b3d', '58be520f7644420c974f69151caf3c45', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:23:41', '1492057421223', '01', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_ext` VALUES ('117d0552be93411ba3de42f572c7d02f', '42e4bc5282424aefab593e5c8520c7f2', 'wxQRcodeSrc', '微信二维码', '', '1492057609601', '01', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_ext` VALUES ('126dda0efd334df9b9cb746aaf232901', 'efcd8dcfe2f24de6a741253f8c7e9790', 'mediaId', '媒体文件id', '', '1492049358828', '01', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19');
INSERT INTO `com_app_user_ext` VALUES ('13c42d9aec02434ba878fd4d019f6d76', 'cdaff7c52dbc4826a0a9df940006e372', 'mediaId', '媒体文件id', '', '1492073231279', '01', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_ext` VALUES ('164a54c1897742628a3bf28a3d1ba5f5', '57fd4c0f533744239bffe41136677b55', 'wxQRcodeSrc', '微信二维码', '', '1492073811333', '01', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_ext` VALUES ('16f1582e307f4937835dd6497560e4b9', 'cc2ab8d65432415e92acb95179149dec', 'wxQRcodeSrc', '微信二维码', '', '1492049978148', '01', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_ext` VALUES ('1a5fff64e8a54dbebf595f3f5baa0d1f', '57fd4c0f533744239bffe41136677b55', 'mediaId', '媒体文件id', '', '1492073811326', '01', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_ext` VALUES ('1dbe07f464294606aa731e96ca4b3e38', 'cc2ab8d65432415e92acb95179149dec', 'openId', '微信公众号个人唯一标识', '', '1492049978150', '01', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_ext` VALUES ('1f96fffd1cee46faa8a3fb639c1aa651', 'b366dad3219442e792fac84c808af617', 'openId', '微信公众号个人唯一标识', '', '1492057860568', '01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_ext` VALUES ('232d941aefb842a6852ed564ee9d1283', 'f79d339325ba42ada9065e80e731574a', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:21:17', '1492050077570', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18');
INSERT INTO `com_app_user_ext` VALUES ('249c415b5853402ea081cbf4ae4ab706', 'f79d339325ba42ada9065e80e731574a', 'wxQRcodeSrc', '微信二维码', '', '1492050077571', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18');
INSERT INTO `com_app_user_ext` VALUES ('25deb953364d458889236563319a3f3b', '9ea0b3f7acce4d858fcf10cb96a67ae8', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:47:43', '1492073263437', '01', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_ext` VALUES ('2607da9505a744968cb49dec4b76d910', '442aed9efe9348eea5dfe84e2b7296d4', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:09:56', '1492049396714', '01', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57');
INSERT INTO `com_app_user_ext` VALUES ('2707113b81d34ab08de661639fa7a9bf', '40a9af4e7242458595b444f481251f26', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:31:44', '1492057904613', '01', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_ext` VALUES ('27deab6cb10a476c911dc359f2163398', 'f9c0d068b00a4ed1a380bbb60f3d8632', 'integralCount', '积分数', '0', '1492049417428', '01', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17');
INSERT INTO `com_app_user_ext` VALUES ('288dff29cf5c45379868688968c81610', '317fb9c81271432e95dcbfd562033393', 'mediaId', '媒体文件id', '', '1492073178110', '01', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_ext` VALUES ('28a2a69640f24d7491ff1a3f40a70348', 'cdaff7c52dbc4826a0a9df940006e372', 'openId', '微信公众号个人唯一标识', '', '1492073231287', '01', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_ext` VALUES ('29952d8270aa42e798965b42a6de5c6b', 'b366dad3219442e792fac84c808af617', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:31:00', '1492057860560', '01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_ext` VALUES ('2ab52dfd55224429b4a1aa9df723f5f3', '317fb9c81271432e95dcbfd562033393', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:46:18', '1492073178108', '01', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_ext` VALUES ('2ad849c662c44aa89705e2e8c3668866', 'cc0162aaedbc470fad09d11f619388ea', 'openId', '微信公众号个人唯一标识', '', '1492073795394', '01', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_ext` VALUES ('2ae4940d7ee4408289050d763c8caf67', '9ea0b3f7acce4d858fcf10cb96a67ae8', 'integralCount', '积分数', '0', '1492073263431', '01', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_ext` VALUES ('2b5ba1c4c6ca4098bf1aa5dc88e266cd', '2b7a195260bb466c8f70c650d1958a30', 'openId', '微信公众号个人唯一标识', '', '1492057632939', '01', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_ext` VALUES ('2d26e0f05c9c4ad1b07abdcb6b26aca5', '0f4c52c428cf4c18b9c9baf26d949460', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:19:10', '1492049950398', '01', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_ext` VALUES ('2d9079ddde0a4703ad7f13ebaf274684', 'cc2ab8d65432415e92acb95179149dec', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:19:38', '1492049978143', '01', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_ext` VALUES ('2e2bac0096984274ae4186d94df3925d', 'f79d339325ba42ada9065e80e731574a', 'openId', '微信公众号个人唯一标识', 'oAQf_wgU22N3diLH4TEqxu_8j6Rk', '1492050077573', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:25:13');
INSERT INTO `com_app_user_ext` VALUES ('3006023f4262404c9fc36ad9149acc06', '58be520f7644420c974f69151caf3c45', 'wxQRcodeSrc', '微信二维码', '', '1492057421225', '01', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_ext` VALUES ('336157f3d9424b2a8507d0aaa30cae61', '317fb9c81271432e95dcbfd562033393', 'integralCount', '积分数', '0', '1492073178106', '01', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_ext` VALUES ('3598bebe3dca4ad79b43522160089fb7', '970ac5008a624f42aa44b9dc43cd1e56', 'mediaId', '媒体文件id', '', '1492057717800', '01', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38');
INSERT INTO `com_app_user_ext` VALUES ('3b8f9046a04f4b628430f10e2e85e2b1', 'cdaff7c52dbc4826a0a9df940006e372', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:47:11', '1492073231283', '01', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_ext` VALUES ('3e59c6e8301342e98d63ce5720f231a0', 'c2abded86a9944d8a3b312b7c8828eb8', 'integralCount', '积分数', '0', '1492073756662', '01', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_ext` VALUES ('3ee35dc8ca7c4ee38d6631529156913e', '10a6895288574d9fa55c04b5f96e478f', 'integralCount', '积分数', '0', '1492057530339', '01', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_ext` VALUES ('3f59e32360a14f16a33da7e5e4415b25', '317fb9c81271432e95dcbfd562033393', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:46:18', '1492073178111', '01', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_ext` VALUES ('3fa7d19be8774ae4b899198d558a695f', 'cdaff7c52dbc4826a0a9df940006e372', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:47:11', '1492073231275', '01', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_ext` VALUES ('3ff5da9a95e045f2a8af96dac3f2667e', '10a6895288574d9fa55c04b5f96e478f', 'mediaId', '媒体文件id', '', '1492057530348', '01', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_ext` VALUES ('404d084782014370a6e7a3e3f53a4ce9', '8233cb6ad6e84d75a36d472c1569e3e5', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:26:07', '1492057567993', '01', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_ext` VALUES ('4224b402bb7e442db4f859173fbf73d6', '72c970c8ed2440fa8356c0de681256f7', 'integralCount', '积分数', '0', '1492057589001', '01', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_ext` VALUES ('428ad516b88045c2a0392a79b02c2845', 'cc0162aaedbc470fad09d11f619388ea', 'mediaId', '媒体文件id', '', '1492073795383', '01', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_ext` VALUES ('43aeeffacaca40d2939e21836bf9f9df', 'f79d339325ba42ada9065e80e731574a', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:21:17', '1492050077567', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 10:21:18');
INSERT INTO `com_app_user_ext` VALUES ('449ffc5113b94519b8fc804300cc8ed0', 'e8f6a7d0eae342ea8147ab5b79358766', 'integralCount', '积分数', '0', '1492057842126', '01', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_ext` VALUES ('481cd2c50a534d81a16c7a1a68cc8470', '57fd4c0f533744239bffe41136677b55', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:56:51', '1492073811323', '01', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_ext` VALUES ('48cd5680d1174a508543796dff492742', '58be520f7644420c974f69151caf3c45', 'mediaId', '媒体文件id', '', '1492057421222', '01', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_ext` VALUES ('4ab0682f536c43f1907b2bc6e671c171', 'bb5a3c0dd9744f179c506dec8e409654', 'wxQRcodeSrc', '微信二维码', '', '1492057752258', '01', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_ext` VALUES ('4d4b950e717e4c59912b2a50a14ace8b', 'ce0df9cebd2345bcadbbd5726cf84e4c', 'openId', '微信公众号个人唯一标识', '', '1492050041414', '01', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_ext` VALUES ('4ed7d3701029457392faa388337ffad5', 'cc0162aaedbc470fad09d11f619388ea', 'wxQRcodeSrc', '微信二维码', '', '1492073795391', '01', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_ext` VALUES ('4fb313b7a6694eb4be2bdb8ff27df522', 'ce0df9cebd2345bcadbbd5726cf84e4c', 'mediaId', '媒体文件id', '', '1492050041410', '01', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_ext` VALUES ('5083d8c89151468299c213c96f05ce81', 'cc0162aaedbc470fad09d11f619388ea', 'integralCount', '积分数', '0', '1492073795376', '01', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_ext` VALUES ('5335f4817c34472bae7413f75dd5dc5b', 'f9c0d068b00a4ed1a380bbb60f3d8632', 'wxQRcodeSrc', '微信二维码', '', '1492049417443', '01', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17');
INSERT INTO `com_app_user_ext` VALUES ('533fa91f79ee48139c4950b48e1d8c2f', '2b7a195260bb466c8f70c650d1958a30', 'integralCount', '积分数', '0', '1492057632923', '01', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_ext` VALUES ('55487dabe3624449aa9037dbcffaa75e', 'c3ec05c4e9bf438298b602424ae479c1', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:48:03', '1492073283498', '01', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03');
INSERT INTO `com_app_user_ext` VALUES ('55b47d59549b419baf05eba1eefe9304', '442aed9efe9348eea5dfe84e2b7296d4', 'wxQRcodeSrc', '微信二维码', '', '1492049396720', '01', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57');
INSERT INTO `com_app_user_ext` VALUES ('5a020e2c63ab40dab296896c5b61c864', '970ac5008a624f42aa44b9dc43cd1e56', 'openId', '微信公众号个人唯一标识', '', '1492057717807', '01', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38');
INSERT INTO `com_app_user_ext` VALUES ('5a9cc0c1f4fe4a818c4f19d713838b94', '40a9af4e7242458595b444f481251f26', 'integralCount', '积分数', '0', '1492057904610', '01', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_ext` VALUES ('5c50fdc4753044beb34575bfefdd24dc', '8233cb6ad6e84d75a36d472c1569e3e5', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:26:07', '1492057567989', '01', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_ext` VALUES ('5dc4d72c78524e49b4b4bd5a1263081e', '814389c1d99e40c6a98e87a777650442', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:17:41', '1492049861090', '01', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_ext` VALUES ('5ddb96445a4d4584b01ed2084edc1522', 'bb5a3c0dd9744f179c506dec8e409654', 'integralCount', '积分数', '0', '1492057752243', '01', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_ext` VALUES ('5fede68a34ec4603830c0307ccd5f313', 'bfbe2f99b0d44d0fa6c26deec514539b', 'integralCount', '积分数', '0', '1492057734445', '01', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_ext` VALUES ('61a81003d1fa4424ab5f6c46883b02cc', '10a6895288574d9fa55c04b5f96e478f', 'wxQRcodeSrc', '微信二维码', '', '1492057530357', '01', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_ext` VALUES ('6499bef2d86a4cbf943968ae2119eb66', '0f4c52c428cf4c18b9c9baf26d949460', 'integralCount', '积分数', '0', '1492049950397', '01', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_ext` VALUES ('66adc08687fe4c5db2ff3a3ec233da4b', '40a9af4e7242458595b444f481251f26', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:31:44', '1492057904617', '01', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_ext` VALUES ('66f73088e2554927bf034bcf68cdd042', '442aed9efe9348eea5dfe84e2b7296d4', 'mediaId', '媒体文件id', '', '1492049396716', '01', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57');
INSERT INTO `com_app_user_ext` VALUES ('677e72d049ac4ef6aee86001efc6ea60', 'f9c0d068b00a4ed1a380bbb60f3d8632', 'mediaId', '媒体文件id', '', '1492049417435', '01', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17');
INSERT INTO `com_app_user_ext` VALUES ('67f827c8c097422a8bce8758e659db45', 'c2abded86a9944d8a3b312b7c8828eb8', 'mediaId', '媒体文件id', '', '1492073756668', '01', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_ext` VALUES ('6901f1bae5c543fbbe1449f5a5fb177c', 'bb5a3c0dd9744f179c506dec8e409654', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:29:12', '1492057752247', '01', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_ext` VALUES ('6a0c514a39c94a3daa27ed6f9454ede3', 'bfbe2f99b0d44d0fa6c26deec514539b', 'openId', '微信公众号个人唯一标识', '', '1492057734455', '01', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_ext` VALUES ('6a34d79b98b74feebbb258aac644d3e1', '814389c1d99e40c6a98e87a777650442', 'mediaId', '媒体文件id', '', '1492049861092', '01', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_ext` VALUES ('6a6697c556514eac88f76b595ab34650', '970ac5008a624f42aa44b9dc43cd1e56', 'wxQRcodeSrc', '微信二维码', '', '1492057717805', '01', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38');
INSERT INTO `com_app_user_ext` VALUES ('6b91119dd32440acae298f9560ac5e39', 'e8f6a7d0eae342ea8147ab5b79358766', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:30:42', '1492057842132', '01', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_ext` VALUES ('6de69964e10e4fe880e5a0eaf6c16320', 'bfbe2f99b0d44d0fa6c26deec514539b', 'wxQRcodeSrc', '微信二维码', '', '1492057734453', '01', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_ext` VALUES ('6e5c7488f38343d2b74094baa0a9a4e4', 'efcd8dcfe2f24de6a741253f8c7e9790', 'integralCount', '积分数', '0', '1492049358825', '01', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19');
INSERT INTO `com_app_user_ext` VALUES ('6eb669dd78434a30b31a07620ee22013', 'ce0df9cebd2345bcadbbd5726cf84e4c', 'integralCount', '积分数', '0', '1492050041407', '01', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_ext` VALUES ('6f8d8d7cbaf445d0a2aee9cd8b4ae4e4', 'bfbe2f99b0d44d0fa6c26deec514539b', 'mediaId', '媒体文件id', '', '1492057734450', '01', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_ext` VALUES ('6ff8c371e7a14ad0a0a345b79c15f059', '10a6895288574d9fa55c04b5f96e478f', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:25:30', '1492057530352', '01', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_ext` VALUES ('70d8b0eb95d04615907e0fead032a3bd', '1e858d6dac4f474693a87467d8e095db', 'wxQRcodeSrc', '微信二维码', '', '1492073777558', '01', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_ext` VALUES ('717b966e6c6148349fa879b7d2eeafa5', 'c2abded86a9944d8a3b312b7c8828eb8', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:55:56', '1492073756671', '01', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_ext` VALUES ('717c9b580d3841979a2b3b73fd66a760', '1e858d6dac4f474693a87467d8e095db', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:56:17', '1492073777552', '01', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_ext` VALUES ('725d9a1f7c7b46a5b52c643466bab344', 'c3ec05c4e9bf438298b602424ae479c1', 'wxQRcodeSrc', '微信二维码', '', '1492073283560', '01', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:04', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:04');
INSERT INTO `com_app_user_ext` VALUES ('726a018281874fa79e0bb3f2434817f4', '970ac5008a624f42aa44b9dc43cd1e56', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:28:37', '1492057717803', '01', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38');
INSERT INTO `com_app_user_ext` VALUES ('78429f1bae55467fa09862c3d3c34498', '42e4bc5282424aefab593e5c8520c7f2', 'openId', '微信公众号个人唯一标识', '', '1492057609603', '01', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_ext` VALUES ('785434e7b1b24578b65414b1a310c3d9', '72c970c8ed2440fa8356c0de681256f7', 'openId', '微信公众号个人唯一标识', '', '1492057589018', '01', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_ext` VALUES ('7900acd7705b4dedab19dba3ef0d20af', 'bb5a3c0dd9744f179c506dec8e409654', 'mediaId', '媒体文件id', '', '1492057752251', '01', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_ext` VALUES ('82ef64286e0e44b4b8bdbb0a73f26313', 'b366dad3219442e792fac84c808af617', 'integralCount', '积分数', '0', '1492057860548', '01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_ext` VALUES ('8341568c51ec4c47a8082165e410cce6', '72c970c8ed2440fa8356c0de681256f7', 'wxQRcodeSrc', '微信二维码', '', '1492057589016', '01', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_ext` VALUES ('8552d10633c048e4a250f85788aeccf5', 'cc2ab8d65432415e92acb95179149dec', 'mediaId', '媒体文件id', '', '1492049978146', '01', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_ext` VALUES ('8a643a21cf4b41f2bd9ef0ac563338aa', '42e4bc5282424aefab593e5c8520c7f2', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:26:49', '1492057609599', '01', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_ext` VALUES ('8a7a7746cf17499fb88f48d0c16f23cc', 'b366dad3219442e792fac84c808af617', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:31:00', '1492057860552', '01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_ext` VALUES ('8a97f34305fe4858bf2c2513d63fcac7', 'c3ec05c4e9bf438298b602424ae479c1', 'integralCount', '积分数', '0', '1492073283490', '01', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03');
INSERT INTO `com_app_user_ext` VALUES ('8b47d0d6b52d471e8406acf1f66f3c1b', '40a9af4e7242458595b444f481251f26', 'openId', '微信公众号个人唯一标识', '', '1492057904622', '01', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_ext` VALUES ('8eb2bdcf3e4c47db8417c5a56650d56a', '2b7a195260bb466c8f70c650d1958a30', 'mediaId', '媒体文件id', '', '1492057632932', '01', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_ext` VALUES ('91d2594f2c0f462a8e7098a751693a51', 'efcd8dcfe2f24de6a741253f8c7e9790', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:09:18', '1492049358827', '01', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19');
INSERT INTO `com_app_user_ext` VALUES ('925376a3f31c4bda8a49cf6764a11a72', '10a6895288574d9fa55c04b5f96e478f', 'openId', '微信公众号个人唯一标识', '', '1492057530361', '01', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_ext` VALUES ('92be3cc9c4954b8086cdf6ba50300386', '814389c1d99e40c6a98e87a777650442', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:17:41', '1492049861094', '01', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_ext` VALUES ('93acb9538e0c45d08bb95719dde34d02', 'bb5a3c0dd9744f179c506dec8e409654', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:29:12', '1492057752254', '01', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_ext` VALUES ('93d802eb7edf4753bc8c02e89b6c5b3a', '8233cb6ad6e84d75a36d472c1569e3e5', 'wxQRcodeSrc', '微信二维码', '', '1492057567995', '01', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_ext` VALUES ('940e04ceb6dd463980244d5b0159f6ec', '04042bb273ac48f7903a579094dba4ae', 'openId', '微信公众号个人唯一标识', '', '1492057510010', '01', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10');
INSERT INTO `com_app_user_ext` VALUES ('94914f6107604738accc2945849ba90a', 'b366dad3219442e792fac84c808af617', 'wxQRcodeSrc', '微信二维码', '', '1492057860564', '01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_ext` VALUES ('95f240ef68224e2fbf3baff48fe4b965', 'cc0162aaedbc470fad09d11f619388ea', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:56:35', '1492073795380', '01', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_ext` VALUES ('960a339221f240709a735f9fc7e1a9a2', '0f4c52c428cf4c18b9c9baf26d949460', 'openId', '微信公众号个人唯一标识', '', '1492049950404', '01', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_ext` VALUES ('984969ade7e34cb1a7a63fd964f7637a', '970ac5008a624f42aa44b9dc43cd1e56', 'integralCount', '积分数', '0', '1492057717792', '01', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38');
INSERT INTO `com_app_user_ext` VALUES ('9a6760526c84427eac0e3fb1e14bd37d', 'ce0df9cebd2345bcadbbd5726cf84e4c', 'wxQRcodeSrc', '微信二维码', '', '1492050041413', '01', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_ext` VALUES ('9c44de3b062c4f2e9da4bac09714b69f', 'efcd8dcfe2f24de6a741253f8c7e9790', 'openId', '微信公众号个人唯一标识', '', '1492049358832', '01', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19');
INSERT INTO `com_app_user_ext` VALUES ('9cb2557a4c2f420785ff2c8bb12b1952', '40a9af4e7242458595b444f481251f26', 'wxQRcodeSrc', '微信二维码', '', '1492057904619', '01', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_ext` VALUES ('a0a767e6462043a382a524c40f1851f4', '0f4c52c428cf4c18b9c9baf26d949460', 'mediaId', '媒体文件id', '', '1492049950399', '01', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_ext` VALUES ('a26274cb1ddd4344a8beea855f29186c', 'efcd8dcfe2f24de6a741253f8c7e9790', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:09:18', '1492049358829', '01', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19');
INSERT INTO `com_app_user_ext` VALUES ('a3afbd2f48f34643ab1b0414b0e45e23', '57fd4c0f533744239bffe41136677b55', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:56:51', '1492073811330', '01', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_ext` VALUES ('a5ffa1e952a74484a13d1a679988c9a1', '58be520f7644420c974f69151caf3c45', 'openId', '微信公众号个人唯一标识', '', '1492057421226', '01', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_ext` VALUES ('a8c91e557a1a41e6b4e5c319c8df75b4', '9ea0b3f7acce4d858fcf10cb96a67ae8', 'mediaId', '媒体文件id', '', '1492073263441', '01', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_ext` VALUES ('aea947cd1fbc4db5a94dda2f9a50e92f', '317fb9c81271432e95dcbfd562033393', 'wxQRcodeSrc', '微信二维码', '', '1492073178113', '01', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_ext` VALUES ('afa0d8a47e1740c6bb0059e5efee48c3', '10a6895288574d9fa55c04b5f96e478f', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:25:30', '1492057530344', '01', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30', '10a6895288574d9fa55c04b5f96e478f', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_ext` VALUES ('b01d5faa44184ce28e5d7ab9939e546b', '442aed9efe9348eea5dfe84e2b7296d4', 'openId', '微信公众号个人唯一标识', '', '1492049396722', '01', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57');
INSERT INTO `com_app_user_ext` VALUES ('b260af67bcd14ff18f472bb1654ca854', '1e858d6dac4f474693a87467d8e095db', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:56:17', '1492073777556', '01', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_ext` VALUES ('b499eeb7f1794223bb6e004f25519aab', 'f9c0d068b00a4ed1a380bbb60f3d8632', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:10:17', '1492049417439', '01', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17');
INSERT INTO `com_app_user_ext` VALUES ('b6920da70add4324a737a332be06c484', 'ce0df9cebd2345bcadbbd5726cf84e4c', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:20:41', '1492050041411', '01', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_ext` VALUES ('ba38a897e27a4dcb9fbd7a90c3e71436', 'c3ec05c4e9bf438298b602424ae479c1', 'mediaId', '媒体文件id', '', '1492073283495', '01', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03');
INSERT INTO `com_app_user_ext` VALUES ('bd4bfcc6b1a741afbd6e6090a0178463', 'ce0df9cebd2345bcadbbd5726cf84e4c', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:20:41', '1492050041408', '01', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41', 'ce0df9cebd2345bcadbbd5726cf84e4c', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_ext` VALUES ('beece7b3c2a842d1b656f2f714443da8', '58be520f7644420c974f69151caf3c45', 'integralCount', '积分数', '0', '1492057421218', '01', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_ext` VALUES ('c3a390fe74fd4ddfb21e15f7ed1228b7', '57fd4c0f533744239bffe41136677b55', 'integralCount', '积分数', '0', '1492073811319', '01', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51', '57fd4c0f533744239bffe41136677b55', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_ext` VALUES ('c4d2f06d33a14f4596a3a48aeeb0c1eb', 'bfbe2f99b0d44d0fa6c26deec514539b', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:28:54', '1492057734451', '01', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_ext` VALUES ('c54eae39cb5d4aef82d87bcbf325c632', '04042bb273ac48f7903a579094dba4ae', 'integralCount', '积分数', '0', '1492057509983', '01', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10');
INSERT INTO `com_app_user_ext` VALUES ('c62d37fedef940bc9aa13e58df37becb', '317fb9c81271432e95dcbfd562033393', 'openId', '微信公众号个人唯一标识', '', '1492073178114', '01', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18', '317fb9c81271432e95dcbfd562033393', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_ext` VALUES ('c71529ad732d4e1b800702e5c3fb1a77', '1e858d6dac4f474693a87467d8e095db', 'mediaId', '媒体文件id', '', '1492073777554', '01', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_ext` VALUES ('c7ba64063c1e4664b0c0e0a842fe2c3d', '04042bb273ac48f7903a579094dba4ae', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:25:09', '1492057509991', '01', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10');
INSERT INTO `com_app_user_ext` VALUES ('c815c5f578f14549acc157959bd14a8c', '814389c1d99e40c6a98e87a777650442', 'integralCount', '积分数', '0', '1492049861088', '01', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_ext` VALUES ('c94df17809d4442ca7d9d145a653ffa6', 'f9c0d068b00a4ed1a380bbb60f3d8632', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 10:10:17', '1492049417432', '01', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17');
INSERT INTO `com_app_user_ext` VALUES ('cb88c416005448adb03c9077c5281904', '0f4c52c428cf4c18b9c9baf26d949460', 'wxQRcodeSrc', '微信二维码', '', '1492049950402', '01', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10', '0f4c52c428cf4c18b9c9baf26d949460', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_ext` VALUES ('cb9f1f3feb9b4a80a9692e9956e2e08c', '814389c1d99e40c6a98e87a777650442', 'wxQRcodeSrc', '微信二维码', '', '1492049861095', '01', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_ext` VALUES ('cc44e1f2212443a2818e0a88b00a7641', '72c970c8ed2440fa8356c0de681256f7', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:26:28', '1492057589014', '01', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_ext` VALUES ('cf1397c272e242feaa4bb4dbf82aa29e', '72c970c8ed2440fa8356c0de681256f7', 'mediaId', '媒体文件id', '', '1492057589010', '01', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_ext` VALUES ('d0badc3b95bb44e4a8e92182a1c71ebf', '1e858d6dac4f474693a87467d8e095db', 'integralCount', '积分数', '0', '1492073777548', '01', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_ext` VALUES ('d2158fc6a61e479e8142dab78250b680', 'c3ec05c4e9bf438298b602424ae479c1', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:48:03', '1492073283493', '01', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:03');
INSERT INTO `com_app_user_ext` VALUES ('d3530cad777645b2a1e1493631c4491d', '1e858d6dac4f474693a87467d8e095db', 'openId', '微信公众号个人唯一标识', '', '1492073777560', '01', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18', '1e858d6dac4f474693a87467d8e095db', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_ext` VALUES ('d4a11855941e4070b2cd13f1ed2ca9ff', '814389c1d99e40c6a98e87a777650442', 'openId', '微信公众号个人唯一标识', '', '1492049861097', '01', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41', '814389c1d99e40c6a98e87a777650442', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_ext` VALUES ('d6ad2e9f58cb492bb12b2c7d38b81165', 'c2abded86a9944d8a3b312b7c8828eb8', 'openId', '微信公众号个人唯一标识', '', '1492073756678', '01', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_ext` VALUES ('dbc63526ddba4cf6ad8dc3a725ad1d88', 'f9c0d068b00a4ed1a380bbb60f3d8632', 'openId', '微信公众号个人唯一标识', '', '1492049417445', '01', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17', 'f9c0d068b00a4ed1a380bbb60f3d8632', '2017-04-13 10:10:17');
INSERT INTO `com_app_user_ext` VALUES ('dc582868d1044694a2bb6af1aa7f8c94', '8233cb6ad6e84d75a36d472c1569e3e5', 'openId', '微信公众号个人唯一标识', '', '1492057567997', '01', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_ext` VALUES ('dc6f1ee101ca47bbbd5b68a853964c75', '970ac5008a624f42aa44b9dc43cd1e56', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:28:37', '1492057717796', '01', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38', '970ac5008a624f42aa44b9dc43cd1e56', '2017-04-13 12:28:38');
INSERT INTO `com_app_user_ext` VALUES ('dd6e5766185045039f4dba7e69e079ac', '58be520f7644420c974f69151caf3c45', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:23:41', '1492057421221', '01', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41', '58be520f7644420c974f69151caf3c45', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_ext` VALUES ('de9275a65d754050af96df0b120a67dd', '40a9af4e7242458595b444f481251f26', 'mediaId', '媒体文件id', '', '1492057904615', '01', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45', '40a9af4e7242458595b444f481251f26', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_ext` VALUES ('e1f769a151374400af8324a103214ff6', '42e4bc5282424aefab593e5c8520c7f2', 'mediaId', '媒体文件id', '', '1492057609597', '01', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_ext` VALUES ('e4f50984ed2545b6bb727659b08aa84a', '442aed9efe9348eea5dfe84e2b7296d4', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:09:56', '1492049396718', '01', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57');
INSERT INTO `com_app_user_ext` VALUES ('e53d7b2e3b7245569d4ad1981cefe50a', 'bfbe2f99b0d44d0fa6c26deec514539b', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:28:54', '1492057734448', '01', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54', 'bfbe2f99b0d44d0fa6c26deec514539b', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_ext` VALUES ('e61db371e6e048bf9d0f88984751ef29', '9ea0b3f7acce4d858fcf10cb96a67ae8', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:47:43', '1492073263445', '01', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_ext` VALUES ('e7dee87b045e43c4bd3c3cc01003c5a4', 'c2abded86a9944d8a3b312b7c8828eb8', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 16:55:56', '1492073756665', '01', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57', 'c2abded86a9944d8a3b312b7c8828eb8', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_ext` VALUES ('e857c19c584041729e8124724fdf447b', '42e4bc5282424aefab593e5c8520c7f2', 'integralCount', '积分数', '0', '1492057609593', '01', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50', '42e4bc5282424aefab593e5c8520c7f2', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_ext` VALUES ('eba7ad4e8971427e879504625a7de217', '9ea0b3f7acce4d858fcf10cb96a67ae8', 'openId', '微信公众号个人唯一标识', '', '1492073263454', '01', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_ext` VALUES ('ec54685505594ceeaec9e709d8a38881', 'b366dad3219442e792fac84c808af617', 'mediaId', '媒体文件id', '', '1492057860556', '01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01', 'b366dad3219442e792fac84c808af617', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_ext` VALUES ('edec70bab6b2451fabcef6654527f995', '04042bb273ac48f7903a579094dba4ae', 'wxQRcodeSrc', '微信二维码', '', '1492057510006', '01', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10');
INSERT INTO `com_app_user_ext` VALUES ('eebbca1777d642739d77e6d33818a7d2', 'cc2ab8d65432415e92acb95179149dec', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 10:19:38', '1492049978147', '01', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_ext` VALUES ('f03777bc81d142cf8e05c3475d6bf575', 'cc0162aaedbc470fad09d11f619388ea', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 16:56:35', '1492073795387', '01', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35', 'cc0162aaedbc470fad09d11f619388ea', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_ext` VALUES ('f0380d0d207f4a429d73b8383ccb32d6', '04042bb273ac48f7903a579094dba4ae', 'mediaId', '媒体文件id', '', '1492057509995', '01', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10');
INSERT INTO `com_app_user_ext` VALUES ('f1bcc6016f5d4f20896ee38a8240194b', 'cdaff7c52dbc4826a0a9df940006e372', 'integralCount', '积分数', '0', '1492073231269', '01', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_ext` VALUES ('f1f2f3be6a8b4f589a3d20e9dee96d20', '9ea0b3f7acce4d858fcf10cb96a67ae8', 'wxQRcodeSrc', '微信二维码', '', '1492073263451', '01', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43', '9ea0b3f7acce4d858fcf10cb96a67ae8', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_ext` VALUES ('f22bef3cfd5f4c9e817a3e4a13e0e171', '2b7a195260bb466c8f70c650d1958a30', 'wxQRcodeSrc', '微信二维码', '', '1492057632936', '01', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_ext` VALUES ('f2792b8043c545568573d1091c59aac4', '442aed9efe9348eea5dfe84e2b7296d4', 'integralCount', '积分数', '0', '1492049396711', '01', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57', '442aed9efe9348eea5dfe84e2b7296d4', '2017-04-13 10:09:57');
INSERT INTO `com_app_user_ext` VALUES ('f28ff22aee534cc791e6deb6b78e3e5b', 'cdaff7c52dbc4826a0a9df940006e372', 'wxQRcodeSrc', '微信二维码', '', '1492073231285', '01', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11', 'cdaff7c52dbc4826a0a9df940006e372', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_ext` VALUES ('f480f8d89b4c43fe9616c27fbfdf1032', 'e8f6a7d0eae342ea8147ab5b79358766', 'openId', '微信公众号个人唯一标识', '', '1492057842135', '01', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_ext` VALUES ('f5f864767519478384c052f715d6f9c6', 'efcd8dcfe2f24de6a741253f8c7e9790', 'wxQRcodeSrc', '微信二维码', '', '1492049358830', '01', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19', 'efcd8dcfe2f24de6a741253f8c7e9790', '2017-04-13 10:09:19');
INSERT INTO `com_app_user_ext` VALUES ('f6071f178cdf4b2a9f590e31bee2d8b8', 'bb5a3c0dd9744f179c506dec8e409654', 'openId', '微信公众号个人唯一标识', '', '1492057752262', '01', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12', 'bb5a3c0dd9744f179c506dec8e409654', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_ext` VALUES ('f72ce393a36245738e59484ccce47a46', 'cc2ab8d65432415e92acb95179149dec', 'integralCount', '积分数', '0', '1492049978142', '01', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38', 'cc2ab8d65432415e92acb95179149dec', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_ext` VALUES ('f8b9f6c61d5643e297c5ffd26a9391b5', '72c970c8ed2440fa8356c0de681256f7', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:26:28', '1492057589006', '01', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29', '72c970c8ed2440fa8356c0de681256f7', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_ext` VALUES ('f9450d9216624730bdb75cb0b30b176e', '2b7a195260bb466c8f70c650d1958a30', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:27:12', '1492057632934', '01', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_ext` VALUES ('fb35a4bf91ea42108f3b99767b5e1ee0', '2b7a195260bb466c8f70c650d1958a30', 'mediaExpiry', '媒体文件有效时间', '2017-04-13 12:27:12', '1492057632927', '01', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13', '2b7a195260bb466c8f70c650d1958a30', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_ext` VALUES ('fd8664d7de4d4e6e81af196d0458e7bd', '8233cb6ad6e84d75a36d472c1569e3e5', 'mediaId', '媒体文件id', '', '1492057567991', '01', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_ext` VALUES ('fe2aace9af8f40ae9d04cc27254b21e5', 'c3ec05c4e9bf438298b602424ae479c1', 'openId', '微信公众号个人唯一标识', '', '1492073283564', '01', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:04', 'c3ec05c4e9bf438298b602424ae479c1', '2017-04-13 16:48:04');
INSERT INTO `com_app_user_ext` VALUES ('feb538177f46446982961fd0d24d09f3', 'e8f6a7d0eae342ea8147ab5b79358766', 'mediaId', '媒体文件id', '', '1492057842130', '01', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42', 'e8f6a7d0eae342ea8147ab5b79358766', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_ext` VALUES ('ff3d08e47aad452cb556d90f59de3b70', '8233cb6ad6e84d75a36d472c1569e3e5', 'integralCount', '积分数', '0', '1492057567987', '01', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08', '8233cb6ad6e84d75a36d472c1569e3e5', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_ext` VALUES ('ffc1c87188fb4c1e903ce60a5cae0eba', '04042bb273ac48f7903a579094dba4ae', 'wxQRcodeExpiry', '微信二维码有效期', '2017-04-13 12:25:09', '1492057509999', '01', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10', '04042bb273ac48f7903a579094dba4ae', '2017-04-13 12:25:10');

-- ----------------------------
-- Table structure for com_app_user_tree
-- ----------------------------
DROP TABLE IF EXISTS `com_app_user_tree`;
CREATE TABLE `com_app_user_tree` (
  `appUserId` varchar(100) NOT NULL COMMENT '平台用户 主键id',
  `parentId` varchar(100) NOT NULL COMMENT '上级 id',
  `roleId` varchar(100) DEFAULT NULL COMMENT '角色',
  `appUserCode` varchar(100) DEFAULT NULL COMMENT '平台用户代号',
  `appUserName` varchar(100) DEFAULT NULL COMMENT '平台用户名称',
  `appUserType` varchar(100) DEFAULT NULL COMMENT '平台用户类型',
  `appUserStatus` varchar(100) DEFAULT NULL COMMENT '平台用户状态',
  `appUserNum` varchar(100) DEFAULT NULL COMMENT '平台用户编号',
  `phone` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` varchar(100) DEFAULT NULL COMMENT '性别',
  `headImgSrc` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `brithday` datetime DEFAULT NULL COMMENT '生日',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注信息',
  `level` int(100) DEFAULT NULL COMMENT '级别',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`appUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台用户';

-- ----------------------------
-- Records of com_app_user_tree
-- ----------------------------
INSERT INTO `com_app_user_tree` VALUES ('04042bb273ac48f7903a579094dba4ae', 'efcd8dcfe2f24de6a741253f8c7e9790', '02', '15877', '陈建伟', '01', '00', '', '18623329898', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:24:52', '浙江', '2', '1492057492800', '01', '1', '2017-04-13 12:25:10', '1', '2017-04-13 12:25:10');
INSERT INTO `com_app_user_tree` VALUES ('0f4c52c428cf4c18b9c9baf26d949460', '814389c1d99e40c6a98e87a777650442', '02', '15796', '张明', '01', '00', '', '18260620002', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:18:44', '测试', '2', '1492049924597', '01', '1', '2017-04-13 10:19:10', '1', '2017-04-13 10:19:10');
INSERT INTO `com_app_user_tree` VALUES ('10a6895288574d9fa55c04b5f96e478f', 'efcd8dcfe2f24de6a741253f8c7e9790', '02', '15891', '胡建坤', '01', '00', '', '18062560105', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:25:11', '苏北（南京\\南通\\徐州\\盐城\\镇江\\泰州\\宿迁\\淮安\\连云港\\扬州）', '2', '1492057511946', '01', '1', '2017-04-13 12:25:30', '1', '2017-04-13 12:25:30');
INSERT INTO `com_app_user_tree` VALUES ('1e858d6dac4f474693a87467d8e095db', '442aed9efe9348eea5dfe84e2b7296d4', '02', '16131', '李奕岐', '01', '00', '', '18627787186', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:55:58', '天津、暨南（邯郸\\保定\\沧州\\石家庄\\邢台）', '2', '1492073758988', '01', '1', '2017-04-13 16:56:18', '1', '2017-04-13 16:56:18');
INSERT INTO `com_app_user_tree` VALUES ('2b7a195260bb466c8f70c650d1958a30', '442aed9efe9348eea5dfe84e2b7296d4', '02', '15953', '李正', '01', '00', '', '18608406896', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:26:56', '新疆、陕西', '2', '1492057616013', '01', '1', '2017-04-13 12:27:13', '1', '2017-04-13 12:27:13');
INSERT INTO `com_app_user_tree` VALUES ('317fb9c81271432e95dcbfd562033393', '58be520f7644420c974f69151caf3c45', '02', '16067', '唐艳', '01', '00', '', '17701856599', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:41:38', '豫南(信阳\\南阳\\郑州\\周口\\商丘\\平顶山\\开封\\许昌\\驻马店）', '2', '1492072898816', '01', '1', '2017-04-13 16:46:18', '1', '2017-04-13 16:46:18');
INSERT INTO `com_app_user_tree` VALUES ('40a9af4e7242458595b444f481251f26', '58be520f7644420c974f69151caf3c45', '02', '16048', '高繁生', '01', '00', '', '18768877477', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:31:02', '山西、豫北（三门峡\\安阳\\焦作\\濮阳\\新乡\\洛阳）', '2', '1492057862778', '01', '1', '2017-04-13 12:31:45', '1', '2017-04-13 12:31:45');
INSERT INTO `com_app_user_tree` VALUES ('42e4bc5282424aefab593e5c8520c7f2', '442aed9efe9348eea5dfe84e2b7296d4', '02', '15937', '徐文欢', '01', '00', '', '18670388687', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:26:30', '四川（除成都）', '2', '1492057590593', '01', '1', '2017-04-13 12:26:50', '1', '2017-04-13 12:26:50');
INSERT INTO `com_app_user_tree` VALUES ('442aed9efe9348eea5dfe84e2b7296d4', '0', '01', '15766', '徐宏杰（华西大区）（北一大区）', '01', '00', '', '13761937630', '76edd970d6066b9d65b6c2ccef2aab2499ddad772cd6047e1985604bac00fa16f212a1ec9f05df10a84e5446b5ef1d3aff87535411d671af10ee29b21d6dee7b', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:09:23', '陕西、甘肃、宁夏、青海、新疆、四川、重庆、西藏；内蒙古、黑龙江、吉林、辽宁、北京、河北、天津', '1', '1492049363361', '01', '1', '2017-04-13 10:09:57', '1', '2017-04-13 16:50:39');
INSERT INTO `com_app_user_tree` VALUES ('57fd4c0f533744239bffe41136677b55', '442aed9efe9348eea5dfe84e2b7296d4', '02', '16159', '张文涛', '01', '00', '', '18670770624', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:56:41', '黑龙江、吉林、辽宁', '2', '1492073801081', '01', '1', '2017-04-13 16:56:51', '1', '2017-04-13 16:56:51');
INSERT INTO `com_app_user_tree` VALUES ('58be520f7644420c974f69151caf3c45', '0', '01', '15853', '陈贵章（北二大区）', '01', '00', '', '13482762760', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 00:00:00', '山东、河南、山西', '1', '1492057390660', '01', '1', '2017-04-13 12:23:41', '1', '2017-04-13 12:23:41');
INSERT INTO `com_app_user_tree` VALUES ('72c970c8ed2440fa8356c0de681256f7', '442aed9efe9348eea5dfe84e2b7296d4', '02', '15921', '胡思煜', '01', '00', '', '15221770277', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:26:09', '甘肃、青海、宁夏、西藏', '2', '1492057569599', '01', '1', '2017-04-13 12:26:29', '1', '2017-04-13 12:26:29');
INSERT INTO `com_app_user_tree` VALUES ('814389c1d99e40c6a98e87a777650442', '0', '01', '11111', '寇鑫（测试）', '01', '00', '', '18260620001', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:16:50', '测试', '1', '1492049110681', '01', '1', '2017-04-13 10:17:41', '1', '2017-04-13 10:17:41');
INSERT INTO `com_app_user_tree` VALUES ('8233cb6ad6e84d75a36d472c1569e3e5', '442aed9efe9348eea5dfe84e2b7296d4', '02', '15904', '缪鹏伟', '01', '00', '', '15921942249', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:25:52', '重庆、成都', '2', '1492057552657', '01', '1', '2017-04-13 12:26:08', '1', '2017-04-13 12:26:08');
INSERT INTO `com_app_user_tree` VALUES ('970ac5008a624f42aa44b9dc43cd1e56', 'f9c0d068b00a4ed1a380bbb60f3d8632', '02', '15969', '田志华', '01', '00', '', '18274979391', '75bec947c231614193d794153dc9f10f358e51a7739c8feb5efba991efbe8a9784afaefaecc80f26156a2a9652fb78f008e26c3952e8a719a1e0a6a6926ed014', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:27:50', '长沙；湖南（除长沙）', '2', '1492057670176', '01', '1', '2017-04-13 12:28:38', '1', '2017-04-13 16:30:36');
INSERT INTO `com_app_user_tree` VALUES ('9ea0b3f7acce4d858fcf10cb96a67ae8', 'efcd8dcfe2f24de6a741253f8c7e9790', '02', '16094', '王耀', '01', '00', '', '15675813418', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:47:19', '福建、深圳', '2', '1492073239639', '01', '1', '2017-04-13 16:47:43', '1', '2017-04-13 16:47:43');
INSERT INTO `com_app_user_tree` VALUES ('b366dad3219442e792fac84c808af617', '58be520f7644420c974f69151caf3c45', '02', '16029', '董法浩', '01', '00', '', '15626060110', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:30:43', '鲁西（滨州\\淄博\\德州\\枣庄\\泰安\\济南\\济宁\\聊城\\菏泽\\东营）', '2', '1492057843650', '01', '1', '2017-04-13 12:31:01', '1', '2017-04-13 12:31:01');
INSERT INTO `com_app_user_tree` VALUES ('bb5a3c0dd9744f179c506dec8e409654', 'f9c0d068b00a4ed1a380bbb60f3d8632', '02', '15997', '王景坤', '01', '00', '', '18670029911', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:28:55', '贵州、湖北', '2', '1492057735973', '01', '1', '2017-04-13 12:29:12', '1', '2017-04-13 12:29:12');
INSERT INTO `com_app_user_tree` VALUES ('bfbe2f99b0d44d0fa6c26deec514539b', 'f9c0d068b00a4ed1a380bbb60f3d8632', '02', '15984', '孙达超', '01', '00', '', '18521521616', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:28:41', '江西、安徽', '2', '1492057721835', '01', '1', '2017-04-13 12:28:54', '1', '2017-04-13 12:28:54');
INSERT INTO `com_app_user_tree` VALUES ('c2abded86a9944d8a3b312b7c8828eb8', '442aed9efe9348eea5dfe84e2b7296d4', '02', '16119', '李源', '01', '00', '', '13816148288', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:55:35', '内蒙古、冀北（唐山\\廊坊\\张家口\\承德\\秦皇岛）', '2', '1492073735155', '01', '1', '2017-04-13 16:55:57', '1', '2017-04-13 16:55:57');
INSERT INTO `com_app_user_tree` VALUES ('c3ec05c4e9bf438298b602424ae479c1', 'efcd8dcfe2f24de6a741253f8c7e9790', '02', '16107', '庄文杰', '01', '00', '', '18516026622', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:47:46', '广东（除深圳）', '2', '1492073266064', '01', '1', '2017-04-13 16:48:03', '1', '2017-04-13 16:48:03');
INSERT INTO `com_app_user_tree` VALUES ('cc0162aaedbc470fad09d11f619388ea', '442aed9efe9348eea5dfe84e2b7296d4', '02', '16146', '张淼', '01', '00', '', '18010401988', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:56:20', '北京', '2', '1492073780211', '01', '1', '2017-04-13 16:56:35', '1', '2017-04-13 16:56:35');
INSERT INTO `com_app_user_tree` VALUES ('cc2ab8d65432415e92acb95179149dec', '814389c1d99e40c6a98e87a777650442', '02', '15808', '陈华', '01', '00', '', '18260620003', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 00:00:00', '测试', '2', '1492049951941', '01', '1', '2017-04-13 10:19:38', '1', '2017-04-13 10:19:38');
INSERT INTO `com_app_user_tree` VALUES ('cdaff7c52dbc4826a0a9df940006e372', 'efcd8dcfe2f24de6a741253f8c7e9790', '02', '16078', '张涛', '01', '00', '', '18605370581', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 16:46:51', '云南、广西、海南', '2', '1492073211552', '01', '1', '2017-04-13 16:47:11', '1', '2017-04-13 16:47:11');
INSERT INTO `com_app_user_tree` VALUES ('ce0df9cebd2345bcadbbd5726cf84e4c', '0f4c52c428cf4c18b9c9baf26d949460', '03', '15826', '徐波', '01', '00', '', '18260620004', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:19:42', '4s', '3', '1492049982389', '01', '1', '2017-04-13 10:20:41', '1', '2017-04-13 10:20:41');
INSERT INTO `com_app_user_tree` VALUES ('e8f6a7d0eae342ea8147ab5b79358766', '58be520f7644420c974f69151caf3c45', '02', '16014', '刘黄明', '01', '00', '', '13548945448', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 12:30:30', '鲁东（威海\\烟台\\青岛\\日照\\潍坊\\临沂）', '2', '1492057830638', '01', '1', '2017-04-13 12:30:42', '1', '2017-04-13 12:30:42');
INSERT INTO `com_app_user_tree` VALUES ('efcd8dcfe2f24de6a741253f8c7e9790', '0', '01', '15755', '李瑞喜（华东大区）（华南大区）', '01', '00', '', '18621957679', '48d64eb2673205875fb5d1184f0d646e4f5078ffdba588947c36141c40da575522239c9440df6570d539d13c2c3e8e41381ff4773a5fc0d644fb6ad1b89c4f53', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:08:06', '上海、江苏、浙江；内蒙古、黑龙江、吉林、辽宁、北京、河北、天津', '1', '1492049286742', '01', '1', '2017-04-13 10:09:19', '1', '2017-04-13 16:49:12');
INSERT INTO `com_app_user_tree` VALUES ('f79d339325ba42ada9065e80e731574a', 'ce0df9cebd2345bcadbbd5726cf84e4c', '04', '15837', '黄www', '01', '00', '', '18260621130', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:20:50', '销售', '4', '1492050050349', '01', '1', '2017-04-13 10:21:18', '1', '2017-04-13 10:21:18');
INSERT INTO `com_app_user_tree` VALUES ('f9c0d068b00a4ed1a380bbb60f3d8632', '0', '01', '15777', '李刚（华中大区）', '01', '00', '', '18900727796', '946902951a38ca09382f3b7d3a29e7239ef53f93545362d21a7a1fce081c1b0497cb34af1c3477236f979176aa231a55da949416c572fdb8cb8756d8e2c7cd9f', '01', 'static/ace/avatars/user.jpg', '2017-04-13 10:09:59', '安徽、江西、湖北、湖南、贵州', '1', '1492049399695', '01', '1', '2017-04-13 10:10:17', '1', '2017-04-13 10:10:40');

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
INSERT INTO `com_dict_tree` VALUES ('01a0055a41bc4fabb86a1849d0597247', '66ad0e8cf26644ddb5ab57fa762b4091', 'com_isEffective_00', '失效', '01', '00', '00', '1', '1489977484105', '01', '1', '2017-03-20 10:38:33', '1', '2017-03-20 10:38:33');
INSERT INTO `com_dict_tree` VALUES ('049f63a9f923403e94e3056a0216afaf', '0', 'com_dictEffective', '生效数据字典', '02', '00', 'com_dictEffective', '0', '1484804310182', '01', '1', '2017-01-19 13:38:49', '1', '2017-01-19 13:51:18');
INSERT INTO `com_dict_tree` VALUES ('05165084fb2c426b93042f8e5e0b4bbe', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_06', 'Img', '01', '00', '06', '1', '1490061414948', '01', '1', '2017-03-21 09:57:30', '1', '2017-03-21 09:57:30');
INSERT INTO `com_dict_tree` VALUES ('06f38de637814d9285397f1baed966f9', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_02', '跑批任务', '01', '00', '02', '1', '1485051595520', '01', '1', '2017-01-22 10:20:16', '1', '2017-01-22 10:20:16');
INSERT INTO `com_dict_tree` VALUES ('087cf290eefb4c65957ff15d8aa8b59b', '66ad0e8cf26644ddb5ab57fa762b4091', 'com_isEffective_01', '生效', '01', '00', '01', '1', '1489977514802', '01', '1', '2017-03-20 10:38:44', '1', '2017-03-20 10:38:54');
INSERT INTO `com_dict_tree` VALUES ('0bfe186203cd4beea7355649ee9122f6', '78b4651dca85412095298b54c2d01128', 'com_productCategoryType_03', '自定义类型', '01', '00', '03', '1', '1487989230516', '01', '1', '2017-02-25 10:20:50', '1', '2017-02-25 10:20:50');
INSERT INTO `com_dict_tree` VALUES ('0c608e9327344f588eebd8edce01de8b', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_01', '标准型', '01', '00', '01', '1', '1484025817953', '01', '1', '2017-01-10 13:23:38', '1', '2017-01-10 13:23:38');
INSERT INTO `com_dict_tree` VALUES ('1267d5aa01d440b1a8a686e9494eeece', '0', 'com_appUserType', '平台用户类型', '01', '00', 'com_integralCustomerType', '0', '1488524156915', '01', '1', '2017-03-03 14:56:46', '1', '2017-03-06 15:51:54');
INSERT INTO `com_dict_tree` VALUES ('132b054047c14abc87f6be78ad0f710a', '0', 'bg_userEffective', '后台生效用户', '02', '00', 'bg_userEffective', '0', '1484726025794', '01', '1', '2017-01-18 15:55:03', '1', '2017-01-18 15:55:09');
INSERT INTO `com_dict_tree` VALUES ('1355e964b9164d26bb898518d551b518', 'f4d48ab77e274cf4bccbc2c705bd9b13', 'com_shopCarStatus_01', '加入购物车', '01', '00', '01', '1', '1490233744926', '01', '1', '2017-03-23 09:49:50', '1', '2017-03-23 09:49:50');
INSERT INTO `com_dict_tree` VALUES ('14ff3ee812a44691b125d9f4c74142ec', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_scancode_waitmsg', '扫码推事件且弹出“消息接收中”提示框', '01', '00', 'scancode_waitmsg', '1', '1486366098596', '01', '1', '2017-02-06 15:28:33', '1', '2017-02-06 15:28:33');
INSERT INTO `com_dict_tree` VALUES ('16e4fa8be1b4408eae79516c55733df5', '0', 'com_styleCategoryEffective', '生效商品规格分类', '02', '00', 'com_styleCategoryEffective', '0', '1489050940474', '01', '1', '2017-03-09 17:15:53', '1', '2017-03-09 17:16:28');
INSERT INTO `com_dict_tree` VALUES ('16f7cdaebefb469aa05253bd3425fcb7', 'b3e931eea48344c488a11fdc5dac9043', 'com_receiveAddressStatus_00', '非默认', '01', '00', '00', '1', '1490000443175', '01', '1', '2017-03-20 17:01:02', '1', '2017-03-20 17:01:02');
INSERT INTO `com_dict_tree` VALUES ('173468d2a2d24dd48642f6cd452c11d2', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_03', '微信回调', '01', '00', '03', '1', '1485051617535', '01', '1', '2017-01-22 10:21:26', '1', '2017-01-22 10:21:26');
INSERT INTO `com_dict_tree` VALUES ('19c85d8153d243c1ac36196a0dadfab3', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_02', '审核成功', '01', '00', '02', '1', '1488611187930', '01', '1', '2017-03-04 15:06:44', '1', '2017-03-04 15:06:44');
INSERT INTO `com_dict_tree` VALUES ('1c0caa13e3514c87a3280f2f48653d83', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_02', 'Int', '01', '00', '02', '1', '1484018861734', '01', '1', '2017-01-10 11:27:42', '1', '2017-01-11 16:26:59');
INSERT INTO `com_dict_tree` VALUES ('1f15277411304bb3931bd1126d089d36', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_07', '已退款', '01', '00', '07', '1', '1492071028793', '01', '1', '2017-04-13 16:10:49', '1', '2017-04-13 16:10:49');
INSERT INTO `com_dict_tree` VALUES ('2425594bf395472199d41d81611b2f60', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_04', '待评价', '01', '00', '04', '1', '1492070919633', '01', '1', '2017-04-13 16:09:11', '1', '2017-04-13 16:09:11');
INSERT INTO `com_dict_tree` VALUES ('254029268bdf4c309c0e32fb30fa8425', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_pic_weixin', '弹出微信相册发图器', '01', '00', 'pic_weixin', '1', '1486366170539', '01', '1', '2017-02-06 15:29:40', '1', '2017-02-06 15:29:40');
INSERT INTO `com_dict_tree` VALUES ('280f1698d19d49bbb676b09f95d24a51', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_02', '业务菜单', '01', '00', '02', '1', '1484555389350', '01', '1', '2017-01-16 16:30:42', '1', '2017-01-16 16:30:42');
INSERT INTO `com_dict_tree` VALUES ('28510c12e9834ea29bbb5621f99e804e', '0', 'bg_mapleDetailType', '字段属性类型', '01', '00', 'bg_mapleDetailType', '0', '1484018714788', '01', '1', '2017-01-10 11:25:15', '1', '2017-01-11 16:23:30');
INSERT INTO `com_dict_tree` VALUES ('2b6adc1e3f5c48fd8f0778d0bbed13c3', '0', 'com_sex', '性别', '01', '00', 'com_sex', '0', '1488790660180', '01', '1', '2017-03-06 16:58:01', '1', '2017-03-06 16:58:01');
INSERT INTO `com_dict_tree` VALUES ('2c43919dfe434841a92bc2a39b2ad02e', '0', 'bg_menuType', '后台菜单类型', '01', '00', 'bg_menuType', '0', '1484555292472', '01', '1', '2017-01-16 16:28:52', '1', '2017-01-16 16:28:52');
INSERT INTO `com_dict_tree` VALUES ('30a83d0bc4364ac39edb8d0f5786a338', '0', 'bg_crontabType', '定时任务类型', '01', '00', 'bg_crontabType', '0', '1485051437664', '01', '1', '2017-01-22 10:18:50', '1', '2017-01-22 10:18:50');
INSERT INTO `com_dict_tree` VALUES ('30c39f10c07e4b3b905734277e527eee', '0', 'com_sf', '是否判断', '01', '00', 'com_sf', '0', '1484536370790', '01', '1', '2017-01-16 11:13:37', '1', '2017-01-16 11:18:18');
INSERT INTO `com_dict_tree` VALUES ('319bbf4a475b4aa4b852c30d91d9c48c', '0', 'com_wxAccountType', '微信账号类型', '01', '00', 'com_wxAccountType', '0', '1484895809412', '01', '1', '2017-01-20 15:04:51', '1', '2017-01-20 15:04:51');
INSERT INTO `com_dict_tree` VALUES ('351a934eb7654c2db753fc51b39d3e16', '0', 'bg_userType', '后台用户类型', '01', '00', 'bg_userType', '0', '1484804429010', '01', '1', '2017-01-19 13:41:51', '1', '2017-01-19 13:41:51');
INSERT INTO `com_dict_tree` VALUES ('366429147bef4767a545483285ee0b3c', '351a934eb7654c2db753fc51b39d3e16', 'bg_userType_02', '注册用户', '01', '00', '02', '1', '1484804565584', '01', '1', '2017-01-19 13:43:10', '1', '2017-01-19 13:43:10');
INSERT INTO `com_dict_tree` VALUES ('3722cf7a9cb444f8b6e8263427254615', '689c9302878441e0b5639b8164d48b11', 'com_lbtType_01', '商品', '01', '00', '01', '1', '1489823383626', '01', '1', '2017-03-18 15:50:33', '1', '2017-03-18 15:50:33');
INSERT INTO `com_dict_tree` VALUES ('3894f644c42e441aa1a605513f7c22c6', '0', 'bg_menuEffective', '后台生效菜单', '02', '00', 'bg_menuEffective', '0', '1484804259249', '01', '1', '2017-01-19 13:37:57', '1', '2017-01-19 13:51:05');
INSERT INTO `com_dict_tree` VALUES ('3d278f1466ed4b8998e1c198ea8ff770', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_media_id', '下发消息（除文本消息）', '01', '00', 'media_id', '1', '1486366199642', '01', '1', '2017-02-06 15:30:13', '1', '2017-02-06 15:30:13');
INSERT INTO `com_dict_tree` VALUES ('3fa408225ffc4591b2b5a6fbf0fddc95', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_05', '交易成功', '01', '00', '05', '1', '1492070952819', '01', '1', '2017-04-13 16:09:30', '1', '2017-04-13 16:09:30');
INSERT INTO `com_dict_tree` VALUES ('42293fbe78a649568d01da16ff66040f', 'b120815786714648a7247046fe7186d9', 'com_dictType_02', '数据库字典', '01', '00', '02', '1', '1484017474427', '01', '1', '2017-01-10 11:04:34', '1', '2017-01-10 11:04:34');
INSERT INTO `com_dict_tree` VALUES ('4324f08d02c943ab96e9078b01f703d8', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_04', '主从型（从）', '01', '00', '04', '1', '1484025928454', '01', '1', '2017-01-10 13:25:28', '1', '2017-01-10 13:25:28');
INSERT INTO `com_dict_tree` VALUES ('44fc955772664959bd3704eec18e1a39', 'b3e931eea48344c488a11fdc5dac9043', 'com_receiveAddressStatus_01', '默认', '01', '00', '01', '1', '1490000422338', '01', '1', '2017-03-20 17:00:41', '1', '2017-03-20 17:00:41');
INSERT INTO `com_dict_tree` VALUES ('45c83e69651949d4b343dae672baed81', '0', 'com_supplierType', '供应商类型', '01', '00', 'com_supplierType', '0', '1487988818173', '01', '1', '2017-02-25 10:14:52', '1', '2017-02-25 10:14:52');
INSERT INTO `com_dict_tree` VALUES ('46d6fd8c6ccf4d8aa5c7c6c57b063b38', '78b4651dca85412095298b54c2d01128', 'com_productCategoryType_01', '商品类别', '01', '00', '01', '1', '1487989059313', '01', '1', '2017-02-25 10:19:35', '1', '2017-02-25 10:19:35');
INSERT INTO `com_dict_tree` VALUES ('4939f73cd85f4675a469bbbcf56f91dc', '1267d5aa01d440b1a8a686e9494eeece', 'com_appUserType_01', '后台添加', '01', '00', '01', '1', '1488524212184', '01', '1', '2017-03-03 14:57:52', '1', '2017-03-06 15:55:04');
INSERT INTO `com_dict_tree` VALUES ('4ac70dbc8b314418b232a6c7b70aa916', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_03', '4S点客户', '01', '00', '03', '1', '1488524425691', '01', '1', '2017-03-03 15:00:48', '1', '2017-03-06 15:59:47');
INSERT INTO `com_dict_tree` VALUES ('4cf11a0ce3954422962cc670638dabfa', '45c83e69651949d4b343dae672baed81', 'com_supplierType_01', '淘宝商家', '01', '00', '01', '1', '1487988914006', '01', '1', '2017-02-25 10:16:11', '1', '2017-02-25 10:16:11');
INSERT INTO `com_dict_tree` VALUES ('4ed0b868ed2b4d69887bcef4932eb4ef', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_05', 'Dict', '01', '00', '05', '1', '1484019114333', '01', '1', '2017-01-10 11:31:54', '1', '2017-01-11 16:27:15');
INSERT INTO `com_dict_tree` VALUES ('4f220902c9a94be2a15175156b4df257', 'e859d1a4e25640a18e5ed39290ed078a', 'com_productStatus_01', '已上架', '01', '00', '01', '1', '1490146196740', '01', '1', '2017-03-22 09:30:27', '1', '2017-03-22 09:30:27');
INSERT INTO `com_dict_tree` VALUES ('500d275630af44d5a8dc4448b2037ceb', '0', 'com_orderStatus', '订单状态', '01', '00', 'com_orderStatus', '0', '1492070596060', '01', '1', '2017-04-13 16:07:02', '1', '2017-04-13 16:07:02');
INSERT INTO `com_dict_tree` VALUES ('540b4214c9ea4927867b1a878365ec9c', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_wx', 'weixin', '01', '00', 'wx', '1', '1484018416335', '01', '1', '2017-01-10 11:20:16', '1', '2017-01-10 11:20:16');
INSERT INTO `com_dict_tree` VALUES ('54467b4e2dc84c1ca9e21828803eb9b8', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_04', 'Double', '01', '00', '04', '1', '1484019019767', '01', '1', '2017-01-10 11:30:20', '1', '2017-01-11 16:27:10');
INSERT INTO `com_dict_tree` VALUES ('545f7d1209cb407785abca0aa597512b', '78b4651dca85412095298b54c2d01128', 'com_productCategoryType_02', '商品模块', '01', '00', '02', '1', '1487989181508', '01', '1', '2017-02-25 10:20:25', '1', '2017-02-25 10:20:25');
INSERT INTO `com_dict_tree` VALUES ('557fcb9b45c3430d93822cfb4863b05d', '2b6adc1e3f5c48fd8f0778d0bbed13c3', 'com_sex_01', '男', '01', '00', '01', '1', '1488790685296', '01', '1', '2017-03-06 16:58:19', '1', '2017-03-06 16:58:19');
INSERT INTO `com_dict_tree` VALUES ('55be1a6cf32041b9addd3aa8ac43d52c', '0', 'com_productEffective', '生效商品', '02', '00', 'com_productEffective', '0', '1489050565760', '01', '1', '2017-03-09 17:12:25', '1', '2017-03-09 17:14:46');
INSERT INTO `com_dict_tree` VALUES ('572053db6aa44c4ba95d1b73accaad0c', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_location_select', '弹出地理位置选择器', '01', '00', 'location_select', '1', '1486366184718', '01', '1', '2017-02-06 15:29:54', '1', '2017-02-06 15:29:54');
INSERT INTO `com_dict_tree` VALUES ('5ac162d4481c42d79f50098fbb69aaa8', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_01', '待付款', '01', '00', '01', '1', '1492070850371', '01', '1', '2017-04-13 16:07:50', '1', '2017-04-13 16:07:50');
INSERT INTO `com_dict_tree` VALUES ('5bc08d1220ed4fd5912d25b10320ea46', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_04', '销售员', '01', '00', '04', '1', '1488787227290', '01', '1', '2017-03-15 14:11:33', '1', '2017-03-15 14:11:45');
INSERT INTO `com_dict_tree` VALUES ('609bbe934cd24f7588af2f717a8d49f3', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_03', 'Time', '01', '00', '03', '1', '1484019001039', '01', '1', '2017-01-10 11:30:01', '1', '2017-01-11 16:27:04');
INSERT INTO `com_dict_tree` VALUES ('66ad0e8cf26644ddb5ab57fa762b4091', '0', 'com_isEffective', '是否生效', '01', '00', 'com_isEffective', '0', '1489977444920', '01', '1', '2017-03-20 10:38:01', '1', '2017-03-20 10:38:01');
INSERT INTO `com_dict_tree` VALUES ('66e83e268bd24bb9b82187c00892d832', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_scancode_push', '扫码推事件', '01', '00', 'scancode_push', '1', '1486366082814', '01', '1', '2017-02-06 15:28:14', '1', '2017-02-06 15:28:14');
INSERT INTO `com_dict_tree` VALUES ('68297fba30ee4f3d92b0448a578b53e9', 'f4d48ab77e274cf4bccbc2c705bd9b13', 'com_shopCarStatus_02', '生成订单', '01', '00', '02', '1', '1490233792738', '01', '1', '2017-03-23 09:50:36', '1', '2017-03-23 09:50:36');
INSERT INTO `com_dict_tree` VALUES ('689c9302878441e0b5639b8164d48b11', '0', 'com_lbtType', '轮播图类型', '01', '00', 'com_lbtType', '0', '1489823327496', '01', '1', '2017-03-18 15:49:11', '1', '2017-03-18 15:49:11');
INSERT INTO `com_dict_tree` VALUES ('6d58e343386e47afbf79fb2dbc8c7d76', 'b120815786714648a7247046fe7186d9', 'com_dictType_01', '参数字典', '01', '00', '01', '1', '1484017427635', '01', '1', '2017-01-10 11:03:48', '1', '2017-01-10 11:03:48');
INSERT INTO `com_dict_tree` VALUES ('768e01fdd0b9450080a6b0f97a626eae', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_click', '点击推事件', '01', '00', 'click', '1', '1486365867381', '01', '1', '2017-02-06 15:24:59', '1', '2017-02-06 15:24:59');
INSERT INTO `com_dict_tree` VALUES ('78b4651dca85412095298b54c2d01128', '0', 'com_productCategoryType', '商品分类类型', '01', '00', 'com_productCategoryType', '0', '1487989011726', '01', '1', '2017-02-25 10:17:34', '1', '2017-02-25 10:17:34');
INSERT INTO `com_dict_tree` VALUES ('79750bdf92c840e6bcc2fe2cb25cfda5', 'a60fe6c08d7c4708bca2a20cb2feb681', 'bg_roleType_01', '一般角色', '01', '00', '01', '1', '1484804728835', '01', '1', '2017-01-19 13:46:17', '1', '2017-01-19 13:46:17');
INSERT INTO `com_dict_tree` VALUES ('7a4aeb77544c47368075cb7199d42bcb', '0', 'com_sparepartDealStatus', '零配件订单审核状态', '01', '00', 'com_sparepartDealStatus', '0', '1488611074477', '01', '1', '2017-03-04 15:05:15', '1', '2017-03-04 15:05:15');
INSERT INTO `com_dict_tree` VALUES ('7c901ea2b8694c69a2f21cc8ad9a7cb8', 'a39cce16086341e4b6a756e1fe00f104', 'com_curType_01', '人民币', '01', '00', '01', '1', '1489137981117', '01', '1', '2017-03-10 17:26:37', '1', '2017-03-10 17:26:37');
INSERT INTO `com_dict_tree` VALUES ('7faad10eb0424cf29a51fb5add0f1b50', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_01', 'String', '01', '00', '01', '1', '1484018802176', '01', '1', '2017-01-10 11:26:42', '1', '2017-01-11 16:26:54');
INSERT INTO `com_dict_tree` VALUES ('808f87b42557462f822c8d82954a8481', '0', 'com_appUserRole', '平台用户角色', '01', '00', 'com_integralCustomerRole', '0', '1488524362820', '01', '1', '2017-03-03 14:59:41', '1', '2017-03-06 16:00:18');
INSERT INTO `com_dict_tree` VALUES ('86d4dd7fe9bf437fb683de7a4635361a', '1267d5aa01d440b1a8a686e9494eeece', 'com_appUserType_02', '微信注册', '01', '00', '02', '1', '1489559609670', '01', '1', '2017-03-15 14:33:42', '1', '2017-03-15 14:33:42');
INSERT INTO `com_dict_tree` VALUES ('894b66f11f8e44a79ac7f111cd886530', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_02', '小区经理', '01', '00', '02', '1', '1488524406401', '01', '1', '2017-03-03 15:00:24', '1', '2017-03-06 15:59:41');
INSERT INTO `com_dict_tree` VALUES ('8966bc3198364838a95e154308d0ba01', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_02', '待发货', '01', '00', '02', '1', '1492070871391', '01', '1', '2017-04-13 16:08:13', '1', '2017-04-13 16:08:13');
INSERT INTO `com_dict_tree` VALUES ('8a3b081ef7684f86b676b3bd97417c77', '0', 'com_productStyleEffective', '生效商品规格', '02', '00', 'com_productStyleEffective', '0', '1489050954620', '01', '1', '2017-03-09 17:16:08', '1', '2017-03-09 17:16:31');
INSERT INTO `com_dict_tree` VALUES ('8a3d8afa62204207a1f5282be831ce23', '689c9302878441e0b5639b8164d48b11', 'com_lbtType_02', '商品类型', '01', '00', '02', '1', '1489823434448', '01', '1', '2017-03-18 15:51:03', '1', '2017-03-18 15:51:03');
INSERT INTO `com_dict_tree` VALUES ('8aa3ce5197ca48f4a48f58a541a66543', 'f4d48ab77e274cf4bccbc2c705bd9b13', 'com_shopCarStatus_03', '失效', '01', '00', '03', '1', '1490233849757', '01', '1', '2017-03-23 09:54:12', '1', '2017-03-23 09:54:12');
INSERT INTO `com_dict_tree` VALUES ('8b4b8a9f246a4204b8c6029a275d0102', 'a39cce16086341e4b6a756e1fe00f104', 'com_curType_02', '积分', '01', '00', '02', '1', '1489137998847', '01', '1', '2017-03-10 17:27:16', '1', '2017-03-10 17:27:16');
INSERT INTO `com_dict_tree` VALUES ('8ea0f054459f4e619c8a75bfd90f67bb', 'a60fe6c08d7c4708bca2a20cb2feb681', 'bg_roleType_00', '系统管理员角色', '01', '00', '00', '1', '1484804778802', '01', '1', '2017-01-19 13:46:56', '1', '2017-01-19 13:46:56');
INSERT INTO `com_dict_tree` VALUES ('8f1b49981a4d449383bfb703c0176bd7', '351a934eb7654c2db753fc51b39d3e16', 'bg_userType_01', '录入用户', '01', '00', '01', '1', '1484804518887', '01', '1', '2017-01-19 13:42:43', '1', '2017-01-19 13:42:43');
INSERT INTO `com_dict_tree` VALUES ('905cdbb48e804421848a656750619764', '0', 'com_productCustomTypeEffective', '生效商品自定义类型', '02', '00', 'com_productCustomTypeEffective', '0', '1489048359818', '01', '1', '2017-03-09 16:32:53', '1', '2017-03-09 17:12:09');
INSERT INTO `com_dict_tree` VALUES ('916b2c8543b94d488d80a5a08f05b681', '0', 'bg_roleEffective', '后台生效角色', '02', '00', 'bg_roleEffective', '0', '1484804220171', '01', '1', '2017-01-19 13:37:22', '1', '2017-01-19 13:37:37');
INSERT INTO `com_dict_tree` VALUES ('9425b3d5bd8c477fb090bb1cdad1f76d', '0', 'com_styleCategoryEffectiveP', '生效商品规格明细分类', '02', '00', 'com_styleCategoryEffectiveP', '0', '1489134159206', '01', '1', '2017-03-10 16:22:53', '1', '2017-03-10 16:22:53');
INSERT INTO `com_dict_tree` VALUES ('97e0038c0d264bf3a284a0552f952fb4', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_03', '待收货', '01', '00', '03', '1', '1492070894786', '01', '1', '2017-04-13 16:08:38', '1', '2017-04-13 16:08:38');
INSERT INTO `com_dict_tree` VALUES ('97eb8171437447fc8714a8c1a2075b9a', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_01', '数据表备份', '01', '00', '01', '1', '1485051549949', '01', '1', '2017-01-22 10:19:54', '1', '2017-01-22 10:19:54');
INSERT INTO `com_dict_tree` VALUES ('9ede63687cce41c380773ba1917519c9', 'fc97312d70a84af186a000882941e5fc', 'com_sparepartType_01', '朴易汽车零部件', '01', '00', '01', '1', '1488529563651', '01', '1', '2017-03-03 16:26:45', '1', '2017-03-03 16:26:45');
INSERT INTO `com_dict_tree` VALUES ('9f3b2efb9d134a68b90ce00ea00c63f9', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_00', '已取消', '01', '00', '00', '1', '1492070826696', '01', '1', '2017-04-13 16:07:29', '1', '2017-04-13 16:07:29');
INSERT INTO `com_dict_tree` VALUES ('a39cce16086341e4b6a756e1fe00f104', '0', 'com_curType', '货币种类', '01', '00', 'com_curType', '0', '1489137953268', '01', '1', '2017-03-10 17:26:05', '1', '2017-03-10 17:26:05');
INSERT INTO `com_dict_tree` VALUES ('a5266725e42e40b78899453fd81bd9b5', '500d275630af44d5a8dc4448b2037ceb', 'com_orderStatus_06', '退款申请中', '01', '00', '06', '1', '1492070973833', '01', '1', '2017-04-13 16:10:21', '1', '2017-04-13 16:10:21');
INSERT INTO `com_dict_tree` VALUES ('a60fe6c08d7c4708bca2a20cb2feb681', '0', 'bg_roleType', '后台角色类型', '01', '00', 'bg_roleType', '0', '1484804620997', '01', '1', '2017-01-19 13:45:08', '1', '2017-01-19 13:45:08');
INSERT INTO `com_dict_tree` VALUES ('b120815786714648a7247046fe7186d9', '0', 'com_dictType', '字典类型', '01', '00', 'com_dictType', '0', '1484017251117', '01', '1', '2017-01-10 11:00:51', '1', '2017-02-25 10:10:45');
INSERT INTO `com_dict_tree` VALUES ('b20d74e13ced4513b1ac20c4ead6d736', '319bbf4a475b4aa4b852c30d91d9c48c', 'com_wxAccountType_01', '服务号', '01', '00', '01', '1', '1484896386061', '01', '1', '2017-01-20 15:14:06', '1', '2017-01-20 15:14:06');
INSERT INTO `com_dict_tree` VALUES ('b3e931eea48344c488a11fdc5dac9043', '0', 'com_receiveAddressStatus', '收货地址状态', '01', '00', 'com_receiveAddressStatus', '0', '1490000241401', '01', '1', '2017-03-20 16:58:25', '1', '2017-03-20 16:58:25');
INSERT INTO `com_dict_tree` VALUES ('b4bf5552e7454647b535c372a422c069', '0', 'com_appUserEffective', '生效平台用户', '02', '00', 'com_appUserEffective', '0', '1488607885253', '01', '1', '2017-03-04 14:12:55', '1', '2017-03-07 09:49:13');
INSERT INTO `com_dict_tree` VALUES ('bc443acf9b7f48fca6b44934d79a7c99', '0', 'com_sparepartEffective', '生效零配件', '02', '00', 'com_sparepartEffective', '0', '1488608049859', '01', '1', '2017-03-04 14:28:20', '1', '2017-03-04 14:37:10');
INSERT INTO `com_dict_tree` VALUES ('c3731311fb334e4697c2fb76ab1343be', '2b6adc1e3f5c48fd8f0778d0bbed13c3', 'com_sex_02', '女', '01', '00', '02', '1', '1488790700615', '01', '1', '2017-03-06 16:58:40', '1', '2017-03-06 16:58:40');
INSERT INTO `com_dict_tree` VALUES ('c4e773c1a20f45788c12789267d6065b', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_pic_sysphoto', '弹出系统拍照发图', '01', '00', 'pic_sysphoto', '1', '1486366136184', '01', '1', '2017-02-06 15:29:07', '1', '2017-02-06 15:29:07');
INSERT INTO `com_dict_tree` VALUES ('ce4c393e1a0b4bec9bf1166231da6d7f', '689c9302878441e0b5639b8164d48b11', 'com_lbtType_00', '图片', '01', '00', '00', '1', '1489823360833', '01', '1', '2017-03-18 15:49:42', '1', '2017-03-18 15:49:42');
INSERT INTO `com_dict_tree` VALUES ('cef79c2b540147418eb37ab3a3c31272', '0', 'com_productTypeEffective', '生效商品类别', '02', '00', 'com_productTypeEffective', '0', '1489047547471', '01', '1', '2017-03-09 16:20:19', '1', '2017-03-09 16:49:52');
INSERT INTO `com_dict_tree` VALUES ('d29a2f6aab6d4d21babbfc338d90d155', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_03', '审核失败', '01', '00', '03', '1', '1488611205295', '01', '1', '2017-03-04 15:06:59', '1', '2017-03-04 15:06:59');
INSERT INTO `com_dict_tree` VALUES ('d40e20a567a34f04b9af55db0f28474e', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_view', '跳转URL', '01', '00', 'view', '1', '1486366048828', '01', '1', '2017-02-06 15:27:52', '1', '2017-02-06 15:27:52');
INSERT INTO `com_dict_tree` VALUES ('d77946d83bed4b1686396e78d002cee0', '0', 'com_appUserEffective4S', '生效平台用户（4S店）', '02', '00', 'com_appUserEffective4S', '0', '1488608002089', '01', '1', '2017-03-04 14:13:59', '1', '2017-03-07 09:52:33');
INSERT INTO `com_dict_tree` VALUES ('d8374ef4d31541a38ea946aa7c243c8a', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_00', '菜单分目', '01', '00', '00', '1', '1486365867380', '01', '1', '2017-02-06 16:50:35', '1', '2017-02-06 16:50:35');
INSERT INTO `com_dict_tree` VALUES ('d8cf8becf1db4418a098a43500d5cf3f', '0', 'com_packageType', '模块包分类', '01', '00', 'com_packageType', '0', '1484017801545', '01', '1', '2017-01-10 11:10:02', '1', '2017-01-10 11:10:02');
INSERT INTO `com_dict_tree` VALUES ('da15415d7faa43c9abb31701350d0958', '0', 'bg_wxMenuBtnType', '菜单按钮类型', '01', '00', 'bg_wxMenuBtnType', '0', '1486365490238', '01', '1', '2017-02-06 15:19:12', '1', '2017-02-06 15:19:12');
INSERT INTO `com_dict_tree` VALUES ('dee79448e4e546b08bf6c56af9bb8efc', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_01', '大区经理', '01', '00', '01', '1', '1488524385790', '01', '1', '2017-03-03 15:00:05', '1', '2017-03-06 15:59:35');
INSERT INTO `com_dict_tree` VALUES ('e10e75cb973440349c4d457597df3f69', 'e859d1a4e25640a18e5ed39290ed078a', 'com_productStatus_00', '未上架', '01', '00', '00', '1', '1490146227641', '01', '1', '2017-03-22 09:30:41', '1', '2017-03-22 09:30:41');
INSERT INTO `com_dict_tree` VALUES ('e2a07b8b995449dbb70d4e59cb189ea3', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_00', '普通用户', '01', '00', '00', '1', '1488787227291', '01', '1', '2017-03-06 16:00:52', '1', '2017-03-06 16:00:52');
INSERT INTO `com_dict_tree` VALUES ('e385f5a7938143f4b4fe8e88c9771cd5', '45c83e69651949d4b343dae672baed81', 'com_supplierType_02', '实体店家', '01', '00', '02', '1', '1487988972913', '01', '1', '2017-02-25 10:16:43', '1', '2017-02-25 10:16:43');
INSERT INTO `com_dict_tree` VALUES ('e387c8a8f2364d9798fdb323179d034c', '0', 'com_supplierEffective', '生效供应商', '02', '00', 'com_supplierEffective', '0', '1489047419924', '01', '1', '2017-03-09 16:17:32', '1', '2017-03-09 16:53:24');
INSERT INTO `com_dict_tree` VALUES ('e389a2810d0d45a1ba4596c679e7a544', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_pic_photo_or_album', '弹出拍照或者相册发图', '01', '00', 'pic_photo_or_album', '1', '1486366153961', '01', '1', '2017-02-06 15:29:24', '1', '2017-02-06 15:29:24');
INSERT INTO `com_dict_tree` VALUES ('e53230ed682741f4ad63e470e0d86488', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_mb', 'mobile', '01', '00', 'mb', '1', '1484018478585', '01', '1', '2017-01-10 11:21:19', '1', '2017-01-10 11:21:33');
INSERT INTO `com_dict_tree` VALUES ('e67e77715a7c41dd9acda383e6bbe8b3', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_01', '待审核', '01', '00', '01', '1', '1488611166327', '01', '1', '2017-03-04 15:06:26', '1', '2017-03-04 15:06:26');
INSERT INTO `com_dict_tree` VALUES ('e69288a45145429c8c3a865024d4b83c', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_02', '树形', '01', '00', '02', '1', '1484025846486', '01', '1', '2017-01-10 13:24:06', '1', '2017-01-10 13:24:06');
INSERT INTO `com_dict_tree` VALUES ('e859d1a4e25640a18e5ed39290ed078a', '0', 'com_productStatus', '商品上架状态', '01', '00', 'com_productStatus', '0', '1490146174395', '01', '1', '2017-03-22 09:29:53', '1', '2017-03-22 09:29:53');
INSERT INTO `com_dict_tree` VALUES ('ec677b163d7448f8a1b050829917f657', '30c39f10c07e4b3b905734277e527eee', 'com_sf_00', '否', '01', '00', '00', '1', '1484536554576', '01', '1', '2017-01-16 11:16:26', '1', '2017-01-16 11:18:34');
INSERT INTO `com_dict_tree` VALUES ('ee9a01bda113406cb6f8eed7c25c125d', '0', 'com_productModelEffective', '生效商品模型', '02', '00', 'com_productModelEffective', '0', '1489047624518', '01', '1', '2017-03-09 16:21:40', '1', '2017-03-09 16:50:00');
INSERT INTO `com_dict_tree` VALUES ('ef0dcfb7002543e8ac31a28f844c0f16', '689c9302878441e0b5639b8164d48b11', 'com_lbtType_03', '活动', '01', '00', '03', '1', '1489823464904', '01', '1', '2017-03-18 15:51:21', '1', '2017-03-18 15:51:21');
INSERT INTO `com_dict_tree` VALUES ('f0cde8fcc255428fae4cceb7f1a3abf1', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_bg', 'background', '01', '00', 'bg', '1', '1484018374289', '01', '1', '2017-01-10 11:19:34', '1', '2017-01-10 11:19:34');
INSERT INTO `com_dict_tree` VALUES ('f29b6b4266b54c67a3d9f629abc95560', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_com', 'common', '01', '00', 'com', '1', '1484017926047', '01', '1', '2017-01-10 11:12:06', '1', '2017-01-10 11:13:22');
INSERT INTO `com_dict_tree` VALUES ('f2b727ee2e654654bd91363660fe2197', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_03', '主从型（主）', '01', '00', '03', '1', '1484025903963', '01', '1', '2017-01-10 13:25:04', '1', '2017-01-10 13:25:04');
INSERT INTO `com_dict_tree` VALUES ('f413663c92f94f8dbae5825839a23cbd', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_view_limited', '跳转图文消息URL', '01', '00', 'view_limited', '1', '1486366219563', '01', '1', '2017-02-06 15:30:31', '1', '2017-02-06 15:30:31');
INSERT INTO `com_dict_tree` VALUES ('f4377b4baf504d5a98eedbf4cb65a46e', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_01', '系统菜单', '01', '00', '01', '1', '1484555346406', '01', '1', '2017-01-16 16:29:48', '1', '2017-01-16 16:29:48');
INSERT INTO `com_dict_tree` VALUES ('f4d48ab77e274cf4bccbc2c705bd9b13', '0', 'com_shopCarStatus', '购物车状态', '01', '00', 'com_shopCarStatus', '0', '1490233701294', '01', '1', '2017-03-23 09:49:01', '1', '2017-03-23 09:49:01');
INSERT INTO `com_dict_tree` VALUES ('f9831d6e9c3b482381bb940ffc5edf20', '0', 'bg_mapleType', '代码结构类型', '01', '00', 'bg_mapleType', '0', '1484025760602', '01', '1', '2017-01-10 13:22:41', '1', '2017-01-10 13:22:41');
INSERT INTO `com_dict_tree` VALUES ('f9fa8593bcee403bb28d8f5432ee2932', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_00', '待申请', '01', '00', '00', '1', '1488611118567', '01', '1', '2017-03-04 15:06:05', '1', '2017-03-04 15:06:05');
INSERT INTO `com_dict_tree` VALUES ('fc97312d70a84af186a000882941e5fc', '0', 'com_sparepartType', '零部件类型', '01', '00', 'com_sparepartType', '0', '1488529539958', '01', '1', '2017-03-03 16:25:59', '1', '2017-03-03 16:25:59');
INSERT INTO `com_dict_tree` VALUES ('fe507ec52dd74565b9b44138a30763bf', '30c39f10c07e4b3b905734277e527eee', 'com_sf_01', '是', '01', '00', '01', '1', '1484536535794', '01', '1', '2017-01-16 11:15:53', '1', '2017-01-16 11:18:28');

-- ----------------------------
-- Table structure for com_integral_note_info
-- ----------------------------
DROP TABLE IF EXISTS `com_integral_note_info`;
CREATE TABLE `com_integral_note_info` (
  `integralNoteId` varchar(100) NOT NULL COMMENT '积分记录 主键id',
  `integralNoteCode` varchar(100) DEFAULT NULL COMMENT '积分记录代号',
  `integralNoteName` varchar(100) DEFAULT NULL COMMENT '积分记录名称',
  `integralNoteType` varchar(100) DEFAULT NULL COMMENT '积分记录类型',
  `integralDealStatus` varchar(100) DEFAULT NULL COMMENT '积分交易状态',
  `appUserId` varchar(100) DEFAULT NULL COMMENT '平台用户',
  `integralDealCount` double(100,2) DEFAULT NULL COMMENT '积分交易数量',
  `integralCountBefore` double(100,2) DEFAULT NULL COMMENT '交易前积分数量',
  `integralCountAfter` double(100,2) DEFAULT NULL COMMENT '交易后积分数量',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`integralNoteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分记录';

-- ----------------------------
-- Records of com_integral_note_info
-- ----------------------------
INSERT INTO `com_integral_note_info` VALUES ('2800e071b7094d34a23f48b8b53e459c', '1170413135226125', '支付订单“1170413135226125”', '00', '00', 'f79d339325ba42ada9065e80e731574a', '96.00', null, null, '1492062753094', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:33', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:33');
INSERT INTO `com_integral_note_info` VALUES ('62f55ad5f91845778a48f0d7244e00fe', '1170413145530135', '支付订单“1170413145530135”', '00', '00', 'f79d339325ba42ada9065e80e731574a', '102.40', null, null, '1492066536371', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:36', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:36');

-- ----------------------------
-- Table structure for com_invite_info
-- ----------------------------
DROP TABLE IF EXISTS `com_invite_info`;
CREATE TABLE `com_invite_info` (
  `inviteId` varchar(100) NOT NULL COMMENT '邀请 主键id',
  `inviteCode` varchar(100) DEFAULT NULL COMMENT '邀请代号',
  `inviteName` varchar(100) DEFAULT NULL COMMENT '邀请名称',
  `inviteType` varchar(100) DEFAULT NULL COMMENT '邀请类型',
  `inviteStatus` varchar(100) DEFAULT NULL COMMENT '邀请状态',
  `inviteUserId` varchar(100) DEFAULT NULL COMMENT '邀请人id',
  `invitedUserId` varchar(100) DEFAULT NULL COMMENT '被邀请人id',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`inviteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请';

-- ----------------------------
-- Records of com_invite_info
-- ----------------------------

-- ----------------------------
-- Table structure for com_lbt_info
-- ----------------------------
DROP TABLE IF EXISTS `com_lbt_info`;
CREATE TABLE `com_lbt_info` (
  `lbtId` varchar(100) NOT NULL COMMENT '轮播图 主键id',
  `lbtCode` varchar(100) DEFAULT NULL COMMENT '轮播图代号',
  `lbtType` varchar(100) DEFAULT NULL COMMENT '轮播图类型',
  `lbtStatus` varchar(100) DEFAULT NULL COMMENT '轮播图状态',
  `lbtImgSrc` varchar(100) DEFAULT NULL COMMENT '轮播图图片',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`lbtId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='轮播图';

-- ----------------------------
-- Records of com_lbt_info
-- ----------------------------
INSERT INTO `com_lbt_info` VALUES ('2ef8177c8a4642878e287472c7b32f9f', 'b2', '00', '00', 'uploadFiles/image/lbt/lbtImg/72a7e0f1988c49b1b486962b95c0a0b2.jpg', 'safdsa', '1490686229522', '01', '1', '2017-03-28 15:30:40', '1', '2017-03-28 15:30:40');
INSERT INTO `com_lbt_info` VALUES ('7c51c24043a14983820d0b933b70b54d', 'b3', '01', '00', 'uploadFiles/image/lbt/lbtImg/8b64b27362fe45b8aac0e5340d11e2ce.jpg', 'sdf', '1490686262030', '01', '1', '2017-03-28 15:31:13', '1', '2017-03-28 15:31:13');
INSERT INTO `com_lbt_info` VALUES ('b5819a6f8e8f453abfa662edc00f6b74', 'b1', '00', '00', 'uploadFiles/image/lbt/lbtImg/b56b9ca9954f4b19b0074565b798e61c.jpg', 'sadas', '1490686198830', '01', '1', '2017-03-28 15:30:28', '1', '2017-03-28 15:30:28');
INSERT INTO `com_lbt_info` VALUES ('be6c089bb21241caae31c7da1627eca2', 'b4', '01', '00', 'uploadFiles/image/lbt/lbtImg/9fd797a5db5441759bc5ae94e990daca.jpg', 'fss', '1490686274338', '01', '1', '2017-03-28 15:31:22', '1', '2017-03-28 15:31:22');
INSERT INTO `com_lbt_info` VALUES ('ef84b355db1643859e184e03f99f61ba', 'b5', '01', '00', 'uploadFiles/image/lbt/lbtImg/fcf0360b8b3e43de899c2070d9028cd9.png', 'sdf', '1490686288426', '01', '1', '2017-03-28 15:31:39', '1', '2017-03-28 15:31:39');

-- ----------------------------
-- Table structure for com_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `com_order_detail`;
CREATE TABLE `com_order_detail` (
  `orderDetailId` varchar(100) NOT NULL COMMENT '订单商品 主键id',
  `orderId` varchar(100) NOT NULL COMMENT '订单商品 id',
  `productId` varchar(100) DEFAULT NULL COMMENT '商品Id',
  `productName` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `summary` varchar(100) DEFAULT NULL COMMENT '摘要',
  `productStyleId` varchar(100) DEFAULT NULL COMMENT '商品类型id',
  `productStyleName` varchar(100) DEFAULT NULL COMMENT '商品类型名称',
  `headImgSrc` varchar(100) DEFAULT NULL COMMENT '产品头像',
  `originalPrice` double(100,2) DEFAULT NULL COMMENT '原价',
  `currentPrice` double(100,2) DEFAULT NULL COMMENT '现价',
  `count` int(100) DEFAULT NULL COMMENT '购买数量',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`orderDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品';

-- ----------------------------
-- Records of com_order_detail
-- ----------------------------
INSERT INTO `com_order_detail` VALUES ('1461059c56444aefa72e5fb999f8381f', 'e754f5060da14a5da76c7ffe9da7010f', 'fcbcd3c8dc5440d6aa00754d83a005c8', '【蓝牙版】唇膏自拍杆', '以轻奢之名，宠爱自己 | 线控版迷你折叠自拍神器', '6d3bfff0f75f495f94979877a05473f8', '樱桃粉', 'uploadFiles/image/product/headImg/9900d3190d3a44bc97f747f003e9e2ac.jpg', '128.00', '102.40', '1', '1492067030732', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 15:03:51', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 15:03:51');
INSERT INTO `com_order_detail` VALUES ('76bd9262d1064636a9f0a033a64857ae', '200882ebe2344ef1a3882a110fa097a2', 'fcbcd3c8dc5440d6aa00754d83a005c8', '', '以轻奢之名，宠爱自己 | 线控版迷你折叠自拍神器', 'e852db025d9446efa90d69d2cbe036e4', '', 'uploadFiles/image/product/headImg/9900d3190d3a44bc97f747f003e9e2ac.jpg', '120.00', '96.00', '1', '1492062746098', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:26', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:26');
INSERT INTO `com_order_detail` VALUES ('84694813116d4ca2a6580c19c4ed6729', '736d16fd01b04185b440a2defe0ed3db', 'fcbcd3c8dc5440d6aa00754d83a005c8', '', '以轻奢之名，宠爱自己 | 线控版迷你折叠自拍神器', '6d3bfff0f75f495f94979877a05473f8', '', 'uploadFiles/image/product/headImg/9900d3190d3a44bc97f747f003e9e2ac.jpg', '128.00', '102.00', '1', '1492062041957', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:40:42', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:40:42');
INSERT INTO `com_order_detail` VALUES ('914f395580904d69960f3a82220a3883', '169a3ae9bfbe4c1dbee2837985bba3fe', 'fcbcd3c8dc5440d6aa00754d83a005c8', '', '以轻奢之名，宠爱自己 | 线控版迷你折叠自拍神器', '6d3bfff0f75f495f94979877a05473f8', '', 'uploadFiles/image/product/headImg/9900d3190d3a44bc97f747f003e9e2ac.jpg', '128.00', '102.00', '1', '1492066530388', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:30', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:30');
INSERT INTO `com_order_detail` VALUES ('b6860efb927344239848e325a12b2006', '6091a14748224fe1944c66ffe5b1c4f9', 'fcbcd3c8dc5440d6aa00754d83a005c8', '【蓝牙版】唇膏自拍杆', '以轻奢之名，宠爱自己 | 线控版迷你折叠自拍神器', '6d3bfff0f75f495f94979877a05473f8', '樱桃粉', 'uploadFiles/image/product/headImg/9900d3190d3a44bc97f747f003e9e2ac.jpg', '128.00', '102.00', '1', '1492066762580', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:59:23', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:59:23');

-- ----------------------------
-- Table structure for com_order_main
-- ----------------------------
DROP TABLE IF EXISTS `com_order_main`;
CREATE TABLE `com_order_main` (
  `orderId` varchar(100) NOT NULL COMMENT '订单 主键id',
  `orderCode` varchar(100) DEFAULT NULL COMMENT '订单代号',
  `orderName` varchar(100) DEFAULT NULL COMMENT '订单名称',
  `orderType` varchar(100) DEFAULT NULL COMMENT '订单类型',
  `orderStatus` varchar(100) DEFAULT NULL COMMENT '订单状态',
  `appUserId` varchar(100) DEFAULT NULL COMMENT '平台用户',
  `orderProductCount` int(100) DEFAULT NULL COMMENT '订单商品总数',
  `allPrice` double(100,2) DEFAULT NULL COMMENT '商品总价',
  `freight` double(100,2) DEFAULT NULL COMMENT '运费',
  `allDisPrice` double(100,2) DEFAULT NULL COMMENT '总优惠',
  `walletPay` double(100,0) DEFAULT NULL COMMENT '钱包支付',
  `allActPrice` double(100,2) DEFAULT NULL COMMENT '实付款',
  `supplierName` varchar(100) DEFAULT NULL COMMENT '供应商',
  `supplierId` varchar(100) DEFAULT NULL COMMENT '供应商id',
  `receiveAddressId` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `payTime` datetime DEFAULT NULL COMMENT '付款时间',
  `sendTime` datetime DEFAULT NULL COMMENT '发货时间',
  `tradeNum` varchar(100) DEFAULT NULL COMMENT '交易号',
  `payMethod` varchar(100) DEFAULT NULL COMMENT '付款方式',
  `wlgs` varchar(100) DEFAULT NULL COMMENT '物流公司',
  `wlNum` varchar(100) DEFAULT NULL COMMENT '运单编号',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of com_order_main
-- ----------------------------
INSERT INTO `com_order_main` VALUES ('169a3ae9bfbe4c1dbee2837985bba3fe', '1170413145530135', null, '01', '02', 'f79d339325ba42ada9065e80e731574a', '1', '128.00', null, '25.60', null, '102.40', '酷礼积分商城', 'd9c3ce8e48f94e65a04f447eff130ec0', '', null, null, null, null, null, null, '1492066530379', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:30', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:36');
INSERT INTO `com_order_main` VALUES ('200882ebe2344ef1a3882a110fa097a2', '1170413135226125', null, '01', '02', 'f79d339325ba42ada9065e80e731574a', '1', '120.00', '0.00', '24.00', null, '96.00', '酷礼积分商城', 'd9c3ce8e48f94e65a04f447eff130ec0', '', null, null, null, null, null, null, '1492062746091', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:26', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:33');
INSERT INTO `com_order_main` VALUES ('6091a14748224fe1944c66ffe5b1c4f9', '1170413145922135', null, '01', '01', 'f79d339325ba42ada9065e80e731574a', '1', '128.00', null, '25.60', null, '102.40', '酷礼积分商城', 'd9c3ce8e48f94e65a04f447eff130ec0', '', null, null, null, null, null, null, '1492066762508', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:59:23', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:59:23');
INSERT INTO `com_order_main` VALUES ('736d16fd01b04185b440a2defe0ed3db', '1170413134041138', null, '01', '01', 'f79d339325ba42ada9065e80e731574a', '1', '128.00', null, '25.60', null, '102.40', '酷礼积分商城', 'd9c3ce8e48f94e65a04f447eff130ec0', '', null, null, null, null, null, null, '1492062041928', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:40:42', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:40:42');
INSERT INTO `com_order_main` VALUES ('e754f5060da14a5da76c7ffe9da7010f', '1170413150350134', null, '01', '01', 'f79d339325ba42ada9065e80e731574a', '1', '128.00', null, '25.60', null, '102.40', '酷礼积分商城', 'd9c3ce8e48f94e65a04f447eff130ec0', '', null, null, null, null, null, null, '1492067030723', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 15:03:51', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 15:03:51');

-- ----------------------------
-- Table structure for com_product_category_detail
-- ----------------------------
DROP TABLE IF EXISTS `com_product_category_detail`;
CREATE TABLE `com_product_category_detail` (
  `productCategoryDetailId` varchar(100) NOT NULL COMMENT '商品分类详情 主键id',
  `productCategoryId` varchar(100) NOT NULL COMMENT '商品分类 id',
  `productId` varchar(100) DEFAULT NULL COMMENT '商品',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`productCategoryDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类详情';

-- ----------------------------
-- Records of com_product_category_detail
-- ----------------------------
INSERT INTO `com_product_category_detail` VALUES ('038e24c3173b487b9220083e8ae9f147', 'a918d9225ce54e7a9727eb01d1d30291', '064aad78d2c546f1a3c2050c104cb496', '1490688661479', '01', '1', '2017-03-28 16:11:04', '1', '2017-03-28 16:11:04');
INSERT INTO `com_product_category_detail` VALUES ('aa24138e3d4f41c699a58f6298ef886e', 'a918d9225ce54e7a9727eb01d1d30291', 'fcbcd3c8dc5440d6aa00754d83a005c8', '1490688664963', '01', '1', '2017-03-28 16:11:07', '1', '2017-03-28 16:11:07');

-- ----------------------------
-- Table structure for com_product_category_main
-- ----------------------------
DROP TABLE IF EXISTS `com_product_category_main`;
CREATE TABLE `com_product_category_main` (
  `productCategoryId` varchar(100) NOT NULL COMMENT '商品分类 主键id',
  `productCategoryCode` varchar(100) DEFAULT NULL COMMENT '商品分类代号',
  `productCategoryName` varchar(100) DEFAULT NULL COMMENT '商品分类名称',
  `productCategoryType` varchar(100) DEFAULT NULL COMMENT '商品分类类型',
  `productCategoryStatus` varchar(100) DEFAULT NULL COMMENT '商品分类状态',
  `headImgSrc` varchar(1000) DEFAULT NULL COMMENT '分类头像',
  `imgSrc1` varchar(1000) DEFAULT NULL COMMENT '长框图',
  `imgSrc2` varchar(1000) DEFAULT NULL COMMENT '滚播图',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`productCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- ----------------------------
-- Records of com_product_category_main
-- ----------------------------
INSERT INTO `com_product_category_main` VALUES ('04be05a235734f4681ef19836c8a9c53', 'spfl100189', '创意为礼', '01', '00', 'uploadFiles/image/productCategory/headImg/475e4020166444baa2bd7c328281733c.jpg', 'uploadFiles/image/productCategory/img1/e2b5f880093f49a9825747d221d07253.jpg', 'uploadFiles/image/productCategory/img2/71c7f279d3df4cea9e0f1f27758834e7.jpg_maple,uploadFiles/image/productCategory/img2/2a57ed193a424dc2aef07b823e79a8a2.jpg', '公司的发生的', '1490687491783', '01', '1', '2017-03-28 15:53:31', '1', '2017-03-28 15:53:31');
INSERT INTO `com_product_category_main` VALUES ('131d5b40db5b4edfa37eae450a7cc62e', 'spfl100091', '文艺范', '02', '00', 'uploadFiles/image/productCategory/headImg/6a2d9323fcaa49f190514a314aab6145.jpg', 'uploadFiles/image/productCategory/img1/c45f8bef36664fc4bd74b91e33af38c9.jpg', 'uploadFiles/image/productCategory/img2/27ede8b268e14267b0f82a98a61cd069.jpg', '文艺是一种日常。寻找小而美的事物，发掘每件平凡商品里不平凡的一面。不文艺无生活。', '1490088912534', '01', '1', '2017-03-21 17:35:42', '1', '2017-03-21 20:25:10');
INSERT INTO `com_product_category_main` VALUES ('6cbaee12013e40edafa9f2bc588821ab', 'spfl100121', '送伙伴', '02', '00', 'uploadFiles/image/productCategory/headImg/38ee9940a5cd4ec3aea276b6e3053e95.jpg', 'uploadFiles/image/productCategory/img1/6ace3dc217d1416f88a81f211489ecf9.jpg', 'uploadFiles/image/productCategory/img2/ec364d13ec3f4485a51045fc0b2eb42f.jpg_maple,uploadFiles/image/productCategory/img2/657b21f159464533829f9b9ca4568451.jpg_maple,uploadFiles/image/productCategory/img2/a4319bc55cbe49088680dbbf436711ef.jpg_maple,uploadFiles/image/productCategory/img2/194cf8c48d2649c4b488b991ef297dd0.jpg', '大批量送礼采购太伤脑？买贵了伤钱，买少了伤情？高档精致礼物上哪找？我们帮您既挣了面子，又省了钱。', '1490099502185', '01', '1', '2017-03-21 20:32:15', '1', '2017-03-21 20:32:15');
INSERT INTO `com_product_category_main` VALUES ('6dcf1f555496485eb9504a50cc45c723', 'spfl100103', '小盆友', '02', '00', 'uploadFiles/image/productCategory/headImg/eb1d2a69220142d999962c3e0d3ac2f5.jpg', 'uploadFiles/image/productCategory/img1/7eeb696e64d341fa9065098df5042ae6.jpg', 'uploadFiles/image/productCategory/img2/b3d07fb2edef4b78a0d6a0629f608e9b.jpg', '送一份心意，更是一份健康快乐。我怎么就到了给长辈，给晚辈送礼的年纪了呢？明明我还是个宝宝啊~', '1490099043947', '01', '1', '2017-03-21 20:25:01', '1', '2017-03-21 20:25:01');
INSERT INTO `com_product_category_main` VALUES ('8ebf90e405c841348b2831aaaa9ede57', 'spfl100044', '献孝心', '02', '00', 'uploadFiles/image/productCategory/headImg/6c0b14808453414cafc3d3d71a4671f8.jpg', 'uploadFiles/image/productCategory/img1/603fe81147f54adda45f5bbec9f0304d.jpg', 'uploadFiles/image/productCategory/img2/6c61f71238924efd809ed9d9ee13fff6.jpg_maple,uploadFiles/image/productCategory/img2/074b1092e6b541e289aecef83cbb0b68.jpg', '“雕蚶镂蛤”，是不是不知道什么意思，甚至连字都不认识？像你这样，我们吃货界是不会接纳你的~还不赶紧去买零食，刷绩点！“百善孝为先”，不单单是在节日里，平凡的日子里更要好好照顾长辈。想孝敬的心，永远不会', '1490087026286', '01', '1', '2017-03-21 17:05:34', '1', '2017-03-21 20:25:41');
INSERT INTO `com_product_category_main` VALUES ('a0b51e1f07f4486c9806237b1b81a438', 'spfl100011', '送女友', '02', '00', '', '', '', '你买或者不买，我就在这里，不悲不喜。但我知道，要是你再不买就杯具了，毕竟看中的女孩随时可能被别人追走呀', '1490086792367', '01', '1', '2017-03-21 17:02:17', '1', '2017-03-21 17:09:39');
INSERT INTO `com_product_category_main` VALUES ('a918d9225ce54e7a9727eb01d1d30291', 'spfl100172', '推荐', '03', '00', 'uploadFiles/image/productCategory/headImg/94752b61bbe74b3083b82077843d594b.jpg', 'uploadFiles/image/productCategory/img1/2b550839805a4636b966cae70582a568.jpg', 'uploadFiles/image/productCategory/img2/5f58c72d37464813b03d2b69681c77af.jpg_maple,uploadFiles/image/productCategory/img2/0ccf2b0428724144baf48193d8deb89b.jpg_maple,uploadFiles/image/productCategory/img2/6b8da3c84c994951b4f849871f6d70b9.jpg', '个梵蒂冈地方', '1490161457061', '01', '1', '2017-03-22 13:44:50', '1', '2017-03-28 16:10:57');
INSERT INTO `com_product_category_main` VALUES ('ad5c526445cb47de94beb44f6b4d024f', 'spfl100072', '纪念日', '02', '00', 'uploadFiles/image/productCategory/headImg/65be4935c5ae4b9dad690163c18c66c4.jpg', 'uploadFiles/image/productCategory/img1/51fe425b557e46679399a6f2321f7912.jpg', 'uploadFiles/image/productCategory/img2/b9e1f2dd526a47e99883276cca36fe20.jpg_maple,uploadFiles/image/productCategory/img2/39bfd41af0b54d439330779f0ca47e69.jpg_maple,uploadFiles/image/productCategory/img2/8402152bb2254859b09d9ccddf2227a6.jpg', '一年365天，把每一天过成纪念日。纪念每一次微小感动，纪念每一个小小成功，为了更好的自己。', '1490088885486', '01', '1', '2017-03-21 17:35:08', '1', '2017-03-21 20:25:15');
INSERT INTO `com_product_category_main` VALUES ('b646cf8803af431aa74d3f2eb5599015', 'spfl100133', '私人定制', '02', '00', 'uploadFiles/image/productCategory/headImg/d74eabfae6054e7f8f3a6f0b8d1bc9be.jpg', 'uploadFiles/image/productCategory/img1/391632fa6c694d79b9f2711793a53ad8.jpg', 'uploadFiles/image/productCategory/img2/f83f6c2f84d34e3b85e85ea044c6c4d3.jpg', '比任何人都了解你的。我们之间的秘密，我绝口不提。别人如何爱你，我也不去过问。只为你送上最贴心的关怀。', '1490099727206', '01', '1', '2017-03-21 20:35:53', '1', '2017-03-21 20:35:53');
INSERT INTO `com_product_category_main` VALUES ('ba7bbbb7603442ffb802c37ce1e0f9e1', 'spfl100057', '生日趴', '02', '00', 'uploadFiles/image/productCategory/headImg/ae36b16047e8437ab2d8d532e5043d94.jpg', 'uploadFiles/image/productCategory/img1/a6e5c1cc7e1349a3b92e62ff48406344.jpg', 'uploadFiles/image/productCategory/img2/7bdcf664977b4f79b5cf548f29ec5b86.jpg_maple,uploadFiles/image/productCategory/img2/3e062e19a69641949b47de6e7f590eeb.jpg_maple,uploadFiles/image/productCategory/img2/f8fb1e1879b741819d539dd719693c13.jpg', '你买或者不买，我就在这里，不悲不喜。但我知道，要是不买你就悲了，毕竟看中的女孩随时可能被别人追走呀~恋人、父母、闺蜜、基友……谢谢你们的出现，让我的生活如此丰富多彩。一个人的生日一群人的狂欢。', '1490087190265', '01', '1', '2017-03-21 17:07:33', '1', '2017-03-21 20:25:27');
INSERT INTO `com_product_category_main` VALUES ('c666aded896f4005a685203a59ddd920', 'spfl100145', '水果生鲜', '01', '00', 'uploadFiles/image/productCategory/headImg/60ba0a140c7548f19d5c127bfccd5eef.jpg', 'uploadFiles/image/productCategory/img1/1689ba69fa584286b8663056d25db711.jpg', 'uploadFiles/image/productCategory/img2/03732690686d44ceb2997af4a956d27c.jpg', '时光被奇景惊艳，而岁月在城市里被温柔。每一个城市写给你最动人的情书，就是它的食物。平淡的生活中，食物一次又一次抚慰你疲惫的躯体和心灵。', '1490099767153', '01', '1', '2017-03-21 20:36:47', '1', '2017-03-21 20:36:47');
INSERT INTO `com_product_category_main` VALUES ('d191ce9ed1b549d29951a6067dcaae7e', 'spfl100033', '送男友', '02', '00', 'uploadFiles/image/productCategory/headImg/821c4506baef49848528b6035bb051e7.jpg', 'uploadFiles/image/productCategory/img1/e6be12f5308b4e3e885211aeb3381760.jpg', 'uploadFiles/image/productCategory/img2/35579d59fd084762932e95e71e34efcc.jpg_maple,uploadFiles/image/productCategory/img2/9d39934d184845a2b5839f9f3620c74e.jpg_maple,uploadFiles/image/productCategory/img2/8d0cc2c2a3674e78ab3d6a65776a7bf3.jpg', '时光被奇景惊艳，而岁月在城市里被温柔。每一个城市写给你最动人的情书，就是它的食物。平淡的生活中，食物一次又一次抚慰你疲惫的躯体和心灵。男追女隔层纸，女追男隔座山”，我们愿做“愚公”帮您移山，追到男神也', '1490086943925', '01', '1', '2017-03-21 17:03:22', '1', '2017-03-21 20:25:50');
INSERT INTO `com_product_category_main` VALUES ('d67b615c645f4967baa1189f6c829d5b', 'spfl100156', '零食当道', '01', '00', 'uploadFiles/image/productCategory/headImg/b9b071c9b9de4f5489a9484e8fb4b11a.jpg', 'uploadFiles/image/productCategory/img1/32336528f2cd403bbbc62f4a0f35905b.jpg', 'uploadFiles/image/productCategory/img2/b78b7bd281e24f88b1e6f73fcd16e7e1.jpg', '“雕蚶镂蛤”，是不是不知道什么意思，甚至连字都不认识？像你这样，我们吃货界是不会接纳你的~还不赶紧去买零食，刷绩点！', '1490099811784', '01', '1', '2017-03-21 20:37:23', '1', '2017-03-21 20:37:23');

-- ----------------------------
-- Table structure for com_product_info
-- ----------------------------
DROP TABLE IF EXISTS `com_product_info`;
CREATE TABLE `com_product_info` (
  `productId` varchar(100) NOT NULL COMMENT '产品 主键id',
  `supplierId` varchar(100) DEFAULT NULL COMMENT '供应商Id',
  `productCode` varchar(100) DEFAULT NULL COMMENT '产品代号',
  `productName` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `productType` varchar(100) DEFAULT NULL COMMENT '产品类型',
  `productStatus` varchar(100) DEFAULT NULL COMMENT '产品状态',
  `productModel` varchar(100) DEFAULT NULL COMMENT '产品模型',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `introduction` varchar(1000) DEFAULT NULL COMMENT '简介',
  `headImgSrc` varchar(1000) DEFAULT NULL COMMENT '产品头像',
  `imgSrc1` varchar(1000) DEFAULT NULL COMMENT '长框图',
  `imgSrc2` varchar(1000) DEFAULT NULL COMMENT '滚播图',
  `imgSrc3` varchar(1000) DEFAULT NULL COMMENT '详情图',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Records of com_product_info
-- ----------------------------
INSERT INTO `com_product_info` VALUES ('064aad78d2c546f1a3c2050c104cb496', 'd9c3ce8e48f94e65a04f447eff130ec0', 'sp100029', 'BOW航世 折叠蓝牙键盘', '04be05a235734f4681ef19836c8a9c53', '01', '6cbaee12013e40edafa9f2bc588821ab', '每个夜晚，我都守护你左右。', '触与手，暖于心 | 芒果充电暖手宝', 'uploadFiles/image/product/headImg/930ce9dfa1894b5a94db14eadd51d8a7.jpg', 'uploadFiles/image/product/img1/c9b78d06d2a34c98bd279c67ed4a5069.jpg', 'uploadFiles/image/product/img2/11b8c4b0b79149de89826394850ab17b.jpg_maple,uploadFiles/image/product/img2/18a35b4ca0f0453c95a4551b00643506.jpg_maple,uploadFiles/image/product/img2/4c9e9b661e7249cd854162f22badb98c.jpg_maple,uploadFiles/image/product/img2/45c3bf857a2f4f6c9115fb1cfc223bc0.jpg', 'uploadFiles/image/product/img3/985a0f9941f84a81a174d9c532c995ac.jpg_maple,uploadFiles/image/product/img3/b086f037f1fe421f86c242310931ef10.jpg_maple,uploadFiles/image/product/img3/6350cf7d46844682a9cdeb481d585b46.jpg_maple,uploadFiles/image/product/img3/a854c3391b4640c387c3c5e0974637a1.jpg', '1490688289429', '01', '1', '2017-03-28 16:06:45', '1', '2017-03-28 16:06:45');
INSERT INTO `com_product_info` VALUES ('fcbcd3c8dc5440d6aa00754d83a005c8', 'd9c3ce8e48f94e65a04f447eff130ec0', 'sp100017', '【蓝牙版】唇膏自拍杆', '04be05a235734f4681ef19836c8a9c53', '01', '131d5b40db5b4edfa37eae450a7cc62e', '以轻奢之名，宠爱自己 | 线控版迷你折叠自拍神器', '奋斗的身份是的发送到发送到', 'uploadFiles/image/product/headImg/9900d3190d3a44bc97f747f003e9e2ac.jpg', 'uploadFiles/image/product/img1/eb0430329e53406b9f73cc4a89056210.jpg', 'uploadFiles/image/product/img2/8313f4cfe6ea436886717ea2862d5e74.jpg_maple,uploadFiles/image/product/img2/858cdffedb56431bb062c48df145ad0d.jpg_maple,uploadFiles/image/product/img2/736c2dfa86a445d7a12ae1d5263c7d81.jpg_maple,uploadFiles/image/product/img2/e7a811097dfd4f8daef4aee8a1cf76bd.jpg', 'uploadFiles/image/product/img3/933ac20c636941de8de2774d056c1165.jpg_maple,uploadFiles/image/product/img3/08cc95ba243b45118d97a08b56981958.jpg_maple,uploadFiles/image/product/img3/6c2db70069054d7d8e20c19ab231a652.jpg_maple,uploadFiles/image/product/img3/7d94aa348aec4a239c6492d4dfc9cff6.jpg', '1490687619033', '01', '1', '2017-03-28 15:59:39', '1', '2017-03-28 15:59:39');

-- ----------------------------
-- Table structure for com_product_style_info
-- ----------------------------
DROP TABLE IF EXISTS `com_product_style_info`;
CREATE TABLE `com_product_style_info` (
  `productStyleId` varchar(100) NOT NULL COMMENT '商品规格 主键id',
  `productId` varchar(100) DEFAULT NULL COMMENT '商品id',
  `productStyleCode` varchar(100) DEFAULT NULL COMMENT '商品规格代号',
  `productStyleName` varchar(100) DEFAULT NULL COMMENT '商品规格名称',
  `productStyleType` varchar(1000) DEFAULT NULL COMMENT '商品规格类型',
  `productStyleStatus` varchar(100) DEFAULT NULL COMMENT '商品规格状态',
  `allStockNum` int(100) DEFAULT NULL COMMENT '库存总量',
  `stockNum` int(100) DEFAULT NULL COMMENT '库存数量',
  `originalPrice` double(100,2) DEFAULT NULL COMMENT '原价',
  `curType` varchar(100) DEFAULT NULL COMMENT '货币种类',
  `discountRate` double(100,2) DEFAULT NULL COMMENT '折扣率',
  `discountPrice` double(100,2) DEFAULT NULL COMMENT '折扣优惠',
  `currentPrice` double(100,2) DEFAULT NULL COMMENT '现价',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`productStyleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格';

-- ----------------------------
-- Records of com_product_style_info
-- ----------------------------
INSERT INTO `com_product_style_info` VALUES ('1c865bb3cf5f4fb9a30c90022cc5acb1', '064aad78d2c546f1a3c2050c104cb496', 'spgg100062', '白色', '904952a8184145a49348788ceb234dc0', '01', '30', '30', '430.00', '01', '0.70', '129.00', '301.00', '1490688546654', '01', '1', '2017-03-28 16:09:26', '1', '2017-03-28 16:09:26');
INSERT INTO `com_product_style_info` VALUES ('6d3bfff0f75f495f94979877a05473f8', 'fcbcd3c8dc5440d6aa00754d83a005c8', 'spgg100033', '樱桃粉', '600fcb2d7d7f43a18aaa628784c028f3', '01', '100', '98', '128.00', '01', '0.80', '25.60', '102.40', '1490688251249', '01', '1', '2017-03-28 16:04:36', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 14:55:36');
INSERT INTO `com_product_style_info` VALUES ('88bdd55f5cd2449799aa4815a6eab36a', '064aad78d2c546f1a3c2050c104cb496', 'spgg100046', '黑色', '057088b6891341f780343fe3732a7abc', '01', '500', '500', '320.00', '01', '0.70', '96.00', '224.00', '1490688518283', '01', '1', '2017-03-28 16:09:03', '1', '2017-03-28 16:09:03');
INSERT INTO `com_product_style_info` VALUES ('c296792a87e54895b4ef77249975d1f5', '064aad78d2c546f1a3c2050c104cb496', 'spgg100081', '金色', 'fd48bec71b064076baa12b90b0eeba98', '01', '600', '600', '460.00', '01', '0.70', '138.00', '322.00', '1490688588103', '01', '1', '2017-03-28 16:10:08', '1', '2017-03-28 16:10:08');
INSERT INTO `com_product_style_info` VALUES ('e852db025d9446efa90d69d2cbe036e4', 'fcbcd3c8dc5440d6aa00754d83a005c8', 'spgg100018', '月光白', '077529a3c68a43d5a0ac5dc19ad7ad6e', '01', '2000', '1996', '120.00', '01', '0.80', '24.00', '96.00', '1490688060493', '01', '1', '2017-03-28 16:02:00', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:33');

-- ----------------------------
-- Table structure for com_receive_address_info
-- ----------------------------
DROP TABLE IF EXISTS `com_receive_address_info`;
CREATE TABLE `com_receive_address_info` (
  `receiveAddressId` varchar(100) NOT NULL COMMENT '收货地址 主键id',
  `appUserId` varchar(100) DEFAULT NULL COMMENT '平台用户',
  `receicerName` varchar(100) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `street` varchar(100) DEFAULT NULL COMMENT '街道',
  `detail` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `receiveAddressStatus` varchar(100) DEFAULT NULL COMMENT '收货地址状态',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`receiveAddressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址';

-- ----------------------------
-- Records of com_receive_address_info
-- ----------------------------

-- ----------------------------
-- Table structure for com_shop_car_info
-- ----------------------------
DROP TABLE IF EXISTS `com_shop_car_info`;
CREATE TABLE `com_shop_car_info` (
  `shopCarId` varchar(100) NOT NULL COMMENT '购物车 主键id',
  `appUserId` varchar(100) DEFAULT NULL COMMENT '用户',
  `productStyleId` varchar(100) DEFAULT NULL COMMENT '商品规格',
  `count` int(100) DEFAULT NULL COMMENT '数量',
  `shopCarStatus` varchar(100) DEFAULT NULL COMMENT '购物车状态',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`shopCarId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Records of com_shop_car_info
-- ----------------------------
INSERT INTO `com_shop_car_info` VALUES ('380f051e58eb4d0eba95fde1ca68028e', 'f79d339325ba42ada9065e80e731574a', '6d3bfff0f75f495f94979877a05473f8', '1', '01', '1492061013009', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:23:33', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:23:33');
INSERT INTO `com_shop_car_info` VALUES ('f9c88f1e37184a09a7bcf4c089c34b5d', 'f79d339325ba42ada9065e80e731574a', 'e852db025d9446efa90d69d2cbe036e4', '1', '02', '1492061768349', '01', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:36:08', 'f79d339325ba42ada9065e80e731574a', '2017-04-13 13:52:26');

-- ----------------------------
-- Table structure for com_sparepart_deal_info
-- ----------------------------
DROP TABLE IF EXISTS `com_sparepart_deal_info`;
CREATE TABLE `com_sparepart_deal_info` (
  `sparepartDealId` varchar(100) NOT NULL COMMENT '零部件交易 主键id',
  `sparepartDealCode` varchar(100) DEFAULT NULL COMMENT '零部件交易订单号',
  `sparepartDealStatus` varchar(100) DEFAULT NULL COMMENT '零部件交易审核状态',
  `appUserId` varchar(100) DEFAULT NULL COMMENT '零部件销售客户',
  `sparepartId` varchar(100) DEFAULT NULL COMMENT '零部件',
  `count` int(100) DEFAULT NULL COMMENT '出售数量',
  `dealAmt` double(100,2) DEFAULT NULL COMMENT '交易金额',
  `orderTime` datetime DEFAULT NULL COMMENT '订单日期',
  `checkId` varchar(100) DEFAULT NULL COMMENT '审核人',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '审核结果',
  `checkTime` datetime DEFAULT NULL COMMENT '审核日期',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`sparepartDealId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='零部件交易';

-- ----------------------------
-- Records of com_sparepart_deal_info
-- ----------------------------

-- ----------------------------
-- Table structure for com_sparepart_info
-- ----------------------------
DROP TABLE IF EXISTS `com_sparepart_info`;
CREATE TABLE `com_sparepart_info` (
  `sparepartId` varchar(100) NOT NULL COMMENT '零配件 主键id',
  `sparepartCode` varchar(100) DEFAULT NULL COMMENT '零配件代号',
  `sparepartName` varchar(100) DEFAULT NULL COMMENT '零配件名称',
  `sparepartType` varchar(100) DEFAULT NULL COMMENT '零配件类型',
  `sparepartStatus` varchar(100) DEFAULT NULL COMMENT '零配件状态',
  `allIntegral` double(100,2) DEFAULT NULL COMMENT '总积分',
  `integral1` double(100,2) DEFAULT NULL COMMENT '大区经理可获积分',
  `integral2` double(100,2) DEFAULT NULL COMMENT '小区经理可获积分',
  `integral3` double(100,2) DEFAULT NULL COMMENT '4S店可获积分',
  `integral4` double(100,2) DEFAULT NULL COMMENT '销售员可获积分',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`sparepartId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='零配件';

-- ----------------------------
-- Records of com_sparepart_info
-- ----------------------------

-- ----------------------------
-- Table structure for com_style_category_tree
-- ----------------------------
DROP TABLE IF EXISTS `com_style_category_tree`;
CREATE TABLE `com_style_category_tree` (
  `styleCategoryId` varchar(100) NOT NULL COMMENT '规格分类 主键id',
  `parentId` varchar(100) NOT NULL COMMENT '上级 id',
  `productId` varchar(100) DEFAULT NULL COMMENT '产品编号',
  `styleCategoryName` varchar(100) DEFAULT NULL COMMENT '规格分类名称',
  `isFinal` varchar(100) DEFAULT NULL COMMENT '是否最终分类',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`styleCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格分类';

-- ----------------------------
-- Records of com_style_category_tree
-- ----------------------------
INSERT INTO `com_style_category_tree` VALUES ('057088b6891341f780343fe3732a7abc', 'a9e6b001365b4d89a38b734496fb53f6', '064aad78d2c546f1a3c2050c104cb496', '黑色', '01', '1490688497517', '01', '1', '2017-03-28 16:08:25', '1', '2017-03-28 16:08:25');
INSERT INTO `com_style_category_tree` VALUES ('077529a3c68a43d5a0ac5dc19ad7ad6e', '78fefdccb947422cbcad3403a543384c', 'fcbcd3c8dc5440d6aa00754d83a005c8', '月光白', '01', '1490688036095', '01', '1', '2017-03-28 16:00:39', '1', '2017-03-28 16:00:39');
INSERT INTO `com_style_category_tree` VALUES ('600fcb2d7d7f43a18aaa628784c028f3', '78fefdccb947422cbcad3403a543384c', 'fcbcd3c8dc5440d6aa00754d83a005c8', '樱桃粉', '01', '1490687995996', '01', '1', '2017-03-28 16:00:24', '1', '2017-03-28 16:00:24');
INSERT INTO `com_style_category_tree` VALUES ('78fefdccb947422cbcad3403a543384c', '0', 'fcbcd3c8dc5440d6aa00754d83a005c8', '颜色', '00', '1490687987637', '01', '1', '2017-03-28 15:59:53', '1', '2017-03-28 15:59:53');
INSERT INTO `com_style_category_tree` VALUES ('904952a8184145a49348788ceb234dc0', 'a9e6b001365b4d89a38b734496fb53f6', '064aad78d2c546f1a3c2050c104cb496', '白色', '01', '1490688472317', '01', '1', '2017-03-28 16:08:02', '1', '2017-03-28 16:08:17');
INSERT INTO `com_style_category_tree` VALUES ('a9e6b001365b4d89a38b734496fb53f6', '0', '064aad78d2c546f1a3c2050c104cb496', '颜色', '00', '1490688416868', '01', '1', '2017-03-28 16:07:49', '1', '2017-03-28 16:07:49');
INSERT INTO `com_style_category_tree` VALUES ('fd48bec71b064076baa12b90b0eeba98', 'a9e6b001365b4d89a38b734496fb53f6', '064aad78d2c546f1a3c2050c104cb496', '金色', '01', '1490688483015', '01', '1', '2017-03-28 16:08:12', '1', '2017-03-28 16:08:12');
INSERT INTO `com_style_category_tree` VALUES ('ff7ba76c7c2049138f9d0ee831318621', '78fefdccb947422cbcad3403a543384c', 'fcbcd3c8dc5440d6aa00754d83a005c8', '水晶紫', '01', '1490688040505', '01', '1', '2017-03-28 16:00:49', '1', '2017-03-28 16:00:49');

-- ----------------------------
-- Table structure for com_supplier_info
-- ----------------------------
DROP TABLE IF EXISTS `com_supplier_info`;
CREATE TABLE `com_supplier_info` (
  `supplierId` varchar(100) NOT NULL COMMENT '供应商 主键id',
  `supplierCode` varchar(100) DEFAULT NULL COMMENT '供应商代号',
  `supplierName` varchar(100) DEFAULT NULL COMMENT '供应商名称',
  `supplierType` varchar(100) DEFAULT NULL COMMENT '供应商类型',
  `supplierStatus` varchar(100) DEFAULT NULL COMMENT '供应商状态',
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `linkPhone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '备注',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商';

-- ----------------------------
-- Records of com_supplier_info
-- ----------------------------
INSERT INTO `com_supplier_info` VALUES ('d9c3ce8e48f94e65a04f447eff130ec0', 'gys100013', '酷礼积分商城', '01', '00', '寇鑫', '18260625432', '老板', '1490685484340', '01', '1', '2017-03-28 15:18:47', '1', '2017-03-28 15:18:47');

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
INSERT INTO `com_wx_account_info` VALUES ('9828b1377557429c8cb449440291e042', 'giftOL', '在线送礼', '01', '01', 'wxf3a522044df3f3df', '2028ab8d1cdb5c5e918bfb2c3f4c47f5', 'jiangxiang', '1423462402', '3034adc8e3218f3f3be7bb27590c90ed', 'FC_RGXS5EvdDCay7wgDrKzMznPQm1vXIv5JQp3zCqDXQHIRypxYkssRoZS0jwLgY-NbcALcpbGofVysnfEk5-s4mE8URfdahDcvBC7zyKBABTCjACAJBK', 'kgt8ON7yVITDhtdwci0qeRNuinq5m4Y437sKzdeBHjd750HUGHh8jDvtl7xJIVhw76uy3kEXW6EONoNhlbiA7w', '1484896943192', '01', '1', '2017-01-20 15:26:49', 'wx', '2017-04-13 16:13:00');
