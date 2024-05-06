package com.wx_back.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("creatework")
public class workDto {
    String phone;
    String session_key;
}
