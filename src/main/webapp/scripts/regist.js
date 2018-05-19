/**
 * 用户注册函数
 */
function regist() {
	var username = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var final_password = $("#final_password").val().trim();

	// 表示参数状态
	var ok = true;
	// 检测用户名
	if (username == "") {
		$("#warning_1").show();
		ok = false;
	}
	// 检测密码：1，检测密码是否为空 2，检测密码长度是否小于6

	if (password == "") {
		$("#warning_2 span").html("密码不能为空");
		$("#warning_2").show();
		ok = false;
	} else if (password.length > 0 && password.length < 6) {
		$("#warning_2 span").html("密码长度要大于6位");
		$("#warning_2").show();
		ok = false;
	}
	// 检测确认密码
	if (final_password != password) {
		$("#warning_3").show();
		ok = false;
	}

	if (ok) {// 检测通过
		// 发送ajax请求
		$.ajax({
			url : "user/add.do",
			type : "post",
			data : {
				"username" : username,
				"nick" : nick,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 1) {
					$("#warning_1").html(result.msg);
					$("#warning_1").show();

				} else if (result.status == 0) {
					alert(result.msg);
					$("#back").click();
				}

			},
			error : function() {
				alert("注册失败");
			}
		})
	}
}