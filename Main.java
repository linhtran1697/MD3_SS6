import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static double[] scores = new double[0];

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = inputInteger("Nhập lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    inputScores();
                    break;

                case 2:
                    displayScores();
                    break;

                case 3:
                    calculateAverage();
                    break;

                case 4:
                    findMaxAndMin();
                    break;

                case 5:
                    countPassAndFail();
                    break;

                case 6:
                    sortAscending();
                    break;

                case 7:
                    countGoodAndExcellent();
                    break;

                case 8:
                    System.out.println("Đã thoát chương trình.");
                    break;

                default:
                    System.out.println(
                            "Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 8."
                    );
            }
        } while (choice != 8);

        scanner.close();
    }

    // Hiển thị menu
    public static void displayMenu() {
        System.out.println();
        System.out.println("****************** QUẢN LÝ ĐIỂM SV ******************");
        System.out.println("1. Nhập danh sách điểm sinh viên");
        System.out.println("2. In danh sách điểm");
        System.out.println("3. Tính điểm trung bình của các sinh viên");
        System.out.println("4. Tìm điểm cao nhất và thấp nhất");
        System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
        System.out.println("6. Sắp xếp điểm tăng dần");
        System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
        System.out.println("8. Thoát");
        System.out.println("******************************************************");
    }

    // 1. Nhập danh sách điểm
    public static void inputScores() {
        System.out.println("\n--- NHẬP DANH SÁCH ĐIỂM ---");

        int numberOfStudents;

        do {
            numberOfStudents = inputInteger(
                    "Nhập số lượng sinh viên: "
            );

            if (numberOfStudents <= 0) {
                System.out.println(
                        "Số lượng sinh viên phải lớn hơn 0."
                );
            }
        } while (numberOfStudents <= 0);

        scores = new double[numberOfStudents];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = inputScore(
                    "Nhập điểm sinh viên thứ " + (i + 1) + ": "
            );
        }

        System.out.println("Nhập danh sách điểm thành công.");
    }

    // 2. In danh sách điểm
    public static void displayScores() {
        if (!hasScores()) {
            return;
        }

        System.out.println("\n--- DANH SÁCH ĐIỂM ---");

        for (int i = 0; i < scores.length; i++) {
            System.out.printf(
                    "Sinh viên thứ %d: %.2f%n",
                    i + 1,
                    scores[i]
            );
        }
    }

    // 3. Tính điểm trung bình
    public static void calculateAverage() {
        if (!hasScores()) {
            return;
        }

        double total = 0;

        for (double score : scores) {
            total += score;
        }

        double average = total / scores.length;

        System.out.printf(
                "Điểm trung bình của các sinh viên: %.2f%n",
                average
        );
    }

    // 4. Tìm điểm cao nhất và thấp nhất
    public static void findMaxAndMin() {
        if (!hasScores()) {
            return;
        }

        double max = scores[0];
        double min = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }

            if (scores[i] < min) {
                min = scores[i];
            }
        }

        System.out.printf("Điểm cao nhất: %.2f%n", max);
        System.out.printf("Điểm thấp nhất: %.2f%n", min);
    }

    // 5. Đếm sinh viên đạt và trượt
    public static void countPassAndFail() {
        if (!hasScores()) {
            return;
        }

        int passed = 0;
        int failed = 0;

        for (double score : scores) {
            if (score >= 5) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("Số sinh viên đạt: " + passed);
        System.out.println("Số sinh viên trượt: " + failed);
    }

    // 6. Sắp xếp điểm tăng dần
    public static void sortAscending() {
        if (!hasScores()) {
            return;
        }

        Arrays.sort(scores);

        System.out.println(
                "Đã sắp xếp danh sách điểm theo thứ tự tăng dần."
        );

        displayScores();
    }

    // 7. Thống kê sinh viên giỏi và xuất sắc
    public static void countGoodAndExcellent() {
        if (!hasScores()) {
            return;
        }

        int good = 0;
        int excellent = 0;

        for (double score : scores) {
            if (score >= 9) {
                excellent++;
            } else if (score >= 8) {
                good++;
            }
        }

        System.out.println(
                "Số sinh viên giỏi (từ 8 đến dưới 9): " + good
        );

        System.out.println(
                "Số sinh viên xuất sắc (từ 9 đến 10): " + excellent
        );

        System.out.println(
                "Tổng số sinh viên giỏi và xuất sắc: "
                        + (good + excellent)
        );
    }

    // Kiểm tra danh sách đã có điểm chưa
    public static boolean hasScores() {
        if (scores.length == 0) {
            System.out.println(
                    "Danh sách đang trống. Hãy chọn chức năng 1 trước."
            );
            return false;
        }

        return true;
    }

    // Nhập số nguyên và xử lý nhập sai
    public static int inputInteger(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Dữ liệu không hợp lệ. Vui lòng nhập số nguyên."
                );
            }
        }
    }

    // Nhập và kiểm tra điểm từ 0 đến 10
    public static double inputScore(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                double score = Double.parseDouble(input);

                if (score >= 0 && score <= 10) {
                    return score;
                }

                System.out.println(
                        "Điểm phải nằm trong khoảng từ 0 đến 10."
                );
            } catch (NumberFormatException e) {
                System.out.println(
                        "Điểm không hợp lệ. Vui lòng nhập lại."
                );
            }
        }
    }
}