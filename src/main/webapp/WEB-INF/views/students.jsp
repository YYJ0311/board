<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="resources/static/css/board.css">
</head>
<body>
    <div class="container">
        <!-- 학생 추가 팝업 -->
        <div class="write-popup">
            <div class="editor">
                <div class="input-box">
                    <label for="studentsName">학생 이름 : </label>
                    <input id="insertStudentsName" type="text">
                </div>
                <div class="input-box">
                    <label for="studentPassword">비밀번호 : </label>
                    <input id="studentPassword" type="text">
                </div>
                <div class="btn-area">
                    <a id="contentSubmit" href="#" class="btn-success">등록</a>
                    <a href="#" class="btn-cancel">취소</a>
                </div>
            </div>
        </div>
        <!-- 학생 정보 수정 -->
        <div class="update-popup">
            <div class="editor">
                <div class="close">
                    <a href="#" class="btn-close">닫기</a>
                </div>
                <div class="input-box">
                    <label for="studentsId">학생 아이디 : </label>
                    <input id="studentsId" type="text" readonly>
                </div>
                <div class="input-box">
                    <label for="studentsName">학생 이름 : </label>
                    <input id="studentsName" type="text">
                </div>
                <div class="input-box">
                    <label for="studentsPassword">학생 비밀번호 : </label>
                    <input id="studentsPassword" type="text">
                </div>
                <div class="btn-area">
                    <input id="studentsIdHidden" type="hidden">
                    <!-- studentsId 숨겨서 저장, 수정 삭제할 때 사용할 것임 -->
                    <a id="contentUpdate" href="#" class="btn-update">수정</a>
                    <a id="contentDelete" href="#" class="btn-delete">삭제</a>
                </div>
            </div>
        </div>
        <div class="navigation">
            <ul>
                <li>
                    <a href="#">
                        <span class="icon"><ion-icon name="logo-apple"></ion-icon></span>
                        <span class="title">DW Board</span>                
                    </a>
                </li>
                <li>
                    <a href="/board">
                        <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                        <span class="title">Dashboard</span>                
                    </a>
                </li>
                <li>
                    <a href="/students">
                        <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                        <span class="title">Students</span>                
                    </a>
                </li>
                <li>
                    <a href="/logs">
                        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                        <span class="title">logs</span>                
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                        <span class="title">Sign Out</span>                
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- main -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <!-- toggle은 나중에 만들기 -->
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <!-- search -->
            <div class="search">
                <label>
                    <input id="searchBar" type="text" placeholder="학생이름을 검색하세요..." >
                </label>
            </div>
            <div>
                <a href="#" class="logout">Logout</a>
            </div>
        </div>
         <!-- table -->
         <div class="details">
             <div class="recentOrders">
                 <div class="cardHeader">
                     <h2>학생 명단<span style="font-size: 17px; color:black"></span></h2>
                     <a href="#" class="btn">학생 추가</a>
                 </div>
                 <table>
                     <thead>
                         <tr>
                            <th>학생 아이디</th>
                            <th>학생 이름</th>
                            <th>가입 날짜</th>
                        </tr>
                     </thead>
                     <tbody id="boardData">
                         <!-- <tr>
                             <td>hyunsangwon</td>
                             <td>현상원</td>
                             <td>2022-05-19</td>
                         </tr>
                         <tr>
                            <td>hyunsangwon</td>
                            <td>현상원</td>
                            <td>2022-05-19</td>
                        </tr>
                        <tr>
                            <td>hyunsangwon</td>
                            <td>현상원</td>
                            <td>2022-05-19</td>
                        </tr> -->
                     </tbody>
                 </table>
                 <div class="pagination">
                    <!-- <a href="#">Previous</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">Next</a> -->
                 </div>
             </div>
         </div>
    </div>
</body>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
<script>
    $('.btn').click(function(){
        $('.write-popup').css('display', 'block');
    });
    $('.btn-cancel').click(function(){
        $('.write-popup').css('display', 'none');
    });
    $('.btn-close').click(function(){
        $('.update-popup').css('display','none');
    })
    let list = document.querySelectorAll('.navigation li');
    function activeLink(){
        list.forEach((item) => {item.classList.remove('hovered')});
        this.classList.add('hovered');
    }
    list.forEach((item) => {item.addEventListener('mouseover',activeLink)});
