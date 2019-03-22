var stompClient = null;
var price = 10000;
var object = {
    price : price,
    userName : "aaa",
    itemId : "1",
    itemName : "鸡公碗"
};

//控制按钮
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

//升级连接及订阅
function connect() {
    var socket = new SockJS('/actionSystem-action-webSocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/auctioning/1_鸡公碗', function (object0) {
            var object1 = JSON.parse(object0.body)
            showGreeting(object1.price);
        });
        // stompClient.subscribe('/topic/getComment', function (greeting) {
        //     showComment(JSON.parse(greeting.body).content);
        //     var area = document.getElementById("commentDiv");
        //     area.scrollTop = area.scrollHeight - area.offsetHeight;
        //
        // });
    });
}

//断开连接
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    object.price = price++;
    stompClient.send("/auctioningOp/increaseInPrice", {}, JSON.stringify(object));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function sendComment(){
    stompClient.send("/topic/getComment", {}, JSON.stringify({offerAPrice : $("#comment").val()}));
}

function showComment(message){
    $("#commentArea").append("<li>" + message +"</li>")
}

//绑定按钮方法
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });

    $("#addComment").click(function () {
       sendComment();
    });
});