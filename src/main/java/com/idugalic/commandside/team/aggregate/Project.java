package com.idugalic.commandside.team.aggregate;

public class Project {
	private String projectId;
	private Status status;
	
	
	public Project(String projectId, Status status) {
		super();
		this.projectId = projectId;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
enum Status {

    ASSIGNED("Assigned", "assigned"), FAILED("Failed", "failed");

    private String displayName;
    private String urlSlug;

    Status(String displayName, String urlSlug) {
        this.displayName = displayName;
        this.urlSlug = urlSlug;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public String getId() {
        return name();
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
