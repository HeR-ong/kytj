package com.herong.demo.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author herong22384
 * @date 2020/3/22 22:16
 */
@Configuration
public class PageConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        // 表示支持从接口中读取pageNum和pageSize
        props.setProperty("supportMethodsArguments", "true");
        pageHelper.setProperties(props);
        return pageHelper;
    }
}
