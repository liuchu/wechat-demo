package priv.liuchu.wechat.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@SpringBootApplication
public class WechatDemoApplication {

	public static void main(String[] args) {

		new Thread(() -> {

			while (true) {
				OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

				double systemLoad = operatingSystemMXBean.getSystemLoadAverage();
				System.out.println(systemLoad);

				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		}).start();

		SpringApplication.run(WechatDemoApplication.class, args);
	}

}
