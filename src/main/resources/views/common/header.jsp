<%@ page contentType="text/html;charset=UTF-8"
         isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<head>
    <div class="my-container">
        <title>${title}</title>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Main CSS -->
        <link rel="stylesheet"  href="${ctx}/css/main.css" >
        <!-- Others CSS -->

        <link rel="stylesheet"  href="${ctx}/css/util.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css?v=1572311855">
        <link rel="stylesheet" href="${ctx}/css/animate.min.css?v=1572311855">
        <link rel="stylesheet" href="${ctx}/css/main.css?v=1572311855">

        <link rel="stylesheet" href="${ctx}/css/swiper.min.css?v=1572311855">
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="${ctx}/js/jquery.min.js"></script>
        <script src="${ctx}/js/popper.min.js"></script>
        <script src="${ctx}/js/bootstrap.min.js"></script>
        <script src="${ctx}/js/anime.min.js"></script>
        <script src="${ctx}/js/vue.js"></script>
        <script src="${ctx}/js/swiper.min.js?v=1572311855"></script>
        <link rel="stylesheet" href="${ctx}/css/index.css?v=1572311855">
        <div class="row">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand" href="${ctx}/index">
                    <img src="${ctx}/images/zufang.png" width="40" height="40">
                    租房网 &emsp;&emsp; 你好：${user.name}
                </a>

                <div class="" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/index">网站首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/house">房屋管理</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/contract">合同</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/administrator">管理员操作</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/register">注册</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">登录</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">退出系统</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</head>

