function userLogin() {
	var username = $("#count").val().trim();
	var password = $("#password").val().trim();
	
	
	//清空span的值
	$("#count_span").html("");
	$("#password_span").html("");
	var ok = true;
	// 检测用户名和密码是否为空
	if (username == null || username=="") {
		$("#count_span").html("用户名不能为空");
		ok = false;
	}
	if (password == null || password=="") {
		$("#password_span").html("密码不能为空");
		ok = false;
	}

	if (ok) {// 检测通过
		// 发送ajax请求
		$.ajax({
			url : "user/login.do",
			type : "post",
			data : {
				"username" : username,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				// result为服务器返回的json结果
				if (result.status == 0) {
					// 将用户信息保存到cookie
					var userId = result.data.cn_user_id;
					
					addCookie("uid", userId, 2);
					 var userId = getCookie("uid");
					 
					window.location.href = "edit.html";

				} else if (result.status == 1) {
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {
					$("#password_span").html(result.msg);
				}

			},
			error : function() {
				alert("登录失败");
			}
		})
	}
}