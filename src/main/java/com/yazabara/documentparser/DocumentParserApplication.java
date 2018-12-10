package com.yazabara.documentparser;

import com.yazabara.documentparser.secret.SecretRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DocumentParserApplication implements CommandLineRunner {

    private final SecretRunner reader;

    public DocumentParserApplication(SecretRunner reader) {
        this.reader = reader;
    }

    public static void main(String[] args) {
        SpringApplication.run(DocumentParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        reader.run();
    }
}
