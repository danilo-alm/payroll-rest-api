package com.danilo.payroll.domain.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Employee {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    private @NonNull String name;
    private @NonNull String role;
}
