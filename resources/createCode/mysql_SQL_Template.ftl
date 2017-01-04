
-- ----------------------------
-- Table structure for ${bgMaple.tableCode}
-- ----------------------------
DROP TABLE IF EXISTS `${bgMaple.tableCode}`;
CREATE TABLE `${bgMaple.tableCode}` (

	<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailType == '01'>
	`${bgMapleDetail.mapleDetailCode}` varchar(${bgMapleDetail.length})
		<#elseif bgMapleDetail.mapleDetailType == '02'>
	`${bgMapleDetail.mapleDetailCode}` int(${bgMapleDetail.length})
		<#elseif bgMapleDetail.mapleDetailType == '03'>
	`${bgMapleDetail.mapleDetailCode}` datetime
		<#elseif bgMapleDetail.mapleDetailType == '04'>
	`${bgMapleDetail.mapleDetailCode}` double(${bgMapleDetail.length},${bgMapleDetail.decimalLength})
		</#if>
			<#if bgMapleDetail.isNull == '00'> NOT NULL <#else> DEFAULT NULL </#if> COMMENT '${bgMapleDetail.mapleDetailName}' ,
	</#list>
  
  PRIMARY KEY (<#list bgMapleDetailKeyList as bgMapleDetail>`${bgMapleDetail.mapleDetailCode}`<#if bgMapleDetail_has_next> ,</#if></#list>)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '${bgMaple.tableCode}';
