/** book.js 封装笔记本相关处理 **/
//重命名笔记
function renameNoteBook(){
	//设置笔记本选中状态
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取请求参数
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var bookName=$("#input_notebook_rename").val().trim();
	//发送ajax请求
	$.ajax({
		url:path+"/book/renamebook.do",
		type:"post",
		data:{"bookId":bookId,
			"bookName":bookName},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				// 更新列表li中标题
				var sli = "";
				sli+='    <i class="fa fa-book" title="online" rel="tooltip-bottom">';
				sli+='    </i>'+bookName;
				// 将选中li元素的a内容替换
				$li.find("a").html(sli);
				alert(result.msg);
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("修改笔记本名字异常");
		}
	});
}

//删除笔记本
function deleteBook(){
	//获取请求参数
	$li=$("#book_ul a.checked").parent();
	var bookId=$li.data("bookId");
	//发送ajax请求
	$.ajax({
		url:path+"/book/deletebook.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//清空删除笔记的内容笔记
				$("#note_ul").empty();
				$li.remove();
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除笔记本异常");
		}
	});
}
//确定创建笔记本
function addBook(){
	//获取请求参数
	var name=$("#input_notebook").val().trim();
	var userId=getCookie("uid");
	//格式检查
	var ok=true;
	if(name==""){
		ok=false;
		alert("笔记本名字不能为空");
	}
	if(userId==null){
		ok = false;
		 window.location.href="log_in.html";
	}
	//发送ajax请求
	if(ok){
		$.ajax({
			url:path+"/book/addbook.do",
			type:"post",
			data:{
				"name":name,
				"userId":userId
			},
			dataType:"json",
			success:function(result){
				//创建成功
				if(result.status==1){
					 //关闭对话框
					 closeAlertWindow();
					//生成笔记本li元素
					 var bookId = 
						result.data.cn_notebook_id;
					 var bookName = 
						 result.data.cn_notebook_name;
					 createBookLi(bookId,bookName);
					 //提示成功
					 alert(result.msg);
				}
			},
			error:function(){
				alert("创建笔记本异常");
			}
		});
	}
 };
//加载用户笔记本列表
function loadUserBooks(){
	//获取请求参数
	var userId = getCookie("uid");
    //检查格式
    if(userId==null){
   	 window.location.href="log_in.html";
    }else{
   	//发送ajax请求
   	$.ajax({
   		url:path+"/book/loadbooks.do",
   		type:"post",
   		data:{"userId":userId},
   		dataType:"json",
   		success:function(result){
   			if(result.status==0){
   				//获取返回的笔记本集合
   				var books = result.data;
   				//循环生成列表元素
   				for(var i=0;i<books.length;i++){
   					//获取笔记本ID
   					var bookId = books[i].cn_notebook_id;
   					//获取笔记本名称
   					var bookName = books[i].cn_notebook_name;
   					//创建笔记本列表li
   					createBookLi(bookId,bookName);
   				}
   			}
   		},
   		error:function(){
   			alert("加载笔记本列表异常");
   		}
   	});
    }
};

//创建笔记本列表li元素
function createBookLi(bookId,bookName){
	//构建列表li元素
	var sli = "";
	sli+='<li class="online">';
	sli+='  <a>';
	sli+='    <i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='    </i>'+bookName;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_delete" title="删除">';
	sli+='<i class="fa fa-times"></i>';		
	sli+='</button>';
	sli+='  </a>';
	sli+='</li>';
	//将sli字符串转成jQuery对象li元素
	var $li = $(sli);
	//将bookId值与jQuery对象绑定
	$li.data("bookId",bookId);
	//将li元素添加到ul列表中
	$("#book_ul").append($li);
};

