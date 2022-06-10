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
        <!-- 글 작성 팝업 -->
        <div class="write-popup">
            <div class="editor">
                <div class="input-box">
                    <label for="studentsName">작성자 : </label>
                    <input id="studentsName" type="text" value="유영준" readonly>
                </div>
                <div class="input-box">
                    <label for="title">제목 : </label>
                    <input id="title" type="text" placeholder="제목을 입력하세요...">
                </div>
                <div class="input-box">
                    <textarea id="content" rows="10" cols="65" placeholder="내용을 간단히 적어주세요..."></textarea>
                </div>
                <div class="btn-area">
                    <a id="contentSubmit" href="#" class="btn-success">등록</a>
                    <a href="#" class="btn-cancel">취소</a>
                </div>
            </div>
        </div>
        <!-- 글 작성 수정 -->
        <div class="update-popup">
            <div class="editor">
                <div class="close">
                    <a href="#" class="btn-close">닫기</a>
                </div>
                <div class="input-box">
                    <label for="title">제목 : </label>
                    <input id="upt-title" type="text" placeholder="제목을 입력하세요...">
                </div>
                <div class="input-box">
                    <textarea id="upt-content" rows="10" cols="65" placeholder="내용을 간단히 적어주세요..."></textarea>
                </div>
                <div class="btn-area">
                    <input id="boardIdHidden" type="hidden">
                    <!-- 여기에 boardId를 넣고 사용할 것임 -->
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
                    <a href="/board?pageNum=1&pageSize=10">
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
                    <a href="#">
                        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                        <span class="title">Password</span>                
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
                    <input id="searchBar" type="text" placeholder="작성자를 검색하세요...">
                    <input id="keyword" type="hidden" value="null">
                </label>
            </div>
            <div>
                <a href="#" class="logout">Logout</a>
            </div>
        </div>
         <!-- cards -->
         <div class="cardBox">
             <div class="card">
                 <div>
                    <div id="studentsCnt" class="numbers">1,400</div>
                    <div class="cardName">학생 수</div>
                 </div>
                 <div class="iconBx">
                    <ion-icon name="school-outline"></ion-icon>
                 </div>
             </div>
             <div class="card">
                <div>
                    <div id="boardCnt" class="numbers">500</div>
                    <div class="cardName">게시글 수</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="book-outline"></ion-icon>
                </div>
            </div>
            <div class="card">
                <div>
                   <div id="writerCnt" class="numbers">300</div>
                   <div class="cardName">작성자 수</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="code-slash-outline"></ion-icon>
                </div>
            </div>
            <div class="card">
                <div>
                   <div id="viewsCnt" class="numbers">2,800</div>
                   <div class="cardName">총 조회 수</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="eye-outline"></ion-icon>
                </div>
            </div>
         </div>
         <!-- table -->
         <div class="details">
             <div class="recentOrders">
                 <div class="cardHeader">
                     <h2>Board List</h2>
                     <a href="#" class="btn">글 작성</a>
                 </div>
                 <table>
                     <thead>
                         <tr>
                            <th>게시판 번호</th>
                            <th>작성자</th>
                            <th>제목</th>
                            <th>수정 날짜</th>
                            <th>작성 날짜</th>
                            <th>조회 수</th>
                         </tr>
                     </thead>
                     <tbody id="boardData">
                     	<c:choose>
                     		<c:when test="${fn:length(pageHelper.list) > 0}">
                     			<c:forEach items="${pageHelper.list}" var="item">
	                     			<tr onclick="getBoard(${item.boardId})">
                     					<td>${item.boardId}</td>
                     					<td>${item.studentsName}</td>
                     					<td>${item.title}</td>
                     					<td>${item.updateAt}</td>
                     					<td>${item.createAt}</td>
	                     				<c:if test="${item.cnt < 10}">
	                     					<td><span class="row">${item.cnt}</span></td>
		                     			</c:if>
	                     				<c:if test="${item.cnt >= 10 && item.cnt < 50}">
	                     					<td><span class="middle">${item.cnt}</span></td>
	                     				</c:if>
	                     				<c:if test="${item.cnt >= 50}">
	                     					<td><span class="high">${item.cnt}</span></td>
	                     				</c:if>
	                     			</tr>
                     			</c:forEach>
                     		</c:when>
                     		<c:otherwise>
                     			<tr>
                     				<td colspan="6">게시글이 없습니다.</td>
                     			</tr>
                     		</c:otherwise>
                     	</c:choose>
                         <!-- <tr>
                             <td>1</td>
                             <td>현상원</td>
                             <td>점심시간이 너무 짧아요!</td>
                             <td>2022-05-19</td>
                             <td>2022-05-18</td>
                             <td><span class="high">8320</span></td>
                         </tr>
                         <tr>
                            <td>2</td>
                            <td>현상원</td>
                            <td>학원에 커피기계가 없어요!</td>
                            <td>2022-05-19</td>
                            <td>2022-05-18</td>
                            <td><span class="middle">1200</span></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>현상원</td>
                            <td>너무 졸려요...</td>
                            <td>2022-05-19</td>
                            <td>2022-05-18</td>
                            <td><span class="row">5</span></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>현상원</td>
                            <td>안녕하세요!</td>
                            <td>2022-05-19</td>
                            <td>2022-05-18</td>
                            <td><span class="row">22</span></td>
                        </tr> -->
                        <!-- 더미 데이터는 지우지 않는다. -->
                     </tbody>
                 </table>
                 <div class="pagination">
	                <c:if test="${pageHelper.hasPreviousPage}">
	                 	<a onclick="getBoardList(${pageHelper.pageNum - 1}, 10)">Previous</a>
	                </c:if>
	         			<c:forEach begin="${pageHelper.navigateFirstPage}" end="${pageHelper.navigateLastPage}" var="pageNum">
	          				<a id="pageNum${pageNum}" onclick="getBoardList(${pageNum}, 10)">${pageNum}</a>
	         			</c:forEach>
	         		<c:if test="${pageHelper.hasNextPage}">
	                	<a onclick="getBoardList(${pageHelper.pageNum + 1}, 10)">Next</a>
	                </c:if>
	                   <!-- <a href="#">Previous</a>
	                   <a href="#">1</a>
	                   <a href="#">2</a>
	                   <a href="#">3</a>
	                   <a href="#">4</a>
	                   <a href="#">5</a>
	                   <a href="#">Next</a> -->
                 </div>
                 <input id="nowPageNum" type="hidden" value="${pageHelper.pageNum}">
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
        location.reload(); // 새로고침
    })
    let list = document.querySelectorAll('.navigation li'); // .navigation li를 찾음
    function activeLink(){
        list.forEach((item) => {item.classList.remove('hovered')});
        this.classList.add('hovered'); // hovered 클래스 추가
    }
    list.forEach((item) => {item.addEventListener('mouseup',activeLink)});
