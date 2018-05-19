/**
 *回收站js脚本
 */
//查询回收站笔记
function searchDelete(){
	
	// 切换列表
	$("#pc_part_4").show();
	$("#pc_part_2").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_8").hide();
	//获取请求信息
	var userId=getCookie("uid");
	//发送ajax请求
	$.ajax({
		url:path+"/note/searchdelete.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//清空原有列表
				$("#pc_part_4 ul").empty();
				// 获得笔记本的信息
				var notes = result.data;
				// 循环生成笔记li元素
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记ID和笔记标题
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					// 创建一个笔记li元素
					var sli="";
					sli+='<li class="disable">';
					sli+='<a>';
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=noteTitle;
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_delete" title="删除">';	
					sli+='<i class="fa fa-times"></i>';		
					sli+='</button>';		
					sli+='<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay" title="复原">';	
					sli+='<i class="fa fa-reply"></i>';	
					sli+='</button></a></li>';
					var $li = $(sli);
					$li.data("noteId", noteId);
					// 将li元素添加到笔记列表ul中
					$("#pc_part_4 ul").append($li);	
				}
			}
		},
		error:function(){
			alert("查看回收站异常");
		}
	});
};

//彻底删除笔记
function deleteForever(){
	//获得请求参数
	var $li = $("#pc_part_4 ul a.checked").parent();
	var noteId = $li.data("noteId");
	//发送ajax请求
	$.ajax({
		url:path+"/note/delete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除笔记异常");
		}
	});
}

//根据笔记ID加载回收站笔记信息
function loadDeleteNote() {
	// 切换显示
	$("#pc_part_3").show();
	$("#pc_part_5").hide();
	// 设置笔记选中状态
	$("#pc_part_4 ul a").removeClass("checked");
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

//复原回收站中的笔记
function rollbackNote(){
	//获得请求参数
	var $li = $("#pc_part_4 ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 获取要转入的笔记本ID
	var bookId = $("#replaySelect").val().trim();
	//发送ajax请求
	$.ajax({
		url:path+"/note/rollbacknote.do",
		type:"post",
		data:{"noteId":noteId,
			"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$li.remove();
				alert(result.msg);
			}
		},
		error:function(){
			alert("复原笔记异常");
		}
	});
}