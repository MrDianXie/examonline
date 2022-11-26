login={}

login.toLogin = function (){
    // alert("你干嘛！！哎呦")
    var param = {
        //通过jq获取参数
        tname : $('#tname').val(),
        tpass : $('#tpass').val()
    }

    if (param.tname === ""){
        alert("账号不能为空")
    } else {
        if (param.tpass === "") {
            alert("密码不能为空")
            return false;
        } else {
            //设置请求发送的方法
            $.post("common/login",param,function (f) {
                if (f === true){
                    //成功
                    alert("登录成功");
                    location.href = "common/main.html";
                } else {
                    //失败
                    $("#msg").html("用户名或密码错误");
                    $("#tpass").val("");
                }
            })
        }
    }
}

