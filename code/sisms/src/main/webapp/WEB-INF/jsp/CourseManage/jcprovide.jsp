<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/font.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so layui-form-pane">
          <input class="layui-input" placeholder="课程编号/名称" name="cate_name" id="query">
          <button class="layui-btn" onclick="member_query()">查询</button>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </form>
      </div>
      
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn"  onclick="x_admin_show('编辑','<%=request.getContextPath()%>/courseManage/jcAdd')" lay-submit="" lay-filter="sreach"><i class="layui-icon"></i>添加课程</button>
        <span class="x-right" style="line-height:40px">共有数据：${num } 条</span>
      </xblock>
      <table class="layui-table layui-form">
        <thead>
            <tr>
                <th width="20">
                    <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
                </th>
                <th width="70">课程编号</th>
                <th>课程名称</th>
                <th width="50">数量</th>
                <th width="50">时长</th>
                <th width="70">发布状态</th>
                <th width="220">操作</th>
</thead>
        <tbody class="x-cate">
       	 <c:forEach items="${courseList }" var = "list">
       	 <c:if test="${list.providing!=null}">
				<tr cate-id='1' fid='0'>
					<td>
						<div class="layui-unselect layui-form-checkbox"
							lay-skin="primary" data-id='${list.courseNumber }'>
							<i class="layui-icon">&#xe605;</i>
						</div>
					</td>
					<td>${list.courseNumber }</td>
					<td>${list.courseName }</td>
					<td>${list.number }</td>
					<td>${list.length }</td>
					<!-- ${list.auditing } -->
					<c:if test="${list.providing==1}"><td><input type="checkbox" name="switch" lay-text="发放|未发" checked="check" lay-skin="switch"></td></c:if>
					<c:if test="${list.providing==0}"><td><input type="checkbox" name="switch" lay-text="发放|未发"  lay-skin="switch"></td></c:if>
					
					<td class="td-manage">
						<button class="layui-btn layui-btn layui-btn-xs"
							onclick="member_edit('${list.courseNumber }','编辑','<%=request.getContextPath()%>/courseManage/jcEdit')">
							<i class="layui-icon">&#xe642;</i>编辑
						</button>

						<button class="layui-btn-danger layui-btn layui-btn-xs"
							onclick="member_del(this,'要删除的id',${list.courseNumber })" href="javascript:;">
							<i class="layui-icon">&#xe640;</i>删除
						</button>
					</td>
				</tr>
				</c:if>
			</c:forEach>
        </tbody>
      </table>
    </div>
    <style type="text/css">
      
    </style>
    <script>
      layui.use(['form'], function(){
        form = layui.form;
        
      });

      

      /*用户-删除*/
     function member_del(obj,id,courseNumber){
          layer.confirm('确认要删除吗？',function(id){
              //发异步删除数据
           	var courseDate={
            		  courseNumber:courseNumber
              }
              $.ajax({
            	  type:"POST",
            	  url:"<%=request.getContextPath() %>/courseManage/textDelete",
            	  data:courseDate,
            	  success:function(data){		  
            	  }
              })
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }
      
     /*用户-查询*/
     function member_query(){
   	  var info = document.getElementById("query").value;
   	  var queryData = {
   			  "info" : info,
   			  "flag" : 4
   	  }
   	  
   	  $.ajax({
   		  type : 'POST',
   		  url : '<%=request.getContextPath() %>/courseManage/QueryHelp',
   		  dataType : 'JSON',
   		  data : queryData,
   		  success : function(){},
   		  error : function(){
   			  window.location.href = "/sisms/courseManage/Query"
   			  window.event.returnValue=false;
   		  }
   	  });
   	  
     }
      
      /*用户-编辑*/
  	function member_edit(courseNumber,title,url,w,h){
  		
  		var courseDate={
  	      		  courseNumber:courseNumber
  	        }
  	        $.ajax({
  	      	  type:"POST",
  	      	  url:"<%=request.getContextPath() %>/courseManage/jcEditHelp",
  	      	  data:courseDate,
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



  	/*批量删除*/
    function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            var postData={
            		"deleteData":data,
            }
            
            $.ajax({
            	async : false,
            	cache : false,
            	type : 'POST',
            	url : '<%=request.getContextPath() %>/courseManage/deleteAll',
            	dataType : "JSON",
            	data :postData,
            	traditional :true,
            	error : function(){
            	},
            	success : function(){
            	},
            });
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();
    </script>
  </body>

</html>