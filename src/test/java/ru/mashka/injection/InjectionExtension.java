package ru.mashka.injection;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.mashka.ApplicationBinder;

import java.util.Optional;

public class InjectionExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        Optional<Object> testInstance = extensionContext.getTestInstance();
        ServiceLocator locator = ServiceLocatorUtilities.bind(new ApplicationBinder());
        testInstance.ifPresent(locator::inject);
    }
}
