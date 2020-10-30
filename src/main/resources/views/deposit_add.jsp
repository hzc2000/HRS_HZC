<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
    <div id="main" class="center-block" >

            <div>
                <label for="offerPrice">租客电话：</label>
                <input v-model="tenantPhone"  type="text"  id="offerPrice"  placeholder=""/>
            </div>


             <div>
               <label for="leaveMessage">房东电话</label>
               <input  v-model="landlordPhone" type="text" id="leaveMessage"  placeholder=""/>
             </div>
        <div>
            <label for="leaveMessage">租客押金</label>
            <input  v-model="tenantDeposit" type="text" id="leaveMessage"  placeholder=""/>
        </div>
        <div>
            <label for="leaveMessage">房东押金</label>
            <input  v-model="landlordDeposit" type="text" id="leaveMessage"  placeholder=""/>
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
            tenantPhone:"",
            landlordPhone:"",
            tenantDeposit:0,
            landlordDeposit:0
        }});
    function cancel(){
        window.location.href="${pageContext.request.contextPath}/deposit";
    }
function submit(){

    $.ajax({
        url: "${pageContext.request.contextPath}/deposit/add" + "/addOne",
        data: {
            tenantPhone: vm.tenantPhone
            ,landlordPhone:vm.landlordPhone
            ,tenantDeposit:vm.tenantDeposit
            ,landlordDeposit:vm.landlordDeposit
        },
        success:function(data){
            alert("添加押金信息成功");
                window.location.href="${pageContext.request.contextPath}/deposit";
        },
        type: "post",
        dataType: "json"
    });
}
var role = '${user.role}';
    $(document).ready(function() {
        if(role === 'ADMINISTRATOR')
        {}else{
            alert("请使用管理员账户进入该页面");
            window.location.href="${pageContext.request.contextPath}/login";
        }

    });


</script>
</body>
<%@ include file="common/footer.jsp"%>