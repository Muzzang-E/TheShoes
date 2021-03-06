/* 정규표현식을 이용 */
$("#id").change(function() {
	var idExp = /^(?=.*[a-zA-Z])(?!=.*[$@$!%*?&])(?=.*[0-9]).{4,12}$/;

	if (!idExp.test($(this).val())) {
		$("#id-result").text("영문,숫자 4~12글자로 작성 해 주세요(영문 대소문자 구분)").css("color", "red");
		$(this).val("").focus();
		idFlag = false;
	} else {
		$("#id-result").text("").css("color", "green");
	}
})

$("#password").change(function() {
	var passwordEmp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}/;

	if (!passwordEmp.test($(this).val())) {
		$("#password-result").text("영문, 숫자, 특수문자를 반드시 포함한 8~16글자로 작성 해 주세요(영문 대소문자 구분)").css("color", "red");
		$(this).focus();
	} else {
		$("#password-result").text("").css("color", "green");
	}
})

$("#password-check").change(function() {
	if ($("#password").val() != $(this).val()) {
		$("#password-check-result").text("비밀번호가 일치하지 않습니다.").css("color", "red");
		$(this).focus();
		passwordFlag = false;
	} else {
		$("#password-check-result").text("").css("color", "green");
		passwordFlag = true;
	}
})

$("#name").change(function() {

	/* 한글자만 최소 2자 이상 */
	var regExp = /^[가-힣]{2,}$/;

	if (!regExp.test($(this).val())) {       // 사용자가 한글 2자 이상을 여겼을 때
		$("#name-result").text("한글로 2자 이상 입력하세요").css("color", "red");
		$(this).val("").focus();
		nameFlag = false;
	} else {
		$("#name-result").text("").css("color", "blue");
		$(this).css("background", "white");
		nameFlag = true;
	}
})

$("#birth").change(function() {
	var birthExp = /^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

	if (!birthExp.test($(this).val())) {
		$("#birth-result").text("올바르지 않은 생년월일입니다.").css("color", "red");
		$(this).focus();
		birthFlag = false;
	} else {
		$("#birth-result").text("").css("color", "green");
		birthFlag = true;
	}
})

$("#phone").change(function() {
	/*var phoneExp = /^01(?:0|1|[6-9])[0-9]{8}$/;*/
	var phoneExp = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;

	if (!phoneExp.test($(this).val())) {
		$("#phone-result").text("올바르지 않은 휴대폰 번호 형식입니다.").css("color", "red");
		$(this).focus();
		phoneFlag = false;
	} else {
		$("#phone-result").text("").css("color", "green");
		phoneFlag = true;
	}
})

$("#email").change(function() {
	var emailExp = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/
	emailChangeFlag = false;
	if (!emailExp.test($(this).val())) {
		$("#email-result").text("올바르지 않은 이메일 형식입니다.").css("color", "red");
		$(this).focus();
		emailTextFlag = false;
	} else {
		$("#email-result").text("").css("color", "green");
		emailTextFlag = true;
	}
})

$("#agree").change(function() {
	if ($("#agree").prop('checked')) {
		$("#agree-result").text("").css("color", "red");
	}
});

/* count down 함수 */
function paddedFormat(num) {
	return num < 10 ? "0" + num : num;
}

function startCountDown(duration, element) {

	let secondsRemaining = duration;
	min = 0;
	sec = 0;

	countInterval = setInterval(function() {

		min = parseInt(secondsRemaining / 60);
		sec = parseInt(secondsRemaining % 60);

		element.textContent = `${paddedFormat(min)}:${paddedFormat(sec)}`;

		secondsRemaining = secondsRemaining - 1;
		if (secondsRemaining < 0) { clearInterval(countInterval) };

	}, 1000);
}

function countStart() {
	let time_minutes = 3; // Value in minutes
	let time_seconds = 0; // Value in seconds

	let duration = time_minutes * 60 + time_seconds;

	element = document.querySelector('#count-down');
	element.textContent = `${paddedFormat(time_minutes)}:${paddedFormat(time_seconds)}`;

	startCountDown(--duration, element);
};

