console.log(chartData);
var chartDataStr=decodeHtml(chartData);
console.log(chartDataStr);
//parses json string into javascript object
var chartJsonArray=JSON.parse(chartDataStr);
var arrayLength=chartJsonArray.length
var numericData=[];
var labelData=[];

for(var i=0;i< arrayLength;i++){
numericData[i]=chartJsonArray[i].statusCount;
labelData[i]=chartJsonArray[i].status;
}

// For a pie chart
 new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
      // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'my first dataset',
            backgroundColor: ['rgb(255, 99, 132)','rgb(255, 43, 11)','rgb(25, 9, 1)'],
            borderColor: 'rgb(255, 99, 132)',
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    title:{
    display:true,
    text:'Project Statuses'
    }
}});

// "[{"value":1,"label":"COMPLETED"},{"value":2,"label":"INPROGRESS"},{"value":1,"label":"NOTSTARTED"}]"

function decodeHtml(html){
var txt=document.createElement("textarea");
txt.innerHTML=html;
return txt.value;
}