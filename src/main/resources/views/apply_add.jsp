<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
    <div id="main" class="center-block" >

            <div>
                <label for="offerPrice">出价：</label>
                <input v-model="offerPrice"  type="text"  id="offerPrice"  placeholder=""/>
            </div>


             <div>
               <label for="leaveMessage">对房主想说</label>
               <input  v-model="leaveMessage" type="text" id="leaveMessage"  placeholder=""/>
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
            houseId:0,
            leaveMessage:"",
            offerPrice:0
        }});
    function cancel(){
        window.location.href="${pageContext.request.contextPath}/index";
    }
function submit(){
    window.location.href="${pageContext.request.contextPath}/index";
    $.ajax({
        url: "${pageContext.request.contextPath}/apply/add" + "/addOne",
        data: {
            houseId: vm.houseId
            ,leaveMessage:vm.leaveMessage
            ,offerPrice:vm.offerPrice
            ,tenantId:vm.tenantId
        },
        type: "get",
        dataType: "json"
    });
}

    $(document).ready(function() {
        vm.tenantId = ${user.id};
        vm.houseId = ${houseId};
    });


</script>
</body>
<%@ include file="common/footer.jsp"%>