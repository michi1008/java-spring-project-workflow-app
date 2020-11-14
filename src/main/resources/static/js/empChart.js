let chartDataStr = decodeHtml(chartData); // comes from var chartData = "[[${taskCount}]]" in employee-list.html
let chartJsonArray = JSON.parse(chartDataStr);

let arrayLength = chartJsonArray.length;
let numericData = [];
let labelData = [];

for(let i=0; i<arrayLength; i++){
	numericData[i] = chartJsonArray[i].taskCount;
	labelData[i] = chartJsonArray[i].firstName;
}


new Chart(document.getElementById("empPieChart"), {  //"myPieChart" corresponds to ID in html
    type: 'pie',
    
    data: {
        labels:labelData,
        datasets: [{
            label: 'Task Count',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#9c437b","#3fb3b5","#d1211b", "#3bd140", "5BCDDB",
            					"#A56EC3","3BEC26", "1125D2", "CCDF3B", "3BDFC9"],
            borderColor: '#FFFFFF',
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title:{
    		display:true,
    		text: 'Task Count'
    		}
    	}
});

// [{"stageCount":1,"Stage":"Completed"},{},{}]
function decodeHtml(html){
	let txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}	
	