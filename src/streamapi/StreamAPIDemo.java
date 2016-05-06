package streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIDemo {

	public static Predicate<Department> isLarge() {
		return p -> p.getHeadCount() > 50;
	}

	public static Company company;
	public static ArrayList<Department> departments;
	public static ArrayList<Company> companies;

	public static void main(String[] args) {

		initialize();

		demonstrateStreamDeclaration();

		countElementsInList();

		listLargeDepartments();

		showDepartmentNamesInUpper();

		showManagersOfAllCompanies();

		showHeadCountOfCompany();

		sortDepartments();

		showAnyLargeDepartment();

		hasLargeDepartments();

		collectorsDemo();

	}

	private static void demonstrateStreamDeclaration() {

		// Create Streams from Values
		Stream<String> stream = Stream.of("Streams");
		stream.forEach(System.out::println);
		stream = Stream.of("Create", "Streams", "from", "Values");
		stream.forEach(System.out::println);

		// Create streams from Functions
		Stream<Long> tenNaturalNumbers = Stream.iterate(1L, n -> n + 1).limit(10);

		tenNaturalNumbers.forEach(System.out::println);

		// Streams from Regex
		String str = "XML,CSS,HTML";
		Pattern.compile(",").splitAsStream(str).forEach(System.out::println);

		// Streams from files
		Path path = Paths.get("/Users/dranga/java8/Java8Demo/src/functionalinterfaces/Button.java");
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void countElementsInList() {
		long size = departments.stream().count();
		System.out.println(size);
	}

	private static void listLargeDepartments() {
		// filter - eliminates the stream based on condition provided
		departments.stream()
		.filter(isLarge())
		.forEach(x -> System.out.println(x));
	}

	private static void showDepartmentNamesInUpper() {
		// map applies a function to each element in the stream
		departments.stream()
		.map(x -> x.getDepartmentName().toUpperCase())
		.forEach(System.out::println);
	}

	private static void showManagersOfAllCompanies() {
		Set<Department> depts =
				companies.stream()
						 .map(d -> d.getDepartments()).flatMap(ds -> ds.stream())
						 .collect(Collectors.toSet());
		depts.forEach(d -> System.out.println(d.getDepartmentName()));

	}

	private static void showHeadCountOfCompany() {
		int total = departments.stream()
				.map(d -> d.getHeadCount())
				.reduce(0, Integer::sum);
		System.out.println("Total headcount:" + total);

		// Get total head count of large departments
		total = departments.stream()
				.filter(d -> d.getHeadCount() > 50)
				.map(d -> d.getHeadCount())
				.reduce(0,Integer::sum);
		System.out.println("Head count of large companies" + total);

	}

	private static void sortDepartments() {
		departments.stream()
		.sorted((department1, department2) -> Integer.compare(department1.getHeadCount(), department2.getHeadCount()))
		.forEach(System.out::println);

	}

	private static void collectorsDemo() {
		List<String> departmentNames =
				departments.stream()
						   .map(d -> d.getDepartmentName())
						   .collect(Collectors.toList());
		departmentNames.stream()
					   .forEach(System.out::println);
	}

	private static void showAnyLargeDepartment() {
		Optional<Department> anyDepartment =
				departments.stream()
						   .filter(isLarge())
						   .findAny();
		if (anyDepartment.isPresent())
			System.out.println(anyDepartment.get());
	}

	private static void hasLargeDepartments() {
		boolean flag = departments.stream().anyMatch(isLarge());
		if (flag)
			System.out.println("There are large departments in this company.");
	}

	private static void initialize() {

		ArrayList<Department> departments1 = new ArrayList<>();
		departments1.add(new Department(new Manager("Jack"), "Marketing", 50));
		departments1.add(new Department(new Manager("Jane"), "Sales", 100));
		departments1.add(new Department(new Manager("Jill"), "Finance", 50));
		departments1.add(new Department(new Manager("Alex"), "Engineering", 250));
		departments1.add(new Department(new Manager("Amy"), "Operations", 25));
		departments1.add(new Department(new Manager("Ben"), "HR", 20));
		departments1.add(new Department(new Manager("John"), "Accounting", 15));
		departments1.add(new Department(new Manager("Sheldon"), "Customer Support", 30));
		departments1.add(new Department(new Manager("Sheldon"), "Board", 10));

		Company company1 = new Company(departments1);

		ArrayList<Department> departments2 = new ArrayList<>();
		departments2.add(new Department(new Manager("Jack"), "Labor", 500));
		departments2.add(new Department(new Manager("Amy"), "Manufacturing", 25));
		departments2.add(new Department(new Manager("Ben"), "Supply", 20));
		departments2.add(new Department(new Manager("John"), "HR", 15));
		departments2.add(new Department(new Manager("Sheldon"), "Board", 10));
		departments2.add(new Department(new Manager("Sheldon"), "Retail", 30));

		Company company2 = new Company(departments2);

		company = company1;
		departments = departments1;
		companies = new ArrayList<>(Arrays.asList(company1, company2));

	}
}
