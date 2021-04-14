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
   
  <%-- 댓글 리스트 영역 --%>
  <div class='row'>
  <div class="col-lg-11">
    <!-- /.panel -->
<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
        <button id='addReplyBtn' class='btn btn-primary btn-sm float-right'>New Reply</button>
     </div>         
     <div style="clear:both;"></div>
      <!-- /.panel-heading -->
      <div class="panel-body">        
        <ul class="chat">
	        <!-- start relpy -->
	        <li class="left clearfix" data-rno='12'>
	        <div class="header">
	        	<strong class="primary-font">user00</strong>
	        	<small class="pull-right text-muted">2021-04-14 15:45</small>
	        </div>
	        	<p>Good job!</p>
	        </li>
	        <!-- end reply -->
        </ul>
        <!-- ./ end ul -->
      </div>
      <!-- /.panel .chat-panel -->
	<div class="panel-footer"></div>
		</div>
  </div>
  <!-- ./ end row -->
</div> 
 
<!-- Modal -->
<!-- The Modal -->
  <div class="modal" id="myModal" tabindex="-1" role="dialog"
          aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="form-group">
             <label>Reply</label>
             <input class="form-control" name="reply" value="New Reply!!!">
          </div>
          <div class="form-group">
             <label>Replyer</label>
             <input class="form-control" name="replyer" value="replyer">
          </div>
          <div class="form-group">
             <label>Reply Date</label>
             <input class="form-control" name="replyDate" value="2021-04-14 17:36">
          </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" id="modalModBtn" class="btn btn-warning">Modify</button>
          <button type="button" id="modalRemoveBtn" class="btn btn-danger">Remove</button>
          <button type="button" id="modalRegisterBtn" class="btn btn-primary">Register</button>
          <button type="button" id="modalCloseBtn" class="btn btn-default">Close</button>
        </div>
        
      </div>
    </div>
  </div>

<!-- /.Modal -->   

 <!-- reply.js 모듈 추가 -->
 <script type="text/javascript" src="/resources/js/reply.js"></script> 
 <!-- reply.js 모듈의 replyService객체 생성 --> 
<script>
	$(document).ready(function(){
		console.log(replyService);
		 var bnoValue = '<c:out value="${board.bno}"/>';
		 var replyUL =$(".chat");
		
		showList(1);//댓글리스트 출력 메소드 호출 1페이지로 설정
		
		function showList(page){
			replyService.getList({bno:bnoValue, page:page||1}, function(list){
				var str="";//innerHTML생성 변수
				
				//댓글내역이 없으면 리턴 
				if(list ==null || list.length == 0){
					replyUL.html("");
					return;
				}
				
				//댓글내역이 있으면.
				for(var i=0, len = list.length||0;  i < len;  i++){
					   str +="<li class= 'card mb-4 py-3 border-bottom-secondary'  data-rno='"+list[i].rno+"'>";
					   str +="	<div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
					   str +="<small class='float-right text-muted'>"+  replyService.displayTime(list[i].replyDate)+"</small></div>";
					   str +="	<p>"+list[i].reply+"</p></li>";
				 }
				
				replyUL.html(str);//삽입.
			 });
		}//end showList
	
		
	//댓글 등록 모달창 열기 
	var modal = $("#myModal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click",function(){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id !='modalCloseBtn']").hide();//close버튼 만 visible
		
		modalRegisterBtn.show();//버튼 visible
		
		$(".modal").modal("show");
		
	});
	
		
	});
	
 console.log("============");
 console.log("JS  TEST");
 

 
 //for replyService add test
 /* replyService.add(
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
 
  */

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