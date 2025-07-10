package org.npci;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.TimeUnit;

@RestController
public class MetricsDemoController {

    private final Counter loginSuccessCounter;
    private final AtomicInteger activeSessions;
    private final Timer processingTimer;
    private final DistributionSummary responseSizeSummary;

    public MetricsDemoController(MeterRegistry registry) {
        this.loginSuccessCounter = registry.counter("login_success_total");
        this.activeSessions = new AtomicInteger(3); // just an example
        Gauge.builder("active_sessions", activeSessions, AtomicInteger::get)
                .register(registry);

        this.processingTimer = Timer.builder("request_processing_time")
                .description("Time taken for request processing")
                .register(registry);

        this.responseSizeSummary = DistributionSummary.builder("response_size_bytes")
                .description("Tracks response sizes")
                .baseUnit("bytes")
                .publishPercentileHistogram()
                .register(registry);
    }

    @GetMapping("/metrics-demo/counter")
    public String simulateLoginSuccess() {
        loginSuccessCounter.increment();
        return "Login success counter incremented!";
    }

    @GetMapping("/metrics-demo/gauge")
    public String simulateSessionChange() {
        activeSessions.incrementAndGet();
        return "Simulated session increase. Current: " + activeSessions.get();
    }

    @GetMapping("/metrics-demo/timer")
    public String simulateTimedTask() {
        return processingTimer.record(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {}
            return "Processed with timer!";
        });
    }

    @GetMapping("/metrics-demo/summary")
    public String simulateResponseSize() {
        double size = Math.random() * 1024;
        responseSizeSummary.record(size);
        return "Recorded response size: " + (int) size + " bytes";
    }
}


/*

curl http://localhost:8080/metrics-demo/counter
curl http://localhost:8080/metrics-demo/gauge
curl http://localhost:8080/metrics-demo/timer
curl http://localhost:8080/metrics-demo/summary

 */