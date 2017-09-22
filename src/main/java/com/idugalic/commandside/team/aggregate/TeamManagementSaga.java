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

@Saga
class TeamManagementSaga {

	private String projectId;

	@Autowired
	private CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "projectId")
	public void on(AssignTeamToProjectStartedEvent event) {
		this.projectId = event.getProjectId();
		FindProjectCommand command = new FindProjectCommand(event.getProjectId(), event.getAuditEntry());
		commandGateway.send(command, LoggingCallback.INSTANCE);
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "projectId")
	public void on(ProjectNotFoundEvent event) {

		AssignTeamToProjectFailedCommand command = new AssignTeamToProjectFailedCommand(event.getAuditEntry(), event.getId(), "Project not found.");
		commandGateway.send(command, LoggingCallback.INSTANCE);
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "projectId")
	public void on(ProjectFoundEvent event) {

		AssignTeamToProjectSuccessCommand command = new AssignTeamToProjectSuccessCommand(event.getAuditEntry(), event.getId(), "Project found, success.");
		commandGateway.send(command, LoggingCallback.INSTANCE);
	}
}
