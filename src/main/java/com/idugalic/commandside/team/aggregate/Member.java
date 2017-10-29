package com.idugalic.commandside.team.aggregate;

import java.time.LocalDate;

class Member {

	private String userId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long weeklyHours;

	public Member(String userId, LocalDate startDate, LocalDate endDate, Long weeklyHours) {
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.weeklyHours = weeklyHours;
	}

	public String getUserId() {
		return userId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Long getWeeklyHours() {
		return weeklyHours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
