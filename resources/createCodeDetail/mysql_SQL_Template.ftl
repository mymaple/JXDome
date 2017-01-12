
-- ----------------------------
-- Table structure for ${bgMaple.tableCode}
-- ----------------------------
DROP TABLE IF EXISTS `${bgMaple.tableCode}`;
CREATE TABLE `${bgMaple.tableCode}` (

	`${bgMaple.mapleCode}Id` varchar(100) 
			NOT NULL COMMENT '${bgMaple.mapleName} 主键id',
	`${bgMaple.mapleCode ?replace('Detail','')}Id` varchar(100) 
			NOT NULL COMMENT '${bgMaple.mapleName ?replace('详情','')} id',
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
  
  PRIMARY KEY (`${bgMaple.mapleCode}Id`<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, `${bgMapleDetail.mapleDetailCode}`</#if></#list>)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '${bgMaple.mapleName}';
