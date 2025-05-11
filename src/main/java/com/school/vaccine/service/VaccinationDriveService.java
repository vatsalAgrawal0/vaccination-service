package com.school.vaccine.service;

import com.school.vaccine.dto.DriveDto;
import com.school.vaccine.entity.VaccinationDrive;
import com.school.vaccine.repository.VaccinationDriveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationDriveService {

    private final VaccinationDriveRepository driveRepository;

    public VaccinationDrive createDrive(DriveDto dto) {
        validateDriveDate(dto.getDriveDate());
        validateOverlap(dto.getDriveDate(), dto.getApplicableGrades());

        VaccinationDrive drive = VaccinationDrive.builder()
                .vaccineName(dto.getVaccineName())
                .driveDate(dto.getDriveDate())
                .availableDoses(dto.getAvailableDoses())
                .applicableGrades(dto.getApplicableGrades())
                .build();

        return driveRepository.save(drive);
    }

    public VaccinationDrive updateDrive(Long id, DriveDto dto) {
        VaccinationDrive drive = driveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drive not found"));

        if (drive.getDriveDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Cannot edit past drives");
        }

        validateDriveDate(dto.getDriveDate());
        validateOverlap(dto.getDriveDate(), dto.getApplicableGrades(), id);

        drive.setVaccineName(dto.getVaccineName());
        drive.setDriveDate(dto.getDriveDate());
        drive.setAvailableDoses(dto.getAvailableDoses());
        drive.setApplicableGrades(dto.getApplicableGrades());

        return driveRepository.save(drive);
    }

    public List<VaccinationDrive> getAllDrives() {
        return driveRepository.findAll();
    }

    public List<VaccinationDrive> getUpcomingDrives() {
        LocalDate today = LocalDate.now();
        LocalDate next30Days = today.plusDays(30);
        return driveRepository.findByDriveDateBetween(today, next30Days);
    }

    private void validateDriveDate(LocalDate date) {
        if (date.isBefore(LocalDate.now().plusDays(15))) {
            throw new RuntimeException("Drives must be scheduled at least 15 days in advance");
        }
    }

    private void validateOverlap(LocalDate date, String gradeGroup) {
        List<VaccinationDrive> existing = driveRepository.findByDriveDateAndApplicableGrades(date, gradeGroup);
        if (!existing.isEmpty()) {
            throw new RuntimeException("Overlapping drive already scheduled for this grade on the same date");
        }
    }

    private void validateOverlap(LocalDate date, String gradeGroup, Long excludeId) {
        List<VaccinationDrive> existing = driveRepository.findByDriveDateAndApplicableGrades(date, gradeGroup);
        if (existing.stream().anyMatch(d -> !d.getId().equals(excludeId))) {
            throw new RuntimeException("Overlapping drive already scheduled for this grade on the same date");
        }
    }
}
