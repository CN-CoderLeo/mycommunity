package com.lg.mycommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MycommunityApplication {

    @PostConstruct
    public void init(){
        // 解决netty启动冲突问题 redis和elasticsearch同时需要netty
        // see Netty4Utils.setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(MycommunityApplication.class, args);
    }

}
