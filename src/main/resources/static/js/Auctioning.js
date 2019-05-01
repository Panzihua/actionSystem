var stompClient = null;

$(function () {
    connect();

    $("#offerAPriceButton").click(function () {
        increasePrice()
    });

    $("#offerDefaultPriceButton").click(function () {
        increaseDefaultPrice();
    });

    $("#sendButton").click(function () {
        sendComment();
    });

});

function connect() {
    var socket = new SockJS('/auctionSystem/actionSystem-action-webSocket');
    stompClient = Stomp.over(socket);

    var itemId = $("#itemId").text();
    var itemName = $("#itemName").text();

    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/auctionSystem/auctioning/" + itemId + "_" + itemName, function (data) {
            if (data == null) return;

            var offerPrice = JSON.parse(data.body)
            updatePrice(offerPrice)
        });
        stompClient.subscribe("/auctionSystem/auctioning" + itemId + "_" + itemName + "/getComment", function (data) {
            showComment(JSON.parse(data.body).comment);
            
            var area = document.getElementById("commentDiv");
            area.scrollTop = area.scrollHeight - area.offsetHeight;

        });
    });
}

function updatePrice(offerPrice) {
    $("#nowPrice").val(offerPrice.price);

    var itemId = $("#itemId").text();
    var itemName = $("#itemName").text();

    stompClient.send("/auctionSystem/auctioning" + itemId + "_" + itemName + "/getComment", {},
        JSON.stringify({comment : "当前最高价为" + offerPrice.price + "! 由用户:" + offerPrice.userName + "所出"}));
}

function sendComment() {
    var itemId = $("#itemId").text();
    var itemName = $("#itemName").text();

    stompClient.send("/auctionSystem/auctioning" + itemId + "_" + itemName + "/getComment", {},
        JSON.stringify({comment : $("#userName").text() + ":" + $("#comment").val()}));

    $("#comment").val("");
}

function sendPrice(price) {
    var offerAPrice = {
        price : price,
        userName : $("#userName").text(),
        userAccount : $("#userAccount").text(),
        itemId : $("#itemId").text(),
        itemName : $("#itemName").text()
    };

    stompClient.send("/auctionSystem/auctioningOp/offerAPrice", {}, JSON.stringify(offerAPrice));

    alert("出价成功");
}

function increasePrice() {
    if ($("#nowPrice").val() * 1 + $("#defaultIncrease").val() * 1 > $("#offerPrice").val() * 1){
        alert("出价不得低于加价之后的价格");
        $("#offerPrice").val("");
        return;
    }

    var price = $("#offerPrice").val() * 1;

    sendPrice(price);
}

function increaseDefaultPrice() {
    var price = $("#nowPrice").val() * 1 + $("#defaultIncrease").val() * 1;

    sendPrice(price);

    $("#offerPrice").val("");
}

function showComment(content) {
    $("#commentArea").append("<li>" + content +"</li>");
}