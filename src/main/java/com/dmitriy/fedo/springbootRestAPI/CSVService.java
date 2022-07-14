package com.dmitriy.fedo.springbootRestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    ListOfUniversitiesRepository repository;

    public void save(MultipartFile file) {
        try {
            List<ListOfUniversities> universities = CSVHelper.csvToDatabase(file.getInputStream());
            repository.saveAll(universities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<ListOfUniversities> universities = repository.findAll();

        ByteArrayInputStream in = CSVHelper.databaseToCSV(universities);
        return in;
    }

    public List<ListOfUniversities> getAllUniversities() {
        return repository.findAll();
    }
}
