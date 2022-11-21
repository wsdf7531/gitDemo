package model;

import lombok.Data;

import java.util.Map;

@Data
public class Strategy {
    private String effectTime;
    private Map<Integer,Integer> value;
}
