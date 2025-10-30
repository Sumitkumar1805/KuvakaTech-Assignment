package com.sumit.Kuvaka.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.sumit.Kuvaka.model.Lead;

public class CsvUtil {

    public static List<Lead> parse(InputStream is) {
        List<Lead> leads = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
			 CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                Lead lead = new Lead();
                lead.setName(csvRecord.get("name"));
                lead.setEmail(csvRecord.get("email"));
                lead.setPhone(csvRecord.get("phone"));
                lead.setCompany(csvRecord.get("company"));
                leads.add(lead);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }

        return leads;
    }
}