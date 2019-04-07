$(function () {
    $("#uploadButton").click(function () {
        var files = $('#uploadOffice').prop('files');
        var data = new FormData();
        data.append("uploadOffice", files[0]);
        $.ajax({
            type: "POST",
            url: "uploadPicture",
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            success: function(message){
                var uploadFileName = files[0].name;
                changePicture(uploadFileName);
            }
        });
    });
});

function changePicture(fileName) {
    $("#picture").attr("src", "getPicture?fileName=" + fileName);
    $("#pictureText").val(fileName);
}