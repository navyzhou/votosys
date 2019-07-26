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
	
	$.post("topic", {op:"findByTno", tno:tno}, function(data){
		var str = "<h4>"+data.tname+"</h4>";
		var num = 0;
		if (data.usids != null) {
			num = data.usids.split("&").length;
		}
		str += '<p class="info">共有 '+data.topicItems.length+' 个选项，已有 '+num+' 个网友参与了投票。</p><ol>';
		
		$.each(data.topicItems, function(index, item){
			str += '<li><div class="rate">';
			str += '<div class="ratebg">'+ item.iname+'<div class="percent" style="width:'+(item.nums/num * 100).toFixed(2)+'%"></div></div>';
			str += '<p>'+item.nums+'票<span>('+ (item.nums/num * 100).toFixed(2) +'%)</span></p></div></li>';
		});
		
		str+='</ol><div class="goback"><a href="vote.jsp">返回投票列表</a></div>';
		$("#view_list").append($(str));
	}, "json");
	
})
</script>
</head>

<body>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		<%@ include file="page/nav.jsp" %>
	</div>
	<div class="search">
		<form method="post" action="Subject!search.action">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>

<div id="vote" class="wrap">
	<h2>查看投票</h2>
	<ul class="list">
		<li id="view_list">
				
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>
