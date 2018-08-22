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
	list-style:none;
	width: 100%;
	margin-bottom: 30px;
}
.list-item div {
	background: #EEE;
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

.input-wrap {
	width: 90%;
	height: 100%;
}
.button-wrap {
	width: 10%;
	height: 100%;
	min-height: 100%;
}

</style>
<script type="text/javascript">
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
		url : "chat_findAllById", /* servlet 或者 url index.jsp */
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
				doc_chatlist.append("<li class='list-item f_l'><div class='card "+(chat.src_id==page_data.usr_id?"f_r":"f_l")+"'>消息["+chat.id+"]，发送时间："+new Date(chat.time)+"<br/>用户["+chat.src_id+"]发送给用户["+chat.dst_id+"]的信息为：<br/><br/>"+chat.msg+"</div></li>");
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
</script>
</head>
<body>
<div id="wraper" class="main">
<header>
	<div class="card">
		<h1>对话列表</h1>
	</div>
</header>

<div class="list-wrap f_l">
	<!-- 私信历史对话界面，数据动态加载 -->
	<ul id="chat_list" class="f_l"></ul>
</div>

<!-- 消息编辑框 -->
<div id="sendmsg" class="sendmsg-wrap card f_l">
	<div class="input-wrap f_l">
		<input id="input-text" class="input-box" type="text" />
	</div>
	<div class="button-wrap f_r">
		<button id="send-bth" class="button center">发送</button>
	</div>
</div>

</div>

<!-- <在这里加载页面> -->
<script type="text/javascript">
loadPage();
</script>

</body>
</html>