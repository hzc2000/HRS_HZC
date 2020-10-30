<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
	.alt td{ background:black !important;}
</style>

<body>
<div  id="main" >
<div class="center-block">
			<div id="sd" >
				<div>
					<input type="button" class="btn-success"  value="添加押金信息" onclick="AddOne()">
					<table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
							<th>ID</th>
							<th>租客</th>
							<th>租客电话</th>
						    <th>租客押金</th>
						    <th>房东</th>
						    <th>房东电话</th>
							<th>房东押金</th>
						</tr>


						<tr id="tr_id" v-for="r in rows">
							<td id="td_id">{{r.id}}</td>
							<td><p>{{r.tenant.name}}</p></td>
							<td><p>{{r.tenant.phone}}</p></td>
							<td><p>{{r.tenantDeposit}}</p></td>
							<td><p>{{r.landlord.name}}</p></td>
							<td><p>{{r.landlord.phone}}</p></td>

							<td><p>{{r.landlordDeposit}}</p></td>
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
</div>
		<script>

			function AddOne() {
window.location.href="${pageContext.request.contextPath}/deposit/add";
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
        url: "${pageContext.request.contextPath}/deposit" + "/getAllDepositPageByAdministratorId",
        data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
        type: "post",
        
        dataType: "json",
        success: function (data) {
			if(data.length === 0)
			{
				alert("目前还没有押金信息");
			}
            vm.rows=data;

        }

    });


}
function getTotalSizeHouseByPage() {
    $.ajax({
        url:"${pageContext.request.contextPath}/deposit" + "/getDepositTotalElementsByAdministratorId",
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
                url: "${pageContext.request.contextPath}/deposit"+ "/getDepositTotalPageByAdministratorId",
                data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
                type: "post",
                
                dataType: "json",
                success: function (data) {
                    vm.totalPage=data;
                }
            });
            }

			$(document).ready(function() {
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
					houseId:0
				}
			});


		</script>
</body>

<%@ include file="common/footer.jsp"%>