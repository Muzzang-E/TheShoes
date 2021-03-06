<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${ pageContext.servletContext.contextPath }/resources/css/shoes/shoesDetail.css">
<link rel="shortcut icon"
	href="${ pageContext.servletContext.contextPath }/resources/uses/the-shoes-favicon.png">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>THE SHOES</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="../common/header.jsp" />
   	<fmt:formatDate value="${now}" pattern="yyyyMMddHHmm" var="nowDate" />             <%-- 오늘날짜 --%>
  	<fmt:formatDate value="${requestScope.shoesDetail.endDate}" pattern="yyyyMMddHHmm" var="closeDate"/>
  	<fmt:formatDate value="${requestScope.shoesDetail.startDate}" pattern="yyyyMMddHHmm" var="beginDate"/>  

	<!-- section -->
	<section>
		<div class="info-section">
			<ul class="images">
			<c:forEach var="thumb" items="${requestScope.shoesDetail.thumbList}">
		        <li>
		        	<figure>
						<img src="/TheShoes/resources/upload/image/shoes/${thumb.savedName}" alt="">
					</figure>
				</li>
			
			</c:forEach>


			</ul>
			<div class="info">
				<h1 style="font-size: 30px;">${requestScope.shoesDetail.shoesModel} </h1>
					
					<h5><fmt:formatNumber value="${requestScope.shoesDetail.shoesPrice}" pattern="#,###"/>원</h5>
					<!--211014 수정-->
					<div class="box">
			            <p class="bold">* THE DRAW 응모 이후 사이즈 수정은 불가합니다.</p>
			            <p class="bold">* 당첨결과는 마이페이지에서 확인하실 수 있습니다.</p>
			            	<c:if test="${closeDate > nowDate}">
					            <br>
					            <p style="text-align: center; font-size: 17px"><span>판매 수량 : </span><span style="font-weight: bold;">${ shoesDetail.salesAmount }</span></p>
					        </c:if>
			            <div class="detail">
			              <!-- <p><span>응모시간 : </span><span>10/15(금) 10:00 ~ 10:30 (30분)</span></p>-->
			              <p><span>응모시간 : </span><span><fmt:formatDate value="${requestScope.shoesDetail.startDate}" pattern="MM/dd(E) HH:mm"/>
															~ <fmt:formatDate value="${requestScope.shoesDetail.endDate}" pattern="MM/dd(E) HH:mm"/></span></p>
			              <p><span>당첨자발표 : </span><span><fmt:formatDate value="${requestScope.shoesDetail.winnerDate}" pattern="MM/dd(E) HH:mm"/></span></p>
			            </div>
			          </div>
			          <!--//211015 수정-->
			          <c:choose>
				          <c:when test="${sort == 0}">
						  	<a class="btn" href="javascript:void(0)">응모 불가능</a>
						  </c:when>
						  <c:when test="${sort == 1}">
							  <c:choose> 
									<c:when test="${closeDate > nowDate}">
							          	<c:choose>
							          		<c:when test="${nowDate > beginDate}">
							          			<a class="btn" href="${ pageContext.servletContext.contextPath }/shoes/event?shoesNo=${ shoesDetail.shoesNo }">응모 가능</a>
							          		</c:when>
							          		<c:otherwise>
											  <a class="btn" href="javascript:void(0)">응모 불가능</a>
											</c:otherwise> 
							          	</c:choose>
									</c:when> 
									<c:otherwise>
									  <a class="btn" href="javascript:void(0)">응모 불가능</a>
									</c:otherwise> 
								</c:choose>
						  </c:when>
						  <c:when test="${sort == 2}">
				          	<a class="btn" href="javascript:void(0)">응모 완료</a>
						  </c:when>
					  </c:choose>
					  	<c:if test="${ empty flag }">
							  <a id="wishBtn" onclick="addWish()">
							  	<span class="heart">
							  		<i class="fa fa-heart-o" aria-hidden="true"></i>
							  	</span>
							  	<span class="wish">관심 상품</span>
							  </a>
						  </c:if>
				<input id="shoesNo" value="${ shoesDetail.shoesNo }" hidden>
			</div>
		</div>
	</section>
	
	<!-- footer -->
	<jsp:include page="../common/footer.jsp" />
	
	<script>
		function addWish() {
			var shoesNo = $("#shoesNo").val();
			$.ajax({
				url: "${ pageContext.servletContext.contextPath }/shoes/regWish",
				type: "GET",
				data: { shoesNo : shoesNo },
				success: function(data) {
					if(data == "fail") {
						location.replace("/WEB-INF/views/common/errorPage.jsp");
					} else {
						$("#wishBtn").hide();
						if (confirm("관심 상품 목록으로 이동 하시겠습니까?")) {
							location.replace("${ pageContext.servletContext.contextPath }/myPage/wishList");
					    } else {
					    }
					}
				},
				error: function(request, status, error) {
					alert("code:" + request.responseText + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
				}
			});
		}
	</script>
</body>
</html>