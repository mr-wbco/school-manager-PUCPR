package com.student;

import com.objects.Student;

import java.io.*;

public class StudentBO {

    public static void writeStudent(String fileName, Student student) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("pessoa.txt"));
            String string = student.toString(student);
            System.out.println(string);
            writer.write(student.toString(student));
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
