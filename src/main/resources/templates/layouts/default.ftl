<#macro myLayout>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index page</title>
    <link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bower_components/font-awesome/css/font-awesome.css">
    <script src="/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/bower_components/vue/dist/vue.min.js"></script>
</head>
<body style="width:100%;height:100%">
<table border="1" cellspacing="0" cellpadding="0" style="width:100%;height:100%">
    <tr>
        <td colspan="2">
            <#include "header.ftl"/>
        </td>
    </tr>
    <tr>
        <td>
            <#include "menu.ftl"/>
        </td>
        <td>
            <#nested/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <#include "footer.ftl"/>
        </td>
    </tr>
</table>
</body>
</html>
</#macro>