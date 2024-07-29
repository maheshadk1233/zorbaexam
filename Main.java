package org.example;
import java.io.*;
import java.io.filenotfoundExecption;
import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;
import java.util.properties;
import java.sql.DriverManager;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
  try{
    Properties properties= new Properties();
    String filepath="jdbc:mysql://localhost:3306/?user=root";//databae usr

//
File file=new File(filepath);
FileInputStream fileInputStream= new FileInputStream(file);
properties.load(fileInputStream);
String url = (String) properties.get("url");
String username=(String) properties.get("username");
String password= (String) properties.get("password");
      Scanner scanner = new Scanner(System.in);
//load driver
      Class.forName("com.mysql.cj.jdbc.Driver");

//create connection to my mysql
      Connection connection= DriverManager.getConnection(url,username,password);
System.out.println("connection sucess");

//create the railwayresearvision in data base




      String RailwayReservationTable = "create table railway_reservation( passenger_name varchar(255),passenger_age int, choosen_seat varchar(255),reservation_type varchar(20), is_senior_citizen boolean,amount_paid double)";
      Statement statement = connection.createStatement();
      statement.execute(RailwayReservationTable);

      //Take input from the user and validate it.

      System.out.println("plese enter passenger name");
      String passengerName = scanner.nextLine();
      System.out.println("plese enter passenger age");
      int age = scanner.nextInt();
      System.out.println("plese enter passenger seat");
      String choosenseat = scanner.nextLine();
      System.out.println("plese enter passenger seat type ac/no ac");
      String typeofReservation = scanner.nextLine();
      System.out.println("plese enter amount paid");

      float amount = scanner.nextFloat();
      boolean isSeniorCitizen = age > 60;
      double amountpaid = calculateAmount(typeofReservation);

      //insert records into database

      String insertsql =" insert into RailwayReserationTable (passengerName, age,choosenseat,typeofReservation,isSeniorCitizen,amountpaid) values (?,?,?,?,?,?)";

      PreparedStatement preparedStatement = connection.prepareStatement((insertsql));
      preparedStatement.setString(1,passengerName);
      preparedStatement.setInt(2,age);
      preparedStatement.setString(3,choosenseat);
      preparedStatement.setString(4,typeofReservation);
      preparedStatement.setBoolean(5,isSeniorCitizen);
      preparedStatement.setDouble(6,amountpaid);

      //close the connection

      connection.close();
      scanner.close();
      System.out.println("record inserted....");

  } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());

  } catch (Exception e) {
      System.out.println(e.getMessage());
  }
    }

    private static double calculateAmount(String typeOfReservation) {
        if (typeOfReservation.equalsIgnoreCase("AC")) {
            return 100.0;
        } else {
            return 60.0;
        }
    }

    private static int readValidAge(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a valid integer.");
            }
        }


    }
}




































//    Write a program on Railway ticket reservation systems,
//    Read database url, username, password from properties file.
//            [Hints: consider a class RailwayReservastion, take attributes as ‘passengerName’,
//            ‘passengerAge’, ‘choosenSeat’, ‘typeOfReservasion’ (AC / Non-AC), ‘amountPaid’,
//            ‘isSeniorCitizen’.
//    Take input of passengerName, age, seat, and type, while taking input do proper
//    validation like age provided as integer not any other format like float or String and if age
//> 60 then
//    Consider passenger as senior citizen and populate ‘isSeniorCitizen’ as true assign
//    the lower berth/seat. And after taking all the input need to calculate the amount to be
//    paid for booking,
//            if ‘AC’ is the type of reservation then rate will be $100 and if non-AC the rate will
//    be $60.
//            1. Create one table using jdbc named: ‘railway_reservastion’ with column name as
//‘passenger_name’, ‘paggenger_age’, ‘choosen_seat’, ‘reservation_type’,
//            ‘is_senior_citizen’, ‘amount_paid’.
//            2. Once take input of passengerName, passengerAge, choosenSeat and
//    typeOfReservation, then please insert those records to database.
//3. Once calculated weather the passenger is senior citizen or not then update the same
//    under column name ‘is_senior_citizen’ in the already inserted row (in point 2) with 0
//            / 1 value (while true value to be saved 1 and 0 while value will be false)
//4. Once amount been calculated then same would be updated under ‘amount_paid’
//    column for the inserted row (in point 2).
//
//            ** Make sure you follow the steps mentioned above while inserting and updating the row.