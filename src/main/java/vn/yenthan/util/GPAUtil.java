package vn.yenthan.util;

public class GPAUtil {
    public static String classifyGPA(double gpa) {
        if (gpa < 0 || gpa > 10) {
            return "Invalid GPA";
        }
        if (gpa >= 8.5) {
            return "Giỏi";
        } else if (gpa >= 6.5) {
            return "Khá";
        } else if (gpa >= 5.0) {
            return "Yếu";
        } else {
            return "Cần cải thiện";
        }
    }
}
