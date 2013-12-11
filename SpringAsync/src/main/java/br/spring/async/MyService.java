package br.spring.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	private static final Logger logger = LoggerFactory.getLogger(MyService.class);

	@Autowired
	// Runs a async task using Spring
	private AsyncWorker asyncWorker;

	public void asyncTest(String message) {

		asyncWorker.doAsync(message);

	}

	// Runs async task using pure Java
	// This isnt a good pratice
	public void asyncJavaTest(final String message) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(10000 + (int) (Math.random() * (20000 - 10000) + 1));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				logger.info("Finished message: " + message);
			}

		});
		thread.setName("JOSUE-" + message);
		thread.start();

	}

}
