package com.minorProject.DigitalLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class returnTxnRequest {
    private String email;
    private String bookNo;
}
