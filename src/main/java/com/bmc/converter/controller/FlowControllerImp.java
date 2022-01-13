package com.bmc.converter.controller;

import com.bmc.converter.models.InputDataParams;
import com.bmc.converter.services.ConvTableLoader;
import com.bmc.converter.services.FileConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.RandomAccessFile;
import java.util.List;

@Component
public class FlowControllerImp implements FlowController{
    @Autowired
    ConvTableLoader conversionTableLoader;
    @Autowired
    FileConverter fileConverter;
    @Override
    @SneakyThrows
    public void controlFlow(InputDataParams inputDataParams) {
        List<Integer> conversionTable = conversionTableLoader.loadConvTableToArray(inputDataParams);
        fileConverter.convertInputFile(inputDataParams, conversionTable);
    }
}
