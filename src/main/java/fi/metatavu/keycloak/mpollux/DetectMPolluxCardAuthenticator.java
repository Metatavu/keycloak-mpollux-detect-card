package fi.metatavu.keycloak.mpollux;

import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

/**
 * Detect mPollux Card Authenticator
 */
public class DetectMPolluxCardAuthenticator implements Authenticator {

    private static final Logger logger = Logger.getLogger(DetectMPolluxCardAuthenticator.class);

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        logger.info("Detect mPollux Card Authenticator authenticate");

        LoginFormsProvider form = context.form().setExecution(context.getExecution().getId());
        Response response = form
                .setAttribute("checkInterval", context.getAuthenticatorConfig().getConfig().get(DetectMPolluxCardAuthenticatorConfig.CHECK_INTERVAL))
                .createForm("detect-card-form.ftl");
        context.challenge(response);

        logger.info("Detect mPollux Card Authenticator rendered form");
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        context.attempted();
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {}

    @Override
    public void close() {}

}
