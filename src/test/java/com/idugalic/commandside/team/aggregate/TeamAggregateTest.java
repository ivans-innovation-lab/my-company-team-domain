package com.idugalic.commandside.team.aggregate;

import com.idugalic.commandside.team.command.CreateTeamCommand;
import com.idugalic.common.model.AuditEntry;
import com.idugalic.common.team.event.TeamCreatedEvent;
import com.idugalic.common.team.model.TeamStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

/**
 * Domain (aggregate) test.
 */
public class TeamAggregateTest {

    private static final String WHO = "john";
    private FixtureConfiguration<TeamAggregate> fixture;
    private AuditEntry auditEntry;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<TeamAggregate>(TeamAggregate.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor());
        auditEntry = new AuditEntry(WHO);
    }

    @Test
    public void createTeamTest() throws Exception {
        CreateTeamCommand command = new CreateTeamCommand(auditEntry, "name", "desc", TeamStatus.ACTIVE);
        fixture.given().when(command).expectEvents(new TeamCreatedEvent(command.getId(), command.getAuditEntry(), command.getName(), command.getDescription(), command
                .getStatus()));
    }

    @Test(expected = JSR303ViolationException.class)
    public void createTeamTestWithBadName() throws Exception {
        CreateTeamCommand command = new CreateTeamCommand(auditEntry, null, "desc", TeamStatus.ACTIVE);
        fixture.given().when(command).expectEvents(new TeamCreatedEvent(command.getId(), command.getAuditEntry(), command.getName(), command.getDescription(), command
                .getStatus()));
    }
}
