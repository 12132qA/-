package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data // 前四个功能的集成
@Component
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class user implements Serializable {
    @TableField(value = "id",select = true,exist = true) // select 选择可查询 ， exist 表示在数据库中存在
    @TableId(type = IdType.ASSIGN_ID) // 主键自增
    private Long id;

    private String name;

    private String password;

//    @TableField(exist = false,value = "deleted")
    @TableLogic(value = "0",delval = "1") // 默认还没删除字段   默认已删除字段
    private int deleted;

    @Version
    private int version; // 实现乐观锁

}
