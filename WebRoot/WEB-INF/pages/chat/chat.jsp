<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>私信界面</title>

<link href="css/base.css" rel="stylesheet">
<!-- <link href="css/navigation.css" rel="stylesheet"> -->
<script type="text/javascript" src="./assets/js//jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./js/base.js"></script>

<style type="text/css">
.hidden{
	overflow: hidden
}
.main {
	margin: auto;
	width: 1000px;
}
.list-wrap {
	width: 100%;
	margin-bottom: 10px;
}
#chat_list {
	width: 100%;
}
.list-item {
	list-style: none;
	width: 100%;
	margin-bottom: 20px;
}
.head-wrap{
	height: 100%;
}
.head {
	width: 100px;
	height: 100px;
	background: #EEE;
}
.msg-main {
	max-width: 600px;
	min-height: 100%;
}
.content-head-wrap{
	width: 100%;
	padding: 0 10px;
}
.usr-name {
	width: 100%;
	margin: 10px;
}
.text-content {
	margin: 10px;
	padding: 20px;
	background: #EEE;
	 word-break:break-all;
}
.msg-content-wrap {
	width: 100%;
}
.sendmsg-wrap {
	width: 100%;
	margin-bottom: 5px;
	background: #EEE;
}
.input-box {
	width: 100%;
	padding: 10px;
}

