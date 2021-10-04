<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/css/member/findPassword2.css">
<link rel="shortcut icon" href="${ pageContext.servletContext.contextPath }/resources/uses/the-shoes-favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>THE SHOES</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="../common/header.jsp" />
	
    <!-- section -->
    <section>
        <div class="section-main-div">
        <br><br>
          <img class="logo-form rounded mx-auto d-block" src="${ pageContext.servletContext.contextPath }/resources/uses/Logo.png">
          <br>
          <div class="indicator">
            <p>
              <span>01. 아이디 입력</span>&nbsp;&nbsp;&nbsp;·&nbsp;&nbsp;&nbsp;
              <span class="text-black">02. 본인 확인</span>&nbsp;&nbsp;·&nbsp;&nbsp;
              <span>03. 비밀번호 재설정</span>
            </p>
          </div>
          <hr>
          <br>
          <form action="#" method="POST">
            <div class="row">
                <input type="text" name="email" id="email" class="form-control" placeholder="이메일" autofocus required>
            </div>
            <br>
            <div class="row">
                <input type="text" name="name" id="name" class="col-8 form-control" placeholder="이름" required>
                <div class="col-1"></div>
                <button type="button" id="code-send-btn" class="code-send-btn col-3 hsy-btn btn btn-lg btn-block btn-outline-dark" value="아이디 찾기">인증번호</button>
            </div>
            <br>
            <div class="row">
                <input type="text" name="code" id="code" class="col-8 form-control" placeholder="인증번호" required>
                <div class="col-1"></div>
                <label id="count-down" class="col-3 count-down">03:00</label>
            </div>
            <br>
            <div class="row">
                <button type="submit" class="hsy-btn btn btn-lg btn-block btn-outline-dark">다음</button>
            </div>
          </form>
          <br>
        </div>
    </section>
    <script>
        function paddedFormat(num) {
            return num < 10 ? "0" + num : num; 
        }

        function startCountDown(duration, element) {

            let secondsRemaining = duration;
            let min = 0;
            let sec = 0;

            let countInterval = setInterval(function () {

                min = parseInt(secondsRemaining / 60);
                sec = parseInt(secondsRemaining % 60);

                element.textContent = `${paddedFormat(min)}:${paddedFormat(sec)}`;

                secondsRemaining = secondsRemaining - 1;
                if (secondsRemaining < 0) { clearInterval(countInterval) };

            }, 1000);
        }

        $("#code-send-btn").click(function () {
            let time_minutes = 3; // Value in minutes
            let time_seconds = 0; // Value in seconds

            let duration = time_minutes * 60 + time_seconds;

            element = document.querySelector('#count-down');
            element.textContent = `${paddedFormat(time_minutes)}:${paddedFormat(time_seconds)}`;

            startCountDown(--duration, element);
        });
    </script>
    
    <!-- footer -->
    <jsp:include page="../common/footer.jsp" />
</body>
</html>