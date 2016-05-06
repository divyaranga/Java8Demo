package streamapi;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private List<Department> departments = new ArrayList<>();

	public Company(List<Department> departments) {
		this.departments = departments;
	}

	public void add(Department department) {
		departments.add(department);

	}

	public List<Department> getDepartments() {
		return departments;
	}

}

class Department {
	private Manager manager;
	private String departmentName;
	private Integer headCount = 50;

	public Department(Manager manager, String departmentName) {
		this.manager = manager;
		this.departmentName = departmentName;
	}

	public Department(Manager manager, String departmentName, Integer headCount) {
		this.manager = manager;
		this.departmentName = departmentName;
		this.headCount = headCount;
	}

	public Department(String departmentName) {
		this.departmentName = departmentName;
	}

	Manager getManager() {
		return manager;
	}

	void setManager(Manager manager) {
		this.manager = manager;
	}

	String getDepartmentName() {
		return departmentName;
	}

	void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getHeadCount() {
		return headCount;
	}

	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}

	@Override
	public String toString() {
		return "Department Name: " + departmentName + "\n Manager name: " + manager + "\n HeadCount: " + headCount;
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