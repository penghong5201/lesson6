<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="tripId" value="${trip.id}"/> 
<c:if test="${not empty tripId}">

		<lesson:sidebar icon="fa fa-list">
			<c:choose>
				<c:when test="${trip.status.value!=5}">
	            <li id="trip-code-menu" class="hover">
	            	<a href="trip/trip3.do?tripId=${tripId}&topMenu=true">
	            	  <span class="current-team-code">${trip.teamCode}&nbsp;(<spring:message code="trip.status.${trip.status}"/>)</span>
                      <span class="origin-team-code">${trip.orginalTeamCode}</span>
	            	</a>
	            </li>
	            </c:when>
	            <c:otherwise>
	            	<lesson:singleMenu text="trip.menu.itinerary" link="trip/trip3.do?tripId=${tripId}&quotationId=${param.quotationId}&topMenu=true" />
	            </c:otherwise>
            </c:choose>
            <c:if test="${trip.status.value!=5}">
            	<lesson:singleMenu text="trip.menu.specify" link="trip/trip4.do?tripId=${tripId}&topMenu=true" />
            </c:if>
            
            <c:choose>
				<c:when test="${empty param.quotationId}">
	           		<lesson:singleMenu text="trip.menu.arrangement" link="trip/line/line.do?tripId=${tripId}&topMenu=true" />
	            </c:when>
	            <c:otherwise>
	            	<lesson:singleMenu text="trip.menu.arrangement" link="trip/line/line.do?tripId=${tripId}&quotationId=${param.quotationId}&topMenu=true" />
	            </c:otherwise>
            </c:choose>
			<c:if test="${trip.status.value==5}">
				<lesson:singleMenu text="trip.menu.calculation" link="quotations/generateByTrip.do?tripId=${tripId}&quotationId=${param.quotationId}" />
			</c:if>
            <c:if test="${trip.status.value!=5}">
	            <c:if test="${opt_invoice}">
	            <lesson:singleMenu text="trip.menu.invoice" link="support/receivable/listNeedReceivable.do?tripId=${tripId}" />
	            </c:if>
	            <c:if test="${opt_room}">
	            <lesson:singleMenu title="trip.menu.hotel" icon="fa fa-hotel" link="support/room/listNeedRooms.do?tripId=${tripId}" />
	            </c:if>
	            <c:if test="${opt_coach}">
	            <lesson:singleMenu title="trip.menu.coach" icon="fa fa-bus" link="support/coach/listNeedCoachs.do?tripId=${tripId}" />
	            </c:if>
	            <c:if test="${opt_meal}">
	            <lesson:singleMenu title="trip.menu.meal" icon="fa fa-cutlery" link="support/meal/listNeedMeals.do?tripId=${tripId}" />
	            </c:if>
	            <c:if test="${opt_guide}">
	            <lesson:singleMenu title="trip.menu.guide" icon="fa fa-bullhorn" link="support/guide/listNeedGuides.do?tripId=${tripId}" />
	            </c:if>
	            <c:if test="${opt_ticket}">
	            <lesson:singleMenu title="trip.menu.ticket" icon="fa fa-ticket" link="support/ticket/listNeedTickets.do?tripId=${tripId}" />
	            </c:if>
	            <c:if test="${opt_fee}">
	            <lesson:singleMenu title="trip.menu.others" icon="fa fa-paperclip" link="support/fee/listNeedFees.do?tripId=${tripId}" />
	            </c:if>
	            
	            <c:if test="${opt_payNotice || trip.loginUserIsCurrentOP}">
	            <lesson:singleMenu title="trip.menu.payment" icon="fa fa-credit-card" link="support/paynotice/search.do?tripId=${tripId}" />
	            </c:if>
	            <lesson:singleMenu title="trip.menu.arrange" icon="fa fa-tasks" link="support/arrange/list.do?tripId=${tripId}" />
	            <lesson:singleMenu title="trip.menu.messageboard" icon="fa fa-comment" link="trip/messageboard/list.do?tripId=${tripId}" />
			</c:if>

			<c:if test="${opt_trip_view}">
			<lesson:menuGroup text="trip.menu.documents" icon="fa fa-file-text-o" styleClass="trip-top-menu-group">
				<lesson:singleMenu link="doc/word/trip/finalItinerary.do?tripId=${tripId}&withChineseAttraction=false" text="trip.menu.final.itinerary" icon="fa fa-file-word-o" />
                <lesson:singleMenu link="doc/word/trip/finalItinerary.do?tripId=${tripId}&withChineseAttraction=true" text="trip.menu.final.itinerary.with.chinese.attraction" icon="fa fa-file-word-o" />
				<lesson:singleMenu link="trip/doc/voucher.do?tripId=${tripId}" text="trip.menu.voucher" icon="fa fa-file-word-o" />
				<lesson:singleMenu link="support/room/hotelList/export.do?tripId=${tripId}" text="trip.menu.hotel.list" icon="fa fa-file-excel-o" />
				<lesson:singleMenu link="doc/word/hotel/nameList.do?tripId=${tripId}&orderId=main" text="trip.menu.name.list" icon="fa fa-file-word-o" />
                <lesson:singleMenu link="trip/doc/coachBooking/coachInfo.do?tripId=${tripId}" text="trip.menu.coach.info" icon="fa fa-file-excel-o" />
				<lesson:singleMenu link="trip/group/groupStatement.do?tripId=${tripId}" text="trip.menu.group.statement" icon="fa fa-file-excel-o" />
				<lesson:singleMenu link="trip/finance/doc/revenue.do?tripId=${tripId}" text="trip.menu.export.to.nc" icon="fa fa-file-excel-o" />
			</lesson:menuGroup>
			</c:if>
            <li class="pull-right">
                <div class="nav-search" id="trip-navigator">
                    <form class="form-search" _lpchecked="1" action="trip/search.do">
                        <span class="input-icon">
                            <input type="text" name="keywords" value="${trip.orginalTeamCode}" style="width:250px;" class="nav-search-input" id="nav-search-input" autocomplete="off" onclick="this.select()">
                            <input type="hidden" name="searchButton" value=""/>
                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                        </span>
                    </form>
                </div>
            </li>
        </lesson:sidebar>
</c:if>