package com.student;

import com.objects.Student;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.*;

public class StudentBO {

    public static void writeStudent(String fileName, Student student) {
        try {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("nome", student.getName())
                    .add("idade", student.getAge())
                    .build();

            BufferedWriter writer = new BufferedWriter(new FileWriter("pessoa.txt"));
            System.out.println(jsonObject.toString());
            writer.write(jsonObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readStudent(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("pessoa.txt"));
            String json = reader.readLine();
            reader.close();
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
