package com.idugalic.commandside.team.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.idugalic.common.command.AuditableAbstractCommand;
import com.idugalic.common.model.AuditEntry;

/**
 * A command for removing the member from the team
 * 
 * @author idugalic
 *
 */
public class RemoveMemberFromTeamCommand extends AuditableAbstractCommand {

	@TargetAggregateIdentifier
	private String id;
	private String userId;

	public RemoveMemberFromTeamCommand(AuditEntry auditEntry, String id, String userId){
		super(auditEntry);
		this.id = id;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}



}
