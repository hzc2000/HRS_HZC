<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<div  id="main" class="main">

    <table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
        <tr>
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
            <td id="td_id">{{r.id}}</td>
            <td><img v-bind:src="r.image"  height="50" width="50" class="img-thumbnail"/></td>
            <td><p>{{r.community.name}}</p></td>
            <td><p>{{r.title}}</p></td>
            <td><p>{{r.houseAddress}}</p></td>
            <td><p>{{r.area}}㎡</p></td>
            <td><p>{{r.monthlyRent}}元/月</p></td>
            <td><p>{{r.unitType}}</p></td>
            <td><p>{{r.houseIntroduction}}</p></td>
            <td><p>{{r.flag}}</p></td>
            <td>
                <button class="btn btn-success"  v-bind:value="r.id" onclick="browseComment($(this).val())">查看评论</button>
                <button class="btn btn-success"  v-bind:value="r.id" onclick="rent($(this).val())">我想租用</button>
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


    function  rent(houseId) {
        vm.houseId = houseId;
        var r=confirm("是否要租用该房？");
        if (r==true)
        {
        $.ajax({
            url: "${pageContext.request.contextPath}/house" + "/rentThis",
            data: {houseId: vm.houseId},
            type: "get",
            dataType: "json",
            success: function (data) {

            }
        });
        window.location.href = "${pageContext.request.contextPath}/apply/add";

    }
    }
    function  browseComment(houseId) {
        vm.houseId = houseId;
        var b=confirm("是否要浏览该房屋的评论？");
        if (b==true)
        {
            $.ajax({
                url: "${pageContext.request.contextPath}/comment" + "/browseComment",
                data: {houseId: vm.houseId},
                type: "get"
            });
            window.location.href = "${pageContext.request.contextPath}/comment";
        }


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
        vm.pageNumber = parseInt($("#pageInput").val());
        getByPageNumber();
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
            url: "${pageContext.request.contextPath}/house" + "/getAllHouseByPage",
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
            url: "${pageContext.request.contextPath}/house" + "/getTotalSizeHouseByPage",
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
            url: "${pageContext.request.contextPath}/house" + "/getTotalPageHouseByPage",
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
var houseId = "";
    var vm = new Vue({
        el: '#main',
        data:{
            houseId:0,
            rows:"",
            totalPage:0,
            totalSize:0,
            pageNumber:1,
            pageSize:10,
        }
    });

    console.log(vm.rows);
</script>
</body>
<%@ include file="common/footer.jsp"%>
