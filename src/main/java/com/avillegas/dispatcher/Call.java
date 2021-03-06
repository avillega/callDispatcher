package com.avillegas.dispatcher;

/*
 * Object to encapsulate data about the call eg. Client, Subject, etc. In this example it only conatins an id
 */
public class Call {


    private int id;

    public Call(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        return id == call.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }


}
