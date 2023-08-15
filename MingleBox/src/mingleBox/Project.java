package mingleBox;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private String name;
    private String description;
    private double budget;
    private Buyer buyer;
    private List<Bid> bids;
    private List<Coder> assignedCoders;

    public Project(String name, String description, double budget, Buyer buyer) {
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.buyer = buyer;
        this.bids = new ArrayList<>();
        this.assignedCoders = new ArrayList<>();
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public void setAssignedCoders(List<Coder> assignedCoders) {
		this.assignedCoders = assignedCoders;
	}

    public String getName() {
        return name;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void assignCoders(List<Coder> coders) {
        assignedCoders.addAll(coders);
    }

    public List<Coder> getAssignedCoders() {
        return assignedCoders;
    }
}
