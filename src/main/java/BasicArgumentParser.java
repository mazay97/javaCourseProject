import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BasicArgumentParser implements ArgumentParser {

    private final List<String> mExpectedFlags = new ArrayList<>();
    private final Map<String, String> mArguments = new HashMap<>();
    private final Pattern FLAG_REGEX = Pattern.compile("--[a-zA-Z]+");

    public void parse(String[] args) throws IllegalArgumentException {

        if (args.length == 0) {
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

    public void addFlag(final String flag) {
        if (FLAG_REGEX.matcher(flag).matches()) {
            mExpectedFlags.add(flag);
        } else {
            throw new IllegalArgumentException("Wrong format of flag");
        }
    }

    public String getValue(String flag) {
        return mArguments.get(flag);
    }
}
