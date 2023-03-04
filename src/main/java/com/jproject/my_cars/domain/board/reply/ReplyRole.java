package com.jproject.my_cars.domain.board.reply;

public enum ReplyRole {
    MEMBER("회원"),
    DEALER("딜러");
    private String roleName;

    public String getRoleName(){
        return this.roleName;
    }
    private ReplyRole(String roleName){
        this.roleName = roleName;
    }
}
