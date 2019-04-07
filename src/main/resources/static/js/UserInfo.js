$(function () {
   $("#updateButton").click(function () {
       var condition = {
           userName : $("#userName").val(),
           userCredentialType : $("#credentialType").val(),
           userCredentialNum : $("#credentialNum").val(),
           userSex : $("#userSex").val(),
           userBirthString : $("#birth").val()
       };

       $.ajax({
           type: "POST",
           url: "/auctionSystem/updateUserInfo",
           contentType : "application/json",
           data: JSON.stringify(condition),
           success: function(flag){
               if (flag)
                   alert("更新成功");
           }
       });
   });
});