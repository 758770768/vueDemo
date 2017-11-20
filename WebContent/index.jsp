<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-table.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/vue.js"></script>
</head>
<body>

	<script type="text/javascript">
		$(function() {

			/* 定义vue实例及數據初始化 */
			var a = new Vue({
				/* 绑定页面元素id */
				el : "#d",
				/* 页面数据 */
				data : {
					trs : "",
					/* 莫泰框的显示标记貌似咩用*/
					u_modal : false,
					/*  修改行的数据*/
					mm : "",
					/* 获取部门数据 */
					dept : ""
				},
				methods : {
					/* 修改 按钮事件传递循环中的数据 */
					update : function(h) {
						/* 请求部门数据 */
						$.ajax({
							url : "deptServlet",
							data : {
								method : "selectAll"
							},
							success : function(data) {
								/* 部门信息封装到vue中 */
								a.dept = data;
							}
						});
						//alert(123);
						a.u_modal = true;
						//alert(h.eid);
						/* 要修改的当前行数据绑定到vue中 */
						a.mm = h;
						//console.log(mm);
						$(".modal").modal("show");

					},
					/* 删除按钮事件 */
					del : function(h) {
						/* //alert(234);
						alert(h); */
						$.ajax({
							url : "empServlet?method=del",
							data : {
								eid : h.eid
							},
							success : function(data) {
								alert("yes");
								queryData();
							}
						});

					},
					/* 单选按钮激活类数据计算 */
					sexCom : function(v, f) {
						/* 激活男 */
						if (f == v) {
							return "btn btn-info active";
						}
						return "btn btn-info";

					},
					updateConfirm : function() {
						$(".modal").modal("hide");
						console.log($("form").serialize());
						$.ajax({
							url : "empServlet?method=update",
							data : $("form").serialize(),
							success : function(data) {
								//alert("ok");
								queryData();
							}
						});
					}
				}

			});

			/* 请求数据方法 */
			function queryData() {
				$.ajax({
					url : "Servlet01",
					data : {
						offset : "0",
						limit : "10",
						sort : "eid",
						order : "asc"
					},
					/* 响应成功执行行数 */
					success : function(data) {
						//alert(a);
						/* 服务器与前台数据绑定 */
						a.trs = data.rows;
					}
				});
			}

			/* 请求分页数据 */
			queryData();

			/*     	   $("button:contains('修改')").on("click",function(){
			    		    alert($(this).parent().preAll().eq(0).text());
			    	   });
			 */

		});
	</script>


	<div class="container" id="d">
		<div class="panel panel-default panel-info">
			<div class="panel-title ">
				<h2 class="text-center text-info">雇员管理单</h2>
			</div>
		</div>
		<table class="table table-hover">
			<tr v-for="i in trs">
				<td>{{i.eid}}</td>
				<td>{{i.ename}}</td>
				<td>{{i.city}}</td>
				<td>{{i.sex}}</td>
				<td>{{i.dname}}</td>
				<td>{{i.bdate}}</td>
				<td>{{i.bdate}}</td>
				<td><button class="btn btn-success" @click="update(i)">修改</button>
					<button class="btn btn-danger" @click="del(i)">删除</button></td>
			</tr>
			<span v-show="u_modal"> {{u_modal}} </span>
		</table>


		<!-- 修改模态框 -->
		<div class="modal fade update" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Modal title</h4>
					</div>
					<div class="modal-body">
						<form action="#" class="form-horizontal">
							<div v-for="(value,key) in mm">
								<!-- 部门 -->
								<div v-if="key=='dname'" class="form-group">
									<label class="control-label col-xs-2">部门:</label>
									<div class="col-xs-10">
										<select name="did" class="form-control">
											<option v-for="d in dept" :selected="d.dname==value"
												:value="d.did">{{d.dname}}</option>
										</select>
									</div>
								</div>
								<!-- 性别 -->
								<div v-else-if="key=='sex'" class="form-group">
									<label class="control-label col-xs-2">性别:</label>
									<div class="col-xs-4 col-xs-offset-2  btn-group"
										data-toggle='buttons'>
										<label :class="sexCom(value,'M')"> <input type="radio"
											:name="key" value="M" :checked="value=='M'">绅士
										</label> <label :class="sexCom(value,'F')"> <input
											type="radio" :name="key" value="F" :checked="value=='F'">女士
										</label>
									</div>
								</div>
								<!-- 取消did的显示 -->
								<div v-else-if="key=='did'"></div>
								<!--其他数据为普通表单数据  -->
								<div v-else>
									<div class="form-group">
										<label class="col-xs-2 control-label">{{key}}:</label>
										<div class="col-xs-10">
											<input class="form-control" type="text" :name="key"
												:value="value" />
										</div>
									</div>
								</div>
							</div>
							<button class="btn btn-primary">submit</button>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							@click="updateConfirm">提交修改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</div>
</body>
</html>