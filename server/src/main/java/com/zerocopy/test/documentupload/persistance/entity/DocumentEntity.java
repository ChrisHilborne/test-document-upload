package com.zerocopy.test.documentupload.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DOCS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, columnDefinition = "VARCHAR (500)")
    private String name;

    @Column(name = "PAGES", nullable = false, columnDefinition = "NUMERIC")
    private Integer pages;

}
