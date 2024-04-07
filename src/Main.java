import java.util.ArrayList;
import java.util.Scanner;


abstract class Employee{
    private int id;
    private String name;

    public Employee(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    abstract public double calcSalary();

    @Override
    public String toString(){
        return "Employee [name="+name+", id="+id+", salary="+calcSalary()+"]";
    }
}

class Full_Time_Employee extends Employee{
    private double monthSalary;

    public Full_Time_Employee(int id, String name, double monthSalary){
        super(id, name);
        this.monthSalary = monthSalary;
    }

    @Override
    public double calcSalary(){
        return monthSalary;
    }
}

class Part_Time_Employee extends Employee{
    private int hours;
    private double hourlyRate;

    public Part_Time_Employee(int id, String name, int hours, double hourlyRate){
        super(id, name);
        this.hours = hours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calcSalary(){
        return hours * hourlyRate;
    }
}

class Payroll_System{
    private ArrayList<Employee> employeeList; 

    public Payroll_System(){
        employeeList = new ArrayList<>();
    }

    // To Add An Employee
    public void addEmployee(Employee Emp){
        employeeList.add(Emp);
    }

    // To Remove An Employee
    public void removeEmployee(int id){
        Employee toRemoveEmployee = null;
        for(Employee Emp : employeeList){
            if(Emp.getId() == id){
                toRemoveEmployee = Emp;
                break;
            }
        }

        if(toRemoveEmployee != null){
            employeeList.remove(toRemoveEmployee);
        }
        else{
            System.out.println("Employee Does't Exists!!!");
        }
    }

    // To See List of Employees
    public void displayEmployee(){
        System.out.println("List of Employees:");
        for(Employee Emp : employeeList){
            System.out.println(Emp);
        }
    }

}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Payroll_System payrollSystem = new Payroll_System();

        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addEmployee(scanner, payrollSystem);
                    break;
                case 2:
                    removeEmployee(scanner, payrollSystem);
                    break;
                case 3:
                    payrollSystem.displayEmployee();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addEmployee(Scanner scanner, Payroll_System payrollSystem) {
        System.out.println("Enter employee type (1 for Full-Time, 2 for Part-Time): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Enter employee ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.println("Enter employee name:");
        String name = scanner.nextLine();

        if (type == 1) {
            System.out.println("Enter monthly salary:");
            double monthlySalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            Full_Time_Employee employee = new Full_Time_Employee(id, name, monthlySalary);
            payrollSystem.addEmployee(employee);
        } else if (type == 2) {
            System.out.println("Enter hours worked:");
            int hours = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter hourly rate:");
            double hourlyRate = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            Part_Time_Employee employee = new Part_Time_Employee(id, name, hours, hourlyRate);
            payrollSystem.addEmployee(employee);
        } else {
            System.out.println("Invalid employee type.");
        }
    }

    private static void removeEmployee(Scanner scanner, Payroll_System payrollSystem) {
        System.out.println("Enter ID of the employee to remove:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        payrollSystem.removeEmployee(id);
    }
}


