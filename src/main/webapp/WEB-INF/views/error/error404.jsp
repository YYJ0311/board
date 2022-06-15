<!DOCTYPE html>
<html lang="en">
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap');
    * {margin: 0; padding: 0; box-sizing: border-box; font-family: 'Poppins', sans-serif;}
    body {background: linear-gradient(45deg, #8500ff, #5acaff); height: 100vh;}
    .container {position: absolute; top: 10%; left: 10%; right: 10%; bottom: 10%; display: flex; justify-content: center; align-items: center; background: url('/resources/static/images/error.png'), #151729; box-shadow: 0 15px 30px rgba(0,0,0,.5); border-radius: 10px;}
    .container .content {max-width: 600px; text-align: center;}
    .container .content h2 {font-size: 18vw; color: #fff; line-height: 1em;}
    .container .content h4 {position: relative; font-size: 1.5em; margin-bottom: 20px; color: #111; background: #fff; font-weight: 300; padding: 10px 20px; display: inline-block;}
    .container .content p {color: #fff; font-size: 1.2em;}
    .container .content a {position: relative; display: inline-block; padding: 10px 25px; background: #ff0562; color:#fff; text-decoration: none; margin-top: 25px; border-radius: 25px; border-bottom: 4px solid #d00d56;}

</style>
</head>
<body>
    <div class="container">
        <div class="content">
            <h2>404</h2>
            <h4>Opps! This is not the web page.</h4>
            <p>없는 페이지 이거나 없는 URL 주소입니다. 주소를 다시 입력해주세요.</p>
            <a href="/login">로그인 화면 이동</a>
        </div>
    </div>
</body>
</html>