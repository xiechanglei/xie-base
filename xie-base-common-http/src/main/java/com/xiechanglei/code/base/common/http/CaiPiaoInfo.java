package com.xiechanglei.code.base.common.http;

import lombok.Data;

import java.util.List;

@Data
public class CaiPiaoInfo {
    private Integer no;
    private String date;
    private Long money;
    private List<Integer> number;

    public String toSQL(){
        return "INSERT INTO `caipiao` (`no`, `date`, `money`, `number1`, `number2`, `number3`, `number4`, `number5`, `number6`, `number7`) VALUES ('"+no+"', '"+date+"', '"+money+"', '"+number.get(0)+"', '"+number.get(1)+"', '"+number.get(2)+"', '"+number.get(3)+"', '"+number.get(4)+"', '"+number.get(5)+"', '"+number.get(6)+"')";
    }
}
