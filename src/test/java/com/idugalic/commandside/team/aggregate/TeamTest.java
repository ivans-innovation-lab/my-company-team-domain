package com.idugalic.commandside.team.aggregate;

import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import com.idugalic.common.model.AuditEntry;

/**
 * Domain (aggregate) test.
 */
public class TeamTest {

    private FixtureConfiguration<Team> fixture;

    private AuditEntry auditEntry;

    private static final String WHO = "john";

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<Team>(Team.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor());
        auditEntry = new AuditEntry(WHO);
    }
}
