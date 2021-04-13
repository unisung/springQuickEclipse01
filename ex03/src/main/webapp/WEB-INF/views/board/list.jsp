<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
 <%@include file="../includes/header.jsp" %>
 
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                    <p class="mb-4"><!-- DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation --></a>.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div class="panel-heading">Board List page
                            	<button type="button" id='regBtn' class="btn btn-xs pull-right">Register</button>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>#번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>수정일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                     <c:forEach var="board" items="${list}">
                                        <tr>
                                            <td><c:out value="${board.bno}"/></td>
                                            <%-- <td><a href='/board/get?bno=<c:out value="${board.bno}"/>&pageNum=<c:out value="${pageMaker.cri.pageNum}"/>&amount=<c:out value="${pageMaker.cri.amount}"/>'><c:out value="${board.title}"/></a></td> --%>
                                            <td><a class="move" href='<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td>
                                            
                                            <td><c:out value="${board.writer}"/></td>
                                            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
                                            <td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd"/></td>
                                        </tr>
                                       </c:forEach> 
                                    </tbody>
                                </table>
                
                
        <div class="row">
	        <div class="col-lg-3">
	        
	        <form id='searchForm' action='/board/list' method='get'>
		        <select name="type" class="custom-select custom-select-sm form-control form-control-sm">
		        <option value="" <c:out value="${pageMaker.cri.type==null?'selected':'' }"/>>--</option>
		        <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':'' }"/> >제목</option>
		        <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':'' }"/>>내용</option>
		        <option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':'' }"/>>작성자</option>
		        <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':'' }"/>>제목 or 내용</option>
		        <option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'?'selected':'' }"/>>제목 or 작성자</option>
		        <option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':'' }"/>>제목 or 내용 or 작성자</option>
		        </select>
		         
		     <input type="text" name="keyword" value="<c:out value="${pageMaker.cri.keyword}"/>">
		     <input type="hidden" name='pageNum' value="${pageMaker.cri.pageNum}">
		     <input type="hidden" name='amount' value="${pageMaker.cri.amount}">
		     
		     <button class="btn btn-default">Search</button>
		     </form>
	        </div>

        </div>        
                
                
                
                
                                
             <!--  start Pagination -->                   
              <div class="row">
              <div class="col-sm-12 col-md-5">
              <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
                   Showing ${pageMaker.startPage} to ${pageMaker.endPage} of ${pageMaker.total} entries</div>
              </div>
              <div class="col-sm-12 col-md-7">
              <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
              <ul class="pagination">

							<li class="paginate_button page-item previous ${pageMaker.prev?"enabled":"disabled"} " id="dataTable_previous">
							<a href="${pageMaker.startPage -1}" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li>
				
                                   
                     <c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button   page-item ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${num}</a>
							</li>
						</c:forEach>

                    
							<li class="paginate_button page-item next ${pageMaker.next?"eabled":"disabled"}" id="dataTable_next"><a
								href="${pageMaker.endPage +1 }" data-dt-idx="7" tabindex="0" class="page-link">Next</a></li>
					
						 
                     </ul>
                     </div>
                     </div>
                     </div>
				<!--  end Pagination -->
                                
                  <form  id="actionForm" action="/board/list" method="get">
                   <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                   <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                  <!--   <input type='hidden' name='bno' value='"+$(this).attr("href") + "'> -->
                   <input type="hidden" name="type" value="<c:out value="${pageMaker.cri.type}"/>">
                   <input type="hidden" name="keyword" value="<c:out value="${pageMaker.cri.keyword}"/>">
                  </form>              
                                
                            </div>
                        </div>
                    </div>


<script>
$(document).ready(function(){
	var result = '<c:out value="${result }"/>';
	//alert(result);
	/* 모달 호출 */
	checkModal(result);
	
	history.replaceState({},null,null);
	
	/* 모달함수 선언 */
	function checkModal(result){
		if(result =='' || history.state ){return;}
		
		if(parseInt(result)>0){
			$("#exampleModalLabel").html("게시글 등록 결과!");
			$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
		}
		$("#logoutModal").modal("show");
	}
	
	/* 글 등록 버튼 스크립트 처리 */
	$("#regBtn").on("click",function(){
		self.location="/board/register";
	});
	
	//페이지번호 클릭 이벤트 처리
	var actionForm = $("#actionForm");
	$(".paginate_button a").on("click",function(e){
		e.preventDefault();
		
		console.log('click');
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
	//게시글 상세조회 처리
	$('.move').on("click",function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href") + "'>");//form에 bno태그 추가
		actionForm.attr("action","/board/get");//action경로 변경
		actionForm.submit();//전송처리 subit();
	});
	
	//검색버튼 클릭 처리
	var searchForm=$('#searchForm');
	$('#searchForm button').on("click",function(e){
		if(!searchForm.find("option:selected").val()){
			$("#exampleModalLabel").html("검색종류 선택");
			$(".modal-body").html("검색 종류를 선택하세요");
		    $("#logoutModal").modal("show");
			
			return false;
		}
		
		if(!searchForm.find("input[name='keyword']").val()){
			$("#exampleModalLabel").html("검색");
			$(".modal-body").html("키워드를 입력하세요");
		    $("#logoutModal").modal("show");
			return false;
		}
		
		searchForm.find("input[name='pageNum']").val("1");//검색시 1페이지로 설정
		e.preventDefault();
		
		searchForm.submit();
	});
	
});
</script>

<%@include file="../includes/footer.jsp" %>