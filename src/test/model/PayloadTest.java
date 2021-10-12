package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PayloadTest {
    private Payload payload;

    @BeforeEach
    void runBefore() {
        payload = new Payload();
    }

    @Test
    void addRopChainTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
        }

        // add to out of bounds index
        // TODO

        // add to empty
        // TODO

        // add to start of list
        // TODO

        // add to middle of list
        // TODO

        // add to end of list
        // TODO
    }

    @Test
    void removeRopChainTest() {
        // remove from out of bounds index
        // TODO

        // remove from start of list
        // TODO

        // remove from middle of list
        // TODO

        // remove from end of list
        // TODO

    }

    @Test
    void payloadScriptTest() {
        // TODO

    }

    @Test
    void setIsAmd64Test() {
        // TODO

    }

    @Test
    void getIsAmd64Test() {
        // TODO

    }

    @Test
    void getRopChainTest() {
        // TODO

        // get out of bounds index
        // TODO

    }

    @Test
    void getRopChainListTest() {
        // TODO

    }

    @Test
    void getPayloadLengthTest() {
        // TODO

    }
}
