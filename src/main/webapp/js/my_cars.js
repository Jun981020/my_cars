
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
                            alert("오류발생 getMemberId()"+error);
                        });
    return memberId;
}
//board_write page 비공개글에 비밀번호를 입력하지 않을시 false 리턴 입력하면 input hidden태그에 memberId value로 넣어줌
function nepCheck(){
      if($("#private_content_password").val() == 0 && private_content.value == 1){
            alert("비공개 글은 비밀번호를 입력해야 합니다.");
            $("#private_content_password").focus();
            return false;
      }else{
        let memberId = getSessionMemberId();
        $("#memberId").attr('value',memberId);
      }
}

function checkLogin(){
    let user  = getSessionMemberId();
    if(user.length == 0){
      alert("로그인먼저 해주세요");
      location.href='/member/login';
      return false;
    }
}
console.log('현재 이페이지에 적용중입니다.');