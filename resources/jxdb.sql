/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jxdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-10 17:30:46
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
INSERT INTO `bg_maple_detail` VALUES ('09022fb4f49c4c39a1b0ac365ad5f051', '2b8a44269b4542e48e13549c8e2e14a4', 'originalPrice', '原价', '04', '00', '100', '2', '', '00', '01', '01', '', '1487905809816', '01', '1', '2017-02-24 11:10:45', '1', '2017-02-24 11:11:12');
INSERT INTO `bg_maple_detail` VALUES ('09e80d18610a440fa531f7acd1267e40', 'a4dc9b54bb3e4d17ad983912730a6667', 'productCode', '产品代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902142331', '01', '1', '2017-02-24 10:09:02', '1', '2017-02-24 10:09:02');
INSERT INTO `bg_maple_detail` VALUES ('0cd951c34ead4d23b43a2924903453b9', '307d768ee19e45b09b016970caf257ca', 'sparepartType', '零配件类型', '05', '00', '100', '0', 'com_sparepartType', '00', '01', '01', '\"01\"', '1488528018794', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_detail` VALUES ('111031d1ebdc4ac5a8028ac9c286e08e', '67163a4e05664fa39d5bea61b445163b', 'roleStatus', '后台角色状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484794097652', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('11bfec3c80e54267aea3193e4183fb97', 'd25535c48c444f9fb675294c8a2d8e1f', 'value', '扩展属性值', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533756969', '01', '1', '2017-03-03 17:36:20', '1', '2017-03-03 17:36:20');
INSERT INTO `bg_maple_detail` VALUES ('1260a70eaaec4fa1b9e2c905e43e117f', 'e43dbde47f8e4b2fab9d226b9ce3976b', 'styleCategoryDetailStatus', '规格分类详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487913761140', '01', '1', '2017-02-24 13:22:41', '1', '2017-02-24 13:22:41');
INSERT INTO `bg_maple_detail` VALUES ('1379e9109d224ce784e05df3241b8329', '36ff1b75838f43efac8625df493e0df1', 'mchId', '微信支付商户号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882711370', '01', '1', '2017-01-20 11:33:53', '1', '2017-01-20 11:33:53');
INSERT INTO `bg_maple_detail` VALUES ('161adf42874147d78e00d72a997783e9', 'ebd0083161064722a7c37d25a0030434', 'menuTag', '菜单数字标记', '02', '00', '10', '0', '', '00', '01', '01', '', '1484546691846', '01', '1', '2017-01-16 14:07:03', '1', '2017-01-16 14:07:03');
INSERT INTO `bg_maple_detail` VALUES ('178069562df14ca8860d0cea3ae5e22d', '754773752b53495f9d452fbc9b06c37a', 'remarks', '备注', '01', '00', '1000', '0', '', '00', '01', '01', '', '1487901522779', '01', '1', '2017-02-24 09:58:12', '1', '2017-02-24 10:05:47');
INSERT INTO `bg_maple_detail` VALUES ('18d52145e1d641f9a12e78c196770377', '0d2f5dbae9154a4bb3788bbfc8b61176', 'summary', '摘要', '01', '00', '100', '0', '', '00', '01', '01', '', '1487916371260', '01', '1', '2017-02-24 14:06:23', '1', '2017-02-24 14:06:23');
INSERT INTO `bg_maple_detail` VALUES ('19d9cc3f61c542f4b5f60b2c9633ba0d', '742baf93ab0f4399a98c18d7adbe4622', 'media_id', '永久素材id', '01', '00', '100', '0', '', '00', '01', '01', '', '1486350230757', '01', '1', '2017-02-06 11:05:15', '1', '2017-02-06 11:05:15');
INSERT INTO `bg_maple_detail` VALUES ('1a826d69ea0d4122b31eddb0af8cea04', '3b04706d4ab8494faa53e05e822a6e82', 'jobClass', '任务', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983679664', '01', '1', '2017-01-21 15:28:20', '1', '2017-01-21 15:28:20');
INSERT INTO `bg_maple_detail` VALUES ('1b926da1358b48199cbb5cba490e5d04', '3b3eb93879584c78a5ae5ea461dbc022', 'remarks', '审核结果', '01', '00', '1000', '0', '', '00', '00', '01', '', '1488532060684', '01', '1', '2017-03-03 17:08:04', '1', '2017-03-04 13:38:53');
INSERT INTO `bg_maple_detail` VALUES ('1ce4dd8daffe4d30a03cc2d5080bc98e', '1ba234a840324c2faa9fc56e9ec9e144', 'wxQRcodeUrl', '微信二维码地址', '01', '00', '100', '0', '', '00', '01', '01', '', '1486778974209', '01', '1', '2017-02-11 10:10:45', '1', '2017-02-11 10:10:45');
INSERT INTO `bg_maple_detail` VALUES ('1f7192973fff4fa89e6b19e68b99309c', 'a4dc9b54bb3e4d17ad983912730a6667', 'productStatus', '产品状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487902142334', '01', '1', '2017-02-24 10:09:02', '1', '2017-02-24 10:09:02');
INSERT INTO `bg_maple_detail` VALUES ('20863638be714a019fa891045325e8b8', '0d2f5dbae9154a4bb3788bbfc8b61176', 'imgSrc2', '滚播图', '01', '00', '100', '0', '', '00', '01', '01', '', '1487916225225', '01', '1', '2017-02-24 14:04:01', '1', '2017-02-24 14:04:01');
INSERT INTO `bg_maple_detail` VALUES ('20e9659ee4854df5aac96d8e669ec663', '0d2f5dbae9154a4bb3788bbfc8b61176', 'imgSrc1', '长框图', '01', '00', '100', '0', '', '00', '01', '01', '', '1487916205076', '01', '1', '2017-02-24 14:03:43', '1', '2017-02-24 14:03:43');
INSERT INTO `bg_maple_detail` VALUES ('2155fd11584c4a78aea98a18e54a6414', 'a4dc9b54bb3e4d17ad983912730a6667', 'imgSrc3', '详情图', '01', '00', '1000', '0', '', '00', '01', '01', '', '1487905038002', '01', '1', '2017-02-24 10:57:43', '1', '2017-02-24 10:57:43');
INSERT INTO `bg_maple_detail` VALUES ('2223fbb1f43f4bb4a7cd687cf7e8f4b4', 'a4dc9b54bb3e4d17ad983912730a6667', 'supplierId', '供应商Id', '05', '00', '100', '0', '', '00', '01', '01', '', '1487902142330', '01', '1', '2017-02-24 15:05:01', '1', '2017-02-24 15:05:35');
INSERT INTO `bg_maple_detail` VALUES ('2252b3cc015b4397b80193f919e15b89', 'ebd0083161064722a7c37d25a0030434', 'menuIcon', '菜单图标', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546889624', '01', '1', '2017-01-16 14:08:42', '1', '2017-01-16 14:08:42');
INSERT INTO `bg_maple_detail` VALUES ('22d65fec87014577ace005d6e47d562a', '3b04706d4ab8494faa53e05e822a6e82', 'timeExp', '时间表达式', '01', '00', '100', '0', '', '00', '01', '01', '', '1484989028911', '01', '1', '2017-01-21 17:04:35', '1', '2017-01-21 17:04:35');
INSERT INTO `bg_maple_detail` VALUES ('242c33930551440798e440acd4e5b21d', 'acb56ae4dd454b26beb150b7d08a14ed', 'invitedUserId', '被邀请人id', '01', '00', '100', '0', '', '00', '01', '01', '', '1487572014023', '01', '1', '2017-02-20 14:27:33', '1', '2017-02-20 14:27:45');
INSERT INTO `bg_maple_detail` VALUES ('276866ecbb21416f94f83ab692d96b71', '36ff1b75838f43efac8625df493e0df1', 'wxAccountStatus', '微信账户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484882249480', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('2775107f29434bf68b812a8437b751cf', '36ff1b75838f43efac8625df493e0df1', 'apiKey', 'API密钥', '01', '00', '100', '0', '', '00', '01', '01', '', '1484883254237', '01', '1', '2017-01-20 12:20:56', '1', '2017-01-20 14:55:36');
INSERT INTO `bg_maple_detail` VALUES ('28056f5c4e78405187c39a0b8ec21293', '67163a4e05664fa39d5bea61b445163b', 'seleRights', '查询权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794231222', '01', '1', '2017-01-19 10:50:51', '1', '2017-01-19 10:50:51');
INSERT INTO `bg_maple_detail` VALUES ('28697e1df826455b964fe2f0ebe2db0c', '3b3eb93879584c78a5ae5ea461dbc022', 'dealAmt', '交易金额', '04', '00', '100', '2', '', '00', '01', '01', '', '1488531866162', '01', '1', '2017-03-03 17:13:41', '1', '2017-03-04 13:38:20');
INSERT INTO `bg_maple_detail` VALUES ('28d66288b90a4cc0833f8e6cec268dbe', '7928b5c3a71443e69dac9a775fb732aa', 'userStatus', '后台用户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484726215275', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('2a8ae5e5ca3d46a6a721fc885b85a1ab', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryStatus', '商品分类状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487902706438', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('2ad3266fdcdb4a6b8d384ddc5cea4a8b', 'ad181811909d4f50b3c2c802e901be84', 'dictCode', '数据字典代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484529542050', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('2b09a57a9e5b4c25ad3910caf3ca7f50', '3b3eb93879584c78a5ae5ea461dbc022', 'appUserId', '零部件销售客户', '05', '00', '100', '0', '', '00', '01', '01', '', '1488531743723', '01', '1', '2017-03-03 17:02:44', '1', '2017-03-06 11:06:45');
INSERT INTO `bg_maple_detail` VALUES ('2d1f6f9eb85f4a2987afcee0e32093af', '7928b5c3a71443e69dac9a775fb732aa', 'userCode', '后台用户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484726215270', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_detail` VALUES ('2d5da37fa3764af8a4fe4cc5f735366e', '36ff1b75838f43efac8625df493e0df1', 'accessToken', '公众号的全局唯一票据', '01', '00', '1000', '0', '', '00', '00', '01', '', '1484889777852', '01', '1', '2017-01-20 13:24:19', '1', '2017-01-20 13:24:19');
INSERT INTO `bg_maple_detail` VALUES ('2ee96cb64503442097d963f7b3d15fc7', '742baf93ab0f4399a98c18d7adbe4622', 'menuBtnUrl', '网页链接', '01', '00', '1024', '0', '', '00', '01', '01', '', '1486350025045', '01', '1', '2017-02-06 11:03:26', '1', '2017-02-06 14:00:02');
INSERT INTO `bg_maple_detail` VALUES ('32efc08f0b9b446e9822df5064053c40', '3b04706d4ab8494faa53e05e822a6e82', 'jobName', '任务名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983425892', '01', '1', '2017-01-21 15:27:40', '1', '2017-01-21 15:27:40');
INSERT INTO `bg_maple_detail` VALUES ('37860f50888045d4a1b4dc3e1a13f895', '742baf93ab0f4399a98c18d7adbe4622', 'menuBtnKey', '菜单KEY值', '01', '00', '128', '0', '', '00', '01', '01', '', '1486346807088', '01', '1', '2017-02-06 10:56:54', '1', '2017-02-06 14:00:10');
INSERT INTO `bg_maple_detail` VALUES ('382aec89d15d47f6959c43dd6a663be3', '3b04706d4ab8494faa53e05e822a6e82', 'triggerGroupName', '触发器组名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983820288', '01', '1', '2017-01-21 15:30:57', '1', '2017-01-21 15:30:57');
INSERT INTO `bg_maple_detail` VALUES ('38c4182175c94eaab754e6f90e693a1b', '754773752b53495f9d452fbc9b06c37a', 'supplierCode', '供应商代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487842063069', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_detail` VALUES ('3aa05ba404f14abca74e3f148abd0b09', 'd25535c48c444f9fb675294c8a2d8e1f', 'integralCustomerId', '积分客户', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533665086', '01', '1', '2017-03-03 17:34:43', '1', '2017-03-03 17:34:43');
INSERT INTO `bg_maple_detail` VALUES ('3c2ca551cfcc4847903932819df15dd7', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserNum', '平台用户编号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486697231587', '01', '1', '2017-02-10 11:27:33', '1', '2017-02-10 11:27:33');
INSERT INTO `bg_maple_detail` VALUES ('3fa8dba2b21c4f458acbb0bf82ce6f46', 'ebd0083161064722a7c37d25a0030434', 'menuStatus', '后台菜单状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484546688787', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('40ac3c3da5cc4dcba0ac4ad464f2deb1', '754773752b53495f9d452fbc9b06c37a', 'supplierType', '供应商类型', '05', '00', '100', '0', 'com_supplierType', '00', '01', '01', '\"01\"', '1487842063087', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_detail` VALUES ('40f190c57abe431697318c35808f44c6', 'ebd0083161064722a7c37d25a0030434', 'menuCode', '后台菜单代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688739', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('41d9fdd347ab43649c471ba0a39b61c2', 'd770d5128d82483ab6763cfbed6e847d', 'integralCountBefore', '交易前积分数量', '04', '00', '100', '2', '', '00', '01', '01', '', '1488594678573', '01', '1', '2017-03-04 10:32:11', '1', '2017-03-06 10:30:14');
INSERT INTO `bg_maple_detail` VALUES ('43deda2ca573444eaae12e6136b19356', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleStatus', '代码生成状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4427d48c707045728293a6e68256a061', 'd770d5128d82483ab6763cfbed6e847d', 'integralDealCount', '积分交易数量', '04', '00', '100', '2', '', '00', '01', '01', '', '1488594303772', '01', '1', '2017-03-04 10:25:28', '1', '2017-03-06 10:30:07');
INSERT INTO `bg_maple_detail` VALUES ('44930026a5c6434ebbd84d887c058dbc', 'a4dc9b54bb3e4d17ad983912730a6667', 'headImgSrc', '产品头像', '01', '00', '100', '0', '', '00', '01', '01', '', '1487904553196', '01', '1', '2017-02-24 10:52:42', '1', '2017-02-24 10:52:42');
INSERT INTO `bg_maple_detail` VALUES ('449950385235437e9d0878bf698ce278', '2b8a44269b4542e48e13549c8e2e14a4', 'currentPrice', '现价', '04', '00', '100', '2', '', '00', '01', '01', '', '1487906121367', '01', '1', '2017-02-24 11:16:02', '1', '2017-02-24 11:16:02');
INSERT INTO `bg_maple_detail` VALUES ('44cf249239d04a5ead9aad13c20c1ccd', '9a85215a94334cbc83bb5cf701ccf3bb', 'headImgSrc', '用户头像', '01', '00', '255', '0', '', '00', '00', '01', '', '1486706276767', '01', '1', '2017-02-10 13:59:10', '1', '2017-03-06 13:58:18');
INSERT INTO `bg_maple_detail` VALUES ('4530ca7ba6f9486581ca3f58e31d6980', '9a85215a94334cbc83bb5cf701ccf3bb', 'sex', '性别', '01', '00', '100', '0', '', '00', '01', '01', '', '1486706161606', '01', '1', '2017-02-10 13:57:07', '1', '2017-02-10 13:57:07');
INSERT INTO `bg_maple_detail` VALUES ('4616314fd35947a69e9f0edce517b4ca', '0c1ea3878cd34c8b9f99283de36f25fb', 'typeCode', '类型代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191596294', '01', '1', '2017-01-12 11:26:36', '1', '2017-01-12 11:26:36');
INSERT INTO `bg_maple_detail` VALUES ('47b028fe3bd648f0a5b1d801c3a0eddb', '3b3eb93879584c78a5ae5ea461dbc022', 'sparepartId', '零部件', '05', '00', '100', '0', '', '00', '01', '01', '', '1488531796290', '01', '1', '2017-03-03 17:03:46', '1', '2017-03-03 17:05:06');
INSERT INTO `bg_maple_detail` VALUES ('47ec4fadae18458e8b9ee8d723939085', '7928b5c3a71443e69dac9a775fb732aa', 'phone', '手机号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1484728458411', '01', '1', '2017-01-18 16:35:49', '1', '2017-01-18 16:35:49');
INSERT INTO `bg_maple_detail` VALUES ('4a87a9e1c8404ad696c1b1629ab5bafc', 'a4dc9b54bb3e4d17ad983912730a6667', 'imgSrc1', '长框图', '01', '00', '100', '0', '', '00', '01', '01', '', '1487904971524', '01', '1', '2017-02-24 10:56:44', '1', '2017-02-24 10:56:44');
INSERT INTO `bg_maple_detail` VALUES ('4b44762d9bfa41d79151a9c79532f23c', '2b8a44269b4542e48e13549c8e2e14a4', 'stockNum', '库存数量', '02', '00', '100', '0', '', '00', '01', '01', '', '1487905200855', '01', '1', '2017-02-24 11:06:17', '1', '2017-02-24 11:06:17');
INSERT INTO `bg_maple_detail` VALUES ('4c86cb30de514679be2f9af313a0e1e1', 'ebd0083161064722a7c37d25a0030434', 'menuName', '后台菜单名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484546688782', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('4d48487873ab44f6b5cae2a9f5092c23', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleType', '代码生成类型', '05', '00', '100', '0', 'bg_mapleType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4e80f2fdfb844b8d99f00a66306bd664', '2b0255e6416a49669ba92dd90732c2ab', 'isFinal', '是否最终分类', '05', '00', '100', '0', 'com_sf', '00', '01', '01', '\"00\"', '1489110802696', '01', '1', '2017-03-10 09:55:01', '1', '2017-03-10 09:55:16');
INSERT INTO `bg_maple_detail` VALUES ('4fcb3375c7f5476098db5160c20dcfd0', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryType', '商品分类类型', '05', '00', '100', '0', 'com_productCategoryType', '00', '01', '01', '\"01\"', '1487902706437', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('52be3e073752470cb93d75b2be92b5e1', 'a4dc9b54bb3e4d17ad983912730a6667', 'summary', '摘要', '01', '00', '1000', '0', '', '00', '01', '01', '', '1487903197089', '01', '1', '2017-02-24 10:35:16', '1', '2017-02-24 10:42:00');
INSERT INTO `bg_maple_detail` VALUES ('530502b6cc5b45dfa9d4c751d095de9c', '7928b5c3a71443e69dac9a775fb732aa', 'remarks', '备注信息', '01', '00', '255', '0', '', '00', '01', '01', '', '1484728674804', '01', '1', '2017-01-18 16:38:12', '1', '2017-01-18 16:38:12');
INSERT INTO `bg_maple_detail` VALUES ('530694467ec34ee494604b51f05d76b9', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailStatus', '规格分类详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487913725176', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('531737534ab649f9bd4ced96cda1ffda', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailType', '代码生成详情类型', '05', '00', '100', '0', 'bg_mapleDetailType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('54398981fb824b04bafb3f1d3cd48f6f', '27a853950d0e4876ba0eccf8d7e2dd8f', 'status', '状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 17:11:35', '1', '2017-01-17 16:47:23');
INSERT INTO `bg_maple_detail` VALUES ('54d7b185a0f040dfb95a4d712205d3ed', 'd770d5128d82483ab6763cfbed6e847d', 'integralCountAfter', '交易后积分数量', '04', '00', '100', '2', '', '00', '01', '01', '', '1488594736670', '01', '1', '2017-03-04 10:32:41', '1', '2017-03-06 10:30:20');
INSERT INTO `bg_maple_detail` VALUES ('5712b8ad5b1c4589a7bb5a40aa391962', '0c1ea3878cd34c8b9f99283de36f25fb', 'defaultValue', '默认值', '01', '00', '100', '0', '', '00', '01', '01', '', '1484191753948', '01', '1', '2017-01-12 11:29:14', '1', '2017-01-12 11:29:14');
INSERT INTO `bg_maple_detail` VALUES ('5777ff112dcc4b57a9314a9b7fcf813d', '36ff1b75838f43efac8625df493e0df1', 'wxAccountName', '微信账户名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882249398', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('595ebbf50b574d8c8a40736b0f5a57c4', '3b3eb93879584c78a5ae5ea461dbc022', 'checkTime', '审核日期', '03', '00', '100', '0', '', '00', '00', '01', '', '1488532060685', '01', '1', '2017-03-03 17:06:51', '1', '2017-03-04 13:39:01');
INSERT INTO `bg_maple_detail` VALUES ('5fc96d4d2c2e4f2ab8d145905663cc85', '3b3eb93879584c78a5ae5ea461dbc022', 'count', '出售数量', '02', '00', '100', '0', '', '00', '01', '01', '', '1488531866161', '01', '1', '2017-03-03 17:04:56', '1', '2017-03-03 17:04:56');
INSERT INTO `bg_maple_detail` VALUES ('62cc898b340b484b918c3126def18303', 'a4dc9b54bb3e4d17ad983912730a6667', 'imgSrc2', '滚播图', '01', '00', '1000', '0', '', '00', '01', '01', '', '1487905006817', '01', '1', '2017-02-24 10:57:15', '1', '2017-02-24 10:57:15');
INSERT INTO `bg_maple_detail` VALUES ('62ef773c16164f0a955d5b845930d69c', '1ba234a840324c2faa9fc56e9ec9e144', 'wxQRcodeExpiry', '微信二维码有效期', '03', '00', '100', '0', '', '00', '01', '01', '', '1486779058463', '01', '1', '2017-02-11 10:12:54', '1', '2017-02-11 10:12:54');
INSERT INTO `bg_maple_detail` VALUES ('66b03a304168463e81938cf9c9361d94', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryCode', '商品分类代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902706435', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('688f191c23ef4d1f98a3559e470cf6e4', '27a853950d0e4876ba0eccf8d7e2dd8f', 'code', '代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 16:52:43', '1', '2017-01-11 16:52:43');
INSERT INTO `bg_maple_detail` VALUES ('6ce2076ea1334fcdac96c1e044cea7b9', '3b04706d4ab8494faa53e05e822a6e82', 'triggerName', '触发器名', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983701452', '01', '1', '2017-01-21 15:28:43', '1', '2017-01-21 15:28:43');
INSERT INTO `bg_maple_detail` VALUES ('6d5509c750f84076b65b019c7a49828f', 'ebd0083161064722a7c37d25a0030434', 'menuUrl', '菜单链接', '01', '00', '255', '0', '', '00', '01', '01', '', '1484546828872', '01', '1', '2017-01-16 14:08:02', '1', '2017-01-16 14:08:02');
INSERT INTO `bg_maple_detail` VALUES ('6e0782a153c74e2f967dc04a4e655326', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnName', '微信菜单按钮名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1486346372176', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('6e1c83d28e19411ab4778f546e107435', '27a853950d0e4876ba0eccf8d7e2dd8f', 'type', '类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 17:08:55', '1', '2017-01-11 17:08:55');
INSERT INTO `bg_maple_detail` VALUES ('6f33f41da04d49a7980246d2c1988904', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnCode', '微信菜单按钮代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486346372173', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('72a220e9fbe446e0aba82e240c5ee5c0', '0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategoryName', '商品分类名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902706436', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_detail` VALUES ('7386d05ed06547c39f660e6ef68f1a98', '46359ab1ffb041c196e16b95a654df12', 'productId', '产品id', '01', '00', '100', '0', '', '00', '01', '01', '', '1487917378968', '01', '1', '2017-02-24 14:23:59', '1', '2017-02-24 14:23:59');
INSERT INTO `bg_maple_detail` VALUES ('74c218455f564be4bc0a40c84eb10249', '3b3eb93879584c78a5ae5ea461dbc022', 'orderTime', '订单日期', '03', '00', '100', '0', '', '00', '01', '01', '', '1488531963735', '01', '1', '2017-03-03 17:06:26', '1', '2017-03-03 17:06:26');
INSERT INTO `bg_maple_detail` VALUES ('762f024f88d640ada0f36a59a259f7fb', '67163a4e05664fa39d5bea61b445163b', 'delRights', '删除权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794193285', '01', '1', '2017-01-19 10:50:08', '1', '2017-01-19 10:50:08');
INSERT INTO `bg_maple_detail` VALUES ('76e8bed04d6b4e5f8acdb81b6223ccf3', 'c9da8f2d57774bbbad13030135b6a1cb', 'controllerPackage', '控制器包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150518096', '01', '1', '2017-01-12 00:01:58', '1', '2017-01-12 00:01:58');
INSERT INTO `bg_maple_detail` VALUES ('778217d2d3e54a04b2621a8c2ada60e5', '9a85215a94334cbc83bb5cf701ccf3bb', 'phone', '电话号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1486704900464', '01', '1', '2017-02-10 13:41:04', '1', '2017-02-10 13:53:45');
INSERT INTO `bg_maple_detail` VALUES ('77d5c1c3c1c8425ea9536c64b3614c8e', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleCode', '代码生成代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('77dafcf782114bff9e1a53bb5854c5d3', 'd770d5128d82483ab6763cfbed6e847d', 'integralNoteCode', '积分记录代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488532875558', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
INSERT INTO `bg_maple_detail` VALUES ('7a5d5343cc4d4a8c9b027aea5fabffbe', '3b3eb93879584c78a5ae5ea461dbc022', 'sparepartDealCode', '零部件交易订单号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488531290179', '01', '1', '2017-03-03 16:54:50', '1', '2017-03-04 13:37:20');
INSERT INTO `bg_maple_detail` VALUES ('7ad405a7439c46cf93fd4bda1bb3037b', '7928b5c3a71443e69dac9a775fb732aa', 'password', '密码', '01', '00', '255', '0', '', '00', '01', '01', '', '1484726215272', '01', '1', '2017-01-18 16:36:25', '1', '2017-01-18 16:41:14');
INSERT INTO `bg_maple_detail` VALUES ('7bc20bf03cd640fe8ea4933be62c9abb', 'c723817cc46c4ff885b9f1a80702c6a9', 'phone', '手机号码', '01', '00', '100', '0', '', '00', '01', '01', '', '1488508289866', '01', '1', '2017-03-03 10:37:48', '1', '2017-03-03 14:35:46');
INSERT INTO `bg_maple_detail` VALUES ('7d765b5a1f194786ba6e7112bce8c3e0', 'c723817cc46c4ff885b9f1a80702c6a9', 'level', '级别', '02', '00', '100', '0', '', '00', '00', '01', '', '1488521020635', '01', '1', '2017-03-03 14:05:07', '1', '2017-03-03 14:05:07');
INSERT INTO `bg_maple_detail` VALUES ('80b50a3f08c74d0ab3ee217f1edbc54a', '3b3eb93879584c78a5ae5ea461dbc022', 'checkId', '审核人', '01', '00', '100', '0', '', '00', '00', '01', '', '1488532020455', '01', '1', '2017-03-03 17:07:25', '1', '2017-03-04 13:38:49');
INSERT INTO `bg_maple_detail` VALUES ('81a7ae095a374c31a2dd6f7995806b0d', 'c723817cc46c4ff885b9f1a80702c6a9', 'integralCustomerType', '积分客户类型', '05', '00', '100', '0', 'com_integralCustomerType', '00', '01', '01', '\"01\"', '1488508256595', '01', '1', '2017-03-03 10:30:57', '1', '2017-03-03 10:30:57');
INSERT INTO `bg_maple_detail` VALUES ('83dd1de384524795af254eff73adf927', '36ff1b75838f43efac8625df493e0df1', 'wxAccountCode', '微信账户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882249321', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('84c0bdb39c704c1b84f970c3030267ef', '307d768ee19e45b09b016970caf257ca', 'integral3', '4S店可获积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528285133', '01', '1', '2017-03-03 16:05:11', '1', '2017-03-03 16:05:11');
INSERT INTO `bg_maple_detail` VALUES ('878712f1b94b4a21843ef4d4bb5d096d', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailName', '规格分类详情名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913725171', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('878e2c01af44411b957d4203417bb024', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailStatus', '代码生成详情状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('89a5a14b9b7b44bab50594b6325cf415', '3b04706d4ab8494faa53e05e822a6e82', 'crontabName', '定时任务名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983375383', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('89b3d766603d4b4ea6ff015949c5b433', '0d2f5dbae9154a4bb3788bbfc8b61176', 'headImgSrc', '分类头像', '01', '00', '100', '0', '', '00', '01', '01', '', '1487916024410', '01', '1', '2017-02-24 14:01:39', '1', '2017-02-24 14:01:39');
INSERT INTO `bg_maple_detail` VALUES ('8b5c576423c64414b5987f02568b06d9', 'd770d5128d82483ab6763cfbed6e847d', 'integralDealStatus', '积分交易状态', '05', '00', '100', '0', 'com_integralDealStatus', '00', '00', '01', '\"00\"', '1488532875562', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-04 10:24:54');
INSERT INTO `bg_maple_detail` VALUES ('8bd6080677884a63a4fb72d49ccea2f9', '754773752b53495f9d452fbc9b06c37a', 'supplierStatus', '供应商状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487842063088', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_detail` VALUES ('8fb2330b8dd94af28f4823d86abd8a45', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailCode', '规格分类详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913725167', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('8fdc96238cc54846be115986ea4be2a9', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleStatus', '产品规格状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487902538810', '01', '1', '2017-02-24 10:15:39', '1', '2017-02-24 10:15:39');
INSERT INTO `bg_maple_detail` VALUES ('90f2a237fa464fdba1f77b9b26ae0d7f', '307d768ee19e45b09b016970caf257ca', 'sparepartName', '零配件名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1488528018793', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_detail` VALUES ('942d109f9052435eafa23c760e763fe0', '754773752b53495f9d452fbc9b06c37a', 'linkPhone', '联系电话', '01', '00', '100', '0', '', '00', '01', '01', '', '1487901522777', '01', '1', '2017-02-24 09:59:04', '1', '2017-02-24 09:59:04');
INSERT INTO `bg_maple_detail` VALUES ('969f5383c0a54b479439227e2e8adf24', '0c1ea3878cd34c8b9f99283de36f25fb', 'isKey', '是否主键', '05', '00', '100', '0', '', '00', '01', '01', '\"00\"', '1484191616190', '01', '1', '2017-01-12 11:28:16', '1', '2017-01-17 15:15:51');
INSERT INTO `bg_maple_detail` VALUES ('995aa3b2af92499089cdc88d6ab384b3', 'd770d5128d82483ab6763cfbed6e847d', 'integralNoteName', '积分记录名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1488532875559', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
INSERT INTO `bg_maple_detail` VALUES ('99a0aa832cda43bca5a13ed45c2dd441', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteUserId', '邀请人id', '01', '00', '100', '0', '', '00', '01', '01', '', '1487571940370', '01', '1', '2017-02-20 14:26:50', '1', '2017-02-20 14:27:53');
INSERT INTO `bg_maple_detail` VALUES ('9a92e5a8bdb04d04b01fcb0553c9b030', 'd770d5128d82483ab6763cfbed6e847d', 'integralNoteType', '积分记录类型', '05', '00', '100', '0', 'com_integralNoteType', '00', '01', '01', '\"01\"', '1488532875561', '01', '1', '2017-03-03 17:21:16', '1', '2017-03-03 17:21:16');
INSERT INTO `bg_maple_detail` VALUES ('9ae2dab2efcc41aab876f03b7395c8e1', '67163a4e05664fa39d5bea61b445163b', 'roleCode', '后台角色代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484794097594', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_detail` VALUES ('9b4084cf4fef4c7eb972a4c909cb367e', '2b8a44269b4542e48e13549c8e2e14a4', 'curType', '货币种类', '05', '00', '100', '0', 'com_curType', '00', '01', '01', '01', '1487905809817', '01', '1', '2017-03-10 17:15:59', '1', '2017-03-10 17:30:23');
INSERT INTO `bg_maple_detail` VALUES ('9bf5394fccf044c6b01aac17acdffea6', 'ad181811909d4f50b3c2c802e901be84', 'dictStatus', '数据字典状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484529542053', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 09:19:02');
INSERT INTO `bg_maple_detail` VALUES ('9c6605fc56524aaf8ba920c4cceb49d2', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleInitId', '产品规格初始id', '01', '00', '100', '0', '', '00', '00', '01', '', '1487902538801', '01', '1', '2017-02-24 11:08:14', '1', '2017-02-24 11:09:01');
INSERT INTO `bg_maple_detail` VALUES ('9d29ed260eed4306982adfb52b1f1e6b', '9a85215a94334cbc83bb5cf701ccf3bb', 'appUserStatus', '平台用户状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1486696707784', '01', '1', '2017-02-10 11:18:28', '1', '2017-02-10 11:18:28');
INSERT INTO `bg_maple_detail` VALUES ('9d5252681e574c9b9673296c1b73aad7', '3b04706d4ab8494faa53e05e822a6e82', 'crontabStatus', '定时任务状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1484983375410', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('9e08c85a4a7149c3af63fff1b2eac9fa', '742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtnStatus', '微信菜单按钮状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1486346372203', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_detail` VALUES ('a2b24eba68f742de864f18987eecf80e', 'c723817cc46c4ff885b9f1a80702c6a9', 'integralCustomerCode', '积分客户代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488508256550', '01', '1', '2017-03-03 10:30:57', '1', '2017-03-03 10:30:57');
INSERT INTO `bg_maple_detail` VALUES ('a2e09db55f0c43beb3fe9f09f62d2cd0', '67163a4e05664fa39d5bea61b445163b', 'addRights', '新增权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794135451', '01', '1', '2017-01-19 10:49:47', '1', '2017-01-19 10:49:47');
INSERT INTO `bg_maple_detail` VALUES ('a35792a023f848ac897f4a7753ea6938', '0c1ea3878cd34c8b9f99283de36f25fb', 'isNull', '是否null', '01', '00', '100', '9', '', '00', '01', '01', '\"01\"', '1484191654251', '01', '1', '2017-01-12 11:27:34', '1', '2017-01-17 16:01:38');
INSERT INTO `bg_maple_detail` VALUES ('a3a1939b310d41349f626e6406a20686', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleCode', '产品规格代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902538805', '01', '1', '2017-02-24 10:15:39', '1', '2017-02-24 10:15:39');
INSERT INTO `bg_maple_detail` VALUES ('a5b57af5f936434bad463f687f24cc1e', '9a85215a94334cbc83bb5cf701ccf3bb', 'remarks', '备注信息', '01', '00', '100', '0', '', '00', '01', '01', '', '1488779742618', '01', '1', '2017-03-06 13:56:06', '1', '2017-03-06 13:56:06');
INSERT INTO `bg_maple_detail` VALUES ('a795aac6efd64eb4b81bafcfdba74f19', 'ebd0083161064722a7c37d25a0030434', 'menuType', '后台菜单类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484546688785', '01', '1', '2017-01-16 14:04:49', '1', '2017-01-16 14:04:49');
INSERT INTO `bg_maple_detail` VALUES ('a863984b226847508fa91b8bfc84384a', '351d0afc825f474eb61c692722935fe8', 'styleCategoryDetailType', '规格分类详情类型', '05', '00', '100', '0', 'bg_styleCategoryDetailType', '00', '01', '01', '\"01\"', '1487913725174', '01', '1', '2017-02-24 13:22:05', '1', '2017-02-24 13:22:05');
INSERT INTO `bg_maple_detail` VALUES ('a9d320b6ba534243a2e87ca11cf50eb3', '7928b5c3a71443e69dac9a775fb732aa', 'userType', '后台用户类型', '05', '00', '100', '0', 'bg_userType', '00', '01', '01', '\"01\"', '1484726215274', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-19 14:46:05');
INSERT INTO `bg_maple_detail` VALUES ('aba1d712b9894f07bce792563f35ba21', '3b04706d4ab8494faa53e05e822a6e82', 'crontabCode', '定时任务代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1484983375345', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_detail` VALUES ('acccd70e48194133bb92afb0585b1de2', '36ff1b75838f43efac8625df493e0df1', 'wxAccountType', '微信账户类型', '05', '00', '100', '0', '', '00', '01', '01', '\"01\"', '1484882249476', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_detail` VALUES ('acfc7ae9467e4a79bd3387f6011a632d', 'e43dbde47f8e4b2fab9d226b9ce3976b', 'styleCategoryDetailCode', '规格分类详情代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487913761117', '01', '1', '2017-02-24 13:22:41', '1', '2017-02-24 13:22:41');
INSERT INTO `bg_maple_detail` VALUES ('ae7869c4b1ae443194ec367d56373a08', 'a4dc9b54bb3e4d17ad983912730a6667', 'introduction', '简介', '01', '00', '100', '0', '', '00', '01', '01', '', '1487903728670', '01', '1', '2017-02-24 10:41:10', '1', '2017-02-24 10:42:07');
INSERT INTO `bg_maple_detail` VALUES ('ae880d3d066b4fa7851d89c9732be099', '67163a4e05664fa39d5bea61b445163b', 'roleRights', '角色权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794097660', '01', '1', '2017-01-19 10:57:05', '1', '2017-01-19 10:57:32');
INSERT INTO `bg_maple_detail` VALUES ('af1467b9630543038a2784eba02e562a', '67163a4e05664fa39d5bea61b445163b', 'roleType', '后台角色类型', '05', '00', '100', '0', 'bgRoleType', '00', '01', '01', '\"01\"', '1484794097628', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 14:46:28');
INSERT INTO `bg_maple_detail` VALUES ('af635e4db1894f64b7de2ac306bb7d96', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeType', '用户邀请码类型', '05', '00', '100', '0', 'com_appInviteCodeType', '00', '01', '01', '\"01\"', '1486778955094', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('b10bf48749f24c639c45465c52e434d3', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleName', '产品规格名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487902538807', '01', '1', '2017-02-24 10:15:39', '1', '2017-02-24 10:15:39');
INSERT INTO `bg_maple_detail` VALUES ('b2904db80ab24c398ae0acb4a2eb4845', '46359ab1ffb041c196e16b95a654df12', 'categoryType', '分类类型', '05', '00', '100', '0', 'com_categoryType', '00', '01', '01', '\"01\"', '1487917239221', '01', '1', '2017-02-24 14:20:39', '1', '2017-02-24 14:20:39');
INSERT INTO `bg_maple_detail` VALUES ('b3262a3697484cd48874443d34b7e4f9', '307d768ee19e45b09b016970caf257ca', 'allIntegral', '总积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528032670', '01', '1', '2017-03-03 16:02:31', '1', '2017-03-03 16:02:31');
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
INSERT INTO `bg_maple_detail` VALUES ('dc3554660f37472194ed5acbbda72bf5', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteCode', '邀请代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1487571659395', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('dd86b865d60748f9b7e1bda79e943425', '2b8a44269b4542e48e13549c8e2e14a4', 'discountPrice', '折扣优惠', '04', '00', '100', '2', '', '00', '01', '01', '', '1487906084294', '01', '1', '2017-02-24 11:15:10', '1', '2017-02-24 11:15:18');
INSERT INTO `bg_maple_detail` VALUES ('deb09d9a2ee847a78adf60db3ee07b70', '2b8a44269b4542e48e13549c8e2e14a4', 'productStyleType', '产品规格类型', '05', '00', '100', '0', 'com_productStyleType', '00', '01', '01', '\"01\"', '1487902538809', '01', '1', '2017-02-24 10:15:39', '1', '2017-02-24 10:15:39');
INSERT INTO `bg_maple_detail` VALUES ('dec6270eeefc467a891e4b8ae9ddba55', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeCode', '用户邀请码代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1486778955069', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('ded2d6acc5044f3ea3e8dadbea72f2c4', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeStatus', '用户邀请码状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1486778955103', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('e099d4d3642848d99496eb3c33e9fd4c', 'd25535c48c444f9fb675294c8a2d8e1f', 'code', '扩展属性代号', '01', '00', '100', '0', '', '00', '01', '01', '', '1488533783136', '01', '1', '2017-03-03 17:37:07', '1', '2017-03-03 17:37:07');
INSERT INTO `bg_maple_detail` VALUES ('e193264b441e4e7986e0600393698674', '9a85215a94334cbc83bb5cf701ccf3bb', 'password', '密码', '01', '00', '255', '0', '', '00', '01', '01', '', '1486706055223', '01', '1', '2017-02-10 13:54:37', '1', '2017-03-06 13:56:31');
INSERT INTO `bg_maple_detail` VALUES ('e2aad00ef3e849abae3cf13157b076c6', '754773752b53495f9d452fbc9b06c37a', 'supplierName', '供应商名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487842063086', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_detail` VALUES ('e8e429fb06a048aa942aac0dcac2707a', '2b8a44269b4542e48e13549c8e2e14a4', 'discountRate', '折扣', '04', '00', '100', '2', '', '00', '01', '01', '', '1487905886721', '01', '1', '2017-02-24 11:11:50', '1', '2017-02-24 11:13:27');
INSERT INTO `bg_maple_detail` VALUES ('e8f6ef912613464daa85940683606069', '67163a4e05664fa39d5bea61b445163b', 'editRights', '修改权限', '01', '00', '100', '0', '', '00', '00', '01', '', '1484794210103', '01', '1', '2017-01-19 10:50:23', '1', '2017-01-19 10:50:23');
INSERT INTO `bg_maple_detail` VALUES ('ea375dcc8a3842c38cd051e6a3c115f4', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteStatus', '邀请状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487571659418', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('ebe20425039f42ce89cef007dce47f2c', '2b8a44269b4542e48e13549c8e2e14a4', 'productId', '商品id', '05', '00', '100', '0', '', '00', '01', '01', '', '1487902538803', '01', '1', '2017-02-24 11:07:20', '1', '2017-02-24 11:09:09');
INSERT INTO `bg_maple_detail` VALUES ('ecadb90eba374f51bbfb13482199e30e', 'acb56ae4dd454b26beb150b7d08a14ed', 'inviteName', '邀请名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1487571659416', '01', '1', '2017-02-20 14:20:59', '1', '2017-02-20 14:20:59');
INSERT INTO `bg_maple_detail` VALUES ('edaaa6a0b0164200869165de5681dad1', '36ff1b75838f43efac8625df493e0df1', 'jsApiTicket', '微信JS接口的临时票据', '01', '00', '100', '0', '', '00', '00', '01', '', '1484889868656', '01', '1', '2017-01-20 13:49:23', '1', '2017-01-20 14:55:51');
INSERT INTO `bg_maple_detail` VALUES ('edd8ca8904a84061beb0d184bd534f6c', '1ba234a840324c2faa9fc56e9ec9e144', 'mediaExpiry', '媒体文件有效时间', '03', '00', '100', '0', '', '00', '01', '01', '', '1486782310614', '01', '1', '2017-02-11 11:05:53', '1', '2017-02-11 11:05:53');
INSERT INTO `bg_maple_detail` VALUES ('eeafe483879b40c6aaeb56083f9b8c44', '27a853950d0e4876ba0eccf8d7e2dd8f', 'name', '名称', '01', '00', '100', '0', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 17:07:21', '1', '2017-01-11 17:07:21');
INSERT INTO `bg_maple_detail` VALUES ('ef3cc316b9bf45059bd65405e7366741', 'c9da8f2d57774bbbad13030135b6a1cb', 'entityPackage', '实体类包代号', '05', '00', '100', '0', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150564639', '01', '1', '2017-01-12 00:02:44', '1', '2017-01-12 00:02:44');
INSERT INTO `bg_maple_detail` VALUES ('ef643eda1c024e2cae5eb8278ed002f7', 'a4dc9b54bb3e4d17ad983912730a6667', 'productModel', '产品模型', '05', '00', '100', '0', '', '00', '01', '01', '', '1487902775533', '01', '1', '2017-02-24 10:22:33', '1', '2017-02-24 10:22:42');
INSERT INTO `bg_maple_detail` VALUES ('ef6b8a854ab140bbb0c01b18094f9db9', '0c1ea3878cd34c8b9f99283de36f25fb', 'totalLength', '总长度', '02', '00', '20', '0', '', '00', '01', '01', '100', '1484191421616', '01', '1', '2017-01-12 11:23:42', '1', '2017-01-15 23:59:55');
INSERT INTO `bg_maple_detail` VALUES ('ef844e57651d4fb1a2ff2ae1ddb8834e', '2b0255e6416a49669ba92dd90732c2ab', 'styleCategoryStatus', '规格分类状态', '01', '00', '100', '0', '', '00', '00', '01', '\"00\"', '1487912483496', '01', '1', '2017-02-24 13:01:23', '1', '2017-02-24 13:01:23');
INSERT INTO `bg_maple_detail` VALUES ('f233145128f84b12b9ed7c16c00c57db', '307d768ee19e45b09b016970caf257ca', 'integral2', '小区经理可获积分', '04', '00', '100', '2', '', '00', '01', '01', '', '1488528219886', '01', '1', '2017-03-03 16:04:43', '1', '2017-03-03 16:04:43');
INSERT INTO `bg_maple_detail` VALUES ('f388076884914adb9df661523c17ff88', '36ff1b75838f43efac8625df493e0df1', 'appId', 'AppID(应用ID)', '01', '00', '100', '0', '', '00', '01', '01', '', '1484882330433', '01', '1', '2017-01-20 11:20:13', '1', '2017-01-20 13:22:42');
INSERT INTO `bg_maple_detail` VALUES ('f48e476770d747eab5981bc1aa20861f', '1ba234a840324c2faa9fc56e9ec9e144', 'appInviteCodeName', '用户邀请码名称', '01', '00', '100', '0', '', '00', '01', '01', '', '1486778955084', '01', '1', '2017-02-11 10:09:15', '1', '2017-02-11 10:09:15');
INSERT INTO `bg_maple_detail` VALUES ('f5288b368efa4b3eaf1ad35f56a2d348', 'ad181811909d4f50b3c2c802e901be84', 'dictType', '数据字典类型', '05', '00', '100', '0', 'bg_dictType', '00', '01', '01', '\"01\"', '1484529542052', '01', '1', '2017-01-16 09:19:02', '1', '2017-01-16 10:37:46');
INSERT INTO `bg_maple_detail` VALUES ('f550e5c2a7f94cd5b5fefcafaf4203f8', 'd770d5128d82483ab6763cfbed6e847d', 'appUserId', '平台用户', '05', '00', '100', '0', '', '00', '01', '01', '', '1488594303771', '01', '1', '2017-03-06 11:08:13', '1', '2017-03-06 11:10:25');
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
INSERT INTO `bg_maple_main` VALUES ('0d2f5dbae9154a4bb3788bbfc8b61176', 'productCategory', '商品分类', '01', '00', 'bg', 'com', '1487902595858', '01', '1', '2017-02-24 10:18:26', '1', '2017-02-24 10:18:26');
INSERT INTO `bg_maple_main` VALUES ('27a853950d0e4876ba0eccf8d7e2dd8f', 'baseField', '基本字段', '01', '00', 'bg', 'bg', '1484122184486', '01', '1', '2017-01-11 16:09:44', '1', '2017-01-11 16:09:44');
INSERT INTO `bg_maple_main` VALUES ('2b0255e6416a49669ba92dd90732c2ab', 'styleCategory', '规格分类', '02', '00', 'bg', 'com', '1487912444000', '01', '1', '2017-02-24 13:01:23', '1', '2017-03-10 09:56:44');
INSERT INTO `bg_maple_main` VALUES ('2b8a44269b4542e48e13549c8e2e14a4', 'productStyle', '产品规格', '01', '00', 'bg', 'com', '1487902149025', '01', '1', '2017-02-24 10:15:39', '1', '2017-02-24 10:15:39');
INSERT INTO `bg_maple_main` VALUES ('307d768ee19e45b09b016970caf257ca', 'sparepart', '零配件', '01', '00', 'bg', 'com', '1488527998004', '01', '1', '2017-03-03 16:00:19', '1', '2017-03-03 16:00:19');
INSERT INTO `bg_maple_main` VALUES ('36ff1b75838f43efac8625df493e0df1', 'wxAccount', '微信账户', '01', '00', 'bg', 'com', '1484881971240', '01', '1', '2017-01-20 11:17:29', '1', '2017-01-20 11:17:29');
INSERT INTO `bg_maple_main` VALUES ('3b04706d4ab8494faa53e05e822a6e82', 'crontab', '定时任务', '01', '00', 'bg', 'bg', '1484983080995', '01', '1', '2017-01-21 15:22:55', '1', '2017-01-21 15:22:55');
INSERT INTO `bg_maple_main` VALUES ('3b3eb93879584c78a5ae5ea461dbc022', 'sparepartDeal', '零部件交易', '01', '00', 'bg', 'com', '1488531259942', '01', '1', '2017-03-03 16:54:50', '1', '2017-03-03 17:21:22');
INSERT INTO `bg_maple_main` VALUES ('67163a4e05664fa39d5bea61b445163b', 'role', '后台角色', '01', '00', 'bg', 'bg', '1484794064296', '01', '1', '2017-01-19 10:48:18', '1', '2017-01-19 10:48:18');
INSERT INTO `bg_maple_main` VALUES ('742baf93ab0f4399a98c18d7adbe4622', 'wxMenuBtn', '微信菜单按钮', '02', '00', 'bg', 'bg', '1486346320511', '01', '1', '2017-02-06 09:59:32', '1', '2017-02-06 09:59:32');
INSERT INTO `bg_maple_main` VALUES ('754773752b53495f9d452fbc9b06c37a', 'supplier', '供应商', '01', '00', 'bg', 'com', '1487841762885', '01', '1', '2017-02-23 17:27:43', '1', '2017-02-23 17:27:43');
INSERT INTO `bg_maple_main` VALUES ('7928b5c3a71443e69dac9a775fb732aa', 'user', '后台用户', '01', '00', 'bg', 'bg', '1484726157405', '01', '1', '2017-01-18 15:56:55', '1', '2017-01-18 15:56:55');
INSERT INTO `bg_maple_main` VALUES ('9a85215a94334cbc83bb5cf701ccf3bb', 'appUser', '平台用户', '02', '00', 'bg', 'com', '1486696475857', '01', '1', '2017-02-10 11:18:28', '1', '2017-03-06 11:19:24');
INSERT INTO `bg_maple_main` VALUES ('a4dc9b54bb3e4d17ad983912730a6667', 'product', '产品', '01', '00', 'bg', 'com', '1487901959012', '01', '1', '2017-02-24 10:09:02', '1', '2017-02-24 10:09:02');
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
INSERT INTO `bg_menu_tree` VALUES ('211833bb44a94521b6589dc3b76c3f81', '2ecf674826d941438d9bc8175d309588', 'background_productCategory', '产品类别管理', '02', '01', '22', 'background/productCategory/list.do', 'menu-icon fa fa-globe red', '1487928431940', '01', '1', '2017-02-24 17:32:03', '1', '2017-02-25 09:40:37');
INSERT INTO `bg_menu_tree` VALUES ('24ca601e8eb6460d82f26c3bd0277e45', '2a66f51272904a7e9e4e419448b6400a', 'qxgl', '权限管理', '02', '01', '10', '#', 'menu-icon fa fa-lock black', '1487926583528', '01', '1', '2017-02-24 16:56:44', '1', '2017-02-24 16:56:50');
INSERT INTO `bg_menu_tree` VALUES ('286d62cc09eb41ecaac2f8435a97fc6e', 'e198cad6387b4ccb8a0209b961c34f7d', 'background_crontab', '定时任务管理', '02', '01', '16', 'background/crontab/list.do', 'menu-icon fa fa-lightbulb-o orange', '1487927010037', '01', '1', '2017-02-24 17:04:00', '1', '2017-02-24 17:04:34');
INSERT INTO `bg_menu_tree` VALUES ('2a66f51272904a7e9e4e419448b6400a', '0', 'xtgl', '系统管理', '02', '01', '1', '#', 'menu-icon fa fa-desktop blue', '1487924690462', '01', '1', '2017-02-24 16:25:59', '1', '2017-02-24 16:45:14');
INSERT INTO `bg_menu_tree` VALUES ('2af6eded47c742839cd38ac1815ec305', '0', 'ddgl', '订单管理', '02', '01', '7', '#', 'menu-icon fa fa-credit-card green', '1487925605005', '01', '1', '2017-02-24 16:40:32', '1', '2017-02-24 16:48:32');
INSERT INTO `bg_menu_tree` VALUES ('2ecf674826d941438d9bc8175d309588', '0', 'cpgl', '产品管理', '02', '01', '6', '#', 'menu-icon fa fa-gift brown', '1487925444388', '01', '1', '2017-02-24 16:38:09', '1', '2017-02-24 16:48:26');
INSERT INTO `bg_menu_tree` VALUES ('4237a71a32f442e6ab9b5ad33e2c45c6', '2ecf674826d941438d9bc8175d309588', 'background_sparepart', '零配件管理', '02', '01', '27', 'background/sparepart/list.do', 'menu-icon fa fa-leaf purple', '1488528558603', '01', '1', '2017-03-03 16:10:31', '1', '2017-03-03 16:10:47');
INSERT INTO `bg_menu_tree` VALUES ('4631fcf6f8e24b5180bb5ae64cc118a4', '0', 'shgl', '售后管理', '02', '01', '9', '#', 'menu-icon fa fa-laptop blue', '1487925842602', '01', '1', '2017-02-24 16:45:06', '1', '2017-02-24 16:52:00');
INSERT INTO `bg_menu_tree` VALUES ('656a072a483d44b8b02906c6cc692154', '2a66f51272904a7e9e4e419448b6400a', 'background_dict', '数据字典', '02', '01', '12', 'background/dict/main.do', 'menu-icon fa fa-book orange', '1487926669970', '01', '1', '2017-02-24 16:59:24', '1', '2017-02-24 16:59:51');
INSERT INTO `bg_menu_tree` VALUES ('6952f6500bd7403ebe1dd61337209f29', '0', 'sjkgl', '数据库管理', '02', '01', '3', '#', 'menu-icon fa fa-hdd-o blue', '1487925347271', '01', '1', '2017-02-24 16:36:07', '1', '2017-02-24 16:45:40');
INSERT INTO `bg_menu_tree` VALUES ('6b5fe0915dd64cdc98edb038ce55980a', '24ca601e8eb6460d82f26c3bd0277e45', 'background_role', '角色(基础权限)', '02', '01', '13', 'background/role/list.do', 'menu-icon fa fa-key orange', '1487926824242', '01', '1', '2017-02-24 17:00:48', '1', '2017-02-24 17:01:01');
INSERT INTO `bg_menu_tree` VALUES ('6d8cec4e3459405daae1fcd6016b26f2', '967da4d8a0e34db393a28ba315e59ae0', 'background_integralNote', '积分客户积分交易记录', '02', '01', '29', 'background/integralNote/list.do', 'menu-icon fa fa-leaf black', '1488596110247', '01', '1', '2017-03-04 10:56:30', '1', '2017-03-04 10:56:30');
INSERT INTO `bg_menu_tree` VALUES ('7276636bd85440858b814e04105fc044', '2ecf674826d941438d9bc8175d309588', 'background_productStyle', '产品规格管理', '02', '01', '23', 'background/productStyle/list.do', 'menu-icon fa fa-leaf black', '1487986244765', '01', '1', '2017-02-25 09:33:30', '1', '2017-02-25 09:33:30');
INSERT INTO `bg_menu_tree` VALUES ('76ea10cc8ddf4648877e3729997eb873', '2ecf674826d941438d9bc8175d309588', 'background_supplier', '供应商管理', '02', '01', '21', 'background/supplier/list.do', 'menu-icon fa fa-users black', '1487928333702', '01', '1', '2017-02-24 17:26:59', '1', '2017-02-25 09:39:37');
INSERT INTO `bg_menu_tree` VALUES ('8c598c875e5a415297d952557199b3a2', '96b19ecb695e45219374388a264faf8b', 'background_wxMenuBtn', '微信菜单按钮管理', '02', '01', '20', 'background/wxMenuBtn/main.do', 'menu-icon fa fa-comments red', '1487927760117', '01', '1', '2017-02-24 17:16:19', '1', '2017-02-24 17:16:30');
INSERT INTO `bg_menu_tree` VALUES ('904be00723324555932961eeb6d1114e', '2ecf674826d941438d9bc8175d309588', 'background_product', '产品管理', '02', '01', '25', 'background/product/list.do', 'menu-icon fa fa-globe purple', '1487928431950', '01', '1', '2017-02-25 09:37:00', '1', '2017-02-25 09:41:42');
INSERT INTO `bg_menu_tree` VALUES ('967da4d8a0e34db393a28ba315e59ae0', '0', 'zdgl', '账单管理', '02', '01', '8', '#', 'menu-icon fa fa-credit-card pink', '1487925762928', '01', '1', '2017-02-24 16:43:49', '1', '2017-02-24 16:48:36');
INSERT INTO `bg_menu_tree` VALUES ('96b19ecb695e45219374388a264faf8b', '0', 'wxgl', '微信管理', '02', '01', '5', '#', 'menu-icon fa fa-comments green', '1487925387762', '01', '1', '2017-02-24 16:48:05', '1', '2017-02-24 16:51:38');
INSERT INTO `bg_menu_tree` VALUES ('982aa26baaa84b6e9d43a0d69654a827', '96b19ecb695e45219374388a264faf8b', 'background_wxAccount', '微信账号管理', '02', '01', '19', 'background/wxAccount/list.do', 'menu-icon fa fa-comments blue', '1487927712953', '01', '1', '2017-02-24 17:15:46', '1', '2017-02-24 17:15:58');
INSERT INTO `bg_menu_tree` VALUES ('9e89d4909c7440c3b9fd5f933055f824', 'd41c4bf59b274d81bcb982c00cb2b2b3', 'background_appUser', '平台用户', '02', '01', '18', 'background/appUser/main.do', 'menu-icon fa fa-users blue', '1487927658996', '01', '1', '2017-02-24 17:14:38', '1', '2017-03-06 15:37:59');
INSERT INTO `bg_menu_tree` VALUES ('af781392f8f54627b165fa414c4562d6', '2ecf674826d941438d9bc8175d309588', 'background_styleCategory', '规格分类管理', '02', '01', '24', 'background/styleCategory/main.do', 'menu-icon fa fa-globe green', '1487928431960', '01', '1', '2017-02-25 09:35:29', '1', '2017-03-10 10:12:45');
INSERT INTO `bg_menu_tree` VALUES ('c2f704b36ccf49abae4ec777a640dac6', 'daffae3ab1554bd89dab69d72c1b9826', 'background_sparepartDeal', '零配件订单审核', '02', '01', '30', 'background/sparepartDeal/list.do', 'menu-icon fa fa-gavel orange', '1488595937615', '01', '1', '2017-03-04 10:53:01', '1', '2017-03-04 10:57:26');
INSERT INTO `bg_menu_tree` VALUES ('c676a8cdc73c4496bae43af104e2b524', '11ebc426cf7341628ecb33ddc676f887', 'background_maple', '正向生成', '02', '01', '15', 'background/maple/list.do', 'menu-icon fa fa-camera-retro green', '1487926917132', '01', '1', '2017-02-24 17:02:20', '1', '2017-02-24 17:03:17');
INSERT INTO `bg_menu_tree` VALUES ('d080a9f1d0b04ee5bba99c352563178d', '2a66f51272904a7e9e4e419448b6400a', 'background_menu', '菜单管理', '02', '01', '11', 'background/menu/main.do', 'menu-icon fa fa-folder-open-o red', '1487926618522', '01', '1', '2017-02-24 16:57:33', '1', '2017-02-24 16:57:45');
INSERT INTO `bg_menu_tree` VALUES ('d41c4bf59b274d81bcb982c00cb2b2b3', '0', 'yhgl', '用户管理', '02', '01', '4', '#', 'menu-icon fa fa-users blue', '1487925387761', '01', '1', '2017-02-24 16:37:03', '1', '2017-02-24 16:45:48');
INSERT INTO `bg_menu_tree` VALUES ('daffae3ab1554bd89dab69d72c1b9826', '0', 'shegl', '审核管理', '02', '01', '28', '#', 'menu-icon fa fa-gavel black', '1488531040354', '01', '1', '2017-03-03 16:52:10', '1', '2017-03-04 10:57:50');
INSERT INTO `bg_menu_tree` VALUES ('e198cad6387b4ccb8a0209b961c34f7d', '0', 'xtgj', '系统工具', '02', '01', '2', '#', 'menu-icon fa fa-cogs grey', '1487925067666', '01', '1', '2017-02-24 16:34:37', '1', '2017-02-24 16:45:28');
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
INSERT INTO `bg_role_info` VALUES ('1', 'admin', '系统管理员', '00', '00', '2080374782', '2080374782', '2080374782', '2080374782', '2080374782', '1', '01', '1', '2017-01-19 13:24:30', '1', '2017-03-06 15:40:55');
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
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('1f4ca1027b91435c93eec22ab6f445dd', '9828b1377557429c8cb449440291e042', 'giftOL_personal', '个人中心', '00', '00', '', '', '', '1486371274109', '01', '1', '2017-02-06 16:55:33', '1', '2017-02-07 15:33:10');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('3ab4159f505e4a88902f651200fd87c9', '9828b1377557429c8cb449440291e042', 'giftOL_active', '活动', 'view', '00', '', 'http://gift.yooojung.com/mine/mine!toMineIndex1.shtml', '', '1486370775800', '01', '1', '2017-02-06 16:48:04', '1', '2017-02-07 14:12:05');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('52f2b818341d4468885f741236b47b29', '1f4ca1027b91435c93eec22ab6f445dd', 'giftOL_toLogin', '登录', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/main/toLogin.do', '', '1487665058373', '01', '1', '2017-02-21 16:18:19', '1', '2017-02-21 16:18:41');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('619831691da0444484c39502fbc307fc', '1f4ca1027b91435c93eec22ab6f445dd', 'giftOL_myWallet', '我的钱包', 'view', '00', '', 'http://1b62112g29.iok.la/JXDome/weixin/mine/toMyWallet.do', '', '1486454357819', '01', '1', '2017-02-07 15:59:54', '1', '2017-02-21 16:17:16');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('9828b1377557429c8cb449440291e042', '0', 'giftOL', '在线送礼', '00', '00', null, null, null, '1484896943192', '01', '1', '2017-01-20 15:26:49', '1', '2017-01-20 15:26:49');
INSERT INTO `bg_wx_menu_btn_tree` VALUES ('ccd4757c210b48baa852f08ddeac6ece', '1f4ca1027b91435c93eec22ab6f445dd', 'giftOL_myqrcode', '我的二维码', 'click', '00', 'ewm', '', '', '1487057185478', '01', '1', '2017-02-14 15:27:31', '1', '2017-02-14 15:27:31');
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
INSERT INTO `com_app_user_ext` VALUES ('0ab9ff26d62b48428a9665c5b109f15a', '7b550a0b71ef4927a4cacf86ec82e7c3', 'integralCount', '积分数', '53536080', '1488876957842', '01', '1', '2017-03-07 16:55:58', '1', '2017-03-08 11:25:34');
INSERT INTO `com_app_user_ext` VALUES ('1ce3df0789304de5b5921c0610813a32', '523f04fe17124aeb85d80b67387efc8b', 'wxQRcodeSrc', '微信二维码', '', '1488876871600', '01', '1', '2017-03-07 16:54:32', '1', '2017-03-07 16:54:32');
INSERT INTO `com_app_user_ext` VALUES ('454d0f0d15d44e59ab1d5a2148c90a7a', 'b46d1c1881724aa2b4e35314847f4f5a', 'integralCount', '积分数', '10038015', '1488876926670', '01', '1', '2017-03-07 16:55:27', '1', '2017-03-08 11:25:34');
INSERT INTO `com_app_user_ext` VALUES ('4927e53d867a4201a38bf1f4932770c4', '7b550a0b71ef4927a4cacf86ec82e7c3', 'wxQRcodeExpiry', '微信二维码有效期', '', '1488876957849', '01', '1', '2017-03-07 16:55:58', '1', '2017-03-07 16:55:58');
INSERT INTO `com_app_user_ext` VALUES ('4b44724fe11f4aabb55ca92fcf1285f0', '7b550a0b71ef4927a4cacf86ec82e7c3', 'mediaId', '媒体文件id', '', '1488876957846', '01', '1', '2017-03-07 16:55:58', '1', '2017-03-07 16:55:58');
INSERT INTO `com_app_user_ext` VALUES ('5557fbba5dca4945a413e1a3ae979c8b', 'b46d1c1881724aa2b4e35314847f4f5a', 'wxQRcodeExpiry', '微信二维码有效期', '', '1488876926676', '01', '1', '2017-03-07 16:55:27', '1', '2017-03-07 16:55:27');
INSERT INTO `com_app_user_ext` VALUES ('7a6121354346438cbe1ce9356c3dc5d1', '523f04fe17124aeb85d80b67387efc8b', 'mediaExpiry', '媒体文件有效时间', '', '1488876871596', '01', '1', '2017-03-07 16:54:32', '1', '2017-03-07 16:54:32');
INSERT INTO `com_app_user_ext` VALUES ('9aab9d19cd4548119e5dd41465edeed8', '523f04fe17124aeb85d80b67387efc8b', 'wxQRcodeExpiry', '微信二维码有效期', '', '1488876871599', '01', '1', '2017-03-07 16:54:32', '1', '2017-03-07 16:54:32');
INSERT INTO `com_app_user_ext` VALUES ('9e9afef00ef748cbaeb07d138617406b', 'b46d1c1881724aa2b4e35314847f4f5a', 'mediaId', '媒体文件id', '', '1488876926674', '01', '1', '2017-03-07 16:55:27', '1', '2017-03-07 16:55:27');
INSERT INTO `com_app_user_ext` VALUES ('a49f796ee9b2466cace434ee540bdc82', '7b550a0b71ef4927a4cacf86ec82e7c3', 'mediaExpiry', '媒体文件有效时间', '', '1488876957844', '01', '1', '2017-03-07 16:55:58', '1', '2017-03-07 16:55:58');
INSERT INTO `com_app_user_ext` VALUES ('a9605734ac2d48558437a9d9708a49f8', '523f04fe17124aeb85d80b67387efc8b', 'integralCount', '积分数', '3346005', '1488876871593', '01', '1', '2017-03-07 16:54:32', '1', '2017-03-08 11:25:34');
INSERT INTO `com_app_user_ext` VALUES ('d2ab0097e9c94f3a847c166e44aefda7', 'b46d1c1881724aa2b4e35314847f4f5a', 'mediaExpiry', '媒体文件有效时间', '', '1488876926672', '01', '1', '2017-03-07 16:55:27', '1', '2017-03-07 16:55:27');
INSERT INTO `com_app_user_ext` VALUES ('d499f8d9d38548adbd9fa59c8d0082e0', '7b550a0b71ef4927a4cacf86ec82e7c3', 'wxQRcodeSrc', '微信二维码', '', '1488876957851', '01', '1', '2017-03-07 16:55:58', '1', '2017-03-07 16:55:58');
INSERT INTO `com_app_user_ext` VALUES ('e227068f065145569d3bf022fde89bbe', '523f04fe17124aeb85d80b67387efc8b', 'mediaId', '媒体文件id', '', '1488876871598', '01', '1', '2017-03-07 16:54:32', '1', '2017-03-07 16:54:32');
INSERT INTO `com_app_user_ext` VALUES ('f379908f0ac44282896ea196d21e8b96', 'b46d1c1881724aa2b4e35314847f4f5a', 'wxQRcodeSrc', '微信二维码', '', '1488876926678', '01', '1', '2017-03-07 16:55:27', '1', '2017-03-07 16:55:27');

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
INSERT INTO `com_app_user_tree` VALUES ('523f04fe17124aeb85d80b67387efc8b', '0', '01', 'ic0101', '张明', '01', '00', 'ic0101', '13575542432', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '01', '', '2017-03-07 16:54:15', 'sad', '1', '1488876855952', '01', '1', '2017-03-07 16:54:31', '1', '2017-03-07 16:54:31');
INSERT INTO `com_app_user_tree` VALUES ('7b550a0b71ef4927a4cacf86ec82e7c3', 'b46d1c1881724aa2b4e35314847f4f5a', '03', 'ic0124', '陈华', '01', '00', 'ic0124', '13685777441', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '02', '', '2017-03-07 16:55:29', 'fd从五色风范', '3', '1488876929273', '01', '1', '2017-03-07 16:55:58', '1', '2017-03-07 16:55:58');
INSERT INTO `com_app_user_tree` VALUES ('b46d1c1881724aa2b4e35314847f4f5a', '523f04fe17124aeb85d80b67387efc8b', '02', 'ic0102', '向南', '01', '00', 'ic0102', '15876362530', '73148c263ffc498634cc1ebebd67516997011169a37e2811ed51d52c82d4d572af32c231139f1d0fd7afe29e441d44aefa29c1855ec65207639272110d195674', '02', '', '2017-03-07 16:55:09', '发收到', '2', '1488876909716', '01', '1', '2017-03-07 16:55:27', '1', '2017-03-07 16:55:27');

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
INSERT INTO `com_dict_tree` VALUES ('0bfe186203cd4beea7355649ee9122f6', '78b4651dca85412095298b54c2d01128', 'com_productCategoryType_03', '自定义类型', '01', '00', '03', '1', '1487989230516', '01', '1', '2017-02-25 10:20:50', '1', '2017-02-25 10:20:50');
INSERT INTO `com_dict_tree` VALUES ('0c608e9327344f588eebd8edce01de8b', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_01', '标准型', '01', '00', '01', '1', '1484025817953', '01', '1', '2017-01-10 13:23:38', '1', '2017-01-10 13:23:38');
INSERT INTO `com_dict_tree` VALUES ('1267d5aa01d440b1a8a686e9494eeece', '0', 'com_appUserType', '平台用户类型', '01', '00', 'com_integralCustomerType', '0', '1488524156915', '01', '1', '2017-03-03 14:56:46', '1', '2017-03-06 15:51:54');
INSERT INTO `com_dict_tree` VALUES ('132b054047c14abc87f6be78ad0f710a', '0', 'bg_userEffective', '后台生效用户', '02', '00', 'bg_userEffective', '0', '1484726025794', '01', '1', '2017-01-18 15:55:03', '1', '2017-01-18 15:55:09');
INSERT INTO `com_dict_tree` VALUES ('14ff3ee812a44691b125d9f4c74142ec', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_scancode_waitmsg', '扫码推事件且弹出“消息接收中”提示框', '01', '00', 'scancode_waitmsg', '1', '1486366098596', '01', '1', '2017-02-06 15:28:33', '1', '2017-02-06 15:28:33');
INSERT INTO `com_dict_tree` VALUES ('16e4fa8be1b4408eae79516c55733df5', '0', 'com_styleCategoryEffective', '生效商品规格分类', '02', '00', 'com_styleCategoryEffective', '0', '1489050940474', '01', '1', '2017-03-09 17:15:53', '1', '2017-03-09 17:16:28');
INSERT INTO `com_dict_tree` VALUES ('173468d2a2d24dd48642f6cd452c11d2', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_03', '微信回调', '01', '00', '03', '1', '1485051617535', '01', '1', '2017-01-22 10:21:26', '1', '2017-01-22 10:21:26');
INSERT INTO `com_dict_tree` VALUES ('19c85d8153d243c1ac36196a0dadfab3', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_02', '审核成功', '01', '00', '02', '1', '1488611187930', '01', '1', '2017-03-04 15:06:44', '1', '2017-03-04 15:06:44');
INSERT INTO `com_dict_tree` VALUES ('1c0caa13e3514c87a3280f2f48653d83', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_02', 'Int', '01', '00', '02', '1', '1484018861734', '01', '1', '2017-01-10 11:27:42', '1', '2017-01-11 16:26:59');
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
INSERT INTO `com_dict_tree` VALUES ('3894f644c42e441aa1a605513f7c22c6', '0', 'bg_menuEffective', '后台生效菜单', '02', '00', 'bg_menuEffective', '0', '1484804259249', '01', '1', '2017-01-19 13:37:57', '1', '2017-01-19 13:51:05');
INSERT INTO `com_dict_tree` VALUES ('3d278f1466ed4b8998e1c198ea8ff770', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_media_id', '下发消息（除文本消息）', '01', '00', 'media_id', '1', '1486366199642', '01', '1', '2017-02-06 15:30:13', '1', '2017-02-06 15:30:13');
INSERT INTO `com_dict_tree` VALUES ('42293fbe78a649568d01da16ff66040f', 'b120815786714648a7247046fe7186d9', 'com_dictType_02', '数据库字典', '01', '00', '02', '1', '1484017474427', '01', '1', '2017-01-10 11:04:34', '1', '2017-01-10 11:04:34');
INSERT INTO `com_dict_tree` VALUES ('4324f08d02c943ab96e9078b01f703d8', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_04', '主从型（从）', '01', '00', '04', '1', '1484025928454', '01', '1', '2017-01-10 13:25:28', '1', '2017-01-10 13:25:28');
INSERT INTO `com_dict_tree` VALUES ('45c83e69651949d4b343dae672baed81', '0', 'com_supplierType', '供应商类型', '01', '00', 'com_supplierType', '0', '1487988818173', '01', '1', '2017-02-25 10:14:52', '1', '2017-02-25 10:14:52');
INSERT INTO `com_dict_tree` VALUES ('46d6fd8c6ccf4d8aa5c7c6c57b063b38', '78b4651dca85412095298b54c2d01128', 'com_productCategoryType_01', '商品类别', '01', '00', '01', '1', '1487989059313', '01', '1', '2017-02-25 10:19:35', '1', '2017-02-25 10:19:35');
INSERT INTO `com_dict_tree` VALUES ('4939f73cd85f4675a469bbbcf56f91dc', '1267d5aa01d440b1a8a686e9494eeece', 'com_appUserType_01', '后台添加', '01', '00', '01', '1', '1488524212184', '01', '1', '2017-03-03 14:57:52', '1', '2017-03-06 15:55:04');
INSERT INTO `com_dict_tree` VALUES ('4ac70dbc8b314418b232a6c7b70aa916', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_03', '4S点客户', '01', '00', '03', '1', '1488524425691', '01', '1', '2017-03-03 15:00:48', '1', '2017-03-06 15:59:47');
INSERT INTO `com_dict_tree` VALUES ('4cf11a0ce3954422962cc670638dabfa', '45c83e69651949d4b343dae672baed81', 'com_supplierType_01', '淘宝商家', '01', '00', '01', '1', '1487988914006', '01', '1', '2017-02-25 10:16:11', '1', '2017-02-25 10:16:11');
INSERT INTO `com_dict_tree` VALUES ('4ed0b868ed2b4d69887bcef4932eb4ef', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_05', 'Dict', '01', '00', '05', '1', '1484019114333', '01', '1', '2017-01-10 11:31:54', '1', '2017-01-11 16:27:15');
INSERT INTO `com_dict_tree` VALUES ('540b4214c9ea4927867b1a878365ec9c', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_wx', 'weixin', '01', '00', 'wx', '1', '1484018416335', '01', '1', '2017-01-10 11:20:16', '1', '2017-01-10 11:20:16');
INSERT INTO `com_dict_tree` VALUES ('54467b4e2dc84c1ca9e21828803eb9b8', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_04', 'Double', '01', '00', '04', '1', '1484019019767', '01', '1', '2017-01-10 11:30:20', '1', '2017-01-11 16:27:10');
INSERT INTO `com_dict_tree` VALUES ('545f7d1209cb407785abca0aa597512b', '78b4651dca85412095298b54c2d01128', 'com_productCategoryType_02', '商品模块', '01', '00', '02', '1', '1487989181508', '01', '1', '2017-02-25 10:20:25', '1', '2017-02-25 10:20:25');
INSERT INTO `com_dict_tree` VALUES ('557fcb9b45c3430d93822cfb4863b05d', '2b6adc1e3f5c48fd8f0778d0bbed13c3', 'com_sex_01', '男', '01', '00', '01', '1', '1488790685296', '01', '1', '2017-03-06 16:58:19', '1', '2017-03-06 16:58:19');
INSERT INTO `com_dict_tree` VALUES ('55be1a6cf32041b9addd3aa8ac43d52c', '0', 'com_productEffective', '生效商品', '02', '00', 'com_productEffective', '0', '1489050565760', '01', '1', '2017-03-09 17:12:25', '1', '2017-03-09 17:14:46');
INSERT INTO `com_dict_tree` VALUES ('572053db6aa44c4ba95d1b73accaad0c', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_location_select', '弹出地理位置选择器', '01', '00', 'location_select', '1', '1486366184718', '01', '1', '2017-02-06 15:29:54', '1', '2017-02-06 15:29:54');
INSERT INTO `com_dict_tree` VALUES ('609bbe934cd24f7588af2f717a8d49f3', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_03', 'Time', '01', '00', '03', '1', '1484019001039', '01', '1', '2017-01-10 11:30:01', '1', '2017-01-11 16:27:04');
INSERT INTO `com_dict_tree` VALUES ('66e83e268bd24bb9b82187c00892d832', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_scancode_push', '扫码推事件', '01', '00', 'scancode_push', '1', '1486366082814', '01', '1', '2017-02-06 15:28:14', '1', '2017-02-06 15:28:14');
INSERT INTO `com_dict_tree` VALUES ('6d58e343386e47afbf79fb2dbc8c7d76', 'b120815786714648a7247046fe7186d9', 'com_dictType_01', '参数字典', '01', '00', '01', '1', '1484017427635', '01', '1', '2017-01-10 11:03:48', '1', '2017-01-10 11:03:48');
INSERT INTO `com_dict_tree` VALUES ('768e01fdd0b9450080a6b0f97a626eae', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_click', '点击推事件', '01', '00', 'click', '1', '1486365867381', '01', '1', '2017-02-06 15:24:59', '1', '2017-02-06 15:24:59');
INSERT INTO `com_dict_tree` VALUES ('78b4651dca85412095298b54c2d01128', '0', 'com_productCategoryType', '商品分类类型', '01', '00', 'com_productCategoryType', '0', '1487989011726', '01', '1', '2017-02-25 10:17:34', '1', '2017-02-25 10:17:34');
INSERT INTO `com_dict_tree` VALUES ('79750bdf92c840e6bcc2fe2cb25cfda5', 'a60fe6c08d7c4708bca2a20cb2feb681', 'bg_roleType_01', '一般角色', '01', '00', '01', '1', '1484804728835', '01', '1', '2017-01-19 13:46:17', '1', '2017-01-19 13:46:17');
INSERT INTO `com_dict_tree` VALUES ('7a4aeb77544c47368075cb7199d42bcb', '0', 'com_sparepartDealStatus', '零配件订单审核状态', '01', '00', 'com_sparepartDealStatus', '0', '1488611074477', '01', '1', '2017-03-04 15:05:15', '1', '2017-03-04 15:05:15');
INSERT INTO `com_dict_tree` VALUES ('7c901ea2b8694c69a2f21cc8ad9a7cb8', 'a39cce16086341e4b6a756e1fe00f104', 'com_curType_01', '人民币', '01', '00', '01', '1', '1489137981117', '01', '1', '2017-03-10 17:26:37', '1', '2017-03-10 17:26:37');
INSERT INTO `com_dict_tree` VALUES ('7faad10eb0424cf29a51fb5add0f1b50', '28510c12e9834ea29bbb5621f99e804e', 'bg_mapleDetailType_01', 'String', '01', '00', '01', '1', '1484018802176', '01', '1', '2017-01-10 11:26:42', '1', '2017-01-11 16:26:54');
INSERT INTO `com_dict_tree` VALUES ('808f87b42557462f822c8d82954a8481', '0', 'com_appUserRole', '平台用户角色', '01', '00', 'com_integralCustomerRole', '0', '1488524362820', '01', '1', '2017-03-03 14:59:41', '1', '2017-03-06 16:00:18');
INSERT INTO `com_dict_tree` VALUES ('894b66f11f8e44a79ac7f111cd886530', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_02', '小区经理', '01', '00', '02', '1', '1488524406401', '01', '1', '2017-03-03 15:00:24', '1', '2017-03-06 15:59:41');
INSERT INTO `com_dict_tree` VALUES ('8a3b081ef7684f86b676b3bd97417c77', '0', 'com_productStyleEffective', '生效商品规格', '02', '00', 'com_productStyleEffective', '0', '1489050954620', '01', '1', '2017-03-09 17:16:08', '1', '2017-03-09 17:16:31');
INSERT INTO `com_dict_tree` VALUES ('8b4b8a9f246a4204b8c6029a275d0102', 'a39cce16086341e4b6a756e1fe00f104', 'com_curType_02', '积分', '01', '00', '02', '1', '1489137998847', '01', '1', '2017-03-10 17:27:16', '1', '2017-03-10 17:27:16');
INSERT INTO `com_dict_tree` VALUES ('8ea0f054459f4e619c8a75bfd90f67bb', 'a60fe6c08d7c4708bca2a20cb2feb681', 'bg_roleType_00', '系统管理员角色', '01', '00', '00', '1', '1484804778802', '01', '1', '2017-01-19 13:46:56', '1', '2017-01-19 13:46:56');
INSERT INTO `com_dict_tree` VALUES ('8f1b49981a4d449383bfb703c0176bd7', '351a934eb7654c2db753fc51b39d3e16', 'bg_userType_01', '录入用户', '01', '00', '01', '1', '1484804518887', '01', '1', '2017-01-19 13:42:43', '1', '2017-01-19 13:42:43');
INSERT INTO `com_dict_tree` VALUES ('905cdbb48e804421848a656750619764', '0', 'com_productCustomTypeEffective', '生效商品自定义类型', '02', '00', 'com_productCustomTypeEffective', '0', '1489048359818', '01', '1', '2017-03-09 16:32:53', '1', '2017-03-09 17:12:09');
INSERT INTO `com_dict_tree` VALUES ('916b2c8543b94d488d80a5a08f05b681', '0', 'bg_roleEffective', '后台生效角色', '02', '00', 'bg_roleEffective', '0', '1484804220171', '01', '1', '2017-01-19 13:37:22', '1', '2017-01-19 13:37:37');
INSERT INTO `com_dict_tree` VALUES ('9425b3d5bd8c477fb090bb1cdad1f76d', '0', 'com_styleCategoryEffectiveP', '生效商品规格明细分类', '02', '00', 'com_styleCategoryEffectiveP', '0', '1489134159206', '01', '1', '2017-03-10 16:22:53', '1', '2017-03-10 16:22:53');
INSERT INTO `com_dict_tree` VALUES ('97eb8171437447fc8714a8c1a2075b9a', '30a83d0bc4364ac39edb8d0f5786a338', 'bg_crontabType_01', '数据表备份', '01', '00', '01', '1', '1485051549949', '01', '1', '2017-01-22 10:19:54', '1', '2017-01-22 10:19:54');
INSERT INTO `com_dict_tree` VALUES ('9ede63687cce41c380773ba1917519c9', 'fc97312d70a84af186a000882941e5fc', 'com_sparepartType_01', '朴易汽车零部件', '01', '00', '01', '1', '1488529563651', '01', '1', '2017-03-03 16:26:45', '1', '2017-03-03 16:26:45');
INSERT INTO `com_dict_tree` VALUES ('a39cce16086341e4b6a756e1fe00f104', '0', 'com_curType', '货币种类', '01', '00', 'com_curType', '0', '1489137953268', '01', '1', '2017-03-10 17:26:05', '1', '2017-03-10 17:26:05');
INSERT INTO `com_dict_tree` VALUES ('a60fe6c08d7c4708bca2a20cb2feb681', '0', 'bg_roleType', '后台角色类型', '01', '00', 'bg_roleType', '0', '1484804620997', '01', '1', '2017-01-19 13:45:08', '1', '2017-01-19 13:45:08');
INSERT INTO `com_dict_tree` VALUES ('b120815786714648a7247046fe7186d9', '0', 'com_dictType', '字典类型', '01', '00', 'com_dictType', '0', '1484017251117', '01', '1', '2017-01-10 11:00:51', '1', '2017-02-25 10:10:45');
INSERT INTO `com_dict_tree` VALUES ('b20d74e13ced4513b1ac20c4ead6d736', '319bbf4a475b4aa4b852c30d91d9c48c', 'com_wxAccountType_01', '服务号', '01', '00', '01', '1', '1484896386061', '01', '1', '2017-01-20 15:14:06', '1', '2017-01-20 15:14:06');
INSERT INTO `com_dict_tree` VALUES ('b4bf5552e7454647b535c372a422c069', '0', 'com_appUserEffective', '生效平台用户', '02', '00', 'com_appUserEffective', '0', '1488607885253', '01', '1', '2017-03-04 14:12:55', '1', '2017-03-07 09:49:13');
INSERT INTO `com_dict_tree` VALUES ('bc443acf9b7f48fca6b44934d79a7c99', '0', 'com_sparepartEffective', '生效零配件', '02', '00', 'com_sparepartEffective', '0', '1488608049859', '01', '1', '2017-03-04 14:28:20', '1', '2017-03-04 14:37:10');
INSERT INTO `com_dict_tree` VALUES ('c3731311fb334e4697c2fb76ab1343be', '2b6adc1e3f5c48fd8f0778d0bbed13c3', 'com_sex_02', '女', '01', '00', '02', '1', '1488790700615', '01', '1', '2017-03-06 16:58:40', '1', '2017-03-06 16:58:40');
INSERT INTO `com_dict_tree` VALUES ('c4e773c1a20f45788c12789267d6065b', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_pic_sysphoto', '弹出系统拍照发图', '01', '00', 'pic_sysphoto', '1', '1486366136184', '01', '1', '2017-02-06 15:29:07', '1', '2017-02-06 15:29:07');
INSERT INTO `com_dict_tree` VALUES ('cef79c2b540147418eb37ab3a3c31272', '0', 'com_productTypeEffective', '生效商品类别', '02', '00', 'com_productTypeEffective', '0', '1489047547471', '01', '1', '2017-03-09 16:20:19', '1', '2017-03-09 16:49:52');
INSERT INTO `com_dict_tree` VALUES ('d29a2f6aab6d4d21babbfc338d90d155', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_03', '审核失败', '01', '00', '03', '1', '1488611205295', '01', '1', '2017-03-04 15:06:59', '1', '2017-03-04 15:06:59');
INSERT INTO `com_dict_tree` VALUES ('d40e20a567a34f04b9af55db0f28474e', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_view', '跳转URL', '01', '00', 'view', '1', '1486366048828', '01', '1', '2017-02-06 15:27:52', '1', '2017-02-06 15:27:52');
INSERT INTO `com_dict_tree` VALUES ('d77946d83bed4b1686396e78d002cee0', '0', 'com_appUserEffective4S', '生效平台用户（4S店）', '02', '00', 'com_appUserEffective4S', '0', '1488608002089', '01', '1', '2017-03-04 14:13:59', '1', '2017-03-07 09:52:33');
INSERT INTO `com_dict_tree` VALUES ('d8374ef4d31541a38ea946aa7c243c8a', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_00', '菜单分目', '01', '00', '00', '1', '1486365867380', '01', '1', '2017-02-06 16:50:35', '1', '2017-02-06 16:50:35');
INSERT INTO `com_dict_tree` VALUES ('d8cf8becf1db4418a098a43500d5cf3f', '0', 'com_packageType', '模块包分类', '01', '00', 'com_packageType', '0', '1484017801545', '01', '1', '2017-01-10 11:10:02', '1', '2017-01-10 11:10:02');
INSERT INTO `com_dict_tree` VALUES ('da15415d7faa43c9abb31701350d0958', '0', 'bg_wxMenuBtnType', '菜单按钮类型', '01', '00', 'bg_wxMenuBtnType', '0', '1486365490238', '01', '1', '2017-02-06 15:19:12', '1', '2017-02-06 15:19:12');
INSERT INTO `com_dict_tree` VALUES ('dee79448e4e546b08bf6c56af9bb8efc', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_01', '大区经理', '01', '00', '01', '1', '1488524385790', '01', '1', '2017-03-03 15:00:05', '1', '2017-03-06 15:59:35');
INSERT INTO `com_dict_tree` VALUES ('e2a07b8b995449dbb70d4e59cb189ea3', '808f87b42557462f822c8d82954a8481', 'com_appUserRole_00', '普通用户', '01', '00', '00', '1', '1488787227291', '01', '1', '2017-03-06 16:00:52', '1', '2017-03-06 16:00:52');
INSERT INTO `com_dict_tree` VALUES ('e385f5a7938143f4b4fe8e88c9771cd5', '45c83e69651949d4b343dae672baed81', 'com_supplierType_02', '实体店家', '01', '00', '02', '1', '1487988972913', '01', '1', '2017-02-25 10:16:43', '1', '2017-02-25 10:16:43');
INSERT INTO `com_dict_tree` VALUES ('e387c8a8f2364d9798fdb323179d034c', '0', 'com_supplierEffective', '生效供应商', '02', '00', 'com_supplierEffective', '0', '1489047419924', '01', '1', '2017-03-09 16:17:32', '1', '2017-03-09 16:53:24');
INSERT INTO `com_dict_tree` VALUES ('e389a2810d0d45a1ba4596c679e7a544', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_pic_photo_or_album', '弹出拍照或者相册发图', '01', '00', 'pic_photo_or_album', '1', '1486366153961', '01', '1', '2017-02-06 15:29:24', '1', '2017-02-06 15:29:24');
INSERT INTO `com_dict_tree` VALUES ('e53230ed682741f4ad63e470e0d86488', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_mb', 'mobile', '01', '00', 'mb', '1', '1484018478585', '01', '1', '2017-01-10 11:21:19', '1', '2017-01-10 11:21:33');
INSERT INTO `com_dict_tree` VALUES ('e67e77715a7c41dd9acda383e6bbe8b3', '7a4aeb77544c47368075cb7199d42bcb', 'com_sparepartDealStatus_01', '待审核', '01', '00', '01', '1', '1488611166327', '01', '1', '2017-03-04 15:06:26', '1', '2017-03-04 15:06:26');
INSERT INTO `com_dict_tree` VALUES ('e69288a45145429c8c3a865024d4b83c', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_02', '树形', '01', '00', '02', '1', '1484025846486', '01', '1', '2017-01-10 13:24:06', '1', '2017-01-10 13:24:06');
INSERT INTO `com_dict_tree` VALUES ('ec677b163d7448f8a1b050829917f657', '30c39f10c07e4b3b905734277e527eee', 'com_sf_00', '否', '01', '00', '00', '1', '1484536554576', '01', '1', '2017-01-16 11:16:26', '1', '2017-01-16 11:18:34');
INSERT INTO `com_dict_tree` VALUES ('ee9a01bda113406cb6f8eed7c25c125d', '0', 'com_productModelEffective', '生效商品模型', '02', '00', 'com_productModelEffective', '0', '1489047624518', '01', '1', '2017-03-09 16:21:40', '1', '2017-03-09 16:50:00');
INSERT INTO `com_dict_tree` VALUES ('f0cde8fcc255428fae4cceb7f1a3abf1', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_bg', 'background', '01', '00', 'bg', '1', '1484018374289', '01', '1', '2017-01-10 11:19:34', '1', '2017-01-10 11:19:34');
INSERT INTO `com_dict_tree` VALUES ('f29b6b4266b54c67a3d9f629abc95560', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_com', 'common', '01', '00', 'com', '1', '1484017926047', '01', '1', '2017-01-10 11:12:06', '1', '2017-01-10 11:13:22');
INSERT INTO `com_dict_tree` VALUES ('f2b727ee2e654654bd91363660fe2197', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_03', '主从型（主）', '01', '00', '03', '1', '1484025903963', '01', '1', '2017-01-10 13:25:04', '1', '2017-01-10 13:25:04');
INSERT INTO `com_dict_tree` VALUES ('f413663c92f94f8dbae5825839a23cbd', 'da15415d7faa43c9abb31701350d0958', 'bg_wxMenuBtnType_view_limited', '跳转图文消息URL', '01', '00', 'view_limited', '1', '1486366219563', '01', '1', '2017-02-06 15:30:31', '1', '2017-02-06 15:30:31');
INSERT INTO `com_dict_tree` VALUES ('f4377b4baf504d5a98eedbf4cb65a46e', '2c43919dfe434841a92bc2a39b2ad02e', 'bg_menuType_01', '系统菜单', '01', '00', '01', '1', '1484555346406', '01', '1', '2017-01-16 16:29:48', '1', '2017-01-16 16:29:48');
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
INSERT INTO `com_integral_note_info` VALUES ('04d3a51462da4d68a5fbd263b01e9a8d', 'ca72a60054b84c8cbc399da8512b3a3b', '陈华出售“全新Jeep自由侠主题摄影包*435345”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '6530175.00', null, null, '1488942530764', '01', '1', '2017-03-08 11:08:51', '1', '2017-03-08 11:08:51');
INSERT INTO `com_integral_note_info` VALUES ('115e0aed5fbd4bd4b9e44d0acd11d092', '957f5c0bd83c45279f0fabc8506907e1', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '3075.00', null, null, '1488941727887', '01', '1', '2017-03-08 10:55:28', '1', '2017-03-08 10:55:28');
INSERT INTO `com_integral_note_info` VALUES ('117b1f3933a4468db344eae79cb41653', 'f2cb57ce9cc4415da7e5715a66de8c4e', '陈华出售“全新Jeep自由侠主题摄影包*123”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '9840.00', null, null, '1488943533931', '01', '1', '2017-03-08 11:25:34', '1', '2017-03-08 11:25:34');
INSERT INTO `com_integral_note_info` VALUES ('1607598a0a254b1a8fe94d106d49d168', 'e861a910b1b84f0b9b51996d1407028f', '陈华出售“Jeep专用后备箱固定网*32342”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '808550.00', null, null, '1488942072575', '01', '1', '2017-03-08 11:01:13', '1', '2017-03-08 11:01:13');
INSERT INTO `com_integral_note_info` VALUES ('186dc35e9d224b3d87bac299fa0af746', 'decf653fc5604c188845376edf567530', '陈华出售“Jeep专用后备箱固定网*324”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '8100.00', null, null, '1488941996069', '01', '1', '2017-03-08 10:59:56', '1', '2017-03-08 10:59:56');
INSERT INTO `com_integral_note_info` VALUES ('1a1ceb831f13466ab752f2ec76235b5a', '957f5c0bd83c45279f0fabc8506907e1', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '49200.00', null, null, '1488941727865', '01', '1', '2017-03-08 10:55:28', '1', '2017-03-08 10:55:28');
INSERT INTO `com_integral_note_info` VALUES ('20e176db33914b68b50efca854510dc5', 'ca72a60054b84c8cbc399da8512b3a3b', '陈华出售“全新Jeep自由侠主题摄影包*435345”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '34827600.00', null, null, '1488942530642', '01', '1', '2017-03-08 11:08:51', '1', '2017-03-08 11:08:51');
INSERT INTO `com_integral_note_info` VALUES ('22380d25d51549a6b537e7181779c1d5', 'e861a910b1b84f0b9b51996d1407028f', '陈华出售“Jeep专用后备箱固定网*32342”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '12936800.00', null, null, '1488942072552', '01', '1', '2017-03-08 11:01:13', '1', '2017-03-08 11:01:13');
INSERT INTO `com_integral_note_info` VALUES ('29d935a7db4c44adb63ded9f0ade82fc', '4d2202943d204e5e96f7dca3ffacbceb', '陈华出售“全新Jeep自由侠主题摄影包*213”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '3195.00', null, null, '1488942651770', '01', '1', '2017-03-08 11:10:52', '1', '2017-03-08 11:10:52');
INSERT INTO `com_integral_note_info` VALUES ('2bf7497393384fc1a31ff53db8623b10', '1a78e465144649d29dc08de86d473642', '陈华出售“全新Jeep自由侠主题摄影包*5000”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '25000.00', null, null, '1488941565480', '01', '1', '2017-03-08 10:52:45', '1', '2017-03-08 10:52:45');
INSERT INTO `com_integral_note_info` VALUES ('2e147217ccd645f9aa99e0b4a6f1ddcd', 'f2cb57ce9cc4415da7e5715a66de8c4e', '陈华出售“全新Jeep自由侠主题摄影包*123”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '1845.00', null, null, '1488943534025', '01', '1', '2017-03-08 11:25:34', '1', '2017-03-08 11:25:34');
INSERT INTO `com_integral_note_info` VALUES ('35c45c91dbd443eb8ed899b8438dd477', 'eddd9dab4750428698f12bbcec8c8428', '陈华出售“Jeep专用后备箱固定网*234”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '5850.00', null, null, '1488942277173', '01', '1', '2017-03-08 11:04:37', '1', '2017-03-08 11:04:37');
INSERT INTO `com_integral_note_info` VALUES ('432bb2400ba84046b1ba0af8ad4d4b19', '245f097042d04815897996776ad95a7d', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '9225.00', null, null, '1488942927228', '01', '1', '2017-03-08 11:15:27', '1', '2017-03-08 11:15:27');
INSERT INTO `com_integral_note_info` VALUES ('456548f354ea4ca59e67860ace34652a', '7cd45c0ea37d498ea36ec067f7dd7cf4', '陈华出售“Jeep专用后备箱固定网*12312”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '4924800.00', null, null, '1488942403054', '01', '1', '2017-03-08 11:06:43', '1', '2017-03-08 11:06:43');
INSERT INTO `com_integral_note_info` VALUES ('496255a278d14072a3403d51234679a5', '1a78e465144649d29dc08de86d473642', '陈华出售“全新Jeep自由侠主题摄影包*5000”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '400000.00', null, null, '1488941565419', '01', '1', '2017-03-08 10:52:45', '1', '2017-03-08 10:52:45');
INSERT INTO `com_integral_note_info` VALUES ('4fd64312f27e453784ad7f30d3588aab', 'e861a910b1b84f0b9b51996d1407028f', '陈华出售“Jeep专用后备箱固定网*32342”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '2425650.00', null, null, '1488942072567', '01', '1', '2017-03-08 11:01:13', '1', '2017-03-08 11:01:13');
INSERT INTO `com_integral_note_info` VALUES ('540ca569cc2b43c4ac7ede0e3abd2ab7', 'a4d6b6c65ecd412593d33142a0e2af1c', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '3075.00', null, null, '1488941769790', '01', '1', '2017-03-08 10:56:10', '1', '2017-03-08 10:56:10');
INSERT INTO `com_integral_note_info` VALUES ('5c7a039f9f4d4867a1afc8764dfd447f', 'ca72a60054b84c8cbc399da8512b3a3b', '陈华出售“全新Jeep自由侠主题摄影包*435345”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '2176725.00', null, null, '1488942530767', '01', '1', '2017-03-08 11:08:51', '1', '2017-03-08 11:08:51');
INSERT INTO `com_integral_note_info` VALUES ('616b0705c61f45e9b37140f156a79c78', '957f5c0bd83c45279f0fabc8506907e1', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '9225.00', null, null, '1488941727883', '01', '1', '2017-03-08 10:55:28', '1', '2017-03-08 10:55:28');
INSERT INTO `com_integral_note_info` VALUES ('70f6d67e01a3485e8b23fe751a0f1560', '7cd45c0ea37d498ea36ec067f7dd7cf4', '陈华出售“Jeep专用后备箱固定网*12312”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '307800.00', null, null, '1488942403069', '01', '1', '2017-03-08 11:06:43', '1', '2017-03-08 11:06:43');
INSERT INTO `com_integral_note_info` VALUES ('74c5d906951646a0826042a7a2da7af1', 'decf653fc5604c188845376edf567530', '陈华出售“Jeep专用后备箱固定网*324”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '24300.00', null, null, '1488941996023', '01', '1', '2017-03-08 10:59:56', '1', '2017-03-08 10:59:56');
INSERT INTO `com_integral_note_info` VALUES ('7d8eae204922487ba0425c34235bdffb', 'a4d6b6c65ecd412593d33142a0e2af1c', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '49200.00', null, null, '1488941769746', '01', '1', '2017-03-08 10:56:10', '1', '2017-03-08 10:56:10');
INSERT INTO `com_integral_note_info` VALUES ('896327634ae04c199cc44e758704ecc7', '245f097042d04815897996776ad95a7d', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '49200.00', null, null, '1488942927221', '01', '1', '2017-03-08 11:15:27', '1', '2017-03-08 11:15:27');
INSERT INTO `com_integral_note_info` VALUES ('8e9ffb6234f646b893df7c670b67b031', '245f097042d04815897996776ad95a7d', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '3075.00', null, null, '1488942927231', '01', '1', '2017-03-08 11:15:27', '1', '2017-03-08 11:15:27');
INSERT INTO `com_integral_note_info` VALUES ('9dfd7a88f9d3415ea574f2f3d064a673', 'de0858c5f6ab41dabac62999059b1149', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '3075.00', null, null, '1488942359526', '01', '1', '2017-03-08 11:06:00', '1', '2017-03-08 11:06:00');
INSERT INTO `com_integral_note_info` VALUES ('b4aabc04b72645e7b2eada5d5ded1b72', '4d2202943d204e5e96f7dca3ffacbceb', '陈华出售“全新Jeep自由侠主题摄影包*213”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '1065.00', null, null, '1488942651775', '01', '1', '2017-03-08 11:10:52', '1', '2017-03-08 11:10:52');
INSERT INTO `com_integral_note_info` VALUES ('b9220a16b274446ab38d3dd54b96ac7d', '4d2202943d204e5e96f7dca3ffacbceb', '陈华出售“全新Jeep自由侠主题摄影包*213”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '17040.00', null, null, '1488942651758', '01', '1', '2017-03-08 11:10:52', '1', '2017-03-08 11:10:52');
INSERT INTO `com_integral_note_info` VALUES ('bc4240456b5847f0a995bf0362dc4be0', 'de0858c5f6ab41dabac62999059b1149', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '9225.00', null, null, '1488942359522', '01', '1', '2017-03-08 11:06:00', '1', '2017-03-08 11:06:00');
INSERT INTO `com_integral_note_info` VALUES ('c5f559ba77c24066b3ef7131ff8006ff', '7cd45c0ea37d498ea36ec067f7dd7cf4', '陈华出售“Jeep专用后备箱固定网*12312”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '923400.00', null, null, '1488942403064', '01', '1', '2017-03-08 11:06:43', '1', '2017-03-08 11:06:43');
INSERT INTO `com_integral_note_info` VALUES ('d513efebddcd4bf9947014d94756a046', 'decf653fc5604c188845376edf567530', '陈华出售“Jeep专用后备箱固定网*324”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '129600.00', null, null, '1488941996007', '01', '1', '2017-03-08 10:59:56', '1', '2017-03-08 10:59:56');
INSERT INTO `com_integral_note_info` VALUES ('d6d229469469400db23d1dcc28141452', 'eddd9dab4750428698f12bbcec8c8428', '陈华出售“Jeep专用后备箱固定网*234”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '17550.00', null, null, '1488942277169', '01', '1', '2017-03-08 11:04:37', '1', '2017-03-08 11:04:37');
INSERT INTO `com_integral_note_info` VALUES ('db06aaa9d58d4af5944158f692692944', 'f2cb57ce9cc4415da7e5715a66de8c4e', '陈华出售“全新Jeep自由侠主题摄影包*123”获得积分', '01', '00', '523f04fe17124aeb85d80b67387efc8b', '615.00', null, null, '1488943534029', '01', '1', '2017-03-08 11:25:34', '1', '2017-03-08 11:25:34');
INSERT INTO `com_integral_note_info` VALUES ('dda8663f685a467b85e9db238df7d697', 'eddd9dab4750428698f12bbcec8c8428', '陈华出售“Jeep专用后备箱固定网*234”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '93600.00', null, null, '1488942277053', '01', '1', '2017-03-08 11:04:37', '1', '2017-03-08 11:04:37');
INSERT INTO `com_integral_note_info` VALUES ('ddd2b6bdac86476f99a4155eb22208bc', 'a4d6b6c65ecd412593d33142a0e2af1c', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '9225.00', null, null, '1488941769786', '01', '1', '2017-03-08 10:56:10', '1', '2017-03-08 10:56:10');
INSERT INTO `com_integral_note_info` VALUES ('f01cbfbb3d9843f8bcce65185bbe81e6', '1a78e465144649d29dc08de86d473642', '陈华出售“全新Jeep自由侠主题摄影包*5000”获得积分', '01', '00', 'b46d1c1881724aa2b4e35314847f4f5a', '75000.00', null, null, '1488941565475', '01', '1', '2017-03-08 10:52:45', '1', '2017-03-08 10:52:45');
INSERT INTO `com_integral_note_info` VALUES ('fd8c3b9f7e744ae09e981273691b047b', 'de0858c5f6ab41dabac62999059b1149', '陈华出售“Jeep专用后备箱固定网*123”获得积分', '01', '00', '7b550a0b71ef4927a4cacf86ec82e7c3', '49200.00', null, null, '1488942359444', '01', '1', '2017-03-08 11:05:59', '1', '2017-03-08 11:05:59');

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
-- Table structure for com_product_category_info
-- ----------------------------
DROP TABLE IF EXISTS `com_product_category_info`;
CREATE TABLE `com_product_category_info` (
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
-- Records of com_product_category_info
-- ----------------------------
INSERT INTO `com_product_category_info` VALUES ('1245ed0a5756448aab614c9c16f1b7cb', 'splb0000004', '私房菜的', '01', '00', 'uploadFiles/image/productCategory/headimg/ff2269d850114640afe9f8311249d429.jpg', 'uploadFiles/image/productCategory/img1/4016182aaea34bb7b66195902d55cf56.jpg_maple,uploadFiles/image/productCategory/img1/3bf8b0b27206497c9c6010dcc85a101e.jpg_maple,uploadFiles/image/productCategory/img1/209a391212054ead8b18f821d4ff8bbe.jpg', 'uploadFiles/image/productCategory/img2/c2cb7b09ed694ba29414e05fb4f77468.jpg_maple,uploadFiles/image/productCategory/img2/27c32f19ae424707aad31b9c2de92029.jpg_maple,uploadFiles/image/productCategory/img2/8be0a2cf409d462da73c1f42bde4a6e8.jpg_maple,uploadFiles/image/productCategory/img2/75c67e470acc43958192963df5a3349b.jpg', '打算', '1489046401841', '01', '1', '2017-03-09 16:00:29', '1', '2017-03-09 16:00:29');
INSERT INTO `com_product_category_info` VALUES ('7ed22c6fa8a44d4eaa8f62df8d340b35', 'spmk0000001', '萨达', '02', '00', 'uploadFiles/image/productCategory/headimg/67c4cd91877744faa39888f35e0003c9.jpg', 'uploadFiles/image/productCategory/img1/b9d2130140b741c09452243f4c66afd9.jpg_maple,uploadFiles/image/productCategory/img1/6a5578028f8e4792bf698e66735448ef.jpg_maple,uploadFiles/image/productCategory/img1/e872953f296f48e5b3cc5c16a13f0ac3.jpg', 'uploadFiles/image/productCategory/img2/cae7b8fba7d547e596141993a0e02397.jpg_maple,uploadFiles/image/productCategory/img2/63efaf332a86441d934e576150e94ed1.jpg_maple,uploadFiles/image/productCategory/img2/8196111a13b048b8951abbd095e8d2a2.jpg_maple,uploadFiles/image/productCategory/img2/3ae6a0ed7ba3449bb10743f85cc361fa.jpg', '大声道', '1489046620584', '01', '1', '2017-03-09 16:05:34', '1', '2017-03-09 16:05:34');
INSERT INTO `com_product_category_info` VALUES ('8ceef21e782d497b83674cac4ba58f12', 'splb0000001', '水果', '01', '00', 'uploadFiles/image/productCategory/headimg/d972469d0a03416db1452e37193beb0f.jpg', 'uploadFiles/image/productCategory/img1/66df2b155f514ce086419878db5a1595.jpg_maple,uploadFiles/image/productCategory/img1/dc30641ac6d34162a4fe271b018a508f.jpg_maple,uploadFiles/image/productCategory/img1/875d59da67624850962d971e3cc614d9.jpg', 'uploadFiles/image/productCategory/img2/fa42053260bf4f249b18970e95c6f91e.jpg_maple,uploadFiles/image/productCategory/img2/1dfcc488f3384b37b297cd9b58d8eac1.jpg_maple,uploadFiles/image/productCategory/img2/ea83be26d64e4bef87ee7f50531f7cf8.jpg_maple,uploadFiles/image/productCategory/img2/6c4c64a06412423593cda5270b5fbb01.jpg', '萨达', '1489044866150', '01', '1', '2017-03-09 15:34:44', '1', '2017-03-09 15:34:44');
INSERT INTO `com_product_category_info` VALUES ('a2ade0adc3be4df7affcea78301dce8b', 'spmk0000005', '撒大大', '02', '00', 'uploadFiles/image/productCategory/headimg/76cc860a82484a3fa21ec53e46b0eff3.jpg', 'uploadFiles/image/productCategory/img1/5a918db776c64659a192e4ff2621aaff.jpg_maple,uploadFiles/image/productCategory/img1/9afdc89bae97440394dd956be8ad5183.jpg_maple,uploadFiles/image/productCategory/img1/8af238118f5b4490b1dd6df60c2f2860.jpg', 'uploadFiles/image/productCategory/img2/1b2358f266ad42ce8410c76b7c905ba2.jpg_maple,uploadFiles/image/productCategory/img2/85b94da936e64da28fcbcbb6bdbedfcc.jpg_maple,uploadFiles/image/productCategory/img2/d72432f5d69e49d9afaba211dbcaab5e.jpg_maple,uploadFiles/image/productCategory/img2/d8642258f1ce4c10b240967e3f564ba5.jpg', '打算', '1489046993634', '01', '1', '2017-03-09 16:10:44', '1', '2017-03-09 16:10:44');
INSERT INTO `com_product_category_info` VALUES ('aebec986654e4799b7be2839992646e4', 'splb0000002', '阿萨德撒', '01', '00', 'uploadFiles/image/productCategory/headimg/967a185f83314880a7b20d9055e259bc.jpg', 'uploadFiles/image/productCategory/img1/01e47022a10842ea9b295b8c53bb88cb.jpg_maple,uploadFiles/image/productCategory/img1/c50ea25cd1df4674ae3c4fa2b4960d9f.jpg', 'uploadFiles/image/productCategory/img2/b667de92cd964500a80e2218bd606e96.jpg', '萨达', '1489046509440', '01', '1', '2017-03-09 16:02:10', '1', '2017-03-09 16:02:10');

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
INSERT INTO `com_product_info` VALUES ('878c5545d5b84441b0767c817f554de0', 'fb4291204f804be79b35e0cbb2ed952e', 'sp123456789', '打算的撒', '1245ed0a5756448aab614c9c16f1b7cb', '00', '7ed22c6fa8a44d4eaa8f62df8d340b35', '阿萨德撒', '阿萨德', 'uploadFiles/cache/bb65e3b7ced24d09a649465630d98391.jpg', 'uploadFiles/cache/b3610266cabc4961b303a1a8683d7c4a.jpg,uploadFiles/cache/9a0019cb9a674f20b9a391ef7f228f4a.jpg,uploadFiles/cache/91819c5eb38f4e909bde629895526487.jpg', 'uploadFiles/cache/c929b2d8cb7441269a267e96d625ab6c.jpg,uploadFiles/cache/6c6ea2a8520e47e4ab837caec1f88706.jpg,uploadFiles/cache/87b33facbb52444d86f895aa995e0362.jpg', 'uploadFiles/cache/dcd325a934de437b81d289cdaee73ca7.jpg,uploadFiles/cache/52ed5845121e4f7eb802add713069946.jpg,uploadFiles/cache/313460c6fb744c7cb9d9127440b4df18.jpg', '1489049618151', '01', '1', '2017-03-09 16:59:54', '1', '2017-03-09 16:59:54');
INSERT INTO `com_product_info` VALUES ('f0974b9fdf3e49078c4a3f8b2fe91f53', 'fb4291204f804be79b35e0cbb2ed952e', 'sp12364', 'qweqwe', '8ceef21e782d497b83674cac4ba58f12', '00', 'a2ade0adc3be4df7affcea78301dce8b', '撒旦撒旦', '额外企鹅但是答案是', 'uploadFiles/cache/63a77d92e2c345d999b50fa26cd85934.jpg', 'uploadFiles/cache/e89f1c359dfc4fef819476106e4ab35d.jpg', 'uploadFiles/cache/b6f53b324dd941c49bd89e6e90aaa2c5.jpg,uploadFiles/cache/9e9062de35cb4a0782668a21f1a8af33.jpg,uploadFiles/cache/3a7065a33e694c83bfcf99fc8d021c88.jpg,uploadFiles/cache/ffdd05b46af3451fb5afde93cce5dcea.jpg', 'uploadFiles/cache/5d530141945646d19663c5716c1abd1b.jpg,uploadFiles/cache/5da6d96b5ab04e72a2f4516eb4516ab6.jpg,uploadFiles/cache/2f82ca474db3463391655eadbede3732.jpg,uploadFiles/cache/c90ada3bb93b4540b6e105e421cf5d95.jpg', '1489050000001', '01', '1', '2017-03-09 17:00:46', '1', '2017-03-09 17:00:46');

-- ----------------------------
-- Table structure for com_product_style_info
-- ----------------------------
DROP TABLE IF EXISTS `com_product_style_info`;
CREATE TABLE `com_product_style_info` (
  `productStyleId` varchar(100) NOT NULL COMMENT '产品规格 主键id',
  `productStyleInitId` varchar(100) DEFAULT NULL COMMENT '产品规格初始id',
  `productId` varchar(100) DEFAULT NULL COMMENT '商品id',
  `productStyleCode` varchar(100) DEFAULT NULL COMMENT '产品规格代号',
  `productStyleName` varchar(100) DEFAULT NULL COMMENT '产品规格名称',
  `productStyleType` varchar(100) DEFAULT NULL COMMENT '产品规格类型',
  `productStyleStatus` varchar(100) DEFAULT NULL COMMENT '产品规格状态',
  `stockNum` int(100) DEFAULT NULL COMMENT '库存数量',
  `originalPrice` double(100,2) DEFAULT NULL COMMENT '原价',
  `curType` varchar(100) DEFAULT NULL COMMENT '货币种类',
  `discountRate` double(100,2) DEFAULT NULL COMMENT '折扣',
  `discountPrice` double(100,2) DEFAULT NULL COMMENT '折扣优惠',
  `currentPrice` double(100,2) DEFAULT NULL COMMENT '现价',
  `orderNum` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `effective` varchar(100) DEFAULT NULL COMMENT '有效标志',
  `createUserId` varchar(100) DEFAULT NULL COMMENT '创建人员id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` varchar(100) DEFAULT NULL COMMENT '修改人员id',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`productStyleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品规格';

-- ----------------------------
-- Records of com_product_style_info
-- ----------------------------

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
INSERT INTO `com_sparepart_deal_info` VALUES ('245f097042d04815897996776ad95a7d', '21312123213', '02', '7b550a0b71ef4927a4cacf86ec82e7c3', 'f1f4303f16dd43b187dc61827284619b', '123', '123.00', '2017-03-08 11:12:26', '1', '通过！', '2017-03-08 11:15:27', '1488942746078', '01', '1', '2017-03-08 11:12:37', '1', '2017-03-08 11:15:27');
INSERT INTO `com_sparepart_deal_info` VALUES ('f2cb57ce9cc4415da7e5715a66de8c4e', '21312', '02', '7b550a0b71ef4927a4cacf86ec82e7c3', 'be8106448cba4c1a99280474ec128239', '123', '21312.00', '2017-03-08 11:11:16', '1', '通过！', '2017-03-08 11:25:34', '1488942676181', '01', '1', '2017-03-08 11:11:22', '1', '2017-03-08 11:25:34');

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
INSERT INTO `com_sparepart_info` VALUES ('be8106448cba4c1a99280474ec128239', '59122252', '全新Jeep自由侠主题摄影包', '01', '00', '100.00', '5.00', '15.00', '80.00', '1488530544595', '01', '1', '2017-03-03 16:42:44', '1', '2017-03-03 16:42:44');
INSERT INTO `com_sparepart_info` VALUES ('f1f4303f16dd43b187dc61827284619b', '59122251', 'Jeep专用后备箱固定网', '01', '00', '500.00', '25.00', '75.00', '400.00', '1488530451150', '01', '1', '2017-03-03 16:41:15', '1', '2017-03-03 16:41:15');

-- ----------------------------
-- Table structure for com_style_category_tree
-- ----------------------------
DROP TABLE IF EXISTS `com_style_category_tree`;
CREATE TABLE `com_style_category_tree` (
  `styleCategoryId` varchar(100) NOT NULL COMMENT '规格分类 主键id',
  `parentId` varchar(100) NOT NULL COMMENT '上级 id',
  `productId` varchar(100) DEFAULT NULL COMMENT '产品编号',
  `styleCategoryName` varchar(100) DEFAULT NULL COMMENT '规格分类名称',
  `styleCategoryStatus` varchar(100) DEFAULT NULL COMMENT '规格分类状态',
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
INSERT INTO `com_style_category_tree` VALUES ('36032c7200c14d5aa223048f15ae53dc', '8027e07cf91e404c94183cf8cc5db73c', 'f0974b9fdf3e49078c4a3f8b2fe91f53', '重', '00', '00', '1489113008718', '01', '1', '2017-03-10 10:30:15', '1', '2017-03-10 10:30:15');
INSERT INTO `com_style_category_tree` VALUES ('50080c45ee9c48b390040a0536b5b0f7', 'efc49dfbe45347dc92b5fb3db7285d01', '878c5545d5b84441b0767c817f554de0', '很大', '00', '01', '1489112758100', '01', '1', '2017-03-10 10:26:07', '1', '2017-03-10 10:26:07');
INSERT INTO `com_style_category_tree` VALUES ('8027e07cf91e404c94183cf8cc5db73c', '0', 'f0974b9fdf3e49078c4a3f8b2fe91f53', '重量', '00', '00', '1489112829763', '01', '1', '2017-03-10 10:29:34', '1', '2017-03-10 10:29:34');
INSERT INTO `com_style_category_tree` VALUES ('896ace00ea4245eba01ca0b56b8e810a', 'f18c5dded0c0424f898643439efd3fc5', '878c5545d5b84441b0767c817f554de0', '黑', '00', '00', '1489112805477', '01', '1', '2017-03-10 10:26:52', '1', '2017-03-10 10:26:52');
INSERT INTO `com_style_category_tree` VALUES ('ca2484ffbb8e49809bf99528c4c4212c', 'f18c5dded0c0424f898643439efd3fc5', '878c5545d5b84441b0767c817f554de0', '白', '00', '00', '1489112812947', '01', '1', '2017-03-10 10:27:01', '1', '2017-03-10 10:27:01');
INSERT INTO `com_style_category_tree` VALUES ('ee41cf8120514549aff55d3edc89c698', '8027e07cf91e404c94183cf8cc5db73c', 'f0974b9fdf3e49078c4a3f8b2fe91f53', '很重', '00', '01', '1489112992520', '01', '1', '2017-03-10 10:30:08', '1', '2017-03-10 10:30:08');
INSERT INTO `com_style_category_tree` VALUES ('efc49dfbe45347dc92b5fb3db7285d01', '0', '878c5545d5b84441b0767c817f554de0', '型号', '00', '00', '1489112606027', '01', '1', '2017-03-10 10:24:03', '1', '2017-03-10 10:24:03');
INSERT INTO `com_style_category_tree` VALUES ('f18c5dded0c0424f898643439efd3fc5', '0', '878c5545d5b84441b0767c817f554de0', '颜色', '00', '00', '1489112778973', '01', '1', '2017-03-10 10:26:43', '1', '2017-03-10 10:26:43');
INSERT INTO `com_style_category_tree` VALUES ('f2b4794cff4c4e76be9defe6eb8a9445', 'efc49dfbe45347dc92b5fb3db7285d01', '878c5545d5b84441b0767c817f554de0', '大', '00', '01', '1489112741946', '01', '1', '2017-03-10 10:25:54', '1', '2017-03-10 10:25:54');

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
INSERT INTO `com_supplier_info` VALUES ('fb4291204f804be79b35e0cbb2ed952e', 'aaaa', '按时打算', '01', '00', '大声道', '1254265', '撒大声地', '1487989832367', '01', '1', '2017-02-25 10:31:11', '1', '2017-02-25 10:31:11');

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
INSERT INTO `com_wx_account_info` VALUES ('9828b1377557429c8cb449440291e042', 'giftOL', '在线送礼', '01', '01', 'wxf3a522044df3f3df', '2028ab8d1cdb5c5e918bfb2c3f4c47f5', 'jiangxiang', '1423462402', '3034adc8e3218f3f3be7bb27590c90ed', 'stOUr23SOmH7V34wuNfWiI8Gd7PTAAEU97-C8KYGADIitR3JN1UmBobe035k9bUE98R7gnefLNvooGzp8o-RC1MGWDnAziSNjcBKPKspQJwZXcRY8pUQcANipemvsUOMKTHhAHAOXL', 'kgt8ON7yVITDhtdwci0qeRNuinq5m4Y437sKzdeBHje6zacKnrb3jdzCNt3dw6ZQzUN2ai_fRtv3jkdTBQPrvQ', '1484896943192', '01', '1', '2017-01-20 15:26:49', 'wx', '2017-03-10 17:22:01');
