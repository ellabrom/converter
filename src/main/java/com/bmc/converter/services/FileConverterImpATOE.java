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
        byte[] readBytesBuff = new byte[inputDataParams.getRecordLength()];
        byte[] writeBytes = new byte[inputDataParams.getRecordLength()];
        Integer convertedValue;
        int bytesInWriteLineCounter = 0;
        while (inputFile.getFilePointer() < inputFile.length()) {
            int numberOfReadBytes = inputFile.read(readBytesBuff);
            for (int i = 0; i < numberOfReadBytes; i++) {
                //In case of end of line (/n)  - complete rest ot line with byte 64 and write down.
                if (readBytesBuff[i] == 10) {
                    for (int j = bytesInWriteLineCounter; j < inputDataParams.getRecordLength(); j++) {
                        writeBytes[j] = 64;// complete each line with empty character (@) till required length of line
                    }
                    outputFile.write(writeBytes);
                    bytesInWriteLineCounter = 0;
                } else {
                    convertedValue = conversionTable.get(Byte.toUnsignedInt(readBytesBuff[i]));
                    writeBytes[bytesInWriteLineCounter] = convertedValue.byteValue();
                    bytesInWriteLineCounter++;
                }
            }
        }
        inputFile.close();
        outputFile.close();
    }
}
