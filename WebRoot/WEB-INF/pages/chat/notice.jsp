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
/* 用于对card进行布局包装，以确保card有足够的此村尺寸进行显示 */
.card-wrap {
	background: #EEE;
	margin: 0 5px 10px 5px;	
}
</style>
<script type="text/javascript">
var page_data={
	usr_id: localData.load("usr_id")
};
/* <页面跳转> */
function jumpToChat(e){
	console.log(e);
	let data =eval('('+$(e).attr("data-field")+')');
	console.log("fri_id: ", data.fri_id)
	location.href='ui_chat_chat?src_id='+page_data.usr_id+'&dst_id='+ data.fri_id;
}
/* 页面初始化 */
function init() {}
/* 获取消息列表请求参数 */
function notices_reqdata(){
	let reqdata = '{'+'"usr_id":'+page_data.usr_id+'}';
	console.log("--notices request-> "+reqdata);
	return reqdata;
}

function async_refresh_list(){
	$.ajax({  // 
		type : "post", /* 提交方式 */
		timeout: 1000,
		url : "chat_findAllLastById", /* servlet 或者 url index.jsp */
		data : notices_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : function(result) { /* 请求成功时被调用，result为服务器传来的数据 */
			resdata = eval(result);  
			console.log("--respones data-> ", resdata);  // 页面暂存数据，全局数据能不使用则不使用
			let list_data =[];
			for (i of resdata) {
				let item = {
					last_time: i.time,
					last_send_id: i.src_id,
					last_msg: i.msg,
					fri_id: (i.src_id==page_data.usr_id?i.dst_id:i.src_id)
				}
				list_data.push(item);
			}
			console.log("--list data-> ", list_data);
			refresh_list(list_data);
			page_data.notices =list_data;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败" + errorThrown);
		}
	});
}

/* 从服务器用Ajax加载数据， 返回数据数组 */
function load_notices(){
	$.ajax({  // 
		type : "post", /* 提交方式 */
		async: false,  // 同步加载
		timeout: 1000,
		url : "chat_findAllLastById", /* servlet 或者 url index.jsp */
		data : notices_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : function(result) { /* 请求成功时被调用，result为服务器传来的数据 */
			resdata = eval(result);  
			console.log("--res data-> ",resdata);  // 页面暂存数据，全局数据能不使用则不使用
			let list_data =[];
			for (i of resdata) {
				let item = {
					last_time: i.time,
					last_send_id: i.src_id,
					last_msg: i.msg,
					fri_id: (i.src_id==page_data.usr_id?i.dst_id:i.src_id)
				}
				list_data.push(item);
			}
			console.log(list_data);
			page_data.notices =list_data;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败" + errorThrown);
		}
	});
	// 可能因为this指针出错
	return page_data.notices;
}


/* 传入数据刷新消息列表 */
function refresh_list(data){
	console.log("--params-> ", data);
	let noticelist = $("#notice-list");
	noticelist.html(""); /* 清空原有列表项 */
	for (const item of data) {
		noticelist.append('<li class="card-wrap" onclick="jumpToChat(this)" data-field="{\'fri_id\': '+item.fri_id+'}"><div class="card"><p>用户['+item.fri_id+']<span>最后消息时间：'+new Date(item.last_time)+'</span></p><br/><p>'+(item.last_send_id==page_data.usr_id?"[我]：":"")+item.last_msg+'</p></div></li>');
	}
}
/* 产生随机数据 */
function random_data(){
	let data =[];
	// 相当于通信接口
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
</script>
</head>

<body>

<div id="wrapper">

<header>
	<div class="card">
		<h1>消息列表</h1>
	</div>
</header>

<article>
	<div class="notice-list-wrap">
		<!-- 列表 -->
		<ul id="notice-list" class="list">
			<!-- 列表项 -->
			<li class="card-wrap">
				<div class="card">
					<p>
						用户[xx]<span>最后消息时间：18:50</span>
					</p>
					<br />
					<p>[我]：你好啊！</p>
				</div>
			</li>
			<li class="card-wrap">
				<div class="card">
					<p>
						用户[BB]<span>最后消息时间：18:50</span>
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