/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : jxdb

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-01-16 16:03:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgmenu
-- ----------------------------
DROP TABLE IF EXISTS `bgmenu`;
CREATE TABLE `bgmenu` (
  `menuId` varchar(11) NOT NULL COMMENT '后台菜单表id',
  `parentId` varchar(11) DEFAULT NULL COMMENT '上级id',
  `menuCode` varchar(50) DEFAULT NULL COMMENT '菜单标记名称',
  `menuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menuType` varchar(50) DEFAULT NULL COMMENT '菜单类型',
  `menuStatus` varchar(50) DEFAULT NULL COMMENT '状态',
  `menuTag` varchar(50) DEFAULT NULL COMMENT '菜单标识 ',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `menuIcon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `orderNum` varchar(50) DEFAULT NULL COMMENT '菜单排序',
  `effective` varchar(100) DEFAULT NULL,
  `createUserId` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyUserId` varchar(100) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

-- ----------------------------
-- Records of bgmenu
-- ----------------------------
INSERT INTO `bgmenu` VALUES ('1', '0', '#', '系统管理', '2', '01', '1', '#', 'menu-icon fa fa-desktop blue', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('10', '9', '#', '接口测试', '1', '01', '10', 'tool/interfaceTest.do', 'menu-icon fa fa-exchange green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('11', '9', '#', '发送邮件', '1', '01', '11', 'tool/goSendEmail.do', 'menu-icon fa fa-envelope-o green', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('12', '9', '#', '置二维码', '1', '01', '12', 'tool/goTwoDimensionCode.do', 'menu-icon fa fa-barcode green', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('14', '9', '#', '地图工具', '1', '01', '14', 'tool/map.do', 'menu-icon fa fa-globe black', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('15', '0', '#', '微信管理', '2', '01', '15', '#', 'menu-icon fa fa-comments purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('16', '15', '#', '文本回复', '2', '01', '16', 'textmsg/list.do', 'menu-icon fa fa-comment green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('17', '15', '#', '应用命令', '2', '01', '17', 'command/list.do', 'menu-icon fa fa-comment grey', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('18', '15', '#', '图文回复', '2', '01', '18', 'imgmsg/list.do', 'menu-icon fa fa-comment pink', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('19', '15', '#', '关注回复', '2', '01', '19', 'textmsg/goSubscribe.do', 'menu-icon fa fa-comment orange', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('2', '1', '#', '权限管理', '1', '01', '2', '#', 'menu-icon fa fa-lock black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('20', '1', '#', '在线管理', '1', '01', '20', 'onlinemanager/list.do', 'menu-icon fa fa-laptop green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('21', '9', '#', '打印测试', '1', '01', '21', 'tool/printTest.do', 'menu-icon fa fa-hdd-o grey', '7', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('22', '0', '#', '一级菜单', '2', '01', '22', '#', 'menu-icon fa fa-fire orange', '10', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('23', '22', '#', '二级菜单', '1', '01', '23', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('24', '23', '#', '三级菜单', '1', '01', '24', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('3', '1', '#', '日志管理', '1', '01', '3', 'fhlog/list.do', 'menu-icon fa fa-book blue', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('30', '24', '#', '四级菜单', '1', '01', '30', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('31', '30', '#', '五级菜单1', '1', '01', '31', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('32', '30', '#', '五级菜单2', '1', '01', '32', '#', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('33', '31', '#', '六级菜单', '1', '01', '33', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('34', '31', '#', '六级菜单2', '1', '01', '34', 'login_default.do', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('35', '24', '#', '四级菜单2', '1', '01', '35', 'login_default.do', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('36', '2', 'background/role', '角色(基础权限)', '1', '01', '36', 'background/role/list.do', 'menu-icon fa fa-key orange', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('37', '2', '#', '按钮权限', '1', '01', '37', 'buttonrights/list.do', 'menu-icon fa fa-key green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('38', '1', 'background/menu', '菜单管理', '1', '01', '38', 'background/menu/main.do', 'menu-icon fa fa-folder-open-o brown', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('39', '1', '#', '按钮管理', '1', '01', '39', 'fhbutton/list.do', 'menu-icon fa fa-download orange', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('4', '59', '#', '文件管理', '1', '01', '4', 'fhfile/list.do', 'menu-icon fa fa-folder-open purple', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('40', '0', '#', '用户管理', '2', '01', '40', '#', 'menu-icon fa fa-users blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('41', '40', 'background/user', '系统用户', '1', '01', '41', 'background/user/list.do', 'menu-icon fa fa-users green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('42', '40', '#', '会员管理', '1', '01', '42', 'happuser/listUsers.do', 'menu-icon fa fa-users orange', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('43', '1', 'background/dict', '数据字典', '1', '01', '43', 'background/dict/main.do', 'menu-icon fa fa-book purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('44', '9', '#', '代码生成器', '1', '01', '44', '#', 'menu-icon fa fa-cogs brown', '0', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('45', '33', '#', '七级菜单1', '1', '01', '45', '#', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('46', '33', '#', '七级菜单2', '1', '01', '46', '#', 'menu-icon fa fa-leaf black', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('47', '45', '#', '八级菜单', '1', '01', '47', 'login_default.do', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('48', '9', '#', '图表报表', '1', '01', '48', ' tool/fusionchartsdemo.do', 'menu-icon fa fa-bar-chart-o black', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('50', '6', '#', '站内信', '1', '01', '50', 'fhsms/list.do', 'menu-icon fa fa-envelope green', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('51', '7', '#', '图片列表', '1', '01', '51', 'pictures/list.do', 'menu-icon fa fa-folder-open-o green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('52', '7', '#', '图片爬虫', '1', '01', '52', 'pictures/goImageCrawler.do', 'menu-icon fa fa-cloud-download green', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('53', '9', '#', '表单构建器', '1', '01', '53', 'tool/goFormbuilder.do', 'menu-icon fa fa-leaf black', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('54', '0', '#', '数据库管理', '2', '01', '54', '#', 'menu-icon fa fa-hdd-o blue', '9', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('55', '54', '#', '数据库备份', '1', '01', '55', 'brdb/listAllTable.do', 'menu-icon fa fa-cloud-upload blue', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('56', '54', '#', '数据库还原', '1', '01', '56', 'brdb/list.do', 'menu-icon fa fa-cloud-download blue', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('57', '54', '#', '备份定时器', '1', '01', '57', 'timingbackup/list.do', 'menu-icon fa fa-tachometer blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('58', '54', '#', 'SQL编辑器', '1', '01', '58', 'sqledit/view.do', 'menu-icon fa fa-pencil-square-o blue', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('59', '0', '#', 'OA办公', '2', '01', '59', '#', 'menu-icon fa fa-laptop pink', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('6', '0', '#', '信息管理', '2', '01', '6', '#', 'menu-icon fa fa-credit-card green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('60', '59', '#', '组织机构', '1', '01', '60', 'department/listAllDepartment.do?DEPARTMENT_ID=0', 'menu-icon fa fa-users green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('61', '44', 'background/maple', '反向生成', '1', '01', '61', 'background/maple/list.do', 'menu-icon fa fa-cogs blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('62', '44', 'background/maple', '正向生成', '1', '01', '62', 'background/maple/list.do', 'menu-icon fa fa-cogs green', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('63', '6', '#', '主附结构', '1', '01', '63', 'attached/list.do', 'menu-icon fa fa-folder-open blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('64', '59', '#', '员工管理', '1', '01', '64', 'staff/list.do', 'menu-icon fa fa-users blue', '2', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('65', '6', '#', '多数据源', '1', '01', '65', 'datasource2/list.do', 'menu-icon fa fa-folder-open-o purple', '4', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('66', '6', '#', '下拉联动', '1', '01', '66', 'linkage/view.do', 'menu-icon fa fa-exchange green', '5', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('7', '6', '#', '图片管理', '2', '01', '7', '#', 'menu-icon fa fa-folder-o pink', '1', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('72', '1', 'qweqqweqweqwe', 'asdsad', '1', '01', '72', 'qweqwesadfasd', 'menu-icon fa fa-adjust black', '6', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('8', '9', '#', '性能监控', '1', '01', '8', 'druid/index.html', 'menu-icon fa fa-tachometer red', '8', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
INSERT INTO `bgmenu` VALUES ('9', '0', '#', '系统工具', '2', '01', '9', '#', 'menu-icon fa fa-cog black', '3', '01', '1', '2016-06-06 02:27:47', '1', '2016-06-06 02:27:47');
