package com.idugalic.commandside.team.aggregate;

import com.idugalic.common.command.AuditableAbstractCommand;
import com.idugalic.common.model.AuditEntry;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

class AssignTeamToProjectFailedCommand extends AuditableAbstractCommand {

    @TargetAggregateIdentifier
    private String id;
    private String projectId;

    public AssignTeamToProjectFailedCommand(AuditEntry auditEntry, String id, String projectId) {
        super(auditEntry);
        this.id = id;
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }
}
