package com.wx_back.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("showList")
@AllArgsConstructor
@NoArgsConstructor
public class showList implements Serializable {
    @TableId(type = IdType.ASSIGN_ID) // 唯一标识
    String Sid;
    String phone; //用户手机号
    String title; // 作品标题
    @TableField("showTime")
    String showTime; //展示区添加时间
    String texts; // 文章
    @TableField("imagUrl")
    String imagUrl; // 图片连接
    @TableField("likeNum")
    int likeNum; // 喜欢的数量
    @TableField("attentionNum")
    int attentionNum; // 受关注的数量
    @TableField("id")
    int id; // id 值
    @TableField("session_key")
    String session_key;
}
