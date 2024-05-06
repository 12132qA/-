package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("workimg")
public class workImg implements Serializable {
    String url; //  文件对应的 url
    @TableField("filePath")
    String filePath;
    String name; // 图片文件对应文章标题名称
    String phone; // 电话
    String session_key; // 密钥

}
