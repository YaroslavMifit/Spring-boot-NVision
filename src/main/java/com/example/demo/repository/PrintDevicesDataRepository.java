package com.example.demo.repository;

import com.example.demo.entity.PrintDevicesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrintDevicesDataRepository extends JpaRepository<PrintDevicesData, Long>, JpaSpecificationExecutor<PrintDevicesData> {

    PrintDevicesData findByJobIdAndDevice(Long jobId, String device);
}
