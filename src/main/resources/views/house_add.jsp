
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<script type="text/javascript">

</script>
<body>
    <div id="main" >


            <div>
                <label for="title">小区：</label>
                <select id="s1" v-model="community">
                    <option  v-for="c in Communitys" :value="c" >
                        {{c.name}}
                    </option>
                </select>
            </div>




            <div >
                <label for="title">标题：</label>
                <input v-model="title"  type="text"  id="title"  placeholder=""/>
            </div>


            <div >
                <label for="houseAddress">地址：</label>
                <input v-model="houseAddress"  type="text"   id="houseAddress"  placeholder=""/>
            </div>
            <div >
                <label for="area">租房面积：(单位: 平方米)</label>
                <input v-model="area" id="area"  type="text" placeholder=""/>
            </div>
            <div>
                <label for="monthlyRent">月租金：(单位: 元/月)</label>
                <input v-model="monthlyRent" type="text" id="monthlyRent"  placeholder=""/>
            </div>
            <div >
                <label for="unitType">户型：(格式: 几室几厅几卫)</label>
                <input v-model="unitType"  type="text" id="unitType"  placeholder=""/>
                
                </div>
             <div>
               <label for="houseIntroduction">房屋简介</label>
               <input  v-model="houseIntroduction" type="text" id="houseIntroduction"  placeholder=""/>
                
             </div>

                <div class="form-check">
                    <label for="s3">状态:</label>
                        <select id="s3" v-model=" flag" >
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

    function cancel(){
        window.location.href="${pageContext.request.contextPath}/house/house_edit";
    }

    function submit(){
        console.log(vm.community);
        $.ajax({
            url: "${pageContext.request.contextPath}/house/house_add/addOneHouse",
            data:{
                flag:vm.flag,
                houseIntroduction:vm.houseIntroduction,
                unitType:vm.unitType,
                monthlyRent:vm.monthlyRent,
                area:vm.area,
                houseAddress:vm.houseAddress,
                communityId:vm.community.id,
                title:vm.title,
            },
            type: "post",
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


        $.ajax({
            url: "${pageContext.request.contextPath}/house/house_edit/getAllCommunity",
            data: {},
            type: "get",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (data) {
                if(data!=null)
                {
                    vm.Communitys=data;
                    console.log(vm.Communitys);
                }
            }
        });


    });
    var vm = new Vue({
        el: '#main',
        data:{
            Communitys:"",
            flag:"",
            houseIntroduction:"",
            unitType:"",
            monthlyRent:"",
            area:"",
            houseAddress:"",
            community:"",
            title:"",
        }});

</script>
</body>
<%@ include file="common/footer.jsp"%>