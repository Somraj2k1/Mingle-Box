package mingleBox;

public class Bid {
	private Coder coder;
    private Project project;
    private int amount;

    public Bid(Coder coder, Project project, int amount) {
        this.coder = coder;
        this.project = project;
        this.amount = amount;
    }

    public Coder getCoder() {
        return coder;
    }
    
    public void setCoder(Coder coder) {
		this.coder = coder;
	}
    
    public Project getProject() {
		return project;
	}
    
    public void setProject(Project project) {
		this.project = project;
	}

    public int getAmount() {
        return amount;
    }

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
