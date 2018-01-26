package pojo;

import static org.junit.Assert.*;

public class BlockDataTest {
    @org.junit.Test
    public void getAddedStringNumber() throws Exception {
        BlockData blockData = new BlockData(10, 20);

        assertEquals(10, blockData.getAddedStringNumber().intValue());
    }

    @org.junit.Test
    public void getDeletedStringNumber() throws Exception {
        BlockData blockData = new BlockData(10, 20);

        assertEquals(20, blockData.getDeletedStringNumber().intValue());
    }

}