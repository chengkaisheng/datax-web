package com.wugui.datax.admin.entity;


import lombok.Data;

/**
 * Created by iwlnner on 2020/9/17.
 */
@Data
public class Chart<T> {

    private T min;
    private T max;
    private Long number;
}
