$(function () {
    $("#subscribeButton").click(function () {
        var subButton = $("#subscribeButton");

        if (subButton.text() == "订阅") subscribeFunction();
        else notSubscribeFunction();
    });
});

function subscribeFunction() {
    var itemId = $("#itemId").text();

    $.ajax({
        type: "GET",
        url: "/auctionSystem/subscribe",
        contentType : "application/json",
        data: {itemId : itemId},
        success: function(flag){
            if (flag)
                $("#subscribeButton").text("取消订阅");
        }
    });
}

function notSubscribeFunction() {
    var itemId = $("#itemId").text();

    $.ajax({
        type: "GET",
        url: "/auctionSystem/cancelSubscribe",
        contentType : "application/json",
        data: {itemId : itemId},
        success: function(flag){
            if (flag)
                $("#subscribeButton").text("订阅");
        }
    });
}