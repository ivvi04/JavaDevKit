package ru.lakeevda.lesson4.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import ru.lakeevda.lesson4.entity.Employee;
import ru.lakeevda.lesson4.repository.EmployeeRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class FileService {

    private String path = "./data/";
    private String fileName;
    private String fileExtension = "json";
    private boolean withThrow = false;
    private ObjectMapper objectMapper;

    public FileService() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    private File getFile() throws FileNotFoundException {
        String fullFileName = this.path + this.fileName + "." + this.fileExtension;
        File file = new File(fullFileName);
        if (this.withThrow && !file.exists()) throw new FileNotFoundException("File " + fullFileName + " not found");
        return file;
    }

    public void FileReader() throws IOException {
        EmployeeReader();
    }

    public void EmployeeReader() throws IOException {
        this.setFileName(EmployeeRepository.class.getSimpleName());
        File file = getFile();
        if (file.exists()) {
            TypeReference<List<Employee>> typeReference = new TypeReference<>() {};
            List<Employee> repository = this.objectMapper.readValue(file, typeReference);
            EmployeeRepository.setEmployeeRepository(repository);

        }
    }

    public void FileWriter() throws IOException {
        EmployeeWriter();
    }

    public void EmployeeWriter() throws IOException {
        this.setFileName(EmployeeRepository.class.getSimpleName());
        File file = getFile();
        this.objectMapper.writeValue(file, EmployeeRepository.getEmployeeRepository());
    }
}
