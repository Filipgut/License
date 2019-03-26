var clock = document.getElementById('clock');
var hexColor = document.getElementById('hex-color');
var temp = document.getElementById('temp');
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

  var clockStr = day + ':  '+ hours + ' : ' + minutes + ' : ' + seconds;
  var hexColorStr = '#000000';

  clock.textContent = clockStr;
  document.body.style.backgroundColor = hexColorStr;
}

function dayOfWeekAsString(dayIndex) {
  return ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][dayIndex];
}

function tempClock(){
    var url_string = window.location.href
    var url = new URL(url_string);
    var tempVal= url.searchParams.get("temp");
    tempVal = "Temperature: " + tempVal;
    var tempVarSubStr = tempVal.substring(0, 18);
    temp.textContent = tempVarSubStr;
}


hexClock();
setInterval(hexClock, 1000);
tempClock();

