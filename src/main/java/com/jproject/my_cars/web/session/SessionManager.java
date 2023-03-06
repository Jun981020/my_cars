package com.jproject.my_cars.web.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SessionManager {

    private static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();
    //세선생성
    public void createSession(Object value, HttpServletResponse response){
        String sessionId = UUID.randomUUID().toString();
        System.out.println("sessionId = " + sessionId);
        sessionStore.put(sessionId,value);

        Cookie myCookie = new Cookie(SESSION_COOKIE_NAME,sessionId);
        myCookie.setPath("/");
        response.addCookie(myCookie);
    }
    //세션 가져오기
    public Object getSession(HttpServletRequest request){
        Cookie cookie = findCookie(request,SESSION_COOKIE_NAME);
        if (cookie == null){
            return null;
        }
        log.info("cookie.value : " + cookie.getValue());
        return sessionStore.get(cookie.getValue());
    }
    public void expire(HttpServletRequest request){
        Cookie find = findCookie(request, SESSION_COOKIE_NAME);
        if (find != null){
            sessionStore.remove(find.getValue());
        }
    }
    private Cookie findCookie(HttpServletRequest request,String cookieName){
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

}
