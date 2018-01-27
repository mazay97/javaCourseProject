package pojo;

public class BlockData {
    private final Integer mAddedStringBeginNumber;
    private final Integer mDeletedStringBeginNumber;

    public BlockData(Integer addedStringNumber, Integer deletedStringNumber) {
        mAddedStringBeginNumber = addedStringNumber;
        mDeletedStringBeginNumber = deletedStringNumber;
    }

    public Integer getAddedStringNumber() {
        return mAddedStringBeginNumber;
    }

    public Integer getDeletedStringNumber() {
        return mDeletedStringBeginNumber;
    }
}
