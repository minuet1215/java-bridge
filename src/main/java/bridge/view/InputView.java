package bridge.view;

import bridge.validator.Validator;
import bridge.validator.ValidateBridgeSize;
import bridge.validator.ValidateCommand;
import bridge.validator.ValidateMove;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    public final String ERROR_PREFIX = "[ERROR] ";
    public final String INPUT_BRIDGE_SIZE_MESSAGE = "다리의 길이를 입력해주세요.";
    public final String INPUT_MOVE_MESSAGE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    public final String INPUT_RETRY_OR_QUIT_MESSAGE = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    private static final InputView inputView = new InputView();

    private InputView() {
    }

    public static InputView getInputView() {
        return inputView;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        printInputMessage(INPUT_BRIDGE_SIZE_MESSAGE);
        return Integer.parseInt(getInputValue(new ValidateBridgeSize()));
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        printInputMessage(INPUT_MOVE_MESSAGE);
        return getInputValue(new ValidateMove());
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        printInputMessage(INPUT_RETRY_OR_QUIT_MESSAGE);
        return getInputValue(new ValidateCommand());
    }

    private String getInputValue(Validator validator) {
        String readValue = Console.readLine();
        while (true) {
            try {
                validator.validate(readValue);
                return readValue;
            } catch (IllegalArgumentException e) {
                printErrorMessage(e);
                readValue = Console.readLine();
            }
        }
    }

    private void printInputMessage(String message) {
        System.out.println(message);
    }

    private void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }
}
