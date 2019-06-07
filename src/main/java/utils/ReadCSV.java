package utils;

import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

    public static List<User> readFileStudent(BufferedReader br) {
        List<User> listStudent = new ArrayList<User>();
        try {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                String[] items = line.split(",");
                User student = new User();
                student.setUsername(items[0]);
                student.setPassword(items[1]);
                student.setFullname(items[2]);
                listStudent.add(student);
                line = br.readLine();
            }
            return listStudent;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
