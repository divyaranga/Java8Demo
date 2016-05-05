package functionalinterfaces;

interface Clickable {
	default void click() {
		System.out.println("click");
	}

	default void print() {
		System.out.println("Clickable");
	}
	static void action(){
		System.out.println("Clicked button");
	}
}

interface Accessible {
	default void access() {
		System.out.println("access");
	}

	default void print() {
		System.out.println("Accessible");
	}
}

// Avoid ambiguity from implementing multiple interfaces by overriding methods with same name
public class Button implements Clickable, Accessible {

	// Compilation error when not overridden
	@Override
	public void print() {
		Accessible.super.print();
		Clickable.super.print();
	}

	public static void main(String[] args) {
		Button button = new Button();
		Clickable.action();
		button.click();
		button.access();
		button.print();
	}

}
