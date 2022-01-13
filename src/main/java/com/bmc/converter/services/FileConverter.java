package com.bmc.converter.services;

import com.bmc.converter.models.InputDataParams;

import java.util.List;

public interface FileConverter {
    void convertInputFile(InputDataParams inputDataParams, List<Integer> conversionTable);
}
