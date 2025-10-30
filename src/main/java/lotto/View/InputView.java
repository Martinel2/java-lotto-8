package lotto.View;

import camp.nextstep.edu.missionutils.Console;
import lotto.Util.InputParser;

import java.util.List;

public class InputView {
    private static final String PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final InputParser inputParser = new InputParser();

    private InputView() {
    }

    public static Integer readPrice() {
        System.out.println(PRICE_INPUT_MESSAGE);
        String input = Console.readLine();
        return inputParser.parsePrice(input);
    }

    public static List<Integer> readWinningNumber() {
        System.out.println(WINNING_NUMBERS_INPUT_MESSAGE);
        String input = Console.readLine();
        return inputParser.parseWinningNumbers(input);
    }

    public static int readBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        String input = Console.readLine();
        return inputParser.parseBonusNumber(input);
    }
}
