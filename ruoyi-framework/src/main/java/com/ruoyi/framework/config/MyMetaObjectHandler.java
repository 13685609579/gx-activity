package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * mybatis-plus自动填充
 *
 * @author shuaige
 * @since 2021/6/26
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //自动填充创建时间
        this.strictInsertFill(metaObject, "createTime", Date::new, Date.class);
        //自动填充创建人姓名
        this.strictInsertFill(metaObject, "createBy", MyMetaObjectHandler::getUsername, String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //自动填充更新时间
        this.strictUpdateFill(metaObject, "updateTime", Date::new, Date.class);
        //自动填充更新人姓名
        this.strictUpdateFill(metaObject, "updateBy", MyMetaObjectHandler::getUsername, String.class);
    }

    //获取当前用户,防止匿名访问的时候创建人获取不到报错
    private static String getUsername() {
        String username = "匿名用户";
        try {
            username = SecurityUtils.getLoginUser().getUsername();
        } catch (Exception e) {
            log.error("获取当前用户出现异常:{}", e.getMessage());
        }
        return username;
    }

}
