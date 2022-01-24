package com.bmc.converter.services;

import com.bmc.converter.models.ConvDirection;
import com.bmc.converter.models.InputDataParams;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.RandomAccessFile;
import java.util.List;

@Service(ConvDirection.ATOE)
public class FileConverterImpATOE implements FileConverter {
    @Override
    @SneakyThrows
    public void convertInputFile(InputDataParams inputDataParams, List<Integer> conversionTable) {
        RandomAccessFile inputFile = new RandomAccessFile(inputDataParams.getSourceFullFileName(), "r");
        RandomAccessFile outputFile = new RandomAccessFile(inputDataParams.getDestFullFileName(), "rw");
        byte[] writeBytes = new byte[inputDataParams.getRecordLength()];
        Integer convertedValue;
        while (inputFile.getFilePointer() < inputFile.length()) {
            int read = inputFile.read();
            int bytesInLineCounter = 0;
            while (read != 10 && inputFile.getFilePointer() < inputFile.length()) {
                convertedValue = conversionTable.get(read);
                writeBytes[bytesInLineCounter] = convertedValue.byteValue();
                read = inputFile.read();
                bytesInLineCounter++;
            }
            for (int i = bytesInLineCounter; i < inputDataParams.getRecordLength(); i++) {
                writeBytes[i] = 64;// complete each line with empty character (@) till required length of line
            }
            outputFile.write(writeBytes);
        }
        inputFile.close();
        outputFile.close();
    }
}
