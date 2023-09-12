package Model;

public class Pet {
	
	private int petID;
	private String petCategory;
	private String petType;
	private String color;
	private int age;
	private double price;
	private boolean isVaccinated;
	private String foodHabits;
	
	
	public Pet(int petID, String petCategory, String petType, String color, int age, double price, boolean isVaccinated,
			String foodHabits) {
		super();
		this.petID = petID;
		this.petCategory = petCategory;
		this.petType = petType;
		this.color = color;
		this.age = age;
		this.price = price;
		this.isVaccinated = isVaccinated;
		this.foodHabits = foodHabits;
	}


	public int getPetID() {
		return petID;
	}


	public String getPetCategory() {
		return petCategory;
	}


	public String getPetType() {
		return petType;
	}


	public String getColor() {
		return color;
	}


	public int getAge() {
		return age;
	}


	public double getPrice() {
		return price;
	}


	public boolean isVaccinated() {
		return isVaccinated;
	}


	public String getFoodHabits() {
		return foodHabits;
	}


	@Override
	public String toString() {
		return "Pet [petID=" + petID + ", "
				+ "petCategory=" + petCategory + ", "
				+ "petType=" + petType + ", "
				+ "color=" + color
				+ ", age=" + age + ", "
				+ "price=" + price + ", "
				+ "isVaccinated=" + isVaccinated + ", "
				+ "foodHabits=" + foodHabits
				+ "]";
	}

}
