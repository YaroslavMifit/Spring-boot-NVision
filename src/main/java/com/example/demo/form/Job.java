package com.example.demo.form;

import com.example.demo.entity.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@Accessors (chain = true)
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Job {

    public static final String DATE_PATTERN = "dd.MM.yyyy HH:mm";
    public static final String TIME_ZONE = "Asia/Yekaterinburg";

    @XmlAttribute(name = "id")
    private Long jobId;

    @XmlElement(name = "type")
    private Type type;

    @XmlElement(name = "user")
    private String user;

    @XmlElement(name = "device")
    private String device;

    @XmlElement(name = "amount")
    private Integer amount;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(pattern = DATE_PATTERN, timezone = TIME_ZONE)
    private Date timeFrom;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(pattern = DATE_PATTERN, timezone = TIME_ZONE)
    private Date timeTo;
}
