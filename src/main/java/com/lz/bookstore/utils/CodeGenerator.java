package com.lz.bookstore.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        gererator();
    }

    private static void gererator(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("buyaoke_lz") // 设置作者
                            .enableSwagger()// 开启 swagger 模式

                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\15382\\Desktop\\毕设\\code product\\bookstore\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.lz.bookstore") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\15382\\Desktop\\毕设\\code product\\bookstore\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_comment")// 设置需要生成的表名
                            .addTablePrefix("t_", "c_","sys_")// 设置过滤表前缀
                            .entityBuilder().enableLombok()
                            .controllerBuilder().enableRestStyle();
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
