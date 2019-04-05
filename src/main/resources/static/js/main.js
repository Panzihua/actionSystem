$(function () {

//    -----导航初始化-------//
    $('#navBar ul li a').click(function (e) {
        e.preventDefault();
        $(this).tab('show')
    });

    //-----加载初始化-------//
    var pageNum = ['visitorRegister.html','visitorRegister.html','preRecord.html'];
    $(window).load(function () {
        $('#page1').load('./'+pageNum[0]);
    });

    //-------遍历导入页面-------------//
    $.each($('ul li'), function (index, value) {
        $(this).click(function (e) {
            e.preventDefault();
            $('#page1').load('./'+pageNum[index]);

        })
    });




//

});