</script>
<script>
// 학생 수 구해서 "학생명단" 옆에 붙임
    getStudentListCount(); 
    function getStudentListCount(){
        $.ajax({
            url : "http://localhost:8080/api/v1/students/map",
            type : "GET",
            dataType : "json",
            success : function (response){
                $('.cardHeader span').text("(총"+response.length+"명)");
            }
        })
    }

// 학생 리스트 전체 조회
    getStudentList(1, 10);
    function getStudentList(pageNum, pageSize){
        $.ajax({
            url : "http://localhost:8080/api/v1/students?pageNum="+pageNum+"&pageSize="+pageSize,
            type : "GET",
            dataType : "json",
            success : function (response){
                let html = "";
                if(response.list.length > 0){
                    for(var i=0; i<response.list.length; i++){
                        html += 
                            '<tr onclick=getStudents('+response.list[i].studentsId+')><td>'+
                            response.list[i].studentsId + '</td><td>' + 
                            response.list[i].studentsName + '</td><td>' + 
                            response.list[i].createAt.replace("T"," ")+'</td></tr>'
                    }
                    var paginationHtml = "";
                    if(response.hasPreviousPage){
                        paginationHtml += '<a onclick="getStudentList('+(response.pageNum-1)+','+pageSize+')" href="#">Previous</a>';
                    }
                    for(var i=0; i<response.navigatepageNums.length; i++){
                        paginationHtml += '<a id="pageNum'+response.navigatepageNums[i]+'" onclick="getStudentList('+response.navigatepageNums[i]+','+pageSize+')" href="#">'+response.navigatepageNums[i]+'</a>'
                    }
                    if(response.hasNextPage){
                        paginationHtml += '<a onclick="getStudentList('+(response.pageNum+1)+','+pageSize+')" href="#">Next</a>';
                    }
                    $('.pagination').children().remove();
                    $('.pagination').append(paginationHtml);
                }else{
                    html += '<tr><td colspan=6 style="text-align: center;"><br><br>학생이 없습니다.</td></tr>';
                }
                $('#boardData').children().remove();
                $('#boardData').append(html);
                $('#pageNum'+pageNum).css('backgroundColor', '#287bff');
                $('#pageNum'+pageNum).css('color', '#fff');
            },
            error : function (request, status, error){
                console.log("에러 내용은 ===>" + error);
            },
        });
    }

// 학생 검색
    // 엔터 누르면 검색한 학생 리스트 만들기
    $('#searchBar').keyup(function(key){
        if(key.keyCode == 13){ // 엔터 == 13
            getSearchedStudentList(1,3)
        }
    })
    // 검색한 학생에 대한 리스트 만들기
    function getSearchedStudentList(pageNum, pageSize){
        var search = $('#searchBar').val();
        if(search == '') {
            alert('검색어 입력해주세요');
            return false;
        }
        $.ajax({
            url : "http://localhost:8080/api/v1/students/search?name="+search+"&pageNum="+pageNum+"&pageSize="+pageSize,
            type : "GET",
            dataType : "json", // 서버 결과를 json으로 응답받겠다.
            success : function (response){
                // console.log(response)
                let html = "";
                if(response.list.length >0){
                    for(var i=0; i<response.list.length; i++){
                        html += 
                            '<tr onclick=getStudents('+response.list[i].studentsId+')><td>'+
                            +response.list[i].studentsId+'</td><td>'
                            +response.list[i].studentsName+'</td><td>'
                            +response.list[i].createAt.replace("T"," ")+'</td></tr>'
                    }
                    var paginationHtml = "";
                    if(response.hasPreviousPage){
                        paginationHtml += '<a onclick="getSearchedStudentList('+(response.pageNum-1)+','+pageSize+')" href="#">Previous</a>';
                    }
                    for(var i=0; i<response.navigatepageNums.length; i++){
                        paginationHtml += '<a id="pageNum'+response.navigatepageNums[i]+'" onclick="getSearchedStudentList('+response.navigatepageNums[i]+','+pageSize+')" href="#">'+response.navigatepageNums[i]+'</a>'
                    }
                    if(response.hasNextPage){
                        paginationHtml += '<a onclick="getSearchedStudentList('+(response.pageNum+1)+','+pageSize+')" href="#">Next</a>';
                    }
                    $('.pagination').children().remove();
                    $('.pagination').append(paginationHtml);
                    $('#pageNum'+pageNum).css('backgroundColor', '#287bff');
                    $('#pageNum'+pageNum).css('color', '#fff');
                }else{
                    html += '<tr><td colspan=6 style="text-align: center;"><br><br>존재하지 않는 학생입니다.</td></tr>';
                }
                $("#boardData").children().remove();
                $("tbody").append(html);
            },
            error : function (request, status, error){
                console.log("에러 내용은 ===>" + error);
            },
        });
    } 

