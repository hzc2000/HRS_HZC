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

      <div>
          <label >租房者</label>
          <input   v-model="contract.tenant.name"  type="text"  disabled="disabled" placeholder=""/>
    </div>

    <div>
        <label >房东</label>
        <input   v-model="contract.landlord.name"  type="text" disabled="disabled"  placeholder=""/>
    </div>
            <div>
                <label >房屋标题</label>
                <input   v-model="contract.house.title" type="text" disabled="disabled"  placeholder=""/>
            </div>

            <div>
                <label >合同结束时间</label>
                <input  v-model="contractEndTime" type="datetime-local"  placeholder=""/>
            </div>

            <div>
                <label >每月需支付租金</label>
                <input  v-model="contract.apply.offerPrice"  type="text"  disabled="disabled"  placeholder=""/>
            </div>
      
            <div>
                <label >合同细则</label>
                <input  v-model="contract.contractContent" type="text"   disabled="disabled"  placeholder=""/>
            </div>
    <div>
        <div>
            <button onclick="submit()" class="btn btn-primary" >提交</button>
            <button onclick="cancel()" class="btn btn-primary">取消</button>
        </div>
    </div>
    </div>

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
            window.location.href="${pageContext.request.contextPath}/contract";
        }
        function submit(){
            if(vm.contractEndTime==="")
            {
                alert("请把合同结束时间精确到秒")
            }else{
                window.location.href="${pageContext.request.contextPath}/contract";
                $.ajax({
                    url: "${pageContext.request.contextPath}/contract/renewal" + "/addOne",
                    data: {
                        contractId:vm.contractId,
                        contractEndTime:vm.contractEndTime,
                    },
                    type: "post",
                    dataType: "json"
                });
            }
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

        $(document).ready(function() {
            vm.contractEndTime=new Date().format("yyyy-MM-ddThh:mm");
            vm.contractId = parseInt('${contractId}');
            getContract();
        });
        var vm = new Vue({
            el: '#main',
            data:{
                contractId:0,
                contract:"",
                contractEndTime:""
            }});

    </script>
</body>

<%@ include file="common/footer.jsp"%>