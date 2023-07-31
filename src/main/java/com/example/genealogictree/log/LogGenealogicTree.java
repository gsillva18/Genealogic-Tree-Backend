package com.example.genealogictree.log;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LogGenealogicTree {

    private String name;
    private String time;
    private List<Log> listLogs = new ArrayList<>();

}
