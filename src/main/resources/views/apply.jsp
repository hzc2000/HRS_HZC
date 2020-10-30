<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<div  id="main" class="main">

    <table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
        <tr>
            <th>ID</th>
            <th>房屋图片</th>
            <th>房屋标题</th>
            <th>房屋租金</th>
            <th>租房人</th>
            <th>租房人出价</th>
            <th>租房人留言</th>
            <th>租房申请时间</th>
            <th>操作</th>
        </tr>


        <tr id="tr_id" v-for="r in rows">
            <td id="td_id">{{r.id}}</td>
            <td><img v-bind:src="r.house.image" width="50"  class="img-rounded" height="50"/></td>
            <td><p>{{r.house.title}}</p></td>
            <td><p>{{r.house.monthlyRent}}</p></td>
            <td><p>{{r.tenant.name}}</p></td>
            <td><p>{{r.offerPrice}}元/月</p></td>
            <td><p>{{r.leaveMessage}}</p></td>
            <td><p>{{r.createTime}}</p></td>
            <td>
                <button class="btn btn-success"  v-bind:value="r.id" onclick="SignContract($(this).val())">签订合同</button>
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

            转到第<input type="text" id="pageInput"   />页
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



</body>
<script>
    var vm = new Vue({
        el: '#main',
        data:{
            applyId:0,
            rows:"",
            totalPage:0,
            totalSize:0,
            pageNumber:1,
            pageSize:10,
        }
    });

    var applyId = "";
    var user ='${user}';
    var role = '${user.role}';
    function  SignContract(applyId) {
     vm.applyId = parseInt(applyId);
        if(role === "LANDLORD")
        {
            window.location.href= "${pageContext.request.contextPath}/contract/add";
            $.ajax({
                url: "${pageContext.request.contextPath}/contract" + "/SignContract",
                data: {
                    applyId: vm.applyId
                },
                type: "get",
                dataType: "json",
            });
            alert("完成");
        }
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
            url: "${pageContext.request.contextPath}/apply" + "/getAllByPageByLandlordId",
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
            url: "${pageContext.request.contextPath}/apply" + "/getTotalSizeByPageByLandlordId",
            data: {pageNumber: vm.pageNumber, pageSize: vm.pageSize},
            type: "post",
            dataType: "json",
        });
    }
    function getTotalPageByPage() {
        $.ajax({
            url: "${pageContext.request.contextPath}/apply" + "/getTotalPageByPageByLandlordId",
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
        if(role === "LANDLORD")
        {
        }else{
            alert("你好,请使用房东账号登录");
            window.location.href = "${pageContext.request.contextPath}/login";
        }
    });


</script>
</body>
<%@ include file="common/footer.jsp"%>
