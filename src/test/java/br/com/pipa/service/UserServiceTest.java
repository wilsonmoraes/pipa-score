package br.com.pipa.service;


import br.com.pipa.config.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    @Rollback
    public void testAddScore() {
        userService.getPosition(11L);
        Long val = userService.addScore(11L, 50L);
        Assert.assertNotNull(val);

    }


}
