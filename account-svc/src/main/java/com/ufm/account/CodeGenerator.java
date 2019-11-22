package com.ufm.account;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc
           //设置是否支持AR模式
           // .setActiveRecord(true)
           //设置生成代码的作者
           .setAuthor("hulantian")
           //设置输出代码的位置
           .setOutputDir(projectPath + "/src/main/java")
           //.setEnableCache(false)// XML 二级缓存
           // .setSwagger2(true); 实体属性 Swagger2 注解
           .setBaseResultMap(true)// XML ResultMap
           .setBaseColumnList(true)// XML columList
           //.setKotlin(true) 是否生成 kotlin 代码
           //设置是否覆盖原来的代码
           .setFileOverride(true)
           .setOpen(false);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://218.6.173.136:3306/db_lo_project?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Sjyh.2017");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                   System.out.println("转换类型：" + fieldType);
                   //tinyint转换成Boolean
                     if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                           return DbColumnType.INTEGER;
                     }
                     //将数据库中datetime转换成date
                   if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                           return DbColumnType.DATE;
                   }
                   return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.ufm.lo");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.ufm.lo.common.model.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("lo_");
        strategy.setSuperEntityColumns(new String[] { "is_deleted", "create_time", "update_time"});
        /*List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("is_deleted", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT));
        strategy.setTableFillList(tableFillList);*/

        // 代码生成器
        new AutoGenerator()
        .setGlobalConfig(gc)
        .setDataSource(dsc)
        .setPackageInfo(pc)
        .setCfg(cfg)
        .setTemplate(templateConfig)
        .setStrategy(strategy)
        //.setTemplateEngine(new FreemarkerTemplateEngine())
        .execute();
    }

}