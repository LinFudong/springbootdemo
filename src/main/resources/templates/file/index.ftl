<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <script src="../static/scripts/jquery.min.js"></script>
    <title>add file</title>

</head>
<body>
<script>
    $(".submit").on("click", function () {
        var url = $("#fileForm").attr("action");
        var formData = $("#fileForm").serialize();
        $.ajax({
            type: 'post',
            url: url,
            cache: false,
            data: formData,
            contentType: 'application/json',
            dataType:'json',
            success:function(){},
            error:function(data){
                alert("error")
            }
        })
    })
</script>
<form id="fileForm" action="/file/save" method="post">
    <p>
        <input type="text" id="name" name="name" maxlength="100" placeholder="file name"/>
    </p>
    <p>
        <textarea id="content" name="content" rows="4" cols="50"></textarea>
    </p>
    <p>
        <input type="submit" value="Save"/>
    </p>
</form>
</body>
</html>