package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poetry")
public class poetry {
    int id;
    String author;
    String name;
    String texts;
    @TableField("Dynastic")
    String Dynastic;
    String sort;
    @TableField("sortFaction")
    String sortFaction;

    @TableField("interpret") // 和 sql 语句 关键字 有 重复
    String interpret;
}
