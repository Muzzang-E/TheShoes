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

$("#name").change(function() {
	nameFlag = false;
})

$("#email").change(function() {
	emailTextFlag = false;
})






















