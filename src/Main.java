
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.temporal.ChronoUnit;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter client name: ");
        String clientName = scanner.nextLine();

        System.out.println("Enter meeting date and time (in format dd-MM-yyyy HH:mm): ");
        String meetingDateTimeStr = scanner.nextLine();

        System.out.println("Enter client's contact number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter client's email: ");
        String email = scanner.nextLine();

        System.out.println("Enter meeting topic: ");
        String meetingTopic = scanner.nextLine();

        String[] parts = clientName.split(" ");

        String firstName = parts[0];
        String secondName = parts[1];

        String firstName1 = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String secondName1 = secondName.substring(0, 1).toUpperCase() + secondName.substring(1).toLowerCase();


        System.out.println("Result: ");
        System.out.println("Client Name: " + firstName1 + " " + secondName1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime meetingDateTime;
        try
        {
            meetingDateTime = LocalDateTime.parse(meetingDateTimeStr, formatter);
            System.out.println("Meeting Date: " + meetingDateTime);
        } catch (DateTimeParseException e)
        {
            System.out.println("Error: Date and time must be in the format dd-MM-yyyy HH:mm");
            return;
        }

        String phoneNumberForm = phoneNumber.replaceAll("\\s+", "");
        System.out.println("Contact Number: " + phoneNumberForm);

        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
        {
            System.out.println("Email: " + email);
        } else
        {
            System.out.println("Email: Email is incorrect");
        }

        System.out.println("Meeting Topic: " + meetingTopic);

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(meetingDateTime))
        {
            System.out.println("The meeting has already concluded.");
        } else
        {
            long days = ChronoUnit.DAYS.between(now, meetingDateTime);
            long hours = ChronoUnit.HOURS.between(now.plusDays(days), meetingDateTime);
            long minutes = ChronoUnit.MINUTES.between(now.plusDays(days).plusHours(hours), meetingDateTime);
            System.out.println("Time until meeting: " + days + " days, " + hours + " hours, " + minutes + " minutes.");
        }

    }

}


