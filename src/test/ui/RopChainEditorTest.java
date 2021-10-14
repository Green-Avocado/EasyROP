package ui;

import model.Payload;
import model.RopChain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RopChainEditorTest {
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        ropChainEditor = new RopChainEditor(new RopChain(), new PayloadEditor(new Payload()));
    }

    @Test
    void testHandleInput() {
        // TODO
    }

    @Test
    void testReset() {
        // TODO
    }

    @Test
    void testSetName() {
        // TODO
    }

    @Test
    void testGetContext() {
        // TODO
    }

    @Test
    void testGetMenu() {
        // TODO
    }
}
