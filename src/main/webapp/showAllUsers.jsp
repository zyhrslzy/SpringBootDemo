<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<style type="text/css">
		table{width:100% }
		table,tr,th{border:1px solid gray;border-collapse:collapse; }
	</style>
  </head>
  
  <body>
  <span style="margin-left: 1100px;">
    	<form enctype="multipart/form-data" id="uploadForm">
			<img src="images/default.jpg" style="width: 50px;height: 50px;" onclick="sc()"/>
			<input type="file" name="file" id="file" style="display: none">
		</form>
    	<span>${user.username} 先生/女生，欢迎您  &nbsp;&nbsp;&nbsp;&nbsp;
    	<a href="user/logout" style="text-decoration: none;">【退出】</a></span>
    	<hr/>
    	<table>
    		<thead>
    			<tr><th>用户编号</th><th>用户名称</th><th>用户密码</th><th>用户性别</th><th>操作</th></tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${userList}" var="user" >
    				<tr>
    					<th>${user.userid}</th>
    					<th>${user.username}</th>
    					<th id='${user.userid}'>${user.password}</th>
    					<th>${user.sex}</th>
    					<th>
    						<button onclick="modifyUserinfoById('${user.userid}')">修改</button> &nbsp;&nbsp;&nbsp;&nbsp;
    						<button onclick="delectUserinfoById('${user.userid}')">删除</button>
    					</th>
    					
    				</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    	
    	<center>
    		当前第${pageInfo.pageNum}页/总共${pageInfo.pages}页,
    		<a href="user/getAllUsers?pageNum=${pageInfo.pageNum-1}&maxPage=${pageInfo.pages}">上一页</a>  
    		<a href="user/getAllUsers?pageNum=${pageInfo.pageNum+1}&maxPage=${pageInfo.pages}">下一页</a>  
    		&nbsp;&nbsp;查看第<input size="2" onblur="quick()" placeholder="${pageInfo.pageNum}">页 
    	</center>
  </body>
  <script type="text/javascript">
  	function modifyUserinfoById(userid){
  		var newPass=prompt("请输入新密码");
			 $.ajax({
  				type:"post",//提交方式
  				url:"user/modify?userid="+userid+"&password="+newPass, //请求地址
  				data:null, //携带的参数（地址栏拼参）
  				success:function(msg){ //成功回调函数  msg ---返回的内容
  					/*  alert(msg);  */
  					if(msg>0){
  					//js方式
  					//object.parentNode.parentNode.cells[2].innerHTML=newPass;
  					/* alert("jquery"); */
  					$("#"+userid).html(newPass);
  					}
  				}
  			}); 
  	}
	function delectUserinfoById(userid){
		const msg=confirm("确认删除？");
		$.ajax({
			type:"post",//提交方式
			url:"user/delect?userid="+userid, //请求地址
			data:null, //携带的参数（地址栏拼参）
			success:function(msg){ //成功回调函数  msg ---返回的内容
			   alert("删除成功！")
				location.reload();
			}
		});
	}

	function sc() {
		$("#file").click().change(function () {
			var photo_name = $("img").attr("src").substring(7);
			//alert("图片名称："+photo_name);
			$.ajax({
				type: "post",
				url: "file/personalPhoto_upload?photo_name=" + photo_name,
				//dataType:"json",
				enctype: "multipart/form-data",
				data: new FormData($("#uploadForm")[0]),
				processData: false, //data的值是FormData对象，不需要对数据进行处理
				contentType: false,//FormData对象由form表单构建
				cache: false,
				success: function (msg) {
					alert("回传的:"+msg);
					/* $("img").attr("src","upload/"+msg);  */
					if (!msg.endsWith(".jpg") && !msg.endsWith(".jpeg")) {
						//$("span").html(msg);
						$("img").attr("src", "images/" + photo_name);
					} else {
						$("img").attr("src", "images/" + msg);
					}
				}
			});
			//}
		});
	}
  </script>
</html>
