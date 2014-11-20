package com.ma.lance.restful;

/**
 * Created by malance on 14/11/20.
 */
public interface Lifecycle {
    void init(int port);
    void start();
    void stop();
}
