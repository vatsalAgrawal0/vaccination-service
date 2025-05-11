package com.school.vaccine.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriveDto {
    private String vaccineName;
    private LocalDate driveDate;
    private int availableDoses;
    private String applicableGrades; // e.g., "5-7"
}
