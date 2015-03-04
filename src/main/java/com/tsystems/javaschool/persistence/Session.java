package com.tsystems.javaschool.persistence;

public class Session {

    private static Session session;
    private boolean isOpened;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }

    public static Session getInstance(){
        if (session == null){
            session = new Session();
        }
        return session;
    }

    private Session(){}
}
