<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
    <div id="main" class="center-block" >
<%--        <button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown">--%>
<%--            选择网站--%>
<%--        </button>--%>
<%--        <div class="dropdown-menu">--%>
<%--            <a class="dropdown-item" href="https://www.google.com">GOOGLE</a>--%>
<%--            <a class="dropdown-item" href="https://www.runoob.com">RUNOOB</a>--%>
<%--            <a class="dropdown-item" href="https://www.taobao.com">TAOBAO</a>--%>
<%--        </div>--%>
    <table class="table table-bordered table-hover" cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
   
      <div>
          <label >租房者</label>
          <input   v-model="apply.tenant.name"  type="text"  disabled="disabled" placeholder=""/>
    </div>

    <div>
        <label >房东</label>
        <input   v-model="apply.landlord.name"  type="text" disabled="disabled"  placeholder=""/>
    </div>
            <div>
                <label >房屋标题</label>
                <input   v-model="apply.house.title" type="text" disabled="disabled"  placeholder=""/>
            </div>

            <div>
                <label >合同结束时间</label>
                <input  v-model="contractEndTime" type="datetime-local"  placeholder=""/>
            </div>

            <div>
                <label >每月需支付租金</label>
                <input  v-model="apply.offerPrice" class="form-control" type="text"   placeholder=""/>
            </div>
      
            <div>
                <label >合同细则</label>
                <input  v-model="contractContent" class="form-control" type="text"   placeholder=""/>
            </div>
    <div>
        <div>
            <button onclick="submit()" class="btn btn-primary" >提交</button>
            <button onclick="cancel()" class="btn btn-primary">取消</button>
        </div>
    </div>
    </table>
    </div>
</body>
<script>
    Date.prototype.format = function (format) {
        var args = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var i in args) {
            var n = args[i];
            if (new RegExp("(" + i + ")").test(format))
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
        }
        return format;
    };

    function cancel(){
        window.location.href="${pageContext.request.contextPath}/apply";
    }
    function submit(){
        if(vm.contractEndTime==="")
        {
            alert("请把合同结束时间精确到秒")
        }else{
            window.location.href="${pageContext.request.contextPath}/apply";
            $.ajax({
                url: "${pageContext.request.contextPath}/contract/add" + "/addOne",
                data: {
                    applyId:vm.applyId,
                    contractEndTime:vm.contractEndTime,
                    contractContent:vm.contractContent,
                    contractTenantId:vm.apply.tenant.id,
                    contractLandlordId:vm.apply.landlord.id,
                    contractHouseId:vm.apply.house.id
                },
                type: "post",
                dataType: "json"
            });
        }
    }
    function getApply ()
    {
        $.ajax({
            url: "${pageContext.request.contextPath}/apply" + "/getApply",
            data: {applyId:vm.applyId},
            type: "post",
            dataType: "json",
            success: function (data) {
                vm.apply=data;
            }
        });
    }
    $(document).ready(function() {
        vm.landlordId =parseInt('${user.id}');
        vm.applyId = parseInt('${applyId}');
        getApply();
        vm.contractEndTime=new Date().format("yyyy-MM-ddThh:mm");
    });
    var vm = new Vue({
        el: '#main',
        data:{
            landlordId:0,
            applyId:0,
            apply:"",
            contractContent:"",
            contractEndTime:""
        }});

</script>
<%@ include file="common/footer.jsp"%>