package com.example.demo.controller;

import com.example.demo.form.Job;
import com.example.demo.form.Jobs;
import com.example.demo.entity.PrintDevicesData;
import com.example.demo.service.PrintDevicesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PrintDevicesDataController {

    @Autowired
    private PrintDevicesDataService printDevicesDataService;

    // вывод всех вкладов
    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public Map<String, String> getAmountPage(@RequestBody Jobs jobs) {
        return printDevicesDataService.saveAndGetSumAmountToUser(jobs);
    }

    // вывод всех вкладов
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public List<PrintDevicesData> getPrintDevicesData(@ModelAttribute Job job,
                                                   @SortDefault(sort = "id") Sort sort){
        return printDevicesDataService.getAllWithFilter(job, sort);
    }
}
