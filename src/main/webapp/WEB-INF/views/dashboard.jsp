<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<lesson:page title="欢迎">

    <jsp:attribute name="script">
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>
    </jsp:attribute>
    <jsp:body>
        <%@include file="/WEB-INF/views/common/topMenu.jsp" %>
        <h1>${msg}</h1>
    </jsp:body>
</lesson:page>