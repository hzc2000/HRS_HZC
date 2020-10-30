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
					<table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
							<th>ID</th>
							<th>租客</th>
							<th>房东</th>
							<th>房屋标题</th>
							<th>该租客对房屋的评价</th>
							<th>该租客对房东的评价</th>
							<th>租客评论时间</th>
						</tr>


						<tr id="tr_id" v-for="r in rows">
							<td id="td_id">{{r.id}}</td>
							<td><p>{{r.tenant.name}}</p></td>
							<td><p>{{r.landlord.name}}</p></td>
							<td><p>{{r.house.title}}</p></td>
							<td><p>{{r.houseContent}}</p></td>
							<td><p>{{r.tenantContent}}</p></td>
							<td><p>{{r.tenantContentCreateTime}}</p></td>
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
        url: "${pageContext.request.contextPath}/comment" + "/getAllComment",
        data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
        type: "post",
        
        dataType: "json",
        success: function (data) {
			if(data.length === 0)
			{
				window.location.href="${pageContext.request.contextPath}/index";
				alert("该房屋目前没有评论");
			}
            vm.rows=data;

        }

    });


}
function getTotalSizeHouseByPage() {
    $.ajax({
        url:"${pageContext.request.contextPath}/comment" + "/getCommentTotalElements",
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
                url: "${pageContext.request.contextPath}/comment"+ "/getCommentTotalPages",
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
				vm.houseId = HouseId;

            });
			var user ='${user}';
			var role = '${user.role}';
			var HouseId = parseInt('${houseId}');
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