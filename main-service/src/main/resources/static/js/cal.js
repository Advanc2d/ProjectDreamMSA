$(function () {
    _thisPage.onload();

    $(".btn-add-money").on("click", function () {
        $(".btn-add-money").removeClass("active");
        $(this).addClass("active");
        var calcValue = calcMoney($("#calc01").val(), $(this).val());
        $("#calc01").val(calcValue);
    });

    $(".btn-reset-money").on("click", function () {
        $("#calc01").val("0원");
    });

    $("#calc01").on("keyup", function () {
        var org = $(this).val();
        var orgMoney = String(org.replace("원", "").replace(/[^0-9]/g, "").replace(/,/gi, "")).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        $(this).val(orgMoney + "원");
    });

    // 기간 입력
    $(".btn-add-month").on("click", function () {
        $(".btn-add-month").removeClass("active");
        $(this).addClass("active");
        var calcValue = calcMonth($("#calc02").val(), $(this).val());
        $("#calc02").val(calcValue);
    });

    $(".btn-reset-month").on("click", function () {
        $("#calc02").val("0개월");
    });

    $("#calc02").on("keyup", function () {
        var org = $(this).val();
        var orgMonth = String(org.replace("개월", "").replace(/[^0-9]/g, ""));
        $(this).val(orgMonth + "개월");
    });


    $("#calc03").on("keyup", function () {
        var org = $(this).val();
        var orgRate = String(org.replace("%", "").replace(/[^0-9.]/g, ""));
        $(this).val(orgRate + "%");
    });

    $("#btnCalc").on("click", function () {
        var month = $("#calc02").val();
        var rates = $("#calc03").val();

        if (Number(String(month.replace("개월", ""))) > 60) {
            alert("대출 기간은 60개월 이하로 입력해주세요.");
            return false;
        }

        if (parseFloat(String(rates.replace("%", ""))) > 20) {
            alert("이자율은 20% 이하로 입력해주세요.");
            return false;
        }

        var jexAjax = jexjs.createAjaxUtil('mobsercalc_0300');

        jexAjax.set({
            "LOAN_MTHD": $("input[name=radio1]:checked").val()
            , "LOAN_AMT": $("#calc01").val()
            , "LOAN_MONTH": $("#calc02").val()
            , "LOAN_RATE": $("#calc03").val()
            , "LOAN_YY": $("#calcYear").val()
            , "LOAN_MM": $("#calcMonth").val()
            , "LOAN_DD": $("#calcDay").val()
            , "TP": "3"
        });

        jexAjax.execute(function (dat) {
            $("#loanList").empty();

            var loanMethod = $("input[name=radio1]:checked").val();
            var list = dat.REC;
            var totDt = 0;			// 총합 일수
            var totRpyPrnc = 0;		// 상환 원금
            var totInts = 0;		// 이자
            var totPiMng = 0;		// 납입 한계
            var loanList = "";

            if (loanMethod == '1') {
                // 1. 원금균등상환
                for (var i = 0; i < list.length; i++) {
                    var ints = Math.floor(list[i].INTS);

                    loanList += '<tr>';
                    loanList += '	<td>' + (i + 1) + '</td>';
                    loanList += '	<td>' + list[i].NEXT_DATE + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].D_CNT) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].RPY_PRNC) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(ints) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].PI_MNG) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].LOAN_BLNC) + '</td>';
                    loanList += '</tr>';

                    totDt = totDt + Number(list[i].D_CNT);
                    totRpyPrnc = totRpyPrnc + Number(list[i].RPY_PRNC);
                    totInts = totInts + Number(ints);
                }

                totPiMng = totRpyPrnc + totInts;
            } else if (loanMethod == '2') {
                // 2. 원리금균등상환
                for (var i = 0; i < list.length; i++) {
                    var ints = Math.floor(list[i].INTS);

                    loanList += '<tr>';
                    loanList += '	<td>' + (i + 1) + '</td>';
                    loanList += '	<td>' + list[i].NEXT_DATE + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].D_CNT) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].RPY_PRNC) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(ints) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].PI_MNG) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].LOAN_BLNC) + '</td>';
                    loanList += '</tr>';

                    totDt = totDt + Number(list[i].D_CNT);
                    totRpyPrnc = totRpyPrnc + Number(list[i].RPY_PRNC);
                    totInts = totInts + Number(ints);
                    totPiMng = totPiMng + Number(list[i].PI_MNG);
                }
            } else if (loanMethod == '3') {
                // 3. 만기일시 상환  
                for (var i = 0; i < list.length; i++) {
                    var ints = Math.floor(list[i].INTS);

                    loanList += '<tr>';
                    loanList += '	<td>' + (i + 1) + '</td>';
                    loanList += '	<td>' + list[i].NEXT_DATE + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].D_CNT) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].RPY_PRNC) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(ints) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].PI_MNG) + '</td>';
                    loanList += '	<td>' + getNumberWithCommaInt(list[i].LOAN_BLNC) + '</td>';
                    loanList += '</tr>';

                    totDt = totDt + Number(list[i].D_CNT);
                    totRpyPrnc = totRpyPrnc + Number(list[i].RPY_PRNC);
                    totInts = totInts + Number(ints);
                }

                totPiMng = totRpyPrnc + totInts;
            } else {
                alert("잘못된 계산 요청입니다.");
            }

            loanList += '<tr>';
            loanList += '	<td><strong>합계</strong></td>';
            loanList += '	<td>-</td>';
            loanList += '	<td><strong>' + getNumberWithCommaInt(totDt) + '</strong></td>';
            loanList += '	<td><strong>' + getNumberWithCommaInt(totRpyPrnc) + '</strong></td>';	// 상환원금
            loanList += '	<td><strong>' + getNumberWithCommaInt(totInts) + '</strong></td>';		// 이자
            loanList += '	<td><strong>' + getNumberWithCommaInt(totPiMng) + '</strong></td>';		// 납입한계
            loanList += '	<td>-</td>';	// 대출잔액
            loanList += '</tr>';

            $("#loanList").append(loanList);
        });
    });
    /**
     * TODO Example Event 이며, 구현시 삭제바랍니다.
     * jquery를 이용하지 않는 경우는 원하시는데로 templete 변경하여 사용가능합니다. 
     * jquery를 사용한 Event 구현 예제 입니다.
     * Event 안에서 _thisPage.함수명 을 호출합니다. 
     */
    //    $("#btnSearch").on("click", function(){
    //     	//TODO btnSearch 클릭시 수행될 함수 호출 및 로직 구현
    //     	_thisPage.search();
    //     });

});

