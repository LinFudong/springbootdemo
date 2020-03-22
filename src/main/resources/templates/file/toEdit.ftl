<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <script src="../static/scripts/jquery.min.js"></script>
    <title>${entity.name}</title>
</head>
<body>
<script>
    $(document).ready(function(){
        download();
    });


    function download() {
        var url = "http://localhost:8080/file/download?fileName=${entity.name}"
        window.open(url, '_blank');
    }

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
            success:function(data){},
            error:function(){
                alert("error");
            }
        })
    })
</script>
<form id="fileForm" action="/file/edit" method="post">
    <input type="hidden" id="id" name="id" value="${entity.id}"/>
    <p>
        <textarea id="content" name="content" rows="4" cols="50">${entity.content}</textarea>
    </p>
    <p>
        <input type="submit" value="Save"/>
    </p>
</form>

</body>
</html>