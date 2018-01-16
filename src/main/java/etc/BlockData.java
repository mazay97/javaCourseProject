package etc;

public class BlockData {
    private Integer mAddedStringBeginNumber;
    private Integer mDeletedStringBeginNumber;

    public BlockData(Integer addedStringNumber, Integer deletedStringNumber){
        mAddedStringBeginNumber = addedStringNumber;
        mDeletedStringBeginNumber = deletedStringNumber;
    }

    public Integer getAddedStringNumber(){
        return mAddedStringBeginNumber;
    }

    public Integer getDeletedStringNumber(){
        return mDeletedStringBeginNumber;
    }
}
