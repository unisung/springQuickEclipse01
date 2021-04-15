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
		  $("#uploadBtn").on("click",function(e){
			 var formData = new FormData(); /// <form>  요소 동적 생성
			 var inputFile = $("input[name='uploadFile']");
			 
			 var files = inputFile[0].files;
			 
			 console.log(files);
			 
		  });
	  });
	</script>
</body>
</html>