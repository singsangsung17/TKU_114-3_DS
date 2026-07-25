public class Patient {
    private String ticketNumber;
    private String name;
    private String department;

    public Patient(String ticketNumber, String name, String department) {
        this.ticketNumber = ticketNumber;
        this.name = name;
        this.department = department;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return ticketNumber + " " + name + " (" + department + ")";
    }
}
