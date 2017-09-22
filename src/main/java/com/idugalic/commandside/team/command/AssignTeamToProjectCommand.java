package com.idugalic.commandside.team.command;

import com.idugalic.common.command.AuditableAbstractCommand;
import com.idugalic.common.model.AuditEntry;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AssignTeamToProjectCommand extends AuditableAbstractCommand {

    @TargetAggregateIdentifier
    private String id;
    private String projectId;
    private String reason;

    public AssignTeamToProjectCommand(AuditEntry auditEntry, String id, String projectId, String reason) {
        super(auditEntry);
        this.id = id;
        this.projectId = projectId;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }

	public String getReason() {
		return reason;
	}
    
}
