package lotto.Util;

import java.util.function.Supplier;

public class RetryUtil {
    public static <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get(); // 성공하면 바로 반환
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 오류 메시지 출력
            }
        }
    }
}

