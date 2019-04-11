package com.jazzteam.martynchyk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Mapper {
    private ObjectMapper jsonMapper;

    public Mapper() {
        jsonMapper = new ObjectMapper();
    }

    public String objectToString(Object obj) {
        String objectInString = obj.getClass().getName();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(objectInString);
            stringBuilder.append("\n");
            stringBuilder.append(jsonMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.getStackTrace();
        }
        return stringBuilder.toString();
    }

    public Object stringToObject(String objectInString) {
        String[] parts = objectInString.split("\n");
        try {
            return jsonMapper.readValue(parts[1], Class.forName(parts[0]));
        } catch (ClassNotFoundException | IOException exc) {
            return null;
        }
    }

    public Object fileToObject(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public void objectToFile(Object object, String fileName) {
        String objectInString = objectToString(object);
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(objectInString);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
