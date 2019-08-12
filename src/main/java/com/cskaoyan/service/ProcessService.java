package com.cskaoyan.service;

import com.cskaoyan.bean.Process;
import com.cskaoyan.bean.ProcessExample;

import java.util.List;

public interface ProcessService {
    List<Process> findProcessList(int page, int rows);

    Process getProcessById(String processId);

    boolean insertProcess(Process process);

    boolean updateProcess(Process process, ProcessExample processExample);

    boolean deleteProcesses(ProcessExample processExample);

    List<Process> searchProcessList(int page, int rows, ProcessExample processExample);

    List<Process> findAllProcess();
}