</script>
<script>
//통계 조회하고 값 넣기
	setBoardStatistics();
	function setBoardStatistics(){
	    $.ajax({
	        url : "/api/v1/board/boardstatistics",
	        type : "GET",
	        dataType : "json",
	        success : function (response){
	            $('#studentsCnt').text(response.studentsCnt)
	            $('#boardCnt').text(response.boardCnt)
	            $('#writerCnt').text(response.writerCnt)
	            $('#viewsCnt').text(response.viewsCnt)
	            // input을 컨트롤할 때 val() 사용
	            // text() or html()은 input을 제외한 태그를 컨트롤할 때 사용.
	        },
	        error : function (request, status, error){
	            console.log("에러 내용은 ===>" + error);
	        },
	    });
	}

// 페이지 css 넣기
	getPageNum();
	function getPageNum(){
		var pageNum = $('#nowPageNum').val(); // 현재 페이지번호
		$('#pageNum'+pageNum).css('backgroundColor', '#287bff');
		$('#pageNum'+pageNum).css('color', '#fff');
	}
	
// 게시판 리스트
	function getBoardList(pageNum, pageSize){
		location.href="/board?pageNum="+pageNum+"&pageSize="+pageSize;
	}
	
	$('#searchBar').keyup(function(key){
		var pageNum = 1;
		var pageSize = 10;
        if(key.keyCode == 13){ // 엔터 == 13
            var search = $('#searchBar').val().trim();
            if(search != ''){
				location.href="/board/search?writer="+search+"pageNum="+pageNum+"&pageSize="+pageSize;
            }
        }
    })
	
