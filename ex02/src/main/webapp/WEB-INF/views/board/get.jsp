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
   				 	</form>
   				 	
   				 		
   				 </div><!--  end panel body -->
   			</div><!-- end panel  -->
   		
   		</div>
   </div>       <!-- ./row -->        
                  
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