.edit-wrap {
	height: 100%;
}
.button-wrap {
	height: 100%;
}
</style>
<script type="text/javascript">
/*   
函数：格式化日期   
参数：formatStr-格式化字符串   
d：将日显示为不带前导零的数字，如1   
dd：将日显示为带前导零的数字，如01   
ddd：将日显示为缩写形式，如Sun   
dddd：将日显示为全名，如Sunday   
M：将月份显示为不带前导零的数字，如一月显示为1   
MM：将月份显示为带前导零的数字，如01  
MMM：将月份显示为缩写形式，如Jan  
MMMM：将月份显示为完整月份名，如January  
yy：以两位数字格式显示年份  
yyyy：以四位数字格式显示年份  
h：使用12小时制将小时显示为不带前导零的数字，注意||的用法  
hh：使用12小时制将小时显示为带前导零的数字  
H：使用24小时制将小时显示为不带前导零的数字  
HH：使用24小时制将小时显示为带前导零的数字  
m：将分钟显示为不带前导零的数字  
mm：将分钟显示为带前导零的数字  
s：将秒显示为不带前导零的数字  
ss：将秒显示为带前导零的数字  
l：将毫秒显示为不带前导零的数字  
ll：将毫秒显示为带前导零的数字  
tt：显示am/pm  
TT：显示AM/PM  
返回：格式化后的日期  
*/ 
Date.prototype.format = function (formatStr) {  
    var date = this;  
    /*  
    函数：填充0字符  
    参数：value-需要填充的字符串, length-总长度  
    返回：填充后的字符串  
    */ 
    var zeroize = function (value, length) {  
        if (!length) {  
            length = 2;  
        }  
        value = new String(value);  
        for (var i = 0, zeros = ''; i < (length - value.length); i++) {  
            zeros += '0';  
        }  
            return zeros + value;  
    };  
    return formatStr.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|M{1,4}|yy(?:yy)?|([hHmstT])\1?|[lLZ])\b/g, function($0) {  
        switch ($0) {  
            case 'd': return date.getDate();  
            case 'dd': return zeroize(date.getDate());  
            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][date.getDay()];  
            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][date.getDay()];  
            case 'M': return date.getMonth() + 1;  
            case 'MM': return zeroize(date.getMonth() + 1);  
            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][date.getMonth()];  
            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][date.getMonth()];  
            case 'yy': return new String(date.getFullYear()).substr(2);  
            case 'yyyy': return date.getFullYear();  
            case 'h': return date.getHours() % 12 || 12;  
            case 'hh': return zeroize(date.getHours() % 12 || 12);  
            case 'H': return date.getHours();  
            case 'HH': return zeroize(date.getHours());  
            case 'm': return date.getMinutes();  
            case 'mm': return zeroize(date.getMinutes());  
            case 's': return date.getSeconds();  
            case 'ss': return zeroize(date.getSeconds());  
            case 'l': return date.getMilliseconds();  
            case 'll': return zeroize(date.getMilliseconds());  
            case 'tt': return date.getHours() < 12 ? 'am' : 'pm';  
            case 'TT': return date.getHours() < 12 ? 'AM' : 'PM';  
        }  
    });  
}
var page_data={};
/* <初始化> */
function data_init() {
	console.log(parseQueryString(location.href));
	let urldata = parseQueryString(location.href);
	if (urldata.src_id && urldata.src_id!="") {
		page_data.src_id =new Number(urldata.src_id);  // 发信人id
		page_data.usr_id =new Number(urldata.src_id);  // 登录人id
	} else {
		page_data.src_id =1;  // 发信人id
		page_data.usr_id =1;  // 登录人id
		console.log("src_id empty use default value "+page_data.src_id);
	}
	if (urldata.dst_id && urldata.dst_id!="") {
		page_data.dst_id =new Number(urldata.dst_id);  // 收信人id
	} else {
		page_data.dst_id =2;  // 收信人id
		console.log("dst_id empty use default value "+page_data.dst_id);
	}
	console.log("--init-> "+"usr_id="+page_data.usr_id+", src_id="+page_data.src_id+", dst_id="+page_data.dst_id);
}
/* <监初始化> */
function listener_init() {
	$('#send-bth').click(sendmsg_btn);
	/* <事件初始化-代码是对的,eclipse报错> */
	$('#input-text').bind('keyup', function(event) {
	　　if (event.keyCode == "13") {
	　　　　//回车执行查询
	　　　　$('#send-bth').click();
	　　}
	});
}
/* <加载页面内容> */
function loadPage() {
	data_init();  // url解析，原始数据初始化（localStorage获取usr_id usr_name等）
	refresh_list();  // 刷新对话列表
	listener_init();  // 监听初始化
}
/* 发送消息按钮点击事件处理器，消息发送成功则清空输入框 */
function sendmsg_btn(){
	console.log("src_id: "+page_data.src_id+", dst_id: "+page_data.dst_id);
	console.log("--edit content->"+$("#input-text").val());
	if ($("#input-text").val()=="") {
		console.warn("empty input");
		return;
	}
	/* 上传私信添加请求 */
	$.ajax({  // 
		type : "post", /* 提交方式 */
		timeout: 1000,
		url : "chat_addChat", /* servlet 或者 url index.jsp */
		data : sendmsg_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : function(result) { /* 请求成功时被调用，result为服务器传来的数据 */
			/* 页面数据初始化 */
			/* 数据暂为chatlist */
			let data =eval(result);
			page_data.chatlist = data;  // 页面暂存数据
			console.log(data);
			console.log("--刷新界面");
			refresh_list();
			clear_input();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败" + errorThrown);
		}
	});
}

function clear_input(){
	$("#input-text").val("");
}

