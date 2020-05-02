package com.ecut.cnr.framework.vo;

import lombok.Data;

import java.util.Map;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/29 12:15
 * @Description:
 */
@Data
public class CalendarTaskVo {

    private String title;
    private String start;
    private String end;
    private String id;
    private Map<String, Object> extendedProps;
}
