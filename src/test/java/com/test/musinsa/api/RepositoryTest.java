package com.test.musinsa.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.test.musinsa.domain.UrlConverter;
import com.test.musinsa.repository.UrlConverterRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTest {
	@Autowired UrlConverterRepository urlConverterRepository;
	
	@Test
	@Description("JPA 테스트")
	@Transactional
	public void saveJpaTest() {
		UrlConverter urlConverter = new UrlConverter();
		UrlConverter urlConverter2 = new UrlConverter();
		
		urlConverter.save("https://www.musinsa.com", null, 1);
		UrlConverter returnEntity = urlConverterRepository.save(urlConverter);
		
		urlConverter2.save("https://www.musinsa.com", "http://localhost/test", returnEntity.getCallCount() + 1);
		UrlConverter returnEntity2 = urlConverterRepository.save(urlConverter2);
		
		assertThat(returnEntity.getCallCount()).isEqualTo(1);
		assertThat(returnEntity2.getCallCount()).isEqualTo(2);
		
		assertThat(returnEntity.getLongUrl()).isEqualTo("https://www.musinsa.com");
		assertThat(returnEntity2.getLongUrl()).isEqualTo("https://www.musinsa.com");
		
		assertThat(returnEntity.getShortUrl()).isEqualTo(null);
		assertThat(returnEntity2.getShortUrl()).isEqualTo("http://localhost/test");

	}
	
}
