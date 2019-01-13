package com.example.demo.service.impl;

import com.example.demo.entity.Job;
import com.example.demo.entity.Jobs;
import com.example.demo.entity.PrintDevicesData;
import com.example.demo.repository.PrintDevicesDataRepository;
import com.example.demo.repository.specification.PrintDevicesDataSpecification;
import com.example.demo.service.PrintDevicesDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PrintDevicesDataServiceImpl implements PrintDevicesDataService{
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private PrintDevicesDataRepository printDevicesDataRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PrintDevicesData> getAll(Job job, Sort sort) {
        List<PrintDevicesData> printDevicesDataList = printDevicesDataRepository.findAll(new PrintDevicesDataSpecification(job), sort);
        logger.debug("Успешно получили данные из базы данных");
        return printDevicesDataList;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Map<String, String> saveAndGetSumAmountToUser(Jobs jobs) {
        PrintDevicesData printDevicesData;
        for (Job job: jobs.getJob()) {
            printDevicesData = new PrintDevicesData();

            PrintDevicesData oldPrintDevicesData = printDevicesDataRepository.findAllByJobIdAndDevice(job.getJobId(), job.getDevice());
            if(oldPrintDevicesData != null) {
                printDevicesData.setId(oldPrintDevicesData.getId());
                logger.debug("Запись с Id " + oldPrintDevicesData.getId() + " была обновлена");
            }

            printDevicesData.setJobId(job.getJobId())
                            .setType(job.getType())
                            .setUser(job.getUser())
                            .setDevice(job.getDevice())
                            .setAmount(job.getAmount())
                            .setTime(Calendar.getInstance().getTime());
            printDevicesDataRepository.save(printDevicesData);
            logger.debug("Успешно сохранили запись " + oldPrintDevicesData.toString() + " в базу данных");
        }

        Map<String, List<Job>> result = jobs.getJob().stream().collect(Collectors.groupingBy(Job::getUser));
        Map<String, String> sum = new HashMap<>();
        result.forEach((x, y ) -> sum.put(x, String.valueOf(y.stream().mapToInt(Job::getAmount).sum())));
        return sum;
    }
}
