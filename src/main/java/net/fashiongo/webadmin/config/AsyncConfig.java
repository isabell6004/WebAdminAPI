package net.fashiongo.webadmin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "fashionGoApiThreadPoolTaskExecutor")
    public Executor fashionGoApiThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("FashionGoApiExecutor-");
        executor.initialize();
        return new FashionGoApiExecutor(executor);
    }

    public class FashionGoApiExecutor implements AsyncTaskExecutor {

        private Logger logger = LoggerFactory.getLogger(getClass());
        private AsyncTaskExecutor executor;

        private FashionGoApiExecutor(AsyncTaskExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void execute(Runnable task) {
            executor.execute(task);
        }

        @Override
        public void execute(Runnable task, long startTimeout) {
            executor.execute(
                    () -> {
                        try {
                            task.run();
                        } catch (Exception e) {
                            logger.warn("fail to execute the fashionGo api. : {}", e.getMessage());
                        }
                    }
                    , startTimeout
            );
        }

        @Override
        public Future<?> submit(Runnable task) {
            return executor.submit(
                    () -> {
                        try {
                            task.run();
                        } catch (Exception e) {
                            logger.warn("fail to execute the fashionGo api. : {}", e.getMessage());
                        }
                    }
            );
        }

        @Override
        public <T> Future<T> submit(final Callable<T> task) {
            return executor.submit(
                    () -> {
                        try {
                            return task.call();
                        } catch (Exception e) {
                            logger.warn("fail to execute the fashionGo api. : {}", e.getMessage());
                            throw e;
                        }
                    }
            );
        }
    }
}
