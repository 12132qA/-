package com.wx_back.common;

import lombok.Data;

@Data
public class AddShowListDto {
    String phone;
    String title;
    String session_key;
    String workId;
    String date;

}
