<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta charset="UTF-8">
<title>SISMS职员信息服务管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/font.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/xadmin.css">
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/lib/layui/layui.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/xadmin.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layui-anim layui-anim-up">
	<div class="x-nav">
		<a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
<!--the following codes are added by xxt-->
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/simss"
     user="root"  password=""/>

<sql:query dataSource="${snapshot}" var="result">
SELECT * from application;
</sql:query>
	<div class="x-body">
		<xblock>
		<span class="x-right" style="line-height: 40px">共有数据：${size } 条</span>
		</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>
						<div class="layui-unselect header layui-form-checkbox"
							lay-skin="primary">
							<i class="layui-icon">&#xe605;</i>
						</div>
					</th>
					<th>申请编号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>身份证号</th>
					<th>申请岗位</th>
					<th>小组</th>
					<th>部门</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${result.rows }" var="row">
					<tr>
						<td>
							<div class="layui-unselect layui-form-checkbox"
								lay-skin="primary" data-id='${row.appID}'>
								<i class="layui-icon">&#xe605;</i>
							</div>
						</td>
						<td>${row.appID }</td>
						<td>${row.Name }</td>
						<td>${row.sex }</td>
						<td>${row.identifyID }</td>
						<td>${row.officeNumber }</td>
						<td>${row.groupID }</td>
						<td>${row.projectKind }</td>
						<td class="td-manage">
							<button class="layui-btn layui-btn layui-btn-xs"
								onclick="app_pass(this,'要通过的id',${row.appID })"
								href="javascript:;">
								<i class="layui-icon">&#xe63c;</i>通过
							</button>
			                
							<button class="layui-btn-danger layui-btn layui-btn-xs"
								onclick="app_deny(this,'要删除的id',${row.appID })"
								href="javascript:;">
								<i class="layui-icon">&#xe640;</i>拒绝
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
	<script>
    layui.use(['form'], function(){
        form = layui.form;
        
      });
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

      /*用户-删除*/
      function app_deny(obj,id,appID){
          layer.confirm('确认要删除吗？',function(id){
              //发异步删除数据
              
              var appDate={
            		  appID:appID
              }
              $.ajax({
            	  type:"POST",
            	  url:"<%=request.getContextPath() %>/ApplicationManage/deny",
            	  data:appDate,
            	  success:function(data){		  
            	  }
              })
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }

      function app_pass(obj,id,appID){
          layer.confirm('确认通过吗？',function(id){
              //发异步添加数据
              
              var appDate={
            		  appID:appID
              }
              $.ajax({
            	  type:"POST",
            	  url:"<%=request.getContextPath() %>/ApplicationManage/entry",
            	  data:appDate,
            	  success:function(data){		  
            	  }
              })
              $(obj).parents("tr").remove();
              layer.msg('已添加!',{icon:1,time:1000});
          });
      }
    </script>

</body>

</html>