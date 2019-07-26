<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>在线投票系统</title>
<link type="image/x-icon" href="images/yc.png" rel="shortcut icon"/>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
var isIE = !!document.all;
function AddOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}

function addTopic(){
	var title = $.trim($("#title").val());
	var type = $("input:radio:checked").val();
	var options = "";
	$("#voteoptions input").each(function(index, item) {
		options += item.value + "&";
	});
	if (options != "") {
		options = options.substring(0, options.length - 1);
	}

	$.post("topic", {op:"add", title:title, type: type, options:options}, function(data) {
		data = parseInt($.trim(data));
		if (data > 0) {
			location.href="manage.jsp";
		} else {
			alert("添加投票主题失败...");
		}
	}, "text");
	
	return false;
}
</script>
</head>
<body>
 
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		<%@include file="page/nav.jsp" %>
	</div>
	<div class="search">
		<form method="post" action="">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>

<div id="voteManage" class="box">
	<h2>添加新投票</h2>
	<div class="content">
	<form method="post" action="" onsubmit="return addTopic()">
			<dl>
				<dt>投票内容：</dt>
				<dd>
				   <input type="hidden" name="entityId" value=""/>
				   <input type="text" class="input-text" name="title" id="title" value=""/>
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="type" checked="checked" value="1" />单选
				   <input type="radio" name="type" value="2" />多选
				</dd>
				<dt>投票选项：</dt>
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options" /></p>
					<p><input type="text" class="input-text" name="options" /></p>
				</dd>
				<dt></dt>
				<dd class="button">
					<input type="image" src="images/button_submit.gif" />
					<a href="javascript:void(0);" onclick="AddOption()">增加选项</a>
					<a href="Subject!list.action">取消操作</a>
				</dd>
			</dl>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>