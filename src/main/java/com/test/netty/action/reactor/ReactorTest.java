package com.test.netty.action.reactor;

/**
 * @author songxibo
 * @date 2018/4/27下午4:31
 */
public class ReactorTest {

    public static void main(String[] args) {

        try {

            Reactor reactor = new Reactor(9999);

            reactor.run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
