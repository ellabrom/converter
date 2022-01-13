package com.bmc.converter.services;

import com.bmc.converter.models.ConvDirection;
import com.bmc.converter.models.InputDataParams;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.RandomAccessFile;
import java.util.List;

@Service
public class FileConverterImp implements FileConverter {
    @Override
    @SneakyThrows
    public void convertInputFile(InputDataParams inputDataParams, List<Integer> conversionTable) {
        RandomAccessFile inputFile = new RandomAccessFile(inputDataParams.getSourceFullFileName(), "r");
        RandomAccessFile outputFile = new RandomAccessFile(inputDataParams.getDestFullFileName(), "rw");
        int readLength;
        int writeLength;
        if (inputDataParams.getConvDirection() == ConvDirection.ETOA){
            readLength = inputDataParams.getRecordLength();
            writeLength = inputDataParams.getRecordLength()+1;
        }
        else{
            readLength = inputDataParams.getRecordLength()+1;
            writeLength = inputDataParams.getRecordLength();
        }
        byte[] readBytes = new byte[readLength];
        byte[] writeBytes = new byte[writeLength];
        if (inputDataParams.getConvDirection() == ConvDirection.ETOA) {
            writeBytes[writeLength-1]='\n';
        }
        while (inputFile.getFilePointer() < inputFile.length()) {
            inputFile.read(readBytes);
            for (int i = 0; i < inputDataParams.getRecordLength(); i++) {
               int arrayLocation = Byte.toUnsignedInt(readBytes[i]) ;//  readBytes[i] & 0xFF;
                Integer convertedValue = conversionTable.get(arrayLocation);
                writeBytes[i] = convertedValue.byteValue();
            }
            outputFile.write(writeBytes);
        }
        inputFile.close();
        outputFile.close();
    }
}
