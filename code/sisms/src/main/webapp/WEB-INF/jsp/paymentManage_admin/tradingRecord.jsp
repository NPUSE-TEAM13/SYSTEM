<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎页面-X-admin2.0</title>
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
</head>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a href="">首页</a> <a href="">演示</a>
			<a> <cite>导航元素</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height: 1.6em; margin-top: 3px; float: right"
			href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<div class="layui-row">
			<form class="layui-form layui-col-md12 x-so"
				action="<%=request.getContextPath()%>/static/paymentManage_admin/tradingRecord?check=${check}"
				method="get">
				<input type="text" name="check" id="check" placeholder="请输入员工员工号"
					value="${check}" class="layui-input">
				<button class="layui-btn" type="submit" lay-filter="sreach">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</form>
		</div>
		<table class="layui-table">
			<thead>
				<tr>
					<th>序号</th>
					<th>员工号</th>
					<th>姓名</th>
					<th>缴费类型</th>
					<th>费用</th>
					<th>截止日期</th>
					<th>支付日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${staffList}" var="record">
					<c:if test="${record.isPay == 1}">
						<tr>
							<td>${record.payNumber}</td>
							<td>${record.staffID}</td>
							<td>${record.staffName}</td>
							<td>${record.payKind}</td>
							<td>${record.payPrice}</td>
							<td>${record.deadline}</td>
							<td>${record.payDate}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//执行一个laydate实例
			laydate.render({
				elem : '#start' //指定元素
			});

			//执行一个laydate实例
			laydate.render({
				elem : '#end' //指定元素
			});
		});
	</script>
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
</body>

</html>