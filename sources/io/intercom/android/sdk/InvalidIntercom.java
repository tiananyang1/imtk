package io.intercom.android.sdk;

import android.app.TaskStackBuilder;
import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;
import io.intercom.android.sdk.logger.LumberMill;
import io.intercom.android.sdk.twig.Twig;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:io/intercom/android/sdk/InvalidIntercom.class */
class InvalidIntercom extends Intercom {
    private final Twig twig = LumberMill.getLogger();

    private void logIncorrectInitialisationWarning() {
        this.twig.e("Intercom has been initialized incorrectly. Please make sure the first Intercom method you call is initialize() and that you're passing in the correct app ID and API key", new Object[0]);
    }

    @Override // io.intercom.android.sdk.Intercom
    public void addUnreadConversationCountListener(UnreadConversationCountListener unreadConversationCountListener) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void displayConversationsList() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void displayHelpCenter() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void displayMessageComposer() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void displayMessageComposer(String str) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void displayMessenger() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public int getUnreadConversationCount() {
        logIncorrectInitialisationWarning();
        return 0;
    }

    @Override // io.intercom.android.sdk.Intercom
    public void handlePushMessage() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void handlePushMessage(TaskStackBuilder taskStackBuilder) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void hideMessenger() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void logEvent(String str) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void logEvent(String str, Map<String, ?> map) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void logout() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void registerIdentifiedUser(Registration registration) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void registerUnidentifiedUser() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void removeUnreadConversationCountListener(UnreadConversationCountListener unreadConversationCountListener) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void reset() {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void setBottomPadding(int i) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void setInAppMessageVisibility(Intercom.Visibility visibility) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void setLauncherVisibility(Intercom.Visibility visibility) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void setUserHash(String str) {
        logIncorrectInitialisationWarning();
    }

    @Override // io.intercom.android.sdk.Intercom
    public void updateUser(UserAttributes userAttributes) {
        logIncorrectInitialisationWarning();
    }
}
