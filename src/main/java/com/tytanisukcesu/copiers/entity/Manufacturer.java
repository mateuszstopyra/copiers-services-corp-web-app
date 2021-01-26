package com.tytanisukcesu.copiers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manufacturers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Model> models;

    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Article> articles;

}
