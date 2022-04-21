function update() {
   var proNo = $('#proNo').val();
   var proName = $('#proName').val();
   var proLimit = isNaN(parseFloat($('#proLimit').val().replace(/,/g,''))) ? "" : parseFloat($('#proLimit').val().replace(/,/g,''));
   var detail = $('#detail').val();
   var need = $('#need').val();
   var rate = isNaN(parseFloat($('#rate').val())) ? "" : parseFloat($('#rate').val());
   var term = isNaN(parseInt($('#term').val())) ? "" : parseInt($('#term').val());
   
   
   if(proName === "" ){
      alert("상품명을 입력하세요.");
      return false;
   }else if(proLimit === "" ){
      alert("대출 한도(만원)를 입력하세요.");
      return false;
   }else if(rate === ""){
      alert("이자율(%)을 입력하세요.");
      return false;
   }else if(term === ""){
      alert("대출 최대기간(개월)을 입력하세요.");
      return false;
   }else if(need === ""){
      alert("신청자격을 입력하세요.");
      return false;
   }else if(detail === ""){
      alert("상품설명을 입력하세요.");
   return false;
   }
   

   var arr = {
      "proNo": proNo,
      "proName": proName,
      "detail": detail,
      "need": need,
      "proLimit": proLimit,
      "rate": rate,
      "term": term
   };

   console.log(arr);

   $.ajax({
      anyne : true,
      type: 'POST',
      url: '/product/modify',
      data: JSON.stringify(arr),
      contentType: 'application/json; charset=utf-8;',
      dataType: "text",      
      success: function(data) {
         alert("상품이 수정되었습니다.");
         location.href = "/product/list";
      },
      error: function(request, status, error) {
         alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
      }
   });
}

function register() {
   var proName = $('#proName').val();
   var proLimit = isNaN(parseFloat($('#proLimit').val().replace(/,/g,''))) ? "" : parseFloat($('#proLimit').val().replace(/,/g,''));
   var detail = $('#detail').val();
   var need = $('#need').val();
   var rate = isNaN(parseFloat($('#rate').val())) ? "" : parseFloat($('#rate').val());
   var term = isNaN(parseInt($('#term').val())) ? "" : parseInt($('#term').val());
   
   
   if(proName === "" ){
      alert("상품명을 입력하세요.");
      return false;
   }else if(proLimit === "" ){
      alert("대출 한도(만원)를 입력하세요.");
      return false;
   }else if(rate === ""){
      alert("이자율(%)을 입력하세요.");
      return false;
   }else if(term === ""){
      alert("대출 최대기간(개월)을 입력하세요.");
      return false;
   }else if(need === ""){
      alert("신청자격을 입력하세요.");
      return false;
   }else if(detail === ""){
      alert("상품설명을 입력하세요.");
   return false;
   }
   
   var arr = {
      "proName": proName,
      "detail": detail,
      "need": need,
      "proLimit": proLimit,
      "rate": rate,
      "term": term
   };

   console.log(arr);
   $.ajax({
      type: 'POST',
      url: '/product/register',
      data: JSON.stringify(arr),
      contentType: 'application/json; charset=utf-8;',
      dataType: 'text',
      success: function(data) {
         alert("상품이 등록되었습니다. 메인화면으로 돌아갑니다.");
         location.href = "/product/list";
      },
      error: function(request, status, error) {
         alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
      }
   });

}

function back() {
   location.href="/product/list";
}