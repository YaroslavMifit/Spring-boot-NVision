package com.example.demo.service.impl;

import com.example.demo.form.Job;
import com.example.demo.form.Jobs;
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
    public List<PrintDevicesData> getAllWithFilter(Job job, Sort sort) {
        try {
            List<PrintDevicesData> printDevicesDataList = printDevicesDataRepository.findAll(new PrintDevicesDataSpecification(job), sort);
            logger.debug("Успешно получили данные из базы данных");
            return printDevicesDataList;
        } catch (Exception e) {
            logger.error("Не получилось получить данные из базы данных", e);
            throw e;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, String> saveAndGetSumAmountToUser(Jobs jobs) throws NullPointerException {
        
        PrintDevicesData printDevicesData;
       
        for (Job job : jobs.getJob()) {
                
            if (job.getJobId() == null || job.getUser() == null || job.getAmount() == null || job.getDevice() == null || job.getType() == null){
                logger.error("Один или несколько переданных параметров не были заполнены");
                throw new NullPointerException("Один или несколько переданных параметров не были заполнены");
            }
                
            printDevicesData = new PrintDevicesData();

            PrintDevicesData oldPrintDevicesData = getPrintDevicesData(job);

            savePrintDevicesData(printDevicesData , job, oldPrintDevicesData.getId());
        }
                
        Map<String, List<Job>> result = jobs.getJob().stream().collect(Collectors.groupingBy(Job::getUser));
        Map<String, String> sum = new HashMap<>();
        result.forEach((x, y ) -> sum.put(x, String.valueOf(y.stream().mapToInt(Job::getAmount).sum())));
        return sum;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public PrintDevicesData getPrintDevicesData(Job job) {
        return printDevicesDataRepository.findByJobIdAndDevice(job.getJobId(), job.getDevice());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void savePrintDevicesData(PrintDevicesData printDevicesData, Job job, Long id) {

        if(id != null) {
            printDevicesData.setId(id);
            logger.debug("Запись с Id " + id + " была обновлена");
        }

        printDevicesData.setJobId(job.getJobId())
                .setType(job.getType())
                .setUser(job.getUser())
                .setDevice(job.getDevice())
                .setAmount(job.getAmount())
                .setTime(Calendar.getInstance().getTime());

        printDevicesDataRepository.save(printDevicesData);

        logger.debug("Успешно сохранили запись " + printDevicesData.toString() + " в базу данных");
    }
}
