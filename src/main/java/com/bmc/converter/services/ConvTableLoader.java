package com.bmc.converter.services;

import com.bmc.converter.models.InputDataParams;

import java.util.List;

public interface ConvTableLoader {
    List<Integer> loadConvTableToArray(InputDataParams inpDataParams);
}
