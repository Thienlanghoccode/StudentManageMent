package vn.yenthan.core.config;

import vn.yenthan.core.model.UserContext;

public class ThreadContext {
    private static final ThreadLocal<UserContext> userHolder = new ThreadLocal<>();

    public static UserContext getCurrentUser() {
        return userHolder.get();
    }

    public static void setCurrentUser(UserContext user) {
        userHolder.set(user);
    }
}
