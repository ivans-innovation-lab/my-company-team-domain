package com.idugalic.commandside.team.aggregate;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.idugalic.commandside.project.command.FindProjectCommand;
import com.idugalic.common.project.event.ProjectFoundEvent;
import com.idugalic.common.project.event.ProjectNotFoundEvent;
import com.idugalic.common.team.event.AssignTeamToProjectStartedEvent;
import static org.axonframework.eventhandling.saga.SagaLifecycle.associateWith;

@Saga
public class TeamProjectManagementSaga {

	private String projectId;
	private String teamId;

	@Autowired
	private transient CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "projectId")
	public void on(AssignTeamToProjectStartedEvent event) {
		this.projectId = event.getProjectId();
		this.teamId = event.getId();
		associateWith("id", this.projectId);
		FindProjectCommand command = new FindProjectCommand(this.projectId, event.getAuditEntry());
		commandGateway.send(command, LoggingCallback.INSTANCE);
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(ProjectNotFoundEvent event) {

		MarkAssignTeamToProjectFailedCommand command = new MarkAssignTeamToProjectFailedCommand(event.getAuditEntry(), this.teamId, this.projectId);
		commandGateway.send(command, LoggingCallback.INSTANCE);
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(ProjectFoundEvent event) {
		
		MarkAssignTeamToProjectSucceededCommand command = new MarkAssignTeamToProjectSucceededCommand(event.getAuditEntry(), this.teamId, this.projectId);
		commandGateway.send(command, LoggingCallback.INSTANCE);
	}
}
