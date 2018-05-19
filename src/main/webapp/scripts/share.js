// 分页加载搜索分享笔记
function searchSharePage(keyword, page) {
	$.ajax({
		url : path + "/note/searchshare.do",
		type : "post",
		data : {
			"keyword" : keyword,
			"page" : page
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 获取服务器返回的搜索结果
				var shares = result.data;
				// 循环解析生成列表li元素
				for (var i = 0; i < shares.length; i++) {
					// 分享ID
					var shareId = shares[i].cn_share_id;
					// 分享标题
					var shareTitle = shares[i].cn_share_title;
					// 生成一个li
					var sli = "";
					sli += '<li class="online">';
					sli += '	<a>';
					sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli += shareTitle;
					sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down" title="加入收藏"><i class="fa fa-star"></i></button>';
					sli += '	</a>';
					sli += '</li>';							
					var $li = $(sli);
					$li.data("shareId", shareId);
					// 添加到搜索结果ul中
					$("#pc_part_6 ul").append($li);
				}
			}
		},
		error : function() {
			alert("搜索异常");
		}
	});
};

// 搜索分享笔记
function sureSearchShare(event) {
	var code = event.keyCode;
	if (code == 13) {// 回车键
		// 清除原有列表结果
		$("#pc_part_6 ul").empty();
		// 显示搜索结果列表,其他列表隐藏
		$("#pc_part_2").hide();
		$("#pc_part_4").hide();
		$("#pc_part_6").show();
		$("#pc_part_7").hide();
		$("#pc_part_8").hide();
		// 获取请求参数
		keyword = $("#search_note").val().trim();
		page = 1;// 设置初始值1
		// 发送Ajax请求
		searchSharePage(keyword, page);
	}
}

// "更多"按钮单击处理
function moreSearchShare() {
	// 将page加1
	page = page + 1;
	// 发送Ajax请求加载数据
	searchSharePage(keyword, page);
}

// 查看搜索结果列表的笔记信息
function loadShareNote() {
	// 获取请求参数
	var shareId = $(this).data("shareId");
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/loadshare.do",
		type : "post",
		data : {
			"shareId" : shareId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 获取分享标题
				var title = result.data.cn_share_title;
				// 获取分享内容
				var body = result.data.cn_share_body;
				// 设置标题和内容
				$("#noput_note_title").html(title);
				$("#noput_note_title").next().html(body);
				// 切换显示
				$("#pc_part_3").hide();
				$("#pc_part_5").show();
			}
		},
		error : function() {
			alert("加载笔记信息异常");
		}
	});
}

