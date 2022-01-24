package com.bmc.converter.services;

import com.bmc.converter.models.ConvDirection;
import com.bmc.converter.models.InputDataParams;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.RandomAccessFile;
import java.util.List;

@Service(ConvDirection.ETOA)
public class FileConverterImpETOA implements FileConverter {
    @Override
    @SneakyThrows
    public void convertInputFile(InputDataParams inputDataParams, List<Integer> conversionTable) {
        RandomAccessFile inputFile = new RandomAccessFile(inputDataParams.getSourceFullFileName(), "r");
        RandomAccessFile outputFile = new RandomAccessFile(inputDataParams.getDestFullFileName(), "rw");
        int readLength = inputDataParams.getRecordLength();
        int writeLength = inputDataParams.getRecordLength() + 1;
        byte[] readBytes = new byte[readLength];
        byte[] writeBytes = new byte[writeLength];
        writeBytes[writeLength - 1] = '\n'; // File in EBCDIC doesn't have end of line character, so it is added here.

        while (inputFile.getFilePointer() < inputFile.length()) {
            inputFile.read(readBytes);
            for (int i = 0; i < inputDataParams.getRecordLength(); i++) {
                int arrayLocation = Byte.toUnsignedInt(readBytes[i]);
                Integer convertedValue = conversionTable.get(arrayLocation);
                writeBytes[i] = convertedValue.byteValue();
            }
            outputFile.write(writeBytes);
        }
        inputFile.close();
        outputFile.close();
    }
}

