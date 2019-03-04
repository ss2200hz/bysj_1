package com.example.bysj_1.moduls;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession implements Serializable {
    private static final long serializableUId = 888L;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
