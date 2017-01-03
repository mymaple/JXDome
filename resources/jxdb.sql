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
INSERT INTO `bgmapledetail` VALUES ('62', 'dictId', '数据字典主键id', '01', 'DictId', '100', '', '00', '00', '01', '', '10', '00015', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('63', 'dictCode', '数据字典代号', '01', 'DictCode', '100', '', '01', '01', '00', '', '10', '00016', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('64', 'dictName', '数据字典名称', '01', 'DictName', '100', '', '01', '01', '00', '', '10', '00017', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('65', 'dictType', '数据字典类型', '01', 'DictType', '100', '', '01', '01', '00', '', '10', '00018', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('66', 'dictStatus', '数据字典状态', '01', 'DictStatus', '100', '', '00', '01', '00', '00', '10', '00019', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('67', 'effective', '有效性', '01', 'Effective', '100', '', '00', '01', '00', '01', '10', '00021', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('68', 'createUserId', '创建人员id', '01', 'CreateUserId', '100', '', '00', '01', '00', '', '10', '00022', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('69', 'createTime', '创建时间', '03', 'CreateTime', '', '', '00', '01', '00', '', '10', '00023', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('70', 'modifyUserId', '修改人员id', '01', 'ModifyUserId', '100', '', '00', '01', '00', '', '10', '00024', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('71', 'modifyTime', '修改时间', '03', 'ModifyTime', '', '', '00', '01', '00', '', '10', '00025', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('72', 'parentId', '上级id', '01', 'ParentId', '100', '', '00', '00', '01', '', '10', '00020', '00', '01', '', '1900-01-01 00:00:00', '', '1900-01-01 00:00:00');
INSERT INTO `bgmapledetail` VALUES ('73', 'dictValue', '数据字典值', '01', 'DictValue', '100', '', '01', '01', '00', '', '10', '000191', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('74', 'level', '级别', '02', 'Level', '10', '', '00', '01', '00', '', '10', '000192', '00', '01', null, null, null, null);
INSERT INTO `bgmapledetail` VALUES ('78', 'orderNum', '排序编号', '01', 'OrderNum', '100', '', '00', '01', '00', '', '10', '000209', '00', '01', null, null, null, null);

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
-- Table structure for comDict
-- ----------------------------
DROP TABLE IF EXISTS `comDict`;
CREATE TABLE `comDict` (
 	`dictId` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据字典表id',
	`name` varchar(50)
		 DEFAULT NULL  COMMENT '字典名称' ,	
	`encode` varchar(50)
		 DEFAULT NULL  COMMENT '编码' ,	
	`orderBy` varchar(50)
		 DEFAULT NULL  COMMENT '排序' ,	
	`parentId` int(11)
		 DEFAULT NULL  COMMENT '上级ID' ,	
	`level` varchar(50)
		 DEFAULT NULL  COMMENT '级别' ,	
	`allEncode` varchar(255)
		 DEFAULT NULL  COMMENT '组合编码' ,	
	`modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '数据字典表';

-- ----------------------------
-- Records of comDict
-- ----------------------------
INSERT INTO `comDict` VALUES ('1', '属性类型', 'propType', '1', '0', '1', 'propType', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('2', '字符串String', 'String', '0', '1', '2', 'propType_String', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('3', '整形Int', 'Int', '1', '1', '2', 'propType_Int', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('4', '日期型Date', 'Date', '2', '1', '2', 'propType_Date', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('5', '开发模块', 'developModule', '0', '0', '1', 'developModule', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('6', 'common', 'com', '0', '5', '2', 'developModule_com', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('7', 'background', 'bg', '1', '5', '2', 'developModule_bg', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('8', 'weixin', 'wx', '2', '5', '2', 'developModule_wx', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('9', 'web', 'web', '3', '5', '2', 'developModule_web', '2015-06-03 22:09:13');
INSERT INTO `comDict` VALUES ('10', 'mobile', 'mb', '4', '5', '2', 'developModule_mb', '2015-06-03 22:09:13');
