package optionaldemo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Company {
	private List<Department> departments = new ArrayList<>();

	public void add(Department department) {
		departments.add(department);

	}

	public List<Department> getDepartments() {
		return departments;
	}

}

class Department {
	private Optional<Manager> manager = Optional.empty();
	private String departmentName;

	public Department(Optional<Manager> manager, String departmentName) {
		this.manager = manager;
		this.departmentName = departmentName;
	}

	public Department(String departmentName) {
		this.departmentName = departmentName;
	}

	Optional<Manager> getManager() {
		return manager;
	}

	void setManager(Optional<Manager> manager) {
		this.manager = manager;
	}

	void appendDivision() {
		manager.get().setName(manager.get().toString() + " ShopStyle");
	}


	String getDepartmentName() {
		return departmentName;
	}

	void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}

class Manager {
	private String name;

	public Manager(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}