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

            this.processStudents(jsonObject.getJsonArray(DataVO.STUDENT_LIST), dataVO);
            this.processProfessors(jsonObject.getJsonArray(DataVO.PROFESSOR_LIST), dataVO);
            this.processSubjects(jsonObject.getJsonArray(DataVO.SUBJECT_LIST), dataVO);
            this.processClassrooms(jsonObject.getJsonArray(DataVO.CLASSROOM_LIST), dataVO);
            this.processEnrollings(jsonObject.getJsonArray(DataVO.ENROLLING_LIST), dataVO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataVO;
    }

    private void processStudents(JsonArray studentArray, DataVO dataVO) {
        for (JsonValue jsonValue : studentArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                Student student = this.processStudentObject((JsonObject) jsonValue);
                dataVO.getStudentList().add(student);
            }
        }
    }

    private Student processStudentObject(JsonObject studentObject) {
        if (studentObject == null) {
            return new Student();
        }

        int studentCode = studentObject.getInt(Person.CODE);
        String studentName = studentObject.getString(Person.NAME);
        int studentAge = studentObject.getInt(Person.AGE);
        long studentFederalIdentification = studentObject.getInt(Person.FEDERAL_IDENTIFICATION);
        return new Student(studentCode, studentName, studentAge, studentFederalIdentification);
    }

    private void processProfessors(JsonArray professorArray, DataVO dataVO) {
        for (JsonValue jsonValue : professorArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                Professor professor = this.processProfessorObject((JsonObject) jsonValue);
                dataVO.getProfessorList().add(professor);
            }
        }
    }

    private Professor processProfessorObject(JsonObject professorObject) {
        if (professorObject == null) {
            return new Professor();
        }

        int professorCode = professorObject.getInt(Person.CODE);
        String professorName = professorObject.getString(Person.NAME);
        int professorAge = professorObject.getInt(Person.AGE);
        long professorFederalIdentification = professorObject.getInt(Person.FEDERAL_IDENTIFICATION);
        return new Professor(professorCode, professorName, professorAge, professorFederalIdentification);
    }

    private void processSubjects(JsonArray subjectArray, DataVO dataVO) {
        for (JsonValue jsonValue : subjectArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                Subject subject = processSubjectObject((JsonObject) jsonValue);
                dataVO.getSubjectList().add(subject);
            }
        }
    }

    private Subject processSubjectObject(JsonObject subjectObject) {
        if (subjectObject == null) {
            return new Subject();
        }

        int subjectCode = subjectObject.getInt(Subject.SUBJECT_CODE);
        String subjectName = subjectObject.getString(Subject.SUBJECT_NAME);
        return new Subject(subjectCode, subjectName);
    }

    private void processClassrooms(JsonArray classroomArray, DataVO dataVO) {
        for (JsonValue jsonValue : classroomArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                Classroom classroom = processClassroomObject((JsonObject) jsonValue);
                dataVO.getClassroomList().add(classroom);
            }
        }
    }

    private Classroom processClassroomObject(JsonObject classroomObject) {
        if (classroomObject == null) {
            return new Classroom();
        }

        int classroomCode = classroomObject.getInt(Classroom.CLASSROOM_CODE);
        String classroomName = classroomObject.getString(Classroom.CLASSROOM_NAME);
        Subject subject = this.processSubjectObject(classroomObject.getJsonObject(Classroom.SUBJECT));
        Professor professor = this.processProfessorObject(classroomObject.getJsonObject(Classroom.PROFESSOR));

        DataVO dataVO = new DataVO();
        this.processStudents(classroomObject.getJsonArray(Classroom.STUDENT_LIST), dataVO);

        return new Classroom(classroomCode, classroomName, subject, professor, dataVO.getStudentList());
    }

    private void processEnrollings(JsonArray enrollingArray, DataVO dataVO) {
        for (JsonValue jsonValue : enrollingArray) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                Enrolling enrolling = this.processEnrollingObject((JsonObject) jsonValue);
                dataVO.getEnrollingList().add(enrolling);
            }
        }
    }

    private Enrolling processEnrollingObject(JsonObject enrollingObject) {
        if (enrollingObject == null) {
            return new Enrolling();
        }

        int enrollingCode = enrollingObject.getInt(Enrolling.ENROLLING_CODE);
        int studentCode = enrollingObject.getInt(Enrolling.STUDENT_CODE);
        String studentName = enrollingObject.getString(Enrolling.STUDENT_NAME);
        int classroomCode = enrollingObject.getInt(Enrolling.CLASSROOM_CODE);
        String classroomName = enrollingObject.getString(Enrolling.CLASSROOM_NAME);

        return new Enrolling(enrollingCode, studentCode, studentName, classroomCode, classroomName);
    }
}
