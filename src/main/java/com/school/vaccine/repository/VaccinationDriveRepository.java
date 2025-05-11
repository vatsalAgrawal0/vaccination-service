package com.school.vaccine.repository;

import com.school.vaccine.entity.VaccinationDrive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationDriveRepository extends JpaRepository<VaccinationDrive, Long> {
    List<VaccinationDrive> findByDriveDateBetween(LocalDate start, LocalDate end);
    List<VaccinationDrive> findByDriveDateAndApplicableGrades(LocalDate date, String gradeGroup);
}