/* 刷新列表 */
function refresh_list(){
	$.ajax({  // 
		type : "post", /* 提交方式 */
		timeout: 1000,
		url : "chat_findAllById", /* action servlet 或者 url index.jsp */
		data : chatlist_reqdata(),  /* 发送src_id dst_id */
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : function(result) { /* 请求成功时被调用，result为服务器传来的数据 */
			/* 页面数据初始化 */
			/* 数据暂为chatlist */
			let data =eval(result);
			page_data.chatlist = data;  // 页面暂存数据
			console.log(data);
			let doc_chatlist =$("#chat_list");
			doc_chatlist.html("");
			for (chat of data) {
				console.log(chat);
				let li =$('<li class="list-item f_l"></li>');
				li.appendTo(doc_chatlist);
				let isFr =(chat.src_id==page_data.usr_id?" f_r":" f_l");
				li.append('<div class="head-wrap'+isFr+'"><img class="head card" src="./img/user/default/head.jpg" alt="头像"/></div>');
				let msg_main =$('<div class="msg-main'+isFr+'"></div>');
				//msg_main.append('<div class="usr-name'+isFr+'">'+chat.src_id+'</div>');
				msg_main.append('<div class="content-head-wrap f_l"><span class="'+isFr+'">'+new Date(chat.time).format('yyyy/MM/dd HH:mm:ss')+'</span></div>');
				msg_main.append('<div class="msg-content-wrap f_l"><div class="card text-content'+isFr+'">'+chat.msg+'</div></div></div>');
				//li.append('<div class="msg-main'+isFr+'"><div class="usr-name'+isFr+'">用户名</div><div class="card msg-content'+isFr+'">消息内容</div></div>');
				msg_main.appendTo(li);
			}
			// 页面滚动到输入框, 不保存历史
			replaceHash("#sendmsg"); 
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败" + errorThrown);
		}
	});
}
/* <私信列表的请求数据> */
function chatlist_reqdata(){
	let req_data = '{"src_id": '+page_data.src_id+', "dst_id": '+page_data.dst_id+'}';
	console.log("--request data-> "+req_data);
	return req_data;
}
/* <发送消息的请求数据> */
function sendmsg_reqdata(){
	let req_data = '{"src_id": '+page_data.src_id+', "dst_id": '+page_data.dst_id+', "msg": "'+$("#input-text").val()+'"'+'}';
	console.log("--request data-> "+req_data);
	return req_data;
}
function replaceHash(hash) {
	return location.replace(
		'#' + hash.replace(/^#/, '')
	);
}
function linkToNotice(){
	location.replace('ui_chat_notice?notice_usr_id='+page_data.src_id);
}
function linkToNotice(){
	location.replace('ui_chat_notice?notice_usr_id='+page_data.src_id);
}
function linkToRelation(){
	location.replace('ui_chat_relation?relation_usr_id='+page_data.src_id);
}
</script>
</head>
<body>

<!-- 顶部导航 -->
<div id="top-nav" class="top-nav card"> 
	<a id="home-link" href="./"><span>首页</span><span class="en">Home</span></a>
	<a id="back-link" href="javascript:history.back()"><span>返回</span><span class="en">Back</span></a>
	<a id="notice-link" href="javascript:linkToNotice()"><span>通知</span><span class="en">Notice</span></a>
	<a id="relation-link" href="javascript:linkToRelation()"><span>通讯录</span><span class="en">Relation</span></a>
</div>

<div id="wraper" class="main">
<header class="card">
	<h1>对话列表</h1>
</header>

<div class="list-wrap">
	<!-- 私信历史对话界面，数据动态加载 -->
	<ul id="chat_list">
			<li class='list-item f_l'>
					<div class="head-wrap f_l">
						<img class="head card" src="./img/user/default/head.jpg" alt="头像"/>
					</div>
					<div class="msg-main f_l">
						<div class="usr-name">小白</div>
						<div class="card msg-content">目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
							目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
							目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
						</div>
					</div>
				</li>
				<li class='list-item f_l'>
					<div class="head-wrap f_r">
						<img class="head card" src="./img/user/default/head.jpg" alt="头像"/>
					</div>
					<div class="msg-main f_r">
						<div class="usr-name f_r">小白</div>
						<div class="card msg-content f_r">目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
							目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
							目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
						</div>
					</div>
				</li>
				<li class='list-item f_l'>
					<div class="head-wrap f_l">
						<img class="head card" src="./img/user/default/head.jpg" alt="头像"/>
					</div>
					<div class="msg-main f_l">
						<div class="usr-name">小白</div>
						<div class="card msg-content">目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
							目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
							目前，不少文章对应用于社区论坛、电子购物和网络游戏的网名，应用于QQ、MSN等上的昵称都有过研究和探讨
						</div>
					</div>
				</li>
	</ul>
</div>

<!-- 消息编辑框 -->
<div id="sendmsg" class="sendmsg-wrap card f_l">
	<div class="edit-wrap">
		<textarea id="input-text" rows="3" class="input-box"></textarea>
	</div>
	<div class="button-wrap">
		<button id="send-bth" class="button f_r">发送</button>
	</div>
</div>

</div>

<!-- <在这里加载页面> -->
<script type="text/javascript">
loadPage();
</script>

</body>
</html>