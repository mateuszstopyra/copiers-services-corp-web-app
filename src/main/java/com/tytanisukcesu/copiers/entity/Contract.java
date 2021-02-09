package com.tytanisukcesu.copiers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String contractNumber;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Device device;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal monoPagePrice;

    private BigDecimal colorPagePrice;

    private BigDecimal leasePrice;

    private Integer initialMonoCounter;

    private Integer initialColourCounter;

    @OneToMany(mappedBy = "contract")
    private Set<CopierSettlement> copierSettlementSet;

}
