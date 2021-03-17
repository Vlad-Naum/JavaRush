package zadachi.crud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CRUD {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {

        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //args= new String[]{"-c", "Иванов", "м", "04/01/1990", "Иванова", "ж", "18/05/1999"};
        //args= new String[]{"-u", "0","Петров", "м", "04/01/1991", "1","Петрова", "ж", "18/05/1995"};
        //args= new String[]{"-d", "0","1"};
        //args= new String[]{"-i", "0","1"};

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 3) {
                        if (args[i + 1].equals("м")) {
                            allPeople.add(Person.createMale(args[i], sdf.parse((args[i + 2]))));
                            System.out.println(allPeople.size()-1);
                        } else {
                            allPeople.add(Person.createFemale(args[i], sdf.parse((args[i + 2]))));
                            System.out.println(allPeople.size()-1);
                        }
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 4) {
                        allPeople.get(Integer.parseInt(args[i])).setName(args[i + 1]);
                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(sdf.parse((args[i + 3])));
                        if (args[i + 2].equals("м")) {
                            allPeople.get(Integer.parseInt(args[i])).setSex(Sex.MALE);
                        } else {
                            allPeople.get(Integer.parseInt(args[i])).setSex(Sex.FEMALE);
                        }
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        allPeople.get(Integer.parseInt(args[i])).setName(null);
                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(null);
                        allPeople.get(Integer.parseInt(args[i])).setSex(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        String name = allPeople.get(Integer.parseInt(args[i])).getName();
                        SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        Date date = allPeople.get(Integer.parseInt(args[i])).getBirthDate();
                        String sex = "";
                        if (allPeople.get(Integer.parseInt(args[i])).getSex() == Sex.MALE) {
                            sex = "м";
                        } else {
                            sex = "ж";
                        }
                        System.out.print(name + " " + sex + " " + sd.format(date));
                        System.out.println();
                    }
                }
                break;
        }
    }
}
