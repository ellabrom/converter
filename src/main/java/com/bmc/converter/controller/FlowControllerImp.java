package com.bmc.converter.controller;

import com.bmc.converter.models.InputDataParams;
import com.bmc.converter.services.ConvTableLoader;
import com.bmc.converter.services.FileConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FlowControllerImp implements FlowController{
    @Autowired
    ConvTableLoader conversionTableLoader;

    @Autowired
    Map<String, FileConverter> fileConverterMap;

    @Override
    @SneakyThrows
    public void controlFlow(InputDataParams inputDataParams) {
        List<Integer> conversionTable = conversionTableLoader.loadConvTableToArray(inputDataParams);
        FileConverter fileConverter = fileConverterMap.get(inputDataParams.getConvDirection());
        if (fileConverter != null) {
            fileConverter.convertInputFile(inputDataParams, conversionTable);
        }
    }
}
