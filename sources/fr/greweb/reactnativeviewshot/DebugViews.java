package fr.greweb.reactnativeviewshot;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Locale;
import java.util.Stack;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/DebugViews.class */
public final class DebugViews {
    public static final Matrix EMPTY_MATRIX = new Matrix();
    public static final int LOG_MSG_LIMIT = 200;

    @NonNull
    @TargetApi(21)
    private static String dumpProperties(@NonNull Resources resources, @NonNull View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("id=");
        sb.append(view.getId());
        sb.append(resolveIdToName(resources, view));
        int visibility = view.getVisibility();
        if (visibility == 0) {
            sb.append(", V--");
        } else if (visibility == 4) {
            sb.append(", -I-");
        } else if (visibility != 8) {
            sb.append(", ---");
        } else {
            sb.append(", --G");
        }
        if (!view.getMatrix().equals(EMPTY_MATRIX)) {
            sb.append(", ");
            sb.append("matrix=");
            sb.append(view.getMatrix().toShortString());
            if (0.0f != view.getRotation() || 0.0f != view.getRotationX() || 0.0f != view.getRotationY()) {
                sb.append(", rotate=[");
                sb.append(view.getRotation());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(view.getRotationX());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(view.getRotationY());
                sb.append("]");
                if (view.getWidth() / 2 != view.getPivotX() || view.getHeight() / 2 != view.getPivotY()) {
                    sb.append(", pivot=[");
                    sb.append(view.getPivotX());
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(view.getPivotY());
                    sb.append("]");
                }
            }
            if (0.0f != view.getTranslationX() || 0.0f != view.getTranslationY() || 0.0f != view.getTranslationZ()) {
                sb.append(", translate=[");
                sb.append(view.getTranslationX());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(view.getTranslationY());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(view.getTranslationZ());
                sb.append("]");
            }
            if (1.0f != view.getScaleX() || 1.0f != view.getScaleY()) {
                sb.append(", scale=[");
                sb.append(view.getScaleX());
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(view.getScaleY());
                sb.append("]");
            }
        }
        if (view.getPaddingStart() != 0 || view.getPaddingTop() != 0 || view.getPaddingEnd() != 0 || view.getPaddingBottom() != 0) {
            sb.append(", ");
            sb.append("padding=[");
            sb.append(view.getPaddingStart());
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            sb.append(view.getPaddingTop());
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            sb.append(view.getPaddingEnd());
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            sb.append(view.getPaddingBottom());
            sb.append("]");
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (marginLayoutParams.leftMargin != 0 || marginLayoutParams.topMargin != 0 || marginLayoutParams.rightMargin != 0 || marginLayoutParams.bottomMargin != 0) {
                sb.append(", ");
                sb.append("margin=[");
                sb.append(marginLayoutParams.leftMargin);
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(marginLayoutParams.topMargin);
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(marginLayoutParams.rightMargin);
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                sb.append(marginLayoutParams.bottomMargin);
                sb.append("]");
            }
        }
        sb.append(", position=[");
        sb.append(view.getLeft());
        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        sb.append(view.getTop());
        sb.append("]");
        sb.append(", size=[");
        sb.append(view.getWidth());
        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        sb.append(view.getHeight());
        sb.append("]");
        if (view instanceof TextView) {
            sb.append(", text=\"");
            sb.append(((TextView) view).getText());
            sb.append("\"");
        }
        return sb.toString();
    }

    @NonNull
    public static String logViewHierarchy(@NonNull Activity activity) {
        View findViewById = activity.findViewById(android.R.id.content);
        if (findViewById != null) {
            return logViewHierarchy(findViewById);
        }
        return "Activity [" + activity.getClass().getSimpleName() + "] is not initialized yet. ";
    }

    @NonNull
    private static String logViewHierarchy(@NonNull View view) {
        StringBuilder sb = new StringBuilder(8192);
        sb.append("\n");
        Resources resources = view.getResources();
        Stack stack = new Stack();
        stack.push(Pair.create("", view));
        while (!stack.empty()) {
            Pair pair = (Pair) stack.pop();
            View view2 = (View) pair.second;
            String str = (String) pair.first;
            boolean z = stack.empty() || !str.equals(((Pair) stack.peek()).first);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("");
            sb2.append(str);
            sb2.append(z ? "└── " : "├── ");
            sb.append(sb2.toString() + view2.getClass().getSimpleName() + dumpProperties(resources, view2));
            sb.append("\n");
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                while (true) {
                    int i = childCount - 1;
                    if (i >= 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str);
                        sb3.append(z ? "    " : "│   ");
                        stack.push(Pair.create(sb3.toString(), viewGroup.getChildAt(i)));
                        childCount = i;
                    }
                }
            }
        }
        return sb.toString();
    }

    public static int longDebug(@NonNull String str, @NonNull String str2) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (str2.length() <= 0) {
                return i2;
            }
            int indexOf = str2.indexOf("\n");
            int min = Math.min(str2.length(), Math.min(indexOf < 0 ? LOG_MSG_LIMIT : indexOf + 1, LOG_MSG_LIMIT));
            Log.d(str, String.format(Locale.US, "%02d: %s", Integer.valueOf(i2), str2.substring(0, min)));
            str2 = str2.substring(min);
            i = i2 + 1;
        }
    }

    @NonNull
    private static String resolveIdToName(@Nullable Resources resources, @NonNull View view) {
        if (resources == null) {
            return "";
        }
        try {
            return " / " + resources.getResourceEntryName(view.getId());
        } catch (Throwable th) {
            return "";
        }
    }
}
