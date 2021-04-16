<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload with Ajax</title>
</head>
<body>
	
	<div class="uploadDiv">
		<input type="file" name='uploadFile' multiple>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	  $(document).ready(function(){
		  
			//전송할 파일 사이즈 체크
			var maxSize = 5242880;//5MB
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");//파일확장자 체크
			
			//확장자 체크 함수
			function checkExtension(fileName, fileSize){
				//파일 사이즈 체크
				if(fileSize > maxSize){
					alert("파일 사이즈 초과");
					return false;
				}
				
				//확장자 체크 , 확장자가 exe, sh, zip, alz파일은 전송 금지 처리
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			 
		  
		  $("#uploadBtn").on("click",function(e){
			 var formData = new FormData(); /// <form>  요소 동적 생성
			 var inputFile = $("input[name='uploadFile']");
			 
			 var files = inputFile[0].files;
			 
			 console.log(files);
			 
			 //add filedate to formdata;
			 for(var i=0; i<files.length; i++){//files배열 길이만큼 <form></form>태그안에 생성
				//파일 확장자, 사이즈 체크
				if(!checkExtension(files[i].name, files[i].size)){
					  return false;
				} 
				//파일 확장자, 사이즈 체크 통과시 form에 추가 
				 formData.append("uploadFile",files[i]);//<form><input type="file" name='uploadFile' multiple>
				                                                       //           <input type="file" name='uploadFile' multiple>
				                                                       //</form>
			 }

			 //ajax로 전송처리
			 $.ajax({
				 url:'/uploadAjaxAction',
				 processData:false,
				 contentType:false,
				 data:formData,
				 type:'POST',
				 success:function(result){
					 alert('Uploaded');
				 }
			 });//$.ajax() 끝.
		  });
	  });
	  
	  
	</script>
</body>
</html>