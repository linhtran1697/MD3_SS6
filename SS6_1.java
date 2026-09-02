import java.util.Arrays;
import java.util.Scanner;
public class SS6_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] danhSachDiem = null;
        int luaChon;

        do {
            System.out.println("\n************** QUẢN LÝ ĐIỂM SV **************");
            System.out.println("1. Nhập danh sách điểm sinh viên");
            System.out.println("2. In danh sách điểm");
            System.out.println("3. Tính điểm trung bình của các sinh viên");
            System.out.println("4. Tìm điểm cao nhất và thấp nhất");
            System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
            System.out.println("6. Sắp xếp điểm tăng dần");
            System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
            System.out.println("8. Thoát");
            System.out.println("**********************************************");
            System.out.print("Nhập lựa chọn của bạn: ");

            luaChon = scanner.nextInt();

            switch (luaChon) {
                case 1:
                    System.out.print("Nhập số lượng sinh viên: ");
                    int soLuong = scanner.nextInt();

                    while (soLuong <= 0) {
                        System.out.print("Số lượng phải lớn hơn 0. Nhập lại: ");
                        soLuong = scanner.nextInt();
                    }

                    danhSachDiem = new double[soLuong];

                    for (int i = 0; i < danhSachDiem.length; i++) {
                        do {
                            System.out.printf(
                                    "Nhập điểm sinh viên thứ %d: ",
                                    i + 1
                            );

                            danhSachDiem[i] = scanner.nextDouble();

                            if (danhSachDiem[i] < 0
                                    || danhSachDiem[i] > 10) {
                                System.out.println(
                                        "Điểm phải nằm trong khoảng từ 0 đến 10."
                                );
                            }
                        } while (danhSachDiem[i] < 0
                                || danhSachDiem[i] > 10);
                    }

                    System.out.println("Nhập danh sách điểm thành công.");
                    break;

                case 2:
                    if (danhSachDiem == null) {
                        System.out.println(
                                "Bạn chưa nhập danh sách điểm."
                        );
                        break;
                    }

                    System.out.println("Danh sách điểm sinh viên:");

                    for (int i = 0; i < danhSachDiem.length; i++) {
                        System.out.printf(
                                "Sinh viên %d: %.2f điểm%n",
                                i + 1,
                                danhSachDiem[i]
                        );
                    }
                    break;

                case 3:
                    if (danhSachDiem == null) {
                        System.out.println(
                                "Bạn chưa nhập danh sách điểm."
                        );
                        break;
                    }

                    double tongDiem = 0;

                    for (double diem : danhSachDiem) {
                        tongDiem += diem;
                    }

                    double diemTrungBinh =
                            tongDiem / danhSachDiem.length;

                    System.out.printf(
                            "Điểm trung bình của các sinh viên: %.2f%n",
                            diemTrungBinh
                    );
                    break;

                case 4:
                    if (danhSachDiem == null) {
                        System.out.println(
                                "Bạn chưa nhập danh sách điểm."
                        );
                        break;
                    }

                    double diemCaoNhat = danhSachDiem[0];
                    double diemThapNhat = danhSachDiem[0];

                    for (double diem : danhSachDiem) {
                        if (diem > diemCaoNhat) {
                            diemCaoNhat = diem;
                        }

                        if (diem < diemThapNhat) {
                            diemThapNhat = diem;
                        }
                    }

                    System.out.printf(
                            "Điểm cao nhất: %.2f%n",
                            diemCaoNhat
                    );
                    System.out.printf(
                            "Điểm thấp nhất: %.2f%n",
                            diemThapNhat
                    );
                    break;

                case 5:
                    if (danhSachDiem == null) {
                        System.out.println(
                                "Bạn chưa nhập danh sách điểm."
                        );
                        break;
                    }

                    int soSinhVienDat = 0;
                    int soSinhVienTruot = 0;

                    for (double diem : danhSachDiem) {
                        if (diem >= 5) {
                            soSinhVienDat++;
                        } else {
                            soSinhVienTruot++;
                        }
                    }

                    System.out.println(
                            "Số sinh viên đạt: " + soSinhVienDat
                    );
                    System.out.println(
                            "Số sinh viên trượt: " + soSinhVienTruot
                    );
                    break;

                case 6:
                    if (danhSachDiem == null) {
                        System.out.println(
                                "Bạn chưa nhập danh sách điểm."
                        );
                        break;
                    }

                    Arrays.sort(danhSachDiem);

                    System.out.println(
                            "Danh sách điểm sau khi sắp xếp tăng dần:"
                    );

                    for (double diem : danhSachDiem) {
                        System.out.printf("%.2f ", diem);
                    }

                    System.out.println();
                    break;

                case 7:
                    if (danhSachDiem == null) {
                        System.out.println(
                                "Bạn chưa nhập danh sách điểm."
                        );
                        break;
                    }

                    int soSinhVienGioiVaXuatSac = 0;

                    for (double diem : danhSachDiem) {
                        if (diem >= 8) {
                            soSinhVienGioiVaXuatSac++;
                        }
                    }

                    System.out.println(
                            "Số sinh viên giỏi và xuất sắc: "
                                    + soSinhVienGioiVaXuatSac
                    );
                    break;

                case 8:
                    System.out.println("Đã thoát chương trình.");
                    break;

                default:
                    System.out.println(
                            "Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 8."
                    );
            }
        } while (luaChon != 8);

        scanner.close();
    }
}

