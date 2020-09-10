package br.com.pipa.repository;


import br.com.pipa.config.IntegrationTest;
import br.com.pipa.dao.UserRepository;
import br.com.pipa.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindAll() {
        User user = userRepository.findById(11L).orElse(null);
        Assert.assertNotNull(user);
    }


}
