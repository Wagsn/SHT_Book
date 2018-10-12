<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>消息列表</title>

<link href="css/base.css" rel="stylesheet">
<!-- <link href="css/navigation.css" rel="stylesheet"> -->
<script type="text/javascript" src="./assets/js//jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./js/base.js"></script>

<style type="text/css">
.strong{ font-weight:bold}
/* <用于对card进行布局包装，以确保card有足够的此村尺寸进行显示> */
.card-wrap {
	background: #EEE;
	margin: 0 0 10px 0;	
	overflow: hidden;  /* 消除子元素浮动对父元素的影响 */
}
.head-wrap {
	float: left;
	width: 100px;
}
.head {
	width: 100px;
	height: 100px;
	background: #EEE;
	padding: 5px;
}
.content-wrap {
	float: left;
	width: 900px;
	height: 100%;
}
.content-head-wrap {
	width: 100%;
	padding: 5px;
	margin-bottom: 10px;
	overflow: hidden;  /* 消除子元素浮动对父元素的影响 */
}
.content-main-wrap {
	width: 100%;
	padding: 5px;
	word-break: break-all;  /* 多行换行 */
	height: 2em;  /* 最多两行 */
}
</style>
<script type="text/javascript">
var page_data={
	usr_id: localData.load("usr_id"), /* 用户id */
	notice_usr_id: parseQueryString(location.href).notice_usr_id|1, /* 消息列表的用户id */
};
/* <页面跳转> */
function jumpToChat(e){
	console.log(e);
	let data =eval('('+$(e).attr("data-field")+')');
	console.log("fri_id: ", data.fri_id)
	location.href='ui_chat_chat?src_id='+page_data.notice_usr_id+'&dst_id='+ data.fri_id;
}
/* <页面初始化> */
function init() {}
/* <消息列表请求参数> */
function notices_reqdata(){
	let reqdata = {"usr_id": page_data.notice_usr_id};
	console.log("--notices request-> ", reqdata);
	return JSON.stringify(reqdata);
}
/* <异步刷新列表> */
function async_refresh_list(){
	$.ajax({
		type : "post", /* 提交方式 */
		timeout: 1000, /* 连接超时 */
		url : "chat_findAllLastById", /* servlet 或者 url index.jsp */
		data : notices_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : (result) => { /* 请求成功时被调用，result为服务器传来的数据 */
			resdata = eval(result);  
			console.log("--respones data-> ", resdata);  // 页面暂存数据，全局数据能不使用则不使用
			refresh_list(notice_data_convert(resdata));
		},
		error : (XMLHttpRequest, textStatus, errorThrown) => { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败: " + errorThrown);
		}
	});
}
/* <消息列表数据转换, 前提: page_data.notice_usr_id, 将[JSON for List<Notice>]转换成[view list data]> */
function notice_data_convert(resdata){
	let list_data =[];
	for (it of resdata) {
		let item = {
			last_time: it.time,
			last_send_id: it.src_id,
			last_msg: it.msg,
			fri_id: (it.src_id==page_data.notice_usr_id?it.dst_id:it.src_id)
		}
		list_data.push(item);
	}
	console.log("--list data-> ", list_data);
	return list_data;
}
/* <同步加载消息列表数据，返回消息数据数组, 前提: page_data.notice_usr_id> */
function load_notices(){
	$.ajax({
		type : "post", /* 提交方式 */
		async: false,  /* 同步加载 */
		timeout: 1000,  /* 连接超时 */
		url : "chat_findAllLastById", /* servlet 或者 url index.jsp */
		data : notices_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : (result) => { /* 请求成功时被调用，result为服务器传来的数据 */
			resdata = eval(result);  
			console.log("--res data-> ", resdata);  // JSON for List<Chat>
			return notice_data_convert(resdata);
		},
		error : (XMLHttpRequest, textStatus, errorThrown) => { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败" + errorThrown);
		}
	});
}
/* <传入数据刷新消息列表视图> */
function refresh_list(data){
	console.log("--params-> ", data);
	let noticelist = $("#notice-list");
	noticelist.html(""); /* 清空原有列表项 */
	for (const it of data) {
		let item = $(`<li class="card-wrap" onclick="jumpToChat(this)" data-field="{'fri_id': `+it.fri_id+`}"></div>`);
		item.appendTo(noticelist);
		item.append(`<div class="head-wrap"><img class="head" src="./img/user/default/head.jpg" alt="头像"/></div>`);
		let content = $('<div class="content-wrap"></div>');
		content.appendTo(item);
		content.append('<div class="content-head-wrap"><span class="strong">'+it.fri_id+'</span><span style="float: right;">2018/08/24 18:12:15</span></div><div class="content-main-wrap">'+it.last_msg+'</div>');
	}
}
/* <产生随机数据> */
function notices_random_data(){
	let data =[];
	for (let i=0; i< 10; i++) {
		let item ={
			fri_id: (i+2),
			fri_name: (i+2),
			fri_img_url: "#",
			last_msg: "000",
			last_send_id: (i+1),
			last_time: new Date(i*100000)
		};
		data.push(item);
	}
	return data;
}
function linlToRelation(){
	location.replace('ui_chat_relation?relation_usr_id='+page_data.notice_usr_id);
}
</script>
</head>

<body>

<!-- 顶部导航 -->
<div id="top-nav" class="top-nav card"> 
	<a id="home-link" href="./"><span>首页</span><span class="en">Home</span></a>
	<a id="back-link" href="javascript:history.back()"><span>返回</span><span class="en">Back</span></a>
	<a id="notice-link" href="javascript:void(0)"><span>通知</span><span class="en">Notice</span></a>
	<a id="relation-link" href="javascript:linlToRelation()"><span>通讯录</span><span class="en">Relation</span></a>
</div>

<div id="wrapper">

<header class="card">
	<h1>消息列表</h1>
</header>

<article>
	<div class="notice-list-wrap">
		<!-- 列表 -->
		<ul id="notice-list" class="list">
			<!-- 列表项 -->
			<li class="card-wrap">
				<div class="card">
					<p>
						用户[AA]<span>最后消息时间：18:50</span>
					</p>
					<br />
					<p>[我]：你好啊！</p>
				</div>
			</li>
			<li class="card-wrap">
				<div class="card">
					<p>
						用户[BB]<span>最后消息时间：19:50</span>
					</p>
					<br />
					<p>最近在干嘛？</p>
				</div>
			</li>
		</ul>
	</div>
	<p>```...```</p>
</article>

</div>

<footer>
<div class="center card">
<p>Copyright&copy; 2018 Wagsn</p>
</div>
</footer>
<script type="text/javascript">
async_refresh_list();

</script>
</body>
</html>