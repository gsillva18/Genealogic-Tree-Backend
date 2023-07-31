package com.example.genealogictree.log;

import com.example.genealogictree.config.GenealogicTreeConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ManagerLog {

    private static final String LOG_NAME = "LOG_GENEALOGIC_TREE";
    private static final ManagerLog MANAGER_LOG = new ManagerLog();
    private final LogGenealogicTree logGenealogicTree = new LogGenealogicTree();

    public void finalizeLog(List<Log> listLogs){
        logGenealogicTree.setName(LOG_NAME);
        logGenealogicTree.setTime(LocalDateTime.now().toString());
        logGenealogicTree.setListLogs(listLogs);

        SplunkLog.sendToSplunk(logGenealogicTree);
    }

    public static ManagerLog getInstance(){
        return MANAGER_LOG;
    }

}
