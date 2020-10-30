package org.hzc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_comment_hzc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,
        exclude = {"tenant","landlord","house"})

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "tenantId", referencedColumnName = "id")
    private User tenant;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "contractId", referencedColumnName = "id")
    private Contract contract;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "landlordId", referencedColumnName = "id")
    private User landlord;

    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "houseId", referencedColumnName = "id")
    private House house;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tenantContentCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime landlordContentCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime houseContentCreateTime;

    private String tenantContent;
    private String landlordContent;
    private String houseContent;

}
