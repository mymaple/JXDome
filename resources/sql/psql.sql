DROP PROCEDURE
IF EXISTS pr_list_all_subUser1_spdeal;

CREATE PROCEDURE pr_list_all_subUser1_spdeal (IN userId VARCHAR(100) , IN yearMonth VARCHAR(100))
BEGIN

SET @sqlstr = CONCAT(
	'select roleId into @rootIds from com_app_user_tree WHERE effective = \'01\' and appUserId = \'',
	userId,
	'\''
);

PREPARE stmt1
FROM
	@sqlstr;

EXECUTE stmt1;

DEALLOCATE PREPARE stmt1;

CALL pr_tree_listAllSub (
	@rootIds,
	'appUserRoleId',
	'com_app_user_role_tree'
);

SELECT DISTINCT
	s.*
FROM
	com_app_user_tree u,
	tmp_tree_listAllSub t,
	com_sparepart_deal_info s
WHERE
	u.effective = '01' and
	FIND_IN_SET(t.id, u.roleId) > 0 and 
	s.appUserId = u.appUserId and 
	s.createTime like yearMonth and 
	s.effective = '01'
ORDER BY
	u.orderNum,s.orderNum;


END;


DROP PROCEDURE
IF EXISTS pr_list_all_subUser1;

CREATE PROCEDURE pr_list_all_subUser1 (IN userId VARCHAR(100))
BEGIN

SET @sqlstr = CONCAT(
	'select roleId into @rootIds from com_app_user_tree WHERE effective = \'01\' and appUserId = \'',
	userId,
	'\''
);

PREPARE stmt1
FROM
	@sqlstr;

EXECUTE stmt1;

DEALLOCATE PREPARE stmt1;

CALL pr_tree_listAllSub (
	@rootIds,
	'appUserRoleId',
	'com_app_user_role_tree'
);

SELECT DISTINCT
	u.*
FROM
	com_app_user_tree u,
	tmp_tree_listAllSub t
WHERE
	u.effective = '01' and
	FIND_IN_SET(t.id, u.roleId) > 0
ORDER BY
	u.orderNum;


END;


DROP PROCEDURE
IF EXISTS pr_tree_listAllSub;

CREATE PROCEDURE pr_tree_listAllSub (
	IN rootIds VARCHAR (1000),
	IN idn VARCHAR (100),
	IN tn VARCHAR (100)
)
BEGIN
	CREATE TEMPORARY TABLE
IF NOT EXISTS tmp_tree_listAllSub (
	sno INT PRIMARY KEY auto_increment,
	id VARCHAR (100),
	depth INT
);

DELETE
FROM
	tmp_tree_listAllSub;

DROP VIEW
IF EXISTS v_tn;


SET @sqlstr = CONCAT(
	'create view v_tn as select ',
	idn,
	' as idn, parentId from ',
	tn
);

PREPARE stmt1
FROM
	@sqlstr;

EXECUTE stmt1;

DEALLOCATE PREPARE stmt1;


SET @i = 0;


SET @arraylength = 1 + (
	LENGTH(rootIds) - LENGTH(REPLACE(rootIds, ',', ''))
);


WHILE @i <@arraylength DO

SET @i =@i + 1;


SET @rootId = REVERSE(
	SUBSTRING_INDEX(
		REVERSE(
			SUBSTRING_INDEX(rootIds, ',' ,@i)
		),
		',',
		1
	)
);

CALL pr_tree_createChildList (@rootId, 0);


END
WHILE;

#SELECT r.* FROM tmp_tree_listAllSub t,com_app_user_role_tree r WHERE FIND_IN_SET(r.appUserRoleId, t.id)>0 ORDER BY r.orderNum;
END;

DROP PROCEDURE
IF EXISTS pr_tree_createChildList;

CREATE PROCEDURE pr_tree_createChildList (
	IN rootId VARCHAR (1000),
	IN nDepth INT
)
BEGIN
	DECLARE
		done INT DEFAULT 0;

DECLARE
	b VARCHAR (1000);

DECLARE
	cur1 CURSOR FOR SELECT
		idn
	FROM
		v_tn
	WHERE
		parentId = rootId;

DECLARE
	CONTINUE HANDLER FOR NOT FOUND
SET done = 1;

INSERT INTO tmp_tree_listAllSub
VALUES
	(NULL, rootId, nDepth);

OPEN cur1;

FETCH cur1 INTO b;


WHILE done = 0 DO
	CALL pr_tree_createChildList (b, nDepth + 1);

FETCH cur1 INTO b;


END
WHILE;

CLOSE cur1;


END;