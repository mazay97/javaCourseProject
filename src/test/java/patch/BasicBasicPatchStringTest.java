package patch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BasicBasicPatchStringTest {
    @Test
    public void getStatus() {
        BasicPatchString patchString1 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString2 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString3 = new BasicPatchString(patchString2);

        assertEquals(patchString1.getStatus(), patchString2.getStatus());
        assertEquals(patchString1.getStatus(), patchString3.getStatus());
    }

    @Test
    public void getValue() {
        BasicPatchString patchString1 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString2 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString3 = new BasicPatchString(patchString2);

        assertEquals(patchString1.getValue(), patchString2.getValue());
        assertEquals(patchString1.getValue(), patchString3.getValue());
    }

    @Test
    public void getDeletedStringNumber() {
        BasicPatchString patchString1 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString2 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString3 = new BasicPatchString(patchString2);

        assertEquals(patchString1.getDeletedStringNumber(), patchString2.getDeletedStringNumber());
        assertEquals(patchString1.getDeletedStringNumber(), patchString3.getDeletedStringNumber());
    }

    @Test
    public void setDeletedStringNumber() {
        BasicPatchString patchString1 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString2 = new BasicPatchString(1, '+', "123");

        patchString1.setDeletedStringNumber(123);
        assertNotEquals(patchString1.getDeletedStringNumber(), patchString2.getDeletedStringNumber());
    }

    @Test
    public void setAddedStringNumber() {
        BasicPatchString patchString1 = new BasicPatchString(1, '+', "123");
        BasicPatchString patchString2 = new BasicPatchString(1, '+', "123");

        patchString1.setAddedStringNumber(123);
        patchString1.setAddedStringNumber(1234);
        assertNotEquals(patchString1.getAddedStringNumber(), patchString2.getAddedStringNumber());
    }

}