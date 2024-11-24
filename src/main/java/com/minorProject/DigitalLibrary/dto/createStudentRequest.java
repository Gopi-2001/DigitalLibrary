package com.minorProject.DigitalLibrary.dto;

import com.minorProject.DigitalLibrary.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class createStudentRequest {

    private String name;
    private String email;
    private String address;

    public Student toStudent() {
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .address(this.address)
                .build();
    }
}
