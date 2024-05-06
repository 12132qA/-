package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@Component
@Data
@NoArgsConstructor
@TableName("creatework")
public class createWork implements Serializable {
    /**
     *  自定义 锁机制
     * */
    @TableField("workId") // 个人作品唯一标识 Id (默认值自增)
    String workId;
    @TableId(type = IdType.INPUT)
    String id;
    @TableField(value = "userPhone")
    String userPhone;
    String title;
    String date;
    String texts;
    int status; // 状态 (是否存在于 展示列表中)

}
