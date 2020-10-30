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
@Table(name = "t_contract_hzc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,
        exclude = {"tenant","landlord","house"})


public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    // @JoinColumn默认是不需要的，因为Spring会根据你的属性名+对应实体的id推断出链接列
    @JoinColumn(name = "tenantId", referencedColumnName = "id")
    private User tenant;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    // @JoinColumn默认是不需要的，因为Spring会根据你的属性名+对应实体的id推断出链接列
    @JoinColumn(name = "landlordId", referencedColumnName = "id")
    private User landlord;
    @ManyToOne( cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "houseId", referencedColumnName = "id")
    private House house;
    @OneToOne( cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "applyId", referencedColumnName = "id")
    private Apply apply;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime contractCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime contractEndTime;
    private String contractContent;
    private String approveFromTenant;
    private String approveFromLandlord;
    private String flag;
}
