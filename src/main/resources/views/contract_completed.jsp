<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<div  id="main" class="main">
<div>
    <table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
        <tr>
            <th>ID</th>
            <th>房屋图片</th>
            <th>租房者</th>
            <th>房东</th>
            <th>房屋标题</th>
            <th>合同开始时间</th>
            <th>合同结束时间</th>
            <th>每月需支付租金</th>
            <th>合同细则</th>
            <th>操作</th>
        </tr>


        <tr id="tr_id"  v-for="r in rows">
            <td id="td_id">{{r.id}}</td>
            <td><img v-bind:src="r.house.image" width="50" height="50" class="img-thumbnail"/></td>
            <td>{{r.tenant.name}}</td>
            <td>{{r.landlord.name}}</td>
            <td>{{r.house.title}}</td>
            <td>{{r.contractCreateTime}}</td>
            <td>{{r.contractEndTime}}</td>
            <td>{{r.apply.offerPrice}}</td>
            <td>{{r.contractContent}}</td>
            <td>
                <button class="btn btn-success"  v-if="role === 'TENANT' " v-bind:value="r.id" onclick="renewalContract($(this).val())">续签合同</button>
                <button class="btn btn-success"  v-bind:value="r.id" onclick="writeComment($(this).val())">写评论</button>
            </td>
        </tr>
    </table>
    <div class="">

        <div class="">
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

            转到第<input type="text" id="pageInput"/>页
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


</body>
<script>
    var vm = new Vue({
        el: '#main',
        data:{
            contractId:0,
            rows:"",
            totalPage:0,
            totalSize:0,
            pageNumber:1,
            pageSize:10,
            role:""
        }
    });

    var contractId = "";
    var user ='${user}';
     vm.role = '${user.role}';





    function  renewalContract(contractId) {
     vm.contractId = parseInt(contractId);
        if(vm.role === "TENANT")
        {

            $.ajax({
                url: "${pageContext.request.contextPath}/contract" + "/renewalContract",
                data: {
                    contractId: vm.contractId
                },
                success:function(data)
                {

                    window.location.href= "${pageContext.request.contextPath}/contract/renewalContract";
            },
                type: "get"
            });

        }
    }
    function  writeComment(contractId) {
        vm.contractId = parseInt(contractId);


            $.ajax({
                url: "${pageContext.request.contextPath}/comment" + "/writeComment",
                data: {
                    contractId: vm.contractId
                }, success:function(data)
                {
                    window.location.href= "${pageContext.request.contextPath}/comment/add";
                },
                type: "get"
            });


    }
    function toFristPage() {
        if(vm.pageNumber === 1){
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

    function getAllByPageNumber ()
    {
        $.ajax({
            url: "${pageContext.request.contextPath}/contract" + "/getAllCompletedContractByUserId",
            data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
            type: "post",
            dataType: "json",
            success: function (data) {
                vm.rows=data;
            }
        });
    }
    function getTotalSizeByPage() {
        $.ajax({
            url: "${pageContext.request.contextPath}/contract" + "/getCompletedContractTotalElementsByUserId",
            data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
            type: "post",
            dataType: "json",
            success: function (data) {
                vm.totalSize = data;

            }
        });
    }
    function getTotalPageByPage() {
        $.ajax({
            url: "${pageContext.request.contextPath}/contract" + "/getCompletedContractTotalPageByUserId",
            data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
            type: "post",
            dataType: "json",
            success: function (data) {
                vm.totalPage=data;
            }
        });
    }

    $(document).ready(function() {
        getAllByPageNumber();
        getTotalSizeByPage();
        getTotalPageByPage();

    });


</script>
</body>
<%@ include file="common/footer.jsp"%>
