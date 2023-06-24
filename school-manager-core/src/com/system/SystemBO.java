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

    public DataVO readData(String fileName) {
        DataVO dataVO = new DataVO();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            JsonReader jsonReader = Json.createReader(reader);
            JsonObject jsonObject = jsonReader.readObject();

            processStudents(jsonObject.getJsonArray(DataVO.STUDENT_LIST), dataVO);
            processProfessors(jsonObject.getJsonArray(DataVO.PROFESSOR_LIST), dataVO);
            processSubjects(jsonObject.getJsonArray(DataVO.SUBJECT_LIST), dataVO);
            processClassrooms(jsonObject.getJsonArray(DataVO.CLASSROOM_LIST), dataVO);
            processEnrollings(jsonObject.getJsonArray(DataVO.ENROLLING_LIST), dataVO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataVO;
    }

    private void processStudents(JsonArray studentArray, DataVO dataVO) {
        for (JsonValue jsonValue : studentArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject studentObject = (JsonObject) jsonValue;
                int studentCode = studentObject.getInt(Person.CODE);
                String studentName = studentObject.getString(Person.NAME);
                int studentAge = studentObject.getInt(Person.AGE);
                long studentFederalIdentification = studentObject.getInt(Person.FEDERAL_IDENTIFICATION);
                Student student = new Student(studentCode, studentName, studentAge, studentFederalIdentification);
                dataVO.getStudentList().add(student);
            }
        }
    }

    private void processProfessors(JsonArray professorArray, DataVO dataVO) {
        for (JsonValue jsonValue : professorArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject professorObject = (JsonObject) jsonValue;
                int professorCode = professorObject.getInt(Person.CODE);
                String professorName = professorObject.getString(Person.NAME);
                int professorAge = professorObject.getInt(Person.AGE);
                long professorFederalIdentification = professorObject.getInt(Person.FEDERAL_IDENTIFICATION);
                Professor professor = new Professor(professorCode, professorName, professorAge, professorFederalIdentification);
                dataVO.getProfessorList().add(professor);
            }
        }
    }

    private void processSubjects(JsonArray subjectArray, DataVO dataVO) {
        for (JsonValue jsonValue : subjectArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject subjectObject = (JsonObject) jsonValue;
                int subjectCode = subjectObject.getInt(Subject.SUBJECT_CODE);
                String subjectName = subjectObject.getString(Subject.SUBJECT_NAME);
                Subject subject = new Subject(subjectCode, subjectName);
                dataVO.getSubjectList().add(subject);
            }
        }
    }

    private void processClassrooms(JsonArray classroomArray, DataVO dataVO) {
        for (JsonValue jsonValue : classroomArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject classroomObject = (JsonObject) jsonValue;
                int classroomCode = classroomObject.getInt(Classroom.CLASSROOM_CODE);
                String classroomName = classroomObject.getString(Classroom.CLASSROOM_NAME);
                Classroom classroom = new Classroom(classroomCode, classroomName);
                dataVO.getClassroomList().add(classroom);
            }
        }
    }

    private void processEnrollings(JsonArray enrollingArray, DataVO dataVO) {
        for (JsonValue jsonValue : enrollingArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject enrollingObject = (JsonObject) jsonValue;
                int enrollingCode = enrollingObject.getInt(Enrolling.ENROLLING_CODE);
                String enrollingName = enrollingObject.getString(Enrolling.ENROLLING_NAME);
                Enrolling enrolling = new Enrolling(enrollingCode, enrollingName);
                dataVO.getEnrollingList().add(enrolling);
            }
        }
    }
}
