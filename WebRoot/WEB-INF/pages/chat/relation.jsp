<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关系列表</title>

<link href="css/base.css" rel="stylesheet">
<!-- <link href="css/navigation.css" rel="stylesheet"> -->
<script type="text/javascript" src="./assets/js//jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./js/base.js"></script>
<style type="text/css">
.strong{ font-weight:bold}
.txt-center {
	text-align: center;
}
.full {
	min-width: 100%;
	min-height: 100%;
}
/* <用于对card进行布局包装，以确保card有足够的此村尺寸进行显示> */
.card-wrap {
	background: #EEE;
	margin: 0 0 10px 0;	
	overflow: hidden;  /* 消除子元素浮动对父元素的影响 */
}
.head-wrap {
	float: left;
	width: 100px;
	height: 100px;
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
	margin: auto 0 auto 0;
	padding: 5px;
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
	rel_usr_id: parseQueryString(location.href).relation_usr_id|1,
	usr_id: localData.load("usr_id")|parseQueryString(location.href).relation_usr_id|1,
};
/* <页面跳转> */
function jumpToChat(e){
	console.log(e);
	let data =eval('('+$(e).attr("data-field")+')');
	console.log("fri_id: ", data.fri_id);
	location.href='ui_chat_chat?src_id='+page_data.rel_usr_id+'&dst_id='+ data.fri_id;
}
/* <刷新关系列表> */
function refresh_relation_list(data){
	console.log("--params-> ", data);
	let relationlist = $("#relation-list");
	relationlist.html(""); /* 清空原有列表项 */
	for (const it of data) {
		let item = $(`<li class="card-wrap" onclick="jumpToChat(this)" data-field="{'fri_id': `+it.fri_id+`}"></div>`);
		item.appendTo(relationlist);
		item.append(`<div class="head-wrap"><img class="head" src="./img/user/default/head.jpg" alt="头像"/></div>`);
		item.append('<div class="content-wrap"><span class="strong">'+it.fri_id+'</span><span class="f_r">['+it.rel_type+']</span></div>');
	}
}
/* <异步加载, 并刷新列表> */
function load_relations(){
	$.ajax({  // 
		type : "post", /* 提交方式 */
		timeout: 1000,
		url : "relation_findAllById", /* servlet 或者 url index.jsp */
		data : relation_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : (result) => { /* 请求成功时被调用，result为服务器传来的数据 */
			resdata = eval(result);  
			console.log("--respones data-> ", resdata);  // 页面暂存数据，全局数据能不使用则不使用
			refresh_relation_list(data_convert(resdata));
		},
		error : (XMLHttpRequest, textStatus, errorThrown) => { /* 请求错误时调用，errorThrown为错误数据 */
			alert("失败" + errorThrown);
		}
	});
	// 可能因为this指针出错
	return page_data.relations;
}
/* <数据转换-将原始数据转换为可用数据--> */
function data_convert(resdata) {
	let list_data = new Map();
	for (it of resdata) {
		// 关注 1
		if (it.src_id==page_data.rel_usr_id) {
			if (list_data.get(it.dst_id)) {
				// 朋友3
				list_data.set(it.dst_id, {
					fri_id: it.dst_id,
					rel_type: "朋友"
				});
			} else {
				list_data.set(it.dst_id, {
					fri_id: it.dst_id,
					rel_type: "关注"
				});
			}
		}
		// 粉丝 2
		if (it.dst_id==page_data.rel_usr_id) {
			if (list_data.get(it.src_id)) {
				// 朋友3
				list_data.set(it.src_id, {
					fri_id: it.src_id,
					rel_type: "朋友"
				});
			} else {
				list_data.set(it.src_id, {
					fri_id: it.src_id,
					rel_type: "粉丝"
				});
			}
		}
	}
	console.log("--list data-> ", list_data.values());
	return list_data.values();
}
/* <关系列表请求数据> */
function relation_reqdata(){
	let reqdata = {"usr_id": page_data.rel_usr_id};
	console.log("--relation request-> "+reqdata);
	return JSON.stringify(reqdata);
}
function linkToNotice(){
	location.replace('ui_chat_notice?notice_usr_id='+page_data.rel_usr_id);
}
/* <界面适配 ul: list view, li: list item, map: id|class to field, data: list data > */
function adapter(ul, li, maps, data) {
	let eul =$('<ul></ul>');
	let eli =$('<li></li>');
	// maps : [{ class: 'title', field: 'title' }, { class: 'content', field: 'content' }]
	// data : [{title: '', content: ''}, {title: '', content: ''}]
	/* <遍历填充数据> */
	for (const it of data) {
		for (const map of maps) {
			$('.'+map.class).html(it[map.field]);  // 确保是class叶子节点
		}
	}
}
</script>
</head>
<body>

<!-- 顶部导航 -->
<div id="top-nav" class="top-nav card"> 
	<a id="home-link" href="./"><span>首页</span><span class="en">Home</span></a>
	<a id="back-link" href="javascript:history.back()"><span>返回</span><span class="en">Back</span></a>
	<a id="notice-link" href="javascript:linkToNotice()"><span>通知</span><span class="en">Notice</span></a>
	<a id="relation-link" href="javascript:void(0)"><span>通讯录</span><span class="en">Relation</span></a>
</div>

<div id="wrapper">

<header class="card">
	<h1>关系列表</h1>
</header>

<article>
<div class="relation-list-wrap">
<ul id="relation-list" class="relation-list">
<li class="card card-wrap">
<p>用户[xx]</p>
</li>
<li class="card card-wrap">
<p>用户[xx]</p>
</li>
<li class="card card-wrap">
<p>用户[xx]</p>
</li>
<li class="card card-wrap" onclick="location.href='ui_chat_chat'">
<p>用户[xx]</p>
</li>
</ul>
</div>

</article>

</div>

<footer>
	<div class="center card">
		<p>Copyright&copy; 2018 Wagsn</p>
	</div>
</footer>
<script type="text/javascript">
load_relations();
</script>
</body>

</html>