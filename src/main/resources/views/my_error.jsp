<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>my exception</title>
</head>
<body>
${message}
<H1>自定义异常错误：</H1>${ex.message}
<H2>错误内容：</H2>
${ex.cause}
</body>
</html>
