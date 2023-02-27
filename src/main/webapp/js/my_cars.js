
//board_write page 공개&비공개 바뀔때마다 input 태그 보여주기 숨기기
function checking(){
    if(secret_content.value == 1){
        $('#secret_password').attr('type','text');
    }else{
        $('#secret_password').attr('type','hidden');
    }
}
//session manager 에서 세션값에 멤버 아이디 가져오기
function getSessionMemberId(){
    let memberId = null;
    $.ajax({
            url : '/main/getSessionMemberId',
            method : "GET",
            async: false
                    }).done(function(res){
                        memberId = res;
                        }).fail(function(xhr,status,error){
                            alert("오류발생 getSessionMemberId()"+error);
                        });
    return memberId;
}
function getSessionDealerId(){
    let dealerId = null;
    $.ajax({
            url : '/main/getSessionDealerId',
            method : "GET",
            async: false
                    }).done(function(res){
                        dealerId = res;
                        }).fail(function(xhr,status,error){
                            alert("오류발생 getSessionDealerId()"+error);
                        });
    return dealerId;
}
//board_write page 비공개글에 비밀번호를 입력하지 않을시 false 리턴 입력하면 input hidden태그에 memberId value로 넣어줌
function nepCheck(){
      if($("#secret_password").val() == 0 && secret_content.value == 1){
            alert("비공개 글은 비밀번호를 입력해야 합니다.");
            $("#secret_password").focus();
            return false;
      }
}

function checkLogin(){
    let result = checkSessionEmpty();
    if(!result){
      alert("로그인먼저 해주세요");
      location.href='/member/login';
    }
}
function checkSessionEmpty(){
    var result = null;
    $.ajax({
            url : '/main/getSessionEmpty',
            method : "GET",
            async: false
                    }).done(function(res){
                    result = res;
                    }).fail(function(xhr,status,error){
                            alert("오류발생 checkSessionEmpty()"+error);
                    });
    return result;
}
function refresh1(){
        $("#dealer").removeClass("btn-light");
        $("#dealer").addClass("btn-outline-light");

        $("#member").removeClass("btn-outline-light");
        $("#member").addClass("btn-light");

        let code = "member";
        location.href = '/board/list/' + code;
}
function refresh2(){
        $("#member").removeClass("btn-light");
        $("#member").addClass("btn-outline-light");

        $("#dealer").removeClass("btn-outline-light");
        $("#dealer").addClass("btn-light");

        let code = "dealer";
        location.href = '/board/list/' + code;
}
function boardWriteButton(){
    checkLogin();
    checkModeAndSendBoardWrite();
}
function checkModeAndSendBoardWrite(){
    $.ajax({
        url : "/main/getSessionTypeName",
        type : "GET",
        dataType : "JSON"
    }).done(function(res){
        if(res.Member != null){
            let memberId = getSessionMemberId();
            location.href = '/board/write/'+'member/'+memberId;
        }else{
            let dealerId = getSessionDealerId();
            location.href = '/board/write/'+'dealer/'+dealerId;
        }
    })
}
function getSessionTypeLoginId(){
    let id = null;
    $.ajax({
        url: "/main/getSessionTypeLoginId",
        type: "GET",
        async: false,
        success: function(response) {
            // JSON 데이터를 객체로 변환
            var data = JSON.parse(response);
            id = data.data;
        }
    });
    return id;
}
function getSessionDataForWriteReply(){
    checkLogin();
    let id = getSessionTypeLoginId();
    $("#loginId").attr("value",id);
    repFrm.submit();
}
//년도 가져오기
function getYear(){
        var date = new Date();
        var selYear = date.getFullYear();

        for(var i = selYear;i >=2000; i--){
            $("select[name=year]").append("<option value = " + i + ">" +i +"</option>");
        }
}
//옵션기능 배열화
let optionList = [];
$("select[id=options]").change(function(){
      var text = $("select[id=options] option:selected").text();
      var value = $(this).val();
      $("#ulList").append("<li value ="+value+ ">" +text+"</li>");
      optionList.push(text);
      console.log(optionList);
})
$("button[name=submit]").on('click',function(){
      const result =  confirm("차량등록을 하시겠습니까?");
      if(result){
         $("input[name=options]").attr("value",optionList);
      }else{
          return null;
      }
})
//이미지 클릭시 미리보기 사진 보여주기
function readURL(input,num) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('preview'+num).src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        document.getElementById('preview'+num).src = "";
      }
}
function sendData(data){
    $("#passwordResult").css("display","none");
    $("#password").val("");
    $("#resData").attr("value",data);
}
function checkPrivateContentPassword(data){
    let cat = data;
    console.log(cat);
    let board = $("#resData").val();
    console.log(board);
    let password = $("#password").val();
    console.log(password);


    $.ajax({
        type: 'GET',
        url: '/board/checkPrivateContent/'+cat,
        data: {
                password : password,
                board : board
        },
        success: function(response) {
                if(response){
                location.href = '/board/memberBoard/' + board;
        }else{
             $("#passwordResult").css("display","block")
        }
        },
        error: function(xhr, status, error) {
            alert('오류가 발생했습니다.');
        }
    });
}
console.log('현재 이페이지에 적용중입니다.');