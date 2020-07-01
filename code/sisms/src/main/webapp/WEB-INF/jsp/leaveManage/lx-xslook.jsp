<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  
  <head>
    <meta charset="UTF-8">
    <title>SISMS职员信息服务管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/font.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
  </head>
  
  <body>
    
    <div class="x-body">
    
    
    	<fieldset class="layui-elem-field">
            <legend>个人信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th style="width:250px;">姓名</th>
                            <td>${staff.staffName }</td></tr>
                        <tr>
                            <th>职工号</th>
                            <td>${staff.staffID }</td></tr>
                        <tr>
                            <th>应发工资</th>
                            <td>
                            	<c:forEach items="${recordList }" var="list">
                            		${list.payKind }：${list.payPrice }<hr class="hr15">
                            	</c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <th>在研项目</th>
                            <td>
								<c:forEach items="${borrow.borrowBook }" var="list">
									《${list.borrowName }》
								</c:forEach>
							</td>
						</tr>
                        <tr>
                            <th>离职时间</th>
                            <td>${staff.leaveDate }</td></tr>
                        <tr>
                            <th>审核状态</th>
                            <td>
                            	<c:if test="${staff.onJob == true }">已通过</c:if>
                            	<c:if test="${staff.onJob == false }">待审核</c:if>
                            </td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </div>
    <style type="text/css">   
    </style>
  </body>

</html>