// 클릭한 게시물 확인하는 함수
    function getBoard(boardId){
        // boardId를 html에 hidden 하기
        $('.update-popup').css('display', 'block')
        $.ajax({
            url : "/api/v1/board/boardId/"+boardId,
            type : "GET",
            dataType : "json", // 서버 결과를 json으로 응답받겠다.
            success : function (response){
                // input에 데이터 set 하기
                $('#upt-title').val(response.title);
                $('#upt-content').val(response.content);
                $('#boardIdHidden').val(boardId);
                setBoardViews(boardId); // 조회수 증가 함수 실행
            }
        });
    }
    
// 조회수 카운트  
    function setBoardViews(boardId){
        $.ajax({
            url : "/api/v1/board/views/boardId/"+boardId,
            type : "PATCH",
            dataType : "json",
            success : function (response){
            	if(response > 0){
            		// 에러 페이지로 이동하는 로직
            	}
            }
        });
    }
    
// 게시판 작성
    $("#contentSubmit").click(function(){
        var title = $("#title").val();
        var content = $("#content").val();
        var studentsId = 23;
        // studentsId = 1; // 집에서 쓰는 번호
        
        if(studentsName == ""){
          alert("작성자를 입력해주세요.");
          return false;
        }
        if(title == ""){
          alert("제목을 입력해주세요");
          return false;
        }
        if(content == ""){
          alert("내용을 입력해주세요.");
          return false;
        }
        if(confirm('게시글을 작성하시겠습니까?')){
            var jsonData = {
                "studentsId" : studentsId,
                "title" : title,
                "content" : content
            }
            $.ajax({
                url : "/api/v1/board",
                type : "POST",
                contentType : "application/json", 
                dataType : "json", 
                data : JSON.stringify(jsonData),
                success : function(response){
                    if(response >0){
                    	var pageNum = $('#nowPageNum').val();
                        getBoardList(pageNum, 10);
                    }
                }
            });
        }
    });
    
 // 게시물 수정
    $('#contentUpdate').click(function(){
        var boardId = $('#boardIdHidden').val(); // 숨겨둔 boardId 가져오기
        var title = $('#upt-title').val();
        var content = $('#upt-content').val();
        if(title == ''){
            alert("제목을 작성해주세요.")
            return false;
        }
        if(content == ''){
            alert("내용을 작성해주세요.")
            return false;
        }
        var jsonData = {
            title : title,
            content : content
        };
        $.ajax({
            url : "/api/v1/board/boardId/"+boardId,
            type : "PATCH",
            contentType : "application/json", // 서버에 json 타입으로 보낼 예정(요청)
            dataType : "json", // 서버 결과를 json으로 응답받겠다.
            data : JSON.stringify(jsonData), // 문자로 인식되는 걸 json 타입으로 바꿈
            success : function (response){
                console.log(response)
                if(response > 0){
                	var pageNum = $('#nowPageNum').val();
                    getBoardList(pageNum, 10); // 보드 다시 불러 옴
                }
            }
        });
    });

// 게시물 삭제
    $('#contentDelete').click(function(){
        var boardId = $('#boardIdHidden').val();
        if(confirm('삭제하시겠습니까?')){
	        $.ajax({
	            url : "/api/v1/board/boardId/"+boardId,
	            type : "DELETE",
	            dataType : "json",
	            success : function (response){
	            	if(response > 0){
		                alert('삭제 완료');
		                var pageNum = $('#nowPageNum').val();
		                getBoardList(pageNum, 10);
	            	}
	            }
	        });
        }
    });
</script>
</html>