// 학생 추가
    $("#contentSubmit").click(function(){
        var studentsName = $("#insertStudentsName").val();
        var studentsPassword = $("#studentPassword").val();
        if(studentsName == ""){
          alert("이름을 입력해주세요.");
          return false;
        }
        if(studentsPassword == ""){
          alert("비밀번호를 입력해주세요");
          return false;
        }
        if(confirm('학생을 등록하시겠습니까?')){
            // API 서버에 전송할 json 생성
            var jsonData = {
                "studentsName" : studentsName,
                "studentsPassword" : studentsPassword
            }
            $.ajax({
                url : "http://localhost:8080/api/v1/students",
                type : "POST",
                contentType : "application/json", // 서버에 json 타입으로 보낼 예정(요청)
                dataType : "json", // 서버 결과를 json으로 응답받겠다.
                data : JSON.stringify(jsonData), // 문자로 인식되는 걸 json 타입으로 바꿈
                success : function(response){
                    console.log(response);
                    if(response >0){
                        $('.write-popup').css('display', 'none');
                        $("#insertStudentsName").val("");
                        $("#studentPassword").val("");
                        $("#boardData").children().remove();
                        getStudentList(1, 5);
                    }
                }  
            })
        }
    })

// 학생 상세정보 조회
    function getStudents(studentsId){
        $('.update-popup').css('display', 'block')
        $.ajax({
            url : "http://localhost:8080/api/v1/students/id/" + studentsId,
            type : "GET",
            dataType : "json",
            success : function (response){
                console.log(response);
                $("#studentsId").val(response.studentsId);
                $("#studentsName").val(response.studentsName);
                $("#studentsPassword").val(response.studentsPassword);
                $("#studentsIdHidden").val(studentsId);
            },
            error : function (request, status, error){
                console.log("에러 내용은 ===>" + error);
            },
        });
    }

// 학생 정보 수정
    $("#contentUpdate").click(function(){
        var studentsId = $("#studentsIdHidden").val();
        var studentsName = $("#studentsName").val();
        var studentsPassword = $("#studentsPassword").val();
        var jsonData = {
                studentsName : studentsName,
                studentsPassword : studentsPassword
        };
        $.ajax({
            url : "http://localhost:8080/api/v1/students/id/" + studentsId,
            type : "PATCH",
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(jsonData),
            success : function(response){
                console.log(response)
                if(response > 0){
                    alert('수정 완료');
                    $('.update-popup').css('display', 'none')
                    $("#boardData").children().remove(); 
                    getStudentList(1, 5);
                }
            },
            error : function (request, status, error){
                console.log("에러 내용은 ===>" + error);
            },
        });
    });

// 학생 정보 삭제
    $("#contentDelete").click(function(){
        var studentsId = $("#studentsIdHidden").val();
        if(confirm("학생이 작성한 게시글은 DB에 남아있습니다.\n삭제하시겠습니까?")){ // \n : 줄바꿈
            $.ajax({
                url : "http://localhost:8080/api/v1/students/id/" + studentsId,
                type : "DELETE",
                dataType : "json",
                success : function(response){
                    alert('삭제 완료');
                    $('.update-popup').css('display','none');
                    $("#boardData").children().remove();
                    getStudentList(1, 5);
                },
                error : function(){
                    alert("오류");
                    return 0;
                }
            });
        }
    });
</script>
</html>