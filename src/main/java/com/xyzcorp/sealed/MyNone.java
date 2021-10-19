package com.xyzcorp.sealed;

import java.util.StringJoiner;

public final class MyNone implements MyOption<Void> {
    public MyNone() {}

    @Override
    public String toString() {
        return new StringJoiner(", ", MyNone.class.getSimpleName() + "[", "]")
            .toString();
    }
}


//List<A>
//Concat(A head, List<A> tail)
//Nil


//Account
//enum Account
// Checking(accountNumber:String)
// Saving(accountNumber:String)
// IRA(accountNumber:String, taxWithdrawal)

