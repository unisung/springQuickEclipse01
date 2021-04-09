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
   				 		
   				 		<form action="/board/register" method="post" role="form">
   				 		  <div class="form-group">
   				 		  		<label>Title</label><input  class="form-control form-control-user" name="title">
   				 		  </div>
   				 		  
   				 		  <div class="form-group">
   				 		  		<label>Text area</label><textarea rows="3" name="content"  class="form-control form-control-user"></textarea>
   				 		  </div>
   				 		  
   				 		   <div class="form-group">
   				 		  		<label>Writer</label><input  class="form-control form-control-user" name="writer">
   				 		  </div>
   				 		
   				 		<button type="submit"class="btn btn-primary btn-icon-split">Submit</button>
   				 		<button type="reset" class="btn btn-primary btn-icon-split">Reset Button</button>
   				 		</form>
   				 </div><!--  end panel body -->
   			</div><!-- end panel  -->
   		
   		</div>
   </div>       <!-- ./row -->        
                  
                    

<%@include file="../includes/footer.jsp" %>