package com.bmc.converter;

import com.bmc.converter.controller.FlowController;
import com.bmc.converter.models.ConvDirection;
import com.bmc.converter.models.InputDataParams;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConverterMain {
    @SneakyThrows
    public static void main(String[] args) {
        if (args.length == 5) {
            InputDataParams inputDataParams = InputDataParams.builder().sourceFullFileName(args[0]).destFullFileName(args[1])
                    .conversionTableFullFileName(args[2]).convDirection(args[3]).recordLength(Integer.valueOf(args[4])).build();
            ConfigurableApplicationContext context = SpringApplication.run(ConverterMain.class);
            context.getBean(FlowController.class).controlFlow(inputDataParams);
        }
        else {
            throw new Exception("Wrong number of parameters. Input is: "+args.length + " Expected is: 5. " );
        }
    }

}

