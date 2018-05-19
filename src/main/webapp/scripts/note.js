/** note.js 封装笔记相关操作* */

// 分享笔记
function shareNote() {
	// 获取请求参数
	var $li = $(this).parents("li");
	var noteId = $li.data("noteId");
	// 发送ajax请求
	$.ajax({
		url : path + "/note/sharenote.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 添加分享图标
				var img = '<i class="fa fa-sitemap"></i>';
				$li.find(".btn_slide_down").before(img);
				// 提示成功
				alert(result.msg);
			} else if (result.status == 1) {
				// 提示已分享过
				alert(result.msg);
			}
		},
		error : function() {
			alert("分享笔记异常");
		}

	});
};
// 转移笔记
function moveNote() {
	// 获取请求参数
	// 获取要转移的笔记ID
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 获取要转入的笔记本ID
	var bookId = $("#moveSelect").val();
	// 发送Ajax请求
	$.ajax({
		url : path + "/note/move.do",
		type : "post",
		data : {
			"noteId" : noteId,
			"bookId" : bookId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 移除笔记li
				$li.remove();
				// 提示成功
				alert(result.msg);
			}
		},
		error : function() {
			alert("转移笔记异常");
		}
	});
};
// 删除笔记
function deleteNote() {
	// 获取请求参数
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 发送ajax请求
	$.ajax({
		url : path + "/note/deletenote.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				//将编程笔记区域置空
				$("#input_note_title").val("");
				um.setContent("");
				// 删除li
				$li.remove();
				// 提示成功
				alert(result.msg);
			}
		},
		error : function() {
			alert("删除笔记异常");
		}
	});
};
// 隐藏笔记菜单
function hideNoteMenu() {
	// 隐藏所有笔记菜单
	$("#note_ul div").hide();
};
// 弹出笔记菜单
function popNoteMenu() {
	// 隐藏所有笔记菜单
	$("#note_ul div").hide();
	// 显示点击的笔记菜单
	var $menu = $(this).parent().next();
	$menu.slideDown(1000);
	// 设置点击笔记选中效果
	$("#note_ul a").removeClass("checked");
	$(this).parent().addClass("checked");
	// 阻止事件向li,body冒泡
	return false;
};
// 创建笔记操作
function addNote() {
	// 获取请求参数
	var userId = getCookie("uid");
	// 获取选中的笔记本li元素
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var noteTitle = $("#input_note").val().trim();
	// 检查格式
	var ok = true;
	if (userId == null) {
		ok = false;
		window.location.href = "log_in.html";
	}
	if (noteTitle == "") {
		ok = false;
		alert("笔记名不能为空");
	}
	// 发送ajax请求
	if (ok) {
		$.ajax({
			url : path + "/note/addnote.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookId" : bookId,
				"noteTitle" : noteTitle
			},
			dataType : "json",
			success : function(result) {
				// 创建成功
				if (result.status == 1) {
					// 关闭对话框
					closeAlertWindow();
					// 生成笔记列表li
					var noteId = result.data;// 获取服务器返回的笔记ID
					createNoteLi(noteId, noteTitle);
					// 弹出提示
					alert(result.msg);
				}
			},
			error : function() {
				alert("创建笔记异常");
			}
		})
	}
};
// "保存笔记"按钮的处理
function updateNote() {

	// 获取请求参数
	var title = $("#input_note_title").val().trim();
	var body = um.getContent();
	// 获取选中的笔记li元素
	var $li = $("#note_ul a.checked").parent();
	// 获得noteId
	var noteId = $li.data("noteId");
	// 清除上次提示信息
	$("#note_title_span").html("");
	// 发送ajax请求
	$
			.ajax({
				url : path + "/note/update.do",
				type : "post",
				data : {
					"title" : title,
					"body" : body,
					"noteId" : noteId
				},
				dataType : "json",
				success : function(result) {
					// 更新成功
					if (result.status == 1) {
						// 更新列表li中标题
						var sli = "";
						sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
						sli += title;
						sli += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
						// 将选中li元素的a内容替换
						$li.find("a").html(sli);
						// 提示成功
						alert(result.msg);
					}
					if (result.status == 0) {
						alert(result.msg);
					}

				},
				error : function() {
					alert("保存笔记失败");
				}
			})
};
// 根据笔记ID加载笔记信息
function loadNote() {
	// 切换显示
	$("#pc_part_3").show();
	$("#pc_part_5").hide();
	// 设置笔记选中状态
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 获得请求参数
	var noteId = $(this).data("noteId");
	// 发送ajax请求
	$.ajax({
		url : path + "/note/load.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				// 获取笔记标题
				var title = result.data.cn_note_title;
				// 获得笔记内容
				var body = result.data.cn_note_body;
				// 设置到编辑区域
				$("#input_note_title").val(title);
				// 如果笔记内容为空，清空编辑区域
				if (body == "" || body == null) {
					um.setContent("");
				}
				um.setContent(body);
			}
		},
		error : function() {
			alert("加载笔记异常");
		}
	})
};
// 根据笔记本ID加载笔记列表
function loadBookNotes() {
	// 切换列表显示
	$("#pc_part_2").show();
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_8").hide();
	// 设置笔记本li选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 获取请求参数
	var bookId = $(this).data("bookId");
	// 发送ajax请求
	$.ajax({
		url : path + "/note/loadnotes.do",
		data : {
			"bookId" : bookId
		},
		type : "post",
		datatype : "json",
		success : function(result) {
			if (result.status == 0) {
				// 清空原有笔记列表
				$("#note_ul li").remove();
				// 获得笔记本的信息
				var notes = result.data;
				// 循环生成笔记li元素
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记ID和笔记标题
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					// 创建一个笔记li元素
					createNoteLi(noteId, noteTitle);
				}

			}
		},
		error : function() {
			alert("加载笔记列表异常");
		}
	})
};

// 创建笔记列表li元素
function createNoteLi(noteId, noteTitle) {
	var sli = "";
	sli += '<li class="online">';
	sli += '	<a>';
	sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += noteTitle;
	// sli+='<i class="fa fa-sitemap"></i>';
	sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli += '	</a>';
	sli += '	<div class="note_menu" tabindex="-1">';
	sli += '		<dl>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli += '		</dl>';
	sli += '	</div>';
	sli += '</li>';
	// 将noteId绑定到li元素上
	var $li = $(sli);
	$li.data("noteId", noteId);
	// 将li元素添加到笔记列表ul中
	$("#note_ul").append($li);
};

