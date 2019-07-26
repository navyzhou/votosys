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
$(function() {
	var tno = location.hash;
	tno = tno.substr(tno.indexOf("=") + 1);
	$("#tid").val(tno);
	$.post("topic", {op:"findByTno", tno:tno}, function(data){
		var str = "<h4>"+data.tname+"</h4>";
		var num = 0;
		if (data.usids != null) {
			num = data.usids.split("&").length;
		}
		str += '<p class="info">共有 '+data.topicItems.length+' 个选项，已有 '+num+' 个网友参与了投票。</p><ol>';
		if (data.topicType == 1) {
			$.each(data.topicItems, function(index, item){
				str += '<li><input type="radio" name="options" value="'+item.tno+'"/>'+item.iname+'</li>';
			});
		} else {
			$.each(data.topicItems, function(index, item){
				str += '<li><input type="checkbox" name="options" value="'+item.tno+'"/>'+item.iname+'</li>';
			});
		}
		
		str += '</ol>';
		$("#topic-list").append($(str));
		
		$("#usids").val(data.usids);
	}, "json");
})

function voteInfo(){
	var tid = $("#tid").val();
	var usids = $("#usids").val();
	
	var tnos = "";
	$("#topic-list ol input:checked").each(function(index, item){
		tnos += item.value + ";";
	});
	if (tnos == "") {
		alert("请选择投票项...");
		return false;
	}
	
	tnos = tnos.substr(0, tnos.length - 1);
	$.post("topic", {op:"vote", tid:tid, usids:usids, tnos:tnos}, function(data){
		data = parseInt($.trim(data));
		if (data > 0) {
			alert("添加投票成功...");
			location.href="view.jsp#tid=" + tid;
		} else {
			alert("添加投票失败...");
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
		<form method="post" action="#">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>

<div id="vote" class="wrap">
	<input type="hidden" id="tid" />
	<input type="hidden" id="usids" />
	<form action="" onsubmit="return voteInfo()">
	<h2>参与投票</h2>
	<ul class="list">
		<li id="topic-list">
		</li>
	</ul>
	<p class="voteView"><input type="image" src="images/button_vote.gif" /><a href="Vote!view.action?entityId=103"><img src="images/button_view.gif" /></a></p>
	</form>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>