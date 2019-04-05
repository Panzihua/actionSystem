$(function () {
    //--一层modal---//
    $('#modal-bg').hide();
    $('#new_work').click(function(e)  {
            e.preventDefault();
            $('#modal-bg').fadeIn();
        }
    );
    //---显示case事件------//
    $('#modal-box').click(function(e)  {
            e.stopPropagation();
            $('#modal-caseNum').fadeIn();
        }
    );
    //-----点击切换case----//
    //---载入case1---//
    $('#case1').click(function(){
        $('#loading-box').load('case1.html',function () {
            $('#case1-chein-btn').click(function(){
                $('#loading-box').load('confirm.html',function () {
                    $('#confirm-btn-can').click(function () {
                        $('#loading-box').load('visitorRegister.html')
                    })
                });
            })
            $('#case1-newvis-btn').click(function(){
                $('#loading-box').load('visitorRegister.html');
            })
            $('#case1-can-btn').click(function(){
                $('#loading-box').load('visitorRegister.html')

            })
        });
        $('#modal-bg').hide();
    });
    //---载入case2---//
    $('#case2').click(function( e){
        $('#modal-bg').hide();
        $('#idcard-bg').fadeIn();

    });
    $('#box-btn-l').click(function(){
        $('#loading-box').load('case2.html',function () {
            $('#idcard-bg').hide();
            $('#case2-chein-btn').click(function () {
                $('#loading-box').load('confirm.html',function () {
                    $('#confirm-btn-can').click(function(){
                        $('#loading-box').load('visitorRegister.html');
                    })
                });
            });
            $('#case2-newvis-btn').click(function () {
                $('#loading-box').load('visitorRegister.html');
            });
            $('#case2-can-btn').click(function () {
                $('#loading-box').load('visitorRegister.html');
            });
        });
    });

    //---载入case3---//
    $('#case3').click(function(){
        $('#loading-box').load('case3-.html',function () {
            $('#case3-chein-btn').click(function(){
                $('#loading-box').load('confirm.html',function () {
                    $('#confirm-btn-can').click(function(){
                        $('#loading-box').load('visitorRegister.html')
                        $('#quick-check-out-bg').hide();
                    })
                });
            })
            $('#case3-newvis-btn').click(function(){
                $('#loading-box').load('visitorRegister.html');
            })
            $('#case3-can-btn').click(function(){
                $('#loading-box').load('visitorRegister.html')

            })
        });
        $('#modal-bg').hide();
    });
    //---载入case4---//
    $('#case4').click(function(){
        $('#loading-box').load('indentityInfo.html #identity-page',function () {
            $('#msg-box').hide();
            $('#confirm-box').hide();
            $('.cancelBtn-box .btn-danger').click(function(){
                $('#loading-box').load('visitorRegister.html')

            })
        });
        $('#modal-bg').hide();

    });
    //--载入case5---//
    $('#case5').click(function(){
        $('#loading-box').load('indentityInfo.html #identity-page',function () {
            $('#confirm-box').hide();
            $('.cancelBtn-box .btn-primary').removeClass('disabled').click(function(){
                $('#loading-box').load('confirm.html',function () {
                    $('#confirm-btn-can').click(function(){
                        $('#loading-box').load('visitorRegister.html')
                        $('#quick-check-out-bg').hide();
                    })
                })
            });
            $('.cancelBtn-box .btn-danger').click(function(){
                $('#loading-box').load('visitorRegister.html');
                $('.cancelBtn-box').remove();
            })
        });
        $('#modal-bg').hide();

    });
    //--载入case6---//
    $('#case6').click(function(){
        $('#loading-box').load('indentityInfo.html #identity-page',function () {
            $('#confirm-box').remove();
            $('.cancelBtn-box .btn-danger').click(function(){
                $('#loading-box').load('visitorRegister.html')

            })
        });
        $('#modal-bg').hide();

    });
    //--载入case7---//
    $('#case7').click(function(){
        $('#loading-box').load('indentityInfo.html #change-box',function () {
            $('.cancelBtn-box .btn-danger').click(function(){
                $('#loading-box').load('visitorRegister.html')

            })
        });
        $('#modal-bg').hide();

    });
    //-----点击confirm事件----//
    $('#quick-check-out-bg').hide();
    $('#quick-check-out').click( function(e)  {
            e.preventDefault();
            $('#quick-check-out-bg').fadeIn();
        }
    );
    $('#btn-confirm').click(function(){
        $('#loading-box').load('confirm.html',function () {

        });

    });
    $('#btn-cancel').click(function(){
        $('#quick-check-out-bg').hide();
        // $('#loading-box').load('visitorRegister.html');
    });
    //-----点击case2新弹窗按钮事件----//
    $('#idcard-bg').hide();
    //点击Manually enter

    //点击Cancel
    $('#box-btn-r').click(function(){
        $('#idcard-bg').hide();
        $('#modal-caseNum').hide();
    });

    $('#case3-can-btn').click(function () {
        $('#loading-box').load('visitorRegister.html')
    })

});
