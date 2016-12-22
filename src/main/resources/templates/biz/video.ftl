<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <title>구술기록영상 정보보기</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <!-- jquery 필요 -->
    <script type="text/javascript" src="/bower_components/control/gmedia/vod/player/jquery-1.8.3.js"></script>


    <script type="text/javascript">

        var playerWidth = '640';
        var playerHeight = '480';

        var gmplayer = null; //플레이어 객체

        var state = ""; // 플레이어 상태
        var duration = 0; // 영상의 총 시간
        var currentTime = 0; // 현재 플레이 시간

        var browser = "";
        var strUA;
        strUA = navigator.userAgent;
        if(strUA.indexOf("MSIE") != -1){
            browser = "IE";
        }else if(strUA.indexOf("Trident/7.0") != -1){ // IE 10이상
            browser = "IE11";
        }else if(strUA.indexOf("Chrome") != -1){
            browser = "Chrome";
        }else if(strUA.indexOf("Safari") != -1){
            browser = "Safari"
        } else {
            browser = "IE";
        }

        // 플레이어 로딩 완료
        function GMPlayerCreationComplete() {
            if (browser != "IE") { // IE11 및 크롬, 사파리, 파이어 폭츠
                gmplayer = $("embed[name=GMPlayer]").get(0);

                if(gmplayer == null || gmplayer == "undefined"){
                    gmplayer = document.getElementById("GMPlayer");
                }

            } else { // IE10 이하
                gmplayer = document.getElementById("GMPlayer");
            }

            gmplayer.GMPlayerInit();
        }


        //플레이어 재생 정보 전달
        /*
         * 영상 URL
        rtmp://10.201.38.35/vod3/mp4:2016/12/nanet0001.mp4 - 이용안내
        rtmp://10.201.38.35/vod3/mp4:2016/12/nanet0002.mp4 - korean
        rtmp://10.201.38.35/vod3/mp4:2016/12/nanet0003.mp4 - english

         * 이미지 주소의 root 위치에 crossdomain.xml 이 존재 해야함
         ex)
         http://rms.nanet.go.kr:8088/crossdomain.xml
         */
        function GMPlayerConfig() {
            return{
                playURL : 'rtmp://10.201.38.35/vod3/mp4:2016/12/nanet0002.mp4', // 영상 url
                titleImage : 'http://rms.nanet.go.kr:8088/static/images/thumbnail/2016/12/nanet0001_1.jpg', // 영상 title image
                autoPlay : false, // 자동재생설정 ( true or false )
                playerWidth : playerWidth,
                playerHeight : playerHeight,
                snakeImage : true,
                snakeImageURL : 'http://rms.nanet.go.kr:8088/static/images/thumbnail/2016/12/nanet0001.jpg',
                snakeImageWidth : '100',
                snakeImageHeight : '60',
                snakeImageRowNum : '20',
                snakeImageCellNum : '100',
                snakeImageTimeInterval : '1'
            }
        }

        // 크로스 도메인 URL 정보 호출 - crossdomain.xml 은 root 위치에서 호출 되어야 함
        function getCrossDomain(){
            return 'http://localhost:8081/bower_components/crossdomain.xml';
        }


    </script>
</head>
<body>

<div id="movDiv" style="width:640px; height:480px; background-color:#000000">
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%"
            id="GMPlayer"
            codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
        <param name="movie" value="/bower_components/control/gmedia/vod/player/GMPlayer.swf" /> <!-- 플레이어 swf  경로 설정 -->
        <param name="quality" value="high" />
        <param name="bgcolor" value="#869ca7" />
        <param name="allowScriptAccess" value="sameDomain" />
        <param name="wmode" value="opaque" />
        <param name="allowFullScreen" value="true" />
        <!-- 플레이어 swf  경로 설정 -->
        <embed src="/bower_components/control/gmedia/vod/player/GMPlayer.swf" quality="high" bgcolor="#869ca7"
               width="100%" height="100%" name="GMPlayer" align="middle"
               play="true"
               loop="false"
               quality="high"
               wmode ="opaque"
               allowScriptAccess="always"
               allowFullScreen ="true"
               type="application/x-shockwave-flash"
               pluginspage="http://www.adobe.com/go/getflashplayer" >
        </embed>
    </object>
</div>

<script type="text/javascript">
</script>
</body>
</html>
