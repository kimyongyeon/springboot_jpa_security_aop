<#import "../layouts/default.ftl" as layout>
<@layout.myLayout>
<div>
    <#if message??>${message}</#if>
</div>

<div>
    <form method="POST" enctype="multipart/form-data" action="/fileUpload">
        <table>
            <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
            <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
        </table>
    </form>
</div>

<div>
    <ul>
    <#list files as item>
        <li>
            <a href="${item}">${item}</a>
        </li>
    </#list>
    </ul>
</div>
</@layout.myLayout>
