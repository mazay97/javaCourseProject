package etc;

public class BlockData {
    Integer addedStringBeginNumber;
    Integer deletedStringBeginNumber;

    public BlockData(Integer addedStringNumber, Integer deletedStringNumber){
        addedStringBeginNumber = addedStringNumber;
        deletedStringBeginNumber = deletedStringNumber;
    }

    public Integer getAddedStringNumber(){
        return addedStringBeginNumber;
    }

    public Integer getDeletedStringNumber(){
        return deletedStringBeginNumber;
    }
}
