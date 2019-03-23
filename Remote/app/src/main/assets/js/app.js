var clock = document.getElementById('clock');
var hexColor = document.getElementById('hex-color');

function hexClock() {
  var time = new Date();
  var day = dayOfWeekAsString(time.getDay()).toString();
  var hours = (time.getHours() % 12).toString();
  var minutes = time.getMinutes().toString();
  var seconds = time.getSeconds().toString();

  if (hours.length < 2) {
    hours = '0' + hours;
  }

  if (minutes.length < 2) {
    minutes = '0' + minutes;
  }

  if (seconds.length < 2) {
    seconds = '0' + seconds;
  }

  var clockStr = day + '   ' + hours + ' : ' + minutes + ' : ' + seconds;
  var hexColorStr = '#000000' ;

  clock.textContent = clockStr;
  //hexColor.textContent = hexColorStr;
  document.body.style.backgroundColor = hexColorStr;
}

function dayOfWeekAsString(dayIndex) {
  return ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"][dayIndex-1];
}

hexClock();
setInterval(hexClock, 1000);