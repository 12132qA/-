package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("myattention")
public class myAttention {
   @TableField("phone")
    String phone;
    @TableField("attenPhone")
    String attenPhone;
}
