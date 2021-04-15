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
                                            <td><a href='/board/get?bno=<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td>
                                            <td><c:out value="${board.writer}"/></td>
                                            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
                                            <td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd"/></td>
                                        </tr>
                                       </c:forEach> 
                                    </tbody>
                                </table>
                                
                <div class='pull-right'>
					<ul class="pagination">

						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>


					</ul>
				</div>
				<!--  end Pagination -->
                                
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
	
});
</script>

<%@include file="../includes/footer.jsp" %>