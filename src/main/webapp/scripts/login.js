/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//注册
	$("#regist_button").click(function(){
		register();
	});
	
	//登录
	$("#login").click(function(){
		login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
	
});
//雁阵用户名是否存在
function checkName(e) {
    var name=$(e).val().trim();
    if(name == null || name.length == 0) {
        $('#regist_username').attr("data-content", "用户名不能为空");
        $('#regist_username').popover('show');
        return;
    }
    $.ajax({
        url:"/user/checkName.do",
        method:"post",
        data:{name:name},
        success:function(result){
            if(result){
                $('#regist_username').attr("data-content", "用户名可以使用");
                $('#regist_username').popover('show');
            }else{
                $('#regist_username').attr("data-content", "用户名已存在");
                $('#regist_username').popover('show');
            }

        }
    })
}

function clearError(e) {
    $(e).popover('hide');
}

//注册
function register() {
    var name=$('#regist_username').val().trim();
    var nickname=$('#nickname').val().trim();
    var password=$('#regist_password').val().trim();
    var final_password=$('#final_password').val().trim();
    if(name == null || name.length == 0) {
        $('#regist_username').attr("data-content", "用户名不能为空");
        $('#regist_username').popover('show');
        return;
    }
    if(nickname == null || nickname.length == 0) {
        $('#nickname').attr("data-content", "昵称不能为空");
        $('#nickname').popover('show');
        return;
    }
    if(password == null || password.length < 6) {
        $('#regist_password').attr("data-content", "密码不能少于6位");
        $('#regist_password').popover('show');
        return;
    }
    if(final_password == null || final_password.length == 0) {
        $('#final_password').attr("data-content", "确认密码不能为空");
        $('#final_password').popover('show');
        return;
    }
    if(password != final_password) {
        $('#final_password').attr("data-content", "两次密码不一致");
        $('#final_password').popover('show');
        return;
    }
    $.ajax({
        url:'/user/reg.do',
        method:"post",
        data:{name:name,nickname:nickname,password:password},
        success:function (result) {
            if(result.success){
                alert("成功");
                location.href="login.html";
            }else{
                $('#error').html(result.msg);
            }
        }
    })
}

//登陆
function login() {
        var name=$('#name').val().trim();
        var password=$('#password').val().trim();
    if(name == null || name.length == 0) {
        $('#name').attr("data-content", "用户名不能为空");
        $('#name').popover('show');
        return;
    }
    if(password == null || password.length == 0) {
        $('#password').attr("data-content", "密码不能为空");
        $('#password').popover('show');
        return;
    }
        $.ajax({
            url:'/user/login.do',
            method:"post",
            data:{name:name,password:password},
            success:function (result) {
                if(result.success){
                	addCookie("userId",result.value.id);
                	addCookie("nickname",result.value.nickname);
                    alert(result.value.nickname+"登录成功");
                    location.href="/edit.html";
                }else{
                    $('.error').html(result.msg);
                }
            }
        })
}

/**
 * 退出登录
 */
function logout(){
    delCookie("userId");
    delCookie("nickname");
    $.ajax({
        url:'user/logout.do',
        method:'get',
        success:function () {
            location.href="login.html";
        }
    })


}

/**
 * 修改密码
 */
function changepwd(){
    var password=$('#last_password').val().trim();
    var new_password=$('#new_password').val().trim();
    var final_password=$('#final_password').val().trim();
    var id= getCookie('userId');
    $.ajax({
        url:'/user/updatePassword.do',
        method:"post",
        data:{id:id,old_password:password,password:final_password},
        success:function (result) {
            if(result.success){
                delCookie("userId");
                delCookie("nickname");
                alert("修改成功");
            }else{
                alert(result.msg);
            }
        }
    })

}


