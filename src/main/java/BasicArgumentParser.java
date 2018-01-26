import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicArgumentParser implements ArgumentParser{

    private List<String> mExpectedFlags = new ArrayList<>();
    private Map<String, String> mArguments = new HashMap<>();
    private Pattern FLAG_REGEX = Pattern.compile("--[a-zA-Z]+");

    public void parse(String[] args) throws IllegalArgumentException{

        if (args.length == 0){
            throw new IllegalArgumentException("Wrong size of arguments");
        }

        boolean isFlag = true;
        String currentFlag = "";

        for (String arg : args) {

            if (isFlag) {
                currentFlag = arg;
                if (!mExpectedFlags.contains(currentFlag)) {
                    throw new IllegalArgumentException("Flag expected");
                }
                isFlag = false;
            } else {
                if (!mArguments.containsKey(currentFlag)) {
                    mArguments.put(currentFlag, arg);
                } else {
                    throw new IllegalArgumentException("Duplicate flags");
                }
                isFlag = true;
            }

        }

    }

    public void addFlag(final String flag){
        if (FLAG_REGEX.matcher(flag).matches()){
            mExpectedFlags.add(flag);
        } else {
            throw new IllegalArgumentException("Wrong format of flag");
        }
    }

    public String getValue(String flag){
        return mArguments.get(flag);
    }
}
