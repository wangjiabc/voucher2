package com.voucher.manage.tkmapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(value = "tk.mybatis.mapper.annotation",
        properties = {
                "mappers=com.voucher.manage.tkmapper.mapper",
                "notEmpty=true"
        }
)
public class MyBatisConfigProperties {
    @Configuration
    @MapperScan(value = "tk.mybatis.mapper.annotation", mapperHelperRef = "mapperHelper")
    public static class MyBatisConfigRef {
        //其他

        @Bean
        public MapperHelper mapperHelper() {
            Config config = new Config();
            List<Class> mappers = new ArrayList<>();
            mappers.add(Mapper.class);
            config.setMappers(mappers);

            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.setConfig(config);
            return mapperHelper;
        }
    }
}
