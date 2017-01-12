SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgMapleDetail
-- ----------------------------
DROP TABLE IF EXISTS `bgMapleDetail`;
CREATE TABLE `bgMapleDetail` (
 	`mapleDetailId` int(11) NOT NULL AUTO_INCREMENT COMMENT '代码生成详细id',
	`mapleDetailCode` varchar(50)
		 DEFAULT NULL  COMMENT '属性代号' ,	
	`mapleDetailName` varchar(50)
		 DEFAULT NULL  COMMENT '属性名称' ,	
	`mapleDetailType` varchar(50)
		 DEFAULT NULL  COMMENT '属性类型' ,	
	`mapleDetailCodeUpper` varchar(50)
		 DEFAULT NULL  COMMENT '代号' ,	
	`length` varchar(50)
		 DEFAULT NULL  COMMENT '长度' ,	
	`decimalLength` varchar(50)
		 DEFAULT NULL  COMMENT '小数长度' ,	
	`isEdit` varchar(50)
		 DEFAULT NULL  COMMENT '是否录入' ,	
	`isNull` varchar(50)
		 DEFAULT NULL  COMMENT '是否null' ,	
	`iskey` varchar(50)
		 DEFAULT NULL  COMMENT '是否null' ,
	`defaultValue` varchar(50)
		 DEFAULT NULL  COMMENT '默认值' ,	
	`mapleId` varchar(50)
		 DEFAULT NULL  COMMENT '代码生成id' ,	
	`orderNum` varchar(50)
		 DEFAULT NULL  COMMENT '排序编号' ,	
	`status` varchar(10)
		 DEFAULT NULL  COMMENT '状态标识' ,	
	`effective` varchar(10)
		 DEFAULT NULL  COMMENT '有效标识' ,	
	`createUserId` varchar(50)
		 DEFAULT NULL  COMMENT '创建人员id' ,	
	`createTime` datetime
		 DEFAULT NULL  COMMENT '创建时间' ,	
	`modifyUserId` varchar(50)
		 DEFAULT NULL  COMMENT '修改人员id' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`mapleDetailId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT = '代码生成详细';

-- ----------------------------
-- Records of bgmapledetail
-- ----------------------------
INSERT INTO `bgmapledetail` VALUES ('2', 'userCode', '用户代号', '01', 'UserCode', '255', '', '01', '01', '00', '', '3', '00001', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('3', 'userName', '用户名称', '01', 'UserName', '255', '', '01', '01', '00', '', '3', '00002', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('4', 'userType', '用户类型', '01', 'UserType', '50', '', '00', '01', '00', '', '3', '00003', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('5', 'userNum', '用户编号', '01', 'UserNum', '50', '', '01', '01', '00', '', '3', '00004', '', null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('6', 'userRights', '用户权限', '01', 'UserRights', '255', '', '00', '01', '00', '', '3', '00005', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('7', 'userIconSrc', '用户头像路径', '01', 'UserIconSrc', '255', '', '00', '01', '00', '', '3', '00006', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('8', 'password', '密码', '01', 'Password', '255', '', '01', '01', '00', '', '3', '00007', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('9', 'roleId', '角色编号', '01', 'RoleId', '100', '', '01', '01', '00', '', '3', '00008', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('10', 'lastLoginTime', '上次登录时间', '03', 'LastLoginTime', '', '', '00', '01', '00', '', '3', '00009', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('11', 'lastLoginIp', '上次登录Ip', '01', 'LastLoginIp', '100', '', '00', '01', '00', '', '3', '00011', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('12', 'email', '电子邮箱', '01', 'Email', '100', '', '01', '01', '00', '', '3', '00012', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('13', 'phone', '电话号码', '01', 'Phone', '11', '', '01', '01', '00', '', '3', '00013', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('14', 'remarks', '备注信息', '01', 'Remarks', '255', '', '01', '01', '00', '', '3', '00014', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('15', 'id', '主键id', '01', 'Id', '100', '', '00', '00', '01', '', '00000', '00015', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('16', 'code', '代号', '01', 'Code', '100', '', '01', '01', '00', '', '00000', '00016', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('17', 'name', '名称', '01', 'Name', '100', '', '01', '01', '00', '', '00000', '00017', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('18', 'type', '类型', '01', 'Type', '100', '', '01', '01', '00', '', '00000', '00018', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('19', 'status', '状态', '01', 'Status', '100', '', '00', '01', '00', '00', '00000', '00019', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('20', 'effective', '有效性', '01', 'Effective', '100', '', '00', '01', '00', '01', '00000', '00021', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('21', 'createUserId', '创建人员id', '01', 'CreateUserId', '100', '', '00', '01', '00', '', '00000', '00022', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('22', 'createTime', '创建时间', '03', 'CreateTime', '', '', '00', '01', '00', '', '00000', '00023', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('23', 'modifyUserId', '修改人员id', '01', 'ModifyUserId', '100', '', '00', '01', '00', '', '00000', '00024', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('24', 'modifyTime', '修改时间', '03', 'ModifyTime', '', '', '00', '01', '00', '', '00000', '00025', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('25', 'parentId', '上级id', '01', 'ParentId', '100', '', '00', '00', '01', '', '00000', '00026', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('26', 'orderNum', '排序编号', '01', 'OrderNum', '100', '', '00', '01', '00', '', '00000', '000209', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('63', 'dictCode', '数据字典代号', '01', 'DictCode', '100', '', '01', '01', '00', '', '10', '00016', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('64', 'dictName', '数据字典名称', '01', 'DictName', '100', '', '01', '01', '00', '', '10', '00017', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('65', 'dictType', '数据字典类型', '01', 'DictType', '100', '', '01', '01', '00', '', '10', '00018', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('66', 'dictStatus', '数据字典状态', '01', 'DictStatus', '100', '', '00', '01', '00', '\"00\"', '10', '20', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('67', 'effective', '有效性', '01', 'Effective', '100', '', '00', '01', '00', '\"01\"', '10', '995', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('68', 'createUserId', '创建人员id', '01', 'CreateUserId', '100', '', '00', '01', '00', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '10', '996', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('69', 'createTime', '创建时间', '03', 'CreateTime', '', '', '00', '01', '00', 'nowTime', '10', '997', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('70', 'modifyUserId', '修改人员id', '01', 'ModifyUserId', '100', '', '00', '01', '00', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '10', '998', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('71', 'modifyTime', '修改时间', '03', 'ModifyTime', '', '', '00', '01', '00', 'nowTime', '10', '999', null, null, null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('73', 'dictValue', '数据字典值', '01', 'DictValue', '100', '', '01', '01', '00', '', '10', '19', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('74', 'level', '级别', '02', 'Level', '10', '', '00', '01', '00', '\"0\".equals(parentId)?0:parentComDict.getLevel()+1', '10', '21', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('78', 'orderNum', '排序编号', '01', 'OrderNum', '100', '', '01', '01', '00', 'String.valueOf(nowTime.getTime())', '10', '994', '00', '01', null, null, null, null);

-- ----------------------------
-- Table structure for bgMaple
-- ----------------------------
DROP TABLE IF EXISTS `bgMaple`;
CREATE TABLE `bgMaple` (
 	`mapleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '代码生成id',
	`mapleCode` varchar(50)
		 DEFAULT NULL  COMMENT '代号' ,	
	`mapleName` varchar(50)
		 DEFAULT NULL  COMMENT '名称' ,	
	`mapleType` varchar(50)
		 DEFAULT NULL  COMMENT '类型' ,	
	`mapleCodeUpper` varchar(50)
		 DEFAULT NULL  COMMENT '代号' ,	
	`controllerPackage` varchar(50)
		 DEFAULT NULL  COMMENT '控制器包代号' ,	
	`entityPackage` varchar(50)
		 DEFAULT NULL  COMMENT '实体类包代号' ,	
	`mapleControllerUpper` varchar(150)
		 DEFAULT NULL  COMMENT '控制器中的代号（大写）' ,	
	`mapleControllerLower` varchar(150)
		 DEFAULT NULL  COMMENT '控制器中的代号（小写）' ,	
	`mapleEntityUpper` varchar(150)
		 DEFAULT NULL  COMMENT '实体类中的代号（大写）' ,	
	`mapleEntityLower` varchar(150)
		 DEFAULT NULL  COMMENT '实体类中的代号（小写）' ,	
	`tableCode` varchar(150)
		 DEFAULT NULL  COMMENT '数据表代号' ,	
	`orderNum` varchar(50)
		 DEFAULT NULL  COMMENT '排序编号' ,	
	`status` varchar(10)
		 DEFAULT NULL  COMMENT '状态标识' ,	
	`effective` varchar(10)
		 DEFAULT NULL  COMMENT '有效标识' ,	
	`createUserId` varchar(50)
		 DEFAULT NULL  COMMENT '新增人员' ,	
	`createTime` datetime
		 DEFAULT NULL  COMMENT '新增时间' ,	
	`modifyUserId` varchar(50)
		 DEFAULT NULL  COMMENT '修改人员id' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`mapleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT = '代码生成';

-- ----------------------------
-- Records of bgmaple
-- ----------------------------
INSERT INTO `bgmaple` VALUES ('3', 'user', '用户', '01', 'User', 'background', 'background', 'BgUser', 'bgUser', 'BgUser', 'bgUser', '', '', '', '', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmaple` VALUES ('10', 'dict', '数据字典', '02', 'Dict', 'background', 'common', 'BgDict', 'bgDict', 'ComDict', 'comDict', '', '', '', '', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');

-- ----------------------------
-- Table structure for bgUser
-- ----------------------------
DROP TABLE IF EXISTS `bgUser`;
CREATE TABLE `bgUser` (
 	`userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台用户表id',
	`userCode` varchar(50)
		 DEFAULT NULL  COMMENT '用户名' ,	
	`password` varchar(255)
		 DEFAULT NULL  COMMENT '密码' ,	
	`userName` varchar(50)
		 DEFAULT NULL  COMMENT '真实姓名' ,	
	`userRights` varchar(255)
		 DEFAULT NULL  COMMENT '用户权限' ,	
	`roleId` int(11)
		 DEFAULT NULL  COMMENT '角色id' ,	
	`lastLoginTime` datetime
		 DEFAULT NULL  COMMENT '最后登录时间' ,	
	`lastLoginIp` varchar(50)
		 DEFAULT NULL  COMMENT '最后登录IP' ,	
	`userIconSrc` varchar(255)
		 DEFAULT NULL  COMMENT '用户头像路径' ,	
	`userNumber` varchar(50)
		 DEFAULT NULL  COMMENT '用户编号' ,	
	`email` varchar(255)
		 DEFAULT NULL  COMMENT '电子邮箱' ,	
	`phone` varchar(50)
		 DEFAULT NULL  COMMENT '手机号码' ,	
	`status` varchar(50)
		 DEFAULT NULL  COMMENT '状态' ,	
	`remarks` varchar(255)
		 DEFAULT NULL  COMMENT '备注信息' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台用户表';

-- ----------------------------
-- Records of bgUser
-- ----------------------------
INSERT INTO `bgUser` VALUES ('1', 'admin', '802ad7f0bf5d54f941bd0a866d3fb6225af153a71d90e456b72a62e4a40008c9e8fc7a01b5d89121d90006f4628c95c4e5ba88ced2b958033791f663d97eed3a', 'FH', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2016-06-06 02:27:47', '127.0.0.1', 'static/ace/avatars/user.jpg', '001', 'QQ313596790@main.com', '18788888888', '0','zzzzzzzzzzz', '2016-06-06 02:27:47');

-- ----------------------------
-- Table structure for bgRole
-- ----------------------------
DROP TABLE IF EXISTS `bgRole`;
CREATE TABLE `bgRole` (
 	`roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台角色表id',
	`roleName` varchar(50)
		 DEFAULT NULL  COMMENT '角色名称' ,	
	`roleRights` varchar(255)
		 DEFAULT NULL  COMMENT '角色权限' ,	
	`parentId` int(11)
		 DEFAULT NULL  COMMENT '上级id' ,	
	`addRights` varchar(255)
		 DEFAULT NULL  COMMENT '新增权限' ,	
	`delRights` varchar(255)
		 DEFAULT NULL  COMMENT '删除权限' ,	
	`editRights` varchar(255)
		 DEFAULT NULL  COMMENT '修改权限' ,	
	`seleRights` varchar(255)
		 DEFAULT NULL  COMMENT '查看权限' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台角色表';

-- ----------------------------
-- Records of bgRole
-- ----------------------------
INSERT INTO `bgRole` VALUES ('1', '系统管理组', '147573389638682795998', '0', '1', '1', '1', '1', '2016-06-06 02:27:47');


-- ----------------------------
-- Table structure for bgMenu
-- ----------------------------
DROP TABLE IF EXISTS `bgMenu`;
CREATE TABLE `bgMenu` (
 	`menuId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台菜单表id',
	`menuName` varchar(50)
		 DEFAULT NULL  COMMENT '菜单名称' ,	
	`menuCode` varchar(50)
		 DEFAULT NULL  COMMENT '菜单标记名称' ,	
	`menuTag` varchar(50)
		 DEFAULT NULL  COMMENT '菜单标识 ' ,	
	`menuUrl` varchar(255)
		 DEFAULT NULL  COMMENT '菜单链接' ,	
	`parentId` int(11)
		 DEFAULT NULL  COMMENT '上级id' ,	
	`menuOrder` varchar(50)
		 DEFAULT NULL  COMMENT '菜单排序' ,	
	`menuIcon` varchar(255)
		 DEFAULT NULL  COMMENT '菜单图标' ,	
	`menuType` varchar(50)
		 DEFAULT NULL  COMMENT '菜单类型' ,	
	`status` varchar(50)
		 DEFAULT NULL  COMMENT '状态' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台菜单表';

-- ----------------------------
-- Records of bgMenu
-- ----------------------------
INSERT INTO `bgMenu` VALUES ('1', '系统管理', '#', 1, '#', '0', '1', 'menu-icon fa fa-desktop blue', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('2', '权限管理', '#', 1, '#', '1', '1', 'menu-icon fa fa-lock black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('3', '日志管理', '#', 1, 'fhlog/list.do', '1', '6', 'menu-icon fa fa-book blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('4', '文件管理', '#', 1, 'fhfile/list.do', '59', '3', 'menu-icon fa fa-folder-open purple', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('6', '信息管理', '#', 1, '#', '0', '5', 'menu-icon fa fa-credit-card green', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('7', '图片管理', '#', 1, '#', '6', '1', 'menu-icon fa fa-folder-o pink', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('8', '性能监控', '#', 1, 'druid/index.html', '9', '8', 'menu-icon fa fa-tachometer red', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('9', '系统工具', '#', 1, '#', '0', '3', 'menu-icon fa fa-cog black', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('10', '接口测试', '#', 1, 'tool/interfaceTest.do', '9', '2', 'menu-icon fa fa-exchange green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('11', '发送邮件', '#', 1, 'tool/goSendEmail.do', '9', '3', 'menu-icon fa fa-envelope-o green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('12', '置二维码', '#', 1, 'tool/goTwoDimensionCode.do', '9', '4', 'menu-icon fa fa-barcode green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('14', '地图工具', '#', 1, 'tool/map.do', '9', '6', 'menu-icon fa fa-globe black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('15', '微信管理', '#', 1, '#', '0', '4', 'menu-icon fa fa-comments purple', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('16', '文本回复', '#', 1, 'textmsg/list.do', '15', '2', 'menu-icon fa fa-comment green', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('17', '应用命令', '#', 1, 'command/list.do', '15', '4', 'menu-icon fa fa-comment grey', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('18', '图文回复', '#', 1, 'imgmsg/list.do', '15', '3', 'menu-icon fa fa-comment pink', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('19', '关注回复', '#', 1, 'textmsg/goSubscribe.do', '15', '1', 'menu-icon fa fa-comment orange', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('20', '在线管理', '#', 1, 'onlinemanager/list.do', '1', '5', 'menu-icon fa fa-laptop green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('21', '打印测试', '#', 1, 'tool/printTest.do', '9', '7', 'menu-icon fa fa-hdd-o grey', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('22', '一级菜单', '#', 1, '#', '0', '10', 'menu-icon fa fa-fire orange', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('23', '二级菜单', '#', 1, '#', '22', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('24', '三级菜单', '#', 1, '#', '23', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('30', '四级菜单', '#', 1, '#', '24', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('31', '五级菜单1', '#', 1, '#', '30', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('32', '五级菜单2', '#', 1, '#', '30', '2', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('33', '六级菜单', '#', 1, '#', '31', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('34', '六级菜单2', '#', 1, 'login_default.do', '31', '2', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('35', '四级菜单2', '#', 1, 'login_default.do', '24', '2', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('36', '角色(基础权限)', 'background/role', 36, 'background/role/list.do', '2', '1', 'menu-icon fa fa-key orange', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('37', '按钮权限', '#', 1, 'buttonrights/list.do', '2', '2', 'menu-icon fa fa-key green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('38', '菜单管理', 'background/menu', 38, 'background/menu/main.do', '1', '3', 'menu-icon fa fa-folder-open-o brown', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('39', '按钮管理', '#', 1, 'fhbutton/list.do', '1', '2', 'menu-icon fa fa-download orange', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('40', '用户管理', '#', 1, '#', '0', '2', 'menu-icon fa fa-users blue', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('41', '系统用户', 'background/user', 41, 'background/user/list.do', '40', '1', 'menu-icon fa fa-users green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('42', '会员管理', '#', 1, 'happuser/listUsers.do', '40', '2', 'menu-icon fa fa-users orange', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('43', '数据字典', 'background/dict', 43, 'background/dict/main.do', '1', '4', 'menu-icon fa fa-book purple', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('44', '代码生成器', '#', 1, '#', '9', '0', 'menu-icon fa fa-cogs brown', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('45', '七级菜单1', '#', 1, '#', '33', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('46', '七级菜单2', '#', 1, '#', '33', '2', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('47', '八级菜单', '#', 1, 'login_default.do', '45', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('48', '图表报表', '#', 1, ' tool/fusionchartsdemo.do', '9', '5', 'menu-icon fa fa-bar-chart-o black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('50', '站内信', '#', 1, 'fhsms/list.do', '6', '3', 'menu-icon fa fa-envelope green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('51', '图片列表', '#', 1, 'pictures/list.do', '7', '1', 'menu-icon fa fa-folder-open-o green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('52', '图片爬虫', '#', 1, 'pictures/goImageCrawler.do', '7', '2', 'menu-icon fa fa-cloud-download green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('53', '表单构建器', '#', 1, 'tool/goFormbuilder.do', '9', '1', 'menu-icon fa fa-leaf black', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('54', '数据库管理', '#', 1, '#', '0', '9', 'menu-icon fa fa-hdd-o blue', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('55', '数据库备份', '#', 1, 'brdb/listAllTable.do', '54', '1', 'menu-icon fa fa-cloud-upload blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('56', '数据库还原', '#', 1, 'brdb/list.do', '54', '3', 'menu-icon fa fa-cloud-download blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('57', '备份定时器', '#', 1, 'timingbackup/list.do', '54', '2', 'menu-icon fa fa-tachometer blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('58', 'SQL编辑器', '#', 1, 'sqledit/view.do', '54', '4', 'menu-icon fa fa-pencil-square-o blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('59', 'OA办公', '#', 1, '#', '0', '6', 'menu-icon fa fa-laptop pink', '2', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('60', '组织机构', '#', 1, 'department/listAllDepartment.do?DEPARTMENT_ID=0', '59', '1', 'menu-icon fa fa-users green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('61', '反向生成', '#', 1, 'recreateCode/list.do', '44', '2', 'menu-icon fa fa-cogs blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('62', '正向生成', '#', 1, 'createCode/list.do', '44', '1', 'menu-icon fa fa-cogs green', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('63', '主附结构', '#', 1, 'attached/list.do', '6', '2', 'menu-icon fa fa-folder-open blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('64', '员工管理', '#', 1, 'staff/list.do', '59', '2', 'menu-icon fa fa-users blue', '1', '1', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('65', '多数据源', '#', 1, 'datasource2/list.do', '6', '4', 'menu-icon fa fa-folder-open-o purple', '1', '0', '2016-06-06 02:27:47');
INSERT INTO `bgMenu` VALUES ('66', '下拉联动', '#', 1, 'linkage/view.do', '6', '5', 'menu-icon fa fa-exchange green', '1', '1', '2016-06-06 02:27:47');

-- ----------------------------
-- Table structure for bgConfig
-- ----------------------------
DROP TABLE IF EXISTS `bgConfig`;
CREATE TABLE `bgConfig` (
 	`configId` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台配置id',
	`configType` varchar(50)
		 DEFAULT NULL  COMMENT '配置类型' ,	
	`configName` varchar(50)
		 DEFAULT NULL  COMMENT '配置名称' ,	
	`param1` varchar(100)
		 DEFAULT NULL  COMMENT '接入网址' ,	
	`param2` varchar(100)
		 DEFAULT NULL  COMMENT '端口号/数字' ,	
	`param3` varchar(100)
		 DEFAULT NULL  COMMENT '账号/X' ,	
	`param4` varchar(100)
		 DEFAULT NULL  COMMENT '密码/Y' ,	
	`isOpen` varchar(10)
		 DEFAULT NULL  COMMENT '是否启动' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`configId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '后台配置';

-- ----------------------------
-- Records of bgConfig
-- ----------------------------
INSERT INTO `bgConfig` VALUES ('1', 'configBgSystem', '系统配置', 'JX1', '11', 'admin', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('2', 'configBgEmailServer', '邮箱服务器配置', 'smt1p.qq.com', '21', 'it1@126.com', '1231', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('3', 'configBgMessage', '短信账户配置', 'http://www.dx1ton.com/', null, 'username1', 'ppp1', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('4', 'configBgWordWaterMark', '文字水印配置', 'JX1', '21', '11', '12', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('5', 'configBgImageWaterMark', '图片水印配置', 'watermark.png', NULL, '14', '13', '0', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('6', 'configBgWeiXin', ' 微信接口配置', '/weixin/index ', NULL, 'token1', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('7', 'configBgInstantChat', '即时聊天服务器配置', '127.0.0.2', '6021', '', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('8', 'configBgOnlineManage', '在线管理服务器配置', '127.0.0.3', '6022', '', '', '1', '2015-06-03 22:09:13');



-- ----------------------------
-- Table structure for com_dict
-- ----------------------------
DROP TABLE IF EXISTS `com_dict`;
CREATE TABLE `com_dict` (

	`dictId` varchar(100) 
			NOT NULL COMMENT '数据字典 主键id',
	`parentId` varchar(100) 
			NOT NULL COMMENT '上级 id',
	`dictCode` varchar(100)
			 DEFAULT NULL  COMMENT '数据字典代号', 
	`dictName` varchar(100)
			 DEFAULT NULL  COMMENT '数据字典名称', 
	`dictType` varchar(100)
			 DEFAULT NULL  COMMENT '数据字典类型', 
	`dictValue` varchar(100)
			 DEFAULT NULL  COMMENT '数据字典值', 
	`dictStatus` varchar(100)
			 DEFAULT NULL  COMMENT '数据字典状态', 
	`level` int(10)
			 DEFAULT NULL  COMMENT '级别', 
	`orderNum` varchar(100)
			 DEFAULT NULL  COMMENT '排序编号', 
	`effective` varchar(100)
			 DEFAULT NULL  COMMENT '有效性', 
	`createUserId` varchar(100)
			 DEFAULT NULL  COMMENT '创建人员id', 
	`createTime` datetime
			 DEFAULT NULL  COMMENT '创建时间', 
	`modifyUserId` varchar(100)
			 DEFAULT NULL  COMMENT '修改人员id', 
	`modifyTime` datetime
			 DEFAULT NULL  COMMENT '修改时间', 
  
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'com_dict';

-- ----------------------------
-- Records of com_dict
-- ----------------------------
INSERT INTO `com_dict` VALUES ('0c608e9327344f588eebd8edce01de8b', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_01', '标准型', '01', '01', '00', '1', '1484025817953', '01', '1', '2017-01-10 13:23:38', '1', '2017-01-10 13:23:38');
INSERT INTO `com_dict` VALUES ('1c0caa13e3514c87a3280f2f48653d83', '28510c12e9834ea29bbb5621f99e804e', 'bg_propType_02', 'Int', '01', '02', '00', '1', '1484018861734', '01', '1', '2017-01-10 11:27:42', '1', '2017-01-10 11:27:42');
INSERT INTO `com_dict` VALUES ('28510c12e9834ea29bbb5621f99e804e', '0', 'bg_propType', '字段属性类型', '01', 'bg_propType', '00', '0', '1484018714788', '01', '1', '2017-01-10 11:25:15', '1', '2017-01-10 11:25:15');
INSERT INTO `com_dict` VALUES ('42293fbe78a649568d01da16ff66040f', 'b120815786714648a7247046fe7186d9', 'bg_dictType_02', '数据库字典', '01', '02', '00', '1', '1484017474427', '01', '1', '2017-01-10 11:04:34', '1', '2017-01-10 11:04:34');
INSERT INTO `com_dict` VALUES ('4324f08d02c943ab96e9078b01f703d8', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_04', '主从型（从）', '01', '04', '00', '1', '1484025928454', '01', '1', '2017-01-10 13:25:28', '1', '2017-01-10 13:25:28');
INSERT INTO `com_dict` VALUES ('4ed0b868ed2b4d69887bcef4932eb4ef', '28510c12e9834ea29bbb5621f99e804e', 'bg_propType_05', 'Dict', '01', '05', '00', '1', '1484019114333', '01', '1', '2017-01-10 11:31:54', '1', '2017-01-10 11:31:54');
INSERT INTO `com_dict` VALUES ('540b4214c9ea4927867b1a878365ec9c', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_wx', 'weixin', '01', 'wx', '00', '1', '1484018416335', '01', '1', '2017-01-10 11:20:16', '1', '2017-01-10 11:20:16');
INSERT INTO `com_dict` VALUES ('54467b4e2dc84c1ca9e21828803eb9b8', '28510c12e9834ea29bbb5621f99e804e', 'bg_propType_04', 'Double', '01', '04', '00', '1', '1484019019767', '01', '1', '2017-01-10 11:30:20', '1', '2017-01-10 11:30:20');
INSERT INTO `com_dict` VALUES ('609bbe934cd24f7588af2f717a8d49f3', '28510c12e9834ea29bbb5621f99e804e', 'bg_propType_03', 'Time', '01', '03', '00', '1', '1484019001039', '01', '1', '2017-01-10 11:30:01', '1', '2017-01-10 11:30:01');
INSERT INTO `com_dict` VALUES ('6d58e343386e47afbf79fb2dbc8c7d76', 'b120815786714648a7247046fe7186d9', 'bg_dictType_01', '参数字典', '01', '01', '00', '1', '1484017427635', '01', '1', '2017-01-10 11:03:48', '1', '2017-01-10 11:03:48');
INSERT INTO `com_dict` VALUES ('7faad10eb0424cf29a51fb5add0f1b50', '28510c12e9834ea29bbb5621f99e804e', 'bg_propType_01', 'String', '01', '01', '00', '1', '1484018802176', '01', '1', '2017-01-10 11:26:42', '1', '2017-01-10 11:26:42');
INSERT INTO `com_dict` VALUES ('b120815786714648a7247046fe7186d9', '0', 'bg_dictType', '字典类型', '01', 'bg_dictType', '00', '0', '1484017251117', '01', '1', '2017-01-10 11:00:51', '1', '2017-01-10 11:00:51');
INSERT INTO `com_dict` VALUES ('d8cf8becf1db4418a098a43500d5cf3f', '0', 'com_packageType', '模块包分类', '01', 'com_packageType', '00', '0', '1484017801545', '01', '1', '2017-01-10 11:10:02', '1', '2017-01-10 11:10:02');
INSERT INTO `com_dict` VALUES ('e53230ed682741f4ad63e470e0d86488', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_mb', 'mobile', '01', 'mb', '00', '1', '1484018478585', '01', '1', '2017-01-10 11:21:19', '1', '2017-01-10 11:21:33');
INSERT INTO `com_dict` VALUES ('e69288a45145429c8c3a865024d4b83c', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_02', '树形', '01', '02', '00', '1', '1484025846486', '01', '1', '2017-01-10 13:24:06', '1', '2017-01-10 13:24:06');
INSERT INTO `com_dict` VALUES ('f0cde8fcc255428fae4cceb7f1a3abf1', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_bg', 'background', '01', 'bg', '00', '1', '1484018374289', '01', '1', '2017-01-10 11:19:34', '1', '2017-01-10 11:19:34');
INSERT INTO `com_dict` VALUES ('f29b6b4266b54c67a3d9f629abc95560', 'd8cf8becf1db4418a098a43500d5cf3f', 'com_packageType_com', 'common', '01', 'com', '00', '1', '1484017926047', '01', '1', '2017-01-10 11:12:06', '1', '2017-01-10 11:13:22');
INSERT INTO `com_dict` VALUES ('f2b727ee2e654654bd91363660fe2197', 'f9831d6e9c3b482381bb940ffc5edf20', 'bg_mapleType_03', '主从型（主）', '01', '03', '00', '1', '1484025903963', '01', '1', '2017-01-10 13:25:04', '1', '2017-01-10 13:25:04');
INSERT INTO `com_dict` VALUES ('f9831d6e9c3b482381bb940ffc5edf20', '0', 'bg_mapleType', '代码结构类型', '01', 'bg_mapleType', '00', '0', '1484025760602', '01', '1', '2017-01-10 13:22:41', '1', '2017-01-10 13:22:41');



-- ----------------------------
-- Table structure for bg_maple_main
-- ----------------------------
DROP TABLE IF EXISTS `bg_maple_main`;
CREATE TABLE `bg_maple_main` (

	`mapleId` varchar(100) 
			NOT NULL COMMENT '代码生成 主键id',
	`mapleCode` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成代号', 
	`mapleName` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成名称', 
	`mapleType` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成类型', 
	`mapleStatus` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成状态', 
	`controllerPackage` varchar(100)
			 DEFAULT NULL  COMMENT '控制器包代号', 
	`entityPackage` varchar(100)
			 DEFAULT NULL  COMMENT '实体类包代号', 
	`orderNum` varchar(100)
			 DEFAULT NULL  COMMENT '排序编号', 
	`effective` varchar(100)
			 DEFAULT NULL  COMMENT '有效标志', 
	`createUserId` varchar(100)
			 DEFAULT NULL  COMMENT '创建人员id', 
	`createTime` datetime
			 DEFAULT NULL  COMMENT '创建时间', 
	`modifyUserId` varchar(100)
			 DEFAULT NULL  COMMENT '修改人员id', 
	`modifyTime` datetime
			 DEFAULT NULL  COMMENT '修改时间', 
  
  PRIMARY KEY (`mapleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '代码生成';

-- ----------------------------
-- Records of bg_maple_main
-- ----------------------------
INSERT INTO `bg_maple_main` VALUES ('0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetail', '代码生成详情', '04', '00', 'bg', 'bg', '1484190988154', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_main` VALUES ('27a853950d0e4876ba0eccf8d7e2dd8f', 'baseField', '基本字段', '01', '00', 'bg', 'bg', '1484122184486', '01', '1', '2017-01-11 16:09:44', '1', '2017-01-11 16:09:44');
INSERT INTO `bg_maple_main` VALUES ('c9da8f2d57774bbbad13030135b6a1cb', 'maple', '代码生成', '03', '00', 'bg', 'bg', '1484149707387', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
-- ----------------------------
-- Table structure for bg_maple_detail
-- ----------------------------
DROP TABLE IF EXISTS `bg_maple_detail`;
CREATE TABLE `bg_maple_detail` (

	`mapleDetailId` varchar(100) 
			NOT NULL COMMENT '代码生成详情 主键id',
	`mapleId` varchar(100) 
			NOT NULL COMMENT '代码生成 id',
	`mapleDetailCode` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成详情代号', 
	`mapleDetailName` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成详情名称', 
	`mapleDetailType` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成详情类型', 
	`mapleDetailStatus` varchar(100)
			 DEFAULT NULL  COMMENT '代码生成详情状态', 
	`totalLength` varchar(100)
			 DEFAULT NULL  COMMENT '总长度', 
	`decimalLength` varchar(100)
			 DEFAULT NULL  COMMENT '小数长度', 
	`typeCode` varchar(100)
			 DEFAULT NULL  COMMENT '类型代号', 
	`isKey` varchar(100)
			 DEFAULT NULL  COMMENT '是否主键', 
	`isEdit` varchar(100)
			 DEFAULT NULL  COMMENT '是否录入', 
	`isNull` varchar(100)
			 DEFAULT NULL  COMMENT '是否null', 
	`defaultValue` varchar(100)
			 DEFAULT NULL  COMMENT '默认值', 
	`orderNum` varchar(100)
			 DEFAULT NULL  COMMENT '排序编号', 
	`effective` varchar(100)
			 DEFAULT NULL  COMMENT '有效标志', 
	`createUserId` varchar(100)
			 DEFAULT NULL  COMMENT '创建人员id', 
	`createTime` datetime
			 DEFAULT NULL  COMMENT '创建时间', 
	`modifyUserId` varchar(100)
			 DEFAULT NULL  COMMENT '修改人员id', 
	`modifyTime` datetime
			 DEFAULT NULL  COMMENT '修改时间', 
  
  PRIMARY KEY (`mapleDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '代码生成详情';


-- ----------------------------
-- Records of bg_maple_detail
-- ----------------------------
INSERT INTO `bg_maple_detail` VALUES ('022169ef83d8462ebb373e46d4ded0f6', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleName', '代码生成名称', '01', '00', '100', '', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('06090486109a45ba91ea41fa724f629b', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailCode', '代码生成详情代号', '01', '00', '100', '', '', '00', '01', '01', '', '1', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('0799c155ca024ee88ea6761acca8e369', '0c1ea3878cd34c8b9f99283de36f25fb', 'isEdit', '是否录入', '01', '00', '100', '', '', '00', '01', '01', '\"01\"', '1484191624802', '01', '1', '2017-01-12 11:27:05', '1', '2017-01-12 11:27:05');
INSERT INTO `bg_maple_detail` VALUES ('15a98f494b794541816d4fc808de92df', '27a853950d0e4876ba0eccf8d7e2dd8f', 'orderNum', '排序编号', '01', '00', '100', '', '', '00', '01', '01', 'String.valueOf(nowTime.getTime())', '9999999999994', '01', '1', '2017-01-11 17:22:12', '1', '2017-01-11 17:22:12');
INSERT INTO `bg_maple_detail` VALUES ('1e010e65fb2741e08e1ad6e6fc0dce9c', '27a853950d0e4876ba0eccf8d7e2dd8f', 'effective', '有效标志', '01', '00', '100', '', '', '00', '00', '01', '\"01\"', '9999999999995', '01', '1', '2017-01-11 17:25:35', '1', '2017-01-11 17:25:35');
INSERT INTO `bg_maple_detail` VALUES ('313da8d640f94c65b24950a451dc8f7a', '0c1ea3878cd34c8b9f99283de36f25fb', 'effective', '有效标志', '01', '00', '100', '', '', '00', '00', '01', '\"01\"', '9999999999995', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('3a68078cb50b476297ccdf56357e448d', 'c9da8f2d57774bbbad13030135b6a1cb', 'createUserId', '创建人员id', '01', '00', '100', '', '', '00', '00', '01', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '9999999999996', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('3e6b33c49dc14c40a14fe050ebea0a38', '0c1ea3878cd34c8b9f99283de36f25fb', 'modifyUserId', '修改人员id', '01', '00', '100', '', '', '00', '00', '01', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '9999999999998', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('43deda2ca573444eaae12e6136b19356', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleStatus', '代码生成状态', '01', '00', '100', '', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('454746b728aa471abe5266152b00df58', '27a853950d0e4876ba0eccf8d7e2dd8f', 'modifyUserId', '修改人员id', '01', '00', '100', '', '', '00', '00', '01', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '9999999999998', '01', '1', '2017-01-11 21:11:16', '1', '2017-01-11 21:11:16');
INSERT INTO `bg_maple_detail` VALUES ('4616314fd35947a69e9f0edce517b4ca', '0c1ea3878cd34c8b9f99283de36f25fb', 'typeCode', '类型代号', '01', '00', '100', '', '', '00', '01', '01', '', '1484191596294', '01', '1', '2017-01-12 11:26:36', '1', '2017-01-12 11:26:36');
INSERT INTO `bg_maple_detail` VALUES ('4d48487873ab44f6b5cae2a9f5092c23', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleType', '代码生成类型', '05', '00', '100', '', 'bg_mapleType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('4dd3c3f338d44f6eb850ce11e4f4562c', '0c1ea3878cd34c8b9f99283de36f25fb', 'orderNum', '排序编号', '01', '00', '100', '', '', '00', '01', '01', 'String.valueOf(nowTime.getTime())', '9999999999994', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('51399fcab43044988facc25aeebd888b', 'c9da8f2d57774bbbad13030135b6a1cb', 'effective', '有效标志', '01', '00', '100', '', '', '00', '00', '01', '\"01\"', '9999999999995', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('531737534ab649f9bd4ced96cda1ffda', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailType', '代码生成详情类型', '05', '00', '100', '', 'bg_mapleDetailType', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('54398981fb824b04bafb3f1d3cd48f6f', '27a853950d0e4876ba0eccf8d7e2dd8f', 'status', '状态', '01', '00', '100', '', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-11 17:11:35', '1', '2017-01-11 17:11:35');
INSERT INTO `bg_maple_detail` VALUES ('5712b8ad5b1c4589a7bb5a40aa391962', '0c1ea3878cd34c8b9f99283de36f25fb', 'defaultValue', '默认值', '01', '00', '100', '', '', '00', '01', '01', '', '1484191753948', '01', '1', '2017-01-12 11:29:14', '1', '2017-01-12 11:29:14');
INSERT INTO `bg_maple_detail` VALUES ('59dce9c7d51846a685e13bb87bc349b5', '27a853950d0e4876ba0eccf8d7e2dd8f', 'modifyTime', '修改时间', '03', '00', '', '', '', '00', '00', '01', 'nowTime', '9999999999999', '01', '1', '2017-01-11 21:13:05', '1', '2017-01-11 21:13:05');
INSERT INTO `bg_maple_detail` VALUES ('688f191c23ef4d1f98a3559e470cf6e4', '27a853950d0e4876ba0eccf8d7e2dd8f', 'code', '代号', '01', '00', '100', '', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 16:52:43', '1', '2017-01-11 16:52:43');
INSERT INTO `bg_maple_detail` VALUES ('6e1c83d28e19411ab4778f546e107435', '27a853950d0e4876ba0eccf8d7e2dd8f', 'type', '类型', '05', '00', '100', '', '', '00', '01', '01', '\"01\"', '3', '01', '1', '2017-01-11 17:08:55', '1', '2017-01-11 17:08:55');
INSERT INTO `bg_maple_detail` VALUES ('76e8bed04d6b4e5f8acdb81b6223ccf3', 'c9da8f2d57774bbbad13030135b6a1cb', 'controllerPackage', '控制器包代号', '05', '00', '100', '', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150518096', '01', '1', '2017-01-12 00:01:58', '1', '2017-01-12 00:01:58');
INSERT INTO `bg_maple_detail` VALUES ('77d5c1c3c1c8425ea9536c64b3614c8e', 'c9da8f2d57774bbbad13030135b6a1cb', 'mapleCode', '代码生成代号', '01', '00', '100', '', '', '00', '01', '01', '', '1', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('81e0eb35db3644839a629c4b25e92b8f', '27a853950d0e4876ba0eccf8d7e2dd8f', 'createUserId', '创建人员id', '01', '00', '100', '', '', '00', '00', '01', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '9999999999996', '01', '1', '2017-01-11 21:09:09', '1', '2017-01-11 21:09:09');
INSERT INTO `bg_maple_detail` VALUES ('878e2c01af44411b957d4203417bb024', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailStatus', '代码生成详情状态', '01', '00', '100', '', '', '00', '00', '01', '\"00\"', '4', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('969f5383c0a54b479439227e2e8adf24', '0c1ea3878cd34c8b9f99283de36f25fb', 'isKey', '是否主键', '01', '00', '100', '', '', '00', '01', '01', '\"00\"', '1484191616190', '01', '1', '2017-01-12 11:28:16', '1', '2017-01-12 11:28:16');
INSERT INTO `bg_maple_detail` VALUES ('99035c0c3e594c81a8201789ea42641f', '0c1ea3878cd34c8b9f99283de36f25fb', 'modifyTime', '修改时间', '03', '00', '', '', '', '00', '00', '01', 'nowTime', '9999999999999', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('a35792a023f848ac897f4a7753ea6938', '0c1ea3878cd34c8b9f99283de36f25fb', 'isNull', '是否null', '01', '00', '100', '', '', '00', '01', '01', '\"01\"', '1484191654251', '01', '1', '2017-01-12 11:27:34', '1', '2017-01-12 11:27:34');
INSERT INTO `bg_maple_detail` VALUES ('b02307fa014f45f7b4da682590fc5394', '27a853950d0e4876ba0eccf8d7e2dd8f', 'createTime', '创建时间', '03', '00', '', '', '', '00', '00', '01', 'nowTime', '9999999999997', '01', '1', '2017-01-11 21:10:08', '1', '2017-01-11 21:10:08');
INSERT INTO `bg_maple_detail` VALUES ('b414921ff02e461fb4ab491e0cf4c6c8', 'c9da8f2d57774bbbad13030135b6a1cb', 'orderNum', '排序编号', '01', '00', '100', '', '', '00', '01', '01', 'String.valueOf(nowTime.getTime())', '9999999999994', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('be775a41a6544563b93b6a3e17ca727a', '0c1ea3878cd34c8b9f99283de36f25fb', 'mapleDetailName', '代码生成详情名称', '01', '00', '100', '', '', '00', '01', '01', '', '2', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('c997cc1f6d8a4fbbaa5e4eb0490c501a', '0c1ea3878cd34c8b9f99283de36f25fb', 'decimalLength', '小数长度', '01', '00', '100', '', '', '00', '01', '01', '', '1484191459111', '01', '1', '2017-01-12 11:24:19', '1', '2017-01-12 11:24:19');
INSERT INTO `bg_maple_detail` VALUES ('caa186f0d7fb462babdd983494b4dcb4', 'c9da8f2d57774bbbad13030135b6a1cb', 'createTime', '创建时间', '03', '00', '', '', '', '00', '00', '01', 'nowTime', '9999999999997', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('ced6e11b418a423cb31c4048e0bb4310', '0c1ea3878cd34c8b9f99283de36f25fb', 'createUserId', '创建人员id', '01', '00', '100', '', '', '00', '00', '01', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '9999999999996', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('e71c07b5e504453aadb91a31ae79b19f', '0c1ea3878cd34c8b9f99283de36f25fb', 'createTime', '创建时间', '03', '00', '', '', '', '00', '00', '01', 'nowTime', '9999999999997', '01', '1', '2017-01-12 11:16:28', '1', '2017-01-12 11:16:28');
INSERT INTO `bg_maple_detail` VALUES ('eeafe483879b40c6aaeb56083f9b8c44', '27a853950d0e4876ba0eccf8d7e2dd8f', 'name', '名称', '01', '00', '100', '', '', '00', '01', '01', '', '2', '01', '1', '2017-01-11 17:07:21', '1', '2017-01-11 17:07:21');
INSERT INTO `bg_maple_detail` VALUES ('ef3cc316b9bf45059bd65405e7366741', 'c9da8f2d57774bbbad13030135b6a1cb', 'entityPackage', '实体类包代号', '05', '00', '100', '', 'com_packageType', '00', '01', '01', '\"bg\"', '1484150564639', '01', '1', '2017-01-12 00:02:44', '1', '2017-01-12 00:02:44');
INSERT INTO `bg_maple_detail` VALUES ('ef6b8a854ab140bbb0c01b18094f9db9', '0c1ea3878cd34c8b9f99283de36f25fb', 'totalLength', '总长度', '01', '00', '100', '', '', '00', '01', '01', '\"100\"', '1484191421616', '01', '1', '2017-01-12 11:23:42', '1', '2017-01-12 11:23:42');
INSERT INTO `bg_maple_detail` VALUES ('f06d53540d524302ac0ce4b5138517cb', 'c9da8f2d57774bbbad13030135b6a1cb', 'modifyUserId', '修改人员id', '01', '00', '100', '', '', '00', '00', '01', 'String.valueOf(BgSessionUtil.getSessionBgUserRole().getUserId())', '9999999999998', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
INSERT INTO `bg_maple_detail` VALUES ('f161d4241edb47a1b326e4b1a1e2bdfa', 'c9da8f2d57774bbbad13030135b6a1cb', 'modifyTime', '修改时间', '03', '00', '', '', '', '00', '00', '01', 'nowTime', '9999999999999', '01', '1', '2017-01-11 23:48:27', '1', '2017-01-11 23:48:27');
