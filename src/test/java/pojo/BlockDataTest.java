package pojo;

import static org.junit.Assert.assertEquals;

public class BlockDataTest {
    @org.junit.Test
    public void getAddedStringNumber() {
        BlockData blockData = new BlockData(10, 20);

        assertEquals(10, blockData.getAddedStringNumber().intValue());
    }

    @org.junit.Test
    public void getDeletedStringNumber() {
        BlockData blockData = new BlockData(10, 20);

        assertEquals(20, blockData.getDeletedStringNumber().intValue());
    }

}