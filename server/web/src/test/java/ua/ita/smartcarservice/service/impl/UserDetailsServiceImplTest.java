package ua.ita.smartcarservice.service.impl;
/*
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.UserPrinciple;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@PowerMockIgnore({"*.*"})
@PrepareForTest(UserDetailsServiceImpl.class)
public class UserDetailsServiceImplTest {

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

   // @Test
    public void testLoadUserByUsername() {

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserEntity userEntity = userRepository.findByUsername("admin").get();
        PowerMockito.when(userRepository.findByUsername("admin").get()).thenReturn(userEntity);

        Object actual;
        Object expected;

        expected = userRepository.findByUsername("admin");
        actual = userRepository.findByUsername("admin");

        Assert.assertEquals(actual, expected);


    }
}
*/