/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function createDbSchema() {
    var dbDriver = document.getElementById("driver").value;
    var dbName = document.getElementById("dbName").value;
    var dbUrl = document.getElementById("url").value;
    var dbUser = document.getElementById("user").value;
    var dbPass = document.getElementById("pass").value;
    var obj = { driver: dbDriver, dbName: dbName, url: dbUrl, user: dbUser, pass: dbPass };
    var myJSON = JSON.stringify(obj);

    var xhttp = new XMLHttpRequest();
    var respTxt;
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
             window.location.href = "http://localhost:8080/student/home.html";
         }
    };
    xhttp.open("POST", "http://localhost:8080/student/rest/student-rest/createDB", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(myJSON);
    alert("Data sent to web service");
    window.location.href = "http://localhost:8080/student/home.html";
}