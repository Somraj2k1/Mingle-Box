package mingleBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Mingle_Box {
	private List<User> coders = new ArrayList<>();
    private List<User> buyers = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private User loggedInUser;
    private Scanner scanner = new Scanner(System.in);
    
    
    public void run() {
    	System.out.println("Welcome to Mingle-Box!!!");
        while (true) {
            System.out.println("1. Register as Coder");
            System.out.println("2. Register as Buyer");
            System.out.println("3. Login");
            System.out.println("4. Add Project for Bidding");
            System.out.println("5. Conduct Bidding");
            System.out.println("6. Select Coders for Project");
            System.out.println("7. Pay Coders");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            try {
            		int choice = scanner.nextInt();
            		scanner.nextLine();

            		switch (choice) {
            		case 1:
            			registerCoder();
                    	break;

            		case 2:
            			registerBuyer();
            			break;

            		case 3:
            			login();
            			break;

            		case 4:
            			addProjectForBidding();
            			break;

            		case 5:
            			conductBidding();
            			break;

            		case 6:
            			selectCodersForProject();
            			break;

            		case 7:
            			payCoders();
            			break;

            		case 8:
            			System.out.println("Exiting Mingle Box. Goodbye!");
            			return;
                    
            		default:
            			System.out.println("Invalid choice. Please try again.");
            		}
            }catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    
    public void registerCoder() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter languages (comma-separated): ");
        String languagesStr = scanner.nextLine();
        List<String> languages = Arrays.asList(languagesStr.split(","));

        Coder coder = new Coder(username, password, languages);
        coders.add(coder);
        System.out.println("Coder registered successfully!");
    }

    public void registerBuyer() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Buyer buyer = new Buyer(username, password);
        buyers.add(buyer);
        System.out.println("Buyer registered successfully!");
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = findUserByUsername(username);
        if (user != null && user.authenticate(password)) {
            loggedInUser = user;
            System.out.println("Login successful.");
        } else {
            loggedInUser = null;
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    public User findUserByUsername(String username) {
        for (User user : coders) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        for (User user : buyers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addProjectForBidding() {
        if (loggedInUser instanceof Buyer) {
            System.out.print("Enter project name: ");
            String name = scanner.nextLine();
            System.out.print("Enter project description: ");
            String description = scanner.nextLine();
            System.out.print("Enter project budget: ");
            double budget = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Project project = new Project(name, description, budget, (Buyer) loggedInUser);
            projects.add(project);
            System.out.println("Project added for bidding successfully.");
        } else {
            System.out.println("Only buyers can add projects for bidding.");
        }
    }

    public void conductBidding() {
        if (loggedInUser instanceof Coder) {
            System.out.println("Available Projects for Bidding:");
            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                System.out.println(i + ". " + project.getName());
            }

            System.out.print("Select a project index to place your bid: ");
            int projectIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project selectedProject = projects.get(projectIndex);
                System.out.print("Enter your bid amount: ");
                int bidAmount = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Bid bid = new Bid((Coder) loggedInUser, selectedProject, bidAmount);
                selectedProject.addBid(bid);
                System.out.println("Bid placed successfully.");
            } else {
                System.out.println("Invalid project index.");
            }
        } else {
            System.out.println("Only coders can conduct bidding.");
        }
    }

    public void selectCodersForProject() {
        if (loggedInUser instanceof Buyer) {
            System.out.println("Projects with Bids:");
            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                List<Bid> bids = project.getBids();
                if (!bids.isEmpty()) {
                    System.out.println(i + ". " + project.getName() + " - Bids: " + bids.size());
                }
            }

            System.out.print("Select a project index to finalize coders: ");
            int projectIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project selectedProject = projects.get(projectIndex);
                List<Bid> bids = selectedProject.getBids();
                if (!bids.isEmpty()) {
                    System.out.println("Top Bidders for " + selectedProject.getName() + ":");
                    for (int i = 0; i < bids.size(); i++) {
                        Bid bid = bids.get(i);
                        Coder coder = bid.getCoder();
                        System.out.println(i + ". " + coder.getUsername() + " - Bid: $" + bid.getAmount());
                    }

                    System.out.print("Enter the number of coders to select: ");
                    int numCodersToSelect = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (numCodersToSelect > 0 && numCodersToSelect <= bids.size()) {
                        List<Coder> selectedCoders = new ArrayList<>();
                        for (int i = 0; i < numCodersToSelect; i++) {
                            selectedCoders.add(bids.get(i).getCoder());
                        }
                        selectedProject.assignCoders(selectedCoders);
                        System.out.println("Coders selected and project assigned.");
                    } else {
                        System.out.println("Invalid number of coders.");
                    }
                } else {
                    System.out.println("No bids available for this project.");
                }
            } else {
                System.out.println("Invalid project index.");
            }
        } else {
            System.out.println("Only buyers can select coders for a project.");
        }
    }

    public void payCoders() {
        if (loggedInUser instanceof Buyer) {
            System.out.println("Your Projects:");
            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                if (project.getBuyer() == loggedInUser && project.getAssignedCoders().size() > 0) {
                    System.out.println(i + ". " + project.getName() + " - Coders Assigned: " + project.getAssignedCoders().size());
                }
            }

            System.out.print("Select a project index to pay coders: ");
            int projectIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (projectIndex >= 0 && projectIndex < projects.size()) {
                Project selectedProject = projects.get(projectIndex);
                if (selectedProject.getBuyer() == loggedInUser && selectedProject.getAssignedCoders().size() > 0) {
                    double totalPayment = 0;
                    for (Coder coder : selectedProject.getAssignedCoders()) {
                        totalPayment += selectedProject.getBudget() / selectedProject.getAssignedCoders().size();
                        ((Buyer) loggedInUser).deductBalance(totalPayment);
                        coder.addBalance(totalPayment);
                    }
                    System.out.println("Payment successful to coders.");
                } else {
                    System.out.println("Invalid project index or project not assigned to you.");
                }
            } else {
                System.out.println("Invalid project index.");
            }
        } else {
            System.out.println("Only buyers can make payments to coders.");
        }
    }
    
    public static void main(String args[]) {
    	Mingle_Box mb = new Mingle_Box();
    	mb.run();
    	
    }
}
