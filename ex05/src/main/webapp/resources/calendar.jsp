<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
<script>
function writeSchedule(date){
	alert(date);
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
    var modalInputReplyer = modal.find("input[name='replyer']");
    var modalInputReplyDate = modal.find("input[name='replyDate']");
    
    var modalModBtn = $("#modalModBtn");
    var modalRemoveBtn = $("#modalRemoveBtn");
    var modalRegisterBtn = $("#modalRegisterBtn");
    
    modal.find("input").val("");
    modalInputReplyDate.closest("div").hide();
    modal.find("button[id !='modalCloseBtn']").hide();
    
    modalRegisterBtn.show();
    
    $(".modal").modal("show");
    
  /*   $("#modalCloseBtn").on("click", function(e){
    	
    	modal.modal('hide');
    });
    
    $("#addReplyBtn").on("click", function(e){
      
      modal.find("input").val("");
      modalInputReplyDate.closest("div").hide();
      modal.find("button[id !='modalCloseBtn']").hide();
      
      modalRegisterBtn.show();
      
      $(".modal").modal("show");
      
    });
    

    modalRegisterBtn.on("click",function(e){
      
      var reply = {
            reply: modalInputReply.val(),
            replyer:modalInputReplyer.val(),
            bno:bnoValue
          };
      replyService.add(reply, function(result){
        
        alert(result);
        
        modal.find("input").val("");
        modal.modal("hide");
        
        //showList(1);
        showList(-1);
        
      });
      
    }); */
}
</script>    
<%
    Calendar cal = Calendar.getInstance();
    int year = request.getParameter("y") == null ? cal.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("y"));
    int month = request.getParameter("m") == null ? cal.get(Calendar.MONTH) : (Integer.parseInt(request.getParameter("m")) - 1);

    // ???????????? ??????
    // - Calendar MONTH??? 0-11?????????
    cal.set(year, month, 1);
    int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);

    // ??????/????????? ??????
    // - MONTH ????????? ???????????? ???????????? ????????? +1??? ??? ???????????? ?????????
    int prevYear = year;
    int prevMonth = (month + 1) - 1;
    int nextYear = year;
    int nextMonth = (month  + 1) + 1;

    // 1?????? ?????? ????????? 12?????? ??????
    if (prevMonth < 1) {
        prevYear--;
        prevMonth = 12;
    }

    // 12?????? ?????? ????????? 1?????? ??????
    if (nextMonth > 12) {
        nextYear++;
        nextMonth = 1;
    }
%>
<div class="container">
<table class="table">
<tr>
    <td align="center"><a href="./calendar.jsp?y=<%=prevYear%>&m=<%=prevMonth%>">???</a> <%=year%>??? <%=month+1%>??? <a href="./calendar.jsp?y=<%=nextYear%>&m=<%=nextMonth%>">???</a></td>
</tr>
<tr>
    <td>

        <table class="table table-striped" border="1">
        <tr>
            <td class="col-md-6">???</td>
            <td>???</td>
            <td>???</td>
            <td>???</td>
            <td>???</td>
            <td>???</td>
            <td>???</td>
        </tr>
        <tr>
<%
    // ?????????????????? ??????
    for (int i=1; i<bgnWeek; i++) out.println("<td>&nbsp;</td>");

    // ??????~?????????????????? ??????
    // - ????????? ????????? ???????????? ?????? ??????????????? ?????????
    while (cal.get(Calendar.MONTH) == month) {
        out.println("<td><a href=javascript:writeSchedule('"+year+"-"+month+"-"+cal.get(Calendar.DATE)+"')>" + cal.get(Calendar.DATE) + "</a></td>");

        // ???????????? ?????? ???????????? ??????
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) out.println("</tr><tr>");

        // ?????? ???????????????
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
    }

    // ???????????? ??????????????? ???????????? ??????
    for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++) out.println("<td>&nbsp;</td>");
%>
        </tr>
        </table>

    </td>
</tr>
</table>
</div>
<!-- ?????? ?????????  Modal -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">&times;</button>
              <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label>Reply</label> 
                <input class="form-control" name='reply' value='New Reply!!!!'>
              </div>      
              <div class="form-group">
                <label>Replyer</label> 
                <input class="form-control" name='replyer' value='replyer'>
              </div>
              <div class="form-group">
                <label>Reply Date</label> 
                <input class="form-control" name='replyDate' value='2018-01-01 13:13'>
              </div>
      
            </div>
      <div class="modal-footer">
        <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
        <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
        <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
        <button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
      </div>          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->
      