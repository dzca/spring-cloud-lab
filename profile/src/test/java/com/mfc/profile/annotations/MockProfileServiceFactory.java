package com.mfc.profile.annotations;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.mfc.profile.mocks.MockProfileService;
import com.mfc.profile.service.ProfileService;

public class MockProfileServiceFactory {
	
    private static class ValidateTokenInvocationHandler implements InvocationHandler {

        private final ProfileService profileService;

        public ValidateTokenInvocationHandler(ProfileService profileService) {
            this.profileService = profileService;
        }

        public Object invoke(Object proxy, Method method, Object[] args) 
                      throws Throwable {
            Method realMethod = profileService.getClass().getMethod(
                                        method.getName(), 
                                        method.getParameterTypes());
            ValidateToken audit = realMethod.getAnnotation(ValidateToken.class);

            if (audit != null) {
                audit.handler().newInstance().validate();
            }

            return method.invoke(profileService, args);
        }
    }

    public static ProfileService createProfileService() {
        return (ProfileService) Proxy.newProxyInstance(
        		MockProfileServiceFactory.class.getClassLoader(),
                new Class[]{ProfileService.class},
                new ValidateTokenInvocationHandler(new MockProfileService()));
    }
}
