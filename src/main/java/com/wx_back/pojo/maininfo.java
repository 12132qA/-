package com.wx_back.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("maininfo")
public class maininfo implements Serializable {
    String name;
    String images;
    String descrip;
    String example;

}
