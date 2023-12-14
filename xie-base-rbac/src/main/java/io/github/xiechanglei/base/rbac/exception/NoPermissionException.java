package io.github.xiechanglei.base.rbac.exception;

public class NoPermissionException extends RuntimeException {
    public NoPermissionException() {
        super("no permission");
    }
}
