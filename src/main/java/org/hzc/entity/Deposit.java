package org.hzc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_deposit_hzc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,
        exclude = {"administrator","tenant"})
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "administratorId", referencedColumnName = "id")
    private User administrator;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "tenantId", referencedColumnName = "id")
    private User tenant;
    @OneToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "landlordId", referencedColumnName = "id")
    private User landlord;
    private BigDecimal tenantDeposit;
    private BigDecimal landlordDeposit;
}
