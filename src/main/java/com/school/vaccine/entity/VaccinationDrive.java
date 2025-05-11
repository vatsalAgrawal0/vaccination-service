package com.school.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationDrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vaccineName;
    private LocalDate driveDate;
    private int availableDoses;
    private String applicableGrades;
}
