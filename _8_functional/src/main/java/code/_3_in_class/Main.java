package code._3_in_class;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.Locale.filter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Student> students = getStudents();

        /*over8_iterative_with_for(students);
        System.out.println("-----------------");
        over8_iterative_with_iterator(students);
        System.out.println("-----------------");*/
        over8_iterative_with_threads(students);
        System.out.println("-----------------");

        over8_iterative_with_stream(students);
        System.out.println("-----------------");
        toti_studentii_au_nota_10(students);
        System.out.println("-----------------");
        exista_un_student_cu_nota_4(students);
    }

    private static void exista_un_student_cu_nota_4(List<Student> students) {
        boolean existaStudentCuNota4 = students.stream()
                .anyMatch(s -> s.getNota().equals(4));
        System.out.println("exista student cu nota 4: " + existaStudentCuNota4);
    }

    private static void toti_studentii_au_nota_10(List<Student> students) {
        long studentiDeNota10 = students.stream()
                .filter(s -> s.getNota().equals(10))
                .count();
        System.out.println("totii au 10:" + (studentiDeNota10 == students.size()));

        boolean totiAuNota10 = students.stream()
                .allMatch(s -> s.getNota().equals(10));
        System.out.println("toti au 10: " + totiAuNota10);
    }

    private static void over8_iterative_with_stream(List<Student> students) {
        students.parallelStream()
                .filter(student -> student.getNota() > 8)
                .forEach(x -> System.out.println(x));
    }

    private static void over8_iterative_with_threads(List<Student> students) throws InterruptedException {
        List<Student> s1 = students.subList(0, 3);
        List<Student> s2 = students.subList(3, 6);

        /*Spliterator<Student> spliterator = students.spliterator();
        Spliterator<Student> sp1 = spliterator.trySplit();
        Spliterator<Student> sp2 = spliterator.trySplit();
        Spliterator<Student> sp3 = spliterator.trySplit();*/

        Runnable r1 = new MyThread(s1);
        Runnable r2 = new MyThread(s2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void over8_iterative_with_iterator(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getNota() > 8) {
                System.out.println(student);
            }
        }
    }

    private static void over8_iterative_with_for(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getNota() > 8)
                System.out.println(student);
        }
    }

    private static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Popescu", 10));
        students.add(new Student("Ionescu", 9));
        students.add(new Student("Georgescu", 8));
        students.add(new Student("Petrescu", 7));
        students.add(new Student("Albulescu", 4));
        students.add(new Student("Dragomirescu", 10));
        return students;
    }

    public static class Student {
        private String nume;
        private Integer nota;

        public Student(String nume, int nota) {
            this.nume = nume;
            this.nota = nota;
        }

        public String getNume() {
            return nume;
        }

        public Integer getNota() {
            return nota;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public void setNota(Integer nota) {
            this.nota = nota;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "nume='" + nume + '\'' +
                    ", nota=" + nota +
                    '}';
        }


    }

    public static class MyThread implements Runnable {
        private List<Student> students;

        public MyThread(List<Student> students) {
            this.students = students;
        }

        @Override
        public void run() {
            over8_iterative_with_for(students);
        }
    }
}