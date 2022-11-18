package bridge.model;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;

import java.util.List;

public class Bridge {
    BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    public String[][] bridge;

    public Bridge(int size) {
        List<String> bridge1D = bridgeMaker.makeBridge(size);
        this.bridge = bridgeMaker.make2DBridge(bridge1D);
    }

    public String[][] getBridge() {
        return this.bridge;
    }
}
