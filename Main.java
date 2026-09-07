import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static List<String> licensePlates = new ArrayList<>();

    // Định dạng biển số, ví dụ: 30F-123.45
    static final String LICENSE_PLATE_REGEX =
            "^[0-9]{2}[A-Z]-[0-9]{3}\\.[0-9]{2}$";

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = inputInteger("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    addLicensePlates();
                    break;

                case 2:
                    displayLicensePlates();
                    break;

                case 3:
                    searchExactLicensePlate();
                    break;

                case 4:
                    searchByProvinceCode();
                    break;

                case 5:
                    sortLicensePlates();
                    break;

                case 6:
                    System.out.println("Đã thoát chương trình.");
                    break;

                default:
                    System.out.println(
                            "Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 6."
                    );
            }
        } while (choice != 6);

        scanner.close();
    }

    // Hiển thị menu
    public static void displayMenu() {
        System.out.println();
        System.out.println(
                "**************** QUẢN LÝ BIỂN SỐ XE ****************"
        );
        System.out.println("1. Thêm các biển số xe");
        System.out.println("2. Hiển thị danh sách biển số xe");
        System.out.println("3. Tìm kiếm biển số xe");
        System.out.println("4. Tìm biển số xe theo mã tỉnh");
        System.out.println("5. Sắp xếp biển số xe tăng dần");
        System.out.println("6. Thoát");
        System.out.println(
                "*****************************************************"
        );
    }

    // 1. Thêm các biển số xe
    public static void addLicensePlates() {
        System.out.println("\n--- THÊM BIỂN SỐ XE ---");

        int quantity;

        do {
            quantity = inputInteger(
                    "Nhập số lượng biển số muốn thêm: "
            );

            if (quantity <= 0) {
                System.out.println(
                        "Số lượng phải lớn hơn 0."
                );
            }
        } while (quantity <= 0);

        for (int i = 0; i < quantity; i++) {
            while (true) {
                System.out.print(
                        "Nhập biển số xe thứ " + (i + 1) + ": "
                );

                String licensePlate = scanner.nextLine()
                        .trim()
                        .toUpperCase();

                if (!isValidLicensePlate(licensePlate)) {
                    System.out.println(
                            "Biển số không đúng định dạng."
                    );
                    System.out.println(
                            "Định dạng đúng, ví dụ: 30F-123.45"
                    );
                    continue;
                }

                if (licensePlates.contains(licensePlate)) {
                    System.out.println(
                            "Biển số xe này đã tồn tại."
                    );
                    continue;
                }

                licensePlates.add(licensePlate);
                System.out.println("Thêm biển số thành công.");
                break;
            }
        }
    }

    // 2. Hiển thị danh sách biển số xe
    public static void displayLicensePlates() {
        System.out.println("\n--- DANH SÁCH BIỂN SỐ XE ---");

        if (!hasLicensePlates()) {
            return;
        }

        for (int i = 0; i < licensePlates.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + licensePlates.get(i)
            );
        }
    }

    // 3. Tìm kiếm chính xác theo biển số xe
    public static void searchExactLicensePlate() {
        System.out.println("\n--- TÌM KIẾM BIỂN SỐ XE ---");

        if (!hasLicensePlates()) {
            return;
        }

        System.out.print("Nhập biển số xe cần tìm: ");

        String searchPlate = scanner.nextLine()
                .trim()
                .toUpperCase();

        if (!isValidLicensePlate(searchPlate)) {
            System.out.println(
                    "Biển số không đúng định dạng 30F-123.45."
            );
            return;
        }

        if (licensePlates.contains(searchPlate)) {
            System.out.println(
                    "Đã tìm thấy biển số xe: " + searchPlate
            );
        } else {
            System.out.println(
                    "Không tìm thấy biển số xe: " + searchPlate
            );
        }
    }

    // 4. Tìm biển số xe theo mã tỉnh
    public static void searchByProvinceCode() {
        System.out.println("\n--- TÌM THEO MÃ TỈNH ---");

        if (!hasLicensePlates()) {
            return;
        }

        String provinceCode;

        while (true) {
            System.out.print(
                    "Nhập mã tỉnh gồm 2 chữ số, ví dụ 29 hoặc 30: "
            );

            provinceCode = scanner.nextLine().trim();

            if (provinceCode.matches("^[0-9]{2}$")) {
                break;
            }

            System.out.println(
                    "Mã tỉnh phải bao gồm đúng 2 chữ số."
            );
        }

        boolean found = false;

        System.out.println(
                "Các biển số xe có mã tỉnh " + provinceCode + ":"
        );

        for (String licensePlate : licensePlates) {
            if (licensePlate.startsWith(provinceCode)) {
                System.out.println("- " + licensePlate);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "Không tìm thấy biển số xe thuộc mã tỉnh "
                            + provinceCode + "."
            );
        }
    }

    // 5. Sắp xếp biển số xe tăng dần
    public static void sortLicensePlates() {
        System.out.println("\n--- SẮP XẾP BIỂN SỐ XE ---");

        if (!hasLicensePlates()) {
            return;
        }

        Collections.sort(licensePlates);

        System.out.println(
                "Đã sắp xếp biển số xe theo thứ tự tăng dần."
        );

        displayLicensePlates();
    }

    // Kiểm tra định dạng biển số xe
    public static boolean isValidLicensePlate(
            String licensePlate
    ) {
        return Pattern.matches(
                LICENSE_PLATE_REGEX,
                licensePlate
        );
    }

    // Kiểm tra danh sách có dữ liệu hay chưa
    public static boolean hasLicensePlates() {
        if (licensePlates.isEmpty()) {
            System.out.println(
                    "Danh sách đang trống. Hãy chọn chức năng 1 trước."
            );
            return false;
        }

        return true;
    }

    // Nhập và kiểm tra số nguyên
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
}