package com.wx_back.common;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *  继承 session 会话类
 *
 * */
public class MySessionContext {

    private static HashMap<String,Object> sessionIdMap = new HashMap<String,Object>();

    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionIdMap.put(session.getId(), session);
        }
    }

    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionIdMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (sessionIdMap.containsKey(session_id)){
            return (HttpSession) sessionIdMap.get(session_id);
        }else{
            return null;
        }
    }
}
