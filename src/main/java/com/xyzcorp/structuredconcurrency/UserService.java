package com.xyzcorp.structuredconcurrency;

public class UserService {
    public User findUser(Long id) {
        System.out.println("findUser: " + Thread.currentThread());
        return new User("Simon", "Roberts");
    }

    public User findUserError(long id) {
        System.out.println("findUserError: " + Thread.currentThread());
        throw new RuntimeException("Couldn't find user");
    }

    public User findUserLongTime(long id) {
        System.out.println("findUserLongTime: " + Thread.currentThread());
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new User("Simon", "Roberts");
    }
}
