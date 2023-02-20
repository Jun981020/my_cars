
//board_write page 공개&비공개 바뀔때마다 input 태그 보여주기 숨기기
function checking(){
    if(private_content.value == 1){
        $('#private_content_password').attr('type','text');
    }else{
        $('#private_content_password').attr('type','hidden');
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
      if($("#private_content_password").val() == 0 && private_content.value == 1){
            alert("비공개 글은 비밀번호를 입력해야 합니다.");
            $("#private_content_password").focus();
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
console.log('현재 이페이지에 적용중입니다.');