var _thisPage = {
    /**
    페이지 onload 함수
     */
    onload: function () {
        //TODO onload시 수행할 로직 구현
    }
    /**
    * Example)
        예제 함수이며, 페이지 구현시 삭제 바랍니다.
     **/
    //        ,search: function() {
    //        		//TODO btnSearch 버튼 클릭시 구현할 로직
    //	        	var jexAjax = jexjs.createAjaxUtil("WSVC ID");
    //	          jexAjax.set("InputDomainKey", "id");
    //	          jexAjax.execute( function( data ) {
    //	        	  if ( jexjs.isJexError( data ) ) {
    //	        		  //TODO Error
    //					  var errCode = jexjs.getJexErrorCode( data );
    //					  var errMsg = jexjs.getJexErrorMessage( data );
    //	        	  } else {
    //	        		  //TODO Success
    //	        	  }
    //	           });
    //         }
}

function calcMoney(org, add) {
    var orgMoney = org.replace("원", "").replace(/,/gi, "");
    orgMoney = Number(orgMoney) + Number(add);
    orgMoney = String(orgMoney).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";
    return orgMoney;
}

function calcMonth(org, add) {
    var orgMoney = org.replace("개월", "");
    orgMoney = Number(orgMoney) + Number(add);
    //	orgMoney = String(orgMoney).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";
    return String(orgMoney) + "개월";
}


function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    return true;
}

function cal() {
    //대출 금액
    var t1 = $('#calc01').val();
    var money = t1.replace(/[^0-9]/g, '');   //숫자만 추출
    //대출기간
    var t2 = $('#calc02').val();
    var term = t2.replace(/[^0-9]/g, '');   //숫자만 추출
    //대출금리
    var t3 = $('#calc03').val();
    var rate = t3.replace(/[^0-9]/g, '');   //숫자만 추출    

    if (money == "0원" || term == "0개월" || rate == "0%") {
        alert("입력 안한 값이 있습니다. 확인해주세요.");
    } else {
        var cal = money * (1 + (rate / 100)) / term;    //달마나 낼 금액 계산
        // 소수점 2자리 이상일때 반올림
        cal = cal.toFixed(2);
        // 숫자 3자리마다 컴마 찍기
        var callate = cal.toString()
            .replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        document.getElementById('tbl-wrap').innerHTML = '매달 상환 금액은 ' + "<font color=red>"+callate+"</font>" + '원 입니다.';
    }
}