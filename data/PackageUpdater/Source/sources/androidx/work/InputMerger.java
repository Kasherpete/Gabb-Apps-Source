package androidx.work;

import java.util.List;

public abstract class InputMerger {
    private static final String TAG = Logger.tagWithPrefix("InputMerger");

    public abstract Data merge(List<Data> list);

    public static InputMerger fromClassName(String str) {
        try {
            return (InputMerger) Class.forName(str).newInstance();
        } catch (Exception e) {
            Logger.get().error(TAG, "Trouble instantiating + " + str, e);
            return null;
        }
    }
}
