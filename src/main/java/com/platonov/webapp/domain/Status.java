package com.platonov.webapp.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Status implements GrantedAuthority {
    BLOCK,UNBLOCK;

    @Override
    public String getAuthority() {
        return name();
    }
}
