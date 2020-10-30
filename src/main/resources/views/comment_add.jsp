<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
    <div id="main" class="center-block" >
<%--        如果房东评论--%>
            <div v-if="tenantId !== 0 ">
                <label >对房东的评价</label>
                <input v-model="tenantContent"  type="text"   placeholder=""/>
                <label >对房屋的评价</label>
                <input v-model="houseContent"  type="text"   placeholder=""/>
            </div>


    <div v-if="landlordId !== 0  ">
        <label>对租客的评价</label>
               <input  v-model="landlordContent" type="text"  placeholder=""/>
    </div>


        <div>
            <button onclick="submit()" class="btn btn-primary" >提交</button>
            <button onclick="cancel()" class="btn btn-primary">取消</button>
         </div>

    </div>
<script>
    var vm = new Vue({
        el: '#main',
        data:{
            tenantId:0,
            landlordId:0,
            tenantContent:"",
            landlordContent:"",
            houseContent:"",
            contractId:0,
        }});
    function cancel(){
        window.location.href="${pageContext.request.contextPath}/index";
    }

    function getContract ()
    {
        $.ajax({
            url: "${pageContext.request.contextPath}/contract" + "/getContract",
            data: {contractId:vm.contractId},
            type: "post",
            dataType: "json",
            success: function (data) {
                vm.contract=data;
            }
        });
    }


function submit(){
    if(role ==="TENANT")
    {
        window.location.href="${pageContext.request.contextPath}/index";
        $.ajax({
            url: "${pageContext.request.contextPath}/comment/add" + "/TenantAddOne",
            data: {
                tenantContent:vm.tenantContent
                ,houseContent:vm.houseContent
                ,contractId:vm.contractId
            },
            type: "post",
            dataType: "json"
        });
    }
    if(role ==="LANDLORD")
    {
        window.location.href="${pageContext.request.contextPath}/index";
        $.ajax({
            url: "${pageContext.request.contextPath}/comment/add" + "/LandlordAddOne",
            data: {
                landlordContent:vm.landlordContent
                ,contractId:vm.contractId
            },
            type: "post",
            dataType: "json"
        });
    }


}
    var role = '${user.role}';
    $(document).ready(function() {

        if(role ==="TENANT")
        {
            vm.tenantId =parseInt('${user.id}') ;
        }
        if(role ==="LANDLORD")
        {
            vm.landlordId = parseInt('${user.id}');
        }

         vm.contractId = parseInt('${contractId}');
        getContract();
    });


</script>
</body>
<%@ include file="common/footer.jsp"%>