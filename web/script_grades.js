/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function insertGrades() {
    var studFst = document.getElementById("studentFname").value;
    var studLst = document.getElementById("studentLname").value;
    var studGrade = document.getElementById("studentGrade").value;
    var obj = { studentName: studFst, studentLastName: studLst, studentGrade: studGrade };
    var myJSON = JSON.stringify(obj);

    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             alert(this.responseText);
             window.location.href = "http://localhost:8080/student/home.html";
         }
    };
    xhttp.open("POST", "http://localhost:8080/student/rest/student-rest/insertGrade", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(myJSON);
    alert("Data sent to web service");
    window.location.href = "http://localhost:8080/student/home.html";
}