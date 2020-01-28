package com.qsq.ego.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProviderTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml", "spring/applicationContext-tx.xml", "spring/applicationContext-dubbo.xml");
        ac.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ac.stop();
    }
}
