//통계 조회하고 값 넣기
setBoardStatistics();
function setBoardStatistics() {
	$.ajax({
		url: "/api/v1/board/boardstatistics",
		type: "GET",
		dataType: "json",
		success: function(response) {
			$('#studentsCnt').text(response.studentsCnt)
			$('#boardCnt').text(response.boardCnt)
			$('#writerCnt').text(response.writerCnt)
			$('#viewsCnt').text(response.viewsCnt)
			// input을 컨트롤할 때 val() 사용
			// text() or html()은 input을 제외한 태그를 컨트롤할 때 사용.
		}
	});
}

// 페이지 css 넣기
getPageNum();
function getPageNum() {
	var pageNum = $('#nowPageNum').val(); // 현재 페이지번호
	$('#pageNum' + pageNum).css('backgroundColor', '#287bff');
	$('#pageNum' + pageNum).css('color', '#fff');
}

// 게시판 리스트 조회
function getBoardList(pageNum, pageSize) {
	location.href = "/board?pageNum=" + pageNum + "&pageSize=" + pageSize;
}

// 게시판 작성자 검색 리스트 조회
function getSearchedList(pageNum, pageSize) {
	var search = $('#searchBar').val().trim();
	$('#keyword').val(search);
	var keyword = $('#keyword').val();
	location.href = "/board/search?writer=" + keyword + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
}
function getSerchedNavList(pageNum, pageSize, writer) {
	location.href = "/board/search?writer=" + writer + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
}
$('#searchBar').keyup(function(key) {
	var pageNum = 1;
	var pageSize = 10;
	if (key.keyCode == 13) { // 엔터 == 13
		var search = $('#searchBar').val().trim();
		if (search != '') {
			$('#keyword').val(search);
			getSearchedList(pageNum, pageSize)
		}
	}
})

// 클릭한 게시물 확인하는 함수
function getBoard(boardId) {
	// boardId를 html에 hidden 하기
	$('.update-popup').css('display', 'block')
	$.ajax({
		url: "/api/v1/board/boardId/" + boardId,
		type: "GET",
		dataType: "json", // 서버 결과를 json으로 응답받겠다.
		success: function(response) {
			// input에 데이터 set 하기
			$('#upt-title').val(response.title);
			$('#upt-content').val(response.content);
			$('#boardIdHidden').val(boardId);
			setBoardViews(boardId); // 조회수 증가 함수 실행
		}
	});
}

// 조회수 카운트  
function setBoardViews(boardId) {
	$.ajax({
		url: "/api/v1/board/views/boardId/" + boardId,
		type: "PATCH",
		dataType: "json",
		success: function(response) {
			if (response > 0) {
				// 에러 페이지로 이동하는 로직
			}
		}
	});
}

// 게시판 작성
$("#contentSubmit").click(function() {
	var title = $("#title").val();
	var content = $("#content").val();
	var studentsId = $('#studentsId').val();

	if (studentsName == "") {
		alert("작성자를 입력해주세요.");
		return false;
	}
	if (title == "") {
		alert("제목을 입력해주세요");
		return false;
	}
	if (content == "") {
		alert("내용을 입력해주세요.");
		return false;
	}
	var jsonData = {
		"studentsId": studentsId,
		"title": title,
		"content": content
	}
	if (confirm('게시글을 작성하시겠습니까?')) {
		$.ajax({
			url: "/api/v1/board",
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(jsonData),
			success: function(response) {
				if (response > 0) {
					var pageNum = $('#nowPageNum').val();
					getBoardList(pageNum, 10);
				}
			}
		});
	}
});

// 게시물 수정
$('#contentUpdate').click(function() {
	var boardId = $('#boardIdHidden').val(); // 숨겨둔 boardId 가져오기
	var title = $('#upt-title').val();
	var content = $('#upt-content').val();
	if (title == '') {
		alert("제목을 작성해주세요.")
		return false;
	}
	if (content == '') {
		alert("내용을 작성해주세요.")
		return false;
	}
	var jsonData = {
		title: title,
		content: content
	};
	$.ajax({
		url: "/api/v1/board/boardId/" + boardId,
		type: "PATCH",
		contentType: "application/json", // 서버에 json 타입으로 보낼 예정(요청)
		dataType: "json", // 서버 결과를 json으로 응답받겠다.
		data: JSON.stringify(jsonData), // 문자로 인식되는 걸 json 타입으로 바꿈
		success: function(response) {
			console.log(response)
			if (response > 0) {
				var pageNum = $('#nowPageNum').val();
				getBoardList(pageNum, 10); // 보드 다시 불러 옴
			}
		}
	});
});

// 게시물 삭제
$('#contentDelete').click(function() {
	var boardId = $('#boardIdHidden').val();
	if (confirm('삭제하시겠습니까?')) {
		$.ajax({
			url: "/api/v1/board/boardId/" + boardId,
			type: "DELETE",
			dataType: "json",
			success: function(response) {
				if (response > 0) {
					alert('삭제 완료');
					var pageNum = $('#nowPageNum').val();
					getBoardList(pageNum, 10);
				}
			}
		});
	}
});

// esc 누르면 팝업 닫기
$(document).keydown(function(key) {
	if (key.keyCode == 27) {
		// window.close();
		// window.location.reload();
		$('.update-popup').css('display', 'none')
		$('.write-popup').css('display', 'none')
		return false;
	}
})