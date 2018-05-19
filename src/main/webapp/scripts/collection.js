/**
 * 收藏笔记的js脚本
 */
//将分享笔记加入收藏
function collectNote(){
	//获得请求参数
	var userId=getCookie("uid");
	var $li=$(this).parents("li");
	var shareId=$li.data("shareId");
	//发送ajax请求
	$.ajax({
		url:path+"/note/collectnote.do",
		type:"post",
		data:{"userId":userId,
			"shareId":shareId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}
			if(result.status==2){
				alert(result.msg);
			}
		},
		error:function(){
			alert("收藏笔记异常");
		}
	});
}

//查看收藏
function searchCollection(){
	//切换列表
	$("#pc_part_7").show();
	$("#pc_part_2").hide();
	$("#pc_part_6").hide();
	$("#pc_part_4").hide();
	$("#pc_part_8").hide();
	//获得请求参数
	var userId=getCookie("uid");
	//发送ajax请求
	$.ajax({
		url:path+"/note/searchcollection.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				// 清除原有列表结果
				$("#pc_part_7 ul").empty();
				// 获取服务器返回的收藏结果
				var notes=result.data;
				for(var i=0;i<notes.length;i++){
					//获得收藏id
					var collectionId=notes[i].cn_collection_id;
					//获得收藏笔记标题
					var title=notes[i].cn_share_title;
					// 生成一个li
					var sli='';
					sli+='<li class="idle">';
					sli+='<a>';
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=title;
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_delete" title="删除"><i class="fa fa-times"></i></button>';
					sli+='</a></li>';
					var $li = $(sli);
					$li.data("collectionId", collectionId);
					// 添加到搜索结果ul中
					$("#pc_part_7 ul").append($li);
				}
				
			}
		},
		error:function(){
			alert("查看收藏异常");
		}
	})
}

//查看收藏笔记的内容
function loadCollectionNote(){
	// 切换显示
	$("#pc_part_3").hide();
	$("#pc_part_5").show();
	// 设置笔记选中状态
	$("#pc_part_7 ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获得请求参数
	var collectionId=$(this).data("collectionId");
	//发送ajax请求
	$.ajax({
		url:path+"/note/loadcollection.do",
		type:"post",
		data:{"collectionId":collectionId},
		dataType:"json",
		success:function(result){
			if (result.status == 0) {
				// 获取分享标题
				var title = result.data.cn_share_title;
				// 获取分享内容
				var body = result.data.cn_share_body;
				// 设置标题和内容
				$("#noput_note_title").html(title);
				$("#noput_note_title").next().html(body);
			}

		},
		error:function(){
			alert("查看笔记异常");
		}
	});
}
//删除收藏的笔记
function deleteCollection(){
	//获取请求参数 
	var $li = $("#pc_part_7 ul a.checked").parent();
	var collectionId=$li.data("collectionId");
	//发送ajax请求
	$.ajax({
		url:path+"/note/deletecollection.do",
		type:"post",
		data:{"collectionId":collectionId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$li.remove();
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除收藏异常");
		}
	});
}