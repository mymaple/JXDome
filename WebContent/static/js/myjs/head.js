var locat = (window.location+'').split('/'); 
$(function(){if('main'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});

var fmid = "fmid";	//菜单点中状态
var mid = "mid";	//菜单点中状态

var fhsmsCount = 0;						//站内信总数
var TFHsmsSound = '1';					//站内信提示音效

var websocket;							//websocket对象

//树形状态转换
function siMenu(id,fid,menuName,menuUrl){
	if(id != mid){
		$("#"+mid).removeClass();
		mid = id;
	}
	if(fid != fmid){
		$("#"+fmid).removeClass();
		fmid = fid;
	}
	$("#"+fid).attr("class","active open");
	$("#"+id).attr("class","active");
	top.mainFrame.tabAddHandler(id,menuName,menuUrl);
	if(menuUrl != "druid/index.html"){
		jzts();
	}
}

$(function(){
	//online();//连接在线管理
});

//加入在线列表
function online(){
	if (window.WebSocket) {
		websocket = new WebSocket(encodeURI('ws://'+onlineManage)); //oladress在main.jsp页面定义
		websocket.onopen = function() {
			//连接成功
			websocket.send('[join]'+userName);
		};
		websocket.onerror = function() {
			//连接失败
		};
		websocket.onclose = function() {
			//连接断开
		};
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
			if(message.type == 'goOut'){
				$("body").html("");
				goOut("1");
			}else if(message.type == 'thegoout'){
				$("body").html("");
				goOut("2");
			}else if(message.type == 'senFhsms'){
				fhsmsCount = Number(fhsmsCount)+1;
				$("#fhsmsCount").html(Number(fhsmsCount));
				$("#fhsmsobj").html('<audio style="display: none;" id="fhsmstsy" src="static/sound/'+TFHsmsSound+'.mp3" autoplay controls></audio>');
				$("#fhsmstss").tips({
					side:3,
		            msg:'有新消息',
		            bg:'#AE81FF',
		            time:30
		        });
			}
		};
	}
}

//下线
function goOut(msg){
	window.location.href=locat+"/background/main/logout.do?msg="+msg;
}

//获取站内信未读总数(在站内信删除未读新信件时调用此函数更新未读数)
function getFhsmsCount(){
	$.ajax({
		type: "POST",
		url: locat+'/head/getFhsmsCount.do?tm='+new Date().getTime(),
    	data: encodeURI(""),
		dataType:'json',
		cache: false,
		success: function(data){
			 fhsmsCount = Number(data.fhsmsCount);
			 $("#fhsmsCount").html(Number(fhsmsCount));	//站内信未读总数
		}
	});
}

//去通知收信人有站内信接收
function fhsmsmsg(USERNAME){
	var arrUSERNAME = USERNAME.split(';');
	for(var i=0;i<arrUSERNAME.length;i++){
		websocket.send('[fhsms]'+arrUSERNAME[i]);//发送通知
	}
}

//读取站内信时减少未读总数
function readFhsms(){
	fhsmsCount = Number(fhsmsCount)-1;
	$("#fhsmsCount").html(Number(fhsmsCount) <= 0 ?'0':fhsmsCount);
}

//站内信
function fhsms(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="站内信";
	 diag.URL = locat+'/fhsms/list.do?STATUS=2';
	 diag.Width = 800;
	 diag.Height = 500;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//修改头像
function toChangeUserIcon(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="修改头像";
	 diag.URL = locat+'/background/user/toChangeUserIcon.do';
	 diag.Width = 650;
	 diag.Height = 530;
	 diag. ShowMaxButton = true;	//最大化按钮
     diag.ShowMinButton = true;		//最小化按钮
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//修改个人资料
function toChangeMyInfo(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="个人资料";
	 diag.URL = locat+'/background/user/toChangeMyInfo.do';
	 diag.Width = 469;
	 diag.Height = 465;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//系统设置
function toEditConfig(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="系统设置";
	 diag.URL = locat+'/background/config/toEdit.do';
	 diag.Width = 600;
	 diag.Height = 526;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//切换菜单
function changeMenus(){
	window.location.href=locat+'/background/main/yes.do';
}

//清除加载进度
function hangge(){
	$("#jzts").hide();
}

//显示加载进度
function jzts(){
	$("#jzts").show();
}
