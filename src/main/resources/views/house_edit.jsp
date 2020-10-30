
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<script type="text/javascript">

</script>
<body>
    <div id="main" >


            <div>
                <label for="title">小区：</label>
                <select id="s1" v-model="House.community">
                    <option  v-for="community in Communitys" :value="community" >
                        {{community.name}}
                    </option>
                </select>
            </div>




            <div >
                <label for="title">标题：</label>
                <input v-model="House.title"  type="text"  id="title"  placeholder=""/>
            </div>


            <div >
                <label for="houseAddress">地址：</label>
                <input v-model="House.houseAddress"  type="text"   id="houseAddress"  placeholder=""/>
            </div>
            <div >
                <label for="area">租房面积：(单位: 平方米)</label>
                <input v-model="House.area" id="area"  type="text" placeholder=""/>
            </div>
            <div>
                <label for="monthlyRent">月租金：(单位: 元/月)</label>
                <input v-model="House.monthlyRent" type="text" id="monthlyRent"  placeholder=""/>
            </div>
            <div >
                <label for="unitType">户型：(格式: 几室几厅几卫)</label>
                <input v-model="House.unitType"  type="text" id="unitType"  placeholder=""/>
                
                </div>
             <div>
               <label for="houseIntroduction">房屋简介</label>
               <input  v-model="House.houseIntroduction" type="text" id="houseIntroduction"  placeholder=""/>
                
             </div>

                <div class="form-check">
                    <label for="s3">状态:</label>
                        <select id="s3" v-model=" House.flag" >
                            <option  disabled="disabled" value="已配租">已配租</option>
                            <option  value="待出租">待出租</option>
                            <option  value="未发布">未发布</option>
                        </select>
                </div>

        <div>
            <button onclick="submit()" class="btn btn-primary" >提交</button>
            <button onclick="cancel()" class="btn btn-primary">取消</button>
         </div>

    </div>
<script>

    var houseId="";
    var house = "";
    var communitys = "";
    function cancel(){
        window.location.href="${pageContext.request.contextPath}/house/house_edit";
    }
    function cancel(){
        window.location.href="${pageContext.request.contextPath}/house";
    }
    function submit(){
        console.log(vm.House);
        $.ajax({
            url: "${pageContext.request.contextPath}/house/house_edit/submit",
            data:
                JSON.stringify(vm.House)
            ,
            type: "post",
            contentType: "application/json;charset=utf-8",
            dataType: "json",

            success: function (data) {
                if(data!==null)
                {
                    window.location.href = "${pageContext.request.contextPath}/house";
                }


            }
        });
    }

    $(document).ready(function() {
        houseId=${houseId};

        $.ajax({
            url: "${pageContext.request.contextPath}/house/house_edit/getAllCommunity",
            data: {},
            type: "get",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (data) {
                if(data!=null)
                {
                    communitys=data;
                    vm.Communitys=communitys;
                    console.log(vm.Communitys);
                }


            }
        });


        if(houseId !==-1)
        {
            $.ajax({
                url: "${pageContext.request.contextPath}/house/house_edit/getOneNullHouse",
                data: {},
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data!=null)
                    {
                        house=data;
                        vm.House=house;
                        console.log(vm.House);
                    }
                }
            });


        }





if(houseId !==-1)
        {

console.log("houseId ="+houseId);
            $.ajax({
                url: "${pageContext.request.contextPath}/house/house_edit/getOneHouse",
                data: {id: houseId},
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data!=null)
                    {
                        house=data;
                        vm.House=house;
                        console.log(vm.House);
                    }
                }
            });
        }
    });
    var vm = new Vue({
        el: '#main',
        data:{
            Communitys:"",
            House:"",
        }});

</script>
</body>
<%@ include file="common/footer.jsp"%>