var itemName, itemFrom, shelfDate, shopName;

$(function () {
   $("#searchButton").click(function () {
       itemName = $("#itemName").val();
       itemFrom = $("#itemFrom").val();
       shelfDate = $("#shelfDate").val();
       shopName = $("#shopName").val();

       if (itemName != "") itemName = "%" + itemName + "%";
       else itemName = null;
       if (itemFrom != "") itemFrom = "%" + itemFrom + "%";
       else itemFrom = null;
       if (shelfDate != null) shelfDate = new Date($("#shelfDate").val()).getTime();
       if (shopName != "") shopName = "%" + shopName + "%";
       else shopName = null;

       var condition = {
           itemName : itemName,
           itemFrom : itemFrom,
           itemShelfDate : shelfDate,
           shopName : shopName
       };

       getData(condition);
   });
});

function getData(condition) {
    $.ajax({
        type: "POST",
        url: "/auctionSystem/selectItemByCondition",
        contentType : "application/json",
        data: JSON.stringify(condition),
        success: function(data){
            $("#item-msg-box").empty();

            if (data.length != 0) writeTable(data);
        }
    });
}

function writeTable(data) {
    var table = $("#item-msg-box");
    var result = "";

    $.each(data, function (i, temp) {
        result = result + "<div class=\"panel-box\" style=\"height: 300px; width:400px; float: left; margin: 15px\">\n" +
            "                        <div>\n" +
            "                             <p class=\"form-title\">Item Information</p>\n" +
            "                        </div>\n" +
            "                        <div> <table class=\"table table-responsive table-striped person-table\">\n" +
            "                            <tr>\n" +
            "                                <td>商家:</td>\n" +
            "                                <td>" + temp.shopName + "</td>\n" +
            "                                <td></td>\n" +
            "                            </tr>" +
                                        "<tr>\n" +
            "                                <td>名称:</td>\n" +
            "                                <td>"+ temp.itemName + "</td>\n" +
            "                                <td rowspan=\"2\" ><img src=\"/auctionSystem/getPicture?fileName=" + temp.itemPicture + "\" style=\"height:90px; width:80px\"></td>\n" +
            "                            </tr>";
        var date1 = new Date(temp.itemStartDate);
        var date2 = new Date(temp.itemEndDate);
        var now = new Date().getTime();
        result = result + "<tr>\n" +
            "                                <td>开拍时间~结束时间:</td>\n" +
            "<td>" + date1.getFullYear() + "-" + (date1.getMonth()+1) + "-" + date1.getDate() +" " + date1.getHours() + ":" + date1.getMinutes() + ":" + date1.getSeconds() + "~" +
            date2.getFullYear() + "-" + (date2.getMonth()+1) + "-" + date2.getDate() + " " + date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds() + "</td>\n" +
            "\n" +
            "                            </tr>" +
                                        "<tr>\n" +
            "                                <td>起拍价格:</td>\n" +
            "                                <td>"+ temp.itemStartingPrice + "</td>\n" +
            "                            </tr>\n" +
            "                        </table></div>\n" +
            "                        <div class=\" btn-box text-center\">\n" +
            "                            <a class=\"btn btn-default btn-primary\" href=\"order/details?orderId="+ temp.itemId + "\">Detail</a>";
            if (temp.itemStartDate <= now && temp.itemEndDate >= now)
            result = result + "<a class=\"btn btn-danger btn-primary\" href=\"order/details?orderId="+ temp.itemId + "\" style='margin-left: 15px'>正在拍卖</a>";

            result = result + "</div>\n</div>";
    });

    table.append(result);
}