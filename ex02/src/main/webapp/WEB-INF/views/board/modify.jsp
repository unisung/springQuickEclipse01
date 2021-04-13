<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
 <%@include file="../includes/header.jsp" %>
 
   <div class="row">
       <div class="col-lg-12">
       		<h1 class="page-header">Board Modify</h1>
       </div>
   </div>
                  
   <div class="row">
   		<div class="col-lg-12">
   			<div  class="panel panel-default">
   				
   				 <div class="panel-heading">Board Register</div><!--  ./panel-heading -->
   				 <div class="panel-body">
   				 		
   				 	<form action="/board/modify" method="post" role="form">
   				 	<!-- pageNum, amount 추가 -->
   				 	<input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
   				 	<input type="hidden" name="amount" value="<c:out value="${cri.amount}"/>">
   				 	<input type="hidden" name="type" value="<c:out value="${cri.type}"/>">
                   <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
                   
                   
   				 		   <div class="form-group">
   				 		  		<label>Bno</label>
   				 		  		<input  class="form-control form-control-user" name="bno" 
   				 		  		              value='<c:out value="${board.bno}"/>' readonly="readonly">
   				 		  </div>
   				 		  
   				 		  <div class="form-group">
   				 		  		<label>Title</label>
   				 		  		<input  class="form-control form-control-user" name="title"
   				 		  		    value='<c:out value="${board.title}"/>' >
   				 		  </div>
   				 		  
   				 		  <div class="form-group">
   				 		  		<label>Text area</label>
   				 		  		<textarea rows="3" name="content"  
   				 		  		  class="form-control form-control-user"><c:out value="${board.content}"/></textarea>
   				 		  </div>
   				 		  
   				 		   <div class="form-group">
   				 		  		<label>Writer</label><input  class="form-control form-control-user" name="writer"
   				 		  		value='<c:out value="${board.writer}"/>' readonly="readonly" >
   				 		  </div>
   				 		<div class="form-group">
					  	<label>RegDate</label> 
					  	<input class="form-control" name='regDate'
					    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'  readonly="readonly">            
						</div>
					
					<div class="form-group">
					  <label>Update Date</label> 
					  <input class="form-control" name='updateDate'
					    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'  readonly="readonly">            
					</div>
   				 		
   				 		<button data-oper='modify' class="btn btn-default">Modify</button>
   				 		<button data-oper='remove' class="btn btn-danger">Remove</button>
						<button data-oper='list' class="btn btn-info">List</button>
   				 		
   				</form>
   				 		<!-- </form> -->
   				 		

   				 	
   				 		
   				 </div><!--  end panel body -->
   			</div><!-- end panel  -->
   		
   		</div>
   </div>       <!-- ./row -->        
                  
 <script>
$(document).ready(function(){
	var formObj = $("form");//form객체 얻기
	
	$('button').on("click",function(e){
		e.preventDefault();//기본동작 방지
		var operation = $(this).data("oper");// this는 클릭한 버튼 정보
		
		console.log(operation);//로그 출력
		
		if(operation=='remove'){
			formObj.attr("action","/board/remove");//삭제 폼으로 이동처리
		}else if(operation=='list'){
			formObj.attr("action","/board/list").attr("method","get");// 게시글 목록으로 이동처리
			
			formObj.empty();//form내용 비우기
			
		}
		
		formObj.submit();//이동처리
		
	});
});
 </script>                 
                    

<%@include file="../includes/footer.jsp" %>