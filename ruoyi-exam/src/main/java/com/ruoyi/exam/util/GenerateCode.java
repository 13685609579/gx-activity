package com.ruoyi.exam.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shuaige
 * @DESCRIPTION
 * @create: 2020/12/25
 */
public class GenerateCode {
    public static void main(String[] args) {

        //常用设置
        String moduleName = "/ruoyi-exam";  //模块路径
        String packageName = "com.ruoyi.exam"; //包名
        //是否覆盖
        boolean override = false;
        //boolean override = true;

        String tablePrefix = "";//前缀
        //映射的表名
        List<String> tables = new ArrayList<>();
        tables.add("test_train");

        String[] tableNames = tables.toArray(new String[1]);

        String url = "183.162.219.15:33306/exam-system";
        String password = "Jsgs123!@#";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //获取项目所在根目录 D:\workspace\idea\all
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + moduleName + "/src/main/java");
        gc.setFileOverride(override); // 是否覆盖,false:不覆盖如果已经有文件则不再生产,true覆盖重新生成
        gc.setAuthor("yxp");//作者名称
        gc.setOpen(false);
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);//实体属性 Swagger2 注解
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        //设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + url + "?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        // dsc.setDriverName("com.mysql.jdbc.Driver"); //mysql5.6以下的驱动
        dsc.setUsername("root");
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);

        //包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName); //包名
        // pc.setModuleName("day1225"); //模块名, 如果填写模块名会在controller访问路径上加一层模块名
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableNames); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);//表名映射规则
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//属性名映射规则
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setRestControllerStyle(true); //restful风格
        strategy.setLogicDeleteFieldName("delFlag"); //逻辑删除字段名
//        strategy.setVersionFieldName("version");// 乐观锁
        strategy.setTablePrefix(tablePrefix);

        //自动填充配置
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.UPDATE);
        TableFill createRealName = new TableFill("create_by", FieldFill.INSERT);
        TableFill updateBy = new TableFill("update_by", FieldFill.UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        tableFills.add(createRealName);
        tableFills.add(updateBy);
        strategy.setTableFillList(tableFills);
        //根据你的表名来建对应的类名，如果你的表名没有下划线，比如test，那么你就可以取消这一步
        // strategy.setTablePrefix("t_");
        //自动转下划线，比如localhost:8080/hello_id_2
        strategy.setControllerMappingHyphenStyle(true);

        // 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
        //添加
        mpg.setGlobalConfig(gc); //添加全局配置
        mpg.setDataSource(dsc); //添加数据源
        mpg.setPackageInfo(pc); //添加包的配置
        mpg.setStrategy(strategy); //添加策略配置

        //mpg.setTemplateEngine(null);
        //执行
        mpg.execute();
    }
}
