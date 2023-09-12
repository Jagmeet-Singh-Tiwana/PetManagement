package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Pet;

public class PetDao {
	
	
	//private static List<Pet> list;
	public PetDao()
	{
		
	}

	public Connection getConnection() {
		Connection con=null;
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver"); //registration
			 con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger"); //connection
		 }catch(Exception e) {
			 System.out.println("error in connection");
		 }
		 return con;
	 }
	
	public int addPetDetails(Pet pet) {
		 int count=0;
		 
		 String insertsql = "INSERT INTO PETS (petId, petCategory, petType, color, age, price, isVaccinated, foodHabits) VALUES (?, ?, ?,?,?,?,?,?)";
		 try {
			 PreparedStatement pst=getConnection().prepareStatement(insertsql);
			 pst.setInt(1, pet.getPetID());
			 pst.setString(2, pet.getPetCategory());
			 pst.setString(3, pet.getPetType());
			 pst.setString(4, pet.getColor());
			 pst.setInt(5, pet.getAge());
			 pst.setDouble(6, pet.getPrice());
			 pst.setBoolean(7, pet.isVaccinated());
			 pst.setString(8, pet.getFoodHabits());
			 count= pst.executeUpdate();
			
		 }catch(Exception e) {
			 System.out.println("error");
		 }
		 return count;
	 }
	
	public int deletePetDetails(int petId) {
		
		String DELETE_PET = "DELETE FROM PETS WHERE petId = ?";
        try {
             PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_PET);
            preparedStatement.setInt(1, petId);

            return preparedStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
	
	public boolean updatePetPriceAndVaccinationStatus(double price, boolean isVaccinated, int petId) {
		
		String UPDATE_PET = "UPDATE pets SET price = ?, isVaccinated = ? WHERE petId = ?";
        try {
             PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_PET);
            preparedStatement.setDouble(1, price);
            preparedStatement.setBoolean(2, isVaccinated);
            preparedStatement.setInt(3, petId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public int countPetsByCategory(String categoryToCount) {
        String countSQL = "SELECT COUNT(*) FROM PETS WHERE petCategory = ?";
        int count = 0;
        try {
             PreparedStatement preparedStatement = getConnection().prepareStatement(countSQL);

            preparedStatement.setString(1, categoryToCount);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        return count;
    }
	
	
	 public int countByVaccinationStatusForPetType(String petTypeToCount) {
	        String countSQL = "SELECT COUNT(*) FROM pet_table WHERE pet_type = ? AND is_vaccinated = ?";
	        int count = 0;

	        try {
	             PreparedStatement preparedStatement = getConnection().prepareStatement(countSQL);

	            preparedStatement.setString(1, petTypeToCount);
	            preparedStatement.setBoolean(2, true); // Count only vaccinated pets

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    count = resultSet.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return count;
	    }
	 
	 public List<Pet> searchByPrice(double minPrice, double maxPrice) {
	        List<Pet> matchingPets = new ArrayList<>();
	        String searchSQL = "SELECT * FROM PETS WHERE price >= ? AND price <= ?";
	        
	        try {
	             PreparedStatement preparedStatement = getConnection().prepareStatement(searchSQL);

	            preparedStatement.setDouble(1, minPrice);
	            preparedStatement.setDouble(2, maxPrice);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    int petId = resultSet.getInt("petID");
	                    String petCategory = resultSet.getString("petCategory");
	                    String petType = resultSet.getString("petType");
	                    String color = resultSet.getString("color");
	                    int age = resultSet.getInt("age");
	                    double price = resultSet.getDouble("price");
	                    boolean isVaccinated = resultSet.getBoolean("isVaccinated");
	                    String foodHabits = resultSet.getString("foodHabits");

	                    Pet pet = new Pet(petId, petCategory, petType, color, age, price, isVaccinated, foodHabits);
	                    matchingPets.add(pet);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return matchingPets;
	    }
	 
	 public List<Pet> showAllPets() {
	        List<Pet> allPets = new ArrayList<>();
	        String selectSQL = "SELECT * FROM PETS";

	        try {
	             PreparedStatement preparedStatement = getConnection().prepareStatement(selectSQL);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    int petId = resultSet.getInt("petId");
	                    String petCategory = resultSet.getString("petCategory");
	                    String petType = resultSet.getString("petType");
	                    String color = resultSet.getString("color");
	                    int age = resultSet.getInt("age");
	                    double price = resultSet.getDouble("price");
	                    boolean isVaccinated = resultSet.getBoolean("isVaccinated");
	                    String foodHabits = resultSet.getString("foodHabits");
	                    
	                    Pet pet = new Pet(petId, petCategory, petType, color, age, price, isVaccinated, foodHabits);
	                    allPets.add(pet);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return allPets;
	 }
}
