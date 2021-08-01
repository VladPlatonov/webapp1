package com.platonov.webapp.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import java.util.HashSet;
import java.util.Set;

public class RequestsHandler implements HandlerInterceptor {
    public static Set<String> users_to_update_roles = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {

            Principal principal = (Principal) auth.getPrincipal();

            String username = principal.getName();
            if (users_to_update_roles.contains(username)) {
                auth.setAuthenticated(false);
                users_to_update_roles.remove(username);
            }

        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
