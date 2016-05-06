package optionaldemo;

import java.util.Optional;

public class CompanyDemo {
	public static void main(String[] args) {

		Company company = new Company();

		Optional<Manager> manager1 = Optional.of(new Manager("Jane"));
		Optional<Manager> manager2 = Optional.ofNullable(new Manager("Jack"));
		Department department1 = new Department(manager1, "Accounting");
		Department department2 = new Department(manager2, "Engineering");
		Department department3 = new Department("Sales");

		company.add(department1);
		company.add(department2);
		company.add(department3);

		for (Department department : company.getDepartments()) {

			if (department.getManager().isPresent()) {
				System.out.println("Manager " + department.getManager().get());
			}


			System.out.println("Department Name " + department.getDepartmentName());

			//System.out.println("Manager " + department.getManager().orElse(new Manager("John")));

			//Optional<Manager> startsWithJ = department.getManager().filter(x->x.getName().startsWith("J"));
			//System.out.println(startsWithJ.orElse(new Manager("No manager")).getName());

			//department.getManager().orElseThrow(()->new IllegalStateException("Manager cannot be null"));
		}
	}
}
