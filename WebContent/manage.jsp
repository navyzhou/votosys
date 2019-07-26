<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>在线投票系统</title>
<link type="image/x-icon" href="images/yc.png" rel="shortcut icon"/>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
</head>
<body>
<c:choose>
	<c:when test="${empty currentLoginUser}">
		<script type="text/javascript">
			alert("请先登录...");
			location.href="login.html";
		</script>
	</c:when>
	<c:otherwise>
		<input type="hidden" id="usid" value="${currentLoginUser.usid}"/>
		<div id="header" class="wrap">
			<img src="images/logo.gif" />
		</div>
		<div id="navbar" class="wrap">
			<div class="profile">
				<%@include file="page/nav.jsp" %>
			</div>
			<div class="search">
				<form method="post" action="Subject!search.action">
					<input type="text" name="keywords" class="input-text" value=""/>
					<input type="submit" name="submit" class="input-button" value="" />
				</form>
			</div>
		</div>
		
		<div id="vote" class="wrap">
			<h2>投票列表</h2>
			<ul class="list" id="data-list">
			</ul>
		</div>
		<div id="footer" class="wrap">
			源辰信息 &copy; 版权所有
		</div>
		
		<script type="text/javascript">
		$(function(){
			$.post("topic", {op:"findAll"}, function(data){
				var str = "";
				$.each(data, function(index, item){
					if (index % 2 == 0) {
						str += '<li class="odd" >';
					} else {
						str += '<li>';
					}
					str += '<h4><a href="vote.jsp#tid='+item.tid+'">'+item.tname+'</a></h4>';
					
					var num = 0;
					if (item.usids != null) {
						num = item.usids.split("&").length;
						var usid = $("#usid").val();
						if (("&"+item.usids+"&").indexOf("&"+usid+"&") >=0){
							str += '<div class="join"><a href="view.jsp#tid='+item.tid+'">我要查看</a></div>';
						} else {
							str += '<div class="join"><a href="vote.jsp#tid='+item.tid+'">我要参与</a></div>';
						}
					}  else {
						str += '<div class="join"><a href="vote.jsp#tid='+item.tid+'">我要参与</a></div>';
					}
					
					str += '<p class="info">共有 '+item.usid+' 个选项，已有 '+num+' 个网友参与了投票。</p></li>';
				});
				$("#data-list").append($(str));
			}, "json");
		})
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>