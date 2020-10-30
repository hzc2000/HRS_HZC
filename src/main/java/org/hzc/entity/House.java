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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_house_hzc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,
        exclude = {"community","landlord"})

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "communityId", referencedColumnName = "id")
    private Community community;
    @ManyToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    // @JoinColumn默认是不需要的，因为Spring会根据你的属性名+对应实体的id推断出链接列
    @JoinColumn(name = "landlordId", referencedColumnName = "id")
    private User landlord;
    /*
 flag 表示 此房屋在出租状态
     */
    private String flag;
    private String title;
    private String houseAddress;
    private String houseIntroduction;
    private BigDecimal monthlyRent;
    private Double  area;
    private String  unitType;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastEditTime;



}
