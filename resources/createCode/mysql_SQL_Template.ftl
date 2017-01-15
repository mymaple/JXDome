
-- ----------------------------
-- Table structure for ${bgMaple.tableCode}
-- ----------------------------
DROP TABLE IF EXISTS `${bgMaple.tableCode}`;
CREATE TABLE `${bgMaple.tableCode}` (

	`${bgMaple.mapleCode}Id` varchar(100) 
			NOT NULL COMMENT '${bgMaple.mapleName} 主键id',
	<#if bgMaple.mapleType = "02">
	`parentId` varchar(100) 
			NOT NULL COMMENT '上级 id',
	<#elseif bgMaple.mapleType = "04">
	`${bgMaple.mapleCode ?replace('Detail','')}Id` varchar(100) 
			NOT NULL COMMENT '${bgMaple.mapleName ?replace('详情','')} id',
	</#if>
	<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailType == '01' || bgMapleDetail.mapleDetailType == '05'>
	`${bgMapleDetail.mapleDetailCode}` varchar(${bgMapleDetail.totalLength})
		<#elseif bgMapleDetail.mapleDetailType == '02'>
	`${bgMapleDetail.mapleDetailCode}` int(${bgMapleDetail.totalLength})
		<#elseif bgMapleDetail.mapleDetailType == '03'>
	`${bgMapleDetail.mapleDetailCode}` datetime
		<#elseif bgMapleDetail.mapleDetailType == '04'>
	`${bgMapleDetail.mapleDetailCode}` double(${bgMapleDetail.totalLength},${bgMapleDetail.decimalLength})
		</#if>
			<#if bgMapleDetail.isNull == '00'> NOT NULL <#else> DEFAULT NULL </#if> COMMENT '${bgMapleDetail.mapleDetailName}', 
	</#list>
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
  PRIMARY KEY (`${bgMaple.mapleCode}Id`<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, `${bgMapleDetail.mapleDetailCode}`</#if></#list>)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '${bgMaple.mapleName}';
