package com.springbootbroilerstarter.demo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long driverId;

    private float amount;

    private Long customerId;

    @Column(columnDefinition = "int default '0'")
    private int status ;

    @Column(columnDefinition = "int default '0'")
    private long invoiceId = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


}
