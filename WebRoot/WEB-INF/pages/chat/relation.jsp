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
/* 用于对card进行布局包装，以确保card有足够的此村尺寸进行显示 */
.card-wrap {
	background: #EEE;
	margin: 0 5px 10px 5px;	
}
</style>
</head>
<body>

<div id="wrapper">
<header>
	<div class="card">
		<h1>关系列表</h1>
	</div>
</header>
<script type="text/javascript">
var page_data={
	relation_usr_id: localData.load("relation_usr_id")|1,
	usr_id: localData.load("relation_usr_id")|1
};
/* <页面跳转> */
function jumpToChat(e){
	console.log(e);
	let data =eval('('+$(e).attr("data-field")+')');
	console.log("fri_id: ", data.fri_id)
	location.href='ui_chat_chat?src_id='+page_data.usr_id+'&dst_id='+ data.fri_id;
}
/* <刷新关系列表> */
function refresh_relation_list(data){
	console.log("--params-> ", data);
	let relationlist = $("#relation-list");
	relationlist.html(""); /* 清空原有列表项 */
	for (const item of data) {
		relationlist.append('<li class="card card-wrap" onclick="jumpToChat(this)" data-field="{\'fri_id\': '+item.fri_id+'}"><span>用户['+item.fri_id+']</span><span class="f_r">['+item.rel_type+']</span></li>');
	}
}
/* <从服务器用Ajax同步加载数据， 返回数据数组> */
function load_relations(){
	$.ajax({  // 
		type : "post", /* 提交方式 */
		async: false,  // 同步加载
		timeout: 1000,
		url : "relation_findAllById", /* servlet 或者 url index.jsp */
		data : relation_reqdata(),
		contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
		dateType : "json", /* 数据类型为json */
		success : function(result) { /* 请求成功时被调用，result为服务器传来的数据 */
			resdata = eval(result);  
			console.log("--respones data-> ", resdata);  // 页面暂存数据，全局数据能不使用则不使用
			// 数据转换
			let list_data =data_convert(resdata);
			console.log(list_data);
			page_data.relations =list_data;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { /* 请求错误时调用，errorThrown为错误数据 */
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
		if (it.src_id==page_data.usr_id) {
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
		if (it.dst_id==page_data.usr_id) {
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
	return list_data.values();
}

/* <关系列表请求数据> */
function relation_reqdata(){
	let reqdata = '{'+'"usr_id":'+page_data.usr_id+'}';
	console.log("--relation request-> "+reqdata);
	return reqdata;
}
/* <产生随机数据> */
function random_data(){
	let data =[];
	// 相当于通信接口
	for (let i=0; i< 10; i++) {
		let item ={
			fri_id: (i+2),
			fri_name: (i+2),
			fri_img_url: "#"
		};
		data.push(item);
	}
	return data;
}
</script>
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
refresh_relation_list(load_relations());
</script>
</body>

</html>