/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function insertClasses() {
    var studId = document.getElementById("studentID").value;
    var clsTitle = document.getElementById("classTitle").value;
    var clsDesc = document.getElementById("classDesc").value;
    //var studGrade = document.getElementById("studentGrade").value;
    var obj = { studentId: studId, classTitle: clsTitle, classDesc: clsDesc };
    var myJSON = JSON.stringify(obj);

    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
             window.location.href = "http://localhost:8080/student/home.html";
         }
    };
    xhttp.open("POST", "http://localhost:8080/student/rest/student-rest/insertClass", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(myJSON);
    alert("Data sent to web service");
    window.location.href = "http://localhost:8080/student/home.html";
}

function viewClasses() {
    var studID = document.getElementById("studentID").value;
    var txt = "";
    //var obj = { studentID: studID };
    //var myJSON = JSON.stringify(obj);
    document.getElementById("viewTables").innerHTML = txt;
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    alert(this.responseText);
    myObj = JSON.parse(this.responseText);
    txt += "<table border='1'>"
    //cId, cTitle, Id, fName
    txt += "<tr><td>ClassId</td><td>ClassTitle</td><td>StudID</td><td>StudFirstName</td></tr>";
    for (x in myObj) {
    txt += "<tr><td>" + myObj[x].cId + "</td><td>"+ myObj[x].cTitle + "</td><td>"+ myObj[x].Id + "</td><td>"+ myObj[x].fName + "</td></tr>";
    }
    txt += "</table>"    
    document.getElementById("viewTables").innerHTML = txt;
    }
    };
    xhttp.open("GET", "http://localhost:8080/student/rest/student-rest/viewClasses/"+studID, true);
    //xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    alert("Data sent to web service "+studID);
    //window.location.href = "http://localhost:8080/student/home.html";
}