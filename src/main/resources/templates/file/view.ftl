<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <script src="https://cdn.staticfile.org/jquery/3.1.1/jquery.min.js"></script>
    <title>files</title>

</head>
<script>
    function toDownload(name) {
        var url = "http://localhost:8080/file/download?fileName=" + name;
        window.open(url, '_blank');
    }

    function toEdit(id) {
        window.location.href = "http://localhost:8080/file/toEdit?fileId=" + id;
    }
</script>
<body>
<table>
    <tr>
        <th>file name</th>
        <th>operation</th>
    </tr>
        <#list list as entity>
            <tr>
                <td><a href="javascript: void(0)" onclick="toDownload('${entity.name?xhtml}');">${entity.name}</a></td>
                <td><a href="javascript: void(0)" onclick="toEdit(${entity.id});">edit</a></td>
            </tr>
        </#list>
</table>
</body>
<style>
    table {
        width: 50%;
        font-size: .938em;
        border-collapse: collapse;
    }
    th {
        text-align: left;
        padding: .5em .5em;
        font-weight: bold;
        background: #66677c;color: #fff;
    }

    td {
        padding: .5em .5em;
        border-bottom: solid 1px #ccc;
    }

    table,table tr th, table tr td { border:1px solid #0094ff; }/*设置边框*/
</style>
</html>