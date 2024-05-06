package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("mylearn")
public class mylearn {
    String id;
    @TableField("userPhone")
    String userPhone;
    String date;
    @TableField("poertyId") //
    String poertyId;
    String title;
}
