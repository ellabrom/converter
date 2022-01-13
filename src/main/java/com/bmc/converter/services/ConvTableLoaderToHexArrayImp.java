package com.bmc.converter.services;

import com.bmc.converter.models.InputDataParams;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConvTableLoaderToHexArrayImp implements ConvTableLoader{
    @Override
    @SneakyThrows
    public List<Integer> loadConvTableToArray(InputDataParams inpDataParams) {
        BufferedReader reader = new BufferedReader(new FileReader(inpDataParams.getConversionTableFullFileName()));
        String line;
        List<Integer> convTable = new ArrayList<>();
        int i=0;
        while ((line = reader.readLine()) != null){
            String[] parts = line.split("0x", 2);
            if (parts.length >= 2){
                convTable.add(i, Integer.parseInt(parts[1],16));
                i++;
            }
            else{
                reader.close();
                throw new Exception("Conversion table " + inpDataParams.getConversionTableFullFileName() + " has wrong value at line " + i );
            }
        }
        reader.close();
        return convTable;
    }
}
