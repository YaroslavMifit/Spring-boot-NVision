package com.example.demo.service;

import com.example.demo.entity.Job;
import com.example.demo.entity.Jobs;
import com.example.demo.entity.PrintDevicesData;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PrintDevicesDataService {
    List<PrintDevicesData> getAll(Job job, Sort sort);
    Map<String, String> saveAndGetSumAmountToUser(Jobs jobs);
}
