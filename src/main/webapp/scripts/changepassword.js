/**
 * 更换密码页面的js脚本
 */
//更换密码
function changePassword() {
	$("#warning_1").hide();
	var ok = true;
	// 获得请求参数
	var userId = getCookie("uid");
	var lastPassword = $("#last_password").val().trim();
	var newPassword = $("#new_password").val().trim();

	// 格式检查
	if (userId == null) {
		ok = false;
		window.location.href = "log_in.html";
	}
	// 发送ajax请求
	if (ok) {
		$.ajax({
			url : path + "/user/changepassword.do",
			type : "post",
			data : {
				"userId" : userId,
				"lastPassword" : lastPassword,
				"newPassword" : newPassword
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					alert(result.msg);
				}
				if (result.status == 1) {
					$("#warning_1").show();
				}
			},
			error : function() {
				alert("修改密码异常");
			}
		});
	}

}

//返回主页面
function back(){
	window.location.href = "edit.html";
}