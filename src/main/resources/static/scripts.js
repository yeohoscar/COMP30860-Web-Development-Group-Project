$(document).ready(function(){
    let dateInput = document.getElementById("contactDate");
    dateInput.min = new Date().toISOString().slice(0,new Date().toISOString().lastIndexOf(":"));
})