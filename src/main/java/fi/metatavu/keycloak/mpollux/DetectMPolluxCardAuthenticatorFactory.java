package fi.metatavu.keycloak.mpollux;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.*;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

import java.util.List;

/**
 * Authenticator factory for Detect mPollux Card authenticator
 */
public class DetectMPolluxCardAuthenticatorFactory implements AuthenticatorFactory {
    private static AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES;

    @Override
    public String getDisplayType() {
        return "Detect mPollux Card";
    }

    @Override
    public String getReferenceCategory() {
        return "flow-control";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Detect mPollux Card";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property()
                .name(DetectMPolluxCardAuthenticatorConfig.CHECK_INTERVAL)
                .type(ProviderConfigProperty.STRING_TYPE)
                .label("Check Interval")
                .helpText("Check interval for card detection")
                .add()
                .build();
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        return new DetectMPolluxCardAuthenticator();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "detect-mpollux-card";
    }

    static {
        REQUIREMENT_CHOICES = new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE,
                AuthenticationExecutionModel.Requirement.DISABLED
        };
    }
}
