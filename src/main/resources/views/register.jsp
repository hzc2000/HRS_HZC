<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
    .tip {  position: absolute; left: 20px; top: 52px; font-size: 1px; color: #f50; }
</style>
<div class="limiter">
    <div class="container-login100" style="background-image: url('${ctx}/images/bg-01.jpg');">
        <div class="wrap-login100 p-l-100 p-r-100 p-t-100 p-b-100">
            <sf:form modelAttribute="user"
                     action="${pageContext.request.contextPath}/register/handle"
                     method="post"
                     class="login100-form"
            >


                <span class="login100-form-title p-b-49">注册</span>

                <div class="wrap-input100 " >
                    <span class="label-input100 ">手机号:</span>
                    <sf:input path="phone" type="text"
                              cssClass="input100"

                              id="phone" aria-describedby="helpId" placeholder="请输入手机号" />
                    <sf:errors path="phone" cssClass="tip"/>
                </div>

                <div class="wrap-input100" >
                    <span class="label-input100">用户名:</span>
                    <sf:input path="name" type="text"
                              cssClass="input100"

                              id="name" aria-describedby="helpId" placeholder="请输入用户名" />
                    <sf:errors path="name" cssClass="tip"/>
                </div>
                <div class="wrap-input100">
                    <span class="label-input100 ">密码:</span>
                    <ul>
                        <li>
                                <sf:password path="password"
                                             cssClass="input100"

                                             id="password" aria-describedby="helpId" placeholder="请输入密码" />
                                <sf:errors path="password" cssClass="tip"/>
                        <li>


                    </ul>
                </div>



                <div class="form-group">
                    <div>角色：</div>
                    <div class="wrap-input40">
                        <label class="label-input100">
                            <sf:radiobutton id="LANDLORD" path="role" value="LANDLORD"  cssClass="wrap-input40-input" checked="checked" />
                            房东
                        </label>
                    </div>
                    <div class="wrap-input40">
                        <label class="label-input100">
                            <sf:radiobutton id="TENANT" path="role" value="TENANT"  cssClass="wrap-input40-input" />
                            租房者
                        </label>
                    </div>
                    <div class="wrap-input40">
                        <label class="label-input100">
                            <sf:radiobutton id="ADMINISTRATOR" path="role" value="ADMINISTRATOR"  cssClass="wrap-input40-input" />
                            管理员
                        </label>
                    </div>
                </div>

                <div class="text-right p-t-8 p-b-31">
                    <a href="javascript:">忘记密码？</a>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button type="submit"  class="login100-form-btn">注 册</button>
                    </div>
                </div>






                <div class="flex-col-c p-t-25">
                    <a href="javascript:" class="txt2">立即登录</a>
                </div>
            </sf:form>


        </div>
    </div>

</div>

<%@ include file="common/footer.jsp"%>
