package intermediate;

import java.security.KeyStore;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeOperations {

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(1, "Jhansi", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(2, "Smith", 25, "Male", "Sales", 2015, 13500.0));
        employeeList.add(new Employee(3, "David", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(4, "Orlen", 28, "Male", "Development", 2014, 32500.0));
        employeeList.add(new Employee(5, "Charles", 27, "Male", "HR", 2013, 22700.0));
        employeeList.add(new Employee(6, "Cathy", 43, "Male", "Security", 2016, 10500.0));
        employeeList.add(new Employee(7, "Ramesh", 35, "Male", "Finance", 2010, 27000.0));
        employeeList.add(new Employee(8, "Suresh", 31, "Male", "Development", 2015, 34500.0));
        employeeList.add(new Employee(9, "Gita", 24, "Female", "Sales", 2016, 11500.0));
        employeeList.add(new Employee(10, "Mahesh", 38, "Male", "Security", 2015, 11000.5));
        employeeList.add(new Employee(11, "Gouri", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(12, "Nithin", 25, "Male", "Development", 2016, 28200.0));
        employeeList.add(new Employee(13, "Swathi", 27, "Female", "Finance", 2013, 21300.0));
        employeeList.add(new Employee(14, "Buttler", 24, "Male", "Sales", 2017, 10700.5));
        employeeList.add(new Employee(15, "Ashok", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(16, "Sanvi", 26, "Female", "Development", 2015, 28900.0));


//        1. How many male and female employees are there in the organization ?

        Map<String, List<String>> empGenderWise = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("Male :" + empGenderWise.get("Male"));
        System.out.println("Female :" + empGenderWise.get("Female"));

//        2. Print the name of all departments in the organization ?
        System.out.println("===============================================");
        List<String> departments = employeeList.stream().map(Employee::getDepartment).distinct().toList();
        System.out.println(departments);


//        3. What is the average age of male and female employees ?
        System.out.println("===============================================");
        Map<String, Double> avgAge = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println("Average Age of Male: " + avgAge.get("Male"));
        System.out.println("Average Age of Female: " + avgAge.get("Female"));
//        4. Get the details of highest paid employee in the organization ?
        System.out.println("===============================================");
        Optional<Employee> highestPaidEMp = employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst();
        highestPaidEMp.ifPresent(System.out::println);

//        5. Get the names of all employees who have joined after 2015 ?
        System.out.println("===============================================");
        List<String> empAfterFifteen = employeeList.stream().filter(employee -> employee.getYearOfJoining() > 2015).collect(Collectors.mapping(Employee::getName, Collectors.toList()));
        System.out.println(empAfterFifteen);


//                6. Count the number of employees in each department ?
        System.out.println("===============================================");
        Map<String, Long> countDeptWise = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(countDeptWise);

//        7. What is the average salary of each department ?
        System.out.println("===============================================");
        Map<String, Double> avgSalDeptWise = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalDeptWise);


//        8. Get the details of youngest male employee in the Development department ?
        System.out.println("===============================================");
        Optional<Employee> youngestMaleDev = employeeList.stream().filter(e -> "Development".equals(e.getDepartment()) && "Male".equals(e.getGender())).sorted(Comparator.comparingInt(Employee::getAge)).findFirst();
        youngestMaleDev.ifPresent(System.out::println);


//                9. Who has the most working experience in the organization ?
        System.out.println("===============================================");

        Optional<Employee> mostExp = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
        mostExp.ifPresent(System.out::println);

//                10. How many male and female employees are there in the Sales team ?
        System.out.println("===============================================");

        Map<String, Long> salesCount = employeeList.stream().filter(e -> "Sales".equals(e.getDepartment())).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(salesCount);


//        11. List down the names of all employees in each department ?
        System.out.println("===============================================");

        Map<String, List<String>> empNameDeptWise = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(empNameDeptWise);
//        12.  What is the average salary and total salary of the whole organization ?
        System.out.println("===============================================");

        OptionalDouble averageSalOrg = employeeList.stream().mapToDouble(Employee::getSalary).average();
        double totalSalOrg = employeeList.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("Average: " + averageSalOrg);
        System.out.println("Sum: " + totalSalOrg);
//        13.  Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years ?
        System.out.println("===============================================");

        Map<Boolean, List<String>> empAgeMoreThanTwoFive = employeeList.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() > 25, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("Greater than 25:" + empAgeMoreThanTwoFive.get(true));
        System.out.println("Less than 25:" + empAgeMoreThanTwoFive.get(false));
        //                14.  Who is the oldest employee in the organization?(By Age)
        System.out.println("===============================================");


        Optional<Employee> oldestEmp = employeeList.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).findFirst();
        oldestEmp.ifPresent(System.out::println);
//Find the department with more than 2 emp
        System.out.println("+++++++++++++++++++++++++++");
        Map<String, Long> depts = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 2)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(depts);

//        Highest salary emp and salary of each dept
//        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))),Collectors.toMap(Employee::getDepartment,Employee::getName));

        String str = "Java";
        str.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
                .stream().map(Map.Entry::getValue).sorted(Comparator.comparingInt(value -> value)).findFirst();

    }


}
