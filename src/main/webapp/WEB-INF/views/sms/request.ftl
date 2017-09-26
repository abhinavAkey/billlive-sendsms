<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="/billlive-sendsms/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/billlive-sendsms/resources/css/style.css">
</head>
<#include "header.ftl">
<body>
<div id="layout">
<#include "menu.ftl">
    <div class="main" style="margin:55px; max-width:720px;">
    <#include "send-sms-screen.ftl">
    <#include "response.ftl">
    </div>
</div>
</body>

<script src="/billlive-sendsms/resources/js/jquery.js" type="text/javascript"></script>
<script src="/billlive-sendsms/resources/js/state.js" type="text/javascript"></script>
<script src="/billlive-sendsms/resources/js/tools.js" type="text/javascript"></script>

</html>
