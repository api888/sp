package com.example.testpluginlib;

import chao.java.tools.servicepool.annotation.Service;

/**
 * @author luqin
 * @since  2019-07-09
 */
@Service
public class TestPluginService {
    public void print() {
        System.out.println("I'm a test service.");
    }
}
