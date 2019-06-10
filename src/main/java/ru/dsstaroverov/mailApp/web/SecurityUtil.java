package ru.dsstaroverov.mailApp.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.dsstaroverov.mailApp.AuthorizedUser;

import ru.dsstaroverov.mailApp.model.Role;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    private SecurityUtil() {
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int authUserId() {
        return get().getId();
    }

    public static boolean isAdmin(){
        return get().getAuthorities().contains(Role.ROLE_ADMIN);
    }

}