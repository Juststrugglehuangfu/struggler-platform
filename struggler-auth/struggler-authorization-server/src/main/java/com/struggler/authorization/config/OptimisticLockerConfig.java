package com.struggler.authorization.config;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @类名： OptimisticLockerConfig
 * @描述：【乐观锁插件】
 * @修改描述：【修改描述】
 * @版本：1.0
 * @创建人：wolf
 * @创建时间：2019/7/13 16:43
 * @修改人：wolf
 * @修改时间：2019/7/13 16:43
 **/
@Configuration
public class OptimisticLockerConfig {
    /**
     * @return com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor
     * @Author wolf
     * @Description 乐观锁插件
     * @Date 16:44 2019/7/13
     * @Param []
     **/
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
