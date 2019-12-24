
jQuery(document).ready(function() {
    var uuid = getUUID();
    $(".uuid").val(uuid);
    console.log(uuid);
    $(".captcha-msg").attr("src","http://127.0.0.1:8081/cnr/captcha.jpg?uuid="+uuid);
    $(".loginSub").click(function () {
        var username = $(".username").val();
        var password = $(".password").val();
        var captcha = $(".captcha-val").val();
        var uuid = $(".uuid").val();
        var loginRequest = {"username": username, "password": password,"captcha":captcha,"uuid":uuid};
        $.ajax({
            url: "admin/sys/login",
            type: "post",
            data: JSON.stringify(loginRequest),
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                if(data.code == 200){
                    document.cookie = 'token='+data.token;
                    console.log(data.token);
                    window.location.href = 'admin/index';
                }else {
                    alert(data.msg);
                }

            }
        });
    });

    $(".captcha-msg").click(function () {
        var uuid = getUUID();
        $(".uuid").val(uuid);
        console.log(uuid);
        $(".captcha-msg").click(function () { $(this).attr("src","http://127.0.0.1:8081/cnr/captcha.jpg?uuid="+uuid); })
    });

    function getUUID() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            return (c === 'x' ? (Math.random() * 16 | 0) : ('r&0x3' | '0x8')).toString(16);
        });
    }

});