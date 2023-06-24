package com.system;

import com.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.objects.DataVO;

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
            processSubjects(jsonObject.getJsonArray("subjectList"), dataVO);
            processClassrooms(jsonObject.getJsonArray("classroomList"), dataVO);
            processEnrollings(jsonObject.getJsonArray("enrollingList"), dataVO);
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

    private void processSubjects(JsonArray subjectArray, DataVO dataVO) {
        for (JsonValue jsonValue : subjectArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject subjectObject = (JsonObject) jsonValue;
                int subjectCode = subjectObject.getInt("subjectCode");
                String subjectName = subjectObject.getString("subjectName");
                Subject subject = new Subject(subjectCode, subjectName);
                dataVO.getSubjectList().add(subject);
            }
        }
    }

    private void processClassrooms(JsonArray classroomArray, DataVO dataVO) {
        for (JsonValue jsonValue : classroomArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject classroomObject = (JsonObject) jsonValue;
                int classroomCode = classroomObject.getInt("classroomCode");
                String classroomName = classroomObject.getString("classroomName");
                Classroom classroom = new Classroom(classroomCode, classroomName);
                dataVO.getClassroomList().add(classroom);
            }
        }
    }

    private void processEnrollings(JsonArray enrollingArray, DataVO dataVO) {
        for (JsonValue jsonValue : enrollingArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject enrollingObject = (JsonObject) jsonValue;
                int enrollingCode = enrollingObject.getInt("enrollingCode");
                String enrollingName = enrollingObject.getString("enrollingName");
                Enrolling enrolling = new Enrolling(enrollingCode, enrollingName);
                dataVO.getEnrollingList().add(enrolling);
            }
        }
    }
}
