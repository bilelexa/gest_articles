package com.exa.gestarticles.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Entity
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String designation;
    @DecimalMin(value = "0")
    private Double prix;
}
