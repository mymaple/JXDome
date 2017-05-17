#改积分
UPDATE com_app_user_tree u, com_app_user_ext e SET e.extValue='1000' WHERE u.phone = '18260620003' AND u.appUserId = e.appUserId AND e.extCode='integralCount';
SELECT e.* from com_app_user_tree u, com_app_user_ext e WHERE u.phone = '18260620003' AND u.appUserId = e.appUserId AND e.extCode='integralCount';

#管理员
INSERT INTO `jxdb`.`bg_user_info` (`userId`, `roleId`, `userCode`, `password`, `userName`, `userType`, `userStatus`, `userIconSrc`, `email`, `phone`, `remarks`, `orderNum`, `effective`, `createUserId`, `createTime`, `modifyUserId`, `modifyTime`) VALUES ('1', '1', 'admin', '802ad7f0bf5d54f941bd0a866d3fb6225af153a71d90e456b72a62e4a40008c9e8fc7a01b5d89121d90006f4628c95c4e5ba88ced2b958033791f663d97eed3a', 'maple', '01', '00', 'static/ace/avatars/user.jpg', '54325621@qq.com', '13256876192', '灰机', '1', '01', '1', '2017-01-19 10:17:03', '1', '2017-01-19 10:17:07');
