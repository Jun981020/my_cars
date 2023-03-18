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
//session manger 에서 세션값에 딜러 아이디 가져오기
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
//checkSessionEmpty() 로 받은 boolean 으로 pass or 로그인 페이지로 이동시킨다.
function checkLogin(){
    let result = checkSessionEmpty();
    if(!result){
      alert("로그인먼저 해주세요");
      location.href='/member/login';
      return false;
    }else{
      return true;
    }
}
//세션에서 로그인한 데이터가 있으면 true 를 null 이면 false 를 리턴한다.
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
//회원 게시판 클릭시 회원 게시판으로 이동
function refresh1(){
        $("#dealer").removeClass("btn-light");
        $("#dealer").addClass("btn-outline-light");

        $("#member").removeClass("btn-outline-light");
        $("#member").addClass("btn-light");

        let code = "member";
        location.href = '/board/list/' + code;
}
//딜러 게시판 클릭시 딜러 게시판으로 이동
function refresh2(){
        $("#member").removeClass("btn-light");
        $("#member").addClass("btn-outline-light");

        $("#dealer").removeClass("btn-outline-light");
        $("#dealer").addClass("btn-light");

        let code = "dealer";
        location.href = '/board/list/' + code;
}
//로그인한 회원또는 딜러인지 확인하고 true를 리턴하면 checkModeAndSendBoardWrite() 함수 실행
function boardWriteButton(){
    checkLogin();
    checkModeAndSendBoardWrite();
}
//session manger 에서 로그인한게 회원이면 회원게시판에 글을쓰고 딜러라면 딜러게시판에 글을쓴다.
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
//session manger 에서 (회원,회원번호) 또는 (딜러,딜러번호) 의 데이터를 맵으로 내려줌
function getSessionTypeLoginId(){
    let id = null;
    let session = null;
    $.ajax({
        url: "/main/getSessionTypeLoginId",
        type: "GET",
        async: false,
        success: function(response) {
            // JSON 데이터를 객체로 변환
            var data = JSON.parse(response);
            id = data.data;
            session = data.session;
        }
    });
    let data = {id,session};
    return data;
}
//로그인 한 회원인지 확인후, session data 에 id를 가져와 input[id=loginId] 의 value 에 id 를 넣어줌
function getSessionDataForWriteReply(){
    var result = checkLogin();
    if(result){
        let id = getSessionTypeLoginId().id;
        $("#loginId").attr("value",id);
        repFrm.submit();
    }
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
let optionListPost = [];
$("select[id=optionsPost]").change(function(){
      var text = $("select[id=optionsPost] option:selected").text();
      var value = $(this).val();
      $("#ulListPost").append("<li value ="+value+ ">" +text+"</li>");
      optionListPost.push(text);
})
$("button[name=submitPost]").on('click',function(){
      const result =  confirm("차량등록을 하시겠습니까?");
      if(result){
         $("input[name=options]").attr("value",optionListPost);
      }else{
          return null;
      }
})
let optionListModify = [];
$("select[id=optionsModify]").change(function(){
      var text = $("select[id=optionsModify] option:selected").text();
      var value = $(this).val();
      $("#ulList").append("<li value ="+value+ ">" +text+"</li>");
})
    $("button[name=submitModify]").on('click',function(){
        const result =  confirm("차량등록을 하시겠습니까?");
        if(result){
            var length = $("#ulList li").length;
            for(var i = 0 ; i < length; i++){
                var values = $("#ulList").find("li").eq(i).text();
                optionListModify.push(values);
            }
           $("input[name=options]").attr("value",optionListModify);
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
//modal 에 board_id 를 넘겨준다.
function sendData(data){
    $("#passwordResult").css("display","none");
    $("#password").val("");
    $("#resData").attr("value",data);
}
//modal 에 비밀번호 입력창에 비밀번호를 입력하면 서버에서 비밀번호 확인후 true 일경우 해당 게시물로 이동 아닐경우 오류메세지 표시
function checkPrivateContentPassword(data){
    let cat = data;
    let board = $("#resData").val();
    let password = $("#password").val();
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
//댓글 수정버튼 클릭시 새로운 댓글 작성창 표시, 기존 댓글 숨기기
function modifyReplyButton(id){
    var reply_text = '#reply-text'+id;
    var commentText = $(reply_text).text();
    var commentID = id;
    var edit_comment_form = '#edit-comment-form'+id;
    var edit_comment_form_textarea = edit_comment_form +' textarea';
    var edit_comment_form_input = edit_comment_form +' > input[name="id"]'
    $(edit_comment_form_textarea).val(commentText);
    $(edit_comment_form_input).val(commentID);
    $(reply_text).hide();
    $(edit_comment_form).show();
}
//댓글수정 취소클릭시 작성폼 숨기기, 기존댓글 표시
function cancelReplyModify(id){
    var edit_comment_form = '#edit-comment-form'+id;
    var reply_text = '#reply-text'+id;
    $(edit_comment_form).hide();
    $(reply_text).show();
}
function checkMemberJoinNep(){
    if(frm.id.value.length == 0){
        alert("아이디를 입력해주세요");
        frm.id.focus();
        return false;
    }
    if(frm.password.value.length == 0){
        alert("비밀번호를 입력해주세요");
        frm.password.focus();
        return false;
    }
    if(frm.name.value.length == 0){
        alert("이름을 입력해주세요");
        frm.name.focus();
        return false;
    }
    if(frm.email.value.length == 0){
        alert("이메일을 입력해주세요");
        frm.email.focus();
        return false;
    }
    if(frm.phone.value.length == 0){
        alert("이메일을 입력해주세요");
        frm.phone.focus();
        return false;
    }
    alert("회원가입이 완료되었습니다.");
    return true;
}
function checkDealerJoinNep(){
    if(frm.id.value.length == 0){
        alert("아이디를 입력해주세요");
        frm.id.focus();
        return false;
    }
    if(frm.password.value.length == 0){
        alert("비밀번호를 입력해주세요");
        frm.password.focus();
        return false;
    }
    if(frm.name.value.length == 0){
        alert("이름을 입력해주세요");
        frm.name.focus();
        return false;
    }
    if(frm.phone.value.length == 0){
        alert("전화번호를 입력해주세요");
        frm.phone.focus();
        return false;
    }
    if(frm.employee_number.value.length == 0){
        alert("사원번호를 입력해주세요");
        frm.employee_number.focus();
        return false;
    }
    if(frm.company.value.length == 0){
        alert("소속된 회사를 입력해주세요");
        frm.company.focus();
        return false;
    }
    if(frm.acquisition_date.value == null || frm.acquisition_date.value == ''){
        alert("사원증발급 날짜를 입력해주세요");
        frm.acquisition_date.focus();
        return false;
    }
}
console.log('현재 이페이지에 적용중입니다.');
