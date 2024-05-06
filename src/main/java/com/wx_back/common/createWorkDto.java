package com.wx_back.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("creatework")
public class createWorkDto {
    String id;

    @TableField(value = "userPhone")
    String userPhone;

    String title;

    String date;
     //
    String texts;
     //
    String session_key;
}