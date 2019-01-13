package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "print_devices_data")
@JsonIgnoreProperties(ignoreUnknown = false)
public class PrintDevicesData {

    public static final String DATE_PATTERN = "dd.MM.yyyy HH:mm";
    public static final String TIME_ZONE = "Asia/Yekaterinburg";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore(value = true)
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "device")
    private String device;

    @Column(name = "user")
    private String user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "amount")
    private Integer amount;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @Column(name = "time")
    @JsonFormat(pattern = DATE_PATTERN, timezone = TIME_ZONE)
    private Date time;
}
