package com.lg.mycommunity;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @version 1.0.0
 * @ClassName CommunityServeltInitalizer.java
 * @Description 自定义tomcat 启动入口
 * @createTime
 */
public class CommunityServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MycommunityApplication.class);
    }
}
