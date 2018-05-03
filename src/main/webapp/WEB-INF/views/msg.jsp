<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
/**
int minutes = 1440;
java.util.Date d = new java.util.Date();
String modDate = d.toGMTString();
String expDate = null;
expDate = (new java.util.Date(d.getTime() + minutes * 60000)).toGMTString(); 
response.setContentType("application/javascript");
response.setHeader("Last-Modified", modDate);
response.setHeader("Expires", expDate);
response.setHeader("Cache-Control", "public"); // HTTP/1.1
response.setHeader("Pragma", "Pragma"); // HTTP/1.0
*/
%>
var lesson_message = {
        prompt_msg: "<spring:message code="prompt.confirmDelete"/>",
		prompt_cancel: "<spring:message code="button.cancel"/>",
		prompt_confirm: "<spring:message code="button.confirm"/>"
};