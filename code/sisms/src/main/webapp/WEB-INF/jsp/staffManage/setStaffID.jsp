<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>仙人掌🌵职员综合服务管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
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
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so">

				<input type="text" name="username" placeholder="请输入查询信息"
					autocomplete="off" class="layui-input" id="query">
				<button class="layui-btn" onclick="member_query()">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('批量编制','<%=request.getContextPath() %>/staffManage/setIDps',470,200)">
			<i class="layui-icon"></i>批量编制
		</button>
		<button class="layui-btn" onclick="setOfficeClass()">
			<i class="layui-icon"></i>小组办公室分配
		</button>
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
					<th>员工号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>身份证号</th>
					<th>办公室号</th>
					<th>小组</th>
					<th>部门</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${staffList }" var="list">
					<tr>
						<td>
							<div class="layui-unselect layui-form-checkbox"
								lay-skin="primary" data-id='${list.staffID}'>
								<i class="layui-icon">&#xe605;</i>
							</div>
						</td>
						<td>${list.staffID }</td>
						<td>${list.staffName }</td>
						<td>${list.sex }</td>
						<td>${list.identifyID }</td>
						<td>${list.officeNumber }</td>
						<td>${list.groupID }</td>
						<td>${list.projectKind }</td>
						<td class="td-manage">
							<button class="layui-btn layui-btn layui-btn-xs"
								onclick=" member_edit(${list.staffID },'编辑','<%=request.getContextPath()%>/staffManage/staffEdit')">
								<i class="layui-icon">&#xe642;</i>编辑
							</button>

							<button class="layui-btn-danger layui-btn layui-btn-xs"
								onclick="member_del(this,'要删除的id',${list.staffID })"
								href="javascript:;">
								<i class="layui-icon">&#xe640;</i>删除
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
      
      function setOfficeClass() {
    	  var queryData;
    	  $.ajax({
    		  type : 'POST',
    		  url : '<%=request.getContextPath() %>/staffManage/setOfficeClass',
    		  dataType : 'JSON',
    		  data : queryData,
    		  success : function(){},
    		  error : function(){
    			  
    		  }
    	  });
    	  alert("分配完成！")
      }
      
      /*用户-查询*/
      function member_query(){
    	  var info = document.getElementById("query").value;
    	  var queryData = {
    			  "info" : info,
    			  "flag" : 2
    	  }
    	  
    	  $.ajax({
    		  type : 'POST',
    		  url : '<%=request.getContextPath() %>/staffManage/QueryHelp',
    		  dataType : 'JSON',
    		  data : queryData,
    		  success : function(){},
    		  error : function(){
    			  window.location.href = "/sisms/staffManage/Query"
    			  window.event.returnValue=false;
    		  }
    	  });
    	  
      }
      
      /*用户-编辑*/
    	function member_edit(staffID,title,url,w,h){
    		
    		var bookDate={
    	      		  staffID:staffID
    	        }
    	        $.ajax({
    	      	  type:"POST",
    	      	  url:"<%=request.getContextPath() %>/staffManage/staffEditHelp",
    	      	  data:bookDate,
    	      	  success:function(data){	
    	      	  }
    	        });
    	    
    		if (title == null || title == '') {
    	        title=false;
    	    };
    	    if (url == null || url == '') {
    	        url="404.html";
    	    };
    	    if (w == null || w == '') {
    	        w=($(window).width()*0.9);
    	    };
    	    if (h == null || h == '') {
    	        h=($(window).height() - 50);
    	    };
    	    layer.open({
    	        type: 2,
    	        area: [w+'px', h +'px'],
    	        fix: false, //不固定
    	        maxmin: true,
    	        shadeClose: true,
    	        shade:0.4,
    	        title: title,
    	        content: url
    	    })
    	}
      
    	 /*用户-删除*/
        function member_del(obj,id,staffID){
            layer.confirm('确认要删除吗？',function(id){
                //发异步删除数据
                
                var staffDate={
              		  staffID:staffID
                }
                $.ajax({
              	  type:"POST",
              	  url:"<%=request.getContextPath() %>/staffManage/deleteStaff",
              	  data:staffDate,
              	  success:function(data){		  
              	  }
                })
                $(obj).parents("tr").remove();
                layer.msg('已删除!',{icon:1,time:1000});
            });
        }
      
      </script>


</body>

</html>