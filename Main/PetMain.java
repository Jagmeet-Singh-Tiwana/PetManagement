package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.List;
import DAO.PetDao;
import DAO.TestConnection;
import Model.Pet;
public class PetMain {

	
	public static void main(String args[]) 
	{
		System.out.println("Code starting again");	
		new TestConnection();
	

		Scanner scanner = new Scanner(System.in);

        PetDao petdao = new PetDao(); 



        while (true) {

            System.out.println("Welcome to the Pet Store System");

            System.out.println("1. Add PetDetails");

            System.out.println("2. Delete Pet");

            System.out.println("3. Update pet price and Vaccination Status");

            System.out.println("4. View All pets");

            System.out.println("5. Count pet By category");

            System.out.println("6. Find by price");

            System.out.println("7. Find by vaccinatotion ");

            System.out.println("8. Exit");

            System.out.println("Please select an option: ");



            int choice = scanner.nextInt();

            scanner.nextLine(); 


            switch (choice) {

                case 1:

                    System.out.print("Enter pet details (petId, petCategory, petType, color, age, price, isVaccinated, foodHabits): ");

                    String petdetails = scanner.nextLine();

                    String[] petInfo = petdetails.split(",");

                    if (petInfo.length == 8) {

                        Pet pet = new Pet(

                            Integer.parseInt(petInfo[0].trim()),

                            petInfo[1].trim(),

                            petInfo[2].trim(),

                            petInfo[3].trim(),

                            Integer.parseInt(petInfo[4].trim()),

                            Double.parseDouble(petInfo[5].trim()),

                            Boolean.parseBoolean(petInfo[6].trim()),

                            petInfo[7].trim()

                        );

                        petdao.addPetDetails(pet);

                        System.out.println("Pet details added successfully!");

                    } else {

                        System.out.println("Invalid input format. Please try again.");

                    }

                    break;
                   
                case 2:
                	
                	System.out.print("Enter pet ID to delete: ");
                    int petIdToDelete = scanner.nextInt();
                    scanner.nextLine(); 

                    int deleteResult = petdao.deletePetDetails(petIdToDelete);
                    if (deleteResult > 0) {
                        System.out.println("Pet deleted successfully.");
                    } else {
                        System.out.println("Failed to delete pet.");
                    }
                    break;
                    
                case 3:
                	
                	System.out.print("Enter pet ID: ");
                    int petId = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); 

                    System.out.print("Is the pet vaccinated (true/false): ");
                    boolean isVaccinated = scanner.nextBoolean();
                    scanner.nextLine(); 

                    boolean updateResult = petdao.updatePetPriceAndVaccinationStatus(newPrice, isVaccinated, petId);
                    if (updateResult) {
                    	System.out.println("Pet updated successfully.");
                    } else {
                        System.out.println("Failed to update pet.");
                    }
                	break;
                case 4:
                	
                	List<Pet> allPets = petdao.showAllPets(); 

                    System.out.println("All Pets:");

                    for (Pet pet : allPets) {
                        System.out.println(pet);
                    }

                	break;
                	
                case 5:
                	 System.out.println("Enter pet category to count: ");
                     String categoryToCount = scanner.nextLine();
                     int count = petdao.countPetsByCategory(categoryToCount); 
                     if(count>0)
                     {
                    	 System.out.println("Number of pets in " + categoryToCount + ": " + count);
                     }
                     else
                     {
                    	 System.out.println("This category doesn't in table");
                     }
                	break;
                	
                case 6:
                	System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    scanner.nextLine(); 

                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    scanner.nextLine(); 

                    List<Pet> matchingPets = petdao.searchByPrice(minPrice, maxPrice); 

                    System.out.println("Matching Pets:");
                    for (Pet pet : matchingPets) {
                        System.out.println(pet);
                    }
                    
                	break;
                case 7:
                	
                	System.out.print("Enter pet type to count for (e.g., 'Dog', 'Cat'): ");
                    String petTypeToCount = scanner.nextLine();
                    int count1 = petdao.countByVaccinationStatusForPetType(petTypeToCount); 
                    System.out.println("Number of vaccinated " + petTypeToCount + "s: " + count1);
                    
                	break;
                case 8:
                	 System.out.println("Exiting Pet Store System.");
                	 System.out.println("\n");
                     System.exit(0);
                     break;
                	
                default:
                    System.out.println("Invalid choice. Please select a valid option.");   
           }
      }
	}
}


