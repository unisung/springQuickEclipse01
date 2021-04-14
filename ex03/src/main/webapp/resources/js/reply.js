/**
 *  reply.js
 */
console.log("Reply Module.................");

var replyService =(function(){ 
	
	function add(reply, callback, error){
		console.log("add reply........................");
		
		$.ajax({
			type:'post',
			url:'/replies/new',
			data:JSON.stringify(reply),
			contentType:"application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	//댓글 리스트 조회
	function getList(param, callback, error){
		
		var bno = param.bno;
		var page =param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page ,
	        function(data) {
	          if (callback) {
	            callback(data); // 댓글 목록만 가져오는 경우 
	            //callback(data.replyCnt, data.list); //댓글 숫자와 목록을 가져오는 경우 
	          }
	        });
	  }
	
	//댓글 삭제
	function remove(rno, callback, error){
	 $.ajax({
	 		type:'delete',
	 		url:'/replies/' + rno,
	 		success : function(deleteResult, status, xhr){
	 		               if(callback){
	 		                     callback(deleteResult);
	 		                }
	 		              },
	 		 error : function(xhr, status, er){
	 		 		if(error){
	 		 		  error(er);
	 		        }
	 		 }                    
	 });
	}
	
	//댓글 수정
	function update(reply, callback, error) {
		console.log("RNO: " + reply.rno);
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	//댓글 번호로 댓글가져오기
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json", 
		        function(result){
		           if(callback){
		             callback(result);
		             }
		        }).fail(function(xhr, status, err){
		                     if(err){
		                     error();
		                     }
		        });
	}
	
	
	return {add:add,
			  getList:getList,
			  remove:remove,
			  update:update,
			  get:get
				};
				/* replyService = add(){} */ 

	})();

