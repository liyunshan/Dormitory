<%@include  file="/WEB-INF/jsp/util/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="<c:url value='/style/style.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/style/slider.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/style/slideViewer.css'/>" rel="stylesheet" type="text/css" />
        <title><spring:message code="generic.title" /></title>
    </head>
    <body>
        <h1>${intro}</h1>
    </body>
</html>