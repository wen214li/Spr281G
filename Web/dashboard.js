/* globals Chart:false, feather:false */
console.log("dash");

(function () {
  'use strict'

var targetUrl = 'http://localhost:8080/sensor';
var url =  new URL( targetUrl);
var params = {'sensor_id': "daily"};
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
  var ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
console.log(l);
    var myChart = new Chart(ctx, {
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
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
}())
