package link.sharedworld;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Loads LWJGL's tinyfd by reflection so the mod compiles even when the Minecraft
 * version being built against does not expose it on the compile classpath.
 */
public final class NativeFileDialogs {
    private static final String CLASS_NAME = "org.lwjgl.util.tinyfd.TinyFileDialogs";

    private NativeFileDialogs() {
    }

    /** Returns the chosen file path, or null if cancelled. Throws if native dialogs are unavailable. */
    public static String openFile(String title, String defaultPath, String pattern) throws IOException {
        try {
            Class<?> dialogs = Class.forName(CLASS_NAME);
            Method method = dialogs.getMethod("tinyfd_openFileDialog",
                    CharSequence.class, CharSequence.class, PointerBuffer.class, CharSequence.class, boolean.class);
            try (MemoryStack stack = MemoryStack.stackPush()) {
                PointerBuffer patterns = stack.mallocPointer(1);
                patterns.put(stack.UTF8(pattern));
                patterns.flip();
                return (String) method.invoke(null, title, defaultPath, patterns, null, false);
            }
        } catch (ReflectiveOperationException | LinkageError exception) {
            throw new IOException("Native file dialogs are not available in this Minecraft version.", exception);
        }
    }

    /** Returns the chosen folder path, or null if cancelled or unavailable. */
    public static String selectFolder(String title, String defaultPath) {
        try {
            Class<?> dialogs = Class.forName(CLASS_NAME);
            Method method = dialogs.getMethod("tinyfd_selectFolderDialog", CharSequence.class, CharSequence.class);
            return (String) method.invoke(null, title, defaultPath);
        } catch (ReflectiveOperationException | LinkageError exception) {
            return null;
        }
    }
}
