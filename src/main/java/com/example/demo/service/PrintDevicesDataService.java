package com.example.demo.service;

import com.example.demo.form.Job;
import com.example.demo.form.Jobs;
import com.example.demo.entity.PrintDevicesData;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PrintDevicesDataService {
    List<PrintDevicesData> getAllWithFilter(Job job, Sort sort);
    Map<String, String> saveAndGetSumAmountToUser(Jobs jobs);

    @Transactional(propagation = Propagation.REQUIRED)
    PrintDevicesData getPrintDevicesData(Job job);

    @Transactional(propagation = Propagation.MANDATORY)
    void savePrintDevicesData(PrintDevicesData printDevicesData, Job job, Long id);
}
