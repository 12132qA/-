package com.wx_back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class search implements Serializable {
    Integer id;

    String author;

    String name;

    String texts;

    String Dynastic;

    String sort ;

    String sortFaction;
}
