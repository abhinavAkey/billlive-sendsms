<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#include "header.ftl">
<body>
<div id="layout">
<#include "menu.ftl">
    <div class="main" style="margin:55px; max-width:720px;">

    <#list instance_info as detail>

    <div style="border:2px solid grey;margin-top:20px;padding:20px;">${detail.detailHtml}
    <a href="${detail.ruleLoadUrl}" target="_blank">Reload rules</a>
    </div>

    </#list>

    </div>
</div>
</body>

<script src="/billlive-sendsms/resources/js/tools.js" type="text/javascript"></script>

</html>
