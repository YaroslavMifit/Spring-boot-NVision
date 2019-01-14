package com.example.demo.repository.specification;

import com.example.demo.form.Job;
import com.example.demo.entity.PrintDevicesData;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PrintDevicesDataSpecification implements Specification<PrintDevicesData> {

    private final Job job;
    public PrintDevicesDataSpecification(Job job) {
        this.job = job;
    }

    @Override
    public Predicate toPredicate(Root<PrintDevicesData> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(job.getJobId() != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("jobId")), job.getJobId()));
        }
        if (job.getType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), job.getType()));
        }
        if (job.getUser() != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("user")), job.getUser().toUpperCase()));
        }
        if (job.getDevice() != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("device")), job.getDevice().toUpperCase()));
        }
        if (job.getAmount() != null) {
            predicates.add(criteriaBuilder.equal(root.get("amount"), job.getAmount()));
        }
        if (job.getTimeFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time"), job.getTimeFrom()));
        }
        if (job.getTimeTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("time"), job.getTimeTo()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
