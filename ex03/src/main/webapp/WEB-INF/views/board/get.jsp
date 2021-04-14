<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
 <%@include file="../includes/header.jsp" %>
 
   <div class="row">
       <div class="col-lg-12">
       		<h1 class="page-header">Board register</h1>
       </div>
   </div>
                  
   <div class="row">
   		<div class="col-lg-12">
   			<div  class="panel panel-default">
   				
   				 <div class="panel-heading">Board Register</div><!--  ./panel-heading -->
   				 <div class="panel-body">
   				 		
   				 		<!-- <form action="/board/register" method="post" role="form"> -->
   				 		   <div class="form-group">
   				 		  		<label>Bno</label>
   				 		  		<input  class="form-control form-control-user" name="bno" 
   				 		  		              value='<c:out value="${board.bno}"/>' readonly="readonly">
   				 		  </div>
   				 		  
   				 		  <div class="form-group">
   				 		  		<label>Title</label>
   				 		  		<input  class="form-control form-control-user" name="title"
   				 		  		    value='<c:out value="${board.title}"/>' readonly="readonly" >
   				 		  </div>
   				 		  
   				 		  <div class="form-group">
   				 		  		<label>Text area</label>
   				 		  		<textarea rows="3" name="content"  
   				 		  		  class="form-control form-control-user" readonly="readonly"><c:out value="${board.content}"/></textarea>
   				 		  </div>
   				 		  
   				 		   <div class="form-group">
   				 		  		<label>Writer</label><input  class="form-control form-control-user" name="writer"
   				 		  		value='<c:out value="${board.writer}"/>' readonly="readonly" >
   				 		  </div>
   				 		
   				 		<button data-oper='modify' class="btn btn-default">Modify</button>
						<button data-oper='list' class="btn btn-info">List</button>
   				 		<!-- </form> -->
   				 		
   				 	<form id='operForm' action="/board/modify" method="get">
   				 		<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>  
   				 		<input type='hidden' id='pageNum' name='pageNum' value='<c:out value="${cri.pageNum}"/>'> 
   				 		<input type='hidden' id='amount' name='amount' value='<c:out value="${cri.amount}"/>'>  				 	
   				 	
	   				 	<input type="hidden" name="type" value="<c:out value="${cri.type}"/>">
	                   <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>">
   				 	</form>
   				 	
   				 		
   				 </div><!--  end panel body -->
   			</div><!-- end panel  -->
   		
   		</div>
   </div>       <!-- ./row -->        
                  
 <!-- reply.js 모듈 추가 -->
 <script type="text/javascript" src="/resources/js/reply.js"></script> 
 <!-- reply.js 모듈의 replyService객체 생성 --> 
<script>
	$(document).ready(function(){
		console.log(replyService);
	});
	
 console.log("============");
 console.log("JS  TEST");
 
 var bnoValue = '<c:out value="${board.bno}"/>';
 
 //for replyService add test
 replyService.add(
	{reply:"JS TEST",replyer:"tester", bno:bnoValue}		,
	function(result){alert("RESULT: "+result);}
 ); 
 
 replyService.getList({bno:bnoValue, page:1}, function(list){
	 for(var i=0, len = list.length||0;  i < len;  i++){
		   console.log(list[i]);
	 }
 });
 
 // 37번 글 삭제
 replyService.remove(37, function(count){
	 console.log(count);
	 if(count==="success"){
		 alert('REMOVED');
	 }
 },function(err){alert('ERROR....');});
 

 // 25번 댓글 수정
 replyService.update({rno:25, bno:bnoValue, reply:"Modified Reply...."},
		                       function(result){alert("수정완료....");}		 
                             );

 // 25댓글 조회
 replyService.get(25, function(data){console.log(data);});
 
 
 </script>                
                
 <script>
 var operForm=$("#operForm");
 $("button[data-oper='modify']").on('click',function(e){
	operForm.attr("action","/board/modify").submit();//submit처리
 })
 
 $("button[data-oper='list' ]").on("click",function(e){
	 operForm.find("#bno").remove();/* operForm내의 요수중 id가 bno인 요소 제거  */
	 operForm.attr("action","/board/list");
	 operForm.submit();//submit처리
 })
 
 </script>                 
                    

<%@include file="../includes/footer.jsp" %>