package com.idugalic.commandside.team.aggregate;

import com.idugalic.commandside.team.command.CreateTeamCommand;
import com.idugalic.common.team.event.TeamCreatedEvent;
import com.idugalic.common.team.model.TeamStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Team aggregate root
 */
@Aggregate
class TeamAggregate {

    private static final Logger LOG = LoggerFactory.getLogger(TeamAggregate.class);

    /**
     * Aggregates that are managed by Axon must have a unique identifier.
     * Strategies similar to GUID are often used. The annotation 'AggregateIdentifier' identifies the id field as such.
     */
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;
    private TeamStatus status = TeamStatus.PASSIVE;
    private String projectId;
    private Collection<Member> members;

    /**
     * Default constructor
     */
    public TeamAggregate() {
    }

    /**
     * This constructor is marked as a 'CommandHandler' for the CreateTeamCommand.
     * This command can be used to construct new instances of the Aggregate.
     * If successful a new TeamAggregate is 'applied' to the aggregate using the Axon 'apply' method.
     * The apply method appears to also propagate the Event to any other registered 'Event Listeners', who may take further action.
     *
     * @param command
     */
    @CommandHandler
    public TeamAggregate(CreateTeamCommand command) {
        LOG.debug("Command: 'CreateTeamCommand' received.");
        apply(new TeamCreatedEvent(command.getId(), command.getAuditEntry(), command.getName(), command.getDescription(), command.getStatus()));
    }

    /**
     * This method is marked as an EventSourcingHandler and is therefore used by the Axon framework to handle events of the specified type (TeamCreatedEvent).
     * The TeamCreatedEvent can be raised either by the constructor during Team(CreateTeamCommand) or by the Repository when 're-loading' the aggregate.
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(TeamCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.status = event.getStatus();
        LOG.debug("Event Applied: 'TeamCreatedEvent' [{}]", event.getId());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public String getProjectId() {
        return projectId;
    }

    public Collection<Member> getMembers() {
        return Collections.unmodifiableCollection(members);
    }
}
