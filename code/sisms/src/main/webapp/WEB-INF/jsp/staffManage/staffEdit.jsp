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

<body>
	<div class="x-body layui-anim layui-anim-up">
		<form id="staffEdit" class="layui-form">
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>员工号
				</label>
				<div class="layui-input-inline">
					<input type="text" id="staffID" name="staffID"
						value="${staff.staffID}" autocomplete="off"
						class="layui-input" readonly>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>姓名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="staffName" name="staffName"
						value="${staff.staffName}" required="" lay-verify=""
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>性别
				</label>
				<c:if test="${staff.sex eq '男' }">
					<div class="layui-input-inline">
						<select type="text" id="sex" name="sex" required="" lay-verify=""
							autocomplete="off" class="layui-input">
							<option value="男">请选择……</option>
							<option value="男" selected="selected">男</option>
							<option value="女">女</option>
						</select>
					</div>
				</c:if>
				<c:if test="${staff.sex eq '女' }">
					<div class="layui-input-inline">
						<select type="text" id="sex" name="sex" required="" lay-verify=""
							autocomplete="off" class="layui-input">
							<option value="男">请选择……</option>
							<option value="男">男</option>
							<option value="女" selected="selected">女</option>
						</select>
					</div>
				</c:if>

			</div>
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>身份证号
				</label>
				<div class="layui-input-inline">
					<input type="text" id="identifyID" name="identifyID"
						value="${staff.identifyID}" required="" lay-verify=""
						autocomplete="off" class="layui-input" readonly>
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>办公室号
				</label>
				<div class="layui-input-inline">
					<input type="text" id="officeNumber" name="officeNumber"
						value="${staff.officeNumber}" required="" lay-verify=""
						autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>小组
				</label>
				<div class="layui-input-inline">
					<input type="text" id="groupID" name="groupID"
						value="${staff.groupID}" required="" lay-verify=""
						autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red">*</span>培养类型
				</label>
				<c:if test="${empty staff.projectKind}">
					<div class="layui-input-inline">
						<select type="text" id="projectKind" name="projectKind"
							required="" lay-verify="" autocomplete="off" class="layui-input">
							<option value="研发部" selected="selected">请选择……</option>
							<option value="研发部">研发部</option>
							<option value="市场部">市场部</option>
							<option value=财务部>财务部</option>
							<option value=行政部>行政部</option>
						</select>
					</div>
				</c:if>
				<c:if test="${staff.projectKind eq 'A'}">
					<div class="layui-input-inline">
						<select type="text" id="projectKind" name="projectKind"
							required="" lay-verify="" autocomplete="off" class="layui-input">
							<option value="研发部">请选择……</option>
							<option value="研发部" selected="selected">研发部</option>
							<option value="市场部">市场部</option>
							<option value=财务部>财务部</option>
							<option value=行政部>行政部</option>
						</select>
					</div>
				</c:if>
				<c:if test="${staff.projectKind eq 'B'}">
					<div class="layui-input-inline">
						<select type="text" id="projectKind" name="projectKind"
							required="" lay-verify="" autocomplete="off" class="layui-input">
							<option value="研发部">请选择……</option>
							<option value="研发部">研发部</option>
							<option value="市场部" selected="selected">市场部</option>
							<option value=财务部>财务部</option>
							<option value=行政部>行政部</option>
						</select>
					</div>
				</c:if>
				<c:if test="${staff.projectKind eq 'C'}">
					<div class="layui-input-inline">
						<select type="text" id="projectKind" name="projectKind"
							required="" lay-verify="" autocomplete="off" class="layui-input">
							<option value="研发部">请选择……</option>
							<option value="研发部">研发部</option>
							<option value="市场部">市场部</option>
							<option value=财务部 selected="selected">财务部</option>
							<option value=行政部>行政部</option>
						</select>
					</div>
				</c:if>
				<c:if test="${staff.projectKind eq 'D'}">
					<div class="layui-input-inline">
						<select type="text" id="projectKind" name="projectKind"
							required="" lay-verify="" autocomplete="off" class="layui-input">
							<option value="研发部">请选择……</option>
							<option value="研发部">研发部</option>
							<option value="市场部">市场部</option>
							<option value=财务部>财务部</option>
							<option value=行政部 selected="selected">行政部</option>
						</select>
					</div>
				</c:if>

			</div>
			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" id="bt1">完成</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
        
          
          $(function(){
        	    $("#bt1").click(function(){
        	    	var newUrl = '<%=request.getContextPath() %>/staffManage/updateStaff';    //设置新提交地址
        	        $("#staffEdit").attr('action',newUrl);
        	        $("#staffEdit").submit();    //提交ID为myform的表单
        	        /*layer.alert("增加成功", {icon: 6},function () {
                        // 获得frame索引*/
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    /*})*/
        	    })
        	});
        });
    </script>
	<script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>


</body>

</html>