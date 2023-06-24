package com.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.objects.DataVO;
import com.objects.Professor;
import com.objects.Student;

import javax.json.*;
import java.io.*;

public class SystemBO {

    public static final String DATA_JSON_FILE_NAME = "data.json";

    public void writeData(String fileName, DataVO dataVO) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataVO);

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(String fileName, DataVO dataVO) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            JsonReader jsonReader = Json.createReader(reader);
            JsonObject jsonObject = jsonReader.readObject();

            processStudents(jsonObject.getJsonArray("studentList"), dataVO);
            processProfessors(jsonObject.getJsonArray("professorList"), dataVO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processStudents(JsonArray studentArray, DataVO dataVO) {
        for (JsonValue jsonValue : studentArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject studentObject = (JsonObject) jsonValue;
                int studentCode = studentObject.getInt("code");
                String studentName = studentObject.getString("name");
                int studentAge = studentObject.getInt("age");
                long studentFederalIdentification = studentObject.getInt("federalIdentification");
                Student student = new Student(studentCode, studentName, studentAge, studentFederalIdentification);
                dataVO.getStudentList().add(student);
            }
        }
    }

    private void processProfessors(JsonArray professorArray, DataVO dataVO) {
        for (JsonValue jsonValue : professorArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject professorObject = (JsonObject) jsonValue;
                int professorCode = professorObject.getInt("code");
                String professorName = professorObject.getString("name");
                int professorAge = professorObject.getInt("age");
                long professorFederalIdentification = professorObject.getInt("federalIdentification");
                Professor professor = new Professor(professorCode, professorName, professorAge, professorFederalIdentification);
                dataVO.getProfessorList().add(professor);
            }
        }
    }
}
