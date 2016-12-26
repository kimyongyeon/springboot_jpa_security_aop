<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index page</title>
    <link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <script src="/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/bower_components/vue/dist/vue.min.js"></script>
    <style>
        .main {
            font-size: 0px;
        }

        .main > div {
            display: inline-block;
            vertical-align: top;
            height: 900px;
            font-size: 14px;
        }

        .left {
            width: 10%;
            height: auto;
            border: 1px solid red;
        }

        .right {
            width: 89%;
            height: auto;
            border: 1px solid blue;
        }
    </style>
</head>
<body>
<h1>index page : 메인 페이지 입니다. </h1>
<div class="main">
    <div class="left">
        <ul>
            <li><a href="/">홈으로</a></li>
            <li><a href="/main">메인화면</a></li>
            <li><a href="/fileUpload">파일업로드</a></li>
            <li><a href="/hello.html">일반페이지(public)</a></li>
        </ul>
    </div>
    <div class="right">
        <h2>한글은 절대 깨지면 안됩니다.</h2>
        <h3><#if message != ''> ${message} </#if> </h3>
        <p>모델로 던진 값을 받을 수 있을까요?</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt, nam nihil. Est facere minima nulla quasi
            suscipit. Ab aspernatur cum cumque esse minus nihil obcaecati quidem repellendus unde? Beatae blanditiis
            laboriosam sunt veniam! Accusantium alias aliquid consequatur dicta dolore et hic id ipsam, magni
            perspiciatis sunt tempora? Cumque debitis delectus eius expedita fugit laborum magnam molestias nemo nostrum
            porro quasi quia, quis sed veniam voluptatum! Ab architecto asperiores expedita minus nisi officia
            repellendus totam ullam voluptas. A accusantium, commodi cum dolor dolore harum odio quia! Architecto
            asperiores beatae blanditiis, ducimus omnis quam quis voluptas? Consequuntur harum maiores rem repudiandae
            rerum!</p>
    </div>
</div>
</body>
</html>