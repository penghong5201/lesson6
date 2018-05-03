<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<lesson:page title="欢迎">
    <div class="container">
        <form class="form-horizontal">
            <%--<div class="form-group">--%>
                <%--<lesson:citySelect fieldName="cityId" selectedCityId="141" width="250px" />--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<lesson:citySelect fieldName="cityId" selectedCityId="10174" label="City" />--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<lesson:monetarySelect fieldName="monetary" selectedMonetaryId="3" />--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<lesson:area fieldName="areaId" selectedAreaId="2" />--%>
            <%--</div>--%>
            <div class="form-group">
                <lesson:areaNavigation url="tagTest.do" />
            </div>
            <div class="form-group">
                <lesson:cityNavigation url="tagTest.do" companyType="scene" areaId="${param.area}" />
            </div>
            <%--<div class="form-group">--%>
                <%--<lesson:countrySelect fieldName="country" selectedCountryId="3" />--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<lesson:countrySelect fieldName="country" selectedCountryId="3" fieldStyleClass="form-control" multiple="true" />--%>
            <%--</div>--%>
        </form>
    </div>
</lesson:page>