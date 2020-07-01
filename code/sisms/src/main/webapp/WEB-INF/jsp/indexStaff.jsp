<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>SISMS职员信息服务管理系统-admin1.0</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/static/css/font.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/xadmin.js"></script>

</head>
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="<%=request.getContextPath() %>/staffLogin">职员综合服务管理系统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">${name}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="<%=request.getContextPath() %>/leaveManage/information">个人信息</a></dd>
              <dd><a href="<%=request.getContextPath() %>/login.jsp">退出</a></dd>
            </dl>
          </li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe699;</i>
                    <cite>财务系统</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="<%=request.getContextPath() %>/static/paymentManage_staff/Info">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>资金流动通知</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="<%=request.getContextPath() %>/static/paymentManage_staff/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>交易记录</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>办公室管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="<%=request.getContextPath() %>/officeManage/office-staff">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>办公室信息</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                    <a href="javascript:;">
                        <i class="iconfont">&#xe723;</i>
                        <cite>离职管理</cite>
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a _href="<%=request.getContextPath() %>/leaveManage/xslook">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>离职信息</cite>
                            </a>
                        </li >
                        <li>
                            <a _href="<%=request.getContextPath() %>/leaveManage/askforleave">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>请假信息</cite>
                            </a>
                        </li >
                            </a>
                        </li >
                    </ul>
                </li>
            </li>
           
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的主页</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='<%=request.getContextPath() %>/welcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">Copyright ©2020 SISMS职员信息服务管理系统 All Rights Reserved</div>  
    </div>
</body>
</html>