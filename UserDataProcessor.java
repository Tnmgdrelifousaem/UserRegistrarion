import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataProcessor {

    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Ошибка: неверное количество данных. Введите Фамилию Имя Отчество дату_рождения номер_телефона пол.");
            return;
        }

        String surname = args[0];
        String name = args[1];
        String patronymic = args[2];
        String birthDate = args[3];
        String phoneNumber = args[4];
        String gender = args[5];

        try {
            validateDataFormat(birthDate, phoneNumber, gender);
            saveUserDataToFile(surname, name, patronymic, birthDate, phoneNumber, gender);
            System.out.println("Данные успешно записаны в файл " + surname + ".txt");
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e.getMessage());
            if (e instanceof IOException) {
                e.printStackTrace();
            }
        }
    }

    private static void validateDataFormat(String birthDate, String phoneNumber, String gender) {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Ошибка: неверный формат даты рождения.");
        }
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Ошибка: номер телефона должен быть целым числом.");
        }
        if (!gender.matches("[fm]")) {
            throw new IllegalArgumentException("Ошибка: пол должен быть 'f' или 'm'.");
        }
    }

    private static void saveUserDataToFile(String surname, String name, String patronymic,
                                           String birthDate, String phoneNumber, String gender) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true))) {
            writer.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
        }
    }
}
