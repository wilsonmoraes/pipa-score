package br.com.pipa.web;


import br.com.pipa.config.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
@Transactional
public class UserControllerTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;


    @Test
    public void testFindPosition() {
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<Map> entity = testRestTemplate.exchange(
                "/open/user/{id}/position", HttpMethod.GET, requestEntity,
                Map.class, 1);
        Assert.assertEquals(entity.getStatusCode(), HttpStatus.OK);

    }
}
