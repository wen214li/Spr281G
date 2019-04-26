
// addEventListener('click', function() { console.log("option1 is selected!") });
/* globals Chart:false, feather:false */
var element1 = document.getElementById("option1");
var element2 = document.getElementById("option2");
var element3 = document.getElementById("option3");
var element4 = document.getElementById("option4");

plot("daily");
window.resizeBy(0,0);

element1.change= function(){
	myChart.destroy();
	plot("daily");
	myChart.update();


};


element2.change= function(){
	myChart.destroy();
	plot("weekly");
	myChart.update();


};

element3.change= function(){
	myChart.destroy();
	plot("monthly");
};

element4.change= function(){
	myChart.destroy();
	plot("yearly");
};


// element3.change = plot("daily");
// element4.change = plot("weekly");

// element1.change = plot("daily");
// element1.change = plot("daily");

// element.click = console.log("changed!!!");


function plot (s_id) {
  // 'use strict'
console.log (s_id);
var targetUrl = 'http://localhost:8080/sensor';
var url =  new URL( targetUrl);
var params = {'sensor_id': s_id.toString()};
url.search = new URLSearchParams(params)
var l =[];
var v =[];

const pro = fetch(url, {
  method: 'GET'
});
pro.then(response => response.json())
.catch(error => console.error('Error:', error))
.then(function(response){
  for ( var i = 0; i < response.data.length; i++){
//console.log(response.data[i].date);
// .then(response => (console.log(JSON.stringify(response.data)  )));
    l.push(response.data[i].date);
     v.push(response.data[i].value);
   }
 });
  // Graphs
  var ctx = document.getElementById('myChart').getContext("2d");
  ctx.canvas.width = 300;
	ctx.canvas.height = 180;
  // eslint-disable-next-line no-unused-vars
console.log(l);
	window.resizeBy(0,0);
    myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: l,
      datasets: [{
        data: v,
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 2,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      responsive: true,
      
      scales: {
        yAxes: [{

          ticks: {
          	autoSkip: false,
            beginAtZero: false

          }
        }]
      },
      legend: {
        display: false
      }
    }
  })

};


