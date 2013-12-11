package br.spring.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AsyncWorker {

	private static final Logger logger = LoggerFactory.getLogger(AsyncWorker.class);

	@Async
	public void doAsync(String message) {
		try {
			Thread.sleep(10000 + (int) (Math.random() * (20000 - 10000) + 1));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Finished message: " + message);
	}

}
