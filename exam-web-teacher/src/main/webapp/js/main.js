var main = {};

main.toUpdatePwd = function (){

    //发送请求-请求修改密码页面
    $.post("common/updatePwdTemplate.html",{},function (view) {
        //将获取到的view放入modal-body中
        $("#modal-body").html(view);

        //再打开模态窗口
        $("#myModal").modal("show");
    })



};

main.exit = function () {

    if (!confirm("是否确定退出"))
        return;
    location.href="common/exit"
};

//设计一个方法用于请求修改密码
main.updatePwd = function () {
    //测试
    // alert("哎呀你干嘛")
    //获取密码信息
    var param = {

        //获取原密码
        old_pass : $("#old-pass").val(),
        //获取新密码
        new_pass : $("#new-pass").val(),
        //获取确认密码
        re_pass : $("#re-pass").val()
    }

    //判断原密码是否为空
    if (param.old_pass === ""){
        alert("原密码不能为空");
        return;
    }

    //判断新密码是否为空
    if (param.new_pass === ""){
        alert("新密码不能为空")
        return;
    }

    //判断确认密码是否为空
    if (param.re_pass === ""){
        alert("确认密码不能为空")
        return;
    }

    //判断两次密码是否相同
    if (!(param.new_pass === param.re_pass)){
        alert("两次密码不相同")
        return;
    } else {
        //两次密码相同
        //使用ajax发送请求
       $.post("common/updatePwd",param,function (f) {
            if (f){
                alert("修改成功")
                //关闭模态窗口
                $("#myModal").modal("hide")
                //修改密码后用户需要重新登录
                location.href="common/exit"
            } else {
                alert("原密码错误")
            }
       })
    }
}
