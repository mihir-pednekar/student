/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function makePayment() {
    var studId = document.getElementById("studentID").value;
    var amt = document.getElementById("payAmount").value;
    //var clsDesc = document.getElementById("classDesc").value;
    //var studGrade = document.getElementById("studentGrade").value;
    var obj = { studentId: studId, payAmount: amt };
    var myJSON = JSON.stringify(obj);

    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
             window.location.href = "http://localhost:8080/student/home.html";
         }
    };
    xhttp.open("POST", "http://localhost:8080/student/rest/student-rest/insertPay", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(myJSON);
    alert("Data sent to web service");
    window.location.href = "http://localhost:8080/student/home.html";
}

function viewPayment() {
    var studID = document.getElementById("studentID").value;
    var txt = "";
    //var obj = { studentID: studID };
    //var myJSON = JSON.stringify(obj);

    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    alert(this.responseText);
    myObj = JSON.parse(this.responseText);
    txt += "<table border='1'>"
    txt += "<tr><td>Pay Id</td><td>Pay Amount</td><td>Stud ID</td></tr>";
    //private String pId, amount, studId;
    for (x in myObj) {
    txt += "<tr><td>" + myObj[x].pId + "</td><td>"+ myObj[x].amount + "</td><td>"+ myObj[x].studId + "</td></tr>";
    }
    txt += "</table>"    
    document.getElementById("viewTables").innerHTML = txt;
    }
    };
    xhttp.open("GET", "http://localhost:8080/student/rest/student-rest/viewPayment/"+studID, true);
    //xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    alert("Data sent to web service "+studID);
    //window.location.href = "http://localhost:8080/student/home.html";
}

function viewGrades() {
    var studID = document.getElementById("studentID").value;
    var txt = "";
    //var obj = { studentID: studID };
    //var myJSON = JSON.stringify(obj);

    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    alert(this.responseText);
    myObj = JSON.parse(this.responseText);
    txt += "<table border='1'>"
    txt += "<tr><td>StudId</td><td>StudFirstName</td><td>StudLastName</td><td>RegDate</td><td>Grades</td></tr>";
    for (x in myObj) {
    txt += "<tr><td>" + myObj[x].id + "</td><td>"+ myObj[x].fName + "</td><td>"+ myObj[x].lName + "</td><td>"+ myObj[x].regDate + "</td><td>"+ myObj[x].grade + "</td></tr>";
    }
    txt += "</table>"    
    document.getElementById("viewTables").innerHTML = txt;
    }
    };
    xhttp.open("GET", "http://localhost:8080/student/rest/student-rest/viewGrade/"+studID, true);
    //xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    alert("Data sent to web service "+studID);
    //window.location.href = "http://localhost:8080/student/home.html";
}