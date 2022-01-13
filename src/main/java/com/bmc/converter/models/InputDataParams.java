package com.bmc.converter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@AllArgsConstructor
@Data
public class InputDataParams {
    @NonNull
    private String sourceFullFileName;
    @NonNull
    private String destFullFileName;
    @NonNull
    private String conversionTableFullFileName;
    @NonNull
    private ConvDirection convDirection;
    @NonNull
    private Integer recordLength;

}
