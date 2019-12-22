<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${page_title}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- -Bootstrap css file -->
<link href="<c:url value="/assets/bootstrap4/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
<!-- - css file -->
<link href="<c:url value="/assets/css/main.css"/>" rel="stylesheet" type="text/css"/>


<!-- -----End -->

<%-- <title><c:set var="req" value="${pageContext.request}" /> - ${page_title}</title> --%>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="top_nav.jsp"></jsp:include>
<jsp:include page="side_menu.jsp"></jsp:include> 
    


