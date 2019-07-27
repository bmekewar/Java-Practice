package com.bvm.session;

public interface ExpirationListener<E> {
    void expired(Object object);
}
