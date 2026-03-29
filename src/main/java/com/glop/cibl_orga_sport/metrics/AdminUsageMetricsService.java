package com.glop.cibl_orga_sport.metrics;

import com.glop.cibl_orga_sport.repository.VolontaireRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AdminUsageMetricsService {

    private final MeterRegistry meterRegistry;
    private final Counter volontairesCreesCounter;
    private final Timer signinDurationTimer;
    private final Timer authenticatedUsageTimer;

    public AdminUsageMetricsService(MeterRegistry meterRegistry, VolontaireRepository volontaireRepository) {
        this.meterRegistry = meterRegistry;

        this.volontairesCreesCounter = Counter.builder("glop_admin_volontaires_crees_total")
                .description("Nombre total de volontaires enregistres")
                .register(meterRegistry);

        this.signinDurationTimer = Timer.builder("glop_admin_signin_duration_seconds")
                .description("Duree des tentatives de connexion reussies")
                .register(meterRegistry);

        this.authenticatedUsageTimer = Timer.builder("glop_admin_authenticated_usage_seconds")
                .description("Temps cumule passe sur des requetes authentifiees")
                .register(meterRegistry);

        Gauge.builder("glop_admin_volontaires_total", volontaireRepository, VolontaireRepository::count)
                .description("Nombre actuel de volontaires en base")
                .register(meterRegistry);
    }

    public void incrementVolontairesCrees() {
        volontairesCreesCounter.increment();
    }

    public void recordDataConsultation(String endpoint) {
        Counter.builder("glop_admin_data_consultations_total")
                .description("Nombre de consultations de donnees (GET API)")
                .tag("endpoint", endpoint)
                .register(meterRegistry)
                .increment();
    }

    public void recordSigninDuration(long durationNanos) {
        signinDurationTimer.record(durationNanos, TimeUnit.NANOSECONDS);
    }

    public void recordAuthenticatedUsage(long durationNanos) {
        authenticatedUsageTimer.record(durationNanos, TimeUnit.NANOSECONDS);
    }
}
