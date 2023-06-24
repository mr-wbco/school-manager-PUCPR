package com.professor;

import com.objects.Professor;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.*;

public class ProfessorBO {

    public static void writeProfessor(String fileName, Professor professor) {
        try {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("nome", professor.getName())
                    .add("idade", professor.getAge())
                    .build();

            BufferedWriter writer = new BufferedWriter(new FileWriter("pessoa.txt"));
            System.out.println(jsonObject.toString());
            writer.write(jsonObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readProfessor(String fileName) {
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
