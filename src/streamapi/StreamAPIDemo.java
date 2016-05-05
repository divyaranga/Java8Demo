package streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamAPIDemo {
	public static Predicate<Department> isLarge() {
		return p -> p.getHeadCount() > 50;
	}

	public static Predicate<Department> isSmall() {
		return p -> p.getHeadCount() <= 50;
	}

	public static ArrayList<Department> departments;

	public static void main(String[] args) {

		departments = populate();

		countElementsInList();

		listLargeDepartments();

		showDepartmentNamesInUpper();

		sortDepartments();

		showHeadCountOfCompany();

		showAnyLargeDepartment();

		hasLargeDepartments();

		collectorsDemo();

	}

	private static void collectorsDemo() {
		List<String> departmentNames = departments.stream().map(d -> d.getDepartmentName())
				.collect(Collectors.toList());
		departmentNames.stream().forEach(System.out::println);
	}

	private static void showAnyLargeDepartment() {
		Optional<Department> anyDepartment = departments.stream().filter(isLarge()).findAny();
		if (anyDepartment.isPresent())
			System.out.println(anyDepartment.get());
	}

	private static void hasLargeDepartments() {
		boolean flag = departments.stream().anyMatch(isLarge());
		if (flag)
			System.out.println("There are large departments in this company.");
	}

	private static void showHeadCountOfCompany() {
		int total = departments.stream().map(d -> d.getHeadCount()).reduce(0, Integer::sum);
		System.out.println("Total headcount:" + total);

		// Get total head count of large departments
		total = departments.stream().filter(d -> d.getHeadCount() > 50).map(d -> d.getHeadCount()).reduce(0,
				Integer::sum);
		System.out.println("Head count of large companies" + total);

	}

	private static void sortDepartments() {
		departments.stream().sorted(
				(department1, department2) -> Integer.compare(department1.getHeadCount(), department2.getHeadCount()))
				.forEach(System.out::println);

	}

	private static void showDepartmentNamesInUpper() {
		// map applies a function to each element in the stream
		departments.stream().map(x -> x.getDepartmentName().toUpperCase()).forEach(System.out::println);
	}

	private static void listLargeDepartments() {
		// filter - eliminates the stream based on condition provided
		departments.stream().filter(isLarge()).forEach(x -> System.out.println(x));
	}

	private static void countElementsInList() {
		long size = departments.stream().count();
		System.out.println(size);
	}

	private static ArrayList<Department> populate() {
		ArrayList<Department> departments = new ArrayList<>();
		departments.add(new Department(new Manager("Jack"), "Marketing", 50));
		departments.add(new Department(new Manager("Jane"), "Sales", 100));
		departments.add(new Department(new Manager("Jill"), "Finance", 50));
		departments.add(new Department(new Manager("Alex"), "Engineering", 250));
		departments.add(new Department(new Manager("Amy"), "Operations", 25));
		departments.add(new Department(new Manager("Ben"), "HR", 20));
		departments.add(new Department(new Manager("John"), "Accounting", 15));
		departments.add(new Department(new Manager("Sheldon"), "Board", 10));
		departments.add(new Department(new Manager("Sheldon"), "Customer Support", 30));
		return departments;
	}
}
