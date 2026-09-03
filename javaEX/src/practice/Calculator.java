package practice;

public class Calculator {
    double result = 0;

    // 더하기메소드  (double data)
    public double plus(double data) {
        return result += data;
    }

    // 빼기메소드 (double data)
    public double subtract(double data) {
        return result -= data;
    }

    // 곱하기메소드 (double data)
    public double multiple(double data) {
        return result *= data;
    }

    // 나누기메소드 (double data)
    public double divide(double data) {
        if (data == 0) {
            throw new ArithmeticException("분모는 0이 될 수 없습니다.");
        }
        return result /= data;
    }

    public double setResult(double data) {
        return this.result = data;
    }
}
