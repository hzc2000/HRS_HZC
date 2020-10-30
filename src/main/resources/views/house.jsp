<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

	//定义变量，保存修改了的标签
	var editedOne=false;
	//定义变量，保存选中的标签
	var selectOne=false;



	$(document).ready(function(){




		var liObj=document.getElementsByTagName("td");
		for(var i=0;i<liObj.length;i++)
		{
			liObj[i].onclick = liChoose;
		}
		selectOne.ondblclick = editThisTd;

	/** 用户角色   **/
	var userRole = '';

	function editThisTd()
	{
		if(selectOne)
		{
			//创建一个文本框
			var newOne = document.createElement("input");
			newOne.type = "text";
			//将文本框内容设置为li标签的文本值
			newOne.value = selectOne.innerHTML;
			//将文本框添加到li标签内部
			selectOne.removeChild(selectOne.childNodes[0]);
			selectOne.appendChild(newOne);
			editedOne = true;
		}
	}
	//高亮选中的标签
	function liChoose()
	{
		var ul = document.getElementsByTagName("td");
		for (var i = 0; i < ul.length; i++)
		{
			ul[i].style.backgroundColor = "white";
		}
		this.style.backgroundColor = "yellow";
		selectOne = this;
	}

}
	)

</script>
<style>
	.alt td{ background:black !important;}
</style>

<body>
<div  id="main" >
<div class="center-block">
		<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
		<input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>

				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_bottom">
							<input type="button" value="新增"  class="btn btn-info" onclick="add()"  id="addBtn" />
							<input type="button" value="租房申请管理"  class="btn btn-info" onclick="Apply()"  id="applyBtn" />
						</div>
					</div>
				</div>
			</div>
			<div id="sd" >
				<div>
					<table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
							<th width="30"><input type="checkbox" id="all" onclick="" />
							</th>
							<th>ID</th>
							<th>图片</th>
							<th>小区</th>
							<th>标题</th>
							<th>地址</th>
							<th>租房面积</th>
							<th>月租金</th>
							<th>户型</th>
							<th>房屋简介</th>
							<th>状态</th>
							<th>操作</th>
						</tr>


						<tr id="tr_id" v-for="r in rows">
							<td>
								<input type="checkbox" name="IDCheck" v-bind:value="r.id" />
							</td>
							<td id="td_id">{{r.id}}</td>
							<td><img v-bind:src="r.image" width="50"  class="img-rounded" height="50"/></td>
							<td><p>{{r.community.name}}</p></td>
							<td><p>{{r.title}}</p></td>
							<td><p>{{r.houseAddress}}</p></td>
							<td><p>{{r.area}}㎡</p></td>
							<td><p>{{r.monthlyRent}}元/月</p></td>
							<td><p>{{r.unitType}}</p></td>
							<td><p>{{r.houseIntroduction}}</p></td>
							<td><p>{{r.flag}}</p></td>
							<td id="operation">
								<button class="btn btn-success" v-bind:value="r.id" onclick="edit($(this).val())">编辑</button>
								<button class="btn btn-success" v-bind:value="r.id" onclick="del($(this).val())" >删除</button>
							</td>
						</tr>
					</table>
				</div>
				<div class="ui_tb_h30">

					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						<input class="btn btn-default" type="button" value="首页"
							   onclick="toFristPage()"
						/>
						<input class="btn btn-default" type="button" value="上一页"
							   onclick="toPreviousPage()"
						/>
						<input class="btn btn-default" type="button" value="下一页"
							   onclick="toNextPage()"/>
						<input class="btn btn-default" type="button" value="尾页"
							   onclick="toLastPage()" />


						<!--     如果是最后一页，则只显示首页、上一页 -->

						转到第<input type="text"  id="pageInput"  />页
						<input type="button"  class="btn btn-default"  value="跳转" onclick="toPage()" />
						<div class="ui_flt" style="height: 30px; line-height: 30px;">
							共有
							<span class="ui_txt_bold04">{{totalSize}}</span>
							条记录，当前第
							<span class="ui_txt_bold04">{{pageNumber}}
						/
						{{totalPage}}</span>
							页
						</div>
					</div>
					</div>
			</div>
</div>

		<script>

            /** 修改 **/
            function edit(houseId){
                // 非空判断
                if(houseId === '') return;
                if(confirm("您确定要修改吗？")){
							window.location.href=window.location.href+"/house_edit/"+houseId;
					}

                }


            /** 删除 **/
            function del(houseId){
                // 非空判断

                if(houseId === '') return;
                var flag = confirm("您确定要删除吗？");
                if(flag === true){
					alert(houseId);
					vm.houseId = parseInt(houseId);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/house/" + "deleteOneHouseById",
                        data: {id: vm.houseId},
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if(data!=null)
                            {  alert("删除成功");
								window.location.href = "${pageContext.request.contextPath}/house";
                            }


                        }
                    });
                }
            }

			function add() {
				window.location.href="${pageContext.request.contextPath}/house/house_add";
			}
			function Apply() {
				window.location.href="${pageContext.request.contextPath}/apply";
			}


			function toFristPage() {
				if(vm.pageNumber === "1"){
					alert("已经是首页了");
				}else{
                    vm.pageNumber = 1;
                    getByPageNumber();
				}


			}
            function toPage() {
				if($("#pageInput").val()!==""){
					vm.pageNumber = parseInt($("#pageInput").val());
					getByPageNumber();
				}
            }

			function toLastPage() {
				if(vm.pageNumber === vm.totalPage)
				{
					alert("已经是尾页了");
				}else{
                    vm.pageNumber = vm.totalPage;
                    getByPageNumber();
				}

			}
			function toPreviousPage() {
				if( vm.pageNumber > 1){
                    vm.pageNumber = vm.pageNumber - 1;
                    getByPageNumber();
				}else{
					alert("已经是首页了");
				}
			}
			function toNextPage() {
				if(vm.pageNumber < vm.totalPage )
				{
                    vm.pageNumber = vm.pageNumber + 1;
                    getByPageNumber();
				}else{alert("已经是尾页了");}}

function getByPageNumber ()
{
    $.ajax({
        url: "${pageContext.request.contextPath}/house" + "/getAllHousePageByLandlordId",
        data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
        type: "post",
        
        dataType: "json",
        success: function (data) {
            vm.rows=data;
        }
    });

}
function getTotalSizeHouseByPage() {
    $.ajax({
        url:"${pageContext.request.contextPath}/house" + "/getHouseTotalElementsByLandlordId",
        data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
        type: "post",
        dataType: "json",
        success: function (data) {
            vm.totalSize = data;

        }
    });
}
            function getTotalPageHouseByPage() {
            $.ajax({
                url: "${pageContext.request.contextPath}/house"+ "/getHouseTotalPagesByLandlordId",
                data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
                type: "post",
                
                dataType: "json",
                success: function (data) {
                    vm.totalPage=data;
                }
            });
            }

			$(document).ready(function() {
				if(role === "TENANT" || role === "ADMINISTRATOR" )
				{
					alert("你好,管理房间信息请先注册一个房东账号");
					window.location.href = "${pageContext.request.contextPath}/register";
				}
                getByPageNumber();
                getTotalSizeHouseByPage();
                getTotalPageHouseByPage();

            });
			var user ='${user}';
			var role = '${user.role}';

			var vm = new Vue({
				el: '#main',
				data:{
					rows:"",
					totalPage:0,
					totalSize:0,
					pageNumber:1,
					pageSize:10,
				}
			});


		</script>
</body>

<%@ include file="common/footer.jsp"%>