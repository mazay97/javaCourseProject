package pojo;

public class BlockData {
    private Integer mAddedStringBeginNumber;
    private Integer mDeletedStringBeginNumber;

    public BlockData(Integer addedStringBeginNumber, Integer deletedStringBeginNumber){
        mAddedStringBeginNumber = addedStringBeginNumber;
        mDeletedStringBeginNumber = deletedStringBeginNumber;
    }

    public Integer getAddedStringNumber(){
        return mAddedStringBeginNumber;
    }

    public Integer getDeletedStringNumber(){
        return mDeletedStringBeginNumber;
    }
}
