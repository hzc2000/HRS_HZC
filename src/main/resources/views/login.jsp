<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="common/header.jsp"%>
<script type="text/javascript">

</script>
    <div class="center-block">
        <div class="col-3">

        </div>
        <div class="col-6">
        <sf:form modelAttribute="user"
                 action="${pageContext.request.contextPath}/login/handle"
                 method="post">

            <div class="form-group">
                <label for="phone">电话：</label>
                <sf:input path="phone" type="text"
                          cssClass="form-control"
                         
                          id="phone" aria-describedby="helpId" placeholder="" />
                <sf:errors path="phone" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <sf:password path="password"
                             cssClass="form-control"
                            
                             id="password" aria-describedby="helpId" placeholder="" />
                <sf:errors path="password" cssClass="error"/>
            </div>
            <div class="form-group">
                <div>角色：</div>
                <div class="form-check">
                    <label class="form-check-label">
                        <sf:radiobutton id="LANDLORD" path="role" value="LANDLORD"  cssClass="form-check-input" checked="checked" />
                        房东
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <sf:radiobutton id="TENANT" path="role" value="TENANT"  cssClass="form-check-input" />
                        租房者
                    </label>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <sf:radiobutton id="ADMINISTRATOR" path="role" value="ADMINISTRATOR"  cssClass="form-check-input" />
                        管理员
                    </label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">登录</button>
        </sf:form>
            </div>

    </div>
<%@ include file="common/footer.jsp"%>