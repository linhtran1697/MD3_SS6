import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User user;

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = inputInteger("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    inputUser();
                    break;

                case 2:
                    normalizeFullName();
                    break;

                case 3:
                    checkEmail();
                    break;

                case 4:
                    checkPhone();
                    break;

                case 5:
                    checkPassword();
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
                "**************** QUẢN LÝ NGƯỜI DÙNG ****************"
        );
        System.out.println("1. Nhập thông tin người dùng");
        System.out.println("2. Chuẩn hóa họ tên");
        System.out.println("3. Kiểm tra email hợp lệ");
        System.out.println("4. Kiểm tra số điện thoại hợp lệ");
        System.out.println("5. Kiểm tra mật khẩu hợp lệ");
        System.out.println("6. Thoát");
        System.out.println(
                "*****************************************************"
        );
    }

    // 1. Nhập thông tin người dùng
    public static void inputUser() {
        System.out.println("\n--- NHẬP THÔNG TIN NGƯỜI DÙNG ---");

        String fullName = inputNotEmpty("Nhập họ và tên: ");
        String email = inputNotEmpty("Nhập email: ");
        String phone = inputNotEmpty("Nhập số điện thoại: ");
        String password = inputNotEmpty("Nhập mật khẩu: ");

        user = new User(fullName, email, phone, password);

        System.out.println("Nhập thông tin người dùng thành công.");
        user.displayInfo();
    }

    // 2. Chuẩn hóa họ tên
    public static void normalizeFullName() {
        if (!hasUser()) {
            return;
        }

        String fullName = user.getFullName();

        // Xóa khoảng trắng thừa và chuyển về chữ thường
        String[] words = fullName.trim()
                .toLowerCase()
                .split("\\s+");

        StringBuilder normalizedName = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1)
                        .toUpperCase();

                String remainingLetters = word.substring(1);

                normalizedName
                        .append(firstLetter)
                        .append(remainingLetters)
                        .append(" ");
            }
        }

        String result = normalizedName.toString().trim();

        user.setFullName(result);

        System.out.println("Họ tên sau khi chuẩn hóa: " + result);
    }

    // 3. Kiểm tra email
    public static void checkEmail() {
        if (!hasUser()) {
            return;
        }

        String emailRegex =
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        boolean valid = Pattern.matches(
                emailRegex,
                user.getEmail()
        );

        if (valid) {
            System.out.println("Email hợp lệ.");
        } else {
            System.out.println("Email không hợp lệ.");
        }
    }

    // 4. Kiểm tra số điện thoại Việt Nam
    public static void checkPhone() {
        if (!hasUser()) {
            return;
        }

        /*
         * Số điện thoại:
         * - Bắt đầu bằng số 0
         * - Số tiếp theo thuộc nhóm 3, 5, 7, 8 hoặc 9
         * - Tổng cộng có 10 chữ số
         */
        String phoneRegex = "^0[35789][0-9]{8}$";

        boolean valid = Pattern.matches(
                phoneRegex,
                user.getPhone()
        );

        if (valid) {
            System.out.println("Số điện thoại hợp lệ.");
        } else {
            System.out.println(
                    "Số điện thoại không hợp lệ."
            );
        }
    }

    // 5. Kiểm tra mật khẩu
    public static void checkPassword() {
        if (!hasUser()) {
            return;
        }

        /*
         * Mật khẩu phải có:
         * - Ít nhất 8 ký tự
         * - Ít nhất 1 chữ thường
         * - Ít nhất 1 chữ hoa
         * - Ít nhất 1 chữ số
         * - Ít nhất 1 ký tự đặc biệt
         * - Không chứa khoảng trắng
         */
        String passwordRegex =
                "^(?=.*[a-z])" +
                        "(?=.*[A-Z])" +
                        "(?=.*[0-9])" +
                        "(?=.*[^A-Za-z0-9])" +
                        "\\S{8,}$";

        boolean valid = Pattern.matches(
                passwordRegex,
                user.getPassword()
        );

        if (valid) {
            System.out.println("Mật khẩu hợp lệ.");
        } else {
            System.out.println("Mật khẩu không hợp lệ.");
            System.out.println(
                    "Mật khẩu phải có ít nhất 8 ký tự, gồm:"
            );
            System.out.println("- Ít nhất một chữ thường");
            System.out.println("- Ít nhất một chữ hoa");
            System.out.println("- Ít nhất một chữ số");
            System.out.println("- Ít nhất một ký tự đặc biệt");
            System.out.println("- Không chứa khoảng trắng");
        }
    }

    // Kiểm tra đã nhập người dùng chưa
    public static boolean hasUser() {
        if (user == null) {
            System.out.println(
                    "Chưa có thông tin người dùng. Hãy chọn chức năng 1 trước."
            );
            return false;
        }

        return true;
    }

    // Nhập số nguyên cho menu
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

    // Nhập chuỗi không được để trống
    public static String inputNotEmpty(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Thông tin không được để trống."
            );
        }
    }
}