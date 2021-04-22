<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload with Ajax</title>
<style>
.uploadResult{ width: 100%; background-color: gray; }
.uploadResult ul{ display: flex, flex-flow:row; justify-content: center; align-items: center;}
.uploadResult ul li{list-style: none; padding: 10px;}
.uploadResult ul li img{width: 20px;}
</style>
<style>
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
}

.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
</style>

</head>
<body>
	
	 <h1>Upload with Ajax</h1>
	 
	<div class="uploadDiv">
		<input type="file" name='uploadFile' multiple>
	</div>
	
	<!-- 전송결과 리스트 출력 부분  -->
	<div class='uploadResult'>
	  <ul></ul>
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<!--  원본 이미지 출력 부분  -->
	<div class ="bigPictureWrapper">
	       <div class="bigPicture"></div>
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	
	function showImage(fileCallPath){
		//alert("fileCallPath");
		$(".bigPictureWrapper").css("display","flex").show();
		
		  $(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
		     .animate({width:'100%', height: '100%'}, 1000);
	}
	

	$(".bigPictureWrapper").on("click", function(e){
	  $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
	  //setTimeout( () => {   $(this).hide(); }, 1000);//람다식
	  setTimeout(function(){$('.bigPictureWrapper').hide()},1000);
	});
	
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
			 

			
		//업로드 리스트 출력 함수
		var uploadResult = $(".uploadResult ul");
		function showUploadedFile(uploadResultArr){
			var str="";
			
			$(uploadResultArr).each(function(i, obj){
				
				if(!obj.image){//이미지가 아닌 경우
					
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
				
					 console.log("not Image:", fileCallPath);
					str +="<li><div><a href='/download?fileName="+fileCallPath 
							       +"'><img src='/resources/img/attach.png'>" + obj.fileName+"</a>"+
							       "<span data-file=\'"+fileCallPath+"\' data-type='file'>x</span></div></li>";
				}else{//이미지파일인 경우
					str += "<li>" + obj.fileName +"</li>";
					
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
				
					var originPath = obj.uploadPath +"\\" + obj.uuid + "_" +obj.fileName;
					originPath = originPath.replace(new RegExp(/\\/g),"/");// 
					    
					 console.log(fileCallPath);
					str +="<li><a href=\"javascript:showImage(\'" + originPath+"\')\"><img src='/display?fileName=" 
							               + fileCallPath+"'></a>"+
							               "<span data-file=\'"+fileCallPath+"\' data-type='image'>x</span></li>";
				}
			});
			
			uploadResult.append(str);
		}
			
		
		//파일 삭제 처리
		$(".uploadResult").on("click","span",function(e){
			 var targetFile = $(this).data("file");
			 var type=$(this).data("type");
			 console.log(targetFile);
			 
			 $.ajax({
				 url:'/deleteFile',
				 data:{fileName:targetFile, type:type},
				 dataType:'text',
				 type:'POST',
				 success:function(result){
					 alert(result);
				 }
				 });
		});
		
		  // 클래스가 uploadDiv인 <div> 요소 복제
		  var cloneObj = $(".uploadDiv").clone();
		  
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
				 dataType:'json',
				 success:function(result){
					 //alert('Uploaded');
					 console.log(result);
					 
					 //업로드 리스트 출력 함수 호출
					 showUploadedFile(result);
					 
					 //upload처리 전의 초기상태로 설정
					 $(".uploadDiv").html(cloneObj.html());// 요소.thml()-get, 요소.html(내용)-set;
				 }
			 });//$.ajax() 끝.
		  });
	  });
	  
	  
	</script>
</body>